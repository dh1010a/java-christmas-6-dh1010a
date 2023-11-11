package christmas.exception;

public enum ExceptionMessage {

    PREFIX("[ERROR] : "),
    NOT_INTEGER_ERROR("금액은 정수 형태로 입력해야 합니다."),
    EMPTY_INPUT_ERROR("값을 입력해야 합니다."),
    EMPTY_SPACE_INCLUDE_ERROR("공백 없이 입력해야 합니다."),
    INVALID_DATE_RANGE_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + message;
    }
}
