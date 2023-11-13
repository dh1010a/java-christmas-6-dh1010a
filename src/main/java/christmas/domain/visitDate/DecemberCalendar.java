package christmas.domain.visitDate;

public class DecemberCalendar {
    private static final int START_DATE = 1;
    private static final int END_DATE = 31;

    private final Weekend weekend;
    private StarDays starDays;

    public DecemberCalendar() {
        weekend = new Weekend();
        starDays = new StarDays();
    }

    public boolean isInvalidDate(int date) {
        return START_DATE > date || END_DATE < date;
    }

    public boolean isWeekend(int date) {
        return weekend.isWeekend(date);
    }

    public boolean isStarDay(int date) {
        return starDays.isStarDay(date);
    }

}
