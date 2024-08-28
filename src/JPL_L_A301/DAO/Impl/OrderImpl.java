package JPL_L_A301.DAO.Impl;

import JPL_L_A301.DAO.OrderDAO;
import JPL_L_A301.Entity.LineItem;
import JPL_L_A301.Entity.Order;
import JPL_L_A301.Utils.DBUtils;
import JPL_L_A301.Utils.Helper;

import java.sql.*;
import java.util.List;
import java.util.OptionalDouble;

public class OrderImpl implements OrderDAO {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private CallableStatement callableStatement = null;

    @Override
    public boolean insertOrder(Order order) throws SQLException {
        try (Connection connection = DBUtils.getInstance().getConnection()){
            String sql = "insert into Orders values(null,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDate(1, Date.valueOf(Helper.convertDateToString(order.getOrderDate())));
            preparedStatement.setInt(2, order.getCustomerId());
            preparedStatement.setInt(3, order.getEmployeeId());
            preparedStatement.setDouble(4, order.getTotal());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateOrderTotal(int orderId){
        try (Connection connection = DBUtils.getInstance().getConnection()){
            String sql = "Update Orders SET total = ? WHERE order_id = ?";
            preparedStatement = connection.prepareStatement(sql);

            LineItemImpl li = new LineItemImpl();
            List<LineItem> lineItemList = li.getAllItemsByOrderId(orderId);

            if(lineItemList == null || lineItemList.isEmpty()) return false;
            double total = 0;
//        total = lineItemList.stream().reduce(0, (sum, item) -> sum + item.getPrice());
//        lineItemList.stream().map(item -> total += item.getPrice());
//        lineItemList.forEach(item -> total += item.getPrice());

            for (LineItem lineItem : lineItemList) {
                total += lineItem.getPrice() * lineItem.getQuantity();
            }
            preparedStatement.setDouble(1, total);
            preparedStatement.setInt(2, orderId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public double computeOrderTotal(int orderId){
        try (Connection connection = DBUtils.getInstance().getConnection()){
            String sql = "{? = call computeOrderTotal(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.registerOutParameter(1, Types.DOUBLE);
            callableStatement.setInt(2, orderId);
            callableStatement.execute();
            return callableStatement.getDouble(1);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
