[![Build](https://github.com/fintecy/kraken-client/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/fintecy/kraken-client/actions/workflows/gradle.yml)

# Kraken Client

Async client with CompletableFutures based on new HttpClient (java 11+)

## Dependency
https://mvnrepository.com/artifact/org.fintecy.md/kraken-client/1.0.0

### Gradle
```groovy
dependencies {
    implementation 'org.fintecy.md:kraken-client:1.0.0'
}
```

### Maven
```xml
<dependency>
    <groupId>org.fintecy.md</groupId>
    <artifactId>kraken-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage
### Simple client creation
```
krakenApi api = KrakenClient.api();
ExchangeRate latest = api.latest("BTC-USD").get();
```
### Complex client configuration
```
var client = krakenClient()
    .useClient(HttpClient.newBuilder()
        .followRedirects(HttpClient.Redirect.ALWAYS)
        .priority(10)
        .connectTimeout(Duration.ofMillis(500))
        .executor(Executors.newSingleThreadExecutor())
        .build())
    .with(CircuitBreaker.ofDefaults())
    .with(RateLimiter.smoothBuilder(Duration.ofMillis(100))
        .build())
    .with(RetryPolicy.ofDefaults())
    .with(Timeout.of(Duration.ofMillis(400)))
    .rootPath("https://api.kraken.com/0") -- just to use stub in tests
    .build();
```

## Dependencies
- Java 11+
- FailSafe
- Slf4j api
- Jackson (databind, datatype-jsr310)
- WireMock (tests)
- Junit5 (tests)

## Author
Anton Batiaev <anton@batiaev.com>
