package christmas.domain.orders;

import christmas.exception.InvalidMenuException;
import java.util.List;

public class Orders {

    private static final String HYPHEN = "-";
    private static final String EMPTY = "없음";
    private final List<String> ordersContents;


    public Orders(List<String> ordersContents) {
        this.ordersContents = ordersContents;
        validate();
    }

    public int getTotalPriceBeforeDiscount() {
        int totalPrice = 0;

        for (String contents : ordersContents) {
            String name = contents.split(HYPHEN)[0];
            Menu menu = Menu.getMenu(name);
            int orderCount = Integer.parseInt(contents.split(HYPHEN)[1]);
            totalPrice += menu.getPrice() * orderCount;
        }

        return totalPrice;
    }

    private void validate() throws IllegalArgumentException {
        for (String contents : ordersContents) {
            checkHyphenAndThrowException(contents);
            checkIsMenuIncludedAndThrowException(contents);
            checkValidateNumberAndThrowException(contents);
        }
    }

    private void checkValidateNumberAndThrowException(String contents) throws IllegalArgumentException {
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

    private void checkIsMenuIncludedAndThrowException(String contents) throws IllegalArgumentException {
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
        if (contents.split(HYPHEN).length != 2) {
            throw new InvalidMenuException();
        }
    }


}
