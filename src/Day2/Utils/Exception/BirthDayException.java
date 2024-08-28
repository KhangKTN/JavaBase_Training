package Day2.Utils.Exception;

import Day2.Utils.Log;

public class BirthDayException extends Exception {
    public BirthDayException(){
        super("BirthDay input not correct. Please try again.");
        Log.error("BirthDay input not correct. Please try again.");
    }
}
