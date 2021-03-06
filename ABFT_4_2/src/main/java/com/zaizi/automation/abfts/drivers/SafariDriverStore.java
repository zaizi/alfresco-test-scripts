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

import org.openqa.selenium.safari.SafariDriver;

/**
 * @author kvithyashankar@zaizi.com
 *
 */
public class SafariDriverStore
{

    /**
     * Defining WebDriver
     */
    private SafariDriver driver;

    /**
     * @return
     */
    public SafariDriver createWebDriver()
    {
        driver = new SafariDriver();
        return driver;
    }

}
