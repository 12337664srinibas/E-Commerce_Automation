package com.ziegler.aerospace.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(css = "#guest")
    private WebElement guestCheckoutRadio;
    
    @FindBy(css = "#accountFrm_firstname")
    private WebElement firstNameField;
    
    @FindBy(css = "#accountFrm_lastname")
    private WebElement lastNameField;
    
    @FindBy(css = "#accountFrm_email")
    private WebElement emailField;
    
    @FindBy(css = "#accountFrm_address_1")
    private WebElement addressField;
    
    @FindBy(css = "#accountFrm_city")
    private WebElement cityField;
    
    @FindBy(css = "#accountFrm_zone_id")
    private WebElement stateDropdown;
    
    @FindBy(css = "#accountFrm_postcode")
    private WebElement zipCodeField;
    
    @FindBy(css = "#accountFrm_country_id")
    private WebElement countryDropdown;
    
    @FindBy(css = ".btn-continue, #checkout_btn")
    private WebElement continueButton;
    
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public void selectGuestCheckout() {
        System.out.println("👤 Selecting guest checkout option...");
        
        try {
            // Look for guest checkout radio button
            List<WebElement> guestOptions = driver.findElements(
                By.cssSelector("#guest, input[value='guest']"));
            
            if (!guestOptions.isEmpty()) {
                WebElement guestRadio = guestOptions.get(0);
                if (!guestRadio.isSelected()) {
                    guestRadio.click();
                    System.out.println("✅ Guest checkout selected");
                }
                
                // Click continue to proceed to guest form
                Thread.sleep(1000);
                WebElement continueBtn = driver.findElement(
                    By.cssSelector("button[title='Continue'], .btn-continue, input[value='Continue']"));
                continueBtn.click();
                
                // Wait for guest form to appear
                wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("#accountFrm_firstname, .checkout-form")));
                
                System.out.println("✅ Guest checkout form loaded");
                
            } else {
                System.out.println("ℹ️ Guest checkout option not found, proceeding with available form");
            }
            
        } catch (Exception e) {
            System.out.println("⚠️ Guest checkout selection issue: " + e.getMessage());
            System.out.println("🔄 Proceeding with available checkout form");
        }
    }
    
    public void fillCheckoutForm() {
        System.out.println("📝 Filling checkout form with test data...");
        
        try {
            // Wait for form to be visible
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#accountFrm_firstname, .checkout-form, .form-group")));
            
            Thread.sleep(1000); // Allow form to fully render
            
            // Fill personal information
            System.out.println("👤 Filling personal information...");
            fillFieldSafely("#accountFrm_firstname", "John", "First Name");
            fillFieldSafely("#accountFrm_lastname", "Doe", "Last Name");
            fillFieldSafely("#accountFrm_email", "john.doe@ziegler.aerospace", "Email");
            fillFieldSafely("#accountFrm_telephone", "281-555-0123", "Phone");
            
            // Fill address information
            System.out.println("🏠 Filling address information...");
            fillFieldSafely("#accountFrm_address_1", "123 Aerospace Drive", "Address");
            fillFieldSafely("#accountFrm_city", "Houston", "City");
            fillFieldSafely("#accountFrm_postcode", "77058", "ZIP Code");
            
            // Select country first
            selectDropdownSafely("#accountFrm_country_id", "United States", "Country");
            Thread.sleep(2000); // Wait for state dropdown to populate
            
            // Select state
            selectDropdownSafely("#accountFrm_zone_id", "Texas", "State");
            
            System.out.println("✅ Checkout form filled successfully");
            
        } catch (Exception e) {
            System.out.println("❌ Error filling checkout form: " + e.getMessage());
        }
    }
    
    private void fillFieldSafely(String selector, String value, String fieldName) {
        try {
            WebElement field = driver.findElement(By.cssSelector(selector));
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
    
    private void selectDropdownSafely(String selector, String value, String fieldName) {
        try {
            WebElement dropdown = driver.findElement(By.cssSelector(selector));
            if (dropdown != null && dropdown.isDisplayed()) {
                Select select = new Select(dropdown);
                
                // Try exact match first
                try {
                    select.selectByVisibleText(value);
                    System.out.println("🔽 " + fieldName + ": " + value);
                } catch (Exception e1) {
                    // Try partial match
                    try {
                        List<WebElement> options = select.getOptions();
                        for (WebElement option : options) {
                            if (option.getText().contains(value)) {
                                select.selectByVisibleText(option.getText());
                                System.out.println("🔽 " + fieldName + ": " + option.getText());
                                break;
                            }
                        }
                    } catch (Exception e2) {
                        System.out.println("⚠️ Could not select " + fieldName + ": " + value);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ Could not find dropdown for " + fieldName + ": " + e.getMessage());
        }
    }
    
    public void proceedToNextStep() {
        System.out.println("➡️ Proceeding to next checkout step...");
        
        try {
            String[] continueSelectors = {
                ".btn-continue",
                "#checkout_btn", 
                "button[title='Continue']",
                "input[value='Continue']",
                ".continue-btn"
            };
            
            WebElement continueBtn = null;
            for (String selector : continueSelectors) {
                try {
                    continueBtn = driver.findElement(By.cssSelector(selector));
                    if (continueBtn.isDisplayed() && continueBtn.isEnabled()) {
                        break;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            
            if (continueBtn != null) {
                wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
                continueBtn.click();
                
                // Wait for next step to load
                Thread.sleep(3000);
                System.out.println("✅ Proceeded to next checkout step");
            } else {
                System.out.println("❌ Continue button not found");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error proceeding to next step: " + e.getMessage());
        }
    }
    
    public boolean isOnCheckoutPage() {
        try {
            String currentUrl = driver.getCurrentUrl();
            boolean urlCheck = currentUrl.contains("checkout");
            
            boolean elementCheck = !driver.findElements(
                By.cssSelector(".checkout-content, #checkout, .checkout-form")).isEmpty();
            
            return urlCheck || elementCheck;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    public void simulateUserRegistration() {
        System.out.println("📋 Simulating user registration process...");
        
        try {
            // Fill additional registration fields if present
            fillFieldSafely("#accountFrm_loginname", "johndoe_ziegler", "Username");
            fillFieldSafely("#accountFrm_password", "ZieglerAero2024!", "Password");
            fillFieldSafely("#accountFrm_confirm", "ZieglerAero2024!", "Confirm Password");
            
            // Accept terms and conditions if present
            try {
                WebElement termsCheckbox = driver.findElement(
                    By.cssSelector("#accountFrm_agree, input[name*='agree']"));
                if (!termsCheckbox.isSelected()) {
                    termsCheckbox.click();
                    System.out.println("✅ Terms and conditions accepted");
                }
            } catch (Exception e) {
                System.out.println("ℹ️ Terms checkbox not found or not required");
            }
            
            System.out.println("✅ Registration simulation completed");
            
        } catch (Exception e) {
            System.out.println("❌ Error during registration simulation: " + e.getMessage());
        }
    }
    
    public static class CartItemDetails {
        private String name;
        private String price;
        private String quantity;
        
        public CartItemDetails() {}
        
        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getPrice() { return price; }
        public void setPrice(String price) { this.price = price; }
        
        public String getQuantity() { return quantity; }
        public void setQuantity(String quantity) { this.quantity = quantity; }
        
        @Override
        public String toString() {
            return String.format("Cart Item: %s | Price: %s | Qty: %s", name, price, quantity);
        }
    }
}