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

import com.zaizi.automation.abfts.core.elements.Button;
import com.zaizi.automation.abfts.core.elements.Division;
import com.zaizi.automation.abfts.core.elements.Link;
import com.zaizi.automation.abfts.core.elements.Span;

/**
 * @author kvithyashankar@zaizi.com
 *
 */
public class UserDashboard
{

    /**
     * Defining log4j
     */
    private static final Logger LOGGER = Logger.getLogger(UserDashboard.class);
    
    private static final String TEXT_TEST_PASSED = "Test case passed!";
    private static final String TEXT_TEST_FAILED = "Test case failed!";
    
    /**
     * Defining WebDriver
     */
    private WebDriver driver;

    /**
     * @param driver
     */
    public UserDashboard(WebDriver driver)
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
     * @param siteName
     * @throws InterruptedException
     */
    public void confirmOnMyTasks(String siteName) throws InterruptedException
    {
        Button tasks = new Button(driver, By.xpath("//span[@id='HEADER_TASKS_text']"));
        tasks.click();
        Thread.sleep(2000);
        Link myTasks = new Link(driver, By.xpath("//a[contains(., 'My Tasks')]"));
        myTasks.click();
        Thread.sleep(2000);
        Link invitation = new Link(driver, By.xpath("//a[@title='Edit Task' and contains(., 'Invitation to join "
                + siteName + " site')]"));
        if (invitation.getWebElement().isDisplayed())
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
    public void publicSitevisibility() throws InterruptedException
    {
        Thread.sleep(2000);
        Link siteName = new Link(driver, By.xpath("//a[@href='/share/page/site/test-site/dashboard']"));
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
    public void publicSitevisibility1() throws InterruptedException
    {
        Thread.sleep(2000);
        Link siteName = new Link(driver, By.xpath("//a[@href='/share/page/site/alfresco/dashboard']"));
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
    public void privateSiteVisibility() throws InterruptedException
    {
        Thread.sleep(2000);
        Span result = new Span(driver, By.xpath("//span[contains(., 'No sites found')]"));
        if (result.getWebElement().isDisplayed())
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
    public void reviewWorkflowUser() throws InterruptedException
    {
        Thread.sleep(5000);
        Button more = new Button(driver, By.xpath("//button[@id='global_x002e_header_x0023_default-app_more-button']"));
        more.click();
        Link task = new Link(driver, By.xpath("//a[contains(., 'My Tasks')]"));
        task.click();
        Thread.sleep(2000);
        Link name = new Link(driver, By.xpath("//a[contains(., 'Review Task')]"));
        name.click();
        Thread.sleep(2000);
        Button approve = new Button(
                driver,
                By.xpath("//button[@id='page_x002e_data-form_x002e_task-edit_x0023_default_prop_wf_reviewOutcome-Approve-button']"));
        approve.click();
        driver.navigate().refresh();
        Thread.sleep(3000);
        Division result = new Division(driver, By.xpath("//td/div[contains(., 'No tasks')]"));
        if (result.getWebElement().isDisplayed())
        {
            LOGGER.info("The workflow was reviewed by the user!");
        }
        else
        {
            LOGGER.info("The workflow was not reviewed!");
        }
    }

    /**
     * @throws InterruptedException
     */
    public void reviewWorkflowAfterUser() throws InterruptedException
    {
        Thread.sleep(5000);
        Button more = new Button(driver, By.xpath("//button[@id='global_x002e_header_x0023_default-app_more-button']"));
        more.click();
        Link task = new Link(driver, By.xpath("//a[contains(., 'My Tasks')]"));
        task.click();
        Thread.sleep(2000);
        Link approval = new Link(driver, By.xpath("//a[contains(., 'The document was reviewed and approved.')]"));
        approval.click();
        Thread.sleep(1000);
        Button done = new Button(
                driver,
                By.xpath("//button[@id='page_x002e_data-form_x002e_task-edit_x0023_default_prop_transitions-Next-button']"));
        done.click();
        Thread.sleep(1000);
        driver.navigate().refresh();
        Division result = new Division(driver, By.xpath("//td/div[contains(., 'No tasks')]"));
        if (result.getWebElement().isDisplayed())
        {
            LOGGER.info("The workflow was closed by the admin!");
        }
        else
        {
            LOGGER.info("The workflow was not closed!");
        }
    }
}
