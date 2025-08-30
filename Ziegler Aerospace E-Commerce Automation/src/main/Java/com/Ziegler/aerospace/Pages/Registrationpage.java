package com.ziegler.aerospace.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(css = "#AccountFrm_firstname")
    private WebElement firstNameField;
    
    @FindBy(css = "#AccountFrm_lastname")
    private WebElement lastNameField;
    
    @FindBy(css = "#AccountFrm_email")
    private WebElement emailField;
    
    @FindBy(css = "#AccountFrm_loginname")
    private WebElement usernameField;
    
    @FindBy(css = "#AccountFrm_password")
    private WebElement passwordField;
    
    @FindBy(css = "#AccountFrm_confirm")
    private WebElement confirmPasswordField;
    
    @FindBy(css = ".btn-continue, button[title='Continue']")
    private WebElement submitButton;
    
    @FindBy(css = ".alert-error, .error")
    private WebElement errorMessage;
    
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public void navigateToRegistration() {
        System.out.println("📝 Navigating to registration page...");
        driver.get("https://automationteststore.com/index.php?rt=account/create");
        
        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("#AccountFrm_firstname, .registration-form, .form-group")));
        
        System.out.println("✅ Registration page loaded");
    }
    
    public void attemptRegistrationWithEmptyFields() {
        System.out.println("🧪 Testing registration with empty required fields...");
        
        try {
            // Ensure all fields are empty
            clearAllFields();
            
            // Attempt to submit with empty fields
            WebElement submitBtn = findSubmitButton();
            if (submitBtn != null) {
                submitBtn.click();
                System.out.println("🔄 Submitted form with empty fields");
                
                // Wait for validation errors to appear
                Thread.sleep(3000);
                
                boolean hasErrors = hasValidationErrors();
                if (hasErrors) {
                    System.out.println("✅ Validation errors correctly displayed for empty fields");
                } else {
                    System.out.println("⚠️ No validation errors found - this may indicate an issue");
                }
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error during empty field registration test: " + e.getMessage());
        }
    }
    
    public void attemptRegistrationWithInvalidEmail() {
        System.out.println("🧪 Testing registration with invalid email format...");
        
        try {
            // Clear all fields first
            clearAllFields();
            
            // Fill form with invalid email
            fillFieldSafely(firstNameField, "Test", "First Name");
            fillFieldSafely(lastNameField, "User", "Last Name");
            fillFieldSafely(emailField, "invalid-email-format", "Email (Invalid)");
            fillFieldSafely(usernameField, "testuser" + System.currentTimeMillis(), "Username");
            fillFieldSafely(passwordField, "password123", "Password");
            fillFieldSafely(confirmPasswordField, "password123", "Confirm Password");
            
            // Submit form
            WebElement submitBtn = findSubmitButton();
            if (submitBtn != null) {
                submitBtn.click();
                System.out.println("🔄 Submitted form with invalid email");
                
                // Wait for validation errors
                Thread.sleep(3000);
                
                boolean hasErrors = hasValidationErrors();
                if (hasErrors) {
                    System.out.println("✅ Email validation errors correctly displayed");
                } else {
                    System.out.println("⚠️ No email validation errors found");
                }
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error during invalid email registration test: " + e.getMessage());
        }
    }
    
    public void attemptRegistrationWithMismatchedPasswords() {
        System.out.println("🧪 Testing registration with mismatched passwords...");
        
        try {
            clearAllFields();
            
            // Fill form with mismatched passwords
            fillFieldSafely(firstNameField, "Test", "First Name");
            fillFieldSafely(lastNameField, "User", "Last Name");
            fillFieldSafely(emailField, "test@ziegler.aerospace", "Email");
            fillFieldSafely(usernameField, "testuser" + System.currentTimeMillis(), "Username");
            fillFieldSafely(passwordField, "password123", "Password");
            fillFieldSafely(confirmPasswordField, "differentpassword", "Confirm Password (Different)");
            
            // Submit form
            WebElement submitBtn = findSubmitButton();
            if (submitBtn != null) {
                submitBtn.click();
                System.out.println("🔄 Submitted form with mismatched passwords");
                
                Thread.sleep(3000);
                
                boolean hasErrors = hasValidationErrors();
                if (hasErrors) {
                    System.out.println("✅ Password mismatch validation working correctly");
                } else {
                    System.out.println("⚠️ No password mismatch validation found");
                }
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error during password mismatch test: " + e.getMessage());
        }
    }
    
    private void clearAllFields() {
        System.out.println("🧹 Clearing all form fields...");
        
        String[] fieldSelectors = {
            "#AccountFrm_firstname",
            "#AccountFrm_lastname", 
            "#AccountFrm_email",
            "#AccountFrm_loginname",
            "#AccountFrm_password",
            "#AccountFrm_confirm"
        };
        
        for (String selector : fieldSelectors) {
            try {
                WebElement field = driver.findElement(By.cssSelector(selector));
                if (field.isDisplayed()) {
                    field.clear();
                }
            } catch (Exception e) {
                // Field not found, continue
            }
        }
    }
    
    private void fillFieldSafely(WebElement field, String value, String fieldName) {
        try {
            if (field != null && field.isDisplayed() && field.isEnabled()) {
                // Scroll to field
                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", field);
                Thread.sleep(300);
                
                field.clear();
                field.sendKeys(value);
                System.out.println("✏️ " + fieldName + ": " + value);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Could not fill " + fieldName + ": " + e.getMessage());
        }
    }
    
    private WebElement findSubmitButton() {
        String[] submitSelectors = {
            ".btn-continue",
            "button[title='Continue']",
            "input[value='Continue']",
            ".submit-btn",
            "button[type='submit']"
        };
        
        for (String selector : submitSelectors) {
            try {
                WebElement button = driver.findElement(By.cssSelector(selector));
                if (button.isDisplayed() && button.isEnabled()) {
                    return button;
                }
            } catch (Exception e) {
                continue;
            }
        }
        
        return null;
    }
    
    public boolean hasValidationErrors() {
        System.out.println("🔍 Checking for validation errors...");
        
        try {
            // Check for various error indicators
            String[] errorSelectors = {
                ".alert-error",
                ".error", 
                ".field-error",
                ".has-error",
                ".invalid-feedback",
                ".text-danger",
                ".validation-error"
            };
            
            boolean foundErrors = false;
            
            for (String selector : errorSelectors) {
                List<WebElement> errors = driver.findElements(By.cssSelector(selector));
                
                for (WebElement error : errors) {
                    if (error.isDisplayed() && !error.getText().trim().isEmpty()) {
                        System.out.println("🚨 Validation Error: " + error.getText().trim());
                        foundErrors = true;
                    }
                }
            }
            
            // Check for HTML5 validation
            try {
                WebElement activeElement = driver.switchTo().activeElement();
                String validationMessage = activeElement.getAttribute("validationMessage");
                if (validationMessage != null && !validationMessage.isEmpty()) {
                    System.out.println("🚨 HTML5 Validation: " + validationMessage);
                    foundErrors = true;
                }
            } catch (Exception e) {
                // HTML5 validation check failed, continue
            }
            
            // Check for required field highlighting
            List<WebElement> requiredFields = driver.findElements(
                By.cssSelector("input:invalid, .is-invalid, .error-field"));
            
            if (!requiredFields.isEmpty()) {
                System.out.println("🚨 Found " + requiredFields.size() + " invalid/required fields");
                foundErrors = true;
            }
            
            if (!foundErrors) {
                System.out.println("ℹ️ No validation errors detected");
            }
            
            return foundErrors;
            
        } catch (Exception e) {
            System.out.println("❌ Error checking validation: " + e.getMessage());
            return false;
        }
    }
    
    public void takeScreenshotOnFailure(String testName) {
        try {
            System.out.println("📸 Capturing screenshot for validation failure: " + testName);
            // Screenshot capture will be handled by ReportManager
        } catch (Exception e) {
            System.out.println("❌ Error preparing screenshot: " + e.getMessage());
        }
    }
    
    public ValidationResult performComprehensiveValidationTest() {
        System.out.println("🧪 Performing comprehensive validation testing...");
        
        ValidationResult result = new ValidationResult();
        
        // Test 1: Empty fields
        attemptRegistrationWithEmptyFields();
        result.setEmptyFieldsValidation(hasValidationErrors());
        
        // Navigate back to clean form
        navigateToRegistration();
        
        // Test 2: Invalid email
        attemptRegistrationWithInvalidEmail();
        result.setInvalidEmailValidation(hasValidationErrors());
        
        // Navigate back to clean form
        navigateToRegistration();
        
        // Test 3: Mismatched passwords
        attemptRegistrationWithMismatchedPasswords();
        result.setPasswordMismatchValidation(hasValidationErrors());
        
        System.out.println("📊 Validation Test Results:");
        System.out.println("   Empty Fields: " + (result.isEmptyFieldsValidation() ? "✅ PASS" : "❌ FAIL"));
        System.out.println("   Invalid Email: " + (result.isInvalidEmailValidation() ? "✅ PASS" : "❌ FAIL"));
        System.out.println("   Password Mismatch: " + (result.isPasswordMismatchValidation() ? "✅ PASS" : "❌ FAIL"));
        
        return result;
    }
    
    public static class ValidationResult {
        private boolean emptyFieldsValidation;
        private boolean invalidEmailValidation;
        private boolean passwordMismatchValidation;
        
        // Getters and setters
        public boolean isEmptyFieldsValidation() { return emptyFieldsValidation; }
        public void setEmptyFieldsValidation(boolean emptyFieldsValidation) { 
            this.emptyFieldsValidation = emptyFieldsValidation; 
        }
        
        public boolean isInvalidEmailValidation() { return invalidEmailValidation; }
        public void setInvalidEmailValidation(boolean invalidEmailValidation) { 
            this.invalidEmailValidation = invalidEmailValidation; 
        }
        
        public boolean isPasswordMismatchValidation() { return passwordMismatchValidation; }
        public void setPasswordMismatchValidation(boolean passwordMismatchValidation) { 
            this.passwordMismatchValidation = passwordMismatchValidation; 
        }
        
        public boolean allTestsPassed() {
            return emptyFieldsValidation && invalidEmailValidation && passwordMismatchValidation;
        }
    }
}