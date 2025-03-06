package projectIdeas.stock.market;

class Ticker {
    private Symbol symbol;
    private double currentPrice;

    Ticker(Symbol symbol, double currentPrice) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
    }

    Symbol getSymbol() {
        return symbol;
    }

    void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    double getCurrentPrice() {
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
