package christmas.domain;

import java.text.DecimalFormat;

public class Receipt {
    private static final String DEFAULT_MESSAGE = "없음";
    private static final String UNIT = "원";
    private static final String PREFIX = "-";

    private String totalOrderPrice;
    private String totalBenefitPrice;
    private String finalPaymentPrice;
    private String benefitDetails;
    private String giftMenu;
    private DecimalFormat formatter = new DecimalFormat("###,###");

    public Receipt(int totalOrderPrice, int totalDiscountPrice, int totalBenefitPrice) {
        parsePriceToString(totalOrderPrice, totalBenefitPrice);
        setFinalPaymentPrice(totalOrderPrice, totalDiscountPrice);
        benefitDetails = DEFAULT_MESSAGE;
        giftMenu = DEFAULT_MESSAGE;
    }

    private void parsePriceToString(int totalOrderPrice, int totalBenefitPrice) {
        this.totalBenefitPrice = PREFIX + numberFormatter(totalOrderPrice) + UNIT;
        this.totalOrderPrice = numberFormatter(totalBenefitPrice) + UNIT;
    }

    public String getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public String getTotalBenefitPrice() {
        return totalBenefitPrice;
    }

    public void setFinalPaymentPrice(int totalOrderPrice, int totalDiscountPrice) {
        finalPaymentPrice = numberFormatter(totalOrderPrice - totalDiscountPrice);
    }

    public String getBenefitDetails() {
        return benefitDetails;
    }

    public String getFinalPaymentPrice() {
        return finalPaymentPrice;
    }

    public String getGiftMenu() {
        return giftMenu;
    }

    public void setGiftMenu(String message) {
        giftMenu = message;
    }

    public void setBenefitDetails(String message) {
        benefitDetails = message;
    }

    private String numberFormatter(int number) {
        return formatter.format(number);
    }
}
