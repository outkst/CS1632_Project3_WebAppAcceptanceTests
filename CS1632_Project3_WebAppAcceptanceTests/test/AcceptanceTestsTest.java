
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

    /******************************************************************************************************************
     * USER STORY:  AS A USER.
     *              I WOULD LIKE TO GO TO ARCH WIKI. 
     *              IN ORDER TO FIND AN INSTALLATION GUIDE.
     */
    /**
     * GIVEN that I have a browser open 
     * WHEN I enter https://wiki.archlinux.org 
     * THEN I will be on the archlinux wikipedia homepage
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
     * GIVEN that I am on the archlinux home page 
     * WHEN I search for a begginer guide 
     * AND I press the search button 
     * THEN I should be on the Beginners Guide Page
     */
    @Test
    public void searchForBeginnersGuide() {
        // announce test and build expectations
        System.out.println("searchForBeginnersGuide: Testing search box for the 'Beginners' Guide'");
        String searchKeyword = "Beginners' guide";

        try {
            // use helper method
            searchForKeyword(searchKeyword);

        } catch (NoSuchElementException ex) {
            fail();

        } catch (WebDriverException wdEx) {
            fail(String.format("Element is not clickable\n"));
        }

        // test complete
        System.out.println("\tsearchForBeginnersGuide: Success!");
    }

    /**
     * GIVEN that I am on the archlinux home page 
     * WHEN I search for a installation guide 
     * AND I press the search button
     * THEN I should be on the Installation Guide page
     */
    @Test
    public void searchForInstallationGuide() {
        // announce test and build expectations
        System.out.println("searchForInstallationGuide: Testing search box for the 'Installation Guide'");
        String searchKeyword = "Installation guide";

        try {
            // use helper method
            searchForKeyword(searchKeyword);

        } catch (Exception ex) {
            fail();
        }

        // test complete
        System.out.println("\tsearchForInstallationGuide: Success!");
    }

    /**
     * GIVEN that I am on the archlinux home page 
     * WHEN I search for irc channels 
     * AND I press the search button 
     * THEN I should be on the IRC channels page
     */
    @Test
    public void searchForIRCChannels() {
        // announce test and build expectations
        System.out.println("searchForIRCChannels: Testing search box for the 'IRC channels' list");
        String searchKeyword = "IRC channels";

        try {
            // use helper method
            searchForKeyword(searchKeyword);

        } catch (Exception ex) {
            fail();
        }

        // test complete
        System.out.println("\tsearchForIRCChannels: Success!");
    }

    /**
     * GIVEN that I am on the archlinux home page 
     * WHEN I search for forum etiquette 
     * AND I press the search button 
     * THEN I should be on the forum etiquette page
     */
    @Test
    public void searchForForumEtiquette() {
        // announce test and build expectations
        System.out.println("searchForForumEtiquette: Testing search box for the 'Forum etiquette' list");
        String searchKeyword = "Forum etiquette";

        try {
            // use helper method
            searchForKeyword(searchKeyword);

        } catch (Exception ex) {
            fail();
        }

        // test complete
        System.out.println("\tsearchForForumEtiquette: Success!");
    }

    /**
     * Helper method for running the Search tests.
     *
     * @param searchKeyword The keyword (and also the Guide name) to search for.
     */
    public void searchForKeyword(String searchKeyword) {

        // go to the main arch wiki page
        driver.get("https://wiki.archlinux.org/");

        // get the search box of the arch wiki page
        element = driver.findElement(By.id("searchInput"));

        // enter "Installation Guide" into the search box
        element.sendKeys(searchKeyword);

        // submit using the page's default submit button
        element.submit();

        // grab the header telling exactly what page is being shown
        element = driver.findElement(By.id("firstHeading"));

        // grab the header text
        String actualHeaderText = element.getText();

        // make sure the header text says 'Main page'
        assertEquals(String.format("Page says '%s'; expected '%s'", actualHeaderText, searchKeyword), actualHeaderText, searchKeyword);
    }

    /**
     * GIVEN that I am on the archlinux home page 
     * WHEN I search for an arbitary string 
     * AND I press the search button
     * THEN I should be given a list of search results
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

    /******************************************************************************************************************
     *  USER STORY: AS A MEMBER. 
     *              I WOULD LIKE TO LOGIN TO ARCHWIKI. 
     *              IN ORDER TO CHECK MEMBERSHIP.
     */
    
    /**
     * GIVEN that I am on the main page for the arch wiki 
     * WHEN look for account controls 
     * THEN I should be able to find account controls.
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
     * GIVEN that I am on the main page for arch wiki 
     * WHEN look for login button. 
     * THEN I should be able to find a login button.
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
     * GIVEN that I am on login page for arch wiki 
     * WHEN I want to login 
     * THEN there should be fields for username, password, remember login, and a button to login with.
     */
    @Test
    public void verifyLoginForm() {
        // announce test and build expectations
        System.out.println("verifyLoginForm: Testing that the username/password/'remember me'/submit controls exist...");
        String username = "wpName1";
        String password = "wpPassword1";
        String rememberMe = "wpRemember";
        String login = "wpLoginAttempt";

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
     * GIVEN that I am on login page for arch wiki 
     * WHEN I enter an invalid <username, password> pair 
     * THEN I should receive a login error. 
     */
    @Test
    public void testWrongPassWord() {
        // announce test and build expectations
        System.out.printf("testWrongPassword: Testing invalid password for login...\n");
        String login_url = "https://wiki.archlinux.org/index.php?title=Special:UserLogin&returnto=MTP&returntoquery=type%3Drevision%26diff%3D421473%26oldid%3D420213";

        String username = "wpName1";
        String password = "wpPassword1";
        String rememberMe = "wpRemember";
        String login = "wpLoginAttempt";

        WebElement username_field;
        WebElement password_field;
        WebElement login_button;

        // go to the login page for the arch wiki
        driver.get(login_url);

        // RECHECK THAT LOGIN FORM IS CORRECT
        boolean elemExists;
        // see if the username control exists
        username_field = driver.findElement(By.id(username));
        elemExists = !driver.findElements(By.id(username)).isEmpty();
        assertTrue(String.format("Cannot find the username control '%s'.", username), elemExists);

        // see if the password control exists
        password_field = driver.findElement(By.id(password));
        elemExists = !driver.findElements(By.id(password)).isEmpty();
        assertTrue(String.format("Cannot find the password control '%s'.", password), elemExists);

        // see if the "Remember Me" control exists
        elemExists = !driver.findElements(By.id(rememberMe)).isEmpty();
        assertTrue(String.format("Cannot find the 'remember me' control '%s'.", rememberMe), elemExists);

        // see if the Login button control exists
        login_button = driver.findElement(By.id(login));
        elemExists = !driver.findElements(By.id(login)).isEmpty();
        assertTrue(String.format("Cannot find the login button control '%s'.", login), elemExists);

        username_field.sendKeys("Cs1632archtest");
        password_field.sendKeys("Kittypaws");
        login_button.click();

        String bodyText = driver.findElement(By.className("errorbox")).getText();
        assertTrue("Text not found!", bodyText.contains("Login error"));
    }

    /**
     * GIVEN that I am on login page for arch wiki 
     * WHEN I enter a valid username and password 
     * THEN I should be logged into arch wiki. 
     */
    @Test
    public void testValidLogin() {
        // announce test and build expectations
        System.out.printf("testWrongPassword: Testing invalid password for login...\n");
        String login_url = "https://wiki.archlinux.org/index.php?title=Special:UserLogin&returnto=MTP&returntoquery=type%3Drevision%26diff%3D421473%26oldid%3D420213";

        String username = "wpName1";
        String password = "wpPassword1";
        String rememberMe = "wpRemember";
        String login = "wpLoginAttempt";

        WebElement username_field;
        WebElement password_field;
        WebElement login_button;

        WebElement account_name;
        WebElement logout_link;

        // go to the login page for the arch wiki
        driver.get(login_url);

        // RECHECK THAT LOGIN FORM IS CORRECT
        boolean elemExists;
        // see if the username control exists
        username_field = driver.findElement(By.id(username));
        elemExists = !driver.findElements(By.id(username)).isEmpty();
        assertTrue(String.format("Cannot find the username control '%s'.", username), elemExists);

        // see if the password control exists
        password_field = driver.findElement(By.id(password));
        elemExists = !driver.findElements(By.id(password)).isEmpty();
        assertTrue(String.format("Cannot find the password control '%s'.", password), elemExists);

        // see if the "Remember Me" control exists
        elemExists = !driver.findElements(By.id(rememberMe)).isEmpty();
        assertTrue(String.format("Cannot find the 'remember me' control '%s'.", rememberMe), elemExists);

        // see if the Login button control exists
        login_button = driver.findElement(By.id(login));
        elemExists = !driver.findElements(By.id(login)).isEmpty();
        assertTrue(String.format("Cannot find the login button control '%s'.", login), elemExists);

        username_field.sendKeys("Cs1632archtest");
        password_field.sendKeys("archtest");

        /*		login_button.click();

		account_name = driver.findElement(By.cssSelector("div.pbody ul li#pt-userpage a.new"));
   		String expected_name = account_name.getText(); 
    	driver.findElement(By.linkText("Log out")).click();
		assertTrue(expected_name.equals("Cs1632archtest")); */
    }

    /******************************************************************************************************************
     *
     * USER STORY:  AS A USER. 
     *              I WOULD LIKE TO GO TO THE AUR FROM ARCHWIKI IN A NEW TAB 
     *              IN ORDER TO DOWNLOAD googletalk-plugin.
     */
    /**
     * Make sure that Menu bar is available on the main page
     */
   /* @Test
    public void menuBarExistence() {
        // announce test and build expectations
        System.out.printf("menuBarExistence: Testing that the menu bar exists on the main page...\n");
        String menuBarName = "archnavbarlist";

        // go to the main arch wiki page
        driver.get("https://wiki.archlinux.org/");

        // try to grab the ul with nav bar
        boolean menuBarExists = !driver.findElements(By.id(menuBarName)).isEmpty();
        assertTrue(String.format("The menu bar '%s' cannot be found.", menuBarName), menuBarExists);
    }*/

    /**
     * Make sure that the Menu bar on the main page has the items "Home" "Packages" "Forums" "Wiki" "Bugs" "AUR"
     * "Download"
     */
    /*@Test
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
    } */

    /******************************************************************************************************************
     * USER STORY:  AS A USER. 
     *              I WOULD LIKE TO GO FROM ARCH WIKI TO MAIN ARCH PAGE IN A NEW TAB 
     *              IN ORDER TO DOWNLOAD ARCHLINUX
     */
    /**
     * Make sure that the ArchLinux logo exists on the main page
     */
    /*@Test
    //public void archLogoExistence() {
        // announce test and build expectations
        System.out.printf("archLogoExistence: Testing that the logo exists on the main page...\n");
        String logoID = "logo";

        // go to the main wiki page
        driver.get("https://wiki.archlinux.org/");

        // check if the logo exists
        boolean logoExists = !driver.findElements(By.id(logoID)).isEmpty();

        assertTrue(String.format("Cannot find the Arch Linux Logo at id '%s'.", logoID), logoExists);
    } */

    /**
     * Make sure that the ArchLinux logo when clicked takes to user to ArchLinux.org
     */
    /*@Test
    //public void archLogoCorrectness() {
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
    } */

    /******************************************************************************************************************
     * USER STORY:  AS A USER. 
     *              I WOULD LIKE TO SEE A PAGE'S HISTORY. 
     *              IN ORDER TO CHECK MOST RECENT REVISION.
     */
    /**
     * GIVEN that I am on the MTP page on the ARCH linux wikipedia 
     * WHEN look at the beginning of the page 
     * THEN I should find a tab to the page's history.
     */
    @Test
    public void checkHistoryExistence() {
        // announce test and build expectations
        System.out.printf("historyTabCorrectness: Testing that the history tab of MTP page takes user to MTP revision history...\n");

        String link_name = "history";
        String page_url = "https://wiki.archlinux.org/index.php/MTP";

        String expected_title = "Revision history of \"MTP\" - ArchWiki";
        String received_title;

        // go to the mtp page on the arch wiki
        driver.get(page_url);

        // check if history tab exists on MTP page	
        boolean history_existence = !driver.findElements(By.linkText(link_name)).isEmpty();
        assertTrue(String.format("The '%s' tab was not found on '%s'", link_name, page_url), history_existence);
    }

    /**
     * GIVEN that I am on the MTP page on the ARCH linux wikipedia 
     * WHEN click the history tab 
     * THEN I should be taken to the MTP page's history.
     */
    @Test
    public void checkHistoryCorrectness() {
        // announce test and build expectations
        System.out.printf("historyTabCorrectness: Testing that the history tab of MTP page takes user to MTP revision history...\n");

        String link_name = "history";
        String page_url = "https://wiki.archlinux.org/index.php/MTP";

        String expected_title = "Revision history of \"MTP\" - ArchWiki";
        String received_title;

        // go to the mtp page on the arch wiki
        driver.get(page_url);

        // check if history tab exists on MTP page	
        boolean history_existence = !driver.findElements(By.linkText(link_name)).isEmpty();
        assertTrue(String.format("The '%s' tab was not found on '%s'", link_name, page_url), history_existence);

        // get the history tab web element
        WebElement history_tab = driver.findElement(By.linkText(link_name));

        // click history tab and check if title of page is "Revision history for "MTP" - ArchWiki"
        try {
            history_tab.click();
            received_title = driver.getTitle();
            assertTrue(received_title.equals(expected_title));
        } catch (WebDriverException wdEx) {
            fail(String.format("Element is not clickable\n"));
        }
    }

    /**
     * GIVEN that I am on the MTP History page on the ARCH linux wikipedia 
     * WHEN I click a radio button for a particular change 
     * THEN the radio button I clicked should be selected.
     */
    @Test
    public void checkHistoryRadioButtons() {
        // announce test and build expectations
        System.out.printf("checkHIstoryRadioButtons()\n");
        String page_url = "https://wiki.archlinux.org/index.php?title=MTP&action=history";
        System.out.printf("checkHistoryRadioButtons: make sure that the history buttons are clickable\n");

        // go to the mtp page history
        driver.get(page_url);

        try {
            // get a radio button 
            WebElement radio_button = driver.findElement(By.id("mw-diff-421473"));
            radio_button.click();
            boolean selected = radio_button.isSelected();
            assertTrue(selected);
        } catch (WebDriverException wdEx) {
            fail(String.format("Element is not clickable\n"));
        }
    }

    /**
     * GIVEN that I am on the MTP History page on the ARCH linux wikipedia 
     * WHEN I change the search date OR change the month 
     * THEN the revision page should display revisions from the searched time period
	 *
     */
    @Test
    public void checkHistoryTimes() {
        // announce test and build expectations
        System.out.printf("checkHistoryRadioButtons: make sure that the history buttons are clickable\n");
        String page_url = "https://wiki.archlinux.org/index.php?title=MTP&action=history";

        String monthsCSSPath = String.format("select#month.mw-month-selector option");

        // go to the mtp page history
        driver.get(page_url);

        try {
            // get list of months from drop-down menu	
            List<WebElement> monthElements = driver.findElements(By.cssSelector(monthsCSSPath));
            // check that the options in the drop-down menu are correct and in order	
            assertEquals(String.format("The month list item'%s' cannot be found.", "all"), monthElements.get(0).getText(), "all");
            assertEquals(String.format("The month list item'%s' cannot be found.", "January"), monthElements.get(1).getText(), "January");
            assertEquals(String.format("The month list item'%s' cannot be found.", "February"), monthElements.get(2).getText(), "February");
            assertEquals(String.format("The month list item'%s' cannot be found.", "March"), monthElements.get(3).getText(), "March");
            assertEquals(String.format("The month list item'%s' cannot be found.", "April"), monthElements.get(4).getText(), "April");
            assertEquals(String.format("The month list item'%s' cannot be found.", "May"), monthElements.get(5).getText(), "May");
            assertEquals(String.format("The month list item'%s' cannot be found.", "June"), monthElements.get(6).getText(), "June");
            assertEquals(String.format("The month list item'%s' cannot be found.", "July"), monthElements.get(7).getText(), "July");
            assertEquals(String.format("The month list item'%s' cannot be found.", "August"), monthElements.get(8).getText(), "August");
            assertEquals(String.format("The month list item'%s' cannot be found.", "September"), monthElements.get(9).getText(), "September");
            assertEquals(String.format("The month list item'%s' cannot be found.", "October"), monthElements.get(10).getText(), "October");
            assertEquals(String.format("The month list item'%s' cannot be found.", "November"), monthElements.get(11).getText(), "November");
            assertEquals(String.format("The month list item'%s' cannot be found.", "December"), monthElements.get(12).getText(), "December");

            // check that the year field is enterable and go works 
            // get the year field
            WebElement year_input = driver.findElement(By.cssSelector("fieldset#mw-history-search input#year"));
            year_input.sendKeys(Keys.chord(Keys.CONTROL, "a"), "2014");

            // get the 'Go' button for the search
            driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

            String bodyText = driver.findElement(By.tagName("body")).getText();
            assertTrue("Text not found!", bodyText.contains("23 December 2014"));

        } catch (WebDriverException wdEx) {
            fail("Exception received: Test failure");
        }
    }

    /**
     * GIVEN that I am on the MTP History page on the ARCH linux wikipedia
     * WHEN I select two revisions 
     * AND press the compare revisions button 
     * THEN I should be able to find the differences between revisions.
     */
    @Test
    public void testRevisionComparison() {
        // announce test and build expectations
        System.out.printf("testRevisionComparison: check that two revisions can be compared and differences will be displayed\n");
        String page_url = "https://wiki.archlinux.org/index.php?title=MTP&action=history";
        String expected_title = "Difference between revisions of \"MTP\"";
        boolean selected;
        // go to mtp page history
        driver.get(page_url);

        try {
            // click two different radio buttons
            WebElement radio_button_1 = driver.findElement(By.id("mw-oldid-419559"));
            radio_button_1.click();
            selected = radio_button_1.isSelected();
            assertTrue(selected);

            WebElement radio_button_2 = driver.findElement(By.id("mw-diff-420213"));
            radio_button_2.click();
            selected = radio_button_2.isSelected();
            assertTrue(selected);

            driver.findElement(By.cssSelector("input.historysubmit.mw-history-compareselectedversions-button")).click();

            String received_title = driver.getTitle();

            assertTrue(received_title.contains(expected_title));

        } catch (WebDriverException wdEx) {
            fail("Exception received: Test failure");

        }
    }

    /******************************************************************************************************************
     * USER STORY:  AS A USER. 
     *              I WOULD LIKE TO REGISTER ON ARCHWIKI. 
     *              IN ORDER TO BECOME A MEMBER.
     */
    /**
     * GIVEN that I am on main arch page 
     * WHEN I click create account 
     * THEN I should receive a page with fields: USERNAME AND PASSWORD AND CONFIRM PASSWORD AND EMAIL ADDRESS 
     *      AND ANSWER AND a create your account buttoN
     */
    @Test
    public void checkCreateAccount() {
        // announce test and build expections
        System.out.printf("checkCreateAccount: Testing that the create account button takes user to enter information\n");

        String page_url = "https://wiki.archlinux.org";
        String link_name = "create account";
        String expected_title = "Create account - ArchWiki";

        // go to the main archwiki page
        driver.get(page_url);

        // click the create account button trying to go to create account page
        try {
            WebElement create_account_link = driver.findElement(By.linkText(link_name));
            create_account_link.click();
            String received_title = driver.getTitle();
            assertTrue(received_title.equals(expected_title));

            // check all forms on create account page
            checkUserInputForms(driver);
        } catch (NoSuchElementException ex) {
            fail();
        } catch (WebDriverException wdEx) {
            fail(String.format("Element is not clickable\n"));
        }

    }

    /**
     * GIVEN that I am on main arch page 
     * WHEN I click create account 
     * THEN I should see the message that ArchWiki is made by people like you. 
     */
    @Test
    public void checkHappyWikiMessage() {
        // announce test and build expections
        System.out.printf("checkCreateAccount: Testing that the create account button takes user to enter information\n");

        String page_url = "https://wiki.archlinux.org";
        String link_name = "create account";
        String expected_text = "ArchWiki is made by people like you.";
        String received_text;

        // go to the main archwiki page
        driver.get(page_url);

        // click the create account button trying to go to create account page
        try {
            WebElement create_account_link = driver.findElement(By.linkText(link_name));
            create_account_link.click();
            WebElement benefits_header = driver.findElement(By.cssSelector("div.mw-createacct-benefits-container h2"));
            received_text = benefits_header.getText();
            assertEquals(String.format("Benefits text does not exist!"), received_text, expected_text);

        } catch (NoSuchElementException ex) {
            fail();
        } catch (WebDriverException wdEx) {
            fail(String.format("Element is not clickable\n"));
        }
    }

    /**
     * This is a helper function to check user input fields
     *
     * @param driver
	 *
     */
    public void checkUserInputForms(FirefoxDriver driver) {

        // check forms with consideration for ordering 
        try {
            WebElement user_name_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpName2"));
            String un_phtext = user_name_form.getAttribute("placeholder");
            assertTrue(String.format("Form does not exist"), un_phtext.equals("Enter your username"));
            System.out.printf("Form with '%s' exists.  Continuing...\n", un_phtext);

            WebElement password_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpPassword2"));
            String pass_phtext = password_form.getAttribute("placeholder");
            assertTrue(String.format("Form does not exist"), pass_phtext.equals("Enter a password"));
            System.out.printf("Form with '%s' exists.  Continuing...\n", pass_phtext);

            WebElement retype_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpRetype"));
            String rt_phtext = retype_form.getAttribute("placeholder");
            assertTrue(String.format("Form does not exist"), rt_phtext.equals("Enter password again"));
            System.out.printf("Form with '%s' exists.  Continuing...\n", rt_phtext);

            WebElement email_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpEmail"));
            String email_phtext = email_form.getAttribute("placeholder");
            assertTrue(String.format("Form does not exist"), email_phtext.equals("Enter your email address"));
            System.out.printf("Form with '%s' exists.  Continuing...\n", email_phtext);

            WebElement real_name_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpRealName"));
            String rn_phtext = real_name_form.getAttribute("name");
            assertTrue(String.format("Form does not exist"), rn_phtext.equals("wpRealName"));
            System.out.printf("Form with '%s' exists.  Continuing...\n", rn_phtext);

            WebElement question_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#FunnyAnswer"));
            String qst_name = question_form.getAttribute("name");
            assertTrue(String.format("Form does not exist"), qst_name.equals("FunnyAnswer"));
            System.out.printf("Form with '%s' exists.  Continuing...\n", qst_name);

            WebElement submit_button = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpCreateaccount"));
            String submit_name = submit_button.getAttribute("name");
            assertTrue(String.format("Form does not exist"), submit_name.equals("wpCreateaccount"));
            System.out.printf("Form with '%s' exists.  Continuing...\n", submit_name);

        } catch (NoSuchElementException ex) {
            fail();
        }
    }

    /**
     * GIVEN that I am on create account page for arch wiki 
     * WHEN I enter username, password, password_retype, email, and
     *      answer AND password != password_retype AND I click Create account button 
     * THEN I should be on create account page with Account creation error text on page 
	 *
     */
    @Test
    public void testFailedPasswordRetype() {
        // announce test and build expections
        System.out.printf("testFailedPasswordRetype: Testing that the that incorrect password retype on account creation is rejected\n");

        String page_url = "https://wiki.archlinux.org/index.php?title=Special:UserLogin&type=signup";

        String expected_text = "Account creation error";

        String user_name = "LordFuzzyPaws";
        String password_1 = "T0t3sSequre";
        String password_2 = "yellow";
        String email_entry = "cs1632deliverable3@gmail.com";
        String real_name = "Nope";
        /* THIS WORKS AS OF pacman v5.0.1 - libalpm V10.0.1 */
        String fun_answer = "BIQC4LJNFYQCAIBAEAQCAIBAEAQCAIBAEAQCAICQMFRW2YLOEB3DKLRQFYYSALJANRUWEYLMOBWS";

        // go to create account page of arch wiki
        driver.get(page_url);

        try {
            checkUserInputForms(driver);

            WebElement user_name_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpName2"));
            user_name_form.sendKeys(user_name);

            WebElement password_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpPassword2"));
            password_form.sendKeys(password_1);

            WebElement retype_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpRetype"));
            password_form.sendKeys(password_2);

            WebElement email_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpEmail"));
            email_form.sendKeys(email_entry);

            WebElement real_name_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpRealName"));
            real_name_form.sendKeys(real_name);

            WebElement question_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#FunnyAnswer"));
            question_form.sendKeys(fun_answer);

            // click submit button now that all fields are full
            WebElement submit_button = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpCreateaccount"));
            submit_button.click();

            String bodyText = driver.findElement(By.tagName("body")).getText();
            assertTrue("Text not found!", bodyText.contains(expected_text));

        } catch (NoSuchElementException ex) {
            fail();
        }
    }

    /**
     * GIVEN that I am on create account page for arch wiki 
     * WHEN I enter username, password, password_retype, email, and answer AND THERE EXISTS 
     *      username SUCH THAT username EXISTS IN DB AND I click Create account button 
     * THEN I should be on create account page with Account creation error: Username entered already in use text on page 
	 *
     */
    @Test
    public void testNonUniquePassword() {
        // announce test and build expections
        System.out.printf("testNonUniquePassword: Testing that the that entered password is unique and fails otherwise\n");

        String page_url = "https://wiki.archlinux.org/index.php?title=Special:UserLogin&type=signup";

        String expected_text = "Username entered already in use";

        String user_name = "cs1632archtest";
        String password_1 = "T0t3sSequre";
        String password_2 = "yellow";
        String email_entry = "cs1632deliverable3@gmail.com";
        String real_name = "Nope";
        /* THIS WORKS AS OF pacman v5.0.1 - libalpm V10.0.1 */
        String fun_answer = "BIQC4LJNFYQCAIBAEAQCAIBAEAQCAIBAEAQCAICQMFRW2YLOEB3DKLRQFYYSALJANRUWEYLMOBWS";

        // go to create account page of arch wiki
        driver.get(page_url);

        try {
            checkUserInputForms(driver);

            WebElement user_name_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpName2"));
            user_name_form.sendKeys(user_name);

            WebElement password_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpPassword2"));
            password_form.sendKeys(password_1);

            WebElement retype_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpRetype"));
            password_form.sendKeys(password_2);

            WebElement email_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpEmail"));
            email_form.sendKeys(email_entry);

            WebElement real_name_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpRealName"));
            real_name_form.sendKeys(real_name);

            WebElement question_form = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#FunnyAnswer"));
            question_form.sendKeys(fun_answer);

            // click submit button now that all fields are full
            WebElement submit_button = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpCreateaccount"));
            submit_button.click();

            String bodyText = driver.findElement(By.tagName("body")).getText();
            assertTrue("Text not found!", bodyText.contains(expected_text));

        } catch (NoSuchElementException ex) {
            fail();
        }
    }

    /**
     * GIVEN that I am on create account page for arch wiki 
     * WHEN I don't enter username, password, password_retype, email, and answer AND I click Create account button 
     * THEN I should be on create account page with Account creation error. 
     */
    @Test
    public void testNoEnteryForAccountCreation() {
        // announce test and build expections
        System.out.printf("testNoEnteryForAccountCreation: Testing fields have to be filled when creating an account\n");

        String page_url = "https://wiki.archlinux.org/index.php?title=Special:UserLogin&type=signup";

        String expected_text = "Account creation error";

        // go to create account page of arch wiki
        driver.get(page_url);

        try {
            checkUserInputForms(driver);

            WebElement submit_button = driver.findElement(By.cssSelector("div#userloginForm  div.mw-ui-vform-field input#wpCreateaccount"));
            submit_button.click();

            String bodyText = driver.findElement(By.tagName("body")).getText();
            assertTrue("Text not found!", bodyText.contains(expected_text));

        } catch (NoSuchElementException ex) {
            fail();
        }
    }

    /**
     * ****************************************************************************************************************
     */
    /**
     * This main method runs our test suite
     *
     * @param args **not used**
     */
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("AcceptanceTestsTest");
    }
}
