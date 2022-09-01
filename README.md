REST API Service Project
Used Technologies & Tools:
java junit5 rest-assured gradle IDEA git swagger allure

Swagger as a REST API Service Self-Documentation:
Swagger is available on by link http://localhost:9090/swagger-ui/ after Service is started. image

How To Run Service:
The repository should be cloned and Service should be started. Service runs locally on 9090 port. image

List of the Automated Tests:
 Get all books
 Get book by existing ISBN
 Get book by existing ISBN
 Get book by not existing ISBN
 Add new book
 Get all authors
 Get book by existing author
 Get book by not existing author
 Add new author
How To Run Tests
gradle clean test apiTests
Allure Report For Test Results Presentation
How to Run Allure Report
After tests are executed run the following command at Terminal

gradle allureServe
or run report using GUI image

Allure Report Results
image image

heart qa.guru
blue_heart t.me/qa_automation
