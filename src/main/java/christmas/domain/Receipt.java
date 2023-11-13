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

    public Receipt(AmountOfMoney amountOfMoney) {
        parsePriceToString(amountOfMoney);
        setFinalPaymentPrice(amountOfMoney);
        benefitDetails = DEFAULT_MESSAGE;
        giftMenu = DEFAULT_MESSAGE;
    }

    private void parsePriceToString(AmountOfMoney amountOfMoney) {
        this.totalBenefitPrice = PREFIX + numberFormatter(amountOfMoney.totalBenefitPrice()) + UNIT;
        this.totalOrderPrice = numberFormatter(amountOfMoney.totalOrderPrice()) + UNIT;
    }

    public String getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public String getTotalBenefitPrice() {
        return totalBenefitPrice;
    }

    public void setFinalPaymentPrice(AmountOfMoney amountOfMoney) {
        finalPaymentPrice = numberFormatter(amountOfMoney.totalOrderPrice() - amountOfMoney.totalDiscountPrice());
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
