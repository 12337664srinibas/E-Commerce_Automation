@echo off
echo.
echo ===============================================================================
echo 🚀 ZIEGLER AEROSPACE - E-COMMERCE AUTOMATION TEST EXECUTION
echo ===============================================================================
echo.
echo 🎯 Starting automated test execution...
echo 📊 Framework: Selenium + Java + TestNG
echo 🌐 Target: https://automationteststore.com/
echo.

REM Clean previous results
if exist "reports" rmdir /s /q "reports"
if exist "screenshots" rmdir /s /q "screenshots" 
if exist "testdata" rmdir /s /q "testdata"

echo 🧹 Cleaned previous test artifacts
echo.

REM Run Maven tests
echo 🔧 Executing Maven test suite...
mvn clean test

echo.
echo ===============================================================================
echo 🎉 TEST EXECUTION COMPLETED
echo ===============================================================================
echo 📊 Check reports/ directory for detailed HTML reports
echo 📸 Check screenshots/ directory for failure captures  
echo 📄 Check testdata/ directory for CSV exports
echo ===============================================================================

pause