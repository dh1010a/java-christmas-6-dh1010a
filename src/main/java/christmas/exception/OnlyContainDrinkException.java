package christmas.exception;

public class OnlyContainDrinkException extends IllegalArgumentException {
    public OnlyContainDrinkException() {
        super(ExceptionMessage.ONLY_CONTAIN_DRINK_ERROR.getMessage());
    }
}
