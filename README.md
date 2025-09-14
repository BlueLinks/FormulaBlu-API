# FormulaBlu API

A comprehensive Formula 1 microservices API system built with Spring Boot and OpenAPI Specification (OAS) integration.

## 🏗️ Architecture

This project consists of multiple microservices, each with its own API and database, integrated through a centralized OAS specification system.

### Project Structure

```
FormulaBlu-API/
├── formulaBlu/                    # Main microservices
│   ├── drivers-api/               # Driver management API
│   ├── teams-api/                 # Team management API
│   ├── tracks-api/                # Track management API
│   ├── races-api/                 # Race management API
│   ├── seasons-api/               # Season management API
│   ├── events-api/                # Event management API
│   ├── results-api/               # Results management API
│   └── api-gateway/               # API Gateway
├── OAS/                          # Git submodule (FormulaBlu-OAS repo)
│   ├── models/                   # Shared data models
│   ├── apis/                     # API specifications
│   └── shared/                   # Common components
└── scripts/                      # Helper scripts
    └── update-oas.sh
```

## 🚀 OAS Integration

This project uses **OpenAPI Specification (OAS)** for model-driven development:

### How It Works

1. **Define Models** in OAS specifications (FormulaBlu-OAS repository)
2. **Gradle automatically** downloads OAS specs and generates Java models
3. **Use generated models** directly in your API implementations
4. **Build and deploy** with consistent, type-safe models

### OAS Workflow

```bash
# 1. Update OAS specifications in FormulaBlu-OAS repo
# 2. Build your API (Gradle handles everything automatically)
./gradlew :drivers-api:build

# Gradle automatically:
# - Updates OAS submodule
# - Generates Java models from OAS specs
# - Compiles everything including generated models
# - Builds the API successfully
```

## 🛠️ Development

### Prerequisites

-   Java 23
-   Gradle 8.12+
-   Docker
-   Git

### Getting Started

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/FormulaBlu-API.git
    cd FormulaBlu-API
    ```

2. **Initialize OAS submodule:**

    ```bash
    git submodule update --init --recursive
    ```

3. **Build all APIs:**
    ```bash
    cd formulaBlu
    ./gradlew build
    ```

### Available Gradle Tasks

```bash
# Build specific API (with OAS generation)
./gradlew :drivers-api:build

# Generate models only
./gradlew :drivers-api:openApiGenerate

# Update OAS submodule only
./gradlew :drivers-api:updateOasSpec

# Check OAS spec exists
./gradlew :drivers-api:checkOasSpec

# Build all APIs
./gradlew build

# Clean and rebuild
./gradlew clean build
```

## 📋 API Services

### Core APIs

-   **Drivers API** - Driver master data and career information
-   **Teams API** - Constructor information and team stats
-   **Tracks API** - Circuit information and venue details
-   **Races API** - Race information and scheduling
-   **Seasons API** - Championship years, rules, and regulations
-   **Events API** - Race weekends, sessions (Practice, Qualifying, Race)
-   **Results API** - Race results, qualifying results, lap times, points

### API Gateway

-   Routes requests to appropriate microservices
-   Service discovery and load balancing
-   Authentication and authorization
-   Rate limiting and monitoring

## 🐳 Docker Integration

### Building Docker Images

Each API service can be built as a Docker image:

```bash
# Build specific API
cd formulaBlu
./gradlew :drivers-api:bootJar
docker build -t formulablu/drivers-api:latest -f drivers-api/Dockerfile .

# Build all APIs
./gradlew build
for api in drivers-api teams-api tracks-api; do
    docker build -t formulablu/$api:latest -f $api/Dockerfile .
done
```

### Docker Compose

Run the entire system locally:

```bash
cd formulaBlu
docker-compose up -d
```

## ☸️ Kubernetes Deployment

### Prerequisites

-   Kubernetes cluster
-   kubectl configured
-   Docker images pushed to registry

### Deployment Process

1. **Build and push images:**

    ```bash
    # Build all APIs
    ./gradlew build

    # Build and push Docker images
    ./scripts/build-and-push-images.sh
    ```

2. **Deploy to Kubernetes:**

    ```bash
    # Deploy all services
    kubectl apply -f ../Kubernetes/

    # Or deploy specific service
    kubectl apply -f ../Kubernetes/modules/drivers-api/
    ```

### Kubernetes Structure

```
Kubernetes/
├── modules/
│   ├── drivers-api/
│   │   ├── drivers-api-deployment.yaml
│   │   └── drivers-api-service.yaml
│   ├── teams-api/
│   │   ├── teams-api-deployment.yaml
│   │   └── teams-api-service.yaml
│   └── ...
├── shared/
│   ├── namespace/
│   │   └── namespace.yaml
│   └── postgres/
│       ├── postgres-deployment.yaml
│       ├── postgres-service.yaml
│       ├── postgres-configmap.yaml
│       └── postgres-secret.yaml
└── scripts/
    ├── deploy-all.sh
    └── build-and-push-images.sh
```

## 🔧 Configuration

### Environment Variables

Each service can be configured via environment variables:

```yaml
# Example: drivers-api configuration
env:
    - name: SPRING_DATASOURCE_URL
      value: "jdbc:postgresql://postgres:5432/drivers_db"
    - name: SPRING_DATASOURCE_USERNAME
      valueFrom:
          secretKeyRef:
              name: postgres-secret
              key: username
    - name: SPRING_DATASOURCE_PASSWORD
      valueFrom:
          secretKeyRef:
              name: postgres-secret
              key: password
```

### Database Configuration

Each microservice has its own PostgreSQL database:

-   `drivers_db` - Driver data
-   `teams_db` - Team data
-   `tracks_db` - Track data
-   `races_db` - Race data
-   `seasons_db` - Season data
-   `events_db` - Event data
-   `results_db` - Results data

## 📊 Monitoring and Logging

### Health Checks

Each service exposes health check endpoints:

-   `GET /actuator/health` - Service health status
-   `GET /actuator/info` - Service information
-   `GET /actuator/metrics` - Service metrics

### Logging

Structured logging with correlation IDs for request tracing across services.

## 🚀 CI/CD

### GitHub Actions

The project includes GitHub Actions workflows for:

-   **OAS Updates** - Automatically updates OAS specifications
-   **API Builds** - Builds and tests all APIs
-   **Docker Builds** - Builds and pushes Docker images
-   **Kubernetes Deployment** - Deploys to Kubernetes clusters

### Workflow Triggers

-   **Push to main** - Triggers full build and test
-   **OAS changes** - Triggers model regeneration and API rebuilds
-   **Manual trigger** - Allows manual deployment

## 📚 Documentation

### API Documentation

-   **OpenAPI/Swagger** - Auto-generated from OAS specifications
-   **Interactive docs** - Available at `/swagger-ui.html` for each service
-   **API reference** - Available at `/v3/api-docs` for each service

### OAS Documentation

See [OAS-README.md](OAS-README.md) for detailed OAS integration documentation.

## 🤝 Contributing

1. **Fork the repository**
2. **Create a feature branch**
3. **Update OAS specifications** if adding new models
4. **Implement your changes**
5. **Test thoroughly**
6. **Submit a pull request**

### Development Guidelines

-   Follow the OAS-first approach for model definitions
-   Use generated models in your implementations
-   Maintain backward compatibility
-   Write comprehensive tests
-   Update documentation

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

For support and questions:

-   **Issues** - Create an issue in this repository
-   **Discussions** - Use GitHub Discussions for questions
-   **Documentation** - Check the OAS-README.md for OAS integration details

---

**FormulaBlu API** - Bringing Formula 1 data to life through microservices architecture and OpenAPI specifications.
