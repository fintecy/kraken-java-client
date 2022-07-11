package org.fintecy.md.kraken.serialization;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.InstantKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import org.fintecy.md.kraken.model.Asset;
import org.fintecy.md.kraken.model.ExchangeRate;
import org.fintecy.md.kraken.model.Product;
import org.fintecy.md.kraken.model.SystemStatus;
import org.fintecy.md.kraken.model.dto.AssetsResponse;
import org.fintecy.md.kraken.model.dto.ProductsResponse;
import org.fintecy.md.kraken.model.dto.ServerTimeResponse;
import org.fintecy.md.kraken.model.dto.TickerResponse;

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

        addKeyDeserializer(LocalDate.class, LocalDateKeyDeserializer.INSTANCE);
        addKeyDeserializer(Instant.class, InstantKeyDeserializer.INSTANCE);
    }
}
