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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

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
public class SiteDashboard
{
    
    /**
     * Defining log4j
     */
    private static final Logger LOGGER = Logger.getLogger(SiteDashboard.class);
    
    private static final String TEXT_TEST_PASSED = "Test case passed!";
    private static final String TEXT_TEST_FAILED = "Test case failed!";
    
    /**
     * Defining WebDriver
     */
    private WebDriver driver;

    /**
     * @param driver
     */
    public SiteDashboard(WebDriver driver)
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
     * @param documentName
     * @throws InterruptedException
     */
    public void viewDocument(String documentName) throws InterruptedException
    {
        Thread.sleep(4000);
        Link documentLibrary = new Link(driver, By.xpath("//a[contains(., 'Document Library')]"));
        documentLibrary.click();
        Thread.sleep(4000);
        Link document = new Link(driver, By.xpath("//a[contains(., '" + documentName + "')]"));
        document.click();
    }

    /**
     * @throws InterruptedException
     */
    public void checkDocumentPreview() throws InterruptedException
    {
        Thread.sleep(4000);
        Division flash = new Division(driver,
                By.xpath("//embed[@src='/share/res/components/preview/WebPreviewer.swf']"));
        if (flash.getWebElement().isDisplayed())
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
    public void deleteDocument() throws InterruptedException
    {
        Thread.sleep(4000);
        Link deleteDocument = new Link(driver, By.xpath("//a[@title='Delete Document']"));
        deleteDocument.click();
        Thread.sleep(1000);
        Button delete = new Button(driver, By.xpath("//button[contains(., 'Delete')]"));
        delete.click();
    }

    /**
     * @param documentName
     * @throws InterruptedException
     */
    public void checkDocumentAvailability(String documentName) throws InterruptedException
    {
        Thread.sleep(2000);
        driver.get(TestCaseProperties.HOST_URL + "search");
        Thread.sleep(2000);
        TextField searchText = new TextField(driver,
                By.xpath("//input[@id='page_x002e_search_x002e_search_x0023_default-search-text']"));
        searchText.enterText(documentName);
        Button search = new Button(driver,
                By.xpath("//button[@id='page_x002e_search_x002e_search_x0023_default-search-button-button']"));
        search.click();
        Thread.sleep(2000);
        Division searchInfo = new Division(driver,
                By.xpath("//div[@id='page_x002e_search_x002e_search_x0023_default-search-info']"));
        if (searchInfo.getWebElement().getText().equals(TestCaseProperties.TEXT))
        {
            LOGGER.info(TEXT_TEST_PASSED);
        }
        else
        {
            LOGGER.info(TEXT_TEST_FAILED);
        }
    }

    /**
     * @param userName
     * @throws InterruptedException
     */
    public void managePermissions(String userName) throws InterruptedException
    {
        Thread.sleep(2000);
        Span manage = new Span(driver, By.xpath("//span[contains(., 'Manage Permissions')]"));
        manage.click();
        Thread.sleep(2000);
        Span priviledges = new Span(driver, By.xpath("//span[contains(., 'Inherit Permissions')]"));
        priviledges.click();
        Thread.sleep(2000);
        Span remove = new Span(driver, By.xpath("//span/span/span[contains(., 'Yes')]"));
        remove.click();
        Thread.sleep(2000);
        Span add = new Span(driver, By.xpath("//span[contains(., 'Add User/Group')]"));
        add.click();
        Thread.sleep(2000);
        TextField searchText = new TextField(
                driver,
                By.xpath("//input[@id='template_x002e_manage-permissions_x002e_manage-permissions_x0023_default-authorityFinder-search-text']"));
        searchText.enterText(userName);
        Thread.sleep(2000);
        Span serach = new Span(driver, By.xpath("//span[contains(., 'Search')]"));
        serach.click();
        Thread.sleep(2000);
        Span user = new Span(driver, By.xpath("//span/span/span/button[contains(., 'Add')]"));
        user.click();
        Thread.sleep(3000);
        Span permissionButton = new Span(driver, By.xpath("//span/button[contains(., 'Site Collaborator')]"));
        permissionButton.click();
        Thread.sleep(2000);
        Link collaborator = new Link(driver, By.xpath("//a[contains(., 'Collaborator')]"));
        collaborator.click();
        Thread.sleep(2000);
        Button save = new Button(
                driver,
                By.xpath("//button[@id='template_x002e_manage-permissions_x002e_manage-permissions_x0023_default-okButton-button']"));
        save.click();
    }

    /**
     * @throws InterruptedException
     */
    public void checkPermissions() throws InterruptedException
    {
        Thread.sleep(7000);
        Span manage = new Span(driver, By.xpath("//span[contains(., 'Manage Permissions')]"));
        manage.click();
        Thread.sleep(2000);
        Button check = new Button(driver, By.xpath("//span/span/span[contains(., 'Collaborator')]"));
        if (check.getWebElement().getText().contains("Collaborator"))
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
    public void startWorkflow1() throws InterruptedException
    {
        Link documentLibrary = new Link(driver, By.xpath("//a[contains(., 'Document Library')]"));
        documentLibrary.click();
        Thread.sleep(2000);
        Link name = new Link(driver, By.xpath("//a[contains(., 'Test Document.pdf')]"));
        name.click();
        Thread.sleep(6000);
        Span workflow = new Span(driver, By.xpath("//a[@title='Start Workflow']/span"));
        workflow.click();
        Thread.sleep(2000);
        Button type = new Button(
                driver,
                By.xpath("//button[@id='template_x002e_start-workflow_x002e_start-workflow_x0023_default-workflow-definition-button-button']"));
        type.click();
        Span selection = new Span(driver, By.xpath("//li[@index='3']/span[contains(., 'Review And Approve')]"));
        selection.click();
        Thread.sleep(3000);
        Button select = new Button(driver, By.xpath("//button[contains(., 'Select')]"));
        select.click();
        Thread.sleep(5000);
        TextField assignee = new TextField(driver, By.xpath("//div[@class='search']/input[@class='search-input']"));
        assignee.enterText("Nolan Ross");
        Thread.sleep(2000);
        Button search = new Button(
                driver,
                By.xpath("//button[@id='template_x002e_start-workflow_x002e_start-workflow_x0023_default-startWorkflowForm-alf-id4_assoc_bpm_assignee-cntrl-picker-searchButton-button']"));
        search.click();
        Thread.sleep(2000);
        Span add = new Span(driver, By.xpath("//span[@class='addIcon']"));
        add.click();
        Thread.sleep(1000);
        Button ok = new Button(
                driver,
                By.xpath("//button[@id='template_x002e_start-workflow_x002e_start-workflow_x0023_default-startWorkflowForm-alf-id2_assoc_bpm_assignee-cntrl-ok-button']"));
        ok.click();
        Thread.sleep(5000);
        Button start = new Button(
                driver,
                By.xpath("//button[@id='template_x002e_start-workflow_x002e_start-workflow_x0023_default-startWorkflowForm-alf-id2-form-submit-button']"));
        start.click();
        Thread.sleep(10000);
    }
}
