package christmas.domain;

import christmas.exception.InvalidDateRangeException;
import java.util.List;

public class VisitDate {
    private final int date;
    private final DecemberCalendar calendar;
    private List<Integer> holidays;

    public VisitDate(int date) {
        this.date = date;
        validator(date);
        calendar = new DecemberCalendar();
        holidays = getHolidays(date);
    }

    public boolean isHoliday() {
        return holidays.contains(date);
    }

    public int getDate() {
        return date;
    }

    private List<Integer> getHolidays(int date) {
        return calendar.getHolidays();
    }

    private void validator(int date) throws IllegalArgumentException {
        if (calendar.isInvalidDate(date)) {
            throw new InvalidDateRangeException();
        }
    }

}
