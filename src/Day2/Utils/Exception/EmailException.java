package Day2.Utils.Exception;

import Day2.Utils.Log;

public class EmailException extends Exception {
    public EmailException(){
        super("Email is incorrect. Please try again.");
        Log.error("Email is incorrect. Please try again.");
    }
}
