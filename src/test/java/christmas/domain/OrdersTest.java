package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.orders.Menu;
import christmas.domain.orders.Orders;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    @DisplayName("중복된_메뉴입력시_예외_반환")
    @MethodSource("duplicatedMenuProvider")
    @ParameterizedTest
    void duplicatedMenuExceptionTest(List<String> input) {
        assertThatThrownBy(() -> new Orders(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> duplicatedMenuProvider() {
        return Stream.of(
                arguments(List.of("티본스테이크-1", "티본스테이크-1")),
                arguments(List.of("제로콜라-1,티본스테이크-1,제로콜라-1")),
                arguments(List.of("아이스크림-1,제로콜라-1,아이스크림-1"))
        );
    }

    @DisplayName("없는_메뉴입력시_예외_반환")
    @MethodSource("nonexistentMenuProvider")
    @ParameterizedTest
    void nonexistentMenuExceptionTest(List<String> input) {
        assertThatThrownBy(() -> new Orders(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> nonexistentMenuProvider() {
        return Stream.of(
                arguments(List.of("티본스테이크-1", "티본-1")),
                arguments(List.of("제로콜라-1,티본스테이크-1,코카콜라-1")),
                arguments(List.of("아이스크림-1,제로콜라-1,초코아이스크림-1"))
        );
    }

    @DisplayName("메뉴주문갯수_예외_반환")
    @MethodSource("invalidNumberMenuProvider")
    @ParameterizedTest
    void invalidNumberMenuExceptionTest(List<String> input) {
        assertThatThrownBy(() -> new Orders(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> invalidNumberMenuProvider() {
        return Stream.of(
                arguments(List.of("티본스테이크-r", "타파스-1")),
                arguments(List.of("제로콜라-1,티본스테이크-0,아이스크림-1")),
                arguments(List.of("바비큐립-one,제로콜라-1,티본스테이크-1"))
        );
    }

    @DisplayName("주문_형식이_틀릴시_예외_반환")
    @MethodSource("invalidMenuFormatProvider")
    @ParameterizedTest
    void invalidMenuFormatExceptionTest(List<String> input) {
        assertThatThrownBy(() -> new Orders(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> invalidMenuFormatProvider() {
        return Stream.of(
                arguments(List.of("티본스테이크1", "바비큐립-1")),
                arguments(List.of("제로콜라-1,티본스테이크1개,바비큐립-1")),
                arguments(List.of("아이스크림.1,제로콜라.1,바비큐립-1")),
                arguments(List.of("아이스크림=1,제로콜라-1,바비큐립-1"))
        );
    }

}
