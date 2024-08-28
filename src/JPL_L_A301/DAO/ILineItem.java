package JPL_L_A301.DAO;

import JPL_L_A301.Entity.LineItem;

import java.sql.SQLException;
import java.util.List;

public interface ILineItem {
    public List<LineItem> getAllItemsByOrderId(int orderId) throws SQLException;
    public boolean addLineItem(LineItem lineItem) throws SQLException;
}
