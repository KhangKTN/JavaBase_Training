package JPL.L.A201.utils;

public class Validate {
    public static final String REGEX_AIRPORT = "\\bAP\\d{5}\\b";
    public static final String REGEX_FW = "\\bFW\\d{5}\\b";
    public static final String REGEX_RW = "\\bRW\\d{5}\\b";

    public static boolean validateId(String id){
        if(id == null || id.trim().isEmpty()) return false;
        return id.matches(REGEX_AIRPORT);
    }

    public static boolean validateFw(String id){
        if(id == null || id.trim().isEmpty()) return false;
        return id.matches(REGEX_FW);
    }

    public static boolean validateRw(String id){
        if(id == null || id.trim().isEmpty()) return false;
        return id.matches(REGEX_RW);
    }
}
