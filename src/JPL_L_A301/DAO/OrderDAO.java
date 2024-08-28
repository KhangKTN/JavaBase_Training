package JPL_L_A301.DAO;

import JPL_L_A301.Entity.Order;

public interface OrderDAO {
    public boolean insertOrder(Order order) throws Exception;

    public boolean updateOrderTotal(int orderId) throws Exception;

    /*
        DELIMITER &&
        CREATE FUNCTION computeOrderTotal (orderId int) RETURNS INT
        BEGIN
                DECLARE result INT;
                SET result = (
                    SELECT SUM(quantity * price)
                    FROM LineItems
                );
                RETURN result;
        END&&
        DELIMITER ;
    */
    public double computeOrderTotal(int orderId) throws Exception;
}
