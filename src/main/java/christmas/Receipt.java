package christmas;

public class Receipt {
    private static final String DEFAULT_MESSAGE = "없음";

    private final int totalOrderPrice;
    private final int totalDiscountPrice;
    private String benefitDetails;
    private String giftMenu;

    public Receipt(int totalOrderPrice, int totalDiscountPrice) {
        this.totalDiscountPrice = totalDiscountPrice;
        this.totalOrderPrice = totalOrderPrice;
        benefitDetails = DEFAULT_MESSAGE;
        giftMenu = DEFAULT_MESSAGE;
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public int getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public String getBenefitDetails() {
        return benefitDetails;
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


}
