# 🚀 Complete Angular + Spring Boot Integration for WildFly Deployment

## ✅ What Has Been Completed

### 1. **Angular Frontend Configuration**

- ✅ Configured `angular.json` with production build settings
- ✅ Set correct `baseHref: "/equipment/trail/"`
- ✅ Updated `package.json` build script
- ✅ Configured API service to use relative URLs (`/api/equipment`)
- ✅ Updated environment configuration for production

### 2. **Spring Boot Backend Integration**

- ✅ Added frontend-maven-plugin to `pom.xml`
- ✅ Configured automated Angular build during Maven build
- ✅ Updated `WebConfig.java` to handle Angular SPA routing
- ✅ Set up proper resource handlers for `/trail/` path
- ✅ Configured WildFly compatibility (logging, memory, packaging)

### 3. **Build Process Automation**

- ✅ Maven automatically installs Node.js and npm
- ✅ Runs `npm install` and `npm run build` during Maven build
- ✅ Copies Angular build files to Spring Boot webapp
- ✅ Creates single WAR file with both frontend and backend

## 📁 Project Structure (After Integration)

```
fsd/
├── src/main/webapp/
│   ├── trail/                    # Angular app files
│   │   ├── index.html           # Base href: /equipment/trail/
│   │   ├── main-EXIRVEPH.js     # Angular application bundle
│   │   ├── polyfills-FFHMD2TL.js
│   │   └── styles-XIG5JRJ3.css
│   └── WEB-INF/
│       ├── jboss-web.xml        # Context root: /equipment
│       └── jboss-deployment-structure.xml
└── target/
    └── trail.war (66MB)         # Complete integrated WAR file
```

## 🌐 URL Structure

| Purpose           | URL                                        |
| ----------------- | ------------------------------------------ |
| **Angular App**   | `http://172.10.8.61:8080/equipment/trail/` |
| **API Endpoints** | `http://172.10.8.61:8080/equipment/api/`   |
| **WildFly Admin** | `http://172.10.8.61:9990/console`          |

## 🔧 Build Commands

### Complete Build (Angular + Spring Boot)

```bash
cd fsd
mvn clean package
```

This single command:

1. Downloads and installs Node.js and npm
2. Runs `npm install` in the Angular project
3. Builds Angular with production configuration
4. Copies Angular files to Spring Boot webapp
5. Builds the complete WAR file

### Manual Angular Build (if needed)

```bash
cd fsd-frontend
npm run build
```

## 🚀 Deployment Steps

1. **Copy WAR to WildFly**

   ```bash
   cp fsd/target/trail.war /opt/wildfly/standalone/deployments/
   ```

2. **Access the Application**
   - Main Application: `http://172.10.8.61:8080/equipment/trail/`
   - API Documentation: `http://172.10.8.61:8080/equipment/swagger-ui.html`

## 🎯 Key Integration Features

### Frontend-Backend Communication

- Angular services use relative URLs (`/api/equipment`)
- Proxy configuration for development: `http://172.10.8.61:8080/equipment`
- Production: Served from same context, no CORS issues

### SPA Routing Support

- Spring Boot `WebConfig` handles Angular client-side routing
- Falls back to `index.html` for non-API routes
- Preserves Angular routing functionality

### Single Artifact Deployment

- One WAR file contains both frontend and backend
- No separate deployment of Angular app needed
- Simplified deployment and management

## 🔍 Verification

### Check Build Success

```bash
# Verify WAR file size (should be ~66MB)
ls -lh fsd/target/trail.war

# Check Angular files in WAR
unzip -l fsd/target/trail.war | grep -E "\.(html|js|css)$"
```

### Test Deployment

1. Deploy WAR to WildFly
2. Check WildFly admin console: `http://172.10.8.61:9990/console`
3. Verify deployment status
4. Access application: `http://172.10.8.61:8080/equipment/trail/`

## 🛠️ Development Workflow

### For Angular Changes

```bash
cd fsd-frontend
npm run build
cd ../fsd
mvn package -DskipTests
```

### For Spring Boot Changes

```bash
cd fsd
mvn package -DskipTests
```

### For Both

```bash
cd fsd
mvn clean package
```

## 📝 Configuration Files Modified

1. **`fsd-frontend/angular.json`** - Added production baseHref
2. **`fsd-frontend/package.json`** - Updated build script
3. **`fsd-frontend/src/environments/environment.ts`** - Production API URLs
4. **`fsd-frontend/src/app/services/equipment.service.ts`** - Relative API URLs
5. **`fsd/pom.xml`** - Added frontend-maven-plugin
6. **`fsd/src/main/java/com/fsd1/group_project/config/WebConfig.java`** - SPA routing

## 🎉 Final Result

You now have a **complete full-stack application** with:

- ✅ Angular 19 frontend with Material UI
- ✅ Spring Boot 3.4.4 backend with JPA, Security, JWT
- ✅ Single WAR deployment to WildFly
- ✅ Proper routing and API integration
- ✅ Production-ready configuration
- ✅ Automated build process

**Access your application at:** `http://172.10.8.61:8080/equipment/trail/`
