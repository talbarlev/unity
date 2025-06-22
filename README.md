# Automated Testing Framework – Java + TestNG + Selenium + Allure

This project contains automated UI and API tests using Java, TestNG, Selenium WebDriver, RestAssured, and Allure Reports.

---

## ✅ Tech Stack

- **Selenium WebDriver** – UI automation
- **RestAssured** – API testing
- **TestNG** – Test runner and scenario management
- **Allure Reports** – Interactive test reporting
- **WebDriverManager** – Manages browser drivers
- **Jackson / Gson** – JSON serialization
- **Log4j** – Logging

---

## 📦 How to Install

```bash
git clone <your-repo-url>
cd selenium-test
mvn clean install
```

---

## ▶️ How to Run Tests

```bash
mvn test
```

This uses the `testng.xml` file to run both API and UI tests.

---
## 📊 How to View Allure Report

1. After tests run:
   ```bash
   mvn allure:report
   ```

2. To open the report in browser:
   ```bash
   mvn allure:serve
   ```

> Make sure Allure is installed on your system:
```bash
npm install -g allure-commandline --save-dev
# Or using Chocolatey (Windows):
choco install allure
```

---

## 🧪 Sample `testng.xml`

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="Demo Suite">
    <listeners>
        <listener class-name="io.qameta.allure.testng.AllureTestNg"/>
    </listeners>

    <test name="Sanity">
        <classes>
            <class name="com.example.tests.PostTests"/>
            <class name="com.example.tests.APItests"/>
        </classes>
    </test>
</suite>
```

---
pom.xml
README.md
```