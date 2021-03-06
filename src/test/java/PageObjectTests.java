import PageObjects.DevToMainPage;
import PageObjects.DevToPodcastsPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class PageObjectTests {

    WebDriver driverInPageObjectTests; // obiekt typu WebDriver


    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hania\\Desktop\\chromedriver_win32 (3)\\chromedriver.exe");
        driverInPageObjectTests = new ChromeDriver();
        driverInPageObjectTests.manage().window().maximize();
        driverInPageObjectTests.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void test(){
        DevToMainPage devToMainPage = new DevToMainPage(driverInPageObjectTests); //tworzymy nowy obiekt klasy DevToMainPage
        //przekazujemy przeglądarkę z PageObjectTests do DevToMainPage, do konstruktora
        // konstruktor tworzy nam nowy obiekt DevToMainPage
        DevToPodcastsPage devToPodcastsPage = devToMainPage.selectPodcasts();
        devToPodcastsPage.selectPodcastFromList(2);
        devToPodcastsPage.playPodcast();

        assert ("Podcast's wasn't played",devToSinglePodcastPage.isPodcastPlaying);
    }
}
