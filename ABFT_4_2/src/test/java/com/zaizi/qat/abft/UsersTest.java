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
import com.zaizi.automation.abfts.core.pages.LoginPage;
import com.zaizi.automation.abfts.drivers.FirefoxDriverStore;
import com.zaizi.automation.exceptions.IterableException;

/**
 * @author kvaratharaja@zaizi.com
 * 
 */
@RunWith(value = Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsersTest
{
    /**
     * Define firstname
     */
    private String firstname;
    /**
     * Define username
     */
    private String username;
    /**
     * Define password
     */
    private String password;
    /**
     * Define email
     */
    private String email;
    /**
     * Define newUsername
     */
    private String newUsername;
    /**
     * Define WebDriver
     */
    static WebDriver driver;

    /**
     * parameters pass via this constructor
     * 
     * @param firstname
     * @param username
     * @param password
     * @param email
     * @param newUsername
     */
    public UsersTest(String firstname, String username, String password, String email, String newUsername)
    {
        this.firstname = firstname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.newUsername = newUsername;
    }

    /**
     * Declares parameters here { First name, User name, Password, email, New first name} eg : { "Jason", "test1",
     * "test1", "kvithyashankar@zaizi.com", "Mark"}
     * 
     * @return
     * @throws Exception 
     */
    @Parameters()
    public static Iterable<Object[]> data() throws IterableException
    {
        System.out.println("preparing UsersTest ..");
        return TestCaseValues.documentLibraryTestValues("UsersTest");
    }

    /**
     * @throws InterruptedException
     */
    @Test
    public void a_createUser() throws InterruptedException
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

    /**
     * @throws InterruptedException
     */
    @Test
    public void b_editUser() throws InterruptedException
    {
        System.out.println("running editUser test case");
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin();
        Thread.sleep(2000);
        AdminConsole console = new AdminConsole(driver);
        console.goToUsers();
        console.searchUser(firstname);
        console.goToUserProfile(firstname);
        console.editUser(newUsername);
        console.checkUserPresence3();
        TestCaseProperties.closeDriver(driver);
    }

    /**
     * @throws InterruptedException
     */
    @Test
    public void c_deleteUser() throws InterruptedException
    {
        System.out.println("running deleteUser test case");
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin();
        Thread.sleep(2000);
        AdminConsole console = new AdminConsole(driver);
        console.goToUsers();
        console.searchUser(newUsername);
        console.goToUserProfile(newUsername);
        console.deleteUser();
        console.searchUser(newUsername);
        console.checkUserPresence2();
        TestCaseProperties.closeDriver(driver);
    }

}
