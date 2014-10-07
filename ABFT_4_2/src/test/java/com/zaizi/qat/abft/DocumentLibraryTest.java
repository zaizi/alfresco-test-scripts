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
package com.zaizi.qat.abft;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;

import com.zaizi.automation.abfts.core.elements.Link;
import com.zaizi.automation.abfts.core.info.TestCaseProperties;
import com.zaizi.automation.abfts.core.info.TestCaseValues;
import com.zaizi.automation.abfts.core.pages.Dashboard;
import com.zaizi.automation.abfts.core.pages.LoginPage;
import com.zaizi.automation.abfts.core.pages.SiteDashboard;
import com.zaizi.automation.abfts.drivers.FirefoxDriverStore;
import com.zaizi.automation.exceptions.IterableException;

/**
 * @author kvaratharaja@zaizi.com
 * 
 */
@RunWith(value = Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DocumentLibraryTest
{
    /**
     * Define siteName
     */
    private String siteName;
    /**
     * Define siteId
     */
    private String siteId;
    /**
     * Define documentName
     */
    private String documentName;
    /**
     * userName
     */
    private String userName;

    /**
     * Define WebDriver
     */
    static WebDriver driver;

    /**
     * parameters pass via this constructor
     * 
     * @param siteName
     * @param siteId
     * @param documentName
     * @param userName
     */
    public DocumentLibraryTest(String siteName, String siteId, String documentName, String userName)
    {
        this.siteName = siteName;
        this.siteId = siteId;
        this.documentName = documentName;
        this.userName = userName;
    }

    /**
     * Declares parameters here { Site name, Site ID, Document Name, User name}, eg : {"Test Site", "test-site",
     * "Zaizi.pdf", "test"}
     * 
     * @return
     * @throws Exception 
     */
    @Parameters()
    public static Iterable<Object[]> data() throws IterableException
    {
        System.out.println("preparing DocumentLibraryTest ..");
        return TestCaseValues.documentLibraryTestValues("DocumentLibraryTest");
    }

    /**
     * createSite test case
     * 
     * @throws InterruptedException
     */
    @Test
    public void a_createSite() throws InterruptedException
    {
        System.out.println("running createSite test case");
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin();
        Thread.sleep(2000);
        Dashboard dashboard = new Dashboard(driver);
        dashboard.createPublicSite(siteName);
        dashboard.checkSiteAvailability(siteId);
        TestCaseProperties.closeDriver(driver);
    }
    
    /**
     * uploadDocument test case
     * 
     * @throws InterruptedException
     * @throws AWTException 
     */
    @Test
    public void b_uploadDocument() throws InterruptedException, AWTException
    {
        Robot robot = new Robot();  
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin();
        Thread.sleep(2000);
        Dashboard dashboard = new Dashboard(driver);
        dashboard.goToSite(siteName, siteId);
        Thread.sleep(4000);
        Link documentLibrary = new Link(driver, By.xpath("//a[contains(., 'Document Library')]"));
        documentLibrary.click();
        Thread.sleep(2000);
        driver.findElement(By.id("template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-fileUpload-button-button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"template_x002e_dnd-upload_x002e_documentlibrary_x0023_default-file-selection-button-overlay\"]/span/input")).click();
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(2000);
        writeFileName(documentName, robot);
        Thread.sleep(5000);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN); 
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);        
        robot.keyRelease(KeyEvent.VK_ENTER); 
        Thread.sleep(2000);
        
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB); 
        
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN); 
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);        
        robot.keyRelease(KeyEvent.VK_ENTER); 
        Thread.sleep(5000);
        TestCaseProperties.closeDriver(driver);
    }

    /**
     * viewDocument test case
     * 
     * @throws InterruptedException
     */
    @Test
    public void c_viewDocument() throws InterruptedException
    {
        System.out.println("running viewDocument test case");
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin();
        Thread.sleep(2000);
        Dashboard dashboard = new Dashboard(driver);
        dashboard.goToSite(siteName, siteId);
        SiteDashboard sdashboard = new SiteDashboard(driver);
        sdashboard.viewDocument(documentName);
        sdashboard.checkDocumentPreview();
        TestCaseProperties.closeDriver(driver);
    }

    /**
     * managePermissions test case
     * 
     * @throws InterruptedException
     */
    @Test
    public void d_managePermissions() throws InterruptedException
    {
        System.out.println("running managePermissions test case");
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin();
        Thread.sleep(2000);
        Dashboard dashboard = new Dashboard(driver);
        dashboard.goToSite(siteName, siteId);
        SiteDashboard sdashboard = new SiteDashboard(driver);
        sdashboard.viewDocument(documentName);
        sdashboard.managePermissions(userName);
        sdashboard.checkPermissions();
        TestCaseProperties.closeDriver(driver);
    }

    /**
     * deleteDocument test case
     * 
     * @throws InterruptedException
     */
    @Test
    public void e_deleteDocument() throws InterruptedException
    {
        System.out.println("running deleteDocument test case");
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin();
        Thread.sleep(2000);
        Dashboard dashboard = new Dashboard(driver);
        dashboard.goToSite(siteName, siteId);
        SiteDashboard sdashboard = new SiteDashboard(driver);
        sdashboard.viewDocument(documentName);
        sdashboard.deleteDocument();
        sdashboard.checkDocumentAvailability(documentName);
        TestCaseProperties.closeDriver(driver);
    }

    /**
     * deleteSite test case
     * 
     * @throws InterruptedException
     */
    @Test
    public void f_deleteSite() throws InterruptedException
    {
        System.out.println("running deleteSite test case");
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin();
        Thread.sleep(2000);
        Dashboard dashboard = new Dashboard(driver);
        dashboard.searchSite(siteName);
        dashboard.deleteSite();
        dashboard.searchSite(siteName);
        dashboard.checkSiteAvailability2();
        TestCaseProperties.closeDriver(driver);
    }

    private void writeFileName(String fileName, Robot robot){
        for (char ch: fileName.toCharArray()) {
            if(isUpperCase(ch)){
                robot.keyPress(KeyEvent.VK_SHIFT);
                writeUpperCaseLetter(ch, robot);
                robot.keyRelease(KeyEvent.VK_SHIFT); 
            }else{
                writeUpperCaseLetter(Character.toUpperCase(ch), robot);
            }
        }
    }
    
    private void writeUpperCaseLetter(char charLetter, Robot robot){
        robot.keyPress((int) charLetter);
        robot.keyRelease((int) charLetter); 
    }
    static boolean isUpperCase(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }
}
