package christmas.domain;

import christmas.Receipt;

public class ReceiptPrinter {
    private static final String CHRISTMAS_DISCOUNT_MESSAGE = "크리스마스 디데이 할인 : -";
    private static final String WEEKEND_DISCOUNT_MESSAGE = "주말 할인 : -";
    private static final String WEEKDAY_DISCOUNT_MESSAGE = "평일 할인 : -";
    private static final String STAR_DAY_DISCOUNT_MESSAGE = "특별 할인 : -";
    private static final String GIFT_EVENT_DISCOUNT_MESSAGE = "증정 이벤트 : -";
    private static final String CHAMPAGNE_GIFT = "샴페인 1개";
    private static final String SUFFIX = "원\n";

    private String benefitDetails;
    private int totalOrderPrice;

    public ReceiptPrinter(int totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
        benefitDetails = "";
    }

    public Receipt printReceipt(int totalDiscountPrice) {
        Receipt receipt = new Receipt(totalOrderPrice, totalDiscountPrice);
        if (totalDiscountPrice > 0) {
            receipt.setBenefitDetails(benefitDetails);
        }
        if (benefitDetails.contains(GIFT_EVENT_DISCOUNT_MESSAGE)) {
            receipt.setGiftMenu(CHAMPAGNE_GIFT);
        }
        return receipt;
    }

    public void addChristmasDiscountMessage(int price) {
        if (price > 0) {
            benefitDetails += CHRISTMAS_DISCOUNT_MESSAGE + price + SUFFIX;
        }
    }

    public void addWeekDayDiscountMessage(int price) {
        benefitDetails += WEEKDAY_DISCOUNT_MESSAGE + price + SUFFIX;
    }

    public void addWeekendDiscountMessage(int price) {
        benefitDetails += WEEKEND_DISCOUNT_MESSAGE + price + SUFFIX;
    }

    public void addStarDayDiscountMessage(int price) {
        benefitDetails += STAR_DAY_DISCOUNT_MESSAGE + price + SUFFIX;
    }

    public void addGiftEventDiscountMessage(int price) {
        benefitDetails += GIFT_EVENT_DISCOUNT_MESSAGE + price + SUFFIX;
    }
}
