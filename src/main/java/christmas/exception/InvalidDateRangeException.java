package christmas.exception;

public class InvalidDateRangeException extends IllegalArgumentException {
    public InvalidDateRangeException() {
        super(ExceptionMessage.INVALID_DATE_RANGE_ERROR.getMessage());
    }
}
