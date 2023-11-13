package christmas.domain.visitDate;

import christmas.exception.ExceptionMessage;
import christmas.exception.InvalidDateRangeException;

public class VisitDate {
    private final int date;
    private final DecemberCalendar calendar;

    public VisitDate(String dateInput) {
        calendar = new DecemberCalendar();
        validator(dateInput);
        this.date = Integer.parseInt(dateInput);
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

    private void validator(String dateInput) throws IllegalArgumentException {
        String error = ExceptionMessage.INVALID_DATE_RANGE_ERROR.getMessage();
        checkIsNotIntegerAndThrowException(dateInput);
        checkIsNotEmptyAndThrowException(dateInput, error);

        int intDate = Integer.parseInt(dateInput);
        checkInvalidDateRangeAndThrowException(intDate, error);
    }

    private void checkInvalidDateRangeAndThrowException(int intDate, String error) throws IllegalArgumentException {
        if (calendar.isInvalidDate(intDate)) {
            throw new IllegalArgumentException(error);
        }
    }

    private void checkIsNotIntegerAndThrowException(String dateInput) throws IllegalArgumentException {
        try {
            Integer.parseInt(dateInput);
        } catch (NumberFormatException error) {
            throw new InvalidDateRangeException();
        }
    }

    private void checkIsNotEmptyAndThrowException(String dateInput, String error) throws IllegalArgumentException {
        if (dateInput.isEmpty()) {
            throw new IllegalArgumentException(error);
        }
    }

}
