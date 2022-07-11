package org.fintecy.md.kraken;

import org.fintecy.md.kraken.model.*;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author batiaev
 * @see <a href="https://docs.kraken.com/rest/#tag/Market-Data">docs</a>
 */
public interface KrakenApi {
    String ROOT_PATH = "https://api.kraken.com/0/";

    /**
     * @return ticker info
     * @see <a href="https://api.kraken.com/0/public/Ticker?pair=XBTUSD">test request</a>
     * @see <a href="https://docs.kraken.com/rest/#operation/getTickerInformation">api doc</a>
     */
    CompletableFuture<List<ExchangeRate>> ticker(ProductCode... pairs);

    /**
     * @return supported assets
     * @see <a href="https://api.kraken.com/0/public/AssetPairs?pair=XXBTZUSD,XETHXXBT">test request</a>
     * @see <a href="https://docs.kraken.com/rest/#operation/getTradableAssetPairs">api doc</a>
     */
    default CompletableFuture<List<Product>> products(ProductCode... codes) {
        return products(ProductDetailsLevel.INFO, codes);
    }

    CompletableFuture<List<Product>> products(ProductDetailsLevel level, ProductCode... codes);

    /**
     * @return supported assets
     * @see <a href="https://api.kraken.com/0/public/Assets">test request</a>
     */
    CompletableFuture<List<Asset>> assets();

    /**
     * @return system status or error
     * @see <a href="https://api.kraken.com/0/public/SystemStatus">test request</a>
     */
    CompletableFuture<SystemStatus> systemStatus();

    /**
     * @return Server time
     * @see <a href="https://docs.kraken.com/rest/#operation/getServerTime">test request</a>
     */
    CompletableFuture<Instant> serverTime();
}
