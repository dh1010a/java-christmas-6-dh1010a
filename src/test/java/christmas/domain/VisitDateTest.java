package christmas.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;

import christmas.exception.ExceptionMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class VisitDateTest {

    String exceptionMessage = ExceptionMessage.INVALID_DATE_RANGE_ERROR.getMessage();

    @Test
    void 날짜범위_1일과_31일사이가_아니면_에러발생() {
        //given
        String input = "32";

        assertSimpleTest(() -> Assertions.assertThatThrownBy(() -> new VisitDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(exceptionMessage)
        );
    }
}
