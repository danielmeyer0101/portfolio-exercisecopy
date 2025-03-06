package menu;

import market.Market;
import market.Symbol;
import portfolio.Portfolio;

import java.util.Scanner;

/**
 * Represents the menu of the stock market application.
 * The menu allows the user to interact with the market and their portfolio.
 * @author OSNB
 * @version 1.0
 */
public class Menu {
    public static void showMenu(Portfolio portfolio, Scanner scanner) {
        while (true) {
            System.out.println("\n---- STOCK MARKET MENU ----");
            for (MenuOption option : MenuOption.values()) {
                System.out.println(option.getOption() + ": " + option.getDescription());
            }

            System.out.println("Choose an option: ");
            String choice = scanner.nextLine();

            MenuOption selectedOption = MenuOption.fromOption(choice);
            if (selectedOption == null) {
                System.out.println("Invalid choice. Please select a valid option.");
                continue;
            }

            switch (selectedOption) {
                case SHOW_MARKET_PRICES -> {
                    Market.getInstance().showMarketPrices();
                }
                case SHOW_PORTFOLIO_STATISTICS -> {
                    portfolio.showStatistics();
                }
                case SIMULATE_PRICE_CHANGE -> {
                    Market.getInstance().simulatePriceChange();
                }
                case BUY_STOCK -> {
                    Symbol symbol = promptForSymbol(scanner, "Enter stock symbol to buy: ");
                    int quantity = promptForQuantity(scanner, "Enter quantity: ");
                    portfolio.buyStock(symbol, quantity);
                }
                case SELL_STOCK -> {
                    Symbol symbol = promptForSymbol(scanner, "Enter stock symbol to sell: ");
                    portfolio.sellStock(symbol);
                }
                case EXIT -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
            }
        }
    }

    private static Symbol promptForSymbol(Scanner scanner, String msg) {
        while (true) {
            System.out.println(msg);
            String input = scanner.nextLine().trim().toUpperCase();
            // TODO: Tjek om input er valid og returner symbolet hvis det er
            return Symbol.valueOf(input);

//            System.out.println("Invalid stock symbol. Please try again.");
        }
    }

    private static int promptForQuantity(Scanner scanner, String msg) {
        while (true) {
            System.out.println(msg);

            if (scanner.hasNextInt()) {
                int quantity = scanner.nextInt();
                scanner.nextLine();

                // TODO: Returner quantity, kun hvis positivt
                return quantity;
            } else {
                scanner.nextLine();
            }

            System.out.println("Invalid quantity. Please enter a positive number.");
        }
    }
}
