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
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;

import com.zaizi.automation.abfts.core.info.TestCaseProperties;
import com.zaizi.automation.abfts.core.info.TestCaseValues;
import com.zaizi.automation.abfts.core.pages.AdminConsole;
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
public class LoginTest
{
    /**
     * Define username
     */
    private String username;
    /**
     * Define password
     */
    private String password;
    /**
     * Define userlink
     */
    private String userlink;
    /**
     * Define isDefault user boolean
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
     * @param username
     * @param password
     * @param userlink
     * @param isDefault
     * @param firstname
     * @param email
     */
    public LoginTest(String username, String password, String userlink, Boolean isDefault, String firstname,
            String email)
    {
        this.username = username;
        this.password = password;
        this.userlink = userlink;
        this.isDefault = isDefault;
        this.firstname = firstname;
        this.email = email;
    }

    /**
     * Declares parameters here
     * 
     * @return
     * @throws Exception 
     */
    @Parameters()
    public static Iterable<Object[]> data() throws IterableException
    {
        System.out.println("preparing LoginTest ..");
        return TestCaseValues.documentLibraryTestValues("LoginTest");
    }

    /**
     * createUser test case
     * 
     * @throws InterruptedException
     */
    @Test
    public void a_createUser() throws InterruptedException
    {
        if (!isDefault)
        {
            System.out.println("running createUser test case");
            driver = TestCaseProperties.getWebDriver();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAsAdmin();
            Thread.sleep(2000);
            AdminConsole console = new AdminConsole(driver);
            console.goToUsers();
            console.createNewUser(firstname, username, password, email);
            console.searchUser(firstname);
            console.checkUserPresence(firstname);
            TestCaseProperties.closeDriver(driver);
        }
    }

    /**
     * login test case
     * 
     * @throws InterruptedException
     */
    @Test
    public void b_login() throws InterruptedException
    {
        System.out.println("running login test case");
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard = new Dashboard(driver);
        loginPage.loginAsUser(username, password);
        Thread.sleep(2000);
        dashboard.checkUserButton(userlink);
        TestCaseProperties.closeDriver(driver);
    }

    /**
     * logout test case
     * 
     * @throws InterruptedException
     */
    @Test
    public void c_logout() throws InterruptedException
    {
        System.out.println("running logout test case");
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        try
        {
            loginPage.loginAsUser(username, password);
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Dashboard dashboard = new Dashboard(driver);
        dashboard.logOut();
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
