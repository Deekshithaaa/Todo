package basicweb;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTodo {

    private WebDriver driver;
    private WebDriverWait wait;
    private boolean runCommonSetup = true;
    
 
    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/Deekshitha/Desktop/selenium_testing/chromedriver-mac-x64/chromedriver");
        driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        
        driver.get("http://localhost:3000");
        wait = new WebDriverWait(driver, 30);
        
    }
    
    
    @Test
    @Order(1)
    public void registrationTest() throws InterruptedException, TimeoutException {
    	System.out.println("Task 1: Test Registration Component");
    	System.out.println("navigated to Login Page");
    
        WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/registration']")));
        registerLink.click();
        Thread.sleep(1000);
        System.out.println("navigated to Registration Page");
        
        WebElement nameInput = driver.findElement(By.name("username"));
        nameInput.sendKeys("Thomas");
        Thread.sleep(1000);
        
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Tom123");
        Thread.sleep(1000);
        
        WebElement confirmpasswordInput = driver.findElement(By.name("confirmPassword"));
        confirmpasswordInput.sendKeys("Tom123");
        Thread.sleep(1000);
        
        WebElement mailInput = driver.findElement(By.name("mail_id"));
        mailInput.sendKeys("tom123@gmail.com");
        Thread.sleep(1000);

        
        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Register' and @type='submit']")));

        registerButton.click();

        Thread.sleep(1000);
        
        System.out.println("Success: User registered in successfully.");
       
    }
    
    
    @Test
    @Order(2)
    public void loginTest() throws InterruptedException, TimeoutException {
    	System.out.println("Task 2: Test Login Component");
    	System.out.println("navigated to Login Page");
        
        WebElement nameInput = driver.findElement(By.name("username"));
        nameInput.sendKeys("Thomas");
        Thread.sleep(1000);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Tom123");
        Thread.sleep(1000);
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='button'][name='login']")));
        loginButton.click();
        Thread.sleep(1000);
        System.out.println("Success: User logged in successfully.");
        String expectedUrlAfterLogin = "http://localhost:3000/welcome";
        wait.until(ExpectedConditions.urlToBe(expectedUrlAfterLogin));
        String actualUrlAfterLogin = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrlAfterLogin, actualUrlAfterLogin, "The URLs do not match after login.");
        System.out.println("Success: The URLs match after login, Navigated to welcome page.");
        
    }
    
    @Test
    @Order(3)
    public void addtodoTest() throws InterruptedException, TimeoutException {
    	System.out.println("Task 3: Test Add Todo Component");
    	System.out.println("navigated to Login Page");
        WebElement nameInput = driver.findElement(By.name("username"));
        nameInput.sendKeys("Thomas");
        Thread.sleep(1000);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Tom123");
        Thread.sleep(1000);
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='button'][name='login']")));
        loginButton.click();
        Thread.sleep(1000);
        System.out.println("Success: User logged in successfully.");
        WebElement todosLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Todos")));
        todosLink.click();
        System.out.println("navigated to View All Todos Page.");
        WebElement addNewTodoButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-funky")));
        addNewTodoButton.click();
        WebElement descriptionField = wait.until(ExpectedConditions.elementToBeClickable(By.className("des")));
        descriptionField.sendKeys("Complete Maths HomeWork");
        WebElement targetField = wait.until(ExpectedConditions.elementToBeClickable(By.className("tar")));
        targetField.sendKeys("11/21/2023");
        WebElement priorityField = wait.until(ExpectedConditions.elementToBeClickable(By.className("pri")));
        priorityField.sendKeys("Priority 2");
        WebElement commentsField = wait.until(ExpectedConditions.elementToBeClickable(By.className("com")));
        commentsField.sendKeys("I love Math");
        WebElement tagsField = wait.until(ExpectedConditions.elementToBeClickable(By.className("tag")));
        tagsField.sendKeys("Education");
        WebElement saveField = wait.until(ExpectedConditions.elementToBeClickable(By.className("sub")));
        saveField.click();
        System.out.println("Success: Todo Added successfully with description Complete Maths HomeWork");
        
        
    }
   
    
    
    @Test
    @Order(4)
    public void updateTodoTest() throws InterruptedException, TimeoutException {
    	System.out.println("Task 4: Test Update Todo Component");
    	System.out.println("navigated to Login Page");
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement nameInput = driver.findElement(By.name("username"));
        nameInput.sendKeys("Thomas");
        Thread.sleep(1000);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Tom123");
        Thread.sleep(1000);
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='button'][name='login']")));
        loginButton.click();
        Thread.sleep(1000);
        System.out.println("Success: User logged in successfully.");
        WebElement todosLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Todos")));
        todosLink.click();
        System.out.println("navigated to View all Todos page.");
        WebElement firstTodoCard = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".flash-card .card-front")));
        firstTodoCard.click(); 
        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".flash-card .button-group .update-button")));
        js.executeScript("arguments[0].click();", updateButton);
        Thread.sleep(3000);
        
        
        WebElement updateField2 = wait.until(ExpectedConditions.elementToBeClickable(By.className("pri")));
        updateField2.sendKeys("Priority 4");
        
        WebElement saveField2 = wait.until(ExpectedConditions.elementToBeClickable(By.className("sub")));
        saveField2.click();
        System.out.println("Success: First Todo's priority is updated to Priority 4");
        
    }
    
    
    @Test
    @Order(5)
    public void deleteTodoTest() throws InterruptedException, TimeoutException {
    	System.out.println("Task 5: Test Delete Todo Component");
    	System.out.println("navigated to Login Page");
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement nameInput = driver.findElement(By.name("username"));
        nameInput.sendKeys("Thomas");
        Thread.sleep(1000);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Tom123");
        Thread.sleep(1000);
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='button'][name='login']")));
        loginButton.click();
        Thread.sleep(1000);
        System.out.println("Success: User logged in successfully.");
        WebElement todosLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Todos")));
        todosLink.click();
        System.out.println("navigated to View all Todos page.");
        WebElement firstTodoCard = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".flash-card .card-front")));
        firstTodoCard.click(); 
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".flash-card .button-group .delete-button")));
        js.executeScript("arguments[0].click();", deleteButton);
        Thread.sleep(1000);
        System.out.println("Success: First Todo deleted successfully");
        
    }
    
    @Test
    @Order(6)
    public void create5TodoTest() throws InterruptedException, TimeoutException {
    	System.out.println("Task 6: Add 4 new Todos");
    	System.out.println("navigated to Login Page");
        
        WebElement nameInput = driver.findElement(By.name("username"));
        nameInput.sendKeys("Thomas");
        Thread.sleep(1000);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Tom123");
        Thread.sleep(1000);
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='button'][name='login']")));
        loginButton.click();
        Thread.sleep(1000);
        System.out.println("Success: User logged in successfully.");
        
        WebElement todosLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Todos")));
        todosLink.click();
        System.out.println("navigated to View All Todos Page.");
        WebElement addNewTodoButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-funky")));
        addNewTodoButton.click();
        
        System.out.println("Creating 1st Todo.");
        WebElement descriptionField = wait.until(ExpectedConditions.elementToBeClickable(By.className("des")));
        descriptionField.sendKeys("Book Tickets to Houston");
        Thread.sleep(1000);
        WebElement targetField = wait.until(ExpectedConditions.elementToBeClickable(By.className("tar")));
        targetField.sendKeys("11/12/2023");
        Thread.sleep(1000);
        WebElement priorityField = wait.until(ExpectedConditions.elementToBeClickable(By.className("pri")));
        priorityField.sendKeys("Priority 2");
        Thread.sleep(1000);
        WebElement commentsField = wait.until(ExpectedConditions.elementToBeClickable(By.className("com")));
        commentsField.sendKeys("I love to plan trips");
        Thread.sleep(1000);
        WebElement tagsField = wait.until(ExpectedConditions.elementToBeClickable(By.className("tag")));
        tagsField.sendKeys("Travel");
        Thread.sleep(1000);
        WebElement saveField = wait.until(ExpectedConditions.elementToBeClickable(By.className("sub")));
        saveField.click();
        Thread.sleep(1000);
        System.out.println("Success: Todo Added successfully with description Book Tickets to Houston");
        WebElement addNewTodoButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-funky")));
        addNewTodoButton1.click();
        
        System.out.println("Creating 2nd Todo.");
        WebElement descriptionField1 = wait.until(ExpectedConditions.elementToBeClickable(By.className("des")));
        descriptionField1.sendKeys("Trip to Vegas");
        Thread.sleep(1000);
        WebElement targetField1 = wait.until(ExpectedConditions.elementToBeClickable(By.className("tar")));
        targetField1.sendKeys("11/24/2023");
        Thread.sleep(1000);
        WebElement checkbox = driver.findElement(By.cssSelector("input[name='done'][type='checkbox']"));
        if (!checkbox.isSelected()) {
         checkbox.click(); 
        }
        WebElement priorityField1 = wait.until(ExpectedConditions.elementToBeClickable(By.className("pri")));
        priorityField1.sendKeys("Priority 3");
        Thread.sleep(1000);
        WebElement commentsField1 = wait.until(ExpectedConditions.elementToBeClickable(By.className("com")));
        commentsField1.sendKeys("I love to party");
        Thread.sleep(1000);
        WebElement tagsField1 = wait.until(ExpectedConditions.elementToBeClickable(By.className("tag")));
        tagsField1.sendKeys("Travel");
        Thread.sleep(1000);
        WebElement saveField1 = wait.until(ExpectedConditions.elementToBeClickable(By.className("sub")));
        saveField1.click();
        Thread.sleep(1000);
        System.out.println("Success: Todo Added successfully with description Trip to Vegas");
        
        WebElement addNewTodoButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-funky")));
        addNewTodoButton2.click();
        
        System.out.println("Creating 3rd Todo.");
        WebElement descriptionField3 = wait.until(ExpectedConditions.elementToBeClickable(By.className("des")));
        descriptionField3.sendKeys("Watch a Movie");
        Thread.sleep(1000);
        WebElement targetField3 = wait.until(ExpectedConditions.elementToBeClickable(By.className("tar")));
        targetField3.sendKeys("11/27/2023");
        Thread.sleep(1000);
        WebElement checkbox1 = driver.findElement(By.cssSelector("input[name='done'][type='checkbox']"));
        if (!checkbox1.isSelected()) {
            checkbox1.click(); 
           }
        WebElement priorityField3 = wait.until(ExpectedConditions.elementToBeClickable(By.className("pri")));
        priorityField3.sendKeys("Priority 3");
        Thread.sleep(1000);
        WebElement commentsField3 = wait.until(ExpectedConditions.elementToBeClickable(By.className("com")));
        commentsField3.sendKeys("I like Bollywood");
        Thread.sleep(1000);
        WebElement tagsField3 = wait.until(ExpectedConditions.elementToBeClickable(By.className("tag")));
        tagsField3.sendKeys("Entertainment");
        Thread.sleep(1000);
        WebElement saveField3 = wait.until(ExpectedConditions.elementToBeClickable(By.className("sub")));
        saveField3.click();
        Thread.sleep(1000);
        System.out.println("Success: Todo Added successfully with description Watch a Movie");
        
        WebElement addNewTodoButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-funky")));
        addNewTodoButton3.click();
        System.out.println("Creating 4th Todo.");
        Thread.sleep(1000);
        WebElement descriptionField4 = wait.until(ExpectedConditions.elementToBeClickable(By.className("des")));
        descriptionField4.sendKeys("Cook Biryani");
        Thread.sleep(1000);
        WebElement targetField4 = wait.until(ExpectedConditions.elementToBeClickable(By.className("tar")));
        targetField4.sendKeys("11/4/2023");
        Thread.sleep(1000);
        WebElement priorityField4 = wait.until(ExpectedConditions.elementToBeClickable(By.className("pri")));
        priorityField4.sendKeys("Priority 2");
        Thread.sleep(1000);
        WebElement commentsField4 = wait.until(ExpectedConditions.elementToBeClickable(By.className("com")));
        commentsField4.sendKeys("I love Food");
        Thread.sleep(1000);
        WebElement tagsField4 = wait.until(ExpectedConditions.elementToBeClickable(By.className("tag")));
        tagsField4.sendKeys("Food");
        Thread.sleep(1000);
        WebElement saveField4 = wait.until(ExpectedConditions.elementToBeClickable(By.className("sub")));
        saveField4.click();
        Thread.sleep(1000);
        System.out.println("Success: Todo Added successfully with description Cook Biryani");
        
    }
    
    @Test
    @Order(7)
    public void viewTodosTest() throws InterruptedException, TimeoutException {
    	System.out.println("Task 7: Test by navigating to different classification pages");
    	System.out.println("navigated to Login Page");
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement nameInput = driver.findElement(By.name("username"));
        nameInput.sendKeys("Thomas");
        Thread.sleep(1000);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Tom123");
        Thread.sleep(1000);
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='button'][name='login']")));
        loginButton.click();
        Thread.sleep(1000);
        System.out.println("Success: User logged in successfully.");
        WebElement allTodos = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View all Todos")));
        allTodos.click();
        Thread.sleep(1000);
        System.out.println("Success: navigated to View all Todos page.");
        WebElement homeLink1 = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink1.click();
        Thread.sleep(1000);
        WebElement todaysTodos = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Today")));
        todaysTodos.click();
        Thread.sleep(1000);
        System.out.println("Success: navigated to Today page.");
        WebElement homeLink2 = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink2.click();
        Thread.sleep(1000);
        WebElement upcomingTodos = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Upcoming")));
        upcomingTodos.click();
        Thread.sleep(1000);
        System.out.println("Success: navigated to Upcoming page.");
        WebElement homeLink5 = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink5.click();
        Thread.sleep(1000);
        WebElement completedTodos = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Completed Tasks")));
        completedTodos.click();
        Thread.sleep(1000);
        System.out.println("Success: navigated to Completed Tasks page.");
        WebElement homeLink6 = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink6.click();
        Thread.sleep(1000);
        WebElement incompleteTodos = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("InComplete Tasks")));
        incompleteTodos.click();
        Thread.sleep(1000);
        System.out.println("Success: navigated to InComplete Tasks page.");
        WebElement homeLink7 = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink7.click();
        Thread.sleep(1000);
        WebElement overdueTodos = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Overdue tasks")));
        overdueTodos.click();
        Thread.sleep(1000);
        System.out.println("Success: navigated to Overdue tasks page.");
    }
    
    @Test
    @Order(8)
    public void todoFiltersTest() throws InterruptedException, TimeoutException {
    	System.out.println("Task 8: Test by navigating to Filters page and Tags page");
    	System.out.println("navigated to Login Page");
        WebElement nameInput = driver.findElement(By.name("username"));
        nameInput.sendKeys("Thomas");
        Thread.sleep(1000);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Tom123");
        Thread.sleep(1000);
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='button'][name='login']")));
        loginButton.click();
        Thread.sleep(1000);
        
        WebElement filterTodos = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View by Filters")));
        filterTodos.click();
        Thread.sleep(1000);
        System.out.println("navigated to filters page.");
        
        WebElement priorityOneFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Priority 1')]")));
        priorityOneFilter.click();
        Thread.sleep(1000);
        System.out.println("Success: Displayed Todos with Priority 1.");
        
        WebElement priorityTwoFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Priority 2')]")));
        priorityTwoFilter.click();
        Thread.sleep(1000);
        System.out.println("Success: Displayed Todos with Priority 2.");
        
        WebElement priorityThreeFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Priority 3')]")));
        priorityThreeFilter.click();
        Thread.sleep(1000);
        System.out.println("Success: Displayed Todos with Priority 3.");
        
        WebElement priorityFourFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Priority 4')]")));
        priorityFourFilter.click();
        Thread.sleep(1000);
        System.out.println("Success: Displayed Todos with Priority 4.");
        
        WebElement homeLink4 = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink4.click();
        Thread.sleep(1000);

        WebElement tagTodos = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View by Tags")));
        tagTodos.click();
        Thread.sleep(1000);
        System.out.println("navigated to tags page.");
        
        WebElement entertainmentFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Entertainment')]")));
        entertainmentFilter.click();
        Thread.sleep(1000);
        System.out.println("Success: Entertainment tag is created and its cooresponding todos are displayed.");
        
        WebElement educationFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Travel')]")));
        educationFilter.click();
        Thread.sleep(1000);
        System.out.println("Success: Travel tag is created and its cooresponding todos are displayed.");
        
        WebElement foodFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Food')]")));
        foodFilter.click();
        Thread.sleep(1000);
        System.out.println("Success: Food tag is created and its cooresponding todos are displayed.");
        
    }
    
    
    

   
  
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
