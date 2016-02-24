import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Joe Meszar (jwm54@pitt.edu) | Cyrus Ramavarapu (crr41@pitt.edu)
 */

public class AcceptanceTests {
    
    public static void main(String[] args) {
        // test code to mess around with selenium
        WebDriver driver = new FirefoxDriver();
        
        // set a default wait time for DOM to load before doing anything
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        
        // grab the arch wiki page
        driver.get("https://wiki.archlinux.org/");
        
        // get the search box of the arch wiki page
        WebElement searchBox = driver.findElement(By.id("searchInput"));
        
        // enter "Installation Guide" into the search box
        searchBox.sendKeys("Installation Guide");
        
        // submit using the page's default submit button
        searchBox.submit();
    }
}