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
import java.util.ArrayList;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(css = "nav a, .category-link")
    private List<WebElement> categoryLinks;
    
    @FindBy(css = ".product-item, .product-card")
    private List<WebElement> productItems;
    
    @FindBy(css = ".cart-icon, #cart, .cart_counter")
    private WebElement cartIcon;
    
    @FindBy(css = ".search-box, #search")
    private WebElement searchBox;
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    public void navigateToHomepage() {
        System.out.println("🚀 Navigating to Automation Test Store homepage...");
        driver.get("https://automationteststore.com/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        
        // Wait for page to fully load
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
        System.out.println("✅ Homepage loaded successfully");
    }
    
    public List<String> getAllCategoryNames() {
        System.out.println("🔍 Detecting all main category names...");
        List<String> categoryNames = new ArrayList<>();
        
        try {
            // Wait for navigation to load
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".subnav, .nav, .maintext")));
            
            // Multiple selectors to catch different category locations
            String[] categorySelectors = {
                ".subnav a",
                ".maintext a", 
                "nav ul li a",
                ".category-link",
                ".nav-pills a"
            };
            
            for (String selector : categorySelectors) {
                List<WebElement> categories = driver.findElements(By.cssSelector(selector));
                
                for (WebElement category : categories) {
                    String categoryText = category.getText().trim();
                    if (!categoryText.isEmpty() && 
                        !categoryText.equalsIgnoreCase("home") &&
                        !categoryText.equalsIgnoreCase("login") &&
                        !categoryText.equalsIgnoreCase("register") &&
                        !categoryNames.contains(categoryText)) {
                        categoryNames.add(categoryText);
                        System.out.println("📂 Category found: " + categoryText);
                    }
                }
            }
            
            System.out.println("✅ Total categories detected: " + categoryNames.size());
            return categoryNames;
            
        } catch (Exception e) {
            System.out.println("❌ Error detecting categories: " + e.getMessage());
            return categoryNames;
        }
    }
    
    public void selectRandomCategory() {
        System.out.println("🎲 Selecting random category...");
        List<String> categories = getAllCategoryNames();
        
        if (!categories.isEmpty()) {
            int randomIndex = (int) (Math.random() * categories.size());
            String selectedCategory = categories.get(randomIndex);
            System.out.println("🎯 Selected category: " + selectedCategory);
            
            try {
                // Try multiple approaches to find and click the category
                WebElement categoryElement = null;
                
                // Approach 1: Exact text match
                try {
                    categoryElement = driver.findElement(
                        By.xpath("//a[normalize-space(text())='" + selectedCategory + "']"));
                } catch (Exception e1) {
                    // Approach 2: Contains text
                    try {
                        categoryElement = driver.findElement(
                            By.xpath("//a[contains(text(),'" + selectedCategory + "')]"));
                    } catch (Exception e2) {
                        // Approach 3: Case insensitive
                        categoryElement = driver.findElement(
                            By.xpath("//a[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" 
                            + selectedCategory.toLowerCase() + "')]"));
                    }
                }
                
                if (categoryElement != null) {
                    // Scroll to element and click
                    ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", categoryElement);
                    Thread.sleep(500);
                    categoryElement.click();
                    
                    // Wait for category page to load
                    wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".product-item, .product-card, .thumbnails, .product")));
                    
                    System.out.println("✅ Successfully navigated to category: " + selectedCategory);
                }
                
            } catch (Exception e) {
                System.out.println("❌ Error selecting category: " + e.getMessage());
                // Fallback: click first available category link
                try {
                    WebElement firstCategory = driver.findElement(By.cssSelector(".subnav a"));
                    firstCategory.click();
                    System.out.println("🔄 Fallback: Selected first available category");
                } catch (Exception fallbackError) {
                    System.out.println("❌ Fallback also failed: " + fallbackError.getMessage());
                }
            }
        } else {
            System.out.println("❌ No categories found to select");
        }
    }
    
    public boolean hasAtLeastThreeProducts() {
        System.out.println("🔢 Checking if category has at least 3 products...");
        
        try {
            // Wait for products to load
            Thread.sleep(2000);
            
            // Multiple selectors for different product layouts
            String[] productSelectors = {
                ".thumbnails .col-md-3",
                ".product-item",
                ".product-card", 
                ".product",
                ".productcart"
            };
            
            int totalProducts = 0;
            
            for (String selector : productSelectors) {
                List<WebElement> products = driver.findElements(By.cssSelector(selector));
                if (products.size() > totalProducts) {
                    totalProducts = products.size();
                }
            }
            
            System.out.println("📊 Products found: " + totalProducts);
            boolean hasEnough = totalProducts >= 3;
            
            if (hasEnough) {
                System.out.println("✅ Category has sufficient products (" + totalProducts + " >= 3)");
            } else {
                System.out.println("⚠️ Category has insufficient products (" + totalProducts + " < 3)");
            }
            
            return hasEnough;
            
        } catch (Exception e) {
            System.out.println("❌ Error checking product count: " + e.getMessage());
            return false;
        }
    }
    
    public void clickCartIcon() {
        try {
            System.out.println("🛒 Clicking cart icon...");
            
            // Multiple selectors for cart icon
            String[] cartSelectors = {
                ".cart_counter",
                "#cart_total", 
                ".cart-icon",
                "a[href*='cart']",
                ".topcart"
            };
            
            WebElement cartElement = null;
            for (String selector : cartSelectors) {
                try {
                    cartElement = driver.findElement(By.cssSelector(selector));
                    if (cartElement.isDisplayed()) {
                        break;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            
            if (cartElement != null) {
                wait.until(ExpectedConditions.elementToBeClickable(cartElement));
                cartElement.click();
                System.out.println("✅ Cart icon clicked successfully");
            } else {
                System.out.println("❌ Cart icon not found");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error clicking cart icon: " + e.getMessage());
        }
    }
    
    public int getCartItemCount() {
        try {
            WebElement cartCounter = driver.findElement(By.cssSelector(".cart_counter, .cart-count"));
            String countText = cartCounter.getText().replaceAll("[^0-9]", "");
            return countText.isEmpty() ? 0 : Integer.parseInt(countText);
        } catch (Exception e) {
            return 0;
        }
    }
}