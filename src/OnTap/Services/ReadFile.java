package OnTap.Services;

import Day1.Day1_Bai10;
import OnTap.DAO.PhoneDAO;
import OnTap.Entity.ForeignPhone;
import OnTap.Entity.HomePhone;
import OnTap.Entity.OldPhone;
import OnTap.Entity.Phone;
import OnTap.Utils.Exception.DuplicateException;
import OnTap.Utils.Exception.PhoneException;
import OnTap.Utils.Validate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    static List<Phone> phoneList = new ArrayList<>();


    public static void readFile() {
        File myFile = new File("./src/OnTap/phone.csv");
        try {
            if (myFile.exists() && myFile.canRead()) {
                Scanner myReader = new Scanner(myFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    try{
                        if(data != null && !data.trim().isEmpty()) setPhoneFromString(data);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                myReader.close();
            }
            phoneList.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setPhoneFromString(String phoneLine) throws Exception {
        String[] parts = phoneLine.split(",");
        String id = parts[0];
        if(!Validate.validIdPhone(id)) throw new PhoneException("ID " + id + " khong chinh xac");
        if(!Validate.validDuplicateId(id, phoneList)) throw new DuplicateException("ID " + id + " khong duoc trung");
        if(id.startsWith("CH")){
            Phone phone = new HomePhone();
            addCommonData(phone, parts);
            HomePhone homePhone = (HomePhone) phone;
            homePhone.setWarranty(Integer.parseInt(parts[8]));
            int range = Integer.parseInt(parts[9]);
            if(!Validate.validRangeAndStatus(range)) throw new PhoneException("range/status " + id + " khong chinh xac");
            homePhone.setRange(Integer.parseInt(parts[9]));

            phoneList.add(homePhone);
        }
        else if(id.startsWith("XT")){
            Phone phone = new ForeignPhone();
            addCommonData(phone, parts);
            ForeignPhone foreignPhone = (ForeignPhone) phone;
            foreignPhone.setCountry(parts[8]);
            foreignPhone.setPriceWarranty(foreignPhone.getPrice() * 0.06);

            phoneList.add(foreignPhone);
        }
        else if(id.startsWith("OD")){
            Phone phone = new OldPhone();
            addCommonData(phone, parts);
            OldPhone oldPhone = (OldPhone) phone;
            int number = Integer.parseInt(parts[8]);
            if(!Validate.validRangeAndStatus(number)) throw new PhoneException("range/status " + id + " khong chinh xac");
            oldPhone.setStatus(Integer.parseInt(parts[8]));
            oldPhone.setBody(Integer.parseInt(parts[9]));
            oldPhone.setPriceWarranty(oldPhone.getPrice() * 0.07);

            phoneList.add(oldPhone);
        }
    }

    public static void addCommonData(Phone phone, String[] parts){
        phone.setID(parts[0]);
        phone.setName(parts[1]);
        phone.setScreenSize(Float.parseFloat(parts[2]));
        phone.setChipset(parts[3]);
        phone.setRam(Integer.parseInt(parts[4]));
        phone.setStorage(Integer.parseInt(parts[5]));
        phone.setPrice(Double.parseDouble(parts[6]));
        phone.setManufacturer(parts[7]);
    }

    public static boolean addPhoneList() {
        PhoneDAO phoneDAO = new PhoneDAO();
        return phoneDAO.insertPhone(phoneList);
    }

}
