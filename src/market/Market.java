package projectIdeas.stock.market;

import java.util.Random;

public class Market {
    private Ticker[] tickers;

    public Market() {
        // Initialize market
        int noOfTickers = Symbol.values().length;
        this.tickers = new Ticker[noOfTickers];

        Random random = new Random(1);


        for (int i = 0; i < noOfTickers; i++) {
            tickers[i] = new Ticker(Symbol.values()[i],random.nextDouble(100,200));
        }
    }


    public double getPrice(Symbol symbol) {
        Ticker ticker = null;
        for (Ticker value : tickers) {
            if (value.getSymbol() == symbol) {
                ticker = value;
            }
        }
        if (ticker == null) {
            return 0;
        }

        return ticker.getCurrentPrice();
    }
}
