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
package com.urlisit.siteswrapper.srs.gae;

import static org.junit.Assert.*;

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

import static com.urlisit.siteswrapper.srs.SitesWrapper.*;

/**
 * SitesWrapper-GAE-1 - Drop the Server Service Wrapper configuration in the
 * datastore
 * 
 * On occasion there arises the need to delete the contents of the datastore
 * associated with an instance of the SitesWrapper-GAE-GWT Server Service
 * Wrapper. Such a condition arises when the GAE application instance is being
 * re-purposed, or as part of a _setup_ routine which needs to be executed in
 * order to test the features of another application component, the Apps Script
 * Configuration Client for example.
 *
 * The following script will result in the data associated with a
 * SitesWrapper-GAE-GWT application instance in Google App Engine being
 * completely deleted.
 * 
 * @author Todd Url
 */
public class SitesWrapper_GAE_1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://accounts.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    String adminPasswd = SitesWrapper.getAdministratorPasswd();
  }

  @Test
  public void testSitesWrapper_GAE_1() throws Exception {
    driver.get(baseUrl + "/ServiceLogin?service=ah&passive=true&continue=https%3A%2F%2Fappengine.google.com%2F_ah%2Fconflogin%3Fcontinue%3Dhttps%3A%2F%2Fappengine.google.com%2Fdatastore%2Fexplorer%253F%2526app_id%253Ds%7Esiteswrapper&ltmpl=ae");
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys(SitesWrapper.ADMINISTRATOR_EMAIL);
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys(SitesWrapper.getAdministratorPasswd());
    driver.findElement(By.id("signIn")).click();
    new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Site");
    driver.findElement(By.cssSelector("th.cbc.tct-selectall > input[type=\"checkbox\"]")).click();
    driver.findElement(By.id("ae-datastore-explorer-delete")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected items[\\s\\S]$"));
    new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Style");
    driver.findElement(By.cssSelector("th.cbc.tct-selectall > input[type=\"checkbox\"]")).click();
    driver.findElement(By.id("ae-datastore-explorer-delete")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected items[\\s\\S]$"));
    new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Landing");
    driver.findElement(By.cssSelector("th.cbc.tct-selectall > input[type=\"checkbox\"]")).click();
    driver.findElement(By.id("ae-datastore-explorer-delete")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected items[\\s\\S]$"));
    new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Item");
    driver.findElement(By.cssSelector("th.cbc.tct-selectall > input[type=\"checkbox\"]")).click();
    driver.findElement(By.id("ae-datastore-explorer-delete")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected items[\\s\\S]$"));
    new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("Page");
    driver.findElement(By.cssSelector("th.cbc.tct-selectall > input[type=\"checkbox\"]")).click();
    driver.findElement(By.id("ae-datastore-explorer-delete")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected items[\\s\\S]$"));
    new Select(driver.findElement(By.id("ae-datastore-explorer-kind"))).selectByVisibleText("DocumentId");
    driver.findElement(By.cssSelector("th.cbc.tct-selectall > input[type=\"checkbox\"]")).click();
    driver.findElement(By.id("ae-datastore-explorer-delete")).click();
    assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to delete the selected items[\\s\\S]$"));
    driver.findElement(By.linkText("Sign out")).click();
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
}

