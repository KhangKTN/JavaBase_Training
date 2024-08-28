package OnTap;

import OnTap.DAO.PhoneDAO;
import OnTap.Entity.ForeignPhone;
import OnTap.Entity.OldPhone;
import OnTap.Entity.Phone;
import OnTap.Services.ReadFile;
import OnTap.Utils.Validate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static PhoneDAO phoneDAO = new PhoneDAO();

    public static List<Phone> findPhone(){
        long minPrice = 0, maxPrice = 0;
        String temp = "";
        while(true){
            System.out.println("Nhap gia nho nhat: ");
            temp = scanner.nextLine();
            if(Validate.isNumber(temp)){
                minPrice = Long.parseLong(temp);
                break;
            }
            System.out.println("Gia tri phai la so!");
        }

        while(true){
            System.out.print("Nhap gia lon nhat: ");
            temp = scanner.nextLine();
            if(Validate.isNumber(temp)){
                maxPrice = Long.parseLong(temp);
                break;
            }
            System.out.print("Gia tri phai la so!");
        }
        List<Phone> phoneList = phoneDAO.getPhoneListByPrice(minPrice, maxPrice);
        Collections.sort(phoneList, (a, b) -> {
            double comparePrice = a.getPrice() - b.getPrice();
            if(comparePrice != 0){return comparePrice > 0 ? 1 : -1;}
            return b.getStorage() - a.getStorage();
        });
        int index = 1;
        for(Phone phone : phoneList){
            System.out.print((index++) + ". ");
            phone.showInfo();
        }
        return phoneList;
    }

    public static void selectPhone(){
        List<Phone> phoneList;
        while(true){
            phoneList = findPhone();
            if(!phoneList.isEmpty()) break;
            System.out.println("Khong co dien thoai nao thoa man voi gia nhap vao");
        }
        int selection = 0;
        while(true){
            System.out.print("Chon dien thoai mua: ");
            selection = scanner.nextInt();
            scanner.skip("\n");
            if(selection == 0){
                System.out.println("Ban se thoat khoi chuong trinh");
                return;
            }
            if(selection <= phoneList.size()){
                break;
            }
            System.out.println("Vui long chon dung tt dien thoai");
        }

        Phone phoneSelected = phoneList.get(selection - 1);
//        boolean isDeleteSucceed = phoneDAO.deletePhone(findPhone().get(selection - 1).getID());
//        if(!isDeleteSucceed){ return;}

        if(phoneSelected.getID().startsWith("CH")){
            System.out.println("Cam on ban da mua san pham nhu ben duoi:");
            System.out.println(phoneList.get(selection - 1).toString());
        }
        else{
            String confirm = "";
            System.out.println("ban co muon mua goi bao hanh vang hay khong: (Y/N)");
            confirm = scanner.nextLine();

            if("Y".equalsIgnoreCase(confirm)){
                System.out.println("Cam on ban da mua san pham:");
                System.out.println("Name: " + phoneSelected.getID());
                System.out.println("Storage: " + phoneSelected.getStorage());
                System.out.println("Chipset: " + phoneSelected.getChipset());
                System.out.println("Ram: " + phoneSelected.getRam());
                if(phoneSelected instanceof ForeignPhone){
                    System.out.println("Price: " + (phoneSelected.getPrice() + ((ForeignPhone) phoneSelected).getPriceWarranty()));
                }
                else System.out.println("Price: " + (phoneSelected.getPrice() + ((OldPhone) phoneSelected).getPriceWarranty()));
                System.out.println("Warranty: 12 thang");
                System.out.println("Xuat xu: " + phoneSelected.getManufacturer());
            }
            else {
                System.out.println("Cam on ban da mua san pham:");
                System.out.println("Name: " + phoneSelected.getID());
                System.out.println("Storage: " + phoneSelected.getStorage());
                System.out.println("Chipset: " + phoneSelected.getChipset());
                System.out.println("Ram: " + phoneSelected.getRam());
                System.out.println("Price: " + phoneSelected.getPrice());
                System.out.println("Warranty: 12 thang");
                System.out.println("Xuat xu: " + phoneSelected.getManufacturer());
            }
        }
    }


    public static void main(String[] args) {
        try {
//            ReadFile.readFile();
//            if(ReadFile.addPhoneList()) System.out.println("Add to DB successfully!");
//            else System.out.println("Add to DB failed!");

            selectPhone();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
