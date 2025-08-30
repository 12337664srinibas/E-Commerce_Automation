package com.ziegler.aerospace.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(css = ".cart-item, .table tbody tr")
    private List<WebElement> cartItems;
    
    @FindBy(css = ".checkout, .btn-checkout")
    private WebElement checkoutButton;
    
    @FindBy(css = ".total, .cart-total")
    private WebElement totalAmount;
    
    @FindBy(css = ".cart-empty, .empty-cart")
    private WebElement emptyCartMessage;
    
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public void navigateToCart() {
        System.out.println("🛒 Navigating to shopping cart...");
        driver.get("https://automationteststore.com/index.php?rt=checkout/cart");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        
        // Wait for cart content to load
        Thread.sleep(2000);
        System.out.println("✅ Cart page loaded");
    }
    
    public boolean verifyItemsInCart() {
        System.out.println("🔍 Verifying items in shopping cart...");
        
        try {
            // Check if cart is empty first
            if (isCartEmpty()) {
                System.out.println("🛒 Cart is empty - no items to verify");
                return false;
            }
            
            // Find cart items using multiple selectors
            List<WebElement> items = new ArrayList<>();
            
            String[] itemSelectors = {
                ".table tbody tr:not(.cart-empty)",
                ".cart-item",
                ".product-list tr",
                "table tr:has(.prdocutname)"
            };
            
            for (String selector : itemSelectors) {
                try {
                    List<WebElement> foundItems = driver.findElements(By.cssSelector(selector));
                    if (foundItems.size() > items.size()) {
                        items = foundItems;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            
            if (items.size() > 0) {
                System.out.println("📦 Cart contains " + items.size() + " item(s)");
                
                // Log each item details
                for (int i = 0; i < items.size(); i++) {
                    WebElement item = items.get(i);
                    try {
                        CartItemDetails itemDetails = extractItemDetails(item, i + 1);
                        System.out.println("📝 " + itemDetails.toString());
                        
                    } catch (Exception e) {
                        System.out.println("⚠️ Could not extract details for item " + (i + 1) + 
                            ": " + e.getMessage());
                    }
                }
                
                // Verify total amount
                String total = getTotalAmount();
                System.out.println("💰 Cart Total: " + total);
                
                return true;
            } else {
                System.out.println("❌ No items found in cart");
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error verifying cart items: " + e.getMessage());
            return false;
        }
    }
    
    private CartItemDetails extractItemDetails(WebElement item, int itemNumber) {
        CartItemDetails details = new CartItemDetails();
        
        try {
            // Extract item name
            try {
                WebElement nameElement = item.findElement(
                    By.cssSelector("td:nth-child(2), .prdocutname, .product-name"));
                details.setName(nameElement.getText().trim());
            } catch (Exception e) {
                details.setName("Item " + itemNumber);
            }
            
            // Extract price
            try {
                WebElement priceElement = item.findElement(
                    By.cssSelector("td:nth-child(4), .price, .oneprice"));
                details.setPrice(priceElement.getText().trim());
            } catch (Exception e) {
                details.setPrice("Price not found");
            }
            
            // Extract quantity
            try {
                WebElement qtyElement = item.findElement(
                    By.cssSelector("td:nth-child(3), .quantity, input[name*='quantity']"));
                String qtyText = qtyElement.getText().trim();
                if (qtyText.isEmpty()) {
                    qtyText = qtyElement.getAttribute("value");
                }
                details.setQuantity(qtyText);
            } catch (Exception e) {
                details.setQuantity("1");
            }
            
        } catch (Exception e) {
            System.out.println("⚠️ Error extracting item details: " + e.getMessage());
        }
        
        return details;
    }
    
    public void proceedToCheckout() {
        System.out.println("➡️ Proceeding to checkout...");
        
        try {
            // Look for checkout button with multiple possible selectors
            WebElement checkout = null;
            
            String[] checkoutSelectors = {
                "#cart_checkout",
                ".btn-checkout", 
                "a[href*='checkout']",
                ".checkout-btn",
                "input[value*='Checkout']"
            };
            
            for (String selector : checkoutSelectors) {
                try {
                    checkout = driver.findElement(By.cssSelector(selector));
                    if (checkout.isDisplayed() && checkout.isEnabled()) {
                        break;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            
            if (checkout != null) {
                wait.until(ExpectedConditions.elementToBeClickable(checkout));
                checkout.click();
                
                // Wait for checkout page to load
                wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".checkout-content, #checkout, .checkout-form")));
                
                System.out.println("✅ Successfully navigated to checkout");
            } else {
                System.out.println("❌ Checkout button not found");
            }
                
        } catch (Exception e) {
            System.out.println("❌ Error proceeding to checkout: " + e.getMessage());
        }
    }
    
    public String getTotalAmount() {
        try {
            String[] totalSelectors = {
                ".total-price",
                ".cart-total", 
                ".total",
                ".totals .total",
                "td.align_right:last-child"
            };
            
            for (String selector : totalSelectors) {
                try {
                    WebElement total = driver.findElement(By.cssSelector(selector));
                    String totalText = total.getText().trim();
                    if (!totalText.isEmpty()) {
                        return totalText;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            
            return "Total not found";
            
        } catch (Exception e) {
            return "Error retrieving total: " + e.getMessage();
        }
    }
    
    public boolean isCartEmpty() {
        try {
            // Check for empty cart indicators
            List<WebElement> emptyIndicators = driver.findElements(
                By.cssSelector(".cart-empty, .empty-cart, .no-items"));
            
            if (!emptyIndicators.isEmpty()) {
                for (WebElement indicator : emptyIndicators) {
                    if (indicator.isDisplayed()) {
                        return true;
                    }
                }
            }
            
            // Check if no items in cart table
            List<WebElement> cartRows = driver.findElements(
                By.cssSelector(".table tbody tr, .cart-item"));
            
            return cartRows.isEmpty();
            
        } catch (Exception e) {
            return true;
        }
    }
    
    public List<CartItemDetails> getAllCartItems() {
        List<CartItemDetails> allItems = new ArrayList<>();
        
        try {
            List<WebElement> items = driver.findElements(
                By.cssSelector(".table tbody tr:not(.cart-empty)"));
            
            for (int i = 0; i < items.size(); i++) {
                CartItemDetails itemDetails = extractItemDetails(items.get(i), i + 1);
                allItems.add(itemDetails);
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error getting all cart items: " + e.getMessage());
        }
        
        return allItems;
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
            return String.format("Item: %s | Price: %s | Qty: %s", name, price, quantity);
        }
    }
}