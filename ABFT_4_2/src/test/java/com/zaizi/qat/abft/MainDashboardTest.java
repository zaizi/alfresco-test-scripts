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
public class MainDashboardTest
{
    /**
     * Define dashletTitle
     */
    private String dashletTitle;

    /**
     * Define WebDriver
     */
    static WebDriver driver;

    // parameters pass via this constructor
    /**
     * @param dashletTitle
     */
    public MainDashboardTest(String dashletTitle)
    {
        this.dashletTitle = dashletTitle;
    }

    /**
     * Declares parameters here { Dashlet Title} eg : {
     * "RSS Feed - Display the contents of an RSS feed given a URL configuration parameter."}
     * 
     * @return
     * @throws Exception 
     */
    @Parameters()
    public static Iterable<Object[]> data() throws IterableException
    {
        System.out.println("preparing MainDashboardTest ..");
        return TestCaseValues.documentLibraryTestValues("MainDashboardTest");
    }

    /**
     * addDashlet test case
     * 
     * @throws InterruptedException
     */
    @Test
    public void a_addDashlet() throws InterruptedException
    {
        System.out.println("running addDashlet test case");
        driver = TestCaseProperties.getWebDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin();
        Thread.sleep(2000);
        Dashboard dashboard = new Dashboard(driver);
        dashboard.customizeDashboard(dashletTitle);
        TestCaseProperties.closeDriver(driver);
    }
}
