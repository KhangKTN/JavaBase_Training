package JPL_L_A301.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

public class Helper {
    static SimpleDateFormat sdf = null;

    public static String convertDateToString(Date date){
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static Date convertStringToDate(String dateString){
        try {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(dateString);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            return new Date();
        }
    }

    public static boolean validateDate(String dateString){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(dateString);

//            Date date = simpleDateFormat.parse(dateString);
            return true;
        } catch (ParseException ignored){
            return false;
        }
    }
}
