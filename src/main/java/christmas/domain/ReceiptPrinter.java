package christmas.domain;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ReceiptPrinter {
    private static final String CHRISTMAS_DISCOUNT_MESSAGE = "크리스마스 디데이 할인: -";
    private static final String WEEKEND_DISCOUNT_MESSAGE = "주말 할인: -";
    private static final String WEEKDAY_DISCOUNT_MESSAGE = "평일 할인: -";
    private static final String STAR_DAY_DISCOUNT_MESSAGE = "특별 할인: -";
    private static final String GIFT_EVENT_DISCOUNT_MESSAGE = "증정 이벤트: -";
    private static final String CHAMPAGNE_GIFT = "샴페인 1개";
    private static final String SUFFIX = "원";

    private List<String> benefitDetails;
    private AmountOfMoney amountOfMoney;
    private DecimalFormat formatter;

    public ReceiptPrinter() {
        formatter = new DecimalFormat("###,###");
        benefitDetails = new ArrayList<>();
    }

    public Receipt printReceipt(AmountOfMoney amountOfMoney) {
        Receipt receipt = new Receipt(amountOfMoney);
        if (amountOfMoney.totalBenefitPrice() > 0) {
            receipt.setBenefitDetails(benefitDetails);
        }
        if (hasChampagne()) {
            receipt.setGiftMenu(CHAMPAGNE_GIFT);
        }
        return receipt;
    }

    private boolean hasChampagne() {
        return benefitDetails.stream()
                .anyMatch(value -> value.contains(GIFT_EVENT_DISCOUNT_MESSAGE));
    }

    public void addChristmasDiscountMessage(int price) {
        if (price > 0) {
            benefitDetails.add(CHRISTMAS_DISCOUNT_MESSAGE + numberFormatter(price) + SUFFIX);
        }
    }

    public void addWeekDayDiscountMessage(int price) {
        benefitDetails.add(WEEKDAY_DISCOUNT_MESSAGE + numberFormatter(price) + SUFFIX);
    }

    public void addWeekendDiscountMessage(int price) {
        benefitDetails.add(WEEKEND_DISCOUNT_MESSAGE + numberFormatter(price) + SUFFIX);
    }

    public void addStarDayDiscountMessage(int price) {
        benefitDetails.add(STAR_DAY_DISCOUNT_MESSAGE + numberFormatter(price) + SUFFIX);
    }

    public void addGiftEventDiscountMessage(int price) {
        benefitDetails.add(GIFT_EVENT_DISCOUNT_MESSAGE + numberFormatter(price) + SUFFIX);
    }

    private String numberFormatter(int number) {
        return formatter.format(number);
    }
}
