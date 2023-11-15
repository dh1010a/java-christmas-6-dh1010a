package christmas.exception;

public class TotalOrderCountOverflowException extends IllegalArgumentException {
    public TotalOrderCountOverflowException() {
        super(ExceptionMessage.TOTAL_ORDER_COUNT_OVERFLOW_ERROR.getMessage());
    }
}
