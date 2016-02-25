import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author Joe Meszar (jwm54@pitt.edu) | Cyrus Ramavarapu (crr41@pitt.edu)
 */
public class AcceptanceTestsTest {

    private static FirefoxDriver driver;
    WebElement element;
    private static final int implicitWaitTime = 2; //seconds

    /**
     * Before any test is run, start up the Browser instance and set a default wait time between page DOM loading.
     */
    @BeforeClass
    public static void setUpClass() {
        // create a new FireFox driver instance
        driver = new FirefoxDriver();

        // set a default time for Selenium to wait before
        //      considering any operation a failure
        driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
    }

    /**
     * Close the browser instance after all of the testing is complete.
     */
    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }

    /**
     * Make sure the home page is reachable and correct.
     */
    @Test
    public void visitMainPage() {
        // announce test and build expectations
        System.out.println("visitMainPage: Testing that we are on the main Archi Wiki page...");
        String expectedHeaderText = "Main page";

        // go to the main arch wiki page
        driver.get("https://wiki.archlinux.org/");

        // grab the header telling exactly what page is being shown
        element = driver.findElement(By.id("firstHeading"));

        // grab the header text
        String actualHeaderText = element.getText();

        // make sure the header text says 'Main page'
        assertEquals(String.format("Page says '%s'; expected '%s'", actualHeaderText, expectedHeaderText), actualHeaderText, expectedHeaderText);

        // test complete
        System.out.println("\tvisitMainPage: Success!");
    }

    /**
     * Use the search box and look for the "Installation Guide".
     */
    @Test
    public void searchForInstallationGuide() {
        // announce test and build expectations
        System.out.println("searchForInstallationGuide: Testing search box for the 'Installation Guide'");
        String expectedHeaderText = "Installation guide";

        // go to the main arch wiki page
        driver.get("https://wiki.archlinux.org/");

        // get the search box of the arch wiki page
        WebElement searchBox = driver.findElement(By.id("searchInput"));

        // enter "Installation Guide" into the search box
        searchBox.sendKeys("Installation Guide");

        // submit using the page's default submit button
        searchBox.submit();

        // grab the header telling exactly what page is being shown
        element = driver.findElement(By.id("firstHeading"));

        // grab the header text
        String actualHeaderText = element.getText();

        // make sure the header text says 'Main page'
        assertEquals(String.format("Page says '%s'; expected '%s'", actualHeaderText, expectedHeaderText), actualHeaderText, expectedHeaderText);

        // test complete
        System.out.println("\tsearchForInstallationGuide: Success!");
    }

    /**
     * Make sure the div container exists for the account controls.
     */
    @Test
    public void loginControlsDivExists() {
        // announce test and build expectations
        System.out.println("loginControlsDivExists: Testing that the div container for the account controls exists...");
        String divClassName = "pBody";

        // go to the main arch wiki page
        driver.get("https://wiki.archlinux.org/");

        // try to grab the div that contains the account controls
        boolean divExists = !driver.findElements(By.className(divClassName)).isEmpty();
        assertTrue(String.format("The div '%s' that holds the account controls cannot be found.", divClassName), divExists);

        // test complete
        System.out.println("\tloginControlsDivExists: Success!");
    }

    /**
     * Make sure the login button exists, if the user is NOT logged-in currently. (First checks if the div container for
     * the account controls exists).
     */
    @Test
    public void loginButtonExists() {
        // announce test and build expectations
        System.out.println("loginButtonExists: Testing that the login button exists on the page...");
        String loginButtonName = "pt-login";

        // go to the main arch wiki page
        driver.get("https://wiki.archlinux.org/");

        // first make sure the div exists for the account controls
        System.out.println("\tTesting the the div container exists that holds the login button...");
        loginControlsDivExists();
        System.out.println("\tThe div container exists, continuing test...");

        // the div exists, so see if the login button exists
        boolean loginControlExists = !driver.findElements(By.id(loginButtonName)).isEmpty();
        assertTrue(String.format("The login control '%s' cannot be found.", loginButtonName), loginControlExists);

        if (loginControlExists) {
            assertTrue(loginControlExists);

            // test complete
            System.out.println("\tloginButtonExists: Success!");

        } else {
            // maybe the login control doesn't exist because we are LOGGED-IN!

            // get Cyrus to log in and inspect page for the "LOG OUT" button ID name
            String logoutButtonName = "pt-logout"; // assuming button id name is pt-logout

            boolean logoutControlExists = !driver.findElements(By.id(logoutButtonName)).isEmpty();
            if (logoutControlExists) {
                // DO NOT FAIL THIS TEST, we are logged-in so the control should NOT exist
                System.out.println("\tloginButtonExists: SKIPPING! (Currently logged-in so no login control will exist.)");

            } else {
                assertTrue(String.format("The login control '%s' cannot be found.", loginButtonName), loginControlExists);
            }
        }
    }

	/**
  	 * Make sure that Menu bar is available on the main page 
	 */
	@Test
	public void menuBarExistence() {
		// announce test and build expectations
		System.out.printf("menuBarExistence: Testing that the login button exists on the main page...\n");
		String menuBarName = "archnavbarlist";
		
		// go to the main arch wiki page
		driver.get("https://wiki.archlinux.org/");
		
		// try to grab the div with nav bar
		boolean menuBarExists = !driver.findElements(By.id(menuBarName)).isEmpty();
		assertTrue(String.format("The menu bar '%s' cannot be found.", menuBarName), menuBarExists);
	
	}	

	// This main method runs our test suite
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("AcceptanceTestsTest");
	}

}
