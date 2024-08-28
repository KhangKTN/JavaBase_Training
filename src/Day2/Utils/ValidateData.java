package Day2.Utils;

import Day2.Utils.Exception.BirthDayException;
import Day2.Utils.Exception.EmailException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ValidateData {
    public static boolean validateEmail(String email) throws EmailException {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        if(!email.matches(regex)) throw new EmailException();
        return true;
    }

    public static boolean validateBirthday(String dateString) throws BirthDayException {
        /*if(!birthday.matches("\\d{4}-\\d{2}-\\d{2}")){
            throw new BirthDayException();
        }*/

        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(dateString);

            Date date = simpleDateFormat.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int yearInput = calendar.get(Calendar.YEAR);
            if(yearInput < 1900 || yearInput > Calendar.getInstance().get(Calendar.YEAR)){
                throw new BirthDayException();
            }
            return true;
        } catch (ParseException e){
            throw new BirthDayException();
        }
    }

    public static boolean validateDate(String dateString){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(dateString);

            Date date = simpleDateFormat.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int yearInput = calendar.get(Calendar.YEAR);

            return true;
        } catch (ParseException ignored){
            return false;
        }
    }
}
