package portfolio;

import market.Market;
import market.Symbol;
import market.Ticker;

/**
 * Represents a portfolio item with a ticker, purchase price and quantity.
 * @author OSNB
 * @version 1.0
 */
class PortfolioItem {
    private final Ticker ticker;
    private final double purchasePrice;
    private final int quantity;

    PortfolioItem(Symbol symbol, int quantity) {
        this.ticker = Market.getInstance().getTicker(symbol);
        this.purchasePrice = this.ticker.getCurrentPrice();
        this.quantity = quantity;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getPositionAmount() {
        return quantity * ticker.getCurrentPrice();
    }


    public int getQuantity() {
        return quantity;
    }

}
