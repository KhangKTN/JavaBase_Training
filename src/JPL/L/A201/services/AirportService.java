package JPL.L.A201.services;

import JPL.L.A201.entities.Airplane;
import JPL.L.A201.entities.Airport;
import JPL.L.A201.entities.Fixedwing;
import JPL.L.A201.utils.FlyMethod;
import JPL.L.A201.utils.Validate;

import java.io.IOException;
import java.util.*;

public class AirportService {
    static Scanner scanner = new Scanner(System.in);

    public static void addNewAirport(List<Airport> airportList) throws IOException {
        Airport airport = new Airport();
        String temp = "";

        while(true) {
            System.out.print("ID: ");
            temp = scanner.nextLine();
            if(!Validate.validateId(temp)) System.out.println("Invalid ID!");
            else if(existedAirportId(temp, airportList)) System.out.println("Airport already exists!");
            else break;
        }
        airport.setId(temp);

        System.out.print("Name: ");
        airport.setName(scanner.nextLine());
        System.out.print("Run way size: ");
        airport.setRunwaySize(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Max Fixed Wing: ");
        airport.setMaxFixedWing(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Max RotateWing: ");
        airport.setMaxRotateWing(scanner.nextInt());

        airportList.add(airport);
        HandleIO.addAirportToFile(airportList);
    }

    public static boolean existedAirportId(String id, List<Airport> airportList){
        return airportList.stream().anyMatch(airport -> airport.getId().equals(id));
    }

    public static void addAirplaneToAirport(FlyMethod flyMethod, List<Airport> airportList, List<Airplane> airplaneList) throws IOException{
        Optional<Airport> airportOptional;
        while(true){
            System.out.print("Enter ID Airport: ");
            String aiportId = scanner.nextLine();
            airportOptional = airportList.stream().filter(a -> a.getId().equals(aiportId)).findFirst();
            if(airportOptional.isPresent()) break;
            System.out.println("Airport not found!");
        }

        System.out.print("Enter IDs of airplane to add (1,2,...): ");
        String ids = scanner.nextLine();
        String[] idArray = ids.trim().split(",");

        Airport airport = airportOptional.get();
        if(flyMethod == FlyMethod.FIXED_WING){
            List<String> fixedwingIdList = airport.getFixedwingIdList();
            if(fixedwingIdList == null) fixedwingIdList = new ArrayList<>();

            if(airport.getMaxFixedWing() == fixedwingIdList.size()){
                System.out.println("!!! The parking lot is full");
                return;
            }

            for(String id : idArray){
                if(!checkValidRunWay(airport, id, flyMethod, airplaneList)) continue;
                if(allowAdd(id, flyMethod, airportList, airplaneList)){
                    System.out.println("Airplane ID: " + id + " add succeed!");
                    fixedwingIdList.add(id);
                }
            }
            airport.setFixedwingIdList(fixedwingIdList);
            airportList.set(airportList.indexOf(airport), airport);
            HandleIO.addAirportToFile(airportList);
        }
        else if(flyMethod == FlyMethod.ROTATE_WING){
            List<String> rotateWingIdList = airport.getRotateWingIdList();
            if(rotateWingIdList == null) rotateWingIdList = new ArrayList<>();

            if(airport.getMaxRotateWing() == rotateWingIdList.size()){
                System.out.println("!!! The parking lot is full");
                return;
            }

            for(String id : idArray){
                if(allowAdd(id, flyMethod, airportList, airplaneList)){
                    System.out.println("Airplane ID: " + id + " add succeed!");
                    rotateWingIdList.add(id);
                }
            }
            airport.setRotateWingIdList(rotateWingIdList);
            airportList.set(airportList.indexOf(airport), airport);
            HandleIO.addAirportToFile(airportList);
        }
    }

    public static boolean checkValidRunWay(Airport airport, String id, FlyMethod flyMethod, List<Airplane> airplaneList){
        Optional<Airplane> airplaneOptional = AirplaneService.checkAirplaneExistsInAirplaneList(id, airplaneList);

        // Kiem tra may bay da co trong danh sach may bay chua
        if(airplaneOptional.isEmpty()){
            System.err.println("Airplane not exists!");
            return false;
        }

        Airplane airplane = airplaneOptional.get();
        // Kiem tra co dung flyMethod
        if(!airplane.getFlyMethod().equals(flyMethod.toString())){
            System.err.println("Airplane ID: " + id  + " is not allowed to add this fly method!");
            return false;
        }

        // Kiem tra kich thuoc runway
        Fixedwing fixedwing = (Fixedwing) airplane;
        if(fixedwing.getMinNeedRunway() > airport.getRunwaySize()){
            System.out.println("!!! Min runway size does excess the airport runway size");
            return false;
        }
        return true;
    }

    public static boolean allowAdd(String id, FlyMethod flyMethod, List<Airport> airportList, List<Airplane> airplaneList){
        boolean check = true;

        // Kiem tra may bay da co trong san bay nao chua
        if(flyMethod == FlyMethod.FIXED_WING){
            for(Airport airport : airportList){
                if(airport.getFixedwingIdList() == null || airport.getFixedwingIdList().isEmpty()){
                    break;
                }
                if(airport.getFixedwingIdList().stream().anyMatch(item -> item.equals(id))){
                    check = false;
                    break;
                }
            }
        }
        else if(flyMethod == FlyMethod.ROTATE_WING) {
            for(Airport airport : airportList){
                if(airport.getFixedwingIdList() == null || airport.getFixedwingIdList().isEmpty()){
                    break;
                }
                if(airport.getRotateWingIdList().stream().noneMatch(item -> item.equals(id))){
                    check = false;
                    break;
                }
            }
        }
        if(!check) System.err.println("Airplane ID: " + id + " is already in airport!");
        return check;
    }

    public static void removeAirplane(FlyMethod flyMethod, List<Airport> airportList) throws IOException{
        Optional<Airport> airportOptional = null;
        while(true){
            System.out.print("Enter Airport ID: ");
            String airportId = scanner.nextLine();
            airportOptional = airportList.stream().filter(a -> a.getId().equals(airportId)).findFirst();
            if(airportOptional.isEmpty()){
                System.err.println("Airport not found! Please try again!");
            }
            else break;
        }

        System.out.print("Enter list ID airplane to remove (1,2,...): ");
        String ids = scanner.nextLine();
        String[] idListArray = ids.trim().split(",");
        Airport airportToRemove = airportOptional.get();

        if(flyMethod == FlyMethod.FIXED_WING){
            if(airportToRemove.getFixedwingIdList() == null) return;
            List<String> fixedwingIdList = airportToRemove.getFixedwingIdList();
            for(String id : idListArray){
                if(existAirplaneInAirport(id, airportToRemove, flyMethod)){
                    fixedwingIdList.remove(id);
                    System.out.println("Airplane ID: " + id + " removed!");
                }
                else System.out.println("Airplane ID: " + id + " cannot be removed!");
            }
            airportToRemove.setFixedwingIdList(fixedwingIdList);
        }
        else if(flyMethod == FlyMethod.ROTATE_WING){
            if(airportToRemove.getRotateWingIdList() == null) return;
            List<String> rotateWingIdList = airportToRemove.getRotateWingIdList();
            for(String id : idListArray){
                if(existAirplaneInAirport(id, airportToRemove, flyMethod)){
                    rotateWingIdList.remove(id);
                    System.out.println("Airplane ID: " + id + " remove succeed!");
                }
            }
            airportToRemove.setRotateWingIdList(rotateWingIdList);
        }
        airportList.set(airportList.indexOf(airportToRemove), airportToRemove);
        HandleIO.addAirportToFile(airportList);
    }

    public static boolean existAirplaneInAirport(String id, Airport airport, FlyMethod flyMethod){
        if(flyMethod == FlyMethod.FIXED_WING){
            if(airport.getFixedwingIdList() == null) return false;
            return airport.getFixedwingIdList().contains(id);
        }
        else if(flyMethod == FlyMethod.ROTATE_WING){
            if(airport.getRotateWingIdList() == null) return false;
            return airport.getRotateWingIdList().contains(id);
        }
        return false;
    }

    public static void showAllAiport(List<Airport> airportList){
        airportList.sort(Comparator.comparing(Airport::getId));
        for(Airport airport : airportList){
            System.out.println(airport.toString());
        }
    }
}
