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
package com.zaizi.automation.abfts.core.info;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zaizi.automation.abfts.drivers.ChromeDriverStore;
import com.zaizi.automation.abfts.drivers.FirefoxDriverStore;
import com.zaizi.automation.abfts.drivers.SafariDriverStore;

/**
 * @author kvaratharaja@zaizi.com
 * 
 */
public class TestCaseProperties
{
    /**
     * Defining log4j
     */
    private static final Logger LOGGER = Logger.getLogger(TestCaseProperties.class);

    /**
     * Defining Alfresco Share URL
     */
    public static final String HOST_URL = getPropertyValue("ServerUrl");
    /**
     * Defining Alfresco Share Admin username
     */
    public static final String ADMIN_USERNAME = getPropertyValue("AdminUsername");
    /**
     * Defining Alfresco Share Admin password
     */
    public static final String ADMIN_PASSWORD = getPropertyValue("AdminPassword");
    /**
     * Defining Browser (Firefox or Safari or Chrome)
     */
    public static final String BROWSER = getPropertyValue("Browser");
    /**
     * Defining Chrome driver path
     */
    public static final String CHROME_DRIVER_PATH = getPropertyValue("ChromeDriverPath");
    /**
     * Defining zero result string for user search
     */
    public static final String SEARCH_STRING = getPropertyValue("FoundZeroResultsString");
    /**
     * Defining zero result string for document search
     */
    public static final String TEXT = getPropertyValue("FoundZeroSitesString");
    /**
     * Defining test case properties xml path
     */
    private static final String TEST_CASE_PROPERTIES_XML = "src/test/resources/TestProperties.xml";
    /**
     * Defining WebDriver
     */
    private static WebDriver driver = null;
    /**
     * getWebDriver method
     * @return
     */
    public static WebDriver getWebDriver()
    {
        if (driver != null)
        {
            closeDriver(driver);
        }
        if ("Firefox".equals(BROWSER))
        {
            FirefoxDriverStore webDrvFac = new FirefoxDriverStore();
            driver = webDrvFac.createWebDriver();
        }
        else if ("Chrome".equals(BROWSER))
        {
            ChromeDriverStore webDrvFac = new ChromeDriverStore(CHROME_DRIVER_PATH);
            driver = webDrvFac.createWebDriver();
        }
        else if ("Safari".equals(BROWSER))
        {
            SafariDriverStore webDrvFac = new SafariDriverStore();
            driver = webDrvFac.createWebDriver();
        }
        driver.get(HOST_URL);
        return driver;
    }

    /**
     * @param oldDriver
     */
    public static void closeDriver(WebDriver oldDriver)
    {
        oldDriver.close();
        driver = null;
    }

    /**
     * @param propertyName
     * @return
     */
    public static String getPropertyValue(String propertyName)
    {
        String result = null;
        try
        {
            Node serverUrl = getProperty(propertyName);
            result = serverUrl.getNodeValue();
        }
        catch (ParserConfigurationException e)
        {
            LOGGER.error("ParserConfigurationException", e);
        }
        catch (SAXException e)
        {
            LOGGER.error("SAXException", e);
        }
        catch (IOException e)
        {
            LOGGER.error("IOException", e);
        }
        return result;
    }

    /**
     * @param PropertyName
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private static Node getProperty(String propertyName) throws ParserConfigurationException, SAXException, IOException
    {
        File testValues = new File(TEST_CASE_PROPERTIES_XML);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(testValues);
        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName(propertyName);
        Node node = nodes.item(0);
        NodeList testdata = node.getChildNodes();
        return testdata.item(0);
    }
}
