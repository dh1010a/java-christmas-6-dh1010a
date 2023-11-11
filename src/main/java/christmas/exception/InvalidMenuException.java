package christmas.exception;

public class InvalidMenuException extends IllegalArgumentException {
    public InvalidMenuException() {
        super(ExceptionMessage.INVALID_MENU_ERROR.getMessage());
    }
}
