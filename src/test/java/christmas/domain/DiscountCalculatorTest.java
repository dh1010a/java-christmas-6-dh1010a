package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.orders.Orders;
import christmas.domain.visitDate.VisitDate;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DiscountCalculatorTest {

    @Test
    public void 할인_전_총주문금액_정상_계산() throws Exception {
        //given
        List<String> orderContents = List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1");
        Orders orders = new Orders(orderContents);
        VisitDate visitDate = new VisitDate("3");
        //when
        DiscountCalculator discountCalculator = new DiscountCalculator(orders, visitDate);
        Receipt receipt = discountCalculator.calculateAndPrintReceipt();
        //then
        assertThat(receipt.getTotalOrderPrice()).isEqualTo("142,000원");
    }

    @Test
    public void 증정메뉴_정상_제공() throws Exception {
        //given
        List<String> orderContents = List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1");
        Orders orders = new Orders(orderContents);
        VisitDate visitDate = new VisitDate("3");
        //when
        DiscountCalculator discountCalculator = new DiscountCalculator(orders, visitDate);
        Receipt receipt = discountCalculator.calculateAndPrintReceipt();
        //then
        assertThat(receipt.getGiftMenu()).isEqualTo("샴페인 1개");
    }
}

