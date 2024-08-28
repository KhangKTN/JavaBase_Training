package JPL.L.A201.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Airport implements Serializable {
    private String ID;
    private String name;
    private int runwaySize;
    private int maxFixedWing;
    private int maxRotateWing;
    private List<String> fixedwingIdList;
    private List<String> rotateWingIdList;

    public Airport() {}

    public Airport(String id, String name, int runwaySize, int maxFixedWing, int maxRotateWing) {
        this.ID = id;
        this.name = name;
        this.runwaySize = runwaySize;
        this.maxFixedWing = maxFixedWing;
        this.maxRotateWing = maxRotateWing;
    }

    public String getId() {
        return ID;
    }

    public void setId(String id) {
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRunwaySize() {
        return runwaySize;
    }

    public void setRunwaySize(int runwaySize) {
        this.runwaySize = runwaySize;
    }

    public int getMaxFixedWing() {
        return maxFixedWing;
    }

    public void setMaxFixedWing(int maxFixedWing) {
        this.maxFixedWing = maxFixedWing;
    }

    public int getMaxRotateWing() {
        return maxRotateWing;
    }

    public void setMaxRotateWing(int maxRotateWing) {
        this.maxRotateWing = maxRotateWing;
    }

    public List<String> getFixedwingIdList() {
        return fixedwingIdList;
    }

    public void setFixedwingIdList(List<String> fixedwingIdList) {
        this.fixedwingIdList = fixedwingIdList;
    }

    public List<String> getRotateWingIdList() {
        return rotateWingIdList;
    }

    public void setRotateWingIdList(List<String> rotateWingIdList) {
        this.rotateWingIdList = rotateWingIdList;
    }

    @Override
    public String toString() {
        return
                "id=" + ID +
                ",name=" + name +
                ",runwaySize=" + runwaySize +
                ",maxFixedWing=" + maxFixedWing +
                ",maxRotateWing=" + maxRotateWing +
                ",fixedwingIdList=" + (fixedwingIdList == null || fixedwingIdList.isEmpty() ? null : "[" + String.join(";", fixedwingIdList) + "]") +
                ",rotatewingIdList=" + (rotateWingIdList == null || rotateWingIdList.isEmpty() ? null : "[" + String.join(";", rotateWingIdList) + "]");
    }

    public static Airport stringToList(String s){
        if(s.trim().isEmpty()) return null;
        String[] parts = s.split(",");

        Airport airport = new Airport();
        airport.setId(parts[0].split("=")[1]);
        airport.setName(parts[1].split("=")[1]);
        airport.setRunwaySize(Integer.parseInt(parts[2].split("=")[1]));
        airport.setMaxFixedWing(Integer.parseInt(parts[3].split("=")[1]));
        airport.setMaxRotateWing(Integer.parseInt(parts[4].split("=")[1]));

        // Xu li danh sach mang
        List<String> idList;
        String array = "";

        if(!"null".equals(parts[5].split("=")[1])){
            array = parts[5].split("=")[1];
            array = array.substring(1, array.length() - 1);
            idList = new ArrayList<>(Arrays.asList(array.split(";")));
            airport.setFixedwingIdList(idList);
        }
        if(!"null".equals(parts[6].split("=")[1])){
            array = parts[6].split("=")[1];
            array = array.substring(1, array.length() - 1);
            idList = new ArrayList<>(Arrays.asList(array.split(";")));
            airport.setRotateWingIdList(idList);
        }

        return airport;
    }
}
