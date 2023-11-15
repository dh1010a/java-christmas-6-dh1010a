package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.domain.orders.Orders;
import christmas.exception.ExceptionMessage;
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
    public void 주문금액에_따라_이벤트_대상인지_확인() throws Exception {
        //given
        List<String> orderContents = List.of("아이스크림-1");
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
                arguments(List.of("제로콜라-1", "티본스테이크-1", "제로콜라-1")),
                arguments(List.of("아이스크림-1", "제로콜라-1", "아이스크림-1"))
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
                arguments(List.of("제로콜라-1", "티본스테이크-1", "코카콜라-1")),
                arguments(List.of("아이스크림-1", "제로콜라-1", "초코아이스크림-1"))
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
                arguments(List.of("제로콜라-1", "티본스테이크-0", "아이스크림-1")),
                arguments(List.of("바비큐립-one", "제로콜라-1", "티본스테이크-1"))
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
                arguments(List.of("아이스크림.1", "제로콜라.1", "바비큐립-1")),
                arguments(List.of("아이스크림=1", "제로콜라-1", "바비큐립-1"))
        );
    }

    @DisplayName("음료수만_주문시_예외_반환")
    @MethodSource("onlyContainDrinkMenuProvider")
    @ParameterizedTest
    void onlyContainDrinkMenuExceptionTest(List<String> input) {
        assertThatThrownBy(() -> new Orders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.ONLY_CONTAIN_DRINK_ERROR.getMessage());
    }

    static Stream<Arguments> onlyContainDrinkMenuProvider() {
        return Stream.of(
                arguments(List.of("샴페인-1", "제로콜라-1")),
                arguments(List.of("제로콜라-1", "샴페인-1", "레드와인-1"))
        );
    }

    @DisplayName("메뉴_총20개_이상_주문시_예외_반환")
    @MethodSource("TotalOrderCountOverflowMenuProvider")
    @ParameterizedTest
    void TotalOrderCountOverflowExceptionTest(List<String> input) {
        assertThatThrownBy(() -> new Orders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.TOTAL_ORDER_COUNT_OVERFLOW_ERROR.getMessage());
    }

    static Stream<Arguments> TotalOrderCountOverflowMenuProvider() {
        return Stream.of(
                arguments(List.of("티본스테이크-10", "제로콜라-21")),
                arguments(List.of("아이스크림-17", "샴페인-2", "레드와인-2"))
        );
    }

}
