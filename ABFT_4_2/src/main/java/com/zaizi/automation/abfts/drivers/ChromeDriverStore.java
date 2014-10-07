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
package com.zaizi.automation.abfts.drivers;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author kvithyashankar@zaizi.com
 *
 */
public class ChromeDriverStore
{

    /**
     * Defining WebDriver
     */
    private ChromeDriver driver;
    /**
     * Defining driverPath
     */
    private String driverPath;

    /**
     * @param driverPath
     */
    public ChromeDriverStore(String driverPath)
    {
        this.driverPath = driverPath;
        System.setProperty("webdriver.chrome.driver", this.driverPath);
    }

    /**
     * @return
     */
    public ChromeDriver createWebDriver()
    {
        this.driver = new ChromeDriver();
        return driver;
    }

}
