package org.fintecy.md.kraken.serialization;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.InstantKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import org.fintecy.md.kraken.model.*;
import org.fintecy.md.kraken.model.dto.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;

public class KrakenModule extends SimpleModule {
    public static final String GROUP_ID = "org.fintecy.md";
    public static final String ARTIFACT_ID = "kraken-client";
    public static final Version VERSION = new Version(1, 0, 2, "SNAPSHOT",
            GROUP_ID, ARTIFACT_ID);

    public KrakenModule() {
        super(KrakenModule.class.getSimpleName(), VERSION, Map.of(
                Instant.class, InstantDeserializer.INSTANT,
                LocalDate.class, LocalDateDeserializer.INSTANCE));

        addDeserializer(ServerTimeResponse.class, ServerTimeResponseDeserializer.INSTANCE);
        addDeserializer(SystemStatus.class, SystemStatusDeserializer.INSTANCE);
        addDeserializer(Asset.class, AssetDeserializer.INSTANCE);
        addDeserializer(AssetsResponse.class, AssetsResponseDeserializer.INSTANCE);
        addDeserializer(Product.class, ProductDeserializer.INSTANCE);
        addDeserializer(ProductsResponse.class, ProductsResponseDeserializer.INSTANCE);
        addDeserializer(ExchangeRate.class, ExchangeRateDeserializer.INSTANCE);
        addDeserializer(TickerResponse.class, TickerResponseDeserializer.INSTANCE);
        addDeserializer(Candle.class, CandleDeserializer.INSTANCE);
        addDeserializer(OHLCResponse.class, OHLCResponseDeserializer.INSTANCE);

        addKeyDeserializer(LocalDate.class, LocalDateKeyDeserializer.INSTANCE);
        addKeyDeserializer(Instant.class, InstantKeyDeserializer.INSTANCE);
    }
}
