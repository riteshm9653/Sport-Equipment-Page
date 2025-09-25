# Complete Integration Build Script for Windows PowerShell
# Spring Boot + Angular Integration

Write-Host "=== Starting Complete Integration Build ===" -ForegroundColor Green
Write-Host "Building Angular frontend and Spring Boot backend together..." -ForegroundColor Yellow
Write-Host ""

# Get script location
$scriptPath = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $scriptPath

# Build Angular Frontend
Write-Host "Step 1: Building Angular Frontend..." -ForegroundColor Cyan
Set-Location "fsd-frontend"

# Check if npm is installed
try {
    $npmVersion = npm --version
    Write-Host "Using npm version: $npmVersion" -ForegroundColor White
} catch {
    Write-Host "Error: npm is not installed or not in PATH" -ForegroundColor Red
    Write-Host "Please install Node.js from https://nodejs.org/" -ForegroundColor Yellow
    exit 1
}

# Install dependencies
Write-Host "Installing npm dependencies..." -ForegroundColor White
npm install
if ($LASTEXITCODE -ne 0) {
    Write-Host "Error: npm install failed" -ForegroundColor Red
    exit 1
}

# Build Angular for production
Write-Host "Building Angular with production configuration..." -ForegroundColor White
npm run build
if ($LASTEXITCODE -ne 0) {
    Write-Host "Error: Angular build failed" -ForegroundColor Red
    exit 1
}

Write-Host "✓ Angular build completed!" -ForegroundColor Green
Write-Host ""

# Build Spring Boot Backend with Angular integration
Write-Host "Step 2: Building Spring Boot Backend with Angular integration..." -ForegroundColor Cyan
Set-Location "../fsd"

# Clean and package with Maven
Write-Host "Cleaning and packaging with Maven..." -ForegroundColor White
mvn clean package -DskipTests
if ($LASTEXITCODE -ne 0) {
    Write-Host "Error: Maven build failed" -ForegroundColor Red
    exit 1
}

Write-Host "✓ Spring Boot build completed!" -ForegroundColor Green
Write-Host ""

# Display results
Write-Host "=== Build Complete! ===" -ForegroundColor Green
Write-Host "Files created:" -ForegroundColor Yellow
Write-Host "  - Angular build: fsd-frontend\dist\social-media-app\" -ForegroundColor White
Write-Host "  - WAR file: fsd\target\trail.war" -ForegroundColor White
Write-Host ""
Write-Host "Deployment URL structure:" -ForegroundColor Yellow
Write-Host "  - Main App: http://172.10.8.61:8080/equipment/" -ForegroundColor Cyan
Write-Host "  - Angular App: http://172.10.8.61:8080/equipment/trail/" -ForegroundColor Cyan
Write-Host "  - API Base: http://172.10.8.61:8080/equipment/api/" -ForegroundColor Cyan
Write-Host ""
Write-Host "Next steps:" -ForegroundColor Magenta
Write-Host "1. Copy trail.war to WildFly deployments directory" -ForegroundColor White
Write-Host "2. Access: http://172.10.8.61:8080/equipment/trail/" -ForegroundColor White
Write-Host ""

# Return to original directory
Set-Location $scriptPath
