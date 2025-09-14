#!/bin/bash

# FormulaBlu OAS Submodule Setup Script
# This script sets up the OAS submodule for the FormulaBlu API repository

set -e

echo "🚀 Setting up FormulaBlu OAS Submodule..."

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Check if we're in the right directory
if [ ! -f "formulaBlu/build.gradle" ]; then
    print_error "This script must be run from the FormulaBlu-API root directory"
    exit 1
fi

# Check if OAS directory already exists
if [ -d "OAS" ]; then
    print_warning "OAS directory already exists. Checking if it's a submodule..."
    
    if [ -f "OAS/.git" ]; then
        print_success "OAS is already a submodule"
        
        # Update the submodule
        print_status "Updating OAS submodule..."
        git submodule update --remote --merge
        
        print_success "OAS submodule updated successfully"
        exit 0
    else
        print_error "OAS directory exists but is not a submodule. Please remove it first."
        exit 1
    fi
fi

# Get OAS repository URL
read -p "Enter the FormulaBlu-OAS repository URL (e.g., https://github.com/yourusername/FormulaBlu-OAS.git): " OAS_REPO_URL

if [ -z "$OAS_REPO_URL" ]; then
    print_error "Repository URL is required"
    exit 1
fi

# Add the submodule
print_status "Adding OAS submodule..."
git submodule add "$OAS_REPO_URL" OAS

# Initialize and update the submodule
print_status "Initializing OAS submodule..."
git submodule init
git submodule update --remote --merge

# Check if the OAS repository has the required structure
if [ ! -d "OAS/generated/apis" ]; then
    print_warning "OAS repository doesn't have generated/apis directory. Building OAS specifications..."
    
    cd OAS
    
    # Check if package.json exists
    if [ ! -f "package.json" ]; then
        print_error "OAS repository doesn't have package.json. Please ensure it's the correct repository."
        exit 1
    fi
    
    # Install dependencies and build
    print_status "Installing OAS dependencies..."
    npm install
    
    print_status "Building OAS specifications..."
    npm run build:all
    
    cd ..
fi

# Verify the setup
print_status "Verifying setup..."

# Check if drivers-api.yaml exists
if [ -f "OAS/generated/apis/drivers-api.yaml" ]; then
    print_success "drivers-api.yaml found"
else
    print_warning "drivers-api.yaml not found. You may need to build the OAS specifications."
fi

# Test Gradle build
print_status "Testing Gradle build with OAS integration..."
cd formulaBlu

# Test drivers-api build
if ./gradlew :drivers-api:openApiGenerate --no-daemon; then
    print_success "Gradle OAS integration test passed"
else
    print_error "Gradle OAS integration test failed"
    exit 1
fi

cd ..

print_success "OAS submodule setup completed successfully!"
print_status "Next steps:"
echo "1. Commit the submodule changes:"
echo "   git add .gitmodules OAS"
echo "   git commit -m 'Add OAS submodule'"
echo "   git push"
echo ""
echo "2. To update OAS specifications:"
echo "   git submodule update --remote --merge"
echo "   git add OAS"
echo "   git commit -m 'Update OAS submodule'"
echo "   git push"
echo ""
echo "3. To build APIs with latest OAS:"
echo "   cd formulaBlu"
echo "   ./gradlew build"
echo ""
print_status "Setup complete! 🎉"
