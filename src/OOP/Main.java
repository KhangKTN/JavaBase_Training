package OOP;

import FinalJava.Utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class Main {
    public static void foo(Integer i) { System.out.println("foo(Integer)"); }
    public static void foo(short i) { System.out.println("foo(short)"); }
    public static void foo(long i) { System.out.println("foo(long)"); }
    public static void foo(int ... i) { System.out.println("foo(int ...)"); }

    public static void main(String[] args) throws SQLException {
        LocalDate localDate = LocalDate.now(ZoneId.of("GMT+07:00"));
        Instant instant = Instant.now();
        System.out.println(localDate.toString().split("-")[0]);
    }
}
