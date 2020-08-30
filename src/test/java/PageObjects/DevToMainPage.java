package PageObjects;

import org.openqa.selenium.WebDriver;

public class DevToMainPage {
    String url = "https://dev.to";
    WebDriver driverInPageObjectTests;

    public DevToMainPage(WebDriver driverFromPageObjectTests) {
        this.driverInPageObjectTests = driverFromPageObjectTests;
        // przypisanie zależności przeglądarki z klasy PageObjectTests
        // do przeglądarki wykorzystywanej w klasie DevToMainPage
        driverInPageObjectTests.get(url); //otwieramy stronę w przeglądarce

    }
}
