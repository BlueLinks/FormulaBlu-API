#!/bin/bash

# Update OAS Specifications Script
# This script updates the OAS submodule and regenerates models

set -e

echo "🔄 Updating OAS specifications..."

# Check if we're in the right directory
if [ ! -f "formulaBlu/build.gradle" ]; then
    echo "❌ Error: Please run this script from the FormulaBlu-API root directory"
    exit 1
fi

# Update OAS submodule
echo "📥 Updating OAS submodule..."
git submodule update --remote --merge

# Check if there are changes
if git diff --staged --quiet; then
    echo "✅ No changes in OAS specifications"
else
    echo "📝 OAS specifications updated, committing changes..."
    git add OAS
    git commit -m "Update OAS specifications"
    echo "✅ OAS specifications committed"
fi

# Generate models for all APIs
echo "🔧 Generating models from OAS specifications..."
cd formulaBlu

# List of API modules
APIS=("drivers-api" "teams-api" "tracks-api" "races-api" "seasons-api" "events-api" "results-api")

for api in "${APIS[@]}"; do
    if [ -d "$api" ]; then
        # Check if OAS spec exists for this API
        spec_file="../OAS/apis/${api}.yaml"
        if [ -f "$spec_file" ]; then
            echo "📦 Generating models for $api..."
            ./gradlew ":$api:openApiGenerate" --quiet
        else
            echo "⚠️  Skipping $api (OAS spec not found: $spec_file)"
        fi
    else
        echo "⚠️  Skipping $api (directory not found)"
    fi
done

echo "✅ OAS update complete!"
echo ""
echo "Next steps:"
echo "1. Review generated models in formulaBlu/*/build/generated/"
echo "2. Update your API implementations to use generated models"
echo "3. Test your APIs: ./gradlew test"
