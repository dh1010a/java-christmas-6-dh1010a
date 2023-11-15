package christmas.view;

import java.util.List;

public class OutputView {
    private static final String ANNOUNCE_MESSAGE = "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String MENU_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_MESSAGE = "<증정 메뉴>";
    private static final String BENEFIT_DETAIL_MESSAGE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_PRICE_MESSAGE = "<총혜택 금액>";
    private static final String FINAL_PAYMENT_PRICE_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>";

    public void printAnnounceMessage(String fullDate) {
        System.out.println(fullDate + ANNOUNCE_MESSAGE);
        newLine();
    }

    public void printOrderMenu(String orders) {
        System.out.println(MENU_MESSAGE);
        System.out.println(orders);
    }

    public void printTotalOrderPriceBeforeDiscount(String totalPrice) {
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE);
        System.out.println(totalPrice);
        newLine();
    }

    public void printGiftMenu(String orders) {
        System.out.println(GIFT_MENU_MESSAGE);
        System.out.println(orders);
        newLine();
    }

    public void printBenefitDetails(List<String> benefitDetails) {
        System.out.println(BENEFIT_DETAIL_MESSAGE);
        for (String details : benefitDetails) {
            System.out.println(details);
        }
        newLine();
    }

    public void printTotalBenefitPrice(String totalBenefitPrice) {
        System.out.println(TOTAL_BENEFIT_PRICE_MESSAGE);
        System.out.println(totalBenefitPrice);
        newLine();
    }

    public void printFinalPaymentPrice(String finalPaymentPrice) {
        System.out.println(FINAL_PAYMENT_PRICE_MESSAGE);
        System.out.println(finalPaymentPrice);
        newLine();
    }

    public void printEventBadge(String badge) {
        System.out.println(EVENT_BADGE_MESSAGE);
        System.out.println(badge);
    }

    private void newLine() {
        System.out.println();
    }
}
