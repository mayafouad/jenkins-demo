# 🚀 Jenkins CI Demo Project

This project demonstrates a Continuous Integration (CI) pipeline using Jenkins for a Java Maven project with automated testing and reporting.

---

## 🧠 Tech Stack

- Java 21
- Maven
- TestNG
- Selenium
- Jenkins CI
- Allure Report
- poi-ooxml

---

## 📌 Project Features

- ✔ Automated build using Maven
- ✔ Automated test execution
- ✔ Test reporting using Allure
- ✔ Jenkins pipeline integration
- ✔ GitHub integration

---

## ⚙️ CI/CD Pipeline (Jenkins)

The pipeline performs the following steps:

1. Clone repository from GitHub
2. Build project using Maven
3. Run test cases
4. Generate Allure report
5. Publish report in Jenkins

---

## 🔧 Jenkins Requirements

Make sure Jenkins has:

- Maven installed (Global Tool Configuration)
- JDK 21 configured
- Allure Commandline tool installed
- Allure Jenkins Plugin installed

---

## 📷 Output Example

After pipeline execution:

- Jenkins shows build status
- Allure report is generated inside Jenkins dashboard
