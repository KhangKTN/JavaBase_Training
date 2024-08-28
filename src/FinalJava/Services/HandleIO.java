package FinalJava.Services;

import FinalJava.DAO.GeneralDAO;
import FinalJava.Entity.DesktopPhone;
import FinalJava.Entity.KeypadPhone;
import FinalJava.Entity.Phone;
import FinalJava.Entity.SmartPhone;
import FinalJava.Exception.ColorException;
import FinalJava.Exception.ProductionYearException;
import FinalJava.Utils.DefaultMessage;
import FinalJava.Utils.Validate;
import OnTap1.Utils.ProductionCountryException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HandleIO {
    public static List<Phone> phoneList = new ArrayList<>();

    public static void readFile() {
        File myFile = new File("./src/FinalJava/data.csv");
        try {
            if (myFile.exists() && myFile.canRead()) {
                Scanner myReader = new Scanner(myFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if(data != null && !data.trim().isEmpty()) addPhoneFromString(data);
                }
                myReader.close();
            }
//            System.out.println("==== Show list read from file ====");
//            phoneList.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            System.out.println(DefaultMessage.ERROR_MESSAGE);
        }
    }

    public static String[] readFileCustomer() {
        File myFile = new File("./src/FinalJava/customer.txt");
        try {
            if (myFile.exists() && myFile.canRead()) {
                Scanner myReader = new Scanner(myFile);
                if(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    return data.split(",");
                }
                myReader.close();
                return null;
            }
            return null;
        } catch (FileNotFoundException e) {
            System.out.println(DefaultMessage.ERROR_MESSAGE);
            return null;
        }
    }



    public static void addPhoneFromString(String data){
        try {
            String[] parts = data.split(",");
            int type = Integer.parseInt(parts[0]);
            String id = parts[1];
            if(!Validate.isNoDuplidateId(phoneList, id)){
                System.out.println("Phone " + id + " has duplicate ID");
                return;
            };
            long price = Long.parseLong(parts[5]);
            if(!Validate.isValidPrice(price)){
                System.out.println("Price must be multiple of 10,000");
                return;
            }
            if(type == 1){
                Phone phone = new DesktopPhone();
                addCommonData(phone, parts);

                DesktopPhone desktopPhone = (DesktopPhone) phone;
                desktopPhone.setSpeaker(parts[6]);
                desktopPhone.setWire(parts[7]);
                phoneList.add(phone);
            } else if(type == 2){
                Phone phone = new KeypadPhone();
                addCommonData(phone, parts);

                KeypadPhone keypadPhone = (KeypadPhone) phone;
                keypadPhone.setOs(parts[6]);
                keypadPhone.setBatteryTime(Integer.parseInt(parts[7]));
                keypadPhone.setKeyboard(parts[8]);
                phoneList.add(phone);
            } else if(type == 3){
                Phone phone = new SmartPhone();
                addCommonData(phone, parts);

                SmartPhone smartPhone = (SmartPhone) phone;
                smartPhone.setOs(parts[6]);
                smartPhone.setBatteryTime(Integer.parseInt(parts[7]));
                smartPhone.setMemoryCard(Integer.parseInt(parts[8]));
                smartPhone.setScreenSize(Integer.parseInt(parts[9]));
                smartPhone.setFingerPrint(parts[10]);
                phoneList.add(phone);
            }
        } catch (ColorException | ProductionYearException customException) {
            System.out.println(customException.getMessage());
        } catch (Exception e){
            System.out.println(DefaultMessage.ERROR_MESSAGE);
        }
    }

    public static void addCommonData(Phone phone, String[] parts) throws ColorException, ProductionYearException {
        phone.setType(Integer.parseInt(parts[0]));
        phone.setPhoneId(parts[1]);
        phone.setBrand(parts[2]);
        String color = parts[3];
        if(!Validate.isValidColor(color)) throw new ColorException();
        phone.setColor(parts[3]);

        int productionYear = Integer.parseInt(parts[4]);
        if(!Validate.isValidProductionYear(productionYear)) throw new ProductionYearException();
        phone.setProductionYear(productionYear);
        phone.setPrice(Long.parseLong(parts[5]));
    }

    public static boolean addToDB(){
        GeneralDAO generalDAO = new GeneralDAO();
        return generalDAO.insertData(phoneList);
    }
}
