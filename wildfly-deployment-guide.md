# WildFly Deployment Guide

## Optimized WAR Built Successfully

Your optimized `trail.war` has been built with WildFly-specific optimizations to prevent memory issues.

### Key Optimizations Applied:

1. **Excluded Spring Boot DevTools** - Reduces memory footprint
2. **WildFly JAR Exclusions** - Prevents conflicts:
   - jboss-\*.jar
   - undertow-\*.jar
   - wildfly-\*.jar
3. **Manifest Dependencies** - Uses WildFly's JavaEE API instead of bundled versions

## WildFly Memory Configuration

### 1. Update WildFly JVM Settings

Edit `WILDFLY_HOME/bin/standalone.conf` (Linux/Mac) or `standalone.conf.bat` (Windows):

```bash
# Increase Metaspace and Heap Memory
JAVA_OPTS="-Xms1024m -Xmx2048m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m"

# Additional GC optimizations for large applications
JAVA_OPTS="$JAVA_OPTS -XX:+UseG1GC -XX:G1HeapRegionSize=16m -XX:+DisableExplicitGC"
```

For Windows (`standalone.conf.bat`):

```batch
set "JAVA_OPTS=-Xms1024m -Xmx2048m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m -XX:+UseG1GC -XX:G1HeapRegionSize=16m -XX:+DisableExplicitGC"
```

### 2. Deploy the Optimized WAR

1. Copy `trail.war` to `WILDFLY_HOME/standalone/deployments/`
2. Start WildFly: `./standalone.sh` (Linux/Mac) or `standalone.bat` (Windows)
3. Monitor deployment in console logs

### 3. Application URLs

After successful deployment:

- **Angular Application**: `http://localhost:8080/trail/`
- **API Endpoints**: `http://localhost:8080/trail/api/equipment`

### 4. Troubleshooting

#### If you still get memory errors:

1. **Increase Metaspace further**:

   ```bash
   -XX:MaxMetaspaceSize=768m
   ```

2. **Monitor memory usage**:

   ```bash
   -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
   ```

3. **Enable JVM debug logging**:
   ```bash
   -XX:+TraceClassLoading -XX:+TraceClassUnloading
   ```

#### Alternative: Exploded Deployment

If WAR is too large, try exploded deployment:

1. Create directory: `WILDFLY_HOME/standalone/deployments/trail.war/`
2. Extract WAR contents to this directory
3. Create marker file: `trail.war.dodeploy`

### 5. Database Connection

Your PostgreSQL connection to Render should work automatically with the current `application.properties` settings.

### 6. Verification Steps

1. Check WildFly console for "Deployed 'trail.war'" message
2. Test Angular app at `/trail/`
3. Verify API endpoints: `/trail/api/equipment`
4. Check database connectivity in application logs

## File Locations

- **Optimized WAR**: `d:\java With spring boot\FSD\Equipments\fsd\target\trail.war`
- **Size**: Optimized for WildFly deployment
- **Contains**: Angular build with `/trail/` base href + Spring Boot backend

The optimized build should now deploy successfully to WildFly without memory issues!
