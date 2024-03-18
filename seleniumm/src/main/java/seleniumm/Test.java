package seleniumm;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Test {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/deekshitha/Desktop/selenium_testing/chromedriver-mac-x64/chromedriver");
		WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:3000/login");
        WebDriverWait wait = new WebDriverWait(driver, 30); 
        JavascriptExecutor js = (JavascriptExecutor) driver;
        /*WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
        usernameField.sendKeys("Deekshitha");
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        passwordField.sendKeys("dee"); 
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("login")));
        loginButton.click();
        WebElement todosLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Todos")));
        todosLink.click();
        WebElement addNewTodoButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-funky")));
        addNewTodoButton.click();
        WebElement descriptionField = wait.until(ExpectedConditions.elementToBeClickable(By.className("des")));
        descriptionField.sendKeys("Complete Maths HomeWork");
        WebElement targetField = wait.until(ExpectedConditions.elementToBeClickable(By.className("tar")));
        targetField.sendKeys("11/21/2023");
        WebElement priorityField = wait.until(ExpectedConditions.elementToBeClickable(By.className("pri")));
        priorityField.sendKeys("Priority 1");
        WebElement commentsField = wait.until(ExpectedConditions.elementToBeClickable(By.className("com")));
        commentsField.sendKeys("I love Math");
        WebElement tagsField = wait.until(ExpectedConditions.elementToBeClickable(By.className("tag")));
        tagsField.sendKeys("Education");
        WebElement saveField = wait.until(ExpectedConditions.elementToBeClickable(By.className("sub")));
        saveField.click();
        
        WebElement firstTodoCard = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".flash-card .card-front")));
        firstTodoCard.click(); 
        
        

        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".flash-card .button-group .delete-button")));
        js.executeScript("arguments[0].click();", deleteButton);
        WebDriverWait wait1 = new WebDriverWait(driver, 500); 
   
        WebElement homeLink = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink.click();
        Thread.sleep(2500);  

        WebElement allTodos = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("View all Todos")));
        allTodos.click();
        Thread.sleep(5000);

        WebElement homeLink1 = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink1.click();
        Thread.sleep(2500);

        WebElement todaysTodos = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("Today")));
        todaysTodos.click();
        Thread.sleep(5000);

        WebElement homeLink2 = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink2.click();
        Thread.sleep(2500);

        WebElement upcomingTodos = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("Upcoming")));
        upcomingTodos.click();
        Thread.sleep(5000);

        WebElement homeLink3 = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink3.click();
        Thread.sleep(2500);

        WebElement filterTodos = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("View by Filters")));
        filterTodos.click();
        Thread.sleep(5000);
        
        WebElement priorityOneFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Priority 1')]")));
        priorityOneFilter.click();
        Thread.sleep(2500);
        
        WebElement priorityTwoFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Priority 2')]")));
        priorityTwoFilter.click();
        Thread.sleep(2500);
        
        WebElement priorityThreeFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Priority 3')]")));
        priorityThreeFilter.click();
        Thread.sleep(2500);
        
        WebElement priorityFourFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Priority 4')]")));
        priorityFourFilter.click();
        Thread.sleep(2500);
        
 

        WebElement homeLink4 = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink4.click();
        Thread.sleep(2500);

        WebElement tagTodos = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("View by Tags")));
        tagTodos.click();
        Thread.sleep(5000);
        
        WebElement educationFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Education')]")));
        educationFilter.click();
        Thread.sleep(2500);

        WebElement homeLink5 = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink5.click();
        Thread.sleep(2500);

        WebElement completedTodos = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("Completed Tasks")));
        completedTodos.click();
        Thread.sleep(5000);

        WebElement homeLink6 = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink6.click();
        Thread.sleep(2500);

        WebElement incompleteTodos = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("InComplete Tasks")));
        incompleteTodos.click();
        Thread.sleep(5000);

        WebElement homeLink7 = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("🏠 Home")));
        homeLink7.click();
        Thread.sleep(2500);

        WebElement overdueTodos = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("Overdue tasks")));
        overdueTodos.click();
        Thread.sleep(5000);
        
        homeLink7.click();
        Thread.sleep(2500);
        
        WebElement allTodos1 = wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("View all Todos")));
        allTodos1.click();
        Thread.sleep(5000);

        WebElement TodoCard2 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".flash-card .card-front")));
        TodoCard2.click(); 
        Thread.sleep(3000);
        
        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".flash-card .button-group .update-button")));
        js.executeScript("arguments[0].click();", updateButton);
        Thread.sleep(3000);
        
        
        WebElement updateField2 = wait.until(ExpectedConditions.elementToBeClickable(By.className("pri")));
        updateField2.sendKeys("Priority 4");
        
        WebElement saveField2 = wait.until(ExpectedConditions.elementToBeClickable(By.className("sub")));
        saveField2.click();
        
        WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
        logoutLink.click();
        Thread.sleep(2500);*/
        
        WebElement registrationLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Registration")));
        registrationLink.click();
        Thread.sleep(2500);
        
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
        usernameField.sendKeys("Sowmya");
        
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        passwordField.sendKeys("So123+");

        // Locate the confirm password input field and enter the same password to confirm
        WebElement confirmPasswordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("confirmPassword")));
        confirmPasswordField.sendKeys("So123+");

        // Locate the email input field and enter an email
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.name("mail_id")));
        emailField.sendKeys("deekshithaa127@gmail.com");

        // Locate the registration form's submit button and click on it to submit the form
        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Register']")));
        registerButton.click();
        
        
        
        
        
        
        

        
        Thread.sleep(10000);
        driver.quit();
    }

}
