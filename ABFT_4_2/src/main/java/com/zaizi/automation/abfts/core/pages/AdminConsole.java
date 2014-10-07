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

import com.gargoylesoftware.htmlunit.javascript.configuration.BrowserFeature;
import com.zaizi.automation.abfts.core.elements.Button;
import com.zaizi.automation.abfts.core.elements.Division;
import com.zaizi.automation.abfts.core.elements.Link;
import com.zaizi.automation.abfts.core.elements.Span;
import com.zaizi.automation.abfts.core.elements.TextField;
import com.zaizi.automation.abfts.core.info.TestCaseProperties;

/**
 * @author kvithyashankar@zaizi.com
 * 
 */
public class AdminConsole
{
    /**
     * Defining log4j
     */
    private static final Logger LOGGER = Logger.getLogger(AdminConsole.class);
    
    private static final String TEXT_TEST_PASSED = "Test case passed!";
    private static final String TEXT_TEST_FAILED = "Test case failed!";
    
    /**
     * Defining WebDriver
     */
    private WebDriver driver;

    /**
     * @param driver
     */
    public AdminConsole(final WebDriver driver)
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
     * goToUsers method
     * @throws InterruptedException
     */
    public void goToUsers() throws InterruptedException
    {
        Button adminConsole = new Button(driver, By.xpath("//a[contains(., 'Admin Tools')]"));
        adminConsole.click();
        Link userLink = new Link(driver, By.xpath("//a[contains(., 'Users')]"));
        userLink.click();
        Thread.sleep(4000);
    }

    /**
     * @param firstname
     * @param uname
     * @param pwd
     * @param emailVal
     * @throws InterruptedException
     */
    public void createNewUser(String firstname, String uname, String pwd, String emailVal) throws InterruptedException
    {
        Button newUser = new Button(driver,
                By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-newuser-button-button']"));
        newUser.click();
        Thread.sleep(1000);
        TextField userFname = new TextField(driver,
                By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-firstname']"));
        userFname.enterText(firstname);
        TextField email = new TextField(driver,
                By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-email']"));
        email.enterText(emailVal);
        TextField username = new TextField(driver,
                By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-username']"));
        username.enterText(uname);
        TextField password = new TextField(driver,
                By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-password']"));
        password.enterText(pwd);
        TextField verifyPassword = new TextField(driver,
                By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-verifypassword']"));
        verifyPassword.enterText(pwd);
        Thread.sleep(3000);
        Button createUser = new Button(
                driver,
                By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-createuser-ok-button-button']"));
        createUser.click();
    }

    /**
     * @param firstname
     */
    public void goToUserProfile(String firstname)
    {
        Link userName = new Link(driver, By.xpath("//a[contains(., '" + firstname + "')]"));
        userName.click();
    }

    /**
     * @throws InterruptedException
     */
    public void deleteUser() throws InterruptedException
    {
        Button deleteButton = new Button(driver,
                By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-deleteuser-button-button']"));
        deleteButton.click();
        Thread.sleep(2000);
        Button deleteConfirmation = new Button(driver, By.xpath("//span/button[contains(., 'Delete')]"));
        deleteConfirmation.click();
        Thread.sleep(2000);
    }

    /**
     * @param firstname
     * @throws InterruptedException
     */
    public void searchUser(String firstname) throws InterruptedException
    {
        Thread.sleep(5000);
        TextField searchUser = new TextField(driver,
                By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-text']"));
        searchUser.clearText();
        searchUser.enterText(firstname);
        Thread.sleep(2000);
        Button searchButton = new Button(driver,
                By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-button-button']"));
        Thread.sleep(1000);
        searchButton.click();
        Thread.sleep(2000);
    }

    /**
     * @param newUsername
     * @throws InterruptedException
     */
    public void editUser(String newUsername) throws InterruptedException
    {
        Button editButton = new Button(driver,
                By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-edituser-button-button']"));
        editButton.click();
        Thread.sleep(2000);
        TextField userFname = new TextField(driver,
                By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-update-firstname']"));
        userFname.clearText();
        userFname.enterText(newUsername);
        Button saveChangesButton = new Button(
                driver,
                By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-updateuser-save-button-button']"));
        saveChangesButton.click();
    }

    /**
     * @param firstname
     */
    public void checkUserPresence(String firstname)
    {
        Link userName = new Link(driver, By.xpath("//a[contains(., '" + firstname + "')]"));
        if (userName.getWebElement().isDisplayed())
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
    public void checkUserPresence2() throws InterruptedException
    {
        Thread.sleep(4000);
        Division div = new Division(driver,
                By.xpath("//div[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-bar']"));
        Thread.sleep(4000);
        if (div.getWebElement().getText().contains(TestCaseProperties.SEARCH_STRING))
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
    public void checkUserPresence3() throws InterruptedException
    {
        Thread.sleep(2000);
        Button goBack = new Button(driver,
                By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-goback-button-button']"));
        goBack.click();
        Thread.sleep(4000);
        Division div = new Division(driver,
                By.xpath("//div[@id='page_x002e_ctool_x002e_admin-console_x0023_default-search-bar']"));
        Thread.sleep(4000);
        if (div.getWebElement().getText().contains(TestCaseProperties.SEARCH_STRING))
        {
            LOGGER.info(TEXT_TEST_PASSED);
        }
        else
        {
            LOGGER.info(TEXT_TEST_FAILED);
        }
    }

    /**
     * @param groupName
     * @param groupId
     * @throws InterruptedException
     */
    public void createGroup(String groupName, String groupId) throws InterruptedException
    {
        Thread.sleep(4000);
        Button browse = new Button(driver,
                By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-browse-button-button']"));
        browse.click();
        Thread.sleep(2000);
        Span group = new Span(driver, By.xpath("//span[@class='groups-newgroup-button']"));
        group.click();
        Thread.sleep(2000);
        TextField identifier = new TextField(driver,
                By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-shortname']"));
        identifier.enterText(groupId);
        Thread.sleep(2000);
        TextField displayName = new TextField(driver,
                By.xpath("//input[@id='page_x002e_ctool_x002e_admin-console_x0023_default-create-displayname']"));
        displayName.enterText(groupName);
        Thread.sleep(2000);
        Button create = new Button(
                driver,
                By.xpath("//button[@id='page_x002e_ctool_x002e_admin-console_x0023_default-creategroup-ok-button-button']"));
        create.click();
        Thread.sleep(2000);
        Division groupSpace = new Division(driver, By.xpath("//div[@class='yui-columnbrowser-column-body']"));
        Thread.sleep(2000);
        if (groupSpace.getWebElement().getText().contains(groupName + " (" + groupId + ")"))
        {
            LOGGER.info(TEXT_TEST_PASSED);
        }
        else
        {
            LOGGER.info(TEXT_TEST_FAILED);
        }
    }

}
