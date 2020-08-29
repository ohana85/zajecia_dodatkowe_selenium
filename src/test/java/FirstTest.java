import javafx.beans.property.ReadOnlySetProperty;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class FirstTest {
    WebDriver driver; //inicjalizacja pustej przeglądarki

    public void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');", element);
    }

    @Before //sekcja before, a właściwie metoda setup, wykona się przed każdym testem
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hania\\Desktop\\chromedriver_win32 (3)\\chromedriver.exe");
        //1. wskazanie chrome.driver
        driver = new ChromeDriver(); // przypisanie pustej przeglądarki chrome
        driver.manage().window().maximize();
        driver.get("https://dev.to");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // przed każdym kolejnym findElement/s poczekaj 10 sekund, zanim wywalisz error,
        // co sekundę sprawdzaj czy element jest już dostępny
    }

    @Test
    public void goToPodcastsAndSelectThirdOneAndPlayIt() {
        WebElement podcastBtn = driver.findElement((By.xpath("//a[@href='/pod']")));
        highlightElement(driver, podcastBtn);
        podcastBtn.click();
        // przejście na inna stronę z podcastami
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("https://dev.to/pod"));
        //sprawdzaj co sekundę, czy url ma wartość https://dev/to/pod,
        // jeśli w ciągu 10 sekund nie ma takiej wartości - wywal błąd
        List<WebElement> podcasts = driver.findElements(By.tagName("h3"));
//        for (WebElement podcast : podcasts) {
//            highlightElement(driver, podcast);
//        }
        WebElement thirdPodcast = podcasts.get(2);
        thirdPodcast.click();
        WebElement recordButton = driver.findElement(By.className("record-wrapper"));
        recordButton.click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("status-message"))));

        String recordBtnClassAttribute = recordButton.getAttribute("class");

        boolean isPlaying = recordBtnClassAttribute.contains("playing");

        assertTrue("podcast wasn't played", isPlaying);
    }

    @Test
    public void secondTest() {
    }
}