package org.fintecy.md.kraken;

import org.fintecy.md.kraken.model.ProductCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.concurrent.ExecutionException;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;

class NoOpKrakenApiTest {
    private KrakenApi noOpKrakenApi;

    @BeforeEach
    void setUp() {
        noOpKrakenApi = new NoOpKrakenApi();
    }

    @Test
    void should_throw_exception_for_ticker() {
        assertThrows(IllegalStateException.class, () -> noOpKrakenApi.ticker(ProductCode.product("XBTUSD")).get());
    }

    @Test
    void should_throw_exception_for_products() {
        assertThrows(IllegalStateException.class, () -> noOpKrakenApi.products().get());
    }

    @Test
    void should_throw_exception_for_assets() {
        assertThrows(IllegalStateException.class, () -> noOpKrakenApi.assets().get());
    }

    @Test
    void should_throw_exception_for_system_status() {
        assertThrows(IllegalStateException.class, () -> noOpKrakenApi.systemStatus().get());
    }

    @Test
    void should_return_current_server_time() throws ExecutionException, InterruptedException {
        //when
        var instantCompletableFuture = noOpKrakenApi.serverTime();
        //then
        assertTrue(instantCompletableFuture.isDone());
        assertEquals(instantCompletableFuture.get().truncatedTo(SECONDS), Instant.now().truncatedTo(SECONDS));
    }
}
