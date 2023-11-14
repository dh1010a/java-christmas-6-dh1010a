package christmas.domain.visitDate;

import christmas.exception.ExceptionMessage;

public class VisitDate {
    private static final String DECEMBER = "12월 ";
    private static final String DATE_FORMAT = "일";
    private final int date;
    private final DecemberCalendar calendar;

    public VisitDate(int dateInput) {
        calendar = new DecemberCalendar();
        validator(dateInput);
        this.date = dateInput;
    }

    public boolean isWeekend() {
        return calendar.isWeekend(date);
    }

    public boolean isStarDay() {
        return calendar.isStarDay(date);
    }

    public int getDate() {
        return date;
    }

    public String getFullDate() {
        return DECEMBER + date + DATE_FORMAT;
    }

    private void validator(int dateInput) throws IllegalArgumentException {
        String error = ExceptionMessage.INVALID_DATE_RANGE_ERROR.getMessage();

        checkInvalidDateRangeAndThrowException(dateInput, error);
    }

    private void checkInvalidDateRangeAndThrowException(int dateInput, String error) throws IllegalArgumentException {
        if (calendar.isInvalidDate(dateInput)) {
            throw new IllegalArgumentException(error);
        }
    }

}
