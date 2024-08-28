package FinalJava;

import FinalJava.DAO.GeneralDAO;
import FinalJava.Entity.Phone;
import FinalJava.Services.HandleIO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static GeneralDAO gdao = new GeneralDAO();

    static void showList(){
        HandleIO.readFile();
        if(HandleIO.addToDB()) System.out.println("Add phone list to db successfully");
        else System.out.println("Add phone list failed");
    }

    static void updatePromotionPrice(){
        String[] customerType = HandleIO.readFileCustomer();
        if(customerType == null){
            System.out.println("Cannot read file customer");
            return;
        }
        List<Phone> phoneListCSV = HandleIO.phoneList;
        if(phoneListCSV.isEmpty()){
            System.out.println("Size of phone list is empty! Must be read data from file before!");
            return;
        }
        for(int i = 0; i < phoneListCSV.size(); i++){
            long price = phoneListCSV.get(i).getPrice();
            long pricePromotion = price - price * calculatePromotionPrice(customerType[i]) / 100;
            if(gdao.updatePromationPrice(pricePromotion, phoneListCSV.get(i).getPhoneId()))
                System.out.println("Update promotionPrice " + phoneListCSV.get(i).getPhoneId() + " successfully!");
            else System.out.println("Update promotionPrice " + phoneListCSV.get(i).getPhoneId() + " failed!");
        }
    }

    static long calculatePromotionPrice(String customerType){
        int pricePromotion = 0;
        if("HS".equals(customerType)) pricePromotion = 20;
        else if("SV".equals(customerType)) pricePromotion = 15;
        else if("NV".equals(customerType)) pricePromotion = 30;
        return pricePromotion;
    }

    static void showInformation(){
        List<Phone> phoneList = gdao.getAllData();
        Collections.sort(phoneList, Comparator.comparingInt(Phone::getProductionYear).reversed().thenComparingLong(Phone::getPrice));
//        phoneList.sort(Comparator.comparingInt(Phone::getProductionYear).reversed().thenComparingLong(Phone::getPrice));
        phoneList.forEach(Phone::showInfo);
    }

    static void updateWarrantyPeriod(){
        List<Phone> phoneList = gdao.getAllData();
        for(Phone phone : phoneList){
            int warrantyPeriod = 0;
            if(phone.getPromotionPrice() >= 15000000) warrantyPeriod = 3;
            else if(phone.getPromotionPrice() >= 10000000) warrantyPeriod = 2;
            else warrantyPeriod = 1;

            if(gdao.updateWarrantyPeriod(warrantyPeriod, phone.getPhoneId()))
                System.out.println("Update WarrantyPeriod " + phone.getPhoneId() + " successfully!");
            else System.out.println("Update WarrantyPeriod " + phone.getPhoneId() + " failed!");
        }
    }


    public static void main(String[] args) {
        String option = "";
        while(true){
            System.out.println("========== Management ==========\n");
            System.out.println("1. Read from file and insert to DB");
            System.out.println("2. Update price promotion");
            System.out.println("3. Show list phone (Sorted)");
            System.out.println("4. Update warranty period");
            System.out.print("Enter option: ");

            option = sc.nextLine().trim();

            if("0".equals(option)){
                System.out.println("Exit program!!!");
                break;
            }
            switch(option){
                case "1":
                    showList();
                    break;
                case "2":
                    updatePromotionPrice();
                    break;
                case "3":
                    showInformation();
                    break;
                case "4":
                    updateWarrantyPeriod();
                    break;
                default: System.out.println("Invalid option");
            }
        }
    }
}
