#!/bin/bash

# Complete Integration Build Script for Spring Boot + Angular

echo "=== Starting Complete Integration Build ==="
echo "Building Angular frontend and Spring Boot backend together..."
echo ""

# Change to project root
cd "$(dirname "$0")"

# Build Angular Frontend
echo "Step 1: Building Angular Frontend..."
cd fsd-frontend

# Install dependencies
echo "Installing npm dependencies..."
npm install

# Build Angular for production
echo "Building Angular with production configuration..."
npm run build

echo "✓ Angular build completed!"
echo ""

# Build Spring Boot Backend with Angular integration
echo "Step 2: Building Spring Boot Backend with Angular integration..."
cd ../fsd

# Clean and package with Maven
echo "Cleaning and packaging with Maven..."
mvn clean package -DskipTests

echo "✓ Spring Boot build completed!"
echo ""

# Display results
echo "=== Build Complete! ==="
echo "Files created:"
echo "  - Angular build: fsd-frontend/dist/social-media-app/"
echo "  - WAR file: fsd/target/trail.war"
echo ""
echo "Deployment URL structure:"
echo "  - Main App: http://172.10.8.61:8080/equipment/"
echo "  - Angular App: http://172.10.8.61:8080/equipment/trail/"
echo "  - API Base: http://172.10.8.61:8080/equipment/api/"
echo ""
echo "Next steps:"
echo "1. Copy trail.war to WildFly deployments directory"
echo "2. Access: http://172.10.8.61:8080/equipment/trail/"
echo ""
