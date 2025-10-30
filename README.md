

```markdown
# Currency Fetch Service

A Spring Boot microservice that fetches foreign exchange rates from a public API and exposes them for internal consumers and tooling. Designed for easy local development (Maven wrapper, Docker Compose) and production-ready containerization.

## ✨ Features

- Fetches daily (or scheduled) currency exchange rates from a public source  
- REST endpoints to retrieve latest and historical rates  
- Pluggable upstream provider (default examples included)  
- Dockerized local stack using `docker-compose.yml` (app + DB or cache)  
- Health/readiness endpoints for orchestration platforms

Default public source example: **Exchange API** by @fawazahmed0 (no rate limits, many currencies).  
URL structure:  
```

[https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/{endpoint}.json](https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/{endpoint}.json)

```

## 🧱 Tech Stack

- **Java 17+**  
- **Spring Boot** (REST, Scheduler, Actuator)  
- **Maven** (with wrapper)  
- **Docker Compose** for local orchestration  
- (Optional) **PostgreSQL/Redis** if persistence or caching is enabled

## 📦 Project Layout

```

currency-fetch-service/
├─ .mvn/                    # Maven wrapper support
├─ src/
│  ├─ main/
│  │  ├─ java/...           # Spring Boot application & code
│  │  └─ resources/         # application.yml / logback, etc.
│  └─ test/                 # Unit & integration tests
├─ pom.xml                  # Maven project descriptor
├─ docker-compose.yml       # Local stack (service + dependencies)
├─ mvnw, mvnw.cmd           # Maven wrappers
└─ .gitignore, .gitattributes

````

## 🚀 Getting Started

### Prerequisites

- JDK 17+  
- Docker & Docker Compose  
- (Optional) Postman/cURL

### Clone

```bash
git clone https://github.com/Diwakarboya/currency-fetch-service.git
cd currency-fetch-service
````

### Run (Maven)

```bash
./mvnw spring-boot:run
# or
./mvnw clean package
java -jar target/currency-fetch-service-*.jar
```

### Run (Docker Compose)

```bash
docker compose up --build
```

The service will be available at:
`http://localhost:8080`

## ⚙️ Configuration

Example `application.yml`:

```yaml
server:
  port: 8080

currency:
  upstream:
    baseUrl: https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1
    fallbackBaseUrl: https://latest.currency-api.pages.dev/v1
  scheduler:
    enabled: true
    cron: "0 0 * * * *"   # every hour

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/currency
    username: currency_user
    password: currency_pass
  jpa:
    hibernate:
      ddl-auto: update
```

Environment variable equivalents:

* `CURRENCY_UPSTREAM_BASEURL`
* `CURRENCY_UPSTREAM_FALLBACKBASEURL`
* `CURRENCY_SCHEDULER_ENABLED`
* `CURRENCY_SCHEDULER_CRON`
* `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`

## 📡 REST API

### Get latest rates

```
GET /api/v1/rates?base=USD&symbols=EUR,INR,GBP
```

**Response**:

```json
{
  "base": "USD",
  "asOf": "2025-10-30",
  "rates": { "EUR": 0.94, "INR": 84.1, "GBP": 0.78 },
  "source": "exchange-api"
}
```

### Get historical rates

```
GET /api/v1/rates/{date}?base=USD&symbols=INR
# Example: /api/v1/rates/2025-10-01?base=USD&symbols=INR
```

### Health

```
GET /actuator/health
GET /actuator/info
```

## ⏱️ Scheduling

If enabled, the service periodically fetches fresh rates according to the cron expression.

## 🧪 Testing

```bash
./mvnw test
```

## 🐳 Docker

Build image:

```bash
./mvnw -DskipTests clean package
docker build -t currency-fetch-service:local .
```

Run container:

```bash
docker run -p 8080:8080 --env-file .env currency-fetch-service:local
```

## 📈 Roadmap

* Add Swagger/OpenAPI documentation
* Add caching layer for frequent lookups
* Add Prometheus metrics
* Add circuit breaker (Resilience4j)

## 🤝 Contributing

1. Fork this repo
2. Create a feature branch
3. Commit with tests
4. Run `./mvnw verify`
5. Open a PR

## 📜 License

This project is licensed under the MIT License.
Upstream API sources are subject to their own licenses.

```

---

```
