package menu;

/**
 * Enum representing the different options in the menu
 * @author OSNB
 * @version 1.0
 */
enum MenuOption {
    SHOW_MARKET_PRICES("1", "Show market prices"),
    SHOW_PORTFOLIO_STATISTICS("2", "Show portfolio"),
    SIMULATE_PRICE_CHANGE("3", "Simulate price change"),
    BUY_STOCK("4", "Buy stock"),
    SELL_STOCK("5", "Sell stock"),
    EXIT("0", "Exit");

    private final String option;
    private final String description;

    MenuOption(String option, String description) {
        this.option = option;
        this.description = description;
    }

    public String getOption() {
        return option;
    }

    public String getDescription() {
        return description;
    }

    public static MenuOption fromOption(String option) {
        for (MenuOption menuOption : MenuOption.values()) {
            if (menuOption.getOption().equals(option)) {
                return menuOption;
            }
        }
        return null;
    }
}

