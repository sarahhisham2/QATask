import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;

public class firstTest {
    WebDriver driver;
    @BeforeTest

    public void open()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://swinji.azurewebsites.net/account/login");
        driver.findElement(By.name("Email")).sendKeys("testregister@aaa.com");
        driver.findElement(By.name("Password")).sendKeys("Wakram_123");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, (document.body.scrollHeight-400))");
        driver.findElement(By.id("btnLogin")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test

    public void testing() throws InterruptedException {

        //Navigating to the course's creation form
        driver.findElement(By.id("btnMyCoursesList")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(By.id("btnListAddCourse")).click();


        //Entering the courses info
        driver.findElement(By.name("courseName")).sendKeys("FPGA4");

        driver.findElement(By.xpath("//*[@id=\"courseSubject\"]/option[2]")).click();

        driver.findElement(By.xpath("//*[@id=\"courseGrade\"]/option[3]")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, (document.body.scrollHeight/3))");
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException ie){
        }
        WebElement element = driver.findElement(By.xpath("//i[@class='caret pull-right' and @ng-click='$select.toggle($event)' and @role='button' and @tabindex='0']"));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ui-select-choices-row-inner']//h5[contains(text(),'nada rehan')]")));
        option.click();
        js.executeScript("window.scrollTo(0, (document.body.scrollHeight))");

        //Clicking on the save button
        driver.findElement(By.id("btnSaveAsDraftCourse")).click();
        Thread.sleep(5000);

        //to navigate to the courses list page
        driver.findElement(By.id("btnMyCoursesList")).click();

        //Searching for the course by it's exact name and compare it to the number of results and it must be equal 1
        driver.findElement(By.id("txtCourseSearch")).sendKeys("FPGA4");
        driver.findElement(By.id("btnCourseSearch")).click();
        element = driver.findElement(By.id("spnTotalSearchResultsCount"));
        String elementText = element.getText();
        Assert.assertEquals(elementText, "1");

    }

    @AfterTest
    public void close()
    {
        driver.quit();
    }
}