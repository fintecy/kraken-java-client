package org.fintecy.md.kraken;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.failsafe.Policy;
import org.fintecy.md.kraken.serialization.KrakenModule;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

class KrakenClientBuilder {
    private ObjectMapper mapper = new ObjectMapper().registerModule(new KrakenModule());
    private HttpClient client = HttpClient.newHttpClient();
    private List<Policy<Object>> policies = new ArrayList<>();
    private String rootPath = KrakenApi.ROOT_PATH;

    public KrakenClientBuilder useClient(HttpClient client) {
        this.client = client;
        return this;
    }

    public KrakenClientBuilder mapper(ObjectMapper mapper) {
        this.mapper = mapper.registerModule(new KrakenModule());
        return this;
    }

    public KrakenClientBuilder rootPath(String rootPath) {
        this.rootPath = rootPath;
        return this;
    }

    public KrakenClientBuilder with(Policy<Object> policy) {
        this.policies.add(policy);
        return this;
    }

    public KrakenApi build() {
        return new KrakenClient(rootPath, mapper, client, policies);
    }
}
