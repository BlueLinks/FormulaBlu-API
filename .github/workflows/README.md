# FormulaBlu GitHub Workflows

This directory contains GitHub Actions workflows for the FormulaBlu API project's CI/CD pipeline.

## 🚀 Available Workflows

### 1. `build-and-push-images.yml`

**Purpose**: Basic workflow for building and pushing Docker images
**Triggers**:

-   Push to `main` branch
-   PR merged to `main` branch

**Features**:

-   Builds all 8 API services (drivers, teams, tracks, races, seasons, events, results, api-gateway)
-   Pushes images to Docker Hub with `latest` and commit-specific tags
-   Updates Kubernetes deployment manifests
-   Sequential build process

### 2. `build-and-push-images-parallel.yml`

**Purpose**: Optimized parallel workflow for faster builds
**Triggers**:

-   Push to `main` branch
-   PR merged to `main` branch

**Features**:

-   Parallel builds using matrix strategy
-   Faster execution time
-   Same functionality as basic workflow
-   Better resource utilization

### 3. `oas-update.yml`

**Purpose**: Handles OpenAPI Specification updates
**Triggers**:

-   Changes to `OAS/**` directory
-   Changes to `.gitmodules`
-   Manual trigger

**Features**:

-   Updates OAS submodule
-   Regenerates Java models from OAS specs
-   Rebuilds all APIs with updated models
-   Pushes updated Docker images

### 4. `ci-cd-complete.yml`

**Purpose**: Comprehensive CI/CD pipeline with testing and security
**Triggers**:

-   All pushes to `main`
-   All pull requests

**Features**:

-   Unit testing with test reports
-   Parallel Docker image builds
-   Security vulnerability scanning (Trivy)
-   Kubernetes deployment updates
-   Comprehensive status reporting

### 5. `test-secrets.yml`

**Purpose**: Validates GitHub secrets configuration
**Triggers**:

-   Manual trigger (workflow_dispatch)
-   Changes to secret-related files

**Features**:

-   Validates all required secrets are present
-   Tests Docker Hub authentication
-   Provides detailed error messages
-   Safe to run without side effects

## 🔧 Setup Requirements

### Required Secrets

Add these secrets to your GitHub repository settings:

1. **DOCKERHUB_USERNAME**: Your Docker Hub username
2. **DOCKERHUB_TOKEN**: Your Docker Hub access token
3. **GITHUB_TOKEN**: Automatically provided by GitHub

> 📖 **Detailed Setup Instructions**: See [SECRETS.md](SECRETS.md) for comprehensive secret configuration guide and troubleshooting.

### Test Your Secret Configuration

Run the secret validation workflow to ensure everything is configured correctly:

1. Go to Actions tab → "Test Secrets Configuration"
2. Click "Run workflow"
3. Check the results for any missing or misconfigured secrets

### Docker Hub Setup

1. Create a Docker Hub account
2. Create a new repository for each API:

    - `formulablu/drivers-api`
    - `formulablu/teams-api`
    - `formulablu/tracks-api`
    - `formulablu/races-api`
    - `formulablu/seasons-api`
    - `formulablu/events-api`
    - `formulablu/results-api`
    - `formulablu/api-gateway`

3. Generate an access token:
    - Go to Docker Hub → Account Settings → Security
    - Create a new access token
    - Add it as `DOCKERHUB_TOKEN` secret

### Repository Setup

1. **FormulaBlu-API** (this repo): Contains the source code and workflows
2. **FormulaBlu-Deployments**: Contains Kubernetes manifests (will be updated automatically)

## 📋 Workflow Selection Guide

### For Development Teams

-   **Use `ci-cd-complete.yml`** - Provides full testing, security scanning, and comprehensive reporting

### For Production Deployments

-   **Use `build-and-push-images-parallel.yml`** - Fastest build times with parallel execution

### For OAS-Heavy Development

-   **Use `oas-update.yml`** - Automatically handles OAS specification updates

### For Simple Setups

-   **Use `build-and-push-images.yml`** - Basic functionality with sequential builds

## 🔄 Workflow Triggers

### Automatic Triggers

| Event               | Workflows Triggered              |
| ------------------- | -------------------------------- |
| Push to `main`      | All workflows                    |
| PR to `main`        | `ci-cd-complete.yml` (test only) |
| PR merged to `main` | All workflows                    |
| OAS changes         | `oas-update.yml`                 |
| Manual trigger      | `oas-update.yml`                 |

### Manual Triggers

You can manually trigger workflows from the GitHub Actions tab:

1. Go to Actions tab in your repository
2. Select the workflow you want to run
3. Click "Run workflow"
4. Choose the branch and click "Run workflow"

## 📊 Workflow Features

### Caching

-   **Gradle dependencies**: Cached between runs for faster builds
-   **Docker layers**: Cached using GitHub Actions cache
-   **OAS submodule**: Automatically updated

### Security

-   **Dependency scanning**: Built into the complete workflow
-   **Image vulnerability scanning**: Using Trivy
-   **Secret management**: Secure handling of Docker Hub credentials

### Monitoring

-   **Build status**: Clear success/failure indicators
-   **Test reports**: JUnit test results displayed in Actions
-   **Security reports**: Vulnerability scan results in Security tab
-   **Deployment summaries**: Detailed status reports

## 🐳 Docker Images

### Image Naming Convention

```
formulablu/{api-name}:{tag}
```

### Available Tags

-   `latest`: Always points to the latest build from main
-   `{commit-sha}`: Specific commit build (e.g., `abc1234`)
-   `{branch-name}-{commit-sha}`: Branch-specific builds

### Image Registry

All images are pushed to Docker Hub under the `formulablu` namespace.

## ☸️ Kubernetes Integration

### Automatic Updates

The workflows automatically update Kubernetes deployment manifests in the `FormulaBlu-Deployments` repository with new image tags.

### Deployment Process

1. Images are built and pushed to Docker Hub
2. Kubernetes manifests are updated with new image tags
3. Changes are committed to the deployment repository
4. Ready for deployment to Kubernetes clusters

## 🔍 Troubleshooting

### Common Issues

1. **Docker Hub Authentication Failed**

    - Verify `DOCKERHUB_USERNAME` and `DOCKERHUB_TOKEN` secrets
    - Check token permissions

2. **Gradle Build Failed**

    - Check Java version compatibility
    - Verify OAS submodule is properly initialized

3. **Docker Build Failed**

    - Ensure Dockerfile exists for each API
    - Check if JAR files are built successfully

4. **Kubernetes Update Failed**
    - Verify `FormulaBlu-Deployments` repository access
    - Check if deployment files exist

### Debug Steps

1. **Check workflow logs** in the Actions tab
2. **Verify secrets** are properly set
3. **Test locally** using the same commands
4. **Check repository permissions** for cross-repo updates

## 📈 Performance Optimization

### Build Time Optimization

-   Use `build-and-push-images-parallel.yml` for faster builds
-   Enable Gradle build cache
-   Use Docker layer caching

### Resource Optimization

-   Matrix strategy for parallel builds
-   Efficient Docker multi-stage builds
-   Selective workflow triggers

## 🔒 Security Best Practices

1. **Use secrets** for all sensitive data
2. **Enable vulnerability scanning** in the complete workflow
3. **Regular security updates** for base images
4. **Least privilege** for repository access
5. **Audit logs** for all workflow executions

## 📚 Additional Resources

-   [GitHub Actions Documentation](https://docs.github.com/en/actions)
-   [Docker Hub Documentation](https://docs.docker.com/docker-hub/)
-   [Kubernetes Documentation](https://kubernetes.io/docs/)
-   [FormulaBlu API Documentation](../README.md)

---

**FormulaBlu GitHub Workflows** - Automated CI/CD for Formula 1 microservices with Docker and Kubernetes integration.
