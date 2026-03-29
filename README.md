# orangehrm-api-automation-iGenTech-task3
This project is an **API automation testing suite** for the [OrangeHRM](https://opensource-demo.orangehrmlive.com/) demo application.  
It is built using **Java**, **RestAssured**, and **TestNG** for automating recruitment-related API endpoints.

---

## 🛠 Tools & Technologies

- **Java 25**
- **RestAssured 5.3.0** (for API testing)
- **TestNG** (for test execution & assertions)
- **Maven** (for dependency management)
- **IntelliJ IDEA** (for development)

---

## 📝 Project Features

- **Add Candidate** via API
- **Delete Candidate** via API
- Modular methods for reusability
- Uses valid **session cookies** to handle authentication

---

## How to Run

1. Clone the repository:
```bash
git clone https://github.com/Alaa-Mahmoudd/orangehrm-api-automation-iGenTech-task3.git
2. Open the project in IntelliJ IDEA.
3. Run tests using TestNG:
mvn test

or run directly from IntelliJ using the TestNG configuration.

🔑 Notes
Authentication relies on browser session cookies, because OrangeHRM demo authentication is browser-managed.
Make sure the cookie value is valid before running tests.
Logs are printed for requests and responses for easier debugging.
📁 Project Structure
src/
 └─ test/java/
     └─ RecruitmentApiTest.java
pom.xml
README.md
💡 Author
Alaa Mahmoud
