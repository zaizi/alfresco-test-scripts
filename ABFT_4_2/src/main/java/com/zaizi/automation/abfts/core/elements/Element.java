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
package com.zaizi.automation.abfts.core.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author kvithyashankar@zaizi.com
 * 
 */
public class Element
{
    /**
     * Defining driver
     */
    private WebDriver driver;
    /**
     * Defining element
     */
    private WebElement element;

    /**
     * @param driver
     * @param elementIdentifier it could be xpath, id, class etc. example : by.xpath("//input[@id='btn1']")
     */
    public Element(WebDriver driver, By elementIdentifier)
    {
        this.driver = driver;
        element = this.driver.findElement(elementIdentifier);
    }

    /**
     * click method
     */
    public void click()
    {
        element.click();
    }

    /**
     * @param attribute
     * @return
     */
    public String getElementAttribute(String attribute)
    {
        return element.getAttribute(attribute);
    }

    /**
     * @return
     */
    public WebElement getWebElement()
    {
        return this.element;
    }

    /**
     * @return
     */
    public WebDriver getDriver()
    {
        return this.driver;
    }
}
