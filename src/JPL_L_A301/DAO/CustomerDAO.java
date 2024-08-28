package JPL_L_A301.DAO;

import JPL_L_A301.Entity.Customer;
import JPL_L_A301.Entity.Order;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getAllCustomer() throws Exception;

    public List<Order> getAllOrdersByCustomerId(int customerId);

    public boolean addCustomer(Customer customer);

    /*
    BEGIN
        DELETE FROM LineItems
        WHERE LineItems.order_id IN (
            SELECT Orders.order_id
            FROM Orders
            WHERE Orders.customer_id = customerId
       );
       DELETE FROM Orders
       WHERE Orders.customer_id = customerId;
       DELETE FROM Customers
       WHERE Customers.customer_id = customerId;
       SELECT ROW_COUNT() INTO row;
    END
    */
    public boolean deleteCustomer(int customerId);
}
