package JPL.L.A201;

import JPL.L.A201.entities.Airplane;
import JPL.L.A201.entities.Airport;
import JPL.L.A201.services.AirplaneService;
import JPL.L.A201.services.AirportService;
import JPL.L.A201.services.HandleIO;
import JPL.L.A201.utils.FlyMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagement {
    static Scanner scanner = new Scanner(System.in);
    static List<Airport> airports = new ArrayList<>();
    static List<Airplane> airplanes = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        /*FileInputStream fis = new FileInputStream("./src/JPL/L/A201/data/airportData.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        fis.
        if(ois.available() == 0){
            airports = (List<Airport>) ois.readObject();
        }
        ois.close();

        for (Airport airport : airports) {
            System.out.println(airport.toString());
        }*/
        airports = HandleIO.readAirportFromFile();
        airplanes = HandleIO.readAirplaneFromFile();
        int option = 0;
        do {
            System.out.println("\n\n============== MANAGEMENT AIRPORT ==============\n");
            System.out.println("1. Add new Airport");
            System.out.println("2. Add new Fixedwing");
            System.out.println("3. Add new Helicopter");
            System.out.println("4. Show all airplane");
            System.out.println("5. Add fixed wing to airport");
            System.out.println("6. Add rotate wing to airport");
            System.out.println("7. Remove fixed wing from airport");
            System.out.println("8. Remove rotate wing from airport");
            System.out.println("9. Update Fixedwing");
            System.out.println("10. Show all Airport");
            System.out.println("11. Show fixed wing");
            System.out.println("12. Show rotate wing");
            System.out.print("Enter option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            if(option == 1) AirportService.addNewAirport(airports);
            else if(option == 2) AirplaneService.addFixedwing(airplanes);
            else if(option == 3) AirplaneService.addHelicopter(airplanes);
            else if (option == 4) airplanes.forEach(System.out::println);
            else if(option == 5) AirportService.addAirplaneToAirport(FlyMethod.FIXED_WING, airports, airplanes);
            else if(option == 6) AirportService.addAirplaneToAirport(FlyMethod.ROTATE_WING, airports, airplanes);
            else if(option == 7) AirportService.removeAirplane(FlyMethod.FIXED_WING, airports);
            else if(option == 8) AirportService.removeAirplane(FlyMethod.ROTATE_WING, airports);
            else if(option == 9) AirplaneService.updateFixedwing(airplanes);
            else if(option == 10) AirportService.showAllAiport(airports);
            else if(option == 11) AirplaneService.showPlaneDetail(FlyMethod.FIXED_WING, airplanes, airports);
            else if(option == 12) AirplaneService.showPlaneDetail(FlyMethod.ROTATE_WING, airplanes, airports);

        } while (option != 0);
    }
}
