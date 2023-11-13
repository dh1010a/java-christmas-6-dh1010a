package christmas.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitDateTest {

    String exceptionMessage = ExceptionMessage.INVALID_DATE_RANGE_ERROR.getMessage();

    @DisplayName("날짜범위가_1일과_31일사이가_아니면_에러발생")
    @ValueSource(strings = {"0", "33", "40", "1000"})
    @ParameterizedTest
    void invalidDateRangeExceptionTest(String input) {
        assertSimpleTest(() -> assertThatThrownBy(() -> new VisitDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(exceptionMessage)
        );
    }

    @DisplayName("정수형식의_날짜가_아니면_에러발생")
    @ValueSource(strings = {"0 1", " ", "a", "first", "1-1"})
    @ParameterizedTest
    void invalidDateInputExceptionTest(String input) {
        assertSimpleTest(() -> assertThatThrownBy(() -> new VisitDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(exceptionMessage)
        );
    }

    @DisplayName("주말이면_True_반환")
    @ValueSource(strings = {"1", "16", "22", "29"})
    @ParameterizedTest
    void isHolidayTest(String input) {
        //given
        VisitDate visitDate = new VisitDate(input);

        assertThat(visitDate.isWeekend()).isEqualTo(true);
    }

    @DisplayName("주말이_아니면_False_반환")
    @ValueSource(strings = {"3", "17", "19", "31"})
    @ParameterizedTest
    void isNotHolidayTest(String input) {
        //given
        VisitDate visitDate = new VisitDate(input);

        assertThat(visitDate.isWeekend()).isEqualTo(false);
    }

    @DisplayName("별표친_날짜면_True_반환")
    @ValueSource(strings = {"3", "10", "17", "25", "24"})
    @ParameterizedTest
    void isStarDayTest(String input) {
        //given
        VisitDate visitDate = new VisitDate(input);

        assertThat(visitDate.isStarDay()).isEqualTo(true);
    }

    @DisplayName("별표친_날짜가_아니면_False_반환")
    @ValueSource(strings = {"4", "11", "27", "30"})
    @ParameterizedTest
    void isNotStarDayTest(String input) {
        //given
        VisitDate visitDate = new VisitDate(input);

        assertThat(visitDate.isStarDay()).isEqualTo(false);
    }
    
}
