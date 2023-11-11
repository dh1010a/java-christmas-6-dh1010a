package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ExceptionMessage;
import christmas.exception.InvalidDateRangeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ASK_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String EMPTY_SPACE = " ";

    public int getVisitDate() {
        System.out.println(ASK_VISIT_DATE_MESSAGE);

        String input = Console.readLine();
        try {
            dateInputValidator(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return getVisitDate();
        }
    }

    public List<String> getUserOrderContents() {
        System.out.println(ASK_MENU_MESSAGE);

        String input = Console.readLine();
        try {
            orderContentsInputValidator(input);
            return parseOrderContentsInputToStringList(input);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return getUserOrderContents();
        }
    }

    private void dateInputValidator(String input) throws IllegalArgumentException {
        String error = ExceptionMessage.INVALID_DATE_RANGE_ERROR.getMessage();
        checkIsNotIntegerAndThrowException(input);
        checkIsNotEmptyAndThrowException(input, error);
        checkIsNotIncludeSpaceAndThrowException(input, error);
    }

    private void orderContentsInputValidator(String input) {
        String error = ExceptionMessage.INVALID_MENU_ERROR.getMessage();
        checkIsNotEmptyAndThrowException(input, error);
        checkIsNotIncludeSpaceAndThrowException(input, error);
    }

    private List<String> parseOrderContentsInputToStringList(String input) {
        return new ArrayList<>(Arrays.asList(input.split(",")));
    }

    private void checkIsNotIntegerAndThrowException(String input) throws IllegalArgumentException {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException error) {
            throw new InvalidDateRangeException();
        }
    }

    private void checkIsNotIncludeSpaceAndThrowException(String input, String error) throws IllegalArgumentException {
        if (input.contains(EMPTY_SPACE)) {
            throw new IllegalArgumentException(error);
        }
    }

    private void checkIsNotEmptyAndThrowException(String input, String error) throws IllegalArgumentException {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(error);
        }
        if (parseOrderContentsInputToStringList(input).isEmpty()) {
            throw new IllegalArgumentException(error);
        }
    }

}
