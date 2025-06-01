# 🧪 Selenium Test Automation Framework - Amazon.eg

## 📄 Overview
This is a Selenium-based automation framework to test core functionalities of [Amazon.eg](https://www.amazon.eg). It follows the **Page Object Model (POM)** design pattern to enhance maintainability and scalability.

---

## 🚀 Technologies Used
- **Java 17** – For programming the test scripts.
- **Selenium WebDriver** – For browser automation.
- **TestNG** – For test management and execution.
- **Maven** – For build and dependency management.
- **Allure** – For generating detailed test reports.
- **Log4j2** – For logging and debugging.

---

## 🏗️ Project Structure
```bash
📦 Amazon.eg
├── 📁 src
│   ├── 📁 main
│   │   └── 📁 java
│   │       ├── 📁 Pages
│   │       └── 📁 Utilities
│   └── 📁 resources
│       └── Log4j2.properties, allure.properties
├── 📁 test
│   ├── 📁 java
│   │   ├── 📁 BaseTest
│   │   ├── 📁 Listeners
│   │   └── 📁 Tests
│   └── 📁 resources
│       └── environment.properties
├── 📁 test-outputs
│   └── Logs, Screenshots, target
├── 📄 pom.xml
├── 📄 testng.xml
└── 📄 README.md
```

---

## 📋 Test Coverage

| Test Area         | Test Case Description                          | Type         |
|------------------|------------------------------------------------|--------------|
| Sign Up          | Valid sign up                                  | Positive     |
| Sign Up          | Invalid phone / short password                 | Negative     |
| Login            | Successful login with valid creds              | Positive     |
| Login            | Login with incorrect password / user           | Negative     |
| Forget Password  | Valid email/phone -> OTP step                  | Positive     |
| Category & Product | Select toy category -> open correct product | Functional   |

---

## ⚙️ Setup Instructions

### ✅ Prerequisites
- JDK 8 or higher (preferably Java 17)
- Maven installed and configured
- IntelliJ IDEA or Eclipse

### 📥 Installation Steps
```bash
git clone https://github.com/your-username/amazon-eg-automation.git
cd amazon-eg-automation
mvn clean install
```

### 🧪 Running Tests
```bash
mvn test
```

### 📊 Generating Allure Report
```bash
allure serve test-outputs/target/allure-results
```

---

## 📛 Badges

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![TestNG](https://img.shields.io/badge/TestNG-framework-yellow)
![Maven](https://img.shields.io/badge/Maven-build-lightgrey)
![Allure](https://img.shields.io/badge/Allure-Reporting-brightgreen)

---

## 👨‍💻 Author

- **Moataz Mustafa**
- [LinkedIn](www.linkedin.com/in/moataz-mustafa-b97210229)
- Email: moatazmustafa123@gmail.com

---