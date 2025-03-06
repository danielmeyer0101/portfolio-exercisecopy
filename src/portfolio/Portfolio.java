package portfolio;

import market.Market;
import market.Symbol;

import java.util.Arrays;

/**
 * Represents a portfolio with a number of holdings and a cash holding.
 * @author OSNB
 * @version 1.0
 */
public class Portfolio {
    private PortfolioItem[] portfolioItems;
    private int noOfHoldings = 0;
    private double cashHolding;
    private final double initialBalance;
    private final Market market;

    public Portfolio(double initialBalance) {
        this.portfolioItems = new PortfolioItem[2];
        this.cashHolding = initialBalance;
        this.initialBalance = initialBalance;
        this.market = Market.getInstance();
    }

    public void buyStock(Symbol symbol, int quantity) {
        if (quantity < 1 ) {
            System.out.println("Not valid quantity");
            return;
        }
        if (portfolioItems.length <= noOfHoldings) {
            portfolioItems = Arrays.copyOf(portfolioItems, portfolioItems.length*2);
        }

        double amount = market.getPrice(symbol) * quantity;
        if (amount > cashHolding) {
            System.out.println("Insufficient balance");
            return;
        }

        if (isCurrentlyHolding(symbol)) {
            System.out.println("You already own this stock");
            return;
        }

        PortfolioItem portfolioItem = new PortfolioItem(symbol,quantity);
        portfolioItems[noOfHoldings] = portfolioItem;
        noOfHoldings++;
        cashHolding -= amount;
    }

    private boolean isCurrentlyHolding(Symbol symbol) {
        for (int i = 0; i < noOfHoldings; i++) {
            if (portfolioItems[i].getTicker().getSymbol() == symbol) {
                return true;
            }
        }
        return false;
    }

    public void sellStock(Symbol symbol) {
        if (noOfHoldings == 0) {
            return;
        }
        for (int i = 0; i <= noOfHoldings; i++) {
            if (portfolioItems[i].getTicker().getSymbol() == symbol) {
                double currentAmount = market.getPrice(symbol)* portfolioItems[i].getQuantity();
                portfolioItems[i] = portfolioItems[noOfHoldings];
                portfolioItems[noOfHoldings] = null;
                noOfHoldings--;
                cashHolding += currentAmount;
                return;
            }
        }
    }

    public double getMarketValue() {
        double marketValue = cashHolding;
        for (int i = 0; i < noOfHoldings; i++) {
            marketValue += portfolioItems[i].getPositionAmount();
        }
        return marketValue;
    }

    public double getPnL() {
        return initialBalance - getMarketValue();
    }

    public void showStatistics() {
        System.out.println("------------ STATISTICS -----------");
        System.out.printf("%-10s %-10s %12s%n", "Symbol", "Quantity", "Market Value");
        System.out.println("-----------------------------------");

        for (int i = 0; i < noOfHoldings; i++) {
            PortfolioItem portfolioItem = portfolioItems[i];
            Symbol symbol = portfolioItem.getTicker().getSymbol();
            int quantity = portfolioItem.getQuantity();
            double positionValue = portfolioItem.getPositionAmount();

            System.out.printf("%-10s %-10d %12.2f%n", symbol, quantity, positionValue);
        }

        System.out.printf("%-10s %-10d %12.2f%n", "CASH", 1, cashHolding);

        System.out.println("-----------------------------------");

        System.out.printf("%-10s %-10s %12.2f%n", "TOTAL", "", getMarketValue());
    }
}
