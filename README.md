# Test Automation using Selenium with Java and BDD -Cucumber 

## Steps to run automated tests

* Download Java JDK 1.8 or higher.
* Setup the environment variable **JAVA_HOME** and configure the same under the **Path** variable
* Download Apache Maven and configure **MAVEN_HOME** environment variable for the same and add it into existing “Path” environment variable 
* Install Eclipse IDE any other IDE of your choice.
* Clone/Download the project and import the  maven project in Eclipse IDE workspace. 
  git clone `https://github.com/himanshu323/web-test.git`
* Update the maven project , just to make ensure that maven has downloaded all the necessary dependencies in your local repository from maven central.( `Cucumber_Test -> Right Click -> Maven -> Update Project`)
* Open command prompt and navigate to project directory, run `mvn clean test` to start the automation execution OR right click on the project in your IDE->Run As->Maven Test.
* Test NG File located inside the testngXml folder will be executed `testngXml/webTestsCucumber.xml`
* After the test run finishes, Masterthought report can be accessed from location **“./Masterthought/feature-overview.html”**  


## Features Included in the Framework

* **Cucumber** is used as the BBD framework. Feature File Location `src/test/resouces/featureFiles/guardianNewsValidation.feature`
and Runner File Location `src/test/java/runners/GuardianNewsValidationTest.java`
* **TestNG** is used as the testing framework/runner
* **Maven** is used as the build tool for downloading all the necessary dependencies
* TestNG assertions for verifying the test results
* External data such as browserName, guardian and guardian url are referred from properties file `src/test/resouces/properties/config.properties`
* **Page Factory Model** is used as the design pattern
* Support for `Cross Browser Testing` as well as `Cross-platform`
* Added `Logging` for tests using Log4J. Logs can be referred from `./application.log` file located at the root directory
* **Masterthought Report** is used for reporting , test logs and also added screenshot capture functionality within report in case of failures `./Masterthought/feature-overview.html`.

