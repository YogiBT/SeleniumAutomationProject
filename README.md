# Selenium Automation Project

## Overview
This project is designed to automate web application testing using Selenium WebDriver. It includes test cases for various scenarios and uses TestNG for test management and reporting.

## Project Structure
- `src/main/java`: Contains the main application code.
- `src/test/java`: Contains the test cases and test utilities.
- `pom.xml`: Maven configuration file for managing dependencies.

## Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- IntelliJ IDEA or any other preferred IDE

## Setup
1. Clone the repository:
    ```sh
    git clone https://github.com/YogiBT/SeleniumAutomationProject.git
    ```
2. Navigate to the project directory:
    ```sh
    cd SeleniumAutomationProject
    ```
3. Install the dependencies:
    ```sh
    mvn clean install
    ```

## Running Tests
To run the tests, use the following command:
```sh
mvn test
```
## Development Environment
This project was developed on a macOS system using IntelliJ IDEA 2024.3.1.  
## Java Version
This project was created using JAVA 23.0.2.
Ensure you have the correct Java version installed by running:
```sh
java -version
```

# Test Files
### SearchAndVerifyAfterLoginTest.java
This test file contains test cases related to searching for products and verifying the cart after login. It includes methods to:  
* Verify the product in the cart.
* Verify the product in the cart after login.
* Take screenshots after each test method.
* Clean up the test environment by quitting the WebDriver.

### TestCasesPageTest.java
This test file contains test cases related to the Test Cases Page. It includes methods to:  
* Verify the home page arrival.
* Verify that the home page is visible.
* Verify the arrival to the test case page.
* Take screenshots after each test method.
* Clean up the test environment by quitting the WebDriver.

### ProductSearchTest.java
This test file contains test cases related to searching for products. It includes methods to:  
* Verify the search functionality.
* Verify the search results.
* Take screenshots after each test method.
* Clean up the test environment by quitting the WebDriver.

### ContactUsFormPageTest.java
This test file contains test cases related to the Contact Us form. It includes methods to:  
* Verify the Contact Us form page.
* Submit the Contact Us form with valid data.
* Submit the Contact Us form with invalid data.
* Take screenshots after each test method.
* Clean up the test environment by quitting the WebDriver.
### ScrollUpAndDownTest.java
This test file contains test cases related to scrolling up and down on the web page. It includes methods to:  
* Verify scrolling up functionality.
* Verify scrolling down functionality.
* Take screenshots after each test method.
* Clean up the test environment by quitting the WebDriver.

## Page Files
### HomePage.java
This page file contains methods to interact with the home page of the application. It includes methods to:  
* Navigate to the home page.
* Verify the visibility of elements on the home page.
### LoginPage.java
This page file contains methods to interact with the login page of the application. It includes methods to:  
* Enter login credentials.
* Submit the login form.
* Verify login success or failure.
### ProductPage.java
This page file contains methods to interact with the product page of the application. It includes methods to:  
* Search for products.
* Add products to the cart.
* Verify product details.

## Contributing
Fork the repository.
Create a new branch (git checkout -b feature-branch).
Commit your changes (git commit -am 'Add new feature').
Push to the branch (git push origin feature-branch).
Create a new Pull Request.
License
This project is licensed under the MIT License.


