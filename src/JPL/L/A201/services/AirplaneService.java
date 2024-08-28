package JPL.L.A201.services;

import JPL.L.A201.entities.Airplane;
import JPL.L.A201.entities.Airport;
import JPL.L.A201.entities.Fixedwing;
import JPL.L.A201.entities.Helicopter;
import JPL.L.A201.utils.FlyMethod;
import JPL.L.A201.utils.PlaneTypeConstant;
import JPL.L.A201.utils.Validate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AirplaneService {
    static Scanner scanner = new Scanner(System.in);

    public static void addAirPlane(Airplane airplane, FlyMethod flyMethod){
        String temp = "";
        while(true){
            System.out.print("ID: ");
            temp = scanner.nextLine();
            if(flyMethod == FlyMethod.FIXED_WING){
                if(!Validate.validateFw(temp)){
                    System.out.println("ID is not formatted correctly. Try again!");
                    continue;
                };
                break;
            }
            else if(flyMethod == FlyMethod.ROTATE_WING){
                if(!Validate.validateRw(temp)){
                    System.out.println("ID is not formatted correctly. Try again!");
                    continue;
                };
                break;
            }
            airplane.setId(scanner.nextLine());
        }

        System.out.print("Model: ");
        airplane.setModel(scanner.nextLine());
        System.out.print("cruiseSpeed: ");
        airplane.setCruiseSpeed(scanner.nextFloat());
        scanner.nextLine();
        System.out.print("cruiseAngle: ");
        airplane.setCruiseAngle(scanner.nextFloat());
        scanner.nextLine();
        System.out.print("maxTakeoffWeight: ");
        airplane.setMaxTakeoffWeight(scanner.nextFloat());
        scanner.nextLine();
        airplane.setFlyMethod(flyMethod.toString());
    }

    public static void addFixedwing(List<Airplane> airplaneList) throws IOException{
        Airplane airplane = new Fixedwing();
        addAirPlane(airplane, FlyMethod.FIXED_WING);
        Fixedwing fixedwing = (Fixedwing) airplane;

        choosePlaneTypeFixedwing(fixedwing);

        System.out.print("minNeedRunway: ");
        fixedwing.setMinNeedRunway(scanner.nextInt());
        scanner.nextLine();

        airplaneList.add(fixedwing);
        HandleIO.addAirplaneToFile(airplaneList);
    }

    public static void addHelicopter(List<Airplane> airplaneList) throws IOException{
        Airplane airplane = new Helicopter();
        addAirPlane(airplane, FlyMethod.ROTATE_WING);
        Helicopter helicopter = (Helicopter) airplane;
        System.out.print("Range: ");
        helicopter.setRange(scanner.nextInt());
        scanner.nextLine();

        airplaneList.add(helicopter);
        HandleIO.addAirplaneToFile(airplaneList);
    }

    public static void updateFixedwing(List<Airplane> airplaneList) throws IOException {
        String id = "";
        Optional<Airplane> airplaneOptional;
        while(true){
            System.out.println("Enter fixedwing ID: ");
            id = scanner.nextLine();
            String enterId = id;
            airplaneOptional = airplaneList.stream().filter(item -> item.getId().equals(enterId)).findFirst();
            if(airplaneOptional.isPresent()) break;
            System.err.println("Airplane not found!");
        }
        Airplane airplaneUpdate = airplaneOptional.get();
        Fixedwing fixedwing = (Fixedwing) airplaneUpdate;
        System.out.print("Enter min needed runway size: ");
        int minNeededRunwaySize = scanner.nextInt();
        fixedwing.setMinNeedRunway(minNeededRunwaySize);
        choosePlaneTypeFixedwing(fixedwing);

        System.out.println(fixedwing.toString());

        airplaneList.set(airplaneList.indexOf(airplaneUpdate), fixedwing);
        HandleIO.addAirplaneToFile(airplaneList);
    }

    public static void choosePlaneTypeFixedwing(Fixedwing fixedwing){
        System.out.println("Choose plane type: ");
        System.out.println("1. " + PlaneTypeConstant.CAG);
        System.out.println("2. " + PlaneTypeConstant.LGR);
        System.out.println("3. " + PlaneTypeConstant.PRV);
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice == 1){
            fixedwing.setPlaneType(PlaneTypeConstant.CAG);
        }
        else if(choice == 2){
            fixedwing.setPlaneType(PlaneTypeConstant.LGR);
        }
        else if(choice == 3){
            fixedwing.setPlaneType(PlaneTypeConstant.PRV);
        }
    }

    public static void showPlaneDetail(FlyMethod flyMethod, List<Airplane> airplaneList, List<Airport> airportList){
        Optional<Airplane> airplaneOptional;
        String airplaneId = "";
        while(true){
            System.out.print("Enter ID of the plane: ");
            String enterId = scanner.nextLine();
            airplaneOptional = checkAirplaneExistsInAirplaneList(enterId, airplaneList);
            if(airplaneOptional.isPresent()){
                if(airplaneOptional.get().getFlyMethod().equals(flyMethod.toString())){
                    airplaneId = enterId;
                    break;
                }
                System.out.println("!!! FlyMethod must be: " + flyMethod.toString());
                continue;
            };
            System.err.println("Airplane not found!");
        }
        Airplane airplane = airplaneOptional.get();
        System.out.println(airplane.toString());
        for(Airport airport : airportList){
            if(AirportService.existAirplaneInAirport(airplaneId, airport, flyMethod)){
                System.out.println("Id airport: " + airport.getId());
                System.out.println("Name airport: " + airport.getName());
            }
        }
    }

    public static Optional<Airplane> checkAirplaneExistsInAirplaneList(String id, List<Airplane> airplaneList){
        return airplaneList.stream().filter(item -> item.getId().equals(id)).findFirst();
    }
}
