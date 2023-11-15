package christmas.domain;

import christmas.domain.orders.Orders;
import christmas.domain.receipt.AmountOfMoney;
import christmas.domain.receipt.Receipt;
import christmas.domain.receipt.ReceiptPrinter;
import christmas.domain.visitDate.VisitDate;

public class DiscountCalculator {
    private static final int CHRISTMAS = 25;

    private final Orders orders;
    private final VisitDate visitDate;
    private int totalOrderPrice;
    private ReceiptPrinter receiptPrinter;

    public DiscountCalculator(Orders orders, VisitDate visitDate) {
        this.orders = orders;
        this.visitDate = visitDate;
        totalOrderPrice = orders.getTotalPriceBeforeDiscount();
        receiptPrinter = new ReceiptPrinter();
    }

    public Receipt calculateAndPrintReceipt() {
        int totalDiscountPrice = getTotalDiscountPrice();
        AmountOfMoney amountOfMoney = new AmountOfMoney(totalOrderPrice, totalDiscountPrice,
                getTotalBenefitPrice(totalDiscountPrice));
        return receiptPrinter.printReceipt(amountOfMoney);
    }

    private int getTotalBenefitPrice(int totalDiscountPrice) {
        return totalDiscountPrice + applyGiftEvent();
    }


    private int getTotalDiscountPrice() {
        int totalDiscountPrice = 0;
        if (orders.isEventTarget()) {
            totalDiscountPrice += christmasDiscount();
            totalDiscountPrice += dayOfTheWeekDiscount();
            totalDiscountPrice += starDayDiscount();
        }
        return totalDiscountPrice;
    }

    private int christmasDiscount() {
        int date = visitDate.getDate();
        DiscountPolicy christmasDiscountOffset = DiscountPolicy.CHRISTMAS_DISCOUNT_OFFSET;
        DiscountPolicy discountIncrease = DiscountPolicy.CHRISTMAS_DISCOUNT_INCREASE;

        if (isBeforeChristmas(date)) {
            int price = christmasDiscountOffset.getPrice() + ((date - 1) * discountIncrease.getPrice());
            receiptPrinter.addChristmasDiscountMessage(price);
            return price;
        }

        return 0;
    }

    private int dayOfTheWeekDiscount() {
        if (visitDate.isWeekend()) {
            return calculateWeekendDiscount();
        }
        return calculateWeekDayDiscount();
    }

    private int calculateWeekDayDiscount() {
        DiscountPolicy dayOfTheWeekDiscount = DiscountPolicy.DAY_OF_THE_WEEK_DISCOUNT;
        int price = orders.getDesertMenuCount() * dayOfTheWeekDiscount.getPrice();
        if (price > 0) {
            receiptPrinter.addWeekDayDiscountMessage(price);
        }
        return price;
    }

    private int calculateWeekendDiscount() {
        DiscountPolicy dayOfTheWeekDiscount = DiscountPolicy.DAY_OF_THE_WEEK_DISCOUNT;
        int price = orders.getMainMenuCount() * dayOfTheWeekDiscount.getPrice();
        if (price > 0) {
            receiptPrinter.addWeekendDiscountMessage(price);
        }
        return price;
    }

    private int starDayDiscount() {
        DiscountPolicy starDayDiscount = DiscountPolicy.STAR_DAY_DISCOUNT;
        if (visitDate.isStarDay()) {
            receiptPrinter.addStarDayDiscountMessage(starDayDiscount.getPrice());
            return starDayDiscount.getPrice();
        }
        return 0;
    }

    private int applyGiftEvent() {
        int price = 0;
        DiscountPolicy giftEventMinimumPrice = DiscountPolicy.GIFT_EVENT_MINIMUM_PRICE;

        if (totalOrderPrice >= giftEventMinimumPrice.getPrice()) {
            price = orders.giveChampagneAndReturnPrice();
            receiptPrinter.addGiftEventDiscountMessage(price);
        }

        return price;
    }

    private boolean isBeforeChristmas(int date) {
        return CHRISTMAS >= date;
    }
}
