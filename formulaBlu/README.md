# Formula1 Microservices API

A comprehensive Formula1 API built with Spring Boot microservices architecture, featuring separate services for races, drivers, teams, and tracks data.

## Architecture Overview

This project consists of the following microservices:

-   **Eureka Server** (Port 8761) - Service discovery and registration
-   **API Gateway** (Port 8080) - Single entry point for all API requests
-   **Races API** (Port 8081) - Manages race data and results
-   **Drivers API** (Port 8082) - Manages driver information
-   **Teams API** (Port 8083) - Manages team information
-   **Tracks API** (Port 8084) - Manages track/circuit information

Each service has its own PostgreSQL database for data isolation.

## Technology Stack

-   **Java 23**
-   **Spring Boot 3.4.3**
-   **Spring Cloud 2024.0.0**
-   **Spring Data JPA**
-   **PostgreSQL**
-   **Docker & Docker Compose**
-   **Gradle Multi-Module Project**

## Project Structure

```
formulaBlu/
├── eureka-server/          # Service discovery
│   └── Dockerfile         # Eureka server container
├── api-gateway/            # API Gateway
│   └── Dockerfile         # API Gateway container
├── races-api/              # Races API microservice
│   └── Dockerfile         # Races API container
├── drivers-api/            # Drivers API microservice
│   └── Dockerfile         # Drivers API container
├── teams-api/              # Teams API microservice
│   └── Dockerfile         # Teams API container
├── tracks-api/             # Tracks API microservice
│   └── Dockerfile         # Tracks API container
├── docker-compose.yml      # Local development setup
└── build-all.sh           # Build script
```

## Quick Start

### Prerequisites

-   Java 23
-   Docker & Docker Compose
-   Gradle (or use the included wrapper)

### Running with Docker Compose

1. **Build all services:**

    ```bash
    ./gradlew build
    ```

2. **Start all services:**

    ```bash
    docker-compose up -d
    ```

3. **Access the services:**
    - Eureka Dashboard: http://localhost:8761
    - API Gateway: http://localhost:8080
    - Races API: http://localhost:8081
    - Drivers API: http://localhost:8082
    - Teams API: http://localhost:8083
    - Tracks API: http://localhost:8084

### Running Locally (Development)

1. **Start PostgreSQL databases:**

    ```bash
    docker-compose up -d postgres-races postgres-drivers postgres-teams postgres-tracks
    ```

2. **Start Eureka Server:**

    ```bash
    cd eureka-server
    ./gradlew bootRun
    ```

3. **Start API Gateway:**

    ```bash
    cd api-gateway
    ./gradlew bootRun
    ```

4. **Start individual services:**
    ```bash
    # In separate terminals
    cd races-api && ./gradlew bootRun
    cd drivers-api && ./gradlew bootRun
    cd teams-api && ./gradlew bootRun
    cd tracks-api && ./gradlew bootRun
    ```

## API Endpoints

All API requests should go through the API Gateway at `http://localhost:8080`:

### Races API (`/api/races`)

-   `GET /api/races` - Get all races
-   `GET /api/races/{id}` - Get race by ID
-   `POST /api/races` - Create new race
-   `PUT /api/races/{id}` - Update race
-   `DELETE /api/races/{id}` - Delete race

### Drivers API (`/api/drivers`)

-   `GET /api/drivers` - Get all drivers
-   `GET /api/drivers/{id}` - Get driver by ID
-   `GET /api/drivers/nationality/{nationality}` - Get drivers by nationality
-   `GET /api/drivers/team/{teamId}` - Get drivers by team
-   `GET /api/drivers/search?name={name}` - Search drivers by name
-   `POST /api/drivers` - Create new driver
-   `PUT /api/drivers/{id}` - Update driver
-   `DELETE /api/drivers/{id}` - Delete driver

### Teams API (`/api/teams`)

-   `GET /api/teams` - Get all teams
-   `GET /api/teams/{id}` - Get team by ID
-   `GET /api/teams/nationality/{nationality}` - Get teams by nationality
-   `GET /api/teams/search?name={name}` - Search teams by name
-   `GET /api/teams/founded/{year}` - Get teams by founded year
-   `POST /api/teams` - Create new team
-   `PUT /api/teams/{id}` - Update team
-   `DELETE /api/teams/{id}` - Delete team

### Tracks API (`/api/tracks`)

-   `GET /api/tracks` - Get all tracks
-   `GET /api/tracks/{id}` - Get track by ID
-   `GET /api/tracks/country/{country}` - Get tracks by country
-   `GET /api/tracks/search?name={name}` - Search tracks by name
-   `POST /api/tracks` - Create new track
-   `PUT /api/tracks/{id}` - Update track
-   `DELETE /api/tracks/{id}` - Delete track

## Database Schema

Each service has its own PostgreSQL database:

-   **formula1_races** - Race data
-   **formula1_drivers** - Driver data
-   **formula1_teams** - Team data
-   **formula1_tracks** - Track data

## Development

### Building the Project

```bash
# Build all modules
./gradlew build

# Build specific module
./gradlew :races-api:build
./gradlew :drivers-api:build
./gradlew :teams-api:build
./gradlew :tracks-api:build
```

### Running Tests

```bash
# Run all tests
./gradlew test

# Run tests for specific module
./gradlew :races-api:test
```

## Configuration

Each service can be configured via `application.yml` files. Key configuration points:

-   **Database connections** - Update datasource URLs for different environments
-   **Service ports** - Each service runs on a different port
-   **Eureka registration** - All services register with Eureka server

## Monitoring

-   **Eureka Dashboard**: http://localhost:8761 - View registered services
-   **Service Health**: Each service exposes health endpoints at `/actuator/health`

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## License

This project is licensed under the MIT License.
