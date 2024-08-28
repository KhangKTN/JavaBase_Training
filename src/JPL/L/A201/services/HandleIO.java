package JPL.L.A201.services;

import JPL.L.A201.entities.Airplane;
import JPL.L.A201.entities.Airport;
import JPL.L.A201.utils.ConvertToList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class HandleIO {
    public static final String BASE_PATH = "./src/JPL/L/A201/data/";
    public static final String AIRPORT_PATH = BASE_PATH + "airportData.txt";
    public static final String AIRPLANE_PATH = BASE_PATH + "airplaneData.txt";


    public static void addAirportToFile(List<Airport> aiportList) throws IOException {
//        Airport airport = null;
//        for(int i = 1; i <= 4; i++){
//            airport = new Airport(i, "Name", 10, 10, 10);
//            airports.add(airport);
//        }
        if(aiportList == null) return;

        Path filePath = Paths.get(AIRPORT_PATH);
        Files.deleteIfExists(filePath);
        Files.createFile(filePath);
        for (Airport airp : aiportList) {
            Files.writeString(filePath, airp.toString() + System.lineSeparator(),
                    StandardOpenOption.APPEND);
        }

        /*FileOutputStream fos = new FileOutputStream("./src/JPL/L/A201/data/airportData.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(airports);
        oos.close();*/
    }

    public static void addAirplaneToFile(List<Airplane> airplaneList) throws IOException {
        Path filePath = Paths.get(AIRPLANE_PATH);
        Files.deleteIfExists(filePath);
        Files.createFile(filePath);
        for (Airplane airplane : airplaneList) {
            Files.writeString(filePath, airplane.toString() + System.lineSeparator(),
                    StandardOpenOption.APPEND);
        }
    }

    public static List<Airport> readAirportFromFile() throws IOException {
        List<Airport> aiportList = new ArrayList<>();
        Path filePath = Paths.get(AIRPORT_PATH);
        List<String> allLines =  Files.readAllLines(filePath);
        allLines.forEach(line -> aiportList.add(Airport.stringToList(line)));

        return aiportList;
    }

    public static List<Airplane> readAirplaneFromFile() throws IOException {
        List<Airplane> airplaneList = new ArrayList<>();
        Path filePath = Paths.get(AIRPLANE_PATH);
        List<String> allLines =  Files.readAllLines(filePath);
        allLines.forEach(line -> airplaneList.add(ConvertToList.lineToAirplane(line)));

        return airplaneList;
    }
}
