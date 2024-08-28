package OnTap1.Services;

import OnTap.Entity.Phone;
import OnTap.Utils.DefaultMessage;
import OnTap1.DAO.StovenDAO;
import OnTap1.Entity.GasStoven;
import OnTap1.Entity.InfrareStoven;
import OnTap1.Entity.MagneticStoven;
import OnTap1.Entity.Stoven;
import OnTap1.Utils.ProductionCountryException;
import OnTap1.Utils.ProductionYearException;
import OnTap1.Utils.Validate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HandleIO {
    static List<Stoven> stovenList = new ArrayList<>();

    public static void readFile() {
        File myFile = new File("./src/OnTap1/data.csv");
        try {
            if (myFile.exists() && myFile.canRead()) {
                Scanner myReader = new Scanner(myFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if(data != null && !data.trim().isEmpty()) setStovenFromString(data);
                }
                myReader.close();
            }
            stovenList.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setStovenFromString(String data){
        try {
            String[] parts = data.split(",");
            int type = Integer.parseInt(parts[0]);
            String id = parts[1];
            if(!Validate.isNoDuplicateId(stovenList, id)){
                System.out.println("Stoven " + id + "has duplicate ID");
                return;
            };
            if(type == 1){
                Stoven stoven = new GasStoven();
                addCommonData(stoven, parts);

                GasStoven gasStoven = (GasStoven) stoven;
                gasStoven.setFire(parts[8]);
                stovenList.add(gasStoven);
            } else if(type == 2){
                Stoven stoven = new MagneticStoven();
                addCommonData(stoven, parts);

                MagneticStoven magneticStoven = (MagneticStoven) stoven;
                magneticStoven.setWatt(Float.parseFloat(parts[8]));
                magneticStoven.setTimer(parts[9]);
                stovenList.add(magneticStoven);
            } else if(type == 3){
                Stoven stoven = new InfrareStoven();
                addCommonData(stoven, parts);

                InfrareStoven infrareStoven = (InfrareStoven) stoven;
                infrareStoven.setWatt(Float.parseFloat(parts[8]));
                infrareStoven.setCookMode(parts[9]);
                stovenList.add(infrareStoven);
            }
        }
        catch (ProductionCountryException e){
            System.out.println(e.getMessage());
        }
        catch (ProductionYearException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(DefaultMessage.ERROR_MESSAGE);
        }
    }

    public static void addCommonData(Stoven stoven, String[] parts) throws ProductionCountryException, ProductionYearException {
        stoven.setType(Integer.parseInt(parts[0]));
        stoven.setStovenID(parts[1]);
        stoven.setBrand(parts[2]);
        stoven.setProductCode(parts[3]);
        String countryProd = parts[4];
        if(!Validate.isValidProductionCountry(countryProd)) throw new ProductionCountryException();
        stoven.setProductionCountry(countryProd);
        int yearProd = Integer.parseInt(parts[5]);
        if(!Validate.isValidProductionYear(yearProd)) throw new ProductionYearException();
        stoven.setProductionYear(yearProd);
        stoven.setNoOfStoven(Integer.parseInt(parts[6]));
        stoven.setPrice(Long.parseLong(parts[7]));
    }

    public static boolean addToDB(){
        StovenDAO stovenDAO = new StovenDAO();
        return stovenDAO.addStoven(stovenList);
    }

}
