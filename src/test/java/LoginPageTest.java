import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import org.testng.Assert;
import org.testng.annotations.Test;
public class LoginPageTest {
    public WebDriver driver;
    @BeforeMethod
    public void  setFirst(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");

    }
    @AfterMethod
    public void setLast(){
        driver.close();
    }
    @Test
    public void testLoginWithEmptyInputs(){
        WebElement loginBtn = driver.findElement(By.className("login-button"));
        loginBtn.click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error-message-text")));

        WebElement errorele = driver.findElement(By.className("error-message-text"));
        String errorMsg = errorele.getText();
        Assert.assertEquals(errorMsg,"Invalid user ID","Error text with empty input fields does not match");

    }
    @Test(priority = 1)
    public void testLoginWithEmptyUserId(){
        WebElement pininput = driver.findElement(By.id("pinInput"));
        pininput.sendKeys("231225");

        WebElement loginBtn = driver.findElement(By.className("login-button"));
        loginBtn.click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error-message-text")));

        WebElement errormsg = driver.findElement(By.cssSelector("p.error-message-text"));
        String Errormsg = errormsg.getText();
        Assert.assertEquals(Errormsg,"Invalid user ID","Error text with empty User ID do not match");
    }
    @Test(priority = 2)
    public void testLoginWithEmptyPin(){
        WebElement userinput = driver.findElement(By.id("userIdInput"));
        userinput.sendKeys("142420");

        WebElement loginBtn = driver.findElement(By.className("login-button"));
        loginBtn.click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error-message-text")));

        WebElement errorele = driver.findElement(By.className("error-message-text"));
        String errorMsg = errorele.getText();
        Assert.assertEquals(errorMsg,"Invalid PIN","Error text with empty input fields does not match");
    }
    @Test(priority = 3)
    public void testLoginWithInvalidCreds(){
        WebElement userinput = driver.findElement(By.id("userIdInput"));
        userinput.sendKeys("142420");

        WebElement pininput = driver.findElement(By.id("pinInput"));
        pininput.sendKeys("123456");

        WebElement loginBtn = driver.findElement(By.className("login-button"));
        loginBtn.click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error-message-text")));

        WebElement errorele = driver.findElement(By.className("error-message-text"));
        String errorMsg = errorele.getText();
        Assert.assertEquals(errorMsg,"User ID and PIN didn't match","Error text with invalid pin do not match");
    }
    @Test(priority = 4)
    public void  testLoginWithValidCreds(){
        WebElement userinput = driver.findElement(By.id("userIdInput"));
        userinput.sendKeys("142420");

        WebElement pininput = driver.findElement(By.id("pinInput"));
        pininput.sendKeys("231225");

        WebElement loginBtn = driver.findElement(By.className("login-button"));
        loginBtn.click();

        String expurl = "https://qaebank.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlToBe(expurl));
        Assert.assertEquals(expurl,"https://qaebank.ccbp.tech/","URLs do not match");

    }
}
