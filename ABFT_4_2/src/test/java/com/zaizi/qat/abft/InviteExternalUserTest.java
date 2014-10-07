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
import com.zaizi.automation.abfts.core.pages.UserDashboard;
import com.zaizi.automation.abfts.drivers.FirefoxDriverStore;
import com.zaizi.automation.exceptions.IterableException;

/**
 * @author kvaratharaja@zaizi.com
 * 
 */
@RunWith(value = Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InviteExternalUserTest
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
     * Define firstname
     */
    private String firstname;
    /**
     * Define lastname
     */
    private String lastname;
    /**
     * Define email
     */
    private String email;
    /**
     * Define emailUserName
     */
    private String emailUserName;
    /**
     * Define emailPassword
     */
    private String emailPassword;
    /**
     * Define WebDriver
     */
    static WebDriver driver;

    /**
     * parameters pass via this constructor
     * 
     * @param siteName
     * @param siteId
     * @param firstname
     * @param lastname
     * @param email
     * @param emailUserName
     * @param emailPassword
     */
    public InviteExternalUserTest(String siteName, String siteId, String firstname, String lastname, String email,
            String emailUserName, String emailPassword)
    {
        this.siteName = siteName;
        this.siteId = siteId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.emailUserName = emailUserName;
        this.emailPassword = emailPassword;
    }

    //
    //
    /**
     * Declares parameters here { Site name, Site Id, First name, Last Name, Email, Email username, Email password}, eg
     * : {"Test Site Invite External", "test-site-invite-external", "Micheal", "Scofield", "zaizi.vdr@gmail.com",
     * "zaizi.vdr", "zaizi123"}
     * 
     * @return
     * @throws Exception 
     */
    @Parameters()
    public static Iterable<Object[]> data() throws IterableException
    {
        System.out.println("preparing InviteExternalUserTest ..");
        return TestCaseValues.documentLibraryTestValues("InviteExternalUserTest");
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
     * invite test case
     * 
     * @throws InterruptedException
     */
    @Test
    public void a_invite() throws InterruptedException
    {
        System.out.println("running invite test case");
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin();
        Thread.sleep(2000);
        Dashboard dashboard = new Dashboard(driver);
        dashboard.goToSite(siteName, siteId);
        dashboard.inviteExternalUser(firstname, lastname, email);
        dashboard.externalUserInvitationCheck(emailUserName, emailPassword);
        TestCaseProperties.closeDriver(driver);
    }

    /**
     * deleteSite test case
     * 
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
        dashboard.searchSite(siteName);
        dashboard.deleteSite();
        dashboard.searchSite(siteName);
        dashboard.checkSiteAvailability2();
        TestCaseProperties.closeDriver(driver);
    }

    /**
     * deleteUser test case
     * 
     * @throws InterruptedException
     */
    @Test
    public void d_deleteUser() throws InterruptedException
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
