package JPL_L_A301.DAO.Impl;

import JPL_L_A301.DAO.CustomerDAO;
import JPL_L_A301.Entity.Customer;
import JPL_L_A301.Entity.Order;
import JPL_L_A301.Utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private CallableStatement callableStatement = null;
    private ResultSet results = null;

    @Override
    public List<Customer> getAllCustomer(){
        try(Connection connection = DBUtils.getInstance().getConnection()) {
            String sql = "select customers.* from customers JOIN Orders ON Customers.customer_id = Orders.customer_id\n" +
                    "GROUP BY customer_id";
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (results.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(results.getInt("customer_id"));
                customer.setCustomerName(results.getString("customer_name"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Order> getAllOrdersByCustomerId(int customerId){
        try(Connection connection = DBUtils.getInstance().getConnection()) {
            String sql = "SELECT *\n" +
                    "FROM Orders\n" +
                    "WHERE customer_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);

            results = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (results.next()) {
                Order order = new Order();
                order.setCustomerId(results.getInt("customer_id"));
                order.setOrderDate(results.getDate("order_date"));
                order.setEmployeeId(results.getInt("customer_id"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try{
                preparedStatement.close();
                results.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean addCustomer(Customer customer){
        try(Connection connection = DBUtils.getInstance().getConnection()) {
            String sql = "{call procedure_name(?, ?)}";
            callableStatement = connection.prepareCall(sql);

            callableStatement.setString(1, customer.getCustomerName());
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            int rowEffect = callableStatement.getInt(2);
            return rowEffect > 0;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(int customerId){
        try(Connection connection = DBUtils.getInstance().getConnection()) {
            String sql = "{call deleteCustomer(?,?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, customerId);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            int rowEffect = callableStatement.getInt(2);
            return rowEffect > 0;
        }catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
