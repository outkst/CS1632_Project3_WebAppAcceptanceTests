import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;

/**
 * @author Joe Meszar (jwm54@pitt.edu) | Cyrus Ramavarapu (crr41@pitt.edu)
 */

/**
 * Arch Login: Cs1632archtest 
 * Password: archtest
 */ 


public class AcceptanceTestsTest {

    private static FirefoxDriver driver;
    private static WebElement element;
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
        element = driver.findElement(By.id("searchInput"));

        // enter "Installation Guide" into the search box
        element.sendKeys("Installation Guide");

        // submit using the page's default submit button
        element.submit();

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
        System.out.printf("menuBarExistence: Testing that the menu bar exists on the main page...\n");
        String menuBarName = "archnavbarlist";

        // go to the main arch wiki page
        driver.get("https://wiki.archlinux.org/");

        // try to grab the ul with nav bar
        boolean menuBarExists = !driver.findElements(By.id(menuBarName)).isEmpty();
        assertTrue(String.format("The menu bar '%s' cannot be found.", menuBarName), menuBarExists);
    }

    /**
     * Make sure that the Menu bar on the main page has the items
     *      "Home" "Packages" "Forums" "Wiki" "Bugs" "AUR" "Download"
     */
    @Test
    public void menuBarCorrectness() {
        // announce test and build expectations
        System.out.printf("menuBarCorrectness: Testing that the menu bar exists and has the 7 correct elements...\n");
        String menuBarName = "archnavbarmenu";
        String menuBarXPath = String.format("//div[@id='%s']/ul/li", menuBarName);

        // go to the main arch wiki page
        driver.get("https://wiki.archlinux.org/");

        // grab unordered list of nav bar items
        try {
            // grab unordered list and put it in a list object
            List<WebElement> allElements = driver.findElements(By.xpath(menuBarXPath));
            
            // check that the nav bar has 7 elements	
            assertEquals(allElements.size(), 7);

            // check that each of the 7 elements has the correct text
            assertEquals(String.format("The menu bar item '%s' cannot be found.", "Home"), allElements.get(0).getText(), "Home");
            assertEquals(String.format("The menu bar item '%s' cannot be found.", "Packages"), allElements.get(1).getText(), "Packages");
            assertEquals(String.format("The menu bar item '%s' cannot be found.", "Forums"), allElements.get(2).getText(), "Forums");
            assertEquals(String.format("The menu bar item '%s' cannot be found.", "Wiki"), allElements.get(3).getText(), "Wiki");
            assertEquals(String.format("The menu bar item '%s' cannot be found.", "Bugs"), allElements.get(4).getText(), "Bugs");
            assertEquals(String.format("The menu bar item '%s' cannot be found.", "AUR"), allElements.get(5).getText(), "AUR");
            assertEquals(String.format("The menu bar item '%s' cannot be found.", "Download"), allElements.get(6).getText(), "Download");
            
        } catch (NoSuchElementException ex) {
            fail(String.format("The menu bar titled '%s' could not be located.", menuBarName));
        }
        
        // test complete
        System.out.println("\tmenuBarCorrectness: Success!");
    }
    
    /**
     * Make sure the username input, password input, "remember me" checkbox, 
     *      and submit button exists for proper creation of account.
     */
    @Test
    public void verifyLoginForm() {
        // announce test and build expectations
        System.out.println("verifyLoginForm: Testing that the username/password/'remember me'/submit controls exist...");
        String username = "wpName1";
        String password = "wpPassword1";
        String rememberMe = "wpRemember";
        String login = "wpRemember";

        // go to the main arch wiki page
        driver.get("https://wiki.archlinux.org/index.php/Special:UserLogin");

        boolean elemExists;
        // see if the username control exists
        elemExists = !driver.findElements(By.id(username)).isEmpty();
        assertTrue(String.format("Cannot find the username control '%s'.", username), elemExists);
        
        // see if the password control exists
        elemExists = !driver.findElements(By.id(password)).isEmpty();
        assertTrue(String.format("Cannot find the password control '%s'.", password), elemExists);
        
        // see if the "Remember Me" control exists
        elemExists = !driver.findElements(By.id(rememberMe)).isEmpty();
        assertTrue(String.format("Cannot find the 'remember me' control '%s'.", rememberMe), elemExists);
        
        // see if the Login button control exists
        elemExists = !driver.findElements(By.id(login)).isEmpty();
        assertTrue(String.format("Cannot find the login button control '%s'.", login), elemExists);
        
        // test complete
        System.out.println("\tverifyLoginForm: Success!");
    }
    
    /**
     * Tests to make sure the search results container is shown when a
     *      fuzzy search is performed.
     */
    @Test
    public void searchResultsContainerExists() {
        // announce test and build expectations
        System.out.println("searchResultsContainerExists: Testing the search results container is shown...");
        String searchKeyword = "caveats";
        String searchContainer = "mw-search-results";

        // go to the main arch wiki page
        driver.get("https://wiki.archlinux.org/");

        // get the search box of the arch wiki page
        element = driver.findElement(By.id("searchInput"));

        // enter the search keyword into the search box
        element.sendKeys(searchKeyword);

        // submit using the page's default submit button
        element.submit();
        
        boolean elemExists;
        // see if the search container exists
        elemExists = !driver.findElements(By.className(searchContainer)).isEmpty();
        assertTrue(String.format("Cannot find the search container '%s'.", searchContainer), elemExists);
        
        // test complete
        System.out.println("\tsearchResultsContainerExists: Success!");
    }

	/**
	 * USER STORY:
	 *		AS A USER.
	 *		I WOULD LIKE TO GO FROM ARCH WIKI TO MAIN ARCH PAGE IN A NEW TAB
	 *		IN ORDER TO DOWNLOAD ARCHLINUX
	 */

	/**
	 * Make sure that the ArchLinux logo exists on the main page
	 */
	@Test
	public void archLogoExistence() {
        // announce test and build expectations
        System.out.printf("archLogoExistence: Testing that the logo exists on the main page...\n");
		String logoID = "logo";
		
		// go to the main wiki page
		driver.get("https://wiki.archlinux.org/");

		// check if the logo exists
		boolean logoExists = !driver.findElements(By.id(logoID)).isEmpty();	
		
		assertTrue(String.format("Cannot find the Arch Linux Logo at id '%s'.", logoID), logoExists);
	}

	/**
	 * Make sure that the ArchLinux logo when clicked takes to user to ArchLinux.org
	 */
	@Test
	public void archLogoCorrectness() {
        // announce test and build expectations
        System.out.printf("archLogoExistence: Testing that the logo exists on the main page...\n");
		String logoID = "logo";
		String expected_url = "https://www.archlinux.org/";	

		// go to the main wiki page
		driver.get("https://wiki.archlinux.org/");

		// check if the logo exists
		try {	
			driver.findElement(By.id(logoID)).click();
			String current_url = driver.getCurrentUrl();
			assertTrue(String.format("Arch logo did not navigate user to : '%s'", expected_url), expected_url.equals(current_url)); 	
		} catch (NoSuchElementException ex) {
			fail(String.format("The Arch Linux Logo was not found at '%s'", logoID));
		}
	}

	/**
	 * USER STORY:
	 * 		AS A USER.  
	 *		I WOULD LIKE TO SEE A PAGE'S HISTORY.
	 *		IN ORDER TO CHECK MOST RECENT REVISION.
	 */

	/**
  	 * Make sure that clicking the history tab takes user to revision history for the page
	 */	 
	@Test
	public void checkHistoryExistence() {
		// announce test and build expectations
        System.out.printf("historyTabExistence: Testing that the history tab  exists on the MTP page...\n");
		String link_name = "history";
		String page_url = "https://wiki.archlinux.org/index.php/MTP";

		// go to the mtp page on the arch wiki
		driver.get(page_url);
		
		boolean history_existence =!driver.findElements(By.linkText(link_name)).isEmpty();
		
		assertTrue(String.format("The '%s' tab was not found on '%s'", link_name, page_url), history_existence);
	}
	
	/**
     * This main method runs our test suite
     * 
     * @param args **not used**
     */
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("AcceptanceTestsTest");
    }
}
