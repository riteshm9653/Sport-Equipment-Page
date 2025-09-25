# WildFly Deployment Verification Script
param(
    [string]$ServerIP = "172.10.8.61",
    [int]$AppPort = 8080,
    [int]$AdminPort = 9990
)

Write-Host "=== WildFly Deployment Verification ===" -ForegroundColor Green
Write-Host "Server: $ServerIP" -ForegroundColor Cyan
Write-Host "Application Port: $AppPort" -ForegroundColor Cyan
Write-Host "Admin Port: $AdminPort" -ForegroundColor Cyan
Write-Host ""

# Test connectivity
Write-Host "1. Testing connectivity..." -ForegroundColor Yellow
try {
    $appConnection = Test-NetConnection -ComputerName $ServerIP -Port $AppPort -WarningAction SilentlyContinue
    $adminConnection = Test-NetConnection -ComputerName $ServerIP -Port $AdminPort -WarningAction SilentlyContinue

    if ($appConnection.TcpTestSucceeded) {
        Write-Host "   ✓ Application port $AppPort is accessible" -ForegroundColor Green
    } else {
        Write-Host "   ✗ Application port $AppPort is NOT accessible" -ForegroundColor Red
    }

    if ($adminConnection.TcpTestSucceeded) {
        Write-Host "   ✓ Admin port $AdminPort is accessible" -ForegroundColor Green
    } else {
        Write-Host "   ✗ Admin port $AdminPort is NOT accessible" -ForegroundColor Red
    }
} catch {
    Write-Host "   Error testing connectivity: $_" -ForegroundColor Red
}

Write-Host ""

# Test HTTP endpoints
Write-Host "2. Testing HTTP endpoints..." -ForegroundColor Yellow
$endpoints = @(
    "http://${ServerIP}:${AppPort}/",
    "http://${ServerIP}:${AppPort}/equipment/",
    "http://${ServerIP}:${AppPort}/equipment/trail/",
    "http://${ServerIP}:${AppPort}/equipment/api/",
    "http://${ServerIP}:${AdminPort}/"
)

foreach ($endpoint in $endpoints) {
    try {
        $response = Invoke-WebRequest -Uri $endpoint -TimeoutSec 10 -UseBasicParsing -ErrorAction Stop
        Write-Host "   ✓ $endpoint - Status: $($response.StatusCode)" -ForegroundColor Green
    } catch {
        $statusCode = $_.Exception.Response.StatusCode
        if ($statusCode) {
            Write-Host "   ⚠ $endpoint - Status: $statusCode" -ForegroundColor Yellow
        } else {
            Write-Host "   ✗ $endpoint - Connection failed" -ForegroundColor Red
        }
    }
}

Write-Host ""
Write-Host "3. Next steps if issues found:" -ForegroundColor Magenta
Write-Host "   - Check WildFly service: systemctl status wildfly" -ForegroundColor White
Write-Host "   - View WildFly logs: tail -f /opt/wildfly/standalone/log/server.log" -ForegroundColor White
Write-Host "   - Access admin console: http://${ServerIP}:${AdminPort}/console" -ForegroundColor White
Write-Host "   - Deploy WAR: Copy trail.war to /opt/wildfly/standalone/deployments/" -ForegroundColor White
