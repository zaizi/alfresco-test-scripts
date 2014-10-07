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

import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;

import com.zaizi.automation.abfts.core.info.TestCaseProperties;
import com.zaizi.automation.abfts.core.info.TestCaseValues;
import com.zaizi.automation.abfts.core.pages.Dashboard;
import com.zaizi.automation.abfts.core.pages.LoginPage;
import com.zaizi.automation.abfts.drivers.FirefoxDriverStore;
import com.zaizi.automation.exceptions.IterableException;

/**
 * @author kvaratharaja@zaizi.com
 * 
 */
@RunWith(value = Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SiteTest
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
     * Define newSiteName
     */
    private String newSiteName;
    /**
     * Define newSiteId
     */
    private String newSiteId;
    /**
     * Define isPrivate
     */
    private Boolean isPrivate;

    /**
     * Define WebDriver
     */
    static WebDriver driver;

    /**
     * parameters pass via this constructor
     * 
     * @param siteName
     * @param siteId
     * @param isPrivate
     * @param newSiteName
     * @param newSiteId
     */
    public SiteTest(String siteName, String siteId, Boolean isPrivate, String newSiteName, String newSiteId)
    {
        this.siteName = siteName;
        this.siteId = siteId;
        this.isPrivate = isPrivate;
        this.newSiteName = newSiteName;
        this.newSiteId = newSiteId;
    }

    /**
     * Declares parameters here { Site name, Site ID, Private or Public Boolean, Edited Site name, Edited Site ID} eg :
     * { "Test Site 1", "test-site-1", true - Private, "Edited Site 1", "edited-site-1"}
     * 
     * @return
     * @throws Exception 
     */
    @Parameters()
    public static Iterable<Object[]> data() throws IterableException
    {
        System.out.println("preparing SiteTest ..");
        return TestCaseValues.documentLibraryTestValues("SiteTest");
    }

    /**
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
        if (isPrivate)
        {
            dashboard.createPrivateSite(siteName);
        }
        else
        {
            dashboard.createPublicSite(siteName);
        }
        dashboard.checkSiteAvailability(siteId);
        TestCaseProperties.closeDriver(driver);
    }

    /**
     * @throws InterruptedException
     */
    @Test
    public void b_editSite() throws InterruptedException
    {
        System.out.println("running editSite test case");
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin();
        Thread.sleep(2000);
        Dashboard dashboard = new Dashboard(driver);
        dashboard.goToSite(siteName, siteId);
        dashboard.editSite(newSiteName);
        dashboard.navigateToDashboard();
        dashboard.searchSite(siteName);
        dashboard.checkSiteAvailability2();
        TestCaseProperties.closeDriver(driver);
    }

    /**
     * @throws InterruptedException
     */
    @Test
    public void c_deleteSite() throws InterruptedException
    {
        System.out.println("running deleteSite test case");
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin();
        Thread.sleep(2000);
        Dashboard dashboard = new Dashboard(driver);
        dashboard.searchSite(newSiteName);
        dashboard.deleteSite();
        dashboard.searchSite(newSiteName);
        dashboard.checkSiteAvailability2();
        TestCaseProperties.closeDriver(driver);
    }

}
