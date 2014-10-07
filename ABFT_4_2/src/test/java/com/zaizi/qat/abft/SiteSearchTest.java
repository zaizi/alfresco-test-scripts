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
import com.zaizi.automation.abfts.core.pages.AdminConsole;
import com.zaizi.automation.abfts.core.pages.Dashboard;
import com.zaizi.automation.abfts.core.pages.LoginPage;
import com.zaizi.automation.abfts.core.pages.SiteDashboard;
import com.zaizi.automation.abfts.core.pages.UserDashboard;
import com.zaizi.automation.abfts.drivers.FirefoxDriverStore;
import com.zaizi.automation.exceptions.IterableException;

/**
 * @author kvaratharaja@zaizi.com
 * 
 */
@RunWith(value = Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SiteSearchTest
{
    /**
     * Define siteName
     */
    private String siteName;
    /**
     * Define isPrivate
     */
    private Boolean isPrivate;
    /**
     * Define userName
     */
    private String userName;
    /**
     * Define password
     */
    private String password;
    /**
     * Define isDefault
     */
    private Boolean isDefault;
    /**
     * Define firstname
     */
    private String firstname;
    /**
     * Define email
     */
    private String email;
    /**
     * Define WebDriver
     */
    static WebDriver driver;

    /**
     * parameters pass via this constructor
     * 
     * @param siteName
     * @param isPrivate
     * @param userName
     * @param password
     * @param isDefault
     * @param firstname
     * @param email
     */
    public SiteSearchTest(String siteName, Boolean isPrivate, String userName, String password, Boolean isDefault,
            String firstname, String email)
    {
        this.siteName = siteName;
        this.isPrivate = isPrivate;
        this.userName = userName;
        this.password = password;
        this.isDefault = isDefault;
        this.firstname = firstname;
        this.email = email;
    }

    /**
     * Declares parameters here { Site name, Private or Public Boolean, Username, Password, Default user Boolean, First
     * name, email}, eg : {"Test Site", "true", "test", "test", false, "Jason", "kvithyashankar@zaizi.com"}
     * 
     * @return
     * @throws Exception 
     */
    @Parameters()
    public static Iterable<Object[]> data() throws IterableException
    {
        System.out.println("preparing SiteSearchTest ..");
        return TestCaseValues.documentLibraryTestValues("SiteSearchTest");
    }

    /**
     * @throws InterruptedException
     */
    @Test
    public void a_createUser() throws InterruptedException
    {
        if (!isDefault)
        {
            System.out.println("running createSite test case");
            driver = TestCaseProperties.getWebDriver();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAsAdmin();
            Thread.sleep(2000);
            AdminConsole console = new AdminConsole(driver);
            console.goToUsers();
            console.createNewUser(firstname, userName, password, email);
            console.searchUser(firstname);
            console.checkUserPresence(firstname);
            TestCaseProperties.closeDriver(driver);
        }
    }

    /**
     * @throws InterruptedException
     */
    @Test
    public void b_searchSite() throws InterruptedException
    {
        System.out.println("running searchSite test case");
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
        Thread.sleep(2000);
        dashboard.logOut();
        loginPage.loginAsUser(userName, password);
        Thread.sleep(2000);
        dashboard.searchSite(siteName);
        UserDashboard udashboard = new UserDashboard(driver);
        if (isPrivate)
        {
            udashboard.privateSiteVisibility();
        }
        else
        {
            udashboard.publicSitevisibility1();
        }
        dashboard.logOut();
        loginPage.loginAsAdmin();
        Thread.sleep(2000);
        dashboard.searchSite(siteName);
        dashboard.deleteSite();
        TestCaseProperties.closeDriver(driver);
    }

    /**
     * @throws InterruptedException
     */
    @Test
    public void c_deleteUser() throws InterruptedException
    {
        if (!isDefault)
        {
            System.out.println("running deleteUser test case");
            driver = TestCaseProperties.getWebDriver();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAsAdmin();
            Thread.sleep(2000);
            AdminConsole console = new AdminConsole(driver);
            console.goToUsers();
            console.searchUser(firstname);
            console.goToUserProfile(firstname);
            console.deleteUser();
            console.searchUser(firstname);
            console.checkUserPresence2();
            TestCaseProperties.closeDriver(driver);
        }
    }
}
