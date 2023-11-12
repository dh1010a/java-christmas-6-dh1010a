package christmas.domain.orders;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이스프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000),
    T_BONE_STEAK("티본스테이크", 55000),
    BARBECUE_LIP("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000),
    CHOCO_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000),
    ZERO_COKE("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000),
    EMPTY("없음", 0);

    private String title;
    private int price;

    Menu(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public static Menu getMenu(String name) {
        return Arrays.stream(values())
                .filter(value -> value.title.equals(name))
                .findAny()
                .orElse(EMPTY);
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }
}
