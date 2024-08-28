package FinalJava.Utils;

import FinalJava.Entity.Phone;
import OnTap1.Entity.Stoven;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

public class Validate {
    public static boolean isNoDuplidateId(List<Phone> list, String id){
        return list.stream().noneMatch(item -> item.getPhoneId().equals(id));
    }

    public static boolean isValidColor(String color){
        String[] colorList = {"Red", "Black", "Pink", "Brown", "Silver"};
        return Arrays.asList(colorList).contains(color);
    }

    public static boolean isValidProductionYear(int yearProd){
        LocalDate localDate = LocalDate.now(ZoneId.of("GMT+07:00"));
        String yearString = localDate.toString().split("-")[0];
        int yearFromCurrentDate = Integer.parseInt(yearString);
        return yearFromCurrentDate >= yearProd;
    }

    public static boolean isValidPrice(long price){
        return price > 0 && price % 10000 == 0;
    }
}
