package JPL.L.A201.utils;

import JPL.L.A201.entities.Airplane;
import JPL.L.A201.entities.Fixedwing;
import JPL.L.A201.entities.Helicopter;

public class ConvertToList {
    public static Airplane lineToAirplane(String s){
        String[] parts = s.split(",");
        String flyMethod = parts[5].split("=")[1];
        Airplane airplane = null;

        if(String.valueOf(FlyMethod.ROTATE_WING).equals(flyMethod)){
            airplane = new Helicopter();
            addCommonInfo(parts, airplane);
            Helicopter helicopter = (Helicopter) airplane;
            helicopter.setRange(Integer.parseInt(parts[6].split("=")[1]));
        }
        else if(String.valueOf(FlyMethod.FIXED_WING).equals(flyMethod)){
            airplane = new Fixedwing();
            addCommonInfo(parts, airplane);
            Fixedwing fixedwing = (Fixedwing) airplane;
            fixedwing.setPlaneType(PlaneTypeConstant.valueOf(parts[6].split("=")[1]));
            fixedwing.setMinNeedRunway(Integer.parseInt(parts[7].split("=")[1]));
        }

        return airplane;
    }

    public static void addCommonInfo(String[] parts, Airplane airplane){
        airplane.setId(parts[0].split("=")[1]);
        airplane.setModel(parts[1].split("=")[1]);
        airplane.setCruiseSpeed(Float.parseFloat(parts[2].split("=")[1]));
        airplane.setCruiseAngle(Float.parseFloat(parts[3].split("=")[1]));
        airplane.setMaxTakeoffWeight(Float.parseFloat(parts[4].split("=")[1]));
        String flyMethod = parts[5].split("=")[1];
        airplane.setFlyMethod(String.valueOf(flyMethod));
    }
}
