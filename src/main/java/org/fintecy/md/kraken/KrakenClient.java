package org.fintecy.md.kraken;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.failsafe.Policy;
import org.fintecy.md.kraken.model.*;
import org.fintecy.md.kraken.model.dto.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.net.URI.create;
import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

public class KrakenClient implements KrakenApi {
    private final String rootPath;
    private final HttpClient client;
    private final ObjectMapper mapper;
    private final List<Policy<Object>> policies;

    protected KrakenClient(String rootPath, ObjectMapper mapper, HttpClient httpClient, List<Policy<Object>> policies) {
        this.client = checkRequired(httpClient, "Http client required for Kraken client");
        this.mapper = checkRequired(mapper, "object mapper is required for serialization");
        this.rootPath = checkRequired(rootPath, "root path cannot be empty");
        this.policies = ofNullable(policies).orElse(List.of());
    }

    public static KrakenApi api() {
        return new KrakenClientBuilder().build();
    }

    public static KrakenClientBuilder krakenClient() {
        return new KrakenClientBuilder();
    }

    public static double checkRequired(double v, String msg) {
        return (v == 0 ? Optional.<Double>empty() : Optional.of(v))
                .orElseThrow(() -> new IllegalArgumentException(msg));
    }

    public static <T> T checkRequired(T v, String msg) {
        return ofNullable(v)
                .orElseThrow(() -> new IllegalArgumentException(msg));
    }

    @Override
    public CompletableFuture<List<Candle>> candles(ProductCode pair, Interval interval, Optional<Instant> since) {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/public/OHLC?pair=" + pair.getCode()
                        + "&interval=" + interval.getMin()
                        + since.map(instant -> "&since=" + instant.toEpochMilli()).orElse("")))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, OHLCResponse.class))
                .thenApply(OHLCResponse::getDataOrThrow);
    }

    @Override
    public CompletableFuture<List<ExchangeRate>> ticker(ProductCode... pairs) {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/public/Ticker?pair=" + Stream.of(pairs)
                        .map(ProductCode::getCode)
                        .collect(joining(","))))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, TickerResponse.class))
                .thenApply(TickerResponse::getDataOrThrow);
    }

    @Override
    public CompletableFuture<List<Product>> products(ProductDetailsLevel level, ProductCode... codes) {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/public/AssetPairs"
                        + (level.equals(ProductDetailsLevel.INFO) ? "" : "?" + level.name().toLowerCase())
                        + (codes.length <= 0 ? ""
                        : (level.equals(ProductDetailsLevel.INFO) ? "?" : "&")
                        + stream(codes).map(ProductCode::getCode).collect(joining(",")))))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, ProductsResponse.class))
                .thenApply(ProductsResponse::getDataOrThrow);
    }

    @Override
    public CompletableFuture<List<Asset>> assets() {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/public/Assets"))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, AssetsResponse.class))
                .thenApply(AssetsResponse::getDataOrThrow);
    }

    @Override
    public CompletableFuture<SystemStatus> systemStatus() {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/public/SystemStatus"))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, SystemStatus.class));
    }

    @Override
    public CompletableFuture<Instant> serverTime() {
        var httpRequest = HttpRequest.newBuilder()
                .uri(create(rootPath + "/public/Time"))
                .build();

        return client.sendAsync(httpRequest, ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> parseResponse(body, ServerTimeResponse.class))
                .thenApply(MicroType::getValue);
    }

    private <T> T parseResponse(String body, Class<T> tClass) {
        try {
            return mapper.readValue(body, tClass);
        } catch (IOException e) {
            throw new IllegalStateException("Can parse response", e);
        }
    }
}
