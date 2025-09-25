# WildFly Deployment Guide for Equipment Management Application

## ✅ **Updated**: Angular Frontend with `/trail/` Base-Href

Your Angular application has been built with the correct base-href for WildFly deployment:

- **Base-Href**: `/trail/` (matches WildFly context root)
- **Context Root**: `/equipment` (configured in jboss-web.xml)
- **Frontend Build**: Production optimized with command: `npx ng build --configuration production --base-href /trail/`

## 1. Memory Configuration (CRITICAL - Must do this first!)

### For Windows WildFly:

Edit `WILDFLY_HOME/bin/standalone.conf.bat` and add these JVM options:

```bat
set "JAVA_OPTS=%JAVA_OPTS% -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m -Xmx2048m -Xms1024m"
```

### For Linux/Unix WildFly:

Edit `WILDFLY_HOME/bin/standalone.conf` and add:

```bash
JAVA_OPTS="$JAVA_OPTS -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m -Xmx2048m -Xms1024m"
```

## 2. Application Optimizations Applied

✅ **Logging conflicts resolved**: Excluded Logback, using JBoss logging
✅ **Memory optimizations**:

- Disabled SQL logging in production
- Added connection pool optimizations (5 max connections, 2 min idle)
- Enabled compression
- Disabled open-in-view pattern

✅ **WildFly specific configurations**:

- `jboss-deployment-structure.xml` - excludes conflicting modules
- `jboss-web.xml` - sets context root and optimizations

## 3. Deployment Steps

1. **Start WildFly with increased memory** (after configuring memory settings above)
2. **Deploy the WAR file**:
   - Copy `trail.war` to `WILDFLY_HOME/standalone/deployments/`
   - Or use WildFly Admin Console

## 4. Application Access

- **Frontend (Angular)**: `http://localhost:8080/equipment/`
- **API Endpoints**: `http://localhost:8080/equipment/api/`
- **Database**: PostgreSQL (configured with connection pooling)

## 5. If You Still Get Memory Issues

### Option A: Increase memory further

```bat
# Windows
set "JAVA_OPTS=%JAVA_OPTS% -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=1024m -Xmx4096m -Xms2048m"

# Linux/Unix
JAVA_OPTS="$JAVA_OPTS -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=1024m -Xmx4096m -Xms2048m"
```

### Option B: Run as Standalone Spring Boot Application

```powershell
java -jar -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m -Xmx2048m trail.war
```

## 6. Troubleshooting

- **Check WildFly logs**: `WILDFLY_HOME/standalone/log/server.log`
- **Monitor memory usage**: Use JConsole or VisualVM
- **Database connectivity**: Ensure PostgreSQL is accessible from WildFly server

## Files Modified for WildFly Compatibility:

- `pom.xml` - Logging exclusions, memory optimizations
- `application.properties` - Connection pooling, performance tuning
- `jboss-deployment-structure.xml` - Module exclusions
- `jboss-web.xml` - Context root and WildFly optimizations
- `WebConfig.java` - Angular SPA routing support
