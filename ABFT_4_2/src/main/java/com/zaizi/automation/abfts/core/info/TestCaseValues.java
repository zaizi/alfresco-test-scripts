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
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zaizi.automation.exceptions.IterableException;

/**
 * @author kvaratharaja@zaizi.com
 * 
 */
public class TestCaseValues
{
    /**
     * Defining test case values xml path
     */
    private static final String TEST_CASE_VALUES_XML = "src/test/resources/TestValues.xml";

    /**
     * @param testCaseName
     * @return
     * @throws Exception
     */
    public static Iterable<Object[]> documentLibraryTestValues(final String testCaseName)
            throws IterableException
    {
        Iterable<Object[]> result = null;
        NodeList testdata;
        try
        {
            testdata = getTestData(testCaseName);
        }
        catch (ParserConfigurationException e)
        {
            throw new IterableException("ParserConfigurationException : ", e);
        }
        catch (SAXException e)
        {
            throw new IterableException("SAXException : ", e);
        }
        catch (IOException e)
        {
            throw new IterableException("IOException : ", e);
        }
        int noOfTestValues = getNumberOfValues(testdata);
        Object[][] bar = new Object[noOfTestValues][];
        int k = 0;
        for (int j = 0; j < testdata.getLength(); j++)
        {
            Node valueNode = testdata.item(j);
            if (valueNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element valueElement = (Element) valueNode;
                if (("DocumentLibraryTest").equals(testCaseName))
                {
                    Object[] foo = { getValue("siteName", valueElement), getValue("siteId", valueElement),
                            getValue("documentName", valueElement), getValue("userName", valueElement) };
                    bar[k] = foo;
                }
                else if (("InviteExternalUserTest").equals(testCaseName))
                {
                    Object[] foo = { getValue("siteName", valueElement), getValue("siteId", valueElement),
                            getValue("firstname", valueElement), getValue("lastname", valueElement),
                            getValue("email", valueElement), getValue("emailUserName", valueElement),
                            getValue("emailPassword", valueElement) };
                    bar[k] = foo;
                }
                else if (("InviteInternalUserTest").equals(testCaseName))
                {
                    Object[] foo = { getValue("siteName", valueElement), getValue("firstname", valueElement),
                            getValue("username", valueElement), getValue("password", valueElement),
                            Boolean.valueOf(getValue("isDefault", valueElement)), getValue("email", valueElement) };
                    bar[k] = foo;
                }
                else if (("LoginTest").equals(testCaseName))
                {
                    Object[] foo = { getValue("username", valueElement), getValue("password", valueElement),
                            getValue("userlink", valueElement), Boolean.valueOf(getValue("isDefault", valueElement)),
                            getValue("firstname", valueElement), getValue("email", valueElement) };
                    bar[k] = foo;
                }
                else if (("MainDashboardTest").equals(testCaseName))
                {
                    Object[] foo = { getValue("dashletTitle", valueElement) };
                    bar[k] = foo;
                }
                else if (("SiteDashboardTest").equals(testCaseName))
                {
                    Object[] foo = { getValue("siteName", valueElement), getValue("dashletTitle", valueElement) };
                    bar[k] = foo;
                }
                else if (("SiteSearchTest").equals(testCaseName))
                {
                    Object[] foo = { getValue("siteName", valueElement),
                            Boolean.valueOf(getValue("isPrivate", valueElement)), getValue("userName", valueElement),
                            getValue("password", valueElement), Boolean.valueOf(getValue("isDefault", valueElement)),
                            getValue("firstname", valueElement), getValue("email", valueElement) };
                    bar[k] = foo;
                }
                else if (("SiteTest").equals(testCaseName))
                {
                    Object[] foo = { getValue("siteName", valueElement), getValue("siteId", valueElement),
                            Boolean.valueOf(getValue("isPrivate", valueElement)),
                            getValue("newSiteName", valueElement), getValue("newSiteId", valueElement) };
                    bar[k] = foo;
                }
                else if (("UserGroupTest").equals(testCaseName))
                {
                    Object[] foo = { getValue("groupName", valueElement), getValue("groupId", valueElement) };
                    bar[k] = foo;
                }
                else if (("UsersTest").equals(testCaseName))
                {
                    Object[] foo = { getValue("firstname", valueElement), getValue("username", valueElement),
                            getValue("password", valueElement), getValue("email", valueElement),
                            getValue("newUsername", valueElement) };
                    bar[k] = foo;
                }
                k++;
            }
        }
        result = Arrays.asList(bar);
        return result;
    }

    /**
     * @param testCaseName
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private static NodeList getTestData(final String testCaseName) throws ParserConfigurationException, SAXException,
            IOException
    {
        File testValues = new File(TEST_CASE_VALUES_XML);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(testValues);
        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName(testCaseName);
        Node node = nodes.item(0);
        NodeList testdata = node.getChildNodes();
        return testdata;
    }

    /**
     * @param nodeList
     * @return
     */
    private static int getNumberOfValues(final NodeList nodeList)
    {
        int length = 0;
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                length++;
            }
        }
        return length;
    }

    /**
     * @param tag
     * @param element
     * @return
     */
    private static String getValue(final String tag, final Element element)
    {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }
}
