#!/bin/bash

echo "Building Formula1 Microservices..."

# Build all modules
echo "Building all modules..."
./gradlew clean build

# Check if build was successful
if [ $? -eq 0 ]; then
    echo "✅ All modules built successfully!"
    echo ""
    echo "You can now run the services using:"
    echo "  docker-compose up -d"
    echo ""
    echo "Or run locally:"
    echo "  ./gradlew :eureka-server:bootRun"
    echo "  ./gradlew :api-gateway:bootRun"
    echo "  ./gradlew :races-api:bootRun"
    echo "  ./gradlew :drivers-api:bootRun"
    echo "  ./gradlew :teams-api:bootRun"
    echo "  ./gradlew :tracks-api:bootRun"
else
    echo "❌ Build failed. Please check the errors above."
    exit 1
fi
