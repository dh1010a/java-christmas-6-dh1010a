package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.orders.Orders;
import christmas.domain.visitDate.VisitDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiscountCalculatorTest {
    List<String> orderContents;
    Orders orders;
    VisitDate visitDate;
    DiscountCalculator discountCalculator;
    Receipt receipt;


    @BeforeEach
    public void beforeEach() {
        orderContents = List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1");
        orders = new Orders(orderContents);
        visitDate = new VisitDate("3");
        discountCalculator = new DiscountCalculator(orders, visitDate);
        receipt = discountCalculator.calculateAndPrintReceipt();
    }

    @Test
    public void 할인_전_총주문금액_정상_계산() {
        assertThat(receipt.getTotalOrderPrice()).isEqualTo("142,000원");
    }

    @Test
    public void 증정메뉴_정상_제공() {
        assertThat(receipt.getGiftMenu()).isEqualTo("샴페인 1개");
    }

    @Test
    public void 총혜택_금액_정상_계산() {
        assertThat(receipt.getTotalBenefitPrice()).isEqualTo("-31,246원");
    }

    @Test
    public void 할인_후_예상_결제_금액_정상_계산() {
        assertThat(receipt.getFinalPaymentPrice()).isEqualTo("135,754원");
    }

    @Test
    public void 혜택_내역_정상_적용() {
        assertThat(receipt.getBenefitDetails()).contains("크리스마스 디데이 할인: -1,200원", "평일 할인: -4,046원", "특별 할인: -1,000원",
                "증정 이벤트: -25,000원");
    }

    @Test
    public void 혜택_적용_안된_경우() {
        orderContents = List.of("타파스-1", "제로콜라-1");
        orders = new Orders(orderContents);
        visitDate = new VisitDate("3");
        discountCalculator = new DiscountCalculator(orders, visitDate);
        receipt = discountCalculator.calculateAndPrintReceipt();

        System.out.println(receipt.getTotalBenefitPrice());
    }
}

