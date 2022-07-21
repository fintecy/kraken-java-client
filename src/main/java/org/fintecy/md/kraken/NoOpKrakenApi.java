package org.fintecy.md.kraken;

import org.fintecy.md.kraken.model.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Available for testing purposes
 */
public class NoOpKrakenApi implements KrakenApi {
    @Override
    public CompletableFuture<List<Spread>> recentSpreads(ProductCode pair, Optional<Instant> since) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<List<Trade>> recentTrades(ProductCode pair, Optional<Instant> since) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<List<OrderBook>> orderBook(ProductCode pair, int count) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<List<Candle>> candles(ProductCode pair, Interval interval, Optional<Instant> since) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<List<ExchangeRate>> ticker(ProductCode... pairs) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<List<Product>> products(ProductDetailsLevel level, ProductCode... codes) {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<List<Asset>> assets() {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<SystemStatus> systemStatus() {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public CompletableFuture<Instant> serverTime() {
        return CompletableFuture.completedFuture(Instant.now());
    }
}
