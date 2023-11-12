package christmas.domain.orders;

import christmas.exception.InvalidMenuException;
import java.util.List;

public class Orders {

    private static final String HYPHEN = "-";
    private static final String EMPTY = "없음";
    private final List<String> ordersContents;
    private List<MenuGroup> menus;


    public Orders(List<String> ordersContents) {
        this.ordersContents = ordersContents;
        validate(ordersContents);
    }

    private void validate(List<String> ordersContents) throws IllegalArgumentException {
        for (String contents : ordersContents) {
            checkHyphenAndThrowException(contents);
            checkIsMenuIncludedAndThrowException(contents);
            checkValidateNumberAndThrowException(contents);
        }
    }

    private void checkValidateNumberAndThrowException(String contents) {
        String numberString = contents.split(HYPHEN)[0];
        int number;

        try {
            number = Integer.parseInt(numberString);
        } catch (NumberFormatException error) {
            throw new InvalidMenuException();
        }

        if (number < 1) {
            throw new InvalidMenuException();
        }
    }

    private void checkIsMenuIncludedAndThrowException(String contents) {
        String name = contents.split(HYPHEN)[0];
        Menu menu = Menu.getMenu(name);
        if (menu.getTitle().equals(EMPTY)) {
            throw new InvalidMenuException();
        }
    }

    private void checkHyphenAndThrowException(String contents) throws IllegalArgumentException {
        if (!contents.contains(HYPHEN)) {
            throw new InvalidMenuException();
        }
    }


}
