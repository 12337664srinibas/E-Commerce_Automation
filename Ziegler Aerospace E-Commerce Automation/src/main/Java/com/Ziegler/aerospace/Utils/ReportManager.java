package com.ziegler.aerospace.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final String REPORT_PATH = "reports/";
    private static final String SCREENSHOT_PATH = "screenshots/";
    private static String reportFileName;
    
    public static void initReports() {
        if (extent == null) {
            System.out.println("📊 Initializing Ziegler Aerospace test reporting system...");
            
            // Create directories if they don't exist
            new File(REPORT_PATH).mkdirs();
            new File(SCREENSHOT_PATH).mkdirs();
            
            String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            
            reportFileName = REPORT_PATH + "ZieglerAerospace_TestReport_" + timestamp + ".html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFileName);
            
            // Configure report appearance
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Ziegler Aerospace - E-Commerce Automation Report");
            sparkReporter.config().setReportName("🚀 Automation Test Results - E-Commerce Workflow");
            sparkReporter.config().setEncoding("utf-8");
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            
            // Add comprehensive system information
            extent.setSystemInfo("🏢 Company", "Ziegler Aerospace");
            extent.setSystemInfo("🎯 Application", "E-Commerce Workflow Testing");
            extent.setSystemInfo("🌐 Test Site", "https://automationteststore.com/");
            extent.setSystemInfo("🔧 Framework", "Selenium WebDriver + Java");
            extent.setSystemInfo("📋 Test Framework", "TestNG");
            extent.setSystemInfo("🏗️ Design Pattern", "Page Object Model (POM)");
            extent.setSystemInfo("🖥️ Environment", "Automated Testing Environment");
            extent.setSystemInfo("👤 Executed By", System.getProperty("user.name"));
            extent.setSystemInfo("☕ Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("💻 Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("⏰ Execution Time", LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            
            System.out.println("✅ Report system initialized: " + reportFileName);
        }
    }
    
    public static void createTest(String testName, String description) {
        ExtentTest extentTest = extent.createTest("🧪 " + testName, description);
        test.set(extentTest);
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("🚀 STARTING TEST: " + testName);
        System.out.println("📝 Description: " + description);
        System.out.println("=".repeat(80));
    }
    
    public static void logInfo(String message) {
        test.get().log(Status.INFO, "ℹ️ " + message);
        System.out.println("ℹ️ " + message);
    }
    
    public static void logPass(String message) {
        test.get().log(Status.PASS, "✅ " + message);
        System.out.println("✅ " + message);
    }
    
    public static void logFail(String message) {
        test.get().log(Status.FAIL, "❌ " + message);
        System.out.println("❌ " + message);
    }
    
    public static void logWarning(String message) {
        test.get().log(Status.WARNING, "⚠️ " + message);
        System.out.println("⚠️ " + message);
    }
    
    public static void logStep(String stepDescription) {
        test.get().log(Status.INFO, "🔄 STEP: " + stepDescription);
        System.out.println("🔄 STEP: " + stepDescription);
    }
    
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] sourceFile = screenshot.getScreenshotAs(OutputType.BYTES);
            
            String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"));
            String fileName = testName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_PATH + fileName;
            
            Files.write(Paths.get(filePath), sourceFile);
            
            System.out.println("📸 Screenshot captured: " + fileName);
            return filePath;
            
        } catch (IOException e) {
            System.out.println("❌ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
    
    public static void attachScreenshot(WebDriver driver, String testName) {
        String screenshotPath = captureScreenshot(driver, testName);
        if (screenshotPath != null) {
            try {
                test.get().addScreenCaptureFromPath(screenshotPath);
                logInfo("Screenshot attached to report");
            } catch (Exception e) {
                System.out.println("❌ Failed to attach screenshot to report: " + e.getMessage());
            }
        }
    }
    
    public static void logTestSummary(String testName, boolean passed, String details) {
        if (passed) {
            logPass("TEST COMPLETED: " + testName + " - " + details);
        } else {
            logFail("TEST FAILED: " + testName + " - " + details);
        }
    }
    
    public static void logProductDetails(String productName, String price, String quantity, String url) {
        String productInfo = String.format(
            "📦 Product Added - Name: %s | Price: %s | Quantity: %s | URL: %s", 
            productName, price, quantity, url);
        logInfo(productInfo);
    }
    
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
            System.out.println("\n" + "=".repeat(80));
            System.out.println("📊 REPORT GENERATION COMPLETED");
            System.out.println("📁 Report Location: " + reportFileName);
            System.out.println("📸 Screenshots: " + SCREENSHOT_PATH);
            System.out.println("=".repeat(80));
        }
    }
    
    public static void generateExecutionSummary(int totalTests, int passedTests, int failedTests) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("🎯 ZIEGLER AEROSPACE - TEST EXECUTION SUMMARY");
        System.out.println("=".repeat(80));
        System.out.println("📊 Total Test Scenarios: " + totalTests);
        System.out.println("✅ Passed: " + passedTests);
        System.out.println("❌ Failed: " + failedTests);
        System.out.println("📈 Success Rate: " + String.format("%.1f%%", 
            (passedTests * 100.0 / totalTests)));
        System.out.println("⏰ Completed: " + LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("=".repeat(80));
        
        // Log to extent report
        if (test.get() != null) {
            logInfo("Test Execution Summary Generated");
            logInfo("Total Scenarios: " + totalTests + " | Passed: " + passedTests + " | Failed: " + failedTests);
        }
    }
}