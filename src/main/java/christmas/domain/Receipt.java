package christmas.domain;

import java.text.DecimalFormat;
import java.util.List;

public class Receipt {
    private static final String DEFAULT_MESSAGE = "없음";
    private static final List<String> DEFAULT_LIST = List.of(DEFAULT_MESSAGE);
    private static final String UNIT = "원";
    private static final String PREFIX = "-";

    List<String> benefitDetails;
    private String totalOrderPrice;
    private String totalBenefitPrice;
    private String finalPaymentPrice;
    private String giftMenu;
    private DecimalFormat formatter;

    public Receipt(AmountOfMoney amountOfMoney) {
        formatter = new DecimalFormat("###,###");
        parsePriceToString(amountOfMoney);
        setFinalPaymentPrice(amountOfMoney);
        benefitDetails = DEFAULT_LIST;
        giftMenu = DEFAULT_MESSAGE;
    }

    public String getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public String getTotalBenefitPrice() {
        return totalBenefitPrice;
    }

    public List<String> getBenefitDetails() {
        return benefitDetails;
    }

    public String getFinalPaymentPrice() {
        return finalPaymentPrice;
    }

    public String getGiftMenu() {
        return giftMenu;
    }

    public void setGiftMenu(String message) {
        if (!message.isEmpty()) {
            giftMenu = message;
        }
    }

    public void setBenefitDetails(List<String> benefitDetails) {
        if (benefitDetails.size() > 0) {
            this.benefitDetails = benefitDetails;
        }
    }

    private void parsePriceToString(AmountOfMoney amountOfMoney) {
        if (amountOfMoney.totalBenefitPrice() > 0) {
            this.totalBenefitPrice = PREFIX + totalBenefitPrice;
        }
        this.totalBenefitPrice = numberFormatter(amountOfMoney.totalBenefitPrice()) + UNIT;
        this.totalOrderPrice = numberFormatter(amountOfMoney.totalOrderPrice()) + UNIT;
    }

    private void setFinalPaymentPrice(AmountOfMoney amountOfMoney) {
        finalPaymentPrice =
                numberFormatter(amountOfMoney.totalOrderPrice() - amountOfMoney.totalDiscountPrice()) + UNIT;
    }

    private String numberFormatter(int number) {
        return formatter.format(number);
    }
}
