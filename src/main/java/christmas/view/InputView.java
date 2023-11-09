package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.EmptyInputException;
import christmas.exception.NotIntegerInputException;

public class InputView {
    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ASK_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public int getVisitDate() {
        System.out.println(WELCOME_MESSAGE);
        String input = readInput();

        dateValidator(input);

        return Integer.parseInt(input);
    }

    private void dateValidator(String input) {
        checkIsIntegerAndThrowException(input);
        checkIsEmptyAndThrowException(input);
    }

    private static void checkIsIntegerAndThrowException(String input) throws IllegalArgumentException {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException error) {
            throw new NotIntegerInputException();
        }
    }

    private static void checkIsEmptyAndThrowException(String input) throws IllegalArgumentException {
        if (input.isEmpty()) {
            throw new EmptyInputException();
        }
    }

    private String readInput() {
        return Console.readLine();
    }

}
