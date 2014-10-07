/**
 * This file is part of AlfrescoBasicFunctionalityTestingScripts.
 *
 * AlfrescoBasicFunctionalityTestingScripts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AlfrescoBasicFunctionalityTestingScripts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AlfrescoBasicFunctionalityTestingScripts.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.zaizi.automation.abfts.core.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.zaizi.automation.abfts.core.elements.Button;
import com.zaizi.automation.abfts.core.elements.Checkbox;
import com.zaizi.automation.abfts.core.elements.Division;
import com.zaizi.automation.abfts.core.elements.Form;
import com.zaizi.automation.abfts.core.elements.Link;
import com.zaizi.automation.abfts.core.elements.Span;
import com.zaizi.automation.abfts.core.elements.TextField;

/**
 * @author kvithyashankar@zaizi.com
 * 
 */
public class Dashboard
{

    /**
     * Defining log4j
     */
    private static final Logger LOGGER = Logger.getLogger(Dashboard.class);
    
    private static final String ADMIN_BUTTON_XPATH = "//span[@id='HEADER_USER_MENU_POPUP_text']";
    private static final String USER_BUTTON_XPATH = "//span[@id='HEADER_USER_MENU_POPUP_text']";
    private static final String TEXT_TEST_PASSED = "Test case passed!";
    private static final String TEXT_TEST_FAILED = "Test case failed!";
    
    /**
     * Defining WebDriver
     */
    private WebDriver driver;

    /**
     * @param driver
     */
    public Dashboard(WebDriver driver)
    {
        this.driver = driver;
    }

    /**
     * @param driver
     */
    public void setDriver(WebDriver driver)
    {
        this.driver = driver;
    }

    /**
     * method checkAdminButton
     */
    public void checkAdminButton()
    {
        Button adminButton = new Button(driver, By.xpath(ADMIN_BUTTON_XPATH));

        String a = "Administrator";

        if (adminButton.getWebElement().getText().equals(a))
        {
            LOGGER.info(TEXT_TEST_PASSED);
        }
        else
        {
            LOGGER.info(TEXT_TEST_FAILED);
        }
    }

    /**
     * @param user
     */
    public void checkUserButton(String user)
    {
        Button adminButton = new Button(driver, By.xpath(USER_BUTTON_XPATH));

        if (adminButton.getWebElement().getText().equals(user))
        {
            LOGGER.info(TEXT_TEST_PASSED);
        }
        else
        {
            LOGGER.info(TEXT_TEST_FAILED);
        }
    }

    /**
     * @throws InterruptedException
     */
    public void logOut() throws InterruptedException
    {
        Thread.sleep(2000);
        Button adminButton = new Button(driver, By.xpath("//span[@id='HEADER_USER_MENU_POPUP_text']"));
        adminButton.click();
        Thread.sleep(2000);
        Button logOut = new Button(driver, By.xpath("//a[contains(., 'Logout')]"));
        logOut.click();
        Thread.sleep(1000);
        Form form = new Form(driver, By.xpath("//form"));
        if (form.getWebElement().isDisplayed())
        {
            LOGGER.info(TEXT_TEST_PASSED);
        }
        else
        {
            LOGGER.info(TEXT_TEST_FAILED);
        }
    }

    /**
     * @param siteNameText
     * @throws InterruptedException
     */
    public void createPublicSite(String siteNameText) throws InterruptedException
    {
        Button sitesButton = new Button(driver, By.xpath("//span[@id='HEADER_SITES_MENU_text']"));
        sitesButton.click();
        Link createSiteLink = new Link(driver, By.xpath("//td[@id='HEADER_SITES_MENU_CREATE_SITE_text']"));
        createSiteLink.click();
        Thread.sleep(2000);
        TextField siteName = new TextField(driver, By.xpath("//input[@id='alfresco-createSite-instance-title']"));
        siteName.enterText(siteNameText);
        Thread.sleep(2000);
        Checkbox publicOption = new Checkbox(driver, By.xpath("//input[@id='alfresco-createSite-instance-isPublic']"));
        publicOption.click();
        Thread.sleep(2000);
        Button okButton = new Button(driver, By.xpath("//button[@id='alfresco-createSite-instance-ok-button-button']"));
        okButton.click();
        Thread.sleep(2000);
    }

    /**
     * @param siteNameText
     * @throws InterruptedException
     */
    public void createPrivateSite(String siteNameText) throws InterruptedException
    {
        Button sitesButton = new Button(driver, By.xpath("//span[@id='HEADER_SITES_MENU_text']"));
        sitesButton.click();
        Link createSiteLink = new Link(driver, By.xpath("//td[@id='HEADER_SITES_MENU_CREATE_SITE_text']"));
        createSiteLink.click();
        Thread.sleep(2000);
        TextField siteName = new TextField(driver, By.xpath("//input[@id='alfresco-createSite-instance-title']"));
        siteName.enterText(siteNameText);
        Thread.sleep(2000);
        Checkbox privateOption = new Checkbox(driver, By.xpath("//input[@id='alfresco-createSite-instance-isPrivate']"));
        privateOption.click();
        Thread.sleep(2000);
        Button okButton = new Button(driver, By.xpath("//button[@id='alfresco-createSite-instance-ok-button-button']"));
        okButton.click();
    }

    /**
     * @param siteName
     * @throws InterruptedException
     */
    public void searchSite(String siteName) throws InterruptedException
    {
        Button sitesButton = new Button(driver, By.xpath("//span[@id='HEADER_SITES_MENU_text']"));
        sitesButton.click();
        Thread.sleep(2000);
        Link searchForSites = new Link(driver, By.xpath("//td[@id='HEADER_SITES_MENU_SITE_FINDER_text']"));
        searchForSites.click();
        Thread.sleep(2000);
        TextField searchText = new TextField(driver,
                By.xpath("//input[@id='template_x002e_site-finder_x002e_site-finder_x0023_default-term']"));
        searchText.enterText(siteName);
        Thread.sleep(2000);
        Button searchButton = new Button(driver,
                By.xpath("//button[@id='template_x002e_site-finder_x002e_site-finder_x0023_default-button-button']"));
        searchButton.click();
        Thread.sleep(2000);
    }

    /**
     * @throws InterruptedException
     */
    public void navigateToExistingSite() throws InterruptedException
    {
        Thread.sleep(2000);
        Link siteName = new Link(driver, By.xpath("//a[@href='/share/page/site/new/dashboard' and contains(., 'new')]"));
        siteName.click();

    }

    /**
     * @throws InterruptedException
     */
    public void deleteSite() throws InterruptedException
    {
        Thread.sleep(2000);
        Button deleteButton = new Button(driver, By.xpath("//button[contains(., 'Delete')]"));
        deleteButton.click();
        Thread.sleep(1000);
        Button confirmDeletion = new Button(driver, By.xpath("//button[contains(., 'Delete')]"));
        confirmDeletion.click();
        Thread.sleep(1000);
        Button yesButton = new Button(driver, By.xpath("//button[contains(., 'Yes')]"));
        yesButton.click();
    }

    /**
     * @param newSiteName
     * @throws InterruptedException
     */
    public void editSite(String newSiteName) throws InterruptedException
    {
        Thread.sleep(6000);
        Button configurationButton = new Button(driver, By.xpath("//img[@alt='Site configuration options']"));
        configurationButton.click();
        Thread.sleep(1000);
        Link editSiteDetails = new Link(driver, By.xpath("//td[@id='HEADER_EDIT_SITE_DETAILS_text']"));
        editSiteDetails.click();
        Thread.sleep(2000);
        TextField editName = new TextField(driver, By.xpath("//input[@id='alfresco-editSite-instance-title']"));
        editName.clearText();
        editName.enterText(newSiteName);
        Button okayButton = new Button(driver, By.xpath("//button[@id='alfresco-editSite-instance-ok-button-button']"));
        okayButton.click();
    }

    /**
     * @param siteNameText
     * @throws InterruptedException
     */
    public void checkSiteAvailability(String siteNameText) throws InterruptedException
    {
        Thread.sleep(4000);
        Link repository = new Link(driver,
                By.xpath("//a[@href='/share/page/repository' and contains(., 'Repository')]"));
        repository.click();
        Thread.sleep(6000);
        Link sites = new Link(driver, By.xpath("//span/a[contains(., 'Sites')]"));
        sites.click();
        Thread.sleep(2000);
        Link siteName = new Link(driver, By.xpath("//span/a[contains(., '" + siteNameText + "')]"));
        if (siteName.getWebElement().isDisplayed())
        {
            LOGGER.info(TEXT_TEST_PASSED);
        }
        else
        {
            LOGGER.info(TEXT_TEST_FAILED);
        }
    }

    
   /**
     * @throws InterruptedException
     */
    public void navigateToDashboard() throws InterruptedException
    {
        Thread.sleep(2000);
        Button dashboard = new Button(driver, By.xpath("//a[contains(., 'Home')]"));
        dashboard.click();
    }

    /**
     * @throws InterruptedException
     */
    public void checkSiteAvailability2() throws InterruptedException
    {
        Thread.sleep(2000);
        Span spanText = new Span(driver, By.xpath("//span[contains(., 'No sites found')]"));
        if (spanText.getWebElement().isDisplayed())
        {
            LOGGER.info(TEXT_TEST_PASSED);
        }
        else
        {
            LOGGER.info(TEXT_TEST_FAILED);
        }
    }

    /**
     * @param dashletTitle
     * @throws InterruptedException
     */
    public void customizeDashboard(String dashletTitle) throws InterruptedException
    {
        Link customize = new Link(driver, By.xpath("//img[@alt='Customize Dashboard']"));
        customize.click();
        Thread.sleep(2000);
        Button addDashlets = new Button(
                driver,
                By.xpath("//button[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-addDashlets-button-button']"));
        addDashlets.click();
        Thread.sleep(2000);
        Actions performer = new Actions(driver);
        WebElement rssFeed = driver.findElement(By.xpath("//div[@title='" + dashletTitle + "']"));
        WebElement list = driver.findElement(By.xpath("//ul[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-column-ul-1']"));
        Action dragAndDrop = performer.clickAndHold(rssFeed).moveToElement(list).release(list).build();
        Thread.sleep(5000);
        dragAndDrop.perform();
        Thread.sleep(5000);
        Button okButton = new Button(
                driver,
                By.xpath("//button[@id='template_x002e_customise-dashlets_x002e_customise-user-dashboard_x0023_default-save-button-button']"));
        okButton.click();
        Thread.sleep(5000);
        Division rssFeedBlog = new Division(
                driver,
                By.xpath("//div[@id='page_x002e_component-1-3_x002e_user_x007e_admin_x007e_dashboard_x0023_default-title']"));
        if (rssFeedBlog.getWebElement().isDisplayed())
        {
            LOGGER.info(TEXT_TEST_PASSED);
        }
        else
        {
            LOGGER.info(TEXT_TEST_FAILED);
        }
    }

    /**
     * @param dashletTitle
     * @throws InterruptedException
     */
    public void customizeSiteDashboard(String dashletTitle) throws InterruptedException
    {
        Thread.sleep(5000);
        Link customize = new Link(driver, By.xpath("//img[@alt='Site configuration options']"));
        customize.click();
        Thread.sleep(2000);
        Link customizeOption = new Link(driver, By.xpath("//a[contains(., 'Customize Dashboard')]"));
        customizeOption.click();
        Thread.sleep(1000);
        Button addDashlets = new Button(
                driver,
                By.xpath("//span/span/button[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-addDashlets-button-button']"));
        addDashlets.click();
        Thread.sleep(2000);
        Actions performer = new Actions(driver);
        WebElement wiki = driver.findElement(By.xpath("//div[@title='" + dashletTitle + "']"));
        WebElement list = driver.findElement(By.xpath("//div/ul[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-column-ul-1']"));
        Action dragAndDrop = performer.clickAndHold(wiki).moveToElement(list).release(list).build();
        Thread.sleep(5000);
        dragAndDrop.perform();
        Thread.sleep(5000);
        Button okButton = new Button(
                driver,
                By.xpath("//span/span/button[@id='template_x002e_customise-dashlets_x002e_customise-site-dashboard_x0023_default-save-button-button']"));
        okButton.click();
        Thread.sleep(5000);
        Division wikiDashlet = new Division(driver, By.xpath("//div/div/div[@class='title' and contains(., 'Wiki')]"));
        if (wikiDashlet.getWebElement().isDisplayed())
        {
            LOGGER.info(TEXT_TEST_PASSED);
        }
        else
        {
            LOGGER.info(TEXT_TEST_FAILED);
        }
    }

    /**
     * @param user
     * @throws InterruptedException
     */
    public void inviteInternalUser(String user) throws InterruptedException
    {
        Thread.sleep(2000);
        Link inviteLink = new Link(driver, By.xpath("//a[@href='invite' and @class='theme-color-1']"));
        inviteLink.click();
        Thread.sleep(2000);
        TextField userSearch = new TextField(driver,
                By.xpath("//input[@id='template_x002e_people-finder_x002e_invite_x0023_default-search-text']"));
        userSearch.enterText(user);
        Thread.sleep(2000);
        Button search = new Button(
                driver,
                By.xpath("//button[@id='template_x002e_people-finder_x002e_invite_x0023_default-search-button-button']"));
        search.click();
        Thread.sleep(4000);
        Button addUser = new Button(driver, By.xpath("//span/span/span/button[contains(., 'Add')]"));
        addUser.click();
        Thread.sleep(2000);
        Button selectRole = new Button(driver, By.xpath("//span/button[contains(., 'Select Role')]"));
        selectRole.click();
        Thread.sleep(3000);
        Link collaborator = new Link(driver, By.xpath("//a[contains(., 'Collaborator')]"));
        collaborator.click();
        Thread.sleep(2000);
        Button invite = new Button(
                driver,
                By.xpath("//button[@id='template_x002e_invitationlist_x002e_invite_x0023_default-invite-button-button']"));
        invite.click();
    }

    /**
     * @throws InterruptedException
     */
    public void internalUserInvitationCheck() throws InterruptedException
    {
        driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/?ui%3D2&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1");
        Thread.sleep(4000);
        TextField email = new TextField(driver, By.xpath("//input[@id='Email']"));
        email.enterText("test@zaizi.com");
        TextField password = new TextField(driver, By.xpath("//input[@id='Passwd']"));
        password.enterText("test");
        TextField signIn = new TextField(driver, By.xpath("//input[@id='signIn']"));
        signIn.click();
        Thread.sleep(10000);
        Span link = new Span(
                driver,
                By.xpath("//span/b[contains(., 'Alfresco Share: You have been invited to join the Test Site site by admin')]"));
        if (link.getWebElement().isDisplayed())
        {
            LOGGER.info("The invitation has been received!");
        }
        else
        {
            LOGGER.info("The invitation was not found!");
        }
    }

    /**
     * @param firstName
     * @param lastName
     * @param emailText
     * @throws InterruptedException
     */
    public void inviteExternalUser(String firstName, String lastName, String emailText) throws InterruptedException
    {
        Thread.sleep(4000);
        Link inviteLink = new Link(driver, By.xpath("//a[@href='invite' and @class='theme-color-1']"));
        inviteLink.click();
        Thread.sleep(2000);

        TextField fName = new TextField(driver,
                By.xpath("//input[@id='template_x002e_addemail_x002e_invite_x0023_default-firstname']"));
        fName.enterText(firstName);
        TextField lName = new TextField(driver,
                By.xpath("//input[@id='template_x002e_addemail_x002e_invite_x0023_default-lastname']"));
        lName.enterText(lastName);
        TextField email = new TextField(driver,
                By.xpath("//input[@id='template_x002e_addemail_x002e_invite_x0023_default-email']"));
        email.enterText(emailText);
        Button add = new Button(driver,
                By.xpath("//button[@id='template_x002e_addemail_x002e_invite_x0023_default-add-email-button-button']"));
        add.click();
        Thread.sleep(4000);
        Button selectRole = new Button(driver, By.xpath("//span/button[contains(., 'Select Role')]"));
        selectRole.click();
        Thread.sleep(3000);
        Link collaborator = new Link(driver, By.xpath("//a[contains(., 'Collaborator')]"));
        collaborator.click();
        Thread.sleep(2000);
        Button invite = new Button(
                driver,
                By.xpath("//button[@id='template_x002e_invitationlist_x002e_invite_x0023_default-invite-button-button']"));
        invite.click();
    }

    /**
     * @throws InterruptedException
     */
    public void invitationCheck() throws InterruptedException
    {
        Link invitation = new Link(driver, By.xpath("//a[@href='pending-invites' and contains(., 'Pending Invites')]"));
        invitation.click();
        Thread.sleep(2000);
        Link name = new Link(driver,
                By.xpath("//a[@href='/share/page/user/derek_hale/profile' and contains(., 'Derek Hale')]"));
        if (name.getWebElement().isDisplayed())
        {
            LOGGER.info(TEXT_TEST_PASSED);
        }
        else
        {
            LOGGER.info(TEXT_TEST_FAILED);
        }
    }

    /**
     * method goToGroups
     */
    public void goToGroups()
    {
        Button console = new Button(driver, By.xpath("//span[contains(., 'Admin Tools')]"));
        console.click();
        Link groups = new Link(driver, By.xpath("//a[contains(., 'Groups')]"));
        groups.click();
    }

    /**
     * @param userName
     * @param passwordText
     * @throws InterruptedException
     */
    public void externalUserInvitationCheck(String userName, String passwordText) throws InterruptedException
    {
        driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/?ui%3D2&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1");
        Thread.sleep(4000);
        TextField email = new TextField(driver, By.xpath("//input[@id='Email']"));
        email.enterText(userName);
        TextField password = new TextField(driver, By.xpath("//input[@id='Passwd']"));
        password.enterText(passwordText);
        TextField signIn = new TextField(driver, By.xpath("//input[@id='signIn']"));
        signIn.click();
        Thread.sleep(10000);
        Span link = new Span(
                driver,
                By.xpath("//span/b[contains(., 'Alfresco Share: You have been invited to join the Test Site site by admin')]"));
        if (link.getWebElement().isDisplayed())
        {
            LOGGER.info("The invitation has been received!");
        }
        else
        {
            LOGGER.info("The invitation was not found!");
        }

    }

    /**
     * @param siteName
     * @param siteId
     * @throws InterruptedException
     */
    public void goToSite(String siteName, String siteId) throws InterruptedException
    {
        Span site = new Span(driver, By.xpath("//span[@id='HEADER_SITES_MENU_text']"));
        site.click();
        Thread.sleep(500);
        Link siteFinder = new Link(driver, By.xpath("//a[@href='/share/page/site-finder']"));
        siteFinder.click();
        Thread.sleep(3000);
        TextField name = new TextField(driver,
                By.xpath("//input[@id='template_x002e_site-finder_x002e_site-finder_x0023_default-term']"));
        name.enterText(siteName);
        Button search = new Button(driver,
                By.xpath("//button[@id='template_x002e_site-finder_x002e_site-finder_x0023_default-button-button']"));
        search.click();
        Thread.sleep(3000);
        Link result = new Link(driver, By.xpath("//a[@href='/share/page/site/" + siteId
                + "/dashboard' and @class='theme-color-1']"));
        result.click();
        Thread.sleep(4000);
    }

}
