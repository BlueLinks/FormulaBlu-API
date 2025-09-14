#!/bin/bash

# GitHub Secrets Validation Script
# This script helps validate that all required secrets are properly configured

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}🔐 GitHub Secrets Validation Script${NC}"
echo -e "${BLUE}====================================${NC}"
echo ""

# Function to check if running in GitHub Actions
is_github_actions() {
    [ -n "$GITHUB_ACTIONS" ]
}

# Function to validate a secret
validate_secret() {
    local secret_name=$1
    local secret_value=$2
    local is_required=${3:-true}
    
    if [ -z "$secret_value" ]; then
        if [ "$is_required" = true ]; then
            echo -e "  ${RED}❌ $secret_name: Missing (Required)${NC}"
            return 1
        else
            echo -e "  ${YELLOW}⚠️  $secret_name: Not set (Optional)${NC}"
            return 0
        fi
    else
        # Mask the secret value for security
        local masked_value="${secret_value:0:4}${secret_value: -4}"
        echo -e "  ${GREEN}✅ $secret_name: Set (${masked_value})${NC}"
        return 0
    fi
}

# Function to test Docker Hub authentication
test_docker_auth() {
    local username=$1
    local token=$2
    
    if [ -z "$username" ] || [ -z "$token" ]; then
        echo -e "  ${RED}❌ Cannot test Docker authentication - credentials missing${NC}"
        return 1
    fi
    
    echo -e "  ${BLUE}Testing Docker Hub authentication...${NC}"
    
    # Test Docker login (only if not in GitHub Actions to avoid conflicts)
    if ! is_github_actions; then
        if echo "$token" | docker login --username "$username" --password-stdin > /dev/null 2>&1; then
            echo -e "  ${GREEN}✅ Docker Hub authentication successful${NC}"
            docker logout > /dev/null 2>&1
        else
            echo -e "  ${RED}❌ Docker Hub authentication failed${NC}"
            return 1
        fi
    else
        echo -e "  ${YELLOW}⚠️  Skipping Docker auth test in GitHub Actions${NC}"
    fi
}

# Main validation
echo -e "${BLUE}Checking required secrets...${NC}"

# Required secrets
required_secrets=(
    "DOCKERHUB_USERNAME"
    "DOCKERHUB_TOKEN"
)

# Optional secrets
optional_secrets=(
    "DOCKER_REGISTRY"
    "DOCKER_NAMESPACE"
)

# Check required secrets
missing_required=0
for secret in "${required_secrets[@]}"; do
    secret_value=$(eval echo \$$secret)
    if ! validate_secret "$secret" "$secret_value" true; then
        ((missing_required++))
    fi
done

echo ""

# Check optional secrets
echo -e "${BLUE}Checking optional secrets...${NC}"
for secret in "${optional_secrets[@]}"; do
    secret_value=$(eval echo \$$secret)
    validate_secret "$secret" "$secret_value" false
done

echo ""

# Test Docker authentication if credentials are available
if [ $missing_required -eq 0 ]; then
    echo -e "${BLUE}Testing Docker Hub authentication...${NC}"
    test_docker_auth "$DOCKERHUB_USERNAME" "$DOCKERHUB_TOKEN"
else
    echo -e "${YELLOW}⚠️  Skipping Docker authentication test - required secrets missing${NC}"
fi

echo ""

# Summary
if [ $missing_required -eq 0 ]; then
    echo -e "${GREEN}🎉 All required secrets are properly configured!${NC}"
    echo -e "${GREEN}Your GitHub workflows should run successfully.${NC}"
    exit 0
else
    echo -e "${RED}❌ $missing_required required secret(s) are missing.${NC}"
    echo -e "${RED}Please configure the missing secrets in your GitHub repository settings.${NC}"
    echo ""
    echo -e "${BLUE}To configure secrets:${NC}"
    echo "1. Go to your GitHub repository"
    echo "2. Click 'Settings' → 'Secrets and variables' → 'Actions'"
    echo "3. Click 'New repository secret'"
    echo "4. Add each missing secret with the correct name and value"
    echo ""
    echo -e "${BLUE}For detailed instructions, see: .github/SECRETS.md${NC}"
    exit 1
fi
