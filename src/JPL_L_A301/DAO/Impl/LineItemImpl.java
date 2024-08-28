package JPL_L_A301.DAO.Impl;

import JPL_L_A301.DAO.ILineItem;
import JPL_L_A301.Entity.LineItem;
import JPL_L_A301.Utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineItemImpl implements ILineItem {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

    @Override
    public List<LineItem> getAllItemsByOrderId(int orderId) throws SQLException {
        connection = DBUtils.getInstance().getConnection();
        String sql = "select * from LineItems where order_id=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, orderId);
        results = preparedStatement.executeQuery();

        List<LineItem> list = new ArrayList<>();
        LineItem lineItem;
        while (results.next()) {
            lineItem = new LineItem();
            lineItem.setOrderId(results.getInt("order_id"));
            lineItem.setProductId(results.getInt("product_id"));
            lineItem.setQuantity(results.getInt("quantity"));
            lineItem.setPrice(results.getDouble("price"));
            list.add(lineItem);
        }
        return list;
    }

    @Override
    public boolean addLineItem(LineItem lineItem){
        try(Connection connection = DBUtils.getInstance().getConnection()){
            // Check exists order
            String sqlFindOrder = "Select order_id from Orders Where order_id = ?";
            preparedStatement = connection.prepareStatement(sqlFindOrder);
            preparedStatement.setInt(1, lineItem.getOrderId());
            if(!preparedStatement.executeQuery().next()){
                System.out.println("Order ID not found!");
                return false;
            }

            // Check exists product
            String sqlFindProductPrice = "Select price from Products where product_id=?";
            preparedStatement = connection.prepareStatement(sqlFindProductPrice);
            preparedStatement.setInt(1, lineItem.getProductId());
            results = preparedStatement.executeQuery();
            if(!results.next()){
                System.out.println("Product not found!");
                return false;
            }
            double price = 0;
            price = results.getDouble("price");

            // Insert new lineItem
            String sql = "Insert into LineItems(order_id, product_id, quantity, price) values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, lineItem.getOrderId());
            preparedStatement.setInt(2, lineItem.getProductId());
            preparedStatement.setInt(3, lineItem.getQuantity());
            preparedStatement.setDouble(4, price);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
