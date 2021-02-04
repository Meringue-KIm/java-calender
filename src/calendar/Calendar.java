package calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Calendar {

    private static final int[] MAX_DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] LEAP_MAX_DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private HashMap<Date, String> planMap;

    public Calendar() {
        planMap = new HashMap<Date, String>();
    }

    /**
     * @param date ex: "2021.02.04"
     * @param plan
     */
    public void registerPlan(String strDate, String plan) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
        //System.out.println(date);
        planMap.put(date, plan);
    }

    public String searchPlan(String strDate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
        String plan = planMap.get(date);
        return plan;
    }

    public boolean isLeapYear(int year) {
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public int getmaxDaysOfMonth(int year, int month) {
        if (isLeapYear(year)) {
            return LEAP_MAX_DAYS[month];
        } else {
            return MAX_DAYS[month];
        }
    }

    public void printCalendar(int year, int month) {
        System.out.printf("   <<%d년%d월>>\n", year, month);
        System.out.println(" SU MO TU WE TH FR SA");
        System.out.println("---------------------");

        int weekday = getWeekDay(year, month, 1);
        //print blank space
        for (int i = 0; i < weekday; i++) {
            System.out.print("   ");
        }
        int maxDay = getmaxDaysOfMonth(year, month);
        int count = 7 - weekday;

        int delim = (count < 7) ? count : 0;
//        if (count < 7) {         윗줄과 같음
//            delim = count;
//        } else {
//            delim = 0;
//        }


        //print first line
        for (int i = 1; i <= count; i++) {
            System.out.printf("%3d", i);
        }
        System.out.println();

        //print from second line to last

        count++;
        for (int i = count; i <= maxDay; i++) {
            System.out.printf("%3d", i);
            if (i % 7 == delim) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println();
    }

    private int getWeekDay(int year, int month, int day) {
        int syear = 1970;

        final int STANDARD_WEEKDAY = 4; //1970/Jan/1st/Thursday

        int count = 0;

        for (int i = syear; i < year; i++) {
            int delta = isLeapYear(i) ? 366 : 365;
            count += delta;

        }

        for (int i = 1; i < month; i++) {
            int delta = getmaxDaysOfMonth(year, i);
            count += delta;

        }

        count += day - 1;

        int weekday = (count + STANDARD_WEEKDAY) % 7;
        return weekday;
    }

}
