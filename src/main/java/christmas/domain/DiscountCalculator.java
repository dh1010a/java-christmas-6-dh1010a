package christmas.domain;

import christmas.domain.orders.Orders;
import christmas.domain.visitDate.VisitDate;

public class DiscountCalculator {
    private static final int CHRISTMAS = 25; //추후 리팩토링을 통해 Enum으로 분리
    private static final int CHRISTMAS_DISCOUNT_OFFSET = 1000;
    private static final int CHRISTMAS_DISCOUNT_INCREASE = 100;
    private static final int DAY_OF_THE_WEEK_DISCOUNT = 2023;
    private static final int STAR_DAY_DISCOUNT = 1000;
    private static final int GIFT_EVENT_MINIMUM_PRICE = 120000;

    private final Orders orders;
    private final VisitDate visitDate;
    private int totalOrderPrice;

    public DiscountCalculator(Orders orders, VisitDate visitDate) {
        this.orders = orders;
        this.visitDate = visitDate;
        totalOrderPrice = orders.getTotalPriceBeforeDiscount();
    }

    private void getTotalDiscount() {

    }

    private int calculateChristmasDiscount() {
        int date = visitDate.getDate();

        if (isBeforeChristmas(date)) {
            return CHRISTMAS_DISCOUNT_OFFSET + ((date - 1) * CHRISTMAS_DISCOUNT_INCREASE);
        }

        return 0;
    }

    private int calculateDayOfTheWeekDiscount() {
        if (visitDate.isWeekend()) {
            return orders.getMainMenuCount() * DAY_OF_THE_WEEK_DISCOUNT;
        }
        return orders.getDesertMenuCount() * DAY_OF_THE_WEEK_DISCOUNT;
    }

    private int calculateStarDayDiscount() {
        if (visitDate.isStarDay()) {
            return STAR_DAY_DISCOUNT;
        }
        return 0;
    }

    private int applyGiftEvent() {
        int price = 0;

        if (totalOrderPrice >= GIFT_EVENT_MINIMUM_PRICE) {
            price = orders.giveChampagneAndReturnPrice();
        }

        return price;
    }

    private boolean isBeforeChristmas(int date) {
        return CHRISTMAS >= date;
    }
}
