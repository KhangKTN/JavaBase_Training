package OnTap.Utils;

import OnTap.Entity.Phone;

import java.util.List;

public class Validate {
    public static boolean validIdPhone(String idPhone) {
        if(idPhone == null || idPhone.isEmpty()) return false;
        if(!idPhone.startsWith("CH") && !idPhone.startsWith("XT") && !idPhone.startsWith("OD")) {
            return false;
        }
        String afterChar = idPhone.substring(2);
        try {
            int number = Integer.parseInt(afterChar);
            return true;
        }catch(NumberFormatException e) {
            return false;
        }
    }

    public static boolean validDuplicateId(String id, List<Phone> phoneList) {
        for(Phone phone : phoneList) {
            if(phone.getID().equals(id)){
                return false;
            };
        }
        return true;
    }

    public static boolean validRangeAndStatus(int input){
        return input == 1 || input == 2;
    }

    public static boolean isNumber(String number) {
        try{
            long numberInt = Long.parseLong(number);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
