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

@RunWith(value = Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InviteInternalUserTest {
	private String siteName;
	private String firstname;
	private String username;
	private String password;
	private Boolean isDefault;
	private String email;
	
	static WebDriver driver;
	 
	//parameters pass via this constructor
	public InviteInternalUserTest(String siteName, String firstname, String username, String password, Boolean isDefault, 
			String email) {
		this.siteName = siteName;
		this.firstname = firstname;
		this.username = username;
		this.password = password;
		this.isDefault = isDefault;
		this.email = email;
	}
	
	//Declares parameters here
	// { Site name, First name, Username, Password}, eg : {"Test Site Invite Internal", "Test", "test", "test"}
	@Parameters()
	public static Iterable<Object[]> data() throws IterableException {
		System.out.println("preparing InviteInternalUserTest ..");
		return TestCaseValues.documentLibraryTestValues("InviteInternalUserTest");
	}
	
	@Test
	public void a_createUser() throws InterruptedException{
		if(!isDefault){
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
	@Test
	public void a_invite() throws InterruptedException{
		System.out.println("running invite test case");
		driver = TestCaseProperties.getWebDriver();
		LoginPage loginPage = new LoginPage(driver);
	    loginPage.loginAsAdmin();
	    Thread.sleep(2000);
	    Dashboard dashboard = new Dashboard(driver);
	    dashboard.createPublicSite(siteName);
	    dashboard.inviteInternalUser(firstname);
	    dashboard.logOut();
	    loginPage.loginAsUser(username, password);
	    UserDashboard uDashboard = new UserDashboard(driver);
	    uDashboard.confirmOnMyTasks(siteName);
	    TestCaseProperties.closeDriver(driver);
	}
	
	@Test
	public void c_deleteSite() throws InterruptedException{
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
	
	@Test
	public void d_deleteUser() throws InterruptedException{
		if(!isDefault){
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
