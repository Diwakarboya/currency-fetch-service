# Docker Image Build Instructions

## Method 1: Build Docker Image Manually

### Build the Docker image:
```bash
docker build -t currency-fetch-service:latest .
```

### Build with a specific tag/version:
```bash
docker build -t currency-fetch-service:1.0.0 .
```

### Verify the image was created:
```bash
docker images | grep currency-fetch-service
```

### Run the Docker image (standalone):
```bash
docker run -d \
  --name currency-fetch-service \
  -p 8081:8081 \
  -e KAFKA_BOOTSTRAP_SERVERS=localhost:9092 \
  currency-fetch-service:latest
```

**Note:** For standalone run, make sure Kafka is running separately and accessible at the specified address.

---

## Method 2: Build and Run using Docker Compose (Recommended)

### Build and start all services (including Kafka):
```bash
docker-compose up --build
```

### Build without starting:
```bash
docker-compose build
```

### Build a specific service:
```bash
docker-compose build currency-fetch-service
```

### Start services (after building):
```bash
docker-compose up -d
```

### View logs:
```bash
docker-compose logs -f currency-fetch-service
```

### Stop all services:
```bash
docker-compose down
```

---

## Method 3: Save Docker Image to File

### Save the image to a tar file:
```bash
docker save -o currency-fetch-service.tar currency-fetch-service:latest
```

### Load the image from a tar file:
```bash
docker load -i currency-fetch-service.tar
```

---

## Method 4: Push to Docker Registry

### Tag for Docker Hub:
```bash
docker tag currency-fetch-service:latest your-username/currency-fetch-service:latest
docker push your-username/currency-fetch-service:latest
```

### Tag for private registry:
```bash
docker tag currency-fetch-service:latest registry.example.com/currency-fetch-service:latest
docker push registry.example.com/currency-fetch-service:latest
```

---

## Verify Image Details

### Inspect the image:
```bash
docker inspect currency-fetch-service:latest
```

### Check image size:
```bash
docker images currency-fetch-service
```

### View image history:
```bash
docker history currency-fetch-service:latest
```

---

## Troubleshooting

### If build fails, check:
1. Ensure Docker is running: `docker ps`
2. Check Dockerfile syntax
3. Verify Maven build works locally: `mvn clean package`
4. Check available disk space: `docker system df`

### Clean up:
```bash
# Remove unused images
docker image prune

# Remove all stopped containers
docker container prune

# Remove build cache
docker builder prune
```
