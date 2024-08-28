package OnTap.DAO;

import OnTap.Entity.ForeignPhone;
import OnTap.Entity.HomePhone;
import OnTap.Entity.OldPhone;
import OnTap.Entity.Phone;
import OnTap.Utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDAO {
    Connection connection;
    PreparedStatement preparedStatement;

    public boolean insertPhone(List<Phone> phoneList){
        try{
            String sql = "INSERT INTO Phone(ID, Name, ScreenSize, Chipset, Ram, Storage, Price, Manufacturer, Warranty, RangePhone, Country, Status, BodyStatus, PriceWarranty) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            connection = DBUtils.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            phoneList.forEach(phone -> {
                try {
                    preparedStatement.setString(1, phone.getID());
                    preparedStatement.setString(2, phone.getName());
                    preparedStatement.setFloat(3, phone.getScreenSize());
                    preparedStatement.setString(4, phone.getChipset());
                    preparedStatement.setInt(5, phone.getRam());
                    preparedStatement.setInt(6, phone.getStorage());
                    preparedStatement.setDouble(7, phone.getPrice());
                    preparedStatement.setString(8, phone.getManufacturer());

                    if(phone instanceof HomePhone){
                        preparedStatement.setInt(9, ((HomePhone)phone).getWarranty());
                        preparedStatement.setInt(10, ((HomePhone)phone).getRange());
                        preparedStatement.setNull(11, Types.VARCHAR);
                        preparedStatement.setNull(12, Types.INTEGER);
                        preparedStatement.setNull(13, Types.INTEGER);
                        preparedStatement.setNull(14, Types.INTEGER);
                    }
                    else if(phone instanceof ForeignPhone){
                        preparedStatement.setNull(9, Types.INTEGER);
                        preparedStatement.setNull(10, Types.INTEGER);
                        preparedStatement.setString(11, ((ForeignPhone)phone).getCountry());
                        preparedStatement.setNull(12, Types.INTEGER);
                        preparedStatement.setNull(13, Types.INTEGER);
                        preparedStatement.setDouble(14, ((ForeignPhone)phone).getPriceWarranty());
                    }
                    else if(phone instanceof OldPhone){
                        preparedStatement.setNull(9, Types.INTEGER);
                        preparedStatement.setNull(10, Types.INTEGER);
                        preparedStatement.setNull(11, Types.VARCHAR);
                        preparedStatement.setInt(12, ((OldPhone)phone).getStatus());
                        preparedStatement.setInt(13, ((OldPhone)phone).getBody());
                        preparedStatement.setDouble(14, ((OldPhone)phone).getPriceWarranty());
                    }
                    preparedStatement.addBatch();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
            connection.setAutoCommit(false);
            System.out.println(1);
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

    public List<Phone> getPhoneListByPrice(long minPrice, long maxPrice){
        String sql = "Select * From Phone where Price between ? and ?";
        List<Phone> phoneList = new ArrayList<>();
        try(Connection connection1 = DBUtils.getInstance().getConnection()) {
            preparedStatement = connection1.prepareStatement(sql);
            preparedStatement.setLong(1, minPrice);
            preparedStatement.setLong(2, maxPrice);
            ResultSet resultSet = preparedStatement.executeQuery();
            Phone phone = null;
            while(resultSet.next()){
                phone = getPhoneResultSet(resultSet);
                phoneList.add(phone);
            }
            return phoneList;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Phone getPhoneResultSet(ResultSet rs) throws SQLException {
        Phone phone = null;
        String phoneID = rs.getString("ID");

        if(phoneID.startsWith("CH")){
            phone = new HomePhone();
            setComomData(phone, rs);
            HomePhone homePhone = (HomePhone)phone;
            homePhone.setRange(Integer.parseInt(rs.getString("RangePhone")));
            homePhone.setWarranty(Integer.parseInt(rs.getString("Warranty")));
        }
        else if(phoneID.startsWith("XT")){
            phone = new ForeignPhone();
            setComomData(phone, rs);
            ForeignPhone foreignPhone = (ForeignPhone)phone;
            foreignPhone.setCountry(rs.getString("Country"));
            foreignPhone.setPriceWarranty(Double.parseDouble(rs.getString("PriceWarranty")));
        }
        else if(phoneID.startsWith("OD")){
            phone = new OldPhone();
            setComomData(phone, rs);
            OldPhone oldPhone = (OldPhone)phone;
            oldPhone.setBody(Integer.parseInt(rs.getString("BodyStatus")));
            oldPhone.setStatus(Integer.parseInt(rs.getString("Status")));
            oldPhone.setPriceWarranty(Double.parseDouble(rs.getString("PriceWarranty")));
        }

        return phone;
    }

    public boolean deletePhone(String ID){
        try(Connection connection = DBUtils.getInstance().getConnection()) {
            preparedStatement = connection.prepareStatement("Delete From Phone where ID = ?");
            preparedStatement.setString(1, ID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void setComomData(Phone phone, ResultSet rs) throws SQLException {
        phone.setID(rs.getString("ID"));
        phone.setName(rs.getString("Name"));
        phone.setScreenSize(Float.parseFloat(rs.getString("ScreenSize")));
        phone.setChipset(rs.getString("Chipset"));
        phone.setRam(Integer.parseInt(rs.getString("Ram")));
        phone.setStorage(Integer.parseInt(rs.getString("Storage")));
        phone.setPrice(Double.parseDouble(rs.getString("Price")));
        phone.setManufacturer(rs.getString("Manufacturer"));
    }
}
