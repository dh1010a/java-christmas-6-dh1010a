package christmas.domain.visitDate;

import java.util.ArrayList;
import java.util.List;

public class Weekend {
    private static final int START_WEEKEND = 1;
    private static final int MONTH_END_DATE = 31;
    private static final int WEEK = 7;

    private List<Integer> weekends;

    public Weekend() {
        initWeekends();
    }

    public boolean isWeekend(int date) {
        return weekends.contains(date);
    }

    private void initWeekends() {
        weekends = new ArrayList<>();

        for (int i = START_WEEKEND; i <= MONTH_END_DATE; i += WEEK) {
            weekends.add(i);
            weekends.add(i + 1);
        }
    }
}
