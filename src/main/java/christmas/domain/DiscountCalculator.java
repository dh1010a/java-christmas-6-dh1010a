package christmas.domain;

import christmas.domain.orders.Orders;
import christmas.domain.visitDate.VisitDate;

public class DiscountCalculator {
    private static final int CHRISTMAS = 25;
    private static final int DISCOUNT_OFFSET = 1000;
    private static final int DISCOUNT_INCREASE = 100;

    private final Orders orders;
    private final VisitDate visitDate;

    public DiscountCalculator(Orders orders, VisitDate visitDate) {
        this.orders = orders;
        this.visitDate = visitDate;
    }

    private void getTotalDiscount() {

    }

    private int calculateChristmasDiscount() {
        int date = visitDate.getDate();

        if (isBeforeChristmas(date)) {
            return DISCOUNT_OFFSET + ((date - 1) * DISCOUNT_INCREASE);
        }

        return 0;
    }

    private boolean isBeforeChristmas(int date) {
        return CHRISTMAS >= date;
    }
}
