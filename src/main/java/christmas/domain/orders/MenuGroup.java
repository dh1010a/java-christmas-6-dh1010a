package christmas.domain.orders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum MenuGroup {

    APPETIZER("애피타이저", Arrays.asList(Menu.MUSHROOM_SOUP, Menu.CAESAR_SALAD, Menu.TAPAS)),
    MAIN("메인", Arrays.asList(Menu.T_BONE_STEAK, Menu.BARBECUE_LIP, Menu.SEAFOOD_PASTA,
            Menu.CHRISTMAS_PASTA)),
    DESERT("디저트", Arrays.asList(Menu.CHOCO_CAKE, Menu.ICE_CREAM)),
    DRINK("음료", Arrays.asList(Menu.ZERO_COKE, Menu.RED_WINE, Menu.CHAMPAGNE)),
    EMPTY("없음", Collections.emptyList());

    private String title;
    private List<Menu> menus;

    MenuGroup(String title, List<Menu> menus) {
        this.title = title;
        this.menus = menus;
    }

    public static MenuGroup findByMenu(Menu menu) {
        return Arrays.stream(MenuGroup.values())
                .filter(menuGroup -> menuGroup.hasMenu(menu))
                .findAny()
                .orElse(EMPTY);
    }

    public static boolean isMainMenu(Menu menu) {
        MenuGroup menuGroup = findByMenu(menu);
        return menuGroup.title.equals(MAIN.getTitle());
    }

    public static boolean isDesertMenu(Menu menu) {
        MenuGroup menuGroup = findByMenu(menu);
        return menuGroup.title.equals(DESERT.getTitle());
    }

    public String getTitle() {
        return title;
    }

    private boolean hasMenu(Menu menu) {
        return menus.stream()
                .anyMatch(title -> title == menu);
    }

}
