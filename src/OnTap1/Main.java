package OnTap1;

import OnTap1.DAO.StovenDAO;
import OnTap1.Entity.Stoven;
import OnTap1.Services.HandleIO;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static StovenDAO stovenDAO = new StovenDAO();

    public static void sale(){
        System.out.println("Enter StovenID: ");
        String stovenID = "";
        while(true){
            stovenID = scanner.next();
            if(stovenDAO.isExistStoven(stovenID.trim())){break;}
            System.out.println("StovenID not found!");
        }
        String buyerType = "";
        while(true){
            System.out.println("Enter Buyer Type: ");
             buyerType = scanner.next();
            if("SV".equals(buyerType) || "CN".equals(buyerType) || "VC".equals(buyerType))
                break;
            System.out.println("Buyer Type must be ‘SV’ or ‘CN’ or ‘VC");
        }
        int salePricePercent = 0;
        int warrantYear = 0;
        if("SV".equals(buyerType)){
            salePricePercent = 15;
            warrantYear = 3;
        } else if("CN".equals(buyerType)){
            salePricePercent = 10;
            warrantYear = 2;
        } else if("VC".equals(buyerType)){
            salePricePercent = 5;
            warrantYear = 1;
        }
        if(stovenDAO.updateStoven(stovenID, salePricePercent, warrantYear)){
            System.out.println("Stoven successfully updated!");
        } else System.out.println("Stoven update failed!");
    }

    public static void showListStoven(){
        List<Stoven> stovenList = stovenDAO.getStovenList();
//        stovenList.sort(Comparator.comparingInt(Stoven::getProductionYear).thenComparing(Stoven::getStovenID));
        stovenList.forEach(System.out::println);
    }


    public static void main(String[] args) {
        HandleIO.readFile();
//        HandleIO.addToDB();
//        sale();
//        showListStoven();
//        if(stovenDAO.deleteStoven()) System.out.println("Delete successfully!");
//        else System.out.println("No record deleted!");
    }
}
