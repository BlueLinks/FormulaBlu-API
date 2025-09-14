# OpenAPI Specification (OAS) Setup

This document explains how to set up and use the OpenAPI Specification (OAS) system for FormulaBlu APIs.

## 🏗️ Architecture

The OAS system uses Git submodules to share API specifications and models across all microservices:

```
FormulaBlu-API/
├── OAS/                     # Git submodule (FormulaBlu-OAS repo)
│   ├── models/              # Shared data models
│   ├── apis/                # API specifications
│   └── shared/              # Common components
├── formulaBlu/              # Microservices
│   ├── drivers-api/
│   ├── teams-api/
│   └── ...
└── scripts/                 # Helper scripts
    └── update-oas.sh
```

## 🚀 Quick Start

### 1. Initial Setup

```bash
# Add OAS submodule (replace with your actual repo URL)
git submodule add https://github.com/yourusername/FormulaBlu-OAS.git OAS

# Initialize and update
git submodule update --init --recursive
```

### 2. Update OAS Specifications

```bash
# Run the update script
./scripts/update-oas.sh

# Or manually
git submodule update --remote --merge
cd formulaBlu
./gradlew openApiGenerate
```

### 3. Build APIs

```bash
cd formulaBlu
./gradlew build
```

## 📋 OAS Repository Structure

### Models Directory

```
OAS/models/
├── common/                  # Common models (Error, Pagination, etc.)
├── drivers/                 # Driver-related models
├── teams/                   # Team-related models
├── tracks/                  # Track-related models
├── seasons/                 # Season-related models
├── events/                  # Event-related models
├── results/                 # Result-related models
└── championship/            # Championship-related models
```

### APIs Directory

```
OAS/apis/
├── drivers-api.yaml         # Drivers API specification
├── teams-api.yaml           # Teams API specification
├── tracks-api.yaml          # Tracks API specification
├── seasons-api.yaml         # Seasons API specification
├── events-api.yaml          # Events API specification
├── results-api.yaml         # Results API specification
└── championship-api.yaml    # Championship API specification
```

## 🔧 Gradle Integration

The system automatically generates models for each API module:

-   **Input**: `OAS/apis/{api-name}-api.yaml`
-   **Output**: `formulaBlu/{api-name}/build/generated/`
-   **Package**: `com.formulablu.{api-name}.api` and `com.formulablu.{api-name}.model`

### Generated Files

-   **API Interfaces**: Controller interfaces for each endpoint
-   **Models**: Request/response DTOs
-   **Configuration**: Spring Boot configuration classes

## 🔄 Workflow

### Development Workflow

1. **Update OAS specs** in the FormulaBlu-OAS repository
2. **Run update script** to pull latest specs
3. **Generate models** automatically via Gradle
4. **Update API implementations** to use generated models
5. **Test and commit** changes

### CI/CD Workflow

-   **Daily updates**: GitHub Actions automatically updates OAS specs
-   **Build triggers**: API builds are triggered when OAS specs change
-   **Model generation**: Models are generated during build process

## 🛠️ Commands

### Update OAS Specifications

```bash
# Full update (recommended)
./scripts/update-oas.sh

# Manual update
git submodule update --remote --merge
cd formulaBlu
./gradlew openApiGenerate
```

### Generate Models for Specific API

```bash
cd formulaBlu
./gradlew :drivers-api:openApiGenerate
```

### Build All APIs

```bash
cd formulaBlu
./gradlew build
```

### Clean Generated Files

```bash
cd formulaBlu
./gradlew clean
```

## 🔍 Troubleshooting

### Common Issues

1. **Submodule not updating**

    ```bash
    git submodule update --remote --merge
    ```

2. **Generated models not found**

    ```bash
    cd formulaBlu
    ./gradlew openApiGenerate
    ```

3. **Build failures after OAS update**
    ```bash
    cd formulaBlu
    ./gradlew clean
    ./gradlew build
    ```

### Debug Commands

```bash
# Check submodule status
git submodule status

# Check OAS directory contents
ls -la OAS/

# Check generated files
find formulaBlu -name "*.java" -path "*/generated/*"
```

## 📚 Best Practices

1. **Always update OAS specs** in the dedicated repository first
2. **Use the update script** instead of manual commands
3. **Test after updates** to ensure compatibility
4. **Commit generated files** to version control
5. **Keep OAS specs versioned** and tagged

## 🔗 Related Files

-   `formulaBlu/build.gradle` - Root Gradle configuration
-   `scripts/update-oas.sh` - Update script
-   `.github/workflows/update-oas.yml` - OAS update workflow
-   `.github/workflows/build-apis.yml` - API build workflow
