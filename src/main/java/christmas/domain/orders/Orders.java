package christmas.domain.orders;

import christmas.exception.InvalidMenuException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Orders {

    private static final String HYPHEN = "-";
    private static final String EMPTY = "없음";
    private static final String NEWLINE = "\n";
    private static final String UNIT = "개";
    private static final int MINIMUM_EVENT_PRICE = 10000;

    private final List<String> ordersContents;
    private Map<Menu, Integer> orders;

    public Orders(List<String> ordersContents) {
        this.ordersContents = ordersContents;
        validate();
        orders = makeOrders();
    }

    public int getTotalPriceBeforeDiscount() {
        int totalPrice = 0;

        for (Menu menu : orders.keySet()) {
            totalPrice += menu.getPrice() * orders.get(menu);
        }

        return totalPrice;
    }

    public boolean isEventTarget() {
        return getTotalPriceBeforeDiscount() >= MINIMUM_EVENT_PRICE;
    }

    public int getMainMenuCount() {
        int count = 0;

        for (Menu menu : orders.keySet()) {
            if (MenuGroup.isMainMenu(menu)) {
                count++;
            }
        }

        return count;
    }

    public int getDesertMenuCount() {
        int count = 0;

        for (Menu menu : orders.keySet()) {
            if (MenuGroup.isDesertMenu(menu)) {
                count++;
            }
        }

        return count;
    }

    public String toString() {
        String result = "";
        for (Menu menu : orders.keySet()) {
            result += menu.getTitle() + " " + orders.get(menu) + UNIT + NEWLINE;
        }
        return result;
    }

    private Map<Menu, Integer> makeOrders() {
        Map<Menu, Integer> orders = new HashMap<>();
        for (String contents : ordersContents) {
            String name = contents.split(HYPHEN)[0];
            int orderCount = Integer.parseInt(contents.split(HYPHEN)[1]);
            Menu menu = Menu.getMenu(name);

            orders.put(menu, orderCount);
        }
        return orders;
    }

    private void validate() throws IllegalArgumentException {
        for (String contents : ordersContents) {
            checkHyphenAndThrowException(contents);
            checkIsMenuIncludedAndThrowException(contents);
            checkValidateNumberAndThrowException(contents);
        }
        checkDuplicateMenuAndThrowException();
    }

    private void checkDuplicateMenuAndThrowException() throws IllegalArgumentException {
        Set<String> menuNames = new HashSet<>();
        for (String contents : ordersContents) {
            String name = contents.split(HYPHEN)[0];
            menuNames.add(name);
        }
        if (menuNames.size() != ordersContents.size()) {
            throw new InvalidMenuException();
        }
    }

    private void checkValidateNumberAndThrowException(String contents) throws IllegalArgumentException {
        String numberString = contents.split(HYPHEN)[1];
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
