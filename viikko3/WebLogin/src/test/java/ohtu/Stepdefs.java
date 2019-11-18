package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }

    @Given("command new user is selected")
    public void commandNewUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();   
    } 

    @Given("user with username {string} with password {string} is successfully created")
    public void userIsSuccessfullyCreated(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();

        createNewUserWith(username, password, password);
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userIsUnsuccessfullyTriedToBeCreated(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();

        createNewUserWith(username, password, password);
    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @When("nonexistent username {string} and password {string} are given")
    public void nonexistentUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordAndMatchingConfirmationAreEntered(String username, String password) {
      createNewUserWith(username, password, password);
    }

    @When("a too short username {string} and password {string} are entered")
    public void tooShortUsernameAndPasswordAreEntered(String username, String password) {
      createNewUserWith(username, password, password);
    }

    @When("valid username {string} and too short password {string} are entered")
    public void validUsernameAndTooShortPasswordAreEntered(String username, String password) {
      createNewUserWith(username, password, password);
    }
    
    @When("a valid username {string} and password {string} and non-matching password confirmation are entered")
    public void validUsernameAndPasswordAndNonMatchingConfirmationAreEntered(String username, String password) {
      createNewUserWith(username, password, password + "1");
    }
    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @Then("a new user is created")
    public void newUserIsCreated() throws Throwable {
        assertTrue(driver.getPageSource().contains("Welcome to Ohtu Application"));
    }

    @Then("user is not created and error {string} is reported")
    public void userIsNotCreatedWithError(String string) {
        assertTrue(driver.getPageSource().contains(string));
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 

    private void createNewUserWith(String username, String password, String passwordConfirm) {
        assertTrue(driver.getPageSource().contains("Create username"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirm);
        element = driver.findElement(By.name("signup"));
        element.submit();  
    }
}
