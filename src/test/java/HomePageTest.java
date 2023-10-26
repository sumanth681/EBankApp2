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
public class HomePageTest {
    public WebDriver driver;
    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");
    }
    @AfterMethod
    public void setdown(){
        driver.close();
    }
    @Test(priority = 1)
    public void testHomepageHeading(){
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
        if(!(driver.getCurrentUrl().equals("https://qaebank.ccbp.tech/"))){
            System.out.println("URLs do not match");
        }
        WebElement headingEle = driver.findElement(By.cssSelector("h1.heading"));
        String headingText = headingEle.getText();
        Assert.assertEquals(headingText,"Your Flexibility, Our Excellence","Heading text does not match");
    }
    @Test(priority = 2)
    public void testLogoutFunctionality(){
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
        if(!(driver.getCurrentUrl().equals("https://qaebank.ccbp.tech/"))){
            System.out.println("URLs do not match");
        }

        WebElement logoutBtn = driver.findElement(By.className("logout-button"));
       WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout-button")));
 Assert.assertTrue(element.isDisplayed(),"logOut Button is Not Displayed");

        logoutBtn.click();
        String currUrl = driver.getCurrentUrl();
      Assert.assertEquals(currUrl,"https://qaebank.ccbp.tech/ebank/login","Login URL do not match");
    }
}
