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
    private ReceiptPrinter receiptPrinter;

    public DiscountCalculator(Orders orders, VisitDate visitDate) {
        this.orders = orders;
        this.visitDate = visitDate;
        totalOrderPrice = orders.getTotalPriceBeforeDiscount();
        receiptPrinter = new ReceiptPrinter(totalOrderPrice);

    }

    public Receipt calculateAndPrintReceipt() {
        int totalDiscountPrice = getTotalDiscountPrice();
        return receiptPrinter.printReceipt(totalDiscountPrice, getTotalBenefitPrice(totalDiscountPrice));
    }

    private int getTotalBenefitPrice(int totalDiscountPrice) {
        return totalDiscountPrice + applyGiftEvent();
    }


    private int getTotalDiscountPrice() {
        int totalDiscountPrice = 0;

        totalDiscountPrice += calculateChristmasDiscount();
        totalDiscountPrice += calculateDayOfTheWeekDiscount();
        totalDiscountPrice += calculateStarDayDiscount();

        return totalDiscountPrice;
    }

    private int calculateChristmasDiscount() {
        int date = visitDate.getDate();

        if (isBeforeChristmas(date)) {
            int price = CHRISTMAS_DISCOUNT_OFFSET + ((date - 1) * CHRISTMAS_DISCOUNT_INCREASE);
            receiptPrinter.addChristmasDiscountMessage(price);
            return price;
        }

        return 0;
    }

    private int calculateDayOfTheWeekDiscount() {
        if (visitDate.isWeekend()) {
            return calculateWeekendDiscount();
        }
        return calculateWeekDayDiscount();
    }

    private int calculateWeekDayDiscount() {
        int price = orders.getDesertMenuCount() * DAY_OF_THE_WEEK_DISCOUNT;
        receiptPrinter.addWeekDayDiscountMessage(price);
        return price;
    }

    private int calculateWeekendDiscount() {
        int price = orders.getMainMenuCount() * DAY_OF_THE_WEEK_DISCOUNT;
        receiptPrinter.addWeekendDiscountMessage(price);
        return price;
    }

    private int calculateStarDayDiscount() {
        if (visitDate.isStarDay()) {
            receiptPrinter.addStarDayDiscountMessage(STAR_DAY_DISCOUNT);
            return STAR_DAY_DISCOUNT;
        }
        return 0;
    }

    private int applyGiftEvent() {
        int price = 0;

        if (totalOrderPrice >= GIFT_EVENT_MINIMUM_PRICE) {
            price = orders.giveChampagneAndReturnPrice();
            receiptPrinter.addGiftEventDiscountMessage(price);
        }

        return price;
    }

    private boolean isBeforeChristmas(int date) {
        return CHRISTMAS >= date;
    }
}
