package market;

import java.util.Random;

/**
 * This class represents the Market
 * and holds a number of tickers.
 * Its implemented using the Singleton design pattern,
 * to ensure that only one instance is created (ie. we
 * only have one market)
 * @author OSNB
 * @version 1.0
 */
public class Market {
    private final Ticker[] tickers;
    private static Market instance;
    private final Random random = new Random();

    private Market() {
        // Initialize market
        int noOfTickers = Symbol.values().length;
        this.tickers = new Ticker[noOfTickers];

        Random random = new Random(1);


        for (int i = 0; i < noOfTickers; i++) {
            double price = Math.floor(random.nextDouble(100,200) * 100) / 100;
            tickers[i] = new Ticker(Symbol.values()[i],price);
        }
    }

    public static Market getInstance() {
        if (instance == null) {
            instance = new Market();
        }
        return instance;
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

    public Ticker getTicker(Symbol symbol) {
        for (Ticker value : tickers) {
            if (value.getSymbol() == symbol) {
               return value;
            }
        }
        throw new IllegalArgumentException("Illegal ticker symbol");
    }


    public void simulatePriceChange() {
        for (Ticker ticker : tickers) {
            double rateOfReturn = random.nextGaussian(0, 0.015);
            double newPrice = ticker.getCurrentPrice() * (1 + rateOfReturn);
            newPrice = Math.max(0.0, Math.floor(newPrice * 100) / 100);
            ticker.setCurrentPrice(newPrice);
        }
    }

    public void showMarketPrices() {
        System.out.println("-------- MARKET --------");
        System.out.printf("%-10s %12s%n", "Symbol", "Price");
        System.out.println("------------------------");
        for (Ticker ticker : tickers) {
            System.out.printf("%-10s %12s%n", ticker.getSymbol(), ticker.getCurrentPrice());
        }
        System.out.println("------------------------");
    }
}
