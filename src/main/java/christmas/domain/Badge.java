package christmas.domain;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),

    NOTHING("없음", 0);

    private String title;
    private final int requiredPrice;

    Badge(String title, int requiredPrice) {
        this.title = title;
        this.requiredPrice = requiredPrice;
    }

    public static Badge getBadge(int orderPrice) {
        return Arrays.stream(values())
                .filter(value -> orderPrice >= value.requiredPrice)
                .findFirst()
                .orElse(NOTHING);
    }
}
