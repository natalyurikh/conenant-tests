# Covenant tests

Project is a  testing framework  for application Covenant
Find application documentation here [https://github.com/cobbr/Covenant/wiki]


## Components

- `core`: WebDriver management logic, bots
- `domain`: Business objects
- `pageobject`: Page objects
- `step`: Services for performing logical business steps

## Build & deploy
for base e2e_suite:
mvn test
for custom  suite:
mvn test -DsuiteFile=<path to suite>

## Design Patterns

Page object models [https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/]

Bot pattern [https://www.selenium.dev/documentation/test_practices/design_strategies/#bot-pattern]

## Parallelization
WebDriver is unique per thread, see @DriverManager
Every TestNG test has its own WebDriver instance, see BaseUiTest.setUp() annotated with @BeforeTest
It is assumed that parallelization is based on classes, to change use parallel="<desired_level>" in testng xml files 
and annotate BaseUiTest.setUp() with proper annotation

## Adding PageObjects
Pages should be inherited from BasePage with implementation of abstract method waitForReadiness(). Pages should not 
have any assertions

## Listener
TestListener class logs begin and end of every test/method/configuration method

## Steps
Steps/services provide logically-completed business steps, like login, create entity, etc. Service uses pageobject 
methods. Steps should not have assertions

## Tests
Test is a set of steps and verifications. Avoid to use pageobjects directly from tests to keep maintenance simple