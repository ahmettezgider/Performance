package de.performance.tdexpressions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    public static String today() {
        return today("dd.MM.yyyy");
    }

    public static String today(String format) {
        LocalDate ld = LocalDate.now();
        String dt = ld.format(DateTimeFormatter.ofPattern(format));
        return dt;
    }

    public static String now() {
        return now("HH:mm:ss");
    }

    public static String now(String format) {
        LocalTime ld = LocalTime.now();
        String dt = ld.format(DateTimeFormatter.ofPattern(format));
        return dt;
    }

    public static String future(int d, int m, int y) {
        LocalDate ld = LocalDate.now();
        LocalDate future = ld
                .plusDays(d)
                .plusMonths(m)
                .plusYears(y);
        String dt = future.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return dt;
    }

    public static String past(int d, int m, int y) {
        LocalDate ld = LocalDate.now();
        LocalDate past = ld
                .minusDays(d)
                .minusMonths(m)
                .minusYears(y);
        String dt = past.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return dt;
    }
    //TODO futureWorkDay
    public static String futureWorkDay(int d, int m, int y) {
        LocalDate ld = LocalDate.now();
        LocalDate future = ld
                .plusDays(d)
                .plusMonths(m)
                .plusYears(y);
        int weekDay = future.getDayOfWeek().ordinal();
        future = future.plusDays((weekDay == 5 || weekDay == 6) ? (7-weekDay) : 0);
        String dt = future.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return dt;
    }

    public static String getTimestamp(){
        return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ"));
    }

}
