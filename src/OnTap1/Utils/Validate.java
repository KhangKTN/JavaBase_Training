package OnTap1.Utils;

import OnTap1.Entity.Stoven;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

public class Validate {
    public static boolean isValidProductionCountry(String country){
        String[] countryCheckList = {"Korea", "Japan", "VietNam", "Singapore", "Sweden", "Thailand"};
        return Arrays.asList(countryCheckList).contains(country);
    }

    public static boolean isValidProductionYear(int yearProd){
        LocalDate localDate = LocalDate.now(ZoneId.of("GMT+07:00"));
        String yearString = localDate.toString().split("-")[0];
        int yearFromCurrentDate = Integer.parseInt(yearString);
        return yearFromCurrentDate >= yearProd;
    }

    public static boolean isNoDuplicateId(List<Stoven> stovenList, String id){
        return stovenList.stream().noneMatch(stoven -> stoven.getStovenID().equals(id));
    }
}
