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

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import com.zaizi.automation.abfts.core.info.TestCaseProperties;
import com.zaizi.automation.abfts.core.elements.Button;
import com.zaizi.automation.abfts.core.elements.TextField;

/**
 * @author kvithyashankar@zaizi.com
 *
 */
public class LoginPage
{

    /**
     * Defining WebDriver
     */
    private WebDriver driver;

    /**
     * @param driver
     */
    public LoginPage(WebDriver driver)
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
     * method prepareElements
     */
    public void prepareElements()
    {
        driver.get(TestCaseProperties.HOST_URL);
    }

    /**
     * @throws InterruptedException
     */
    public void loginAsAdmin() throws InterruptedException
    {
        Thread.sleep(2000);
        TextField userNameAdmin = new TextField(driver,
                By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-username']"));
        TextField passwordAdmin = new TextField(driver,
                By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-password']"));
        Button btnLognAdmin = new Button(driver,
                By.xpath("//button[@id='page_x002e_components_x002e_slingshot-login_x0023_default-submit-button']"));
        userNameAdmin.clearText();
        Thread.sleep(500);
        passwordAdmin.clearText();
        Thread.sleep(500);
        userNameAdmin.enterText(TestCaseProperties.ADMIN_USERNAME);
        passwordAdmin.enterText(TestCaseProperties.ADMIN_PASSWORD);
        btnLognAdmin.click();
        Thread.sleep(2000);

    }

    /**
     * @param username
     * @param password
     * @throws InterruptedException
     */
    public void loginAsUser(String username, String password) throws InterruptedException
    {
        Thread.sleep(2000);
        TextField userNameAdmin = new TextField(driver,
                By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-username']"));
        TextField passwordAdmin = new TextField(driver,
                By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-password']"));
        Button btnLognAdmin = new Button(driver,
                By.xpath("//button[@id='page_x002e_components_x002e_slingshot-login_x0023_default-submit-button']"));
        userNameAdmin.clearText();
        Thread.sleep(500);
        passwordAdmin.clearText();
        Thread.sleep(500);
        userNameAdmin.enterText(username);
        passwordAdmin.enterText(password);
        btnLognAdmin.click();
        Thread.sleep(2000);

    }

    /**
     * @throws InterruptedException
     */
    public void loginAsUserN() throws InterruptedException
    {
        Thread.sleep(2000);
        TextField userNameAdmin = new TextField(driver,
                By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-username']"));
        TextField passwordAdmin = new TextField(driver,
                By.xpath("//input[@id='page_x002e_components_x002e_slingshot-login_x0023_default-password']"));
        Button btnLognAdmin = new Button(driver,
                By.xpath("//button[@id='page_x002e_components_x002e_slingshot-login_x0023_default-submit-button']"));
        userNameAdmin.clearText();
        Thread.sleep(500);
        passwordAdmin.clearText();
        Thread.sleep(500);
        userNameAdmin.enterText("nolan");
        passwordAdmin.enterText("nolan");
        btnLognAdmin.click();
        Thread.sleep(2000);

    }

}
