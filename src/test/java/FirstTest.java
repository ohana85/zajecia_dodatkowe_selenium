import javafx.beans.property.ReadOnlySetProperty;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    WebDriver driver; //inicjalizacja pustej przeglądarki

    public void highlightElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');", element);
    }

    @Before //sekcja before, a właściwie metoda setup, wykona się przed każdym testem
    public void setup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\hania\\Desktop\\chromedriver_win32 (3)\\chromedriver.exe");
        //1. wskazanie chrome.driver
        driver = new ChromeDriver(); // przypisanie pustej przeglądarki chrome
        driver.manage().window().maximize();
        driver.get("https://dev.to");
    }

    @Test
    public void firstTest(){
        WebElement podcastBtn = driver.findElement((By.xpath("//a[@href='/pod']")));
        highlightElement(driver,podcastBtn);
    }

    @Test
    public void secondTest(){


    }
}
