package christmas.domain;

public enum DiscountPolicy {
    CHRISTMAS(25),
    CHRISTMAS_DISCOUNT_OFFSET(1000),
    CHRISTMAS_DISCOUNT_INCREASE(100),
    DAY_OF_THE_WEEK_DISCOUNT(2023),
    STAR_DAY_DISCOUNT(1000),
    GIFT_EVENT_MINIMUM_PRICE(120000);

    private int price;

    DiscountPolicy(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
