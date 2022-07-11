package org.fintecy.md.kraken;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.fintecy.md.kraken.model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.time.Instant.ofEpochSecond;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.fintecy.md.kraken.KrakenClient.krakenClient;
import static org.fintecy.md.kraken.model.ProductCode.product;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest(httpPort = 7777)
class KrakenClientTest {

    @Test
    void should_return_candle() throws ExecutionException, InterruptedException {
        //given
        var product = product("XXBTZUSD");
        stubFor(get("/public/OHLC?pair=" + product.getCode() + "&interval=1")
                .willReturn(aResponse()
                        .withBodyFile("candle.json")));

        var expected = List.of(
                new Candle(product, Instant.parse("2021-03-25T08:59:00Z"),
                        new BigDecimal("52591.9"),//open
                        new BigDecimal("52599.9"),//high
                        new BigDecimal("52591.8"),//low
                        new BigDecimal("52599.9"),//close
                        new BigDecimal("52599.1"),//vwap
                        new BigDecimal("0.11091626"),//volume
                        5 //count
                ),
                new Candle(product, Instant.parse("2021-03-25T09:00:00Z"),
                        new BigDecimal("52600.0"),//open
                        new BigDecimal("52674.9"),//high
                        new BigDecimal("52599.9"),//low
                        new BigDecimal("52665.2"),//close
                        new BigDecimal("52643.3"),//vwap
                        new BigDecimal("2.49035996"),//volume
                        30 //count
                ),
                new Candle(product, Instant.parse("2021-03-25T09:01:00Z"),
                        new BigDecimal("52677.7"),//open
                        new BigDecimal("52686.4"),//high
                        new BigDecimal("52602.1"),//low
                        new BigDecimal("52609.5"),//close
                        new BigDecimal("52634.5"),//vwap
                        new BigDecimal("1.25810675"),//volume
                        20 //count
                )
        );
        //when
        var actual = krakenClient()
                .rootPath("http://localhost:7777")
                .build()
                .candles(product)
                .get();
        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_ticker() throws ExecutionException, InterruptedException {
        //given
        var product = product("XXBTZUSD");
        stubFor(get("/public/Ticker?pair=" + product.getCode())
                .willReturn(aResponse()
                        .withBodyFile("ticker.json")));

        var expected = List.of(
                new ExchangeRate(product, Instant.now().truncatedTo(SECONDS),
                        new PriceLevel(new BigDecimal("52609.60000"), new BigDecimal("1.000"), 1),
                        new PriceLevel(new BigDecimal("52609.50000"), new BigDecimal("1.000"), 1),
                        new BigDecimal("52641.10000"),
                        new BigDecimal("1920.83610601"),
                        new BigDecimal("52389.94668"),
                        23329,
                        new BigDecimal("51513.90000"),
                        new BigDecimal("53219.90000"),
                        new BigDecimal("52280.40000")
                )
        );
        //when
        var actual = krakenClient()
                .rootPath("http://localhost:7777")
                .build()
                .ticker(product)
                .get();
        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_products() throws ExecutionException, InterruptedException {
        stubFor(get("/public/AssetPairs")
                .willReturn(aResponse()
                        .withBodyFile("products.json")));

        var expected = List.of(
                new Product("XETHXXBT", "ETHXBT", "ETH/XBT", "currency", "XETH",
                        "currency", "XXBT", "unit", 5, 8, 1,
                        new int[]{2, 3, 4, 5}, new int[]{2, 3, 4, 5},
                        Map.of(0L, 0.26, 50000L, 0.24, 100000L, 0.22, 250000L,
                                0.2, 500000L, 0.18, 1000000L, 0.16, 2500000L, 0.14,
                                5000000L, 0.12, 10000000L, 0.1),
                        Map.of(0L, 0.16, 50000L, 0.14, 100000L, 0.12, 250000L,
                                0.1, 500000L, 0.08, 1000000L, 0.06, 2500000L, 0.04,
                                5000000L, 0.02, 10000000L, 0.),
                        "ZUSD", 80, 40, new BigDecimal("0.005")),
                new Product("XXBTZUSD", "XBTUSD", "XBT/USD", "currency", "XXBT",
                        "currency", "ZUSD", "unit", 1, 8, 1,
                        new int[]{2, 3, 4, 5}, new int[]{2, 3, 4, 5},
                        Map.of(0L, 0.26, 50000L, 0.24, 100000L, 0.22, 250000L,
                                0.2, 500000L, 0.18, 1000000L, 0.16, 2500000L, 0.14,
                                5000000L, 0.12, 10000000L, 0.1),
                        Map.of(0L, 0.16, 50000L, 0.14, 100000L, 0.12, 250000L,
                                0.1, 500000L, 0.08, 1000000L, 0.06, 2500000L, 0.04,
                                5000000L, 0.02, 10000000L, 0.),
                        "ZUSD",
                        80, 40, new BigDecimal("0.0002"))
        );
        var actual = krakenClient()
                .rootPath("http://localhost:7777")
                .build()
                .products()
                .get();
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i), i + " element " + actual.get(i) + " not matched with expected " + expected.get(i));
        }
        assertEquals(expected, actual);
    }

    @Test
    void should_return_assets() throws ExecutionException, InterruptedException {
        stubFor(get("/public/Assets")
                .willReturn(aResponse()
                        .withBodyFile("assets.json")));

        var expected = List.of(
                new Asset("XXBT", "XBT", "currency", 10, 5),
                new Asset("ZEUR", "EUR", "currency", 4, 2),
                new Asset("ZUSD", "USD", "currency", 4, 2)
        );
        var actual = krakenClient()
                .rootPath("http://localhost:7777")
                .build()
                .assets()
                .get();
        assertEquals(expected, actual);
    }

    @Test
    void should_return_system_status() throws ExecutionException, InterruptedException {
        stubFor(get("/public/SystemStatus")
                .willReturn(aResponse()
                        .withBodyFile("systemStatus.json")));

        var expected = new SystemStatus("online", Instant.parse("2021-03-21T15:33:02Z"));
        var actual = krakenClient()
                .rootPath("http://localhost:7777")
                .build()
                .systemStatus()
                .get();
        assertEquals(expected, actual);
    }

    @Test
    void should_return_server_time() throws ExecutionException, InterruptedException {
        stubFor(get("/public/Time")
                .willReturn(aResponse()
                        .withBodyFile("serverTime.json")));
        var expected = ofEpochSecond(1616336594L);
        var actual = krakenClient()
                .rootPath("http://localhost:7777")
                .build()
                .serverTime()
                .get();
        assertEquals(expected, actual);
    }
}
