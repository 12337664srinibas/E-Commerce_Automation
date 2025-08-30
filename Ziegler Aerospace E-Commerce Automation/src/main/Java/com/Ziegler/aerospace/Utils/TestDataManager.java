package com.ziegler.aerospace.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import com.ziegler.aerospace.pages.ProductPage.ProductDetails;
import com.ziegler.aerospace.pages.CartPage.CartItemDetails;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestDataManager {
    private static final String TEST_DATA_PATH = "testdata/";
    private static List<TestResult> testResults = new ArrayList<>();
    private static List<ProductDetails> productData = new ArrayList<>();
    private static List<CartItemDetails> cartData = new ArrayList<>();
    private static List<ValidationResult> validationData = new ArrayList<>();
    
    static {
        // Create directory if it doesn't exist
        new java.io.File(TEST_DATA_PATH).mkdirs();
        System.out.println("📁 Test data directory initialized: " + TEST_DATA_PATH);
    }
    
    public static void logTestResult(String testName, String status, 
                                   String details, String timestamp) {
        TestResult result = new TestResult(testName, status, details, timestamp);
        testResults.add(result);
        
        System.out.println("📝 Logged: " + testName + " - " + status);
    }
    
    public static void logProductValidation(ProductDetails product) {
        productData.add(product);
        
        String timestamp = getCurrentTimestamp();
        logTestResult("Product Validation", "LOGGED", 
            String.format("Product: %s, Price: %s, Qty: %s", 
                product.getName(), product.getPrice(), product.getQuantity()),
            timestamp);
        
        System.out.println("📦 Product logged: " + product.getName());
    }
    
    public static void logCartItem(CartItemDetails cartItem) {
        cartData.add(cartItem);
        
        String timestamp = getCurrentTimestamp();
        logTestResult("Cart Item Validation", "LOGGED",
            String.format("Item: %s, Price: %s, Qty: %s",
                cartItem.getName(), cartItem.getPrice(), cartItem.getQuantity()),
            timestamp);
    }
    
    public static void logFailedValidation(String testType, String errorMessage) {
        String timestamp = getCurrentTimestamp();
        
        testResults.add(new TestResult(testType, "FAILED", errorMessage, timestamp));
        System.out.println("❌ Failed validation logged: " + testType);
    }
    
    public static void logValidationResult(String validationType, boolean passed, String details) {
        ValidationResult result = new ValidationResult(validationType, passed, details, getCurrentTimestamp());
        validationData.add(result);
        
        String status = passed ? "PASS" : "FAIL";
        logTestResult("Validation Test", status, validationType + ": " + details, getCurrentTimestamp());
    }
    
    public static void exportResultsToCSV() {
        String timestamp = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        
        exportTestResults(timestamp);
        exportProductData(timestamp);
        exportCartData(timestamp);
        exportValidationData(timestamp);
        
        System.out.println("📊 All test data exported successfully");
    }
    
    private static void exportTestResults(String timestamp) {
        String fileName = TEST_DATA_PATH + "test_results_" + timestamp + ".csv";
        
        try (FileWriter writer = new FileWriter(fileName);
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            
            // Write header
            printer.printRecord("Test Name", "Status", "Details", "Timestamp");
            
            // Write test results
            for (TestResult result : testResults) {
                printer.printRecord(result.getTestName(), result.getStatus(), 
                    result.getDetails(), result.getTimestamp());
            }
            
            System.out.println("📄 Test results exported: " + fileName);
            
        } catch (IOException e) {
            System.out.println("❌ Failed to export test results: " + e.getMessage());
        }
    }
    
    private static void exportProductData(String timestamp) {
        if (productData.isEmpty()) return;
        
        String fileName = TEST_DATA_PATH + "product_data_" + timestamp + ".csv";
        
        try (FileWriter writer = new FileWriter(fileName);
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            
            // Write header
            printer.printRecord("Product Name", "Price", "Quantity", "URL", "In Stock", "Capture Time");
            
            // Write product data
            for (ProductDetails product : productData) {
                printer.printRecord(product.getName(), product.getPrice(), 
                    product.getQuantity(), product.getUrl(), product.isInStock(), getCurrentTimestamp());
            }
            
            System.out.println("📦 Product data exported: " + fileName);
            
        } catch (IOException e) {
            System.out.println("❌ Failed to export product data: " + e.getMessage());
        }
    }
    
    private static void exportCartData(String timestamp) {
        if (cartData.isEmpty()) return;
        
        String fileName = TEST_DATA_PATH + "cart_data_" + timestamp + ".csv";
        
        try (FileWriter writer = new FileWriter(fileName);
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            
            // Write header
            printer.printRecord("Item Name", "Price", "Quantity", "Verification Time");
            
            // Write cart data
            for (CartItemDetails item : cartData) {
                printer.printRecord(item.getName(), item.getPrice(), 
                    item.getQuantity(), getCurrentTimestamp());
            }
            
            System.out.println("🛒 Cart data exported: " + fileName);
            
        } catch (IOException e) {
            System.out.println("❌ Failed to export cart data: " + e.getMessage());
        }
    }
    
    private static void exportValidationData(String timestamp) {
        if (validationData.isEmpty()) return;
        
        String fileName = TEST_DATA_PATH + "validation_results_" + timestamp + ".csv";
        
        try (FileWriter writer = new FileWriter(fileName);
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            
            // Write header
            printer.printRecord("Validation Type", "Result", "Details", "Timestamp");
            
            // Write validation data
            for (ValidationResult validation : validationData) {
                printer.printRecord(validation.getValidationType(), 
                    validation.isPassed() ? "PASS" : "FAIL",
                    validation.getDetails(), validation.getTimestamp());
            }
            
            System.out.println("🧪 Validation data exported: " + fileName);
            
        } catch (IOException e) {
            System.out.println("❌ Failed to export validation data: " + e.getMessage());
        }
    }
    
    public static void logShippedElements(String elementType, String details) {
        String timestamp = getCurrentTimestamp();
        
        testResults.add(new TestResult("Shipped Elements", "LOGGED", 
            elementType + ": " + details, timestamp));
        
        System.out.println("📦 Shipped element logged: " + elementType);
    }
    
    public static void generateFinalSummary() {
        System.out.println("\n" + "=".repeat(100));
        System.out.println("🎯 ZIEGLER AEROSPACE - FINAL TEST EXECUTION SUMMARY");
        System.out.println("=".repeat(100));
        
        // Count results by status
        int totalTests = testResults.size();
        int passedTests = (int) testResults.stream().filter(r -> "PASS".equals(r.getStatus())).count();
        int failedTests = (int) testResults.stream().filter(r -> "FAILED".equals(r.getStatus())).count();
        int loggedItems = (int) testResults.stream().filter(r -> "LOGGED".equals(r.getStatus())).count();
        
        System.out.println("📊 EXECUTION STATISTICS:");
        System.out.println("   📋 Total Test Actions: " + totalTests);
        System.out.println("   ✅ Passed Tests: " + passedTests);
        System.out.println("   ❌ Failed Tests: " + failedTests);
        System.out.println("   📝 Logged Items: " + loggedItems);
        System.out.println("   📦 Products Tested: " + productData.size());
        System.out.println("   🛒 Cart Items Verified: " + cartData.size());
        System.out.println("   🧪 Validation Tests: " + validationData.size());
        
        if (totalTests > 0) {
            double successRate = (passedTests * 100.0) / (passedTests + failedTests);
            System.out.println("   📈 Success Rate: " + String.format("%.1f%%", successRate));
        }
        
        System.out.println("\n📁 GENERATED ARTIFACTS:");
        System.out.println("   📊 HTML Report: " + reportFileName);
        System.out.println("   📸 Screenshots: " + SCREENSHOT_PATH);
        System.out.println("   📄 CSV Data: " + TEST_DATA_PATH);
        
        System.out.println("\n⏰ COMPLETION TIME: " + getCurrentTimestamp());
        System.out.println("🏢 ASSIGNMENT COMPLETED FOR: Ziegler Aerospace");
        System.out.println("=".repeat(100));
    }
    
    public static void clearResults() {
        testResults.clear();
        productData.clear();
        cartData.clear();
        validationData.clear();
        System.out.println("🧹 Test data cleared for new execution");
    }
    
    private static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    // Inner classes for data structure
    public static class TestResult {
        private String testName;
        private String status;
        private String details;
        private String timestamp;
        
        public TestResult(String testName, String status, String details, String timestamp) {
            this.testName = testName;
            this.status = status;
            this.details = details;
            this.timestamp = timestamp;
        }
        
        // Getters
        public String getTestName() { return testName; }
        public String getStatus() { return status; }
        public String getDetails() { return details; }
        public String getTimestamp() { return timestamp; }
    }
    
    public static class ValidationResult {
        private String validationType;
        private boolean passed;
        private String details;
        private String timestamp;
        
        public ValidationResult(String validationType, boolean passed, String details, String timestamp) {
            this.validationType = validationType;
            this.passed = passed;
            this.details = details;
            this.timestamp = timestamp;
        }
        
        // Getters
        public String getValidationType() { return validationType; }
        public boolean isPassed() { return passed; }
        public String getDetails() { return details; }
        public String getTimestamp() { return timestamp; }
    }
}