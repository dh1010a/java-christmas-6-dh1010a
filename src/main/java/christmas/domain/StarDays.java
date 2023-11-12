package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class StarDays {
    private static final int END_DATE = 31;
    private static final int START_STAR_DATE = 3;
    private static final int WEEK = 7;
    private static final int CHRISTMAS_DATE = 25;

    private final List<Integer> starDays;

    public StarDays() {
        starDays = initStarDays();
    }

    public boolean isStarDay(int date) {
        return starDays.contains(date);
    }

    private List<Integer> initStarDays() {
        List<Integer> starDays = new ArrayList<>();

        for (int i = START_STAR_DATE; i <= END_DATE; i += WEEK) {
            starDays.add(i);
        }
        starDays.add(CHRISTMAS_DATE);

        return starDays;
    }
}
