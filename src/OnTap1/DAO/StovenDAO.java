package OnTap1.DAO;

import FinalJava.Utils.DefaultMessage;
import OnTap1.Entity.GasStoven;
import OnTap1.Entity.InfrareStoven;
import OnTap1.Entity.MagneticStoven;
import OnTap1.Entity.Stoven;
import OnTap1.Utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StovenDAO {
    Connection connection;
    PreparedStatement preparedStatement;

    public boolean addStoven(List<Stoven> stovenList) {
        try{
            String sql = "INSERT INTO Stoven(Type, StovenID, Brand, ProductCode, ProductionCountry, ProductionYear, NoOfStoven, Price, Fire, Wattage, Timer, CookMode, SalePrice, WarrantYear) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            connection = DBUtils.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            stovenList.forEach(stoven -> {
                try {
                    preparedStatement.setInt(1, stoven.getType());
                    preparedStatement.setString(2, stoven.getStovenID());
                    preparedStatement.setString(3, stoven.getBrand());
                    preparedStatement.setString(4, stoven.getProductCode());
                    preparedStatement.setString(5, stoven.getProductionCountry());
                    preparedStatement.setInt(6, stoven.getProductionYear());
                    preparedStatement.setInt(7, stoven.getNoOfStoven());
                    preparedStatement.setLong(8, stoven.getPrice());
                    preparedStatement.setNull(13, Types.VARCHAR);
                    preparedStatement.setNull(14, Types.VARCHAR);

                    if(stoven instanceof GasStoven){
                        preparedStatement.setString(9, ((GasStoven)stoven).getFire());
                        preparedStatement.setNull(10, Types.VARCHAR);
                        preparedStatement.setNull(11, Types.VARCHAR);
                        preparedStatement.setNull(12, Types.INTEGER);
                    }
                    else if(stoven instanceof MagneticStoven){
                        preparedStatement.setNull(9, Types.INTEGER);
                        preparedStatement.setFloat(10, ((MagneticStoven) stoven).getWatt());
                        preparedStatement.setString(11, ((MagneticStoven)stoven).getTimer());
                        preparedStatement.setNull(12, Types.INTEGER);
                    }
                    else if(stoven instanceof InfrareStoven){
                        preparedStatement.setNull(9, Types.INTEGER);
                        preparedStatement.setFloat(10, ((InfrareStoven)stoven).getWatt());
                        preparedStatement.setNull(11, Types.VARCHAR);
                        preparedStatement.setString(12, ((InfrareStoven)stoven).getCookMode());
                    }
                    preparedStatement.addBatch();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
            connection.setAutoCommit(false);
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            try{
                connection.rollback();
            } catch (SQLException sqlException){
                System.out.println(sqlException.getMessage());
            }
            return false;
        } finally {
            try{
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean isExistStoven(String stovenID) {
        try{
            String sql = "SELECT StovenID FROM Stoven WHERE StovenID = ?";
            connection = DBUtils.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, stovenID);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateStoven(String stovenId, int salePricePercent, int warrantYear) {
        try{
            String sql = "Update Stoven Set SalePrice = Price - Price*?/100, WarrantYear = ? WHERE StovenID = ?";
            connection = DBUtils.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, salePricePercent);
            preparedStatement.setInt(2, warrantYear);
            preparedStatement.setString(3, stovenId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println(DefaultMessage.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Stoven> getStovenList() {
        List<Stoven> stovenList = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Stoven ORDER BY StovenID";
            connection = DBUtils.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int type = resultSet.getInt("Type");

                if(type == 1){
                    Stoven stoven = new GasStoven();
                    setCommonData(stoven, resultSet);
                    GasStoven gasStoven = (GasStoven) stoven;
                    gasStoven.setFire(resultSet.getString(9));
                    stovenList.add(stoven);
                } else if(type == 2){
                    Stoven stoven = new MagneticStoven();
                    setCommonData(stoven, resultSet);
                    MagneticStoven magStoven = (MagneticStoven) stoven;
                    magStoven.setWatt(resultSet.getFloat(10));
                    magStoven.setTimer(resultSet.getString(11));
                    stovenList.add(magStoven);
                } else if(type == 3){
                    Stoven stoven = new InfrareStoven();
                    setCommonData(stoven, resultSet);
                    InfrareStoven infrareStoven = (InfrareStoven) stoven;
                    infrareStoven.setWatt(resultSet.getFloat(10));
                    infrareStoven.setCookMode(resultSet.getString(12));
                    stovenList.add(infrareStoven);
                }
            }
            return stovenList;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return stovenList;
        }
    }

    public void setCommonData(Stoven stoven, ResultSet rs){
        try{
            stoven.setType(rs.getInt(1));
            stoven.setStovenID(rs.getString(2));
            stoven.setBrand(rs.getString(3));
            stoven.setProductCode(rs.getString(4));
            stoven.setProductionCountry(rs.getString(5));
            stoven.setProductionYear(rs.getInt(6));
            stoven.setNoOfStoven(rs.getInt(7));
            stoven.setPrice(rs.getLong(8));
            stoven.setSalePrice(rs.getLong(13));
            stoven.setWarrantYear(rs.getInt(14));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean deleteStoven() {
        try{
            String sql = "DELETE FROM Stoven WHERE ProductionYear < 2019 AND ProductionCountry = 'Sweden'";
            connection = DBUtils.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
