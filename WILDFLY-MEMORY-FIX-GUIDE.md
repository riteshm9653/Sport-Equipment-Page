# 🚨 WildFly Metaspace OutOfMemoryError - COMPLETE SOLUTION

## ❌ **ERROR**:

```
WFLYCTL0080: Failed services
WFLYSRV0153: Failed to process phase FIRST_MODULE_USE of deployment "trail.war"
Caused by: java.lang.OutOfMemoryError: Metaspace
```

## 🎯 **ROOT CAUSE**:

Your WAR file (66.56 MB) with Spring Boot + Angular requires more Metaspace memory than WildFly's default allocation.

---

## ✅ **SOLUTION 1: Configure WildFly Memory (RECOMMENDED)**

### **Step 1: Locate WildFly Configuration File**

#### **Windows WildFly:**

- File: `WILDFLY_HOME\bin\standalone.conf.bat`
- Example: `C:\wildfly-31.0.0\bin\standalone.conf.bat`

#### **Linux/Unix WildFly:**

- File: `WILDFLY_HOME/bin/standalone.conf`
- Example: `/opt/wildfly/bin/standalone.conf`

### **Step 2: Edit Configuration File**

#### **For Windows** (`standalone.conf.bat`):

```bat
@echo off
rem Add this line at the top of the file (after @echo off)
set "JAVA_OPTS=%JAVA_OPTS% -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=1024m -Xmx4096m -Xms2048m"

rem Optional: For better garbage collection
set "JAVA_OPTS=%JAVA_OPTS% -XX:+UseG1GC -XX:MaxGCPauseMillis=200"

rem Your existing configuration continues below...
```

#### **For Linux/Unix** (`standalone.conf`):

```bash
#!/bin/bash
# Add these lines at the top of the file

# Memory Configuration for Spring Boot + Angular WAR
JAVA_OPTS="$JAVA_OPTS -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=1024m -Xmx4096m -Xms2048m"

# Optional: Better garbage collection
JAVA_OPTS="$JAVA_OPTS -XX:+UseG1GC -XX:MaxGCPauseMillis=200"

# Your existing configuration continues below...
```

### **Step 3: Restart WildFly**

```bash
# Stop WildFly
./bin/jboss-cli.sh --connect command=:shutdown

# Start WildFly with new memory settings
./bin/standalone.sh

# Or on Windows:
bin\standalone.bat
```

### **Step 4: Deploy WAR File**

```bash
# Copy to deployments directory
cp trail.war WILDFLY_HOME/standalone/deployments/

# Or use CLI
./bin/jboss-cli.sh --connect --command="deploy trail.war"
```

---

## ✅ **SOLUTION 2: Alternative Memory Settings**

If you still get memory errors, try these **HIGHER** memory settings:

### **Maximum Memory Configuration:**

```bash
# For Windows (standalone.conf.bat):
set "JAVA_OPTS=%JAVA_OPTS% -XX:MetaspaceSize=1024m -XX:MaxMetaspaceSize=2048m -Xmx8192m -Xms4096m"

# For Linux/Unix (standalone.conf):
JAVA_OPTS="$JAVA_OPTS -XX:MetaspaceSize=1024m -XX:MaxMetaspaceSize=2048m -Xmx8192m -Xms4096m"
```

---

## ✅ **SOLUTION 3: Run as Standalone Spring Boot (FASTEST)**

Instead of using WildFly, run directly as Spring Boot application:

```powershell
# Navigate to WAR location
cd "d:\java With spring boot\FSD\Equipments\fsd\target"

# Run with proper memory settings
java -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m -Xmx2048m -Xms1024m -jar trail.war
```

**Access URLs:**

- **Frontend**: `http://localhost:8080/equipment/`
- **API**: `http://localhost:8080/equipment/`

---

## 🔧 **Memory Explanation**

### **What these settings do:**

- `-XX:MetaspaceSize=512m` → Initial Metaspace size (class metadata)
- `-XX:MaxMetaspaceSize=1024m` → Maximum Metaspace size
- `-Xmx4096m` → Maximum heap memory (4GB)
- `-Xms2048m` → Initial heap memory (2GB)
- `-XX:+UseG1GC` → Better garbage collection for large applications

### **Why your app needs more memory:**

- ✅ **Spring Boot 3.4.4** → Heavy framework
- ✅ **Angular 19** → Large frontend bundle (347 kB)
- ✅ **JPA/Hibernate** → Object-relational mapping
- ✅ **PostgreSQL JDBC** → Database connectivity
- ✅ **Security + JWT** → Authentication layers

---

## 🎯 **RECOMMENDED APPROACH:**

### **For Development/Testing:**

Use **Solution 3** (Standalone Spring Boot) - It's faster and easier.

### **For Production:**

Use **Solution 1** (WildFly with proper memory) - Better for enterprise environments.

---

## 📋 **Step-by-Step Deployment Checklist**

### **Option A: WildFly Deployment**

- [ ] 1. Edit `standalone.conf.bat` (Windows) or `standalone.conf` (Linux)
- [ ] 2. Add memory settings: `-XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=1024m -Xmx4096m -Xms2048m`
- [ ] 3. Restart WildFly server completely
- [ ] 4. Deploy `trail.war` to `deployments/` folder
- [ ] 5. Check `server.log` for successful deployment
- [ ] 6. Access: `http://your-server:8080/equipment/`

### **Option B: Standalone Spring Boot**

- [ ] 1. Stop any running WildFly instances
- [ ] 2. Run: `java -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m -Xmx2048m -jar trail.war`
- [ ] 3. Wait for "Started TestApplication" message
- [ ] 4. Access: `http://localhost:8080/equipment/`

---

## 🚀 **Your Application Details:**

- **WAR File**: `trail.war` (66.56 MB)
- **Framework**: Spring Boot 3.4.4 + Angular 19
- **Database**: PostgreSQL (Render.com)
- **Frontend URL**: `/equipment/`
- **API Endpoints**: `/equipment/` (GET, POST, PUT, DELETE)

---

## 🔍 **Troubleshooting:**

### **If you still get OutOfMemoryError:**

1. **Double the memory**: Use Solution 2 (higher memory settings)
2. **Check system RAM**: Ensure your server has enough physical memory
3. **Use monitoring**: Add `-XX:+PrintGCDetails -XX:+PrintGCTimeStamps` to see memory usage

### **If WildFly won't start:**

1. **Check syntax** in configuration files
2. **Verify JAVA_HOME** is set correctly
3. **Check ports** (8080, 9990) are not in use

### **If deployment fails:**

1. **Check logs**: `WILDFLY_HOME/standalone/log/server.log`
2. **Clear deployments**: Remove old deployments first
3. **Restart clean**: Stop WildFly, delete `tmp/` and `data/`, restart

Your application is ready for deployment! 🎉
