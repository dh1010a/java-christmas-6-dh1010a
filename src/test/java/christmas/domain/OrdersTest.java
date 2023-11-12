package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.orders.Menu;
import christmas.domain.orders.Orders;
import java.util.List;
import org.junit.jupiter.api.Test;

public class OrdersTest {

    @Test
    public void 주문내역_문자열_반환() throws Exception {
        //given
        List<String> orderContents = List.of("타파스-1", "제로콜라-1");
        //when
        Orders orders = new Orders(orderContents);
        //then
        assertThat(orders.toString()).contains("타파스 1개", "제로콜라 1개");
    }

    @Test
    public void 주문금액_일치() throws Exception {
        //given
        List<String> orderContents = List.of("타파스-1", "제로콜라-1", "아이스크림-1");
        //when
        Orders orders = new Orders(orderContents);
        //then
        assertThat(orders.getTotalPriceBeforeDiscount()).isEqualTo(13500);
    }

    @Test
    public void 메인메뉴_확인() throws Exception {
        //given
        List<String> orderContents = List.of("티본스테이크-1", "제로콜라-1", "아이스크림-1");
        //when
        Orders orders = new Orders(orderContents);
        Menu menu = Menu.getMenu("티본스테이크");
        Menu menu1 = Menu.getMenu("제로콜라");
        //then
        assertThat(orders.isMainMenu(menu)).isEqualTo(true);
        assertThat(orders.isMainMenu(menu1)).isEqualTo(false);
    }

    @Test
    public void 주문금액에_따라_이벤트_대상인지_확인() throws Exception {
        //given
        List<String> orderContents = List.of("제로콜라-1");
        List<String> orderContents1 = List.of("제로콜라-1", "티본스테이크-1");
        //when
        Orders orders = new Orders(orderContents);
        Orders orders1 = new Orders(orderContents1);
        //then
        assertThat(orders.isEventTarget()).isEqualTo(false);
        assertThat(orders1.isEventTarget()).isEqualTo(true);
    }
}
