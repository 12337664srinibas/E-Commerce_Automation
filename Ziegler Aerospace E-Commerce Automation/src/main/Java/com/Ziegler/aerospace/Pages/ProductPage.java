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
import java.util.ArrayList;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private List<ProductDetails> selectedProducts;
    
    @FindBy(css = ".product-item, .thumbnails .col-md-3")
    private List<WebElement> productList;
    
    @FindBy(css = ".productname, .product-title h1")
    private WebElement productName;
    
    @FindBy(css = ".productprice, .price")
    private WebElement productPrice;
    
    @FindBy(css = "#option, .product-options select")
    private WebElement productOptions;
    
    @FindBy(css = ".cart, .btn-cart, .addtocart")
    private WebElement addToCartButton;
    
    @FindBy(css = "#product_quantity, .quantity input")
    private WebElement quantityField;
    
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.selectedProducts = new ArrayList<>();
        PageFactory.initElements(driver, this);
    }
    
    public void selectRandomProducts(int count) {
        System.out.println("🛍️ Starting random product selection process...");
        System.out.println("🎯 Target: Select and add " + count + " products to cart");
        
        try {
            // Wait for products to load
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".thumbnails .col-md-3, .product-item, .product")));
            
            List<WebElement> products = driver.findElements(
                By.cssSelector(".thumbnails .col-md-3, .product-item"));
            
            System.out.println("📦 Found " + products.size() + " products available");
            
            if (products.isEmpty()) {
                System.out.println("❌ No products found on this page");
                return;
            }
            
            int productsToSelect = Math.min(count, products.size());
            List<Integer> selectedIndices = new ArrayList<>();
            
            for (int i = 0; i < productsToSelect; i++) {
                int randomIndex;
                do {
                    randomIndex = (int) (Math.random() * products.size());
                } while (selectedIndices.contains(randomIndex));
                
                selectedIndices.add(randomIndex);
                
                System.out.println("\n🎲 Selecting product " + (i + 1) + " of " + productsToSelect);
                
                WebElement selectedProduct = products.get(randomIndex);
                
                // Find and click product link
                WebElement productLink = selectedProduct.findElement(
                    By.cssSelector("a, .product-link, .prdocutname a"));
                
                String productHref = productLink.getAttribute("href");
                System.out.println("🔗 Product URL: " + productHref);
                
                // Scroll to product and click
                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", productLink);
                Thread.sleep(500);
                productLink.click();
                
                // Wait for product page to load
                wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".productname, .product-title, .product-details")));
                
                // Capture product details and add to cart
                ProductDetails details = captureProductDetails();
                addProductToCart();
                selectedProducts.add(details);
                
                System.out.println("✅ Product " + (i + 1) + " added successfully");
                System.out.println("📝 " + details.toString());
                
                // Navigate back to product list if not the last product
                if (i < productsToSelect - 1) {
                    driver.navigate().back();
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(".thumbnails .col-md-3, .product-item")));
                    
                    // Refresh product list
                    products = driver.findElements(
                        By.cssSelector(".thumbnails .col-md-3, .product-item"));
                }
            }
            
            System.out.println("\n🎉 Product selection completed!");
            System.out.println("📊 Total products added to cart: " + selectedProducts.size());
            
        } catch (Exception e) {
            System.out.println("❌ Error during product selection: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public ProductDetails captureProductDetails() {
        ProductDetails details = new ProductDetails();
        
        try {
            // Capture product name
            try {
                WebElement nameElement = driver.findElement(
                    By.cssSelector(".productname, .product-title h1, .bgnone h1"));
                details.setName(nameElement.getText().trim());
            } catch (Exception e) {
                details.setName("Product Name Not Available");
            }
            
            // Capture product price
            try {
                WebElement priceElement = driver.findElement(
                    By.cssSelector(".productprice, .price, .oneprice"));
                details.setPrice(priceElement.getText().trim());
            } catch (Exception e) {
                details.setPrice("Price Not Available");
            }
            
            // Capture quantity (default or user-selected)
            try {
                WebElement qtyElement = driver.findElement(
                    By.cssSelector("#product_quantity, .quantity input, input[name='quantity']"));
                details.setQuantity(qtyElement.getAttribute("value"));
            } catch (Exception e) {
                details.setQuantity("1");
            }
            
            // Capture current URL
            details.setUrl(driver.getCurrentUrl());
            
            // Check if product is in stock
            try {
                List<WebElement> outOfStockElements = driver.findElements(
                    By.cssSelector(".out-of-stock, .nostock"));
                details.setInStock(outOfStockElements.isEmpty());
            } catch (Exception e) {
                details.setInStock(true); // Assume in stock if can't determine
            }
            
        } catch (Exception e) {
            System.out.println("⚠️ Error capturing product details: " + e.getMessage());
        }
        
        return details;
    }
    
    private void addProductToCart() {
        try {
            System.out.println("🛒 Adding product to cart...");
            
            // Handle product options if available
            List<WebElement> optionSelects = driver.findElements(
                By.cssSelector("select[name*='option'], .product-options select"));
            
            for (WebElement select : optionSelects) {
                try {
                    Select dropdown = new Select(select);
                    List<WebElement> options = dropdown.getOptions();
                    if (options.size() > 1) {
                        // Select random option (skip first which is usually "Select...")
                        int randomOption = 1 + (int) (Math.random() * (options.size() - 1));
                        dropdown.selectByIndex(randomOption);
                        System.out.println("🔧 Selected product option: " + 
                            dropdown.getFirstSelectedOption().getText());
                    }
                } catch (Exception e) {
                    System.out.println("⚠️ Could not handle product option: " + e.getMessage());
                }
            }
            
            // Find and click add to cart button
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".cart, .btn-cart, .addtocart, .productcart")));
            
            // Scroll to button and click
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", addButton);
            Thread.sleep(500);
            addButton.click();
            
            // Wait for cart update
            Thread.sleep(3000);
            System.out.println("✅ Product added to cart successfully");
            
        } catch (Exception e) {
            System.out.println("❌ Error adding product to cart: " + e.getMessage());
            
            // Try alternative add to cart method
            try {
                WebElement altButton = driver.findElement(
                    By.cssSelector("input[value*='Add'], button[title*='Add']"));
                altButton.click();
                Thread.sleep(2000);
                System.out.println("✅ Product added using alternative method");
            } catch (Exception altError) {
                System.out.println("❌ Alternative add to cart also failed: " + altError.getMessage());
            }
        }
    }
    
    public List<ProductDetails> getSelectedProducts() {
        return new ArrayList<>(selectedProducts);
    }
    
    public void clearSelectedProducts() {
        selectedProducts.clear();
    }
    
    public static class ProductDetails {
        private String name;
        private String price;
        private String quantity;
        private String url;
        private boolean inStock;
        
        // Constructors
        public ProductDetails() {}
        
        public ProductDetails(String name, String price, String quantity, String url, boolean inStock) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.url = url;
            this.inStock = inStock;
        }
        
        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getPrice() { return price; }
        public void setPrice(String price) { this.price = price; }
        
        public String getQuantity() { return quantity; }
        public void setQuantity(String quantity) { this.quantity = quantity; }
        
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        
        public boolean isInStock() { return inStock; }
        public void setInStock(boolean inStock) { this.inStock = inStock; }
        
        @Override
        public String toString() {
            return String.format("📦 Product: %s | 💰 Price: %s | 📊 Qty: %s | 📈 Stock: %s", 
                name, price, quantity, inStock ? "Available" : "Out of Stock");
        }
        
        public String toCSVString() {
            return String.format("%s,%s,%s,%s,%s", 
                name, price, quantity, url, inStock);
        }
    }
}