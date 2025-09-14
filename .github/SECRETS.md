# GitHub Secrets Configuration Guide

This document outlines the required GitHub secrets for the FormulaBlu API project's CI/CD workflows.

## 🔐 Required Secrets

### Core Secrets (Required)

| Secret Name          | Description                            | Required For        | Example Value                   |
| -------------------- | -------------------------------------- | ------------------- | ------------------------------- |
| `DOCKERHUB_USERNAME` | Docker Hub username for pushing images | All build workflows | `your-dockerhub-username`       |
| `DOCKERHUB_TOKEN`    | Docker Hub access token (not password) | All build workflows | `dckr_pat_xxxxxxxxxxxxxxxxxxxx` |

### Optional Secrets (Environment-Specific)

| Secret Name        | Description            | Default Value | When to Use                    |
| ------------------ | ---------------------- | ------------- | ------------------------------ |
| `DOCKER_REGISTRY`  | Docker registry URL    | `docker.io`   | When using private registry    |
| `DOCKER_NAMESPACE` | Docker image namespace | `formulablu`  | When using different namespace |

## 🚀 Setup Instructions

### 1. Docker Hub Setup

1. **Create Docker Hub Account**

    - Go to [Docker Hub](https://hub.docker.com)
    - Create an account if you don't have one

2. **Create Access Token**

    - Go to Account Settings → Security
    - Click "New Access Token"
    - Give it a name like "FormulaBlu CI/CD"
    - Select "Read, Write, Delete" permissions
    - Copy the generated token

3. **Create Repositories**
   Create the following repositories in Docker Hub:
    - `formulablu/drivers-api`
    - `formulablu/teams-api`
    - `formulablu/tracks-api`
    - `formulablu/races-api`
    - `formulablu/seasons-api`
    - `formulablu/events-api`
    - `formulablu/results-api`
    - `formulablu/api-gateway`

### 2. GitHub Secrets Configuration

1. **Navigate to Repository Settings**

    - Go to your GitHub repository
    - Click "Settings" tab
    - Click "Secrets and variables" → "Actions"

2. **Add Required Secrets**
    - Click "New repository secret"
    - Add each secret with the exact name and value

### 3. Environment-Specific Configuration

For different environments (staging, production), you can override the default values:

```yaml
# Example for staging environment
DOCKER_REGISTRY: your-private-registry.com
DOCKER_NAMESPACE: formulablu-staging
```

## 🔒 Security Best Practices

### Secret Management

-   ✅ Use access tokens instead of passwords
-   ✅ Rotate tokens regularly (every 90 days)
-   ✅ Use least-privilege access
-   ✅ Never commit secrets to code
-   ✅ Use environment-specific secrets when needed

### Token Permissions

-   **Docker Hub Token**: Only needs "Read, Write, Delete" for the specific repositories
-   **GitHub Token**: Automatically provided with appropriate permissions

## 🧪 Testing Secret Configuration

### Manual Testing

1. Create a test branch
2. Push changes to trigger workflows
3. Check workflow logs for secret validation messages
4. Verify Docker images are pushed successfully

### Validation Steps

The workflows include automatic secret validation:

-   ✅ Checks if `DOCKERHUB_USERNAME` is set
-   ✅ Checks if `DOCKERHUB_TOKEN` is set
-   ✅ Fails fast with clear error messages if secrets are missing

## 🚨 Troubleshooting

### Common Issues

1. **"DOCKERHUB_USERNAME secret is required but not set"**

    - Solution: Add the `DOCKERHUB_USERNAME` secret in repository settings

2. **"DOCKERHUB_TOKEN secret is required but not set"**

    - Solution: Add the `DOCKERHUB_TOKEN` secret in repository settings

3. **"denied: requested access to the resource is denied"**

    - Solution: Check if the Docker Hub token has correct permissions
    - Solution: Verify the username matches the token owner

4. **"repository does not exist"**
    - Solution: Create the required Docker Hub repositories
    - Solution: Check the repository names match exactly

### Debug Steps

1. Check workflow logs for detailed error messages
2. Verify secrets are set correctly in repository settings
3. Test Docker Hub login manually with the same credentials
4. Ensure all required repositories exist in Docker Hub

## 📋 Workflow Dependencies

### Workflows Using Secrets

-   `ci-cd-complete.yml` - Full CI/CD pipeline
-   `build-and-push-images.yml` - Basic build workflow
-   `build-and-push-images-parallel.yml` - Parallel build workflow
-   `oas-update.yml` - OAS update and rebuild

### Secret Usage

-   **Docker Authentication**: All workflows that build/push images
-   **Repository Access**: All workflows that checkout code
-   **Environment Configuration**: Optional for custom registries/namespaces

## 🔄 Secret Rotation

### When to Rotate

-   Every 90 days (recommended)
-   If a secret is compromised
-   When changing Docker Hub accounts
-   When switching to different registries

### Rotation Process

1. Generate new token in Docker Hub
2. Update the secret in GitHub repository settings
3. Test with a small change to ensure it works
4. Delete the old token from Docker Hub

## 📞 Support

If you encounter issues with secret configuration:

1. Check this documentation first
2. Review workflow logs for specific error messages
3. Verify all secrets are set correctly
4. Test Docker Hub access manually
5. Contact the development team if issues persist

---

**Note**: This documentation is automatically updated when workflow configurations change. Always refer to the latest version for accurate information.
