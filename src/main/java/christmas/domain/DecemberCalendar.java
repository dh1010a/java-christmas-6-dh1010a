package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class DecemberCalendar {
    private static final int START_DATE = 1;
    private static final int END_DATE = 31;
    private static final int START_FRIDAY = 1;
    private static final int START_SATURDAY = 2;
    private static final int WEEK = 7;

    private List<Integer> holidays;

    public DecemberCalendar() {
        holidays = initCalendar();
    }

    public List<Integer> getHolidays() {
        return holidays;
    }

    public boolean isInvalidDate(int date) {
        return START_DATE > date || END_DATE < date;
    }

    private List<Integer> initCalendar() {
        List<Integer> holidays = new ArrayList<>();

        for (int i = START_FRIDAY; i <= END_DATE; i += WEEK) {
            holidays.add(i);
        }
        for (int i = START_SATURDAY; i <= END_DATE; i += WEEK) {
            holidays.add(i);
        }
        
        return holidays;
    }
}
