/* Copyright 2014 URL IS/IT
 * Licensed under the Apache License, Version 2.0 (the "License"); you  may  not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless  required  by  applicable  law  or  agreed  to  in  writing,  software
 * distributed under the License is distributed on an  "AS  IS"  BASIS,  WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either  express  or  implied.  See  the
 * License for the specific language governing permissions and limitations under
 * the License. */
package com.urlisit.siteswrapper.srs.gas;

import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.urlisit.siteswrapper.srs.SitesWrapper;

/**
 * <a href="https://code.google.com/p/sites-wrapper/wiki/AppsScriptConfigurationClient" >
 * SitesWrapper-GAS-1</a> - Bind the spreadsheet to the datastore, initialize
 * the spreadsheet's configuration and update the datastore with the initial
 * configuration.
 * <p>
 * Initially neither the spreadsheet nor the datastore contain information, and
 * there ins't an established relationship of trust between the two. In order
 * to overcome this situation, the user of the configuration client binds the
 * configuration document (i.e.; the newly created spreadsheet) to the newly
 * created Server Service Wrapper, which at this point will have an empty
 * data-store, and creates a new, default configuration which matches between
 * the two (i.e.; the spreadsheet and the datastore).
 * <p>
 * This goal is accomplished by the Configuration Client composing and sending
 * a DKIM (DomainKeys Identified Mail) verified email to the Service Server 
 * Wrapper having the application id in the subject line and the Google Apps 
 * document id of the Configuration Client in the id field of the mail header.
 * Since the datastore is empty, and DKIM verified domain of the sender matches
 * the domain of the receiver, the Server Service Wrapper can be certain the
 * document id contained in the header is in fact authorized by the application
 * owner to initialize and subsequently update the configuration in the
 * datastore.
 *
 * @author Todd Url
 */
public class SitesWrapper_GAS_1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  /**
   * Precondition
   * 
   * This Test Script requires that:
   * <ul>
   * <li>SitesWrapper-GAE-GWT is installed in (i.e.; deployed to) Google App Engine
   * as an application administered by the same Google account as the primary actor,
   * but not yet configured in either the Configuration Client or the datastore.
   * <li>The primary actor knows the application id of the application in Google App
   * Engine.
   * <li>The primary actor is currently logged in (authenticated) to their Google Account.
   * <li><bold>Note</bold> - In the case that SitesWrapper has already been installed and
   * configured, the Entities in the datastore must be dropped and the configuration
   * sheets in the Configuration Client spreadsheet deleted in order to execute this
   * Test Script.
   * </ul>
   * 
   * @throws Exception
   */
  @Before
  public void precondition() throws Exception {
    /*
     * Determine if SitesWrapper has been installed and configured using the
     * same technique as the Configuration Client by executing an HTTP POST
     * against /isInitialized in the Server Service Wrapper which will return
     * 202 if the client has been bound to the server. If it has, the sheets
     * in the spreadsheet need to be deleted along with the ConfigurationId,
     * Site, Style, Item, Landing and Page objects in the datastore.
     */
    URL isInitialized = new URL(SitesWrapper.SITESWRAPPER_PRODUCTION_URL + "isInitialized");
    HttpURLConnection connection = (HttpURLConnection) isInitialized.openConnection();
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);
    DataOutputStream output = new DataOutputStream(connection.getOutputStream());
    output.writeBytes("");
    output.flush();
    output.close();
    int isInstalled = connection.getResponseCode();
    connection.disconnect();
    if (isInstalled == 202) {
      /*
       * SitesWrapper has been bound and configured.
       */
      startDriver();
      /*
       * Log into the datastore viewer
       */
      //driver.get(baseUrl + "/ServiceLogin?service=ah&passive=true&continue=https%3A%2F%2Fappengine.google.com%2F_ah%2Fconflogin%3Fcontinue%3Dhttps%3A%2F%2Fappengine.google.com%2Fdatastore%2Fexplorer%253F%2526app_id%253Ds%7Esiteswrapper&ltmpl=ae");
      driver.get(baseUrl + SitesWrapper.DATASTORE_VIEWER_LOGIN_URI);
      driver.findElement(By.id("Email")).clear();
      driver.findElement(By.id("Email")).sendKeys(SitesWrapper.ADMINISTRATOR_EMAIL);
      driver.findElement(By.id("Passwd")).clear();
      driver.findElement(By.id("Passwd")).sendKeys(SitesWrapper.getAdministratorPasswd());
      driver.findElement(By.id("signIn")).click();
      /*
       * Delete the Site object
       */
      new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Site");
      driver.findElement(By.cssSelector("th.cbc.tct-selectall > input[type=\"checkbox\"]")).click();
      driver.findElement(By.id("ae-datastore-explorer-delete")).click();
      assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected items[\\s\\S]$"));
      /*
       * Delete the Style collection
       */
      new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Style");
      driver.findElement(By.cssSelector("th.cbc.tct-selectall > input[type=\"checkbox\"]")).click();
      driver.findElement(By.id("ae-datastore-explorer-delete")).click();
      assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected items[\\s\\S]$"));
      /*
       * Delete the Item collection
       */
      new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Item");
      driver.findElement(By.cssSelector("th.cbc.tct-selectall > input[type=\"checkbox\"]")).click();
      driver.findElement(By.id("ae-datastore-explorer-delete")).click();
      assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected items[\\s\\S]$"));
      /*
       * Delete the Landing collection
       */
      new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Landing");
      driver.findElement(By.cssSelector("th.cbc.tct-selectall > input[type=\"checkbox\"]")).click();
      driver.findElement(By.id("ae-datastore-explorer-delete")).click();
      assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected items[\\s\\S]$"));
      /*
       * Delete the Page collection
       */
      new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Page");
      driver.findElement(By.cssSelector("th.cbc.tct-selectall > input[type=\"checkbox\"]")).click();
      driver.findElement(By.id("ae-datastore-explorer-delete")).click();
      assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected items[\\s\\S]$"));
      /*
       * Delete the DocumentId object
       */
      new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("DocumentId");
      driver.findElement(By.cssSelector("th.cbc.tct-selectall > input[type=\"checkbox\"]")).click();
      driver.findElement(By.id("ae-datastore-explorer-delete")).click();
      assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected items[\\s\\S]$"));
      driver.findElement(By.linkText("Sign out")).click();
      driver.close();
      /*
       * Delete the sheets in the spreadsheet by first adding an empty sheet
       * and then selecting and deleting each sheet sequentially beginning
       * with the first sheet but leaving the last empty sheet untouched.
       * Delete the sheets in the spreadsheet logging in to Google Docs as the
       * Administrator and accessing the spreadsheet. Click the QASE Automated
       * Test Scripts link and select SitesWrapper_GAS_1, which is a helper
       * script which deletes all the sheets in the spreadsheet leaving one
       * empty sheet, which is equivalent to the Apps Script Configuration
       * Clients newly installed but yet to be bound and configured state.
       */
      startDriver();
      /*
       * Login to Google Docs
       */
      //driver.get(baseUrl + "/ServiceLogin?continue=https%3A%2F%2Fdocs.google.com%2Fa%2Furlisit.com%2Fspreadsheets%2Fd%2F1HG6P-MswtJoXPSKhIhi0xl8gUGm5Whh-oivVNb9zd_s%2Fedit&ltmpl=sheets&service=wise&sacu=1&hd=urlisit.com");
      driver.get(baseUrl + SitesWrapper.APPS_SCRIPT_CONFIGURATION_CLIENT_LOGIN_URI);
      driver.findElement(By.id("Email")).clear();
      driver.findElement(By.id("Email")).sendKeys(SitesWrapper.ADMINISTRATOR_EMAIL);
      driver.findElement(By.id("Passwd")).clear();
      driver.findElement(By.id("Passwd")).sendKeys(SitesWrapper.getAdministratorPasswd());
      driver.findElement(By.id("signIn")).click();
      /*
       * Switch the selenium context to the sidebar menu
       */
      driver.switchTo().frame(driver.findElement(By.cssSelector("html body div.script-application-sidebar div.script-application-sidebar-content iframe")));
      driver.switchTo().frame(driver.findElement(By.id("script_frame")));
      /*
       *  Click Automated Test Utilities
       */
      driver.findElement(By.id("automatedTestScripts-caja-guest-0___")).click();
      /*
       *  Click SitesWrapper_GAS_1
       */
      driver.switchTo().defaultContent();
      driver.switchTo().frame(driver.findElement(By.cssSelector("html body div.script-application-sidebar div.script-application-sidebar-content iframe")));
      driver.switchTo().frame(driver.findElement(By.id("script_frame")));
      driver.findElement(By.id("SitesWrapper_GAS_1-caja-guest-0___")).click();
      /*
       *  Assert Test PASSED
       */
      driver.switchTo().defaultContent();
      driver.switchTo().frame(driver.findElement(By.cssSelector("html body div.script-application-sidebar div.script-application-sidebar-content iframe")));
      driver.switchTo().frame(driver.findElement(By.id("script_frame")));
      assertEquals("PASSED", driver.findElement(By.id("PASSED-caja-guest-0___")).getText());
      /*
       *  Click Return
       */
      //driver.switchTo().frame(driver.findElement(By.cssSelector("html body div.script-application-sidebar div.script-application-sidebar-content iframe")));
      //driver.switchTo().frame(driver.findElement(By.id("script_frame")));
      driver.findElement(By.id("return-caja-guest-0___")).click();
      /*
       * Logout of Google Docs
       */
      driver.close();
    }
    /*
     * SitesWrapper is ready to be initialized and the default configuration
     * updated in the datastore.
     */
  }

  /**
   * Scenario - Administrator installs, initializes and updates SitesWrapper-App-Script.
   * 
   * <ul>
   * <li>
   * Login to the Apps Script Configuration Client
   * </li>
   * <li>
   * Click Update Configuration in the sidebar menu
   * </li>
   * <li>
   * Wait for the Update Configuration Confirmation panel and verify the
   * text "Configuration Updated" appears
   * </li>
   * <li>
   *  Click Return
   * </li>
   * <li>
   * Verify the Apps Script Configuration Client has been bound to the Server
   * Service Wrapper and the configuration has been updated in the datastore
   * by going to the datastore viewer and verifying the ConfigurationId, Site
   * Style, Item, Landing and Page objects have been created.
   * </li>
   * <li>
   * Verify the Site object exists
   * </li>
   * <li>
   * Verify the collection of Style objects exist
   * </li>
   * <li>
   * Verify the collection of Landing objects exist
   * </li>
   * <li>
   * Verify the collection of Item objects exist
   * </li>
   * <li>
   * Verify the collection of Page objects exist
   * </li>
   * <li>
   * Verify the DocumentId object exists
   * </li>
   * <li>
   * Verify the new installation is available in the production environment
   * by a site loads and has a background image
   * </li>
   * </ul>
   * 
   * @throws Exception
   */
  @Test
  public void scenario() throws Exception {
    startDriver();
    String windowHandle = driver.getWindowHandle();
    /*
     * Login to the Apps Script Configuration Client
     */
    driver.get(baseUrl + SitesWrapper.APPS_SCRIPT_CONFIGURATION_CLIENT_LOGIN_URI);
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys(SitesWrapper.ADMINISTRATOR_EMAIL);
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys(SitesWrapper.getAdministratorPasswd());
    driver.findElement(By.id("PersistentCookie")).click();
    driver.findElement(By.id("signIn")).click();
    /*
     * Click Update Configuration in the sidebar menu
     */
    driver.switchTo().frame(driver.findElement(By.cssSelector("html body div.script-application-sidebar div.script-application-sidebar-content iframe")));
    driver.switchTo().frame(driver.findElement(By.id("script_frame")));
    driver.findElement(By.id("updateConfiguration-caja-guest-0___")).click();
    /*
     * Wait for the Update Configuration Confirmation panel and verify the
     * text "Configuration Updated" appears
     */
    driver.switchTo().defaultContent();
    driver.switchTo().frame(driver.findElement(By.cssSelector("html body div.script-application-sidebar div.script-application-sidebar-content iframe")));
    driver.switchTo().frame(driver.findElement(By.id("script_frame")));
    assertEquals("Configuration Updated", driver.findElement(By.id("ConfigurationUpdated-caja-guest-0___")).getText());
    /*
     *  Click Return
     */
    driver.findElement(By.id("return-caja-guest-0___")).click();
    /*
     * Verify the Apps Script Configuration Client has been bound to the Server
     * Service Wrapper and the configuration has been updated in the datastore
     * by going to the datastore viewer and verifying the ConfigurationId, Site
     * Style, Item, Landing and Page objects have been created.
     */
    driver.get(SitesWrapper.DATASTORE_VIEWER_URL);
    /*
     * Verify the Site object exists
     */
    new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Site");
    assertTrue(isElementPresent(By.cssSelector("#ae-datastore-explorer > h2")));
    /*
     * Verify the collection of Style objects exist
     */
    new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Style");
    assertTrue(isElementPresent(By.cssSelector("#ae-datastore-explorer > h2")));
    /*
     * Verify the collection of Landing objects exist
     */
    new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Landing");
    assertTrue(isElementPresent(By.cssSelector("#ae-datastore-explorer > h2")));
    /*
     * Verify the collection of Item objects exist
     */
    new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Item");
    assertTrue(isElementPresent(By.cssSelector("#ae-datastore-explorer > h2")));
    /*
     * Verify the collection of Page objects exist
     */
    new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Page");
    assertTrue(isElementPresent(By.cssSelector("#ae-datastore-explorer > h2")));
    /*
     * Verify the DocumentId object exists
     */
    new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("DocumentId");
    assertTrue(isElementPresent(By.cssSelector("#ae-datastore-explorer > h2")));
    /*
     * Verify the new installation is available in the production environment
     * by a site loads and has a background image
     */
    driver.get(SitesWrapper.SITESWRAPPER_PRODUCTION_URL);
    assertTrue(isElementPresent(By.cssSelector("img.gwt-Image")));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  @SuppressWarnings("unused")
  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
  
  private void startDriver() {
    driver = new FirefoxDriver();
    baseUrl = SitesWrapper.GOOGLE_ACCOUNTS_URL;
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
}