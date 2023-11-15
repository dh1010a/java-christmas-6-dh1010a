package christmas.exception;

public enum ExceptionMessage {

    PREFIX("[ERROR] "),
    INVALID_MENU_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_DATE_RANGE_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ONLY_CONTAIN_DRINK_ERROR("음료만 주문할 수 없습니다. 다시 입력해 주세요."),
    TOTAL_ORDER_COUNT_OVERFLOW_ERROR("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해주세요.");
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + message;
    }
}
