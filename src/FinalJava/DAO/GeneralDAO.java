package FinalJava.DAO;

import FinalJava.Entity.DesktopPhone;
import FinalJava.Entity.KeypadPhone;
import FinalJava.Entity.Phone;
import FinalJava.Entity.SmartPhone;
import FinalJava.Utils.DBUtil;
import OnTap.Utils.DefaultMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public boolean insertData(List<Phone> phoneList){
        try{
            connection = DBUtil.getInstance().getConnection();
            String sql = "insert into Phone values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            phoneList.forEach(phone -> {
                try {
                    // Set common data
                    preparedStatement.setInt(1, phone.getType());
                    preparedStatement.setString(2, phone.getPhoneId());
                    preparedStatement.setString(3, phone.getColor());
                    preparedStatement.setString(4, phone.getBrand());
                    preparedStatement.setInt(5, phone.getProductionYear());
                    preparedStatement.setLong(6, phone.getPrice());
                    preparedStatement.setNull(7, Types.VARCHAR);
                    preparedStatement.setNull(8, Types.VARCHAR);

                    // Set various data
                    if(phone instanceof DesktopPhone){
                        preparedStatement.setString(9, ((DesktopPhone) phone).getSpeaker());
                        preparedStatement.setString(10, ((DesktopPhone) phone).getWire());
                        preparedStatement.setNull(11, Types.VARCHAR);
                        preparedStatement.setNull(12, Types.VARCHAR);
                        preparedStatement.setNull(13, Types.VARCHAR);
                        preparedStatement.setNull(14, Types.VARCHAR);
                        preparedStatement.setNull(15, Types.VARCHAR);
                        preparedStatement.setNull(16, Types.VARCHAR);
                    } else if(phone instanceof KeypadPhone){
                        preparedStatement.setNull(9, Types.VARCHAR);
                        preparedStatement.setNull(10, Types.VARCHAR);
                        preparedStatement.setString(11, ((KeypadPhone) phone).getOs());
                        preparedStatement.setInt(12, ((KeypadPhone) phone).getBatteryTime());
                        preparedStatement.setString(13, ((KeypadPhone) phone).getKeyboard());
                        preparedStatement.setNull(14, Types.VARCHAR);
                        preparedStatement.setNull(15, Types.VARCHAR);
                        preparedStatement.setNull(16, Types.VARCHAR);
                    } else if(phone instanceof SmartPhone){
                        preparedStatement.setNull(9, Types.VARCHAR);
                        preparedStatement.setNull(10, Types.VARCHAR);
                        preparedStatement.setString(11, ((SmartPhone) phone).getOs());
                        preparedStatement.setInt(12, ((SmartPhone) phone).getBatteryTime());
                        preparedStatement.setNull(13, Types.VARCHAR);
                        preparedStatement.setInt(14, ((SmartPhone) phone).getMemoryCard());
                        preparedStatement.setInt(15, ((SmartPhone) phone).getScreenSize());
                        preparedStatement.setString(16, ((SmartPhone) phone).getFingerPrint());
                    }
                    preparedStatement.addBatch();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                    System.out.println(DefaultMessage.ERROR_MESSAGE);
                }
            });

            connection.setAutoCommit(false);
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e){
            System.out.println(DefaultMessage.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(DefaultMessage.ERROR_MESSAGE);
            }
        }
    }

    public List<Phone> getAllData(){
        try{
            List<Phone> phoneList = new ArrayList<>();
            connection = DBUtil.getInstance().getConnection();
            String sql = "Select * From Phone";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Check type of to set data
                int type = resultSet.getInt("Type");

                if(type == 1){
                    Phone phone = new DesktopPhone();
                    setCommonData(phone, resultSet);
                    DesktopPhone desktopPhone = (DesktopPhone) phone;
                    desktopPhone.setSpeaker(resultSet.getString(9));
                    desktopPhone.setWire(resultSet.getString(10));
                    phoneList.add(phone);
                }
                else if(type == 2){
                    Phone phone = new KeypadPhone();
                    setCommonData(phone, resultSet);
                    KeypadPhone keypadPhone = (KeypadPhone) phone;
                    keypadPhone.setOs(resultSet.getString(11));
                    keypadPhone.setBatteryTime(resultSet.getInt(12));
                    keypadPhone.setKeyboard(resultSet.getString(13));
                    phoneList.add(phone);
                }
                else if(type == 3){
                    Phone phone = new SmartPhone();
                    setCommonData(phone, resultSet);
                    SmartPhone smartPhone = (SmartPhone) phone;
                    smartPhone.setOs(resultSet.getString(11));
                    smartPhone.setBatteryTime(resultSet.getInt(12));
                    smartPhone.setMemoryCard(resultSet.getInt(14));
                    smartPhone.setScreenSize(resultSet.getInt(15));
                    smartPhone.setFingerPrint(resultSet.getString(16));
                    phoneList.add(phone);
                }
            }
            return phoneList;
        } catch (SQLException e){
            System.out.println(DefaultMessage.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(DefaultMessage.ERROR_MESSAGE);
            }
        }
    }

    public void setCommonData(Phone phone, ResultSet resultSet){
        try{
            phone.setType(resultSet.getInt(1));
            phone.setPhoneId(resultSet.getString(2));
            phone.setBrand(resultSet.getString(3));
            phone.setColor(resultSet.getString(4));
            phone.setProductionYear(resultSet.getInt(5));
            phone.setPrice(resultSet.getLong(6));
            phone.setPromotionPrice(resultSet.getLong(7));
            phone.setWarrantyPeriod(resultSet.getInt(8));
        }catch (SQLException e){
            System.out.println(DefaultMessage.ERROR_MESSAGE);
        }
    }

    public boolean updatePromationPrice(long promotionPrice, String phoneId){
        try{
            connection = DBUtil.getInstance().getConnection();
            String sql = "Update Phone SET PromotionPrice = ? Where PhoneID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, promotionPrice);
            preparedStatement.setString(2, phoneId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println(DefaultMessage.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(DefaultMessage.ERROR_MESSAGE);
            }
        }
    }

    public boolean updateWarrantyPeriod(int warranty, String phoneId){
        try{
            connection = DBUtil.getInstance().getConnection();
            String sql = "Update Phone SET WarrantyPeriod = ? Where PhoneID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, warranty);
            preparedStatement.setString(2, phoneId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println(DefaultMessage.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(DefaultMessage.ERROR_MESSAGE);
            }
        }
    }
}
