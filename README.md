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

## 🗂 Project Structure

```
📁 src  
┣ 📂 main – (optional)  
┣ 📂 test  
┃ ┣ 📂 com.example.tests – Contains UI and API tests  
┃ ┗ 📂 com.example.pages – Page Object classes  
📁 target – Compiled output + test results  
📄 testng.xml – TestNG suite configuration  
📄 pom.xml – Maven configuration  
📄 README.md – This file  
```

---

## 🧪 Sample `testng.xml` Suite

The framework uses TestNG to define test suites. Here's an example used in this project:

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="Demo Suite">
    <listeners>
        <listener class-name="io.qameta.allure.testng.AllureTestNg"/>
    </listeners>

    <test name="Sainty">
        <classes>
            <class name="com.example.tests.PostTests"/>
            <class name="com.example.tests.APItests"/>
        </classes>
    </test>
</suite>
```
You can modify this file to control which test classes are executed during builds.

---

## 🧪 Adding a New Test Class

To create a new test:
1. Create a class under `src/test/java/com/example/tests/`.
2. Annotate methods with `@Test`.
3. Add it to `testng.xml` under `<classes>` section.

---

## 🐞 Logs & Debugging

- Logs are generated using Log4j and appear in the console.
- If a test fails, use the **Allure report** to see detailed steps, logs, and screenshots (if implemented).

---

## 🧾 Common Maven Commands

| Command | Description |
|---------|-------------|
| `mvn clean` | Remove compiled classes and reports |
| `mvn test` | Run all tests based on `testng.xml` |
| `mvn allure:report` | Generate Allure report HTML |
| `mvn allure:serve` | Open Allure report in browser |

---

## 🧹 Ignored Files

Your `.gitignore` prevents committing build files and Allure output:
- `target/`
- `allure-results/`
- `allure-report/`
- `.idea/`, `.vscode/`, etc.