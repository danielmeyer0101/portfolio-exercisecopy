package market;

/**
 * Represents a ticker with a symbol and a current price.
 * @author OSNB
 * @version 1.0
 */
public class Ticker {
    private final Symbol symbol;
    private double currentPrice;

    Ticker(Symbol symbol, double currentPrice) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
    }

    public Symbol getSymbol() {
        return symbol;
    }


    public double getCurrentPrice() {
        return currentPrice;
    }

    void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "symbol=" + symbol +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
