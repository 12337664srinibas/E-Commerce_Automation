# 🚀 Ziegler Aerospace - E-Commerce Automation Testing Assignment

## 📋 Assignment Overview
**Automated Testing of Dynamic E-Commerce Flow** using Selenium + Java for Ziegler Aerospace internship application.

**Target Website**: https://automationteststore.com/  
**Framework**: Selenium WebDriver + Java + TestNG  
**Design Pattern**: Page Object Model (POM)  
**Reporting**: ExtentReports + CSV Export  

---

## 🏗️ Project Structure
```
📁 Ziegler Aerospace E-Commerce Automation/
├── 📁 src/
│   ├── 📁 main/java/com/ziegler/aerospace/
│   │   ├── 📁 pages/           # Page Object Model classes
│   │   │   ├── 📄 HomePage.java
│   │   │   ├── 📄 ProductPage.java
│   │   │   ├── 📄 CartPage.java
│   │   │   ├── 📄 CheckoutPage.java
│   │   │   └── 📄 RegistrationPage.java
│   │   └── 📁 utils/           # Utility classes
│   │       ├── 📄 DriverManager.java
│   │       ├── 📄 ReportManager.java
│   │       └── 📄 TestDataManager.java
│   └── 📁 test/
│       ├── 📁 java/com/ziegler/aerospace/tests/
│       │   └── 📄 ECommerceWorkflowTest.java
│       └── 📁 resources/
│           └── 📄 testng.xml
├── 📄 pom.xml                 # Maven dependencies
├── 📄 build.gradle            # Gradle alternative
└── 📄 README.md              # This documentation
```

---

## 🎯 Test Scenarios Covered

### 1. 🏠 Homepage & Category Verification
- ✅ Navigate to https://automationteststore.com/
- ✅ **Dynamically detect and print all main category names**
- ✅ Navigate into random category using detected names
- ✅ **Assert that the category has at least 3 visible products**

### 2. 🛍️ Product Selection & Cart Addition
- ✅ **Randomly select and add 2 products to cart**
- ✅ **Capture and log each product's details:**
  - 📦 Product Name
  - 💰 Price
  - 📊 Quantity
  - 🔗 Product URL
  - 📈 Stock Status
- ✅ **Validate cart counter updates correctly**
- ✅ **Handle out of stock scenarios gracefully**

### 3. 🛒 Cart & Checkout Workflow
- ✅ Navigate to shopping cart
- ✅ **Assert both added items are listed with correct price and total**
- ✅ **Proceed to checkout and simulate user registration**
- ✅ Fill comprehensive checkout form with test data
- ✅ Handle guest checkout and registration workflows

### 4. 🧪 Negative Scenario Testing
- ✅ **Test registration page validation comprehensively**
- ✅ **Leave required fields empty and attempt submission**
- ✅ **Attempt registration with invalid email format**
- ✅ Test password mismatch scenarios
- ✅ **Take screenshots upon validation failure**
- ✅ Verify error messages and field highlighting

### 5. 📊 Comprehensive Reporting
- ✅ **Log total product cost and failed validations**
- ✅ **Generate automated HTML reports with screenshots**
- ✅ **Export test data to CSV format**
- ✅ Performance metrics and execution statistics
- ✅ Professional report formatting with Ziegler Aerospace branding

---

## 🔧 Technical Implementation

### Framework & Tools
- **Language**: Java 11+
- **Build Tool**: Maven 3.6+ / Gradle 7+
- **Web Automation**: Selenium WebDriver 4.15.0
- **Test Framework**: TestNG 7.8.0
- **Design Pattern**: Page Object Model (POM)
- **Wait Strategy**: WebDriverWait with intelligent fallbacks
- **Reporting**: ExtentReports 5.1.1 with custom themes
- **Data Export**: Apache Commons CSV 1.10.0
- **Driver Management**: WebDriverManager 5.6.2

### 🎨 Key Features
- 🎲 **Dynamic category detection** - No hardcoded category names
- 🔀 **Random product selection** - Truly dynamic test scenarios
- 🛡️ **Comprehensive error handling** - Graceful failure recovery
- 📸 **Screenshot capture** - Automatic failure documentation
- 📈 **Detailed reporting** - HTML and CSV formats
- 🔒 **Thread-safe design** - Concurrent execution support
- 🎯 **Smart element detection** - Multiple selector strategies

---

## 🚀 Running the Tests

### Prerequisites
- ☕ Java 11 or higher
- 📦 Maven 3.6+ or Gradle 7+
- 🌐 Chrome browser installed
- 🔗 Internet connection for test site access

### 🏃‍♂️ Execution Commands

#### Using Maven:
```bash
# Install dependencies
mvn clean install

# Run all tests with detailed output
mvn test

# Run specific test class
mvn test -Dtest=ECommerceWorkflowTest

# Run with specific browser
mvn test -Dbrowser=chrome

# Generate reports only
mvn surefire-report:report
```

#### Using Gradle:
```bash
# Install dependencies
./gradlew build

# Run all tests
./gradlew test

# Run custom test task
./gradlew runTests

# Clean and test
./gradlew clean test
```

---

## 📊 Reports and Output

### 📁 Generated Artifacts
- **📊 HTML Report**: `reports/ZieglerAerospace_TestReport_[timestamp].html`
- **📸 Screenshots**: `screenshots/` directory with failure captures
- **📄 Test Data**: `testdata/test_results_[timestamp].csv`
- **📦 Product Data**: `testdata/product_data_[timestamp].csv`
- **🛒 Cart Data**: `testdata/cart_data_[timestamp].csv`
- **🧪 Validation Data**: `testdata/validation_results_[timestamp].csv`

### 📋 Report Contents
- 🎯 **Executive Summary** with pass/fail statistics
- 📝 **Step-by-step execution logs** with timestamps
- 📸 **Screenshots of failures** and key validation points
- 📦 **Product validation details** with pricing and stock info
- ⚡ **Performance metrics** and execution timing
- 🔍 **Error analysis** and troubleshooting information

---

## 🛡️ Error Handling & Validation

### 🔧 Robust Error Management
- 🎯 **Try-catch blocks** for all critical operations
- 🔄 **Graceful handling** of missing elements
- 🎲 **Alternative selector strategies** for element detection
- 📝 **Comprehensive logging** of failures and recoveries
- 🔍 **Smart element detection** with multiple fallback approaches

### ✅ Validation Points
- 🏠 **Homepage accessibility** and load verification
- 📂 **Category availability** and navigation validation
- 📊 **Product count assertions** (minimum 3 products)
- 🛒 **Cart item validation** with price and quantity checks
- 📝 **Form field validation** testing
- 📧 **Email format validation** verification
- 🔐 **Password confirmation** validation testing

---

## 🎯 Assignment Compliance Checklist

### ✅ Core Requirements
- ✅ **Homepage Navigation**: Automated navigation to automation test store
- ✅ **Dynamic Category Detection**: Real-time category name extraction
- ✅ **Random Product Selection**: 2 products with complete detail logging
- ✅ **Cart Workflow**: Complete cart verification and checkout process
- ✅ **Negative Testing**: Comprehensive registration validation scenarios
- ✅ **Professional Reporting**: HTML reports with screenshots and CSV export
- ✅ **Error Handling**: Robust exception management and recovery
- ✅ **POM Design**: Clean, maintainable page object implementation
- ✅ **External Data**: CSV export functionality for all test data

### 🏆 Advanced Features
- ✅ **Multi-browser Support**: Chrome and Firefox compatibility
- ✅ **Thread-safe Execution**: Concurrent test execution support
- ✅ **Smart Wait Strategies**: Dynamic waits with intelligent timeouts
- ✅ **Comprehensive Logging**: Console and report logging integration
- ✅ **Professional Branding**: Ziegler Aerospace themed reports
- ✅ **Data Persistence**: Multiple CSV exports for different data types
- ✅ **Screenshot Management**: Organized failure documentation

---

## 🔍 Technical Highlights

### 🎨 Design Patterns Used
- **Page Object Model (POM)**: Clean separation of test logic and page interactions
- **Factory Pattern**: WebDriver and PageFactory initialization
- **Singleton Pattern**: Report and data management
- **Strategy Pattern**: Multiple element detection approaches

### 🛠️ Best Practices Implemented
- **Explicit Waits**: WebDriverWait for reliable element interactions
- **Exception Handling**: Comprehensive try-catch with meaningful error messages
- **Data Encapsulation**: Proper getter/setter methods and data classes
- **Modular Design**: Reusable components and utilities
- **Clean Code**: Descriptive method names and comprehensive comments

---

## 🎓 Learning Outcomes Demonstrated

### 🔧 Technical Skills
- ✅ **Selenium WebDriver Mastery**: Advanced element detection and interaction
- ✅ **Java Programming**: Object-oriented design and exception handling
- ✅ **TestNG Framework**: Test organization and execution management
- ✅ **Maven/Gradle**: Dependency management and build automation
- ✅ **Reporting**: Professional test documentation and data export

### 🧠 Problem-Solving Skills
- ✅ **Dynamic Content Handling**: Real-time category and product detection
- ✅ **Error Recovery**: Graceful handling of unexpected scenarios
- ✅ **Data Validation**: Comprehensive verification of test results
- ✅ **Performance Optimization**: Efficient wait strategies and element detection

---

## 🏆 Assignment Success Metrics

| Requirement | Status | Implementation |
|-------------|--------|----------------|
| Homepage Navigation | ✅ **COMPLETE** | Dynamic navigation with load verification |
| Category Detection | ✅ **COMPLETE** | Real-time detection with multiple selectors |
| Product Selection | ✅ **COMPLETE** | Random selection with detailed logging |
| Cart Verification | ✅ **COMPLETE** | Complete item and pricing validation |
| Checkout Process | ✅ **COMPLETE** | Full workflow with form automation |
| Negative Testing | ✅ **COMPLETE** | Comprehensive validation scenarios |
| Professional Reporting | ✅ **COMPLETE** | HTML + CSV with screenshots |
| Error Handling | ✅ **COMPLETE** | Robust exception management |

---

## 🎯 Integrity Statement

This assignment demonstrates **original problem-solving and technical skills** developed specifically for Ziegler Aerospace's requirements. All code is **custom-developed** using industry best practices and modern automation frameworks.

**Key Differentiators:**
- 🎲 **Dynamic approach** - No hardcoded values or static test data
- 🛡️ **Production-ready code** - Comprehensive error handling and recovery
- 📊 **Professional reporting** - Enterprise-level documentation and analytics
- 🔧 **Scalable architecture** - Easily extensible for additional test scenarios

---

## 📞 Contact Information

**Developed for**: Ziegler Aerospace Talent Team  
**Assignment**: Automation Testing Internship Application  
**Framework**: Selenium + Java + TestNG + Maven  
**Completion Date**: Ready for Technical Review  

---

*This automation testing assignment showcases advanced technical skills in web automation, demonstrating readiness for challenging aerospace industry projects at Ziegler Aerospace.*