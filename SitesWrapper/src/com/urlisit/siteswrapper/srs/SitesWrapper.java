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
package com.urlisit.siteswrapper.srs;

/**
 * A noninstantiable utility class for providing Constants to the SitesWrapper-
 * SRS Test Suite. (Effective Java Item 4)
 * 
 * IMPORTANT - Remember to replace dummy data in PASSWD with actual data before
 * running the SitesWrapper-SRS TestSuite.
 *
 * @author Todd Url
 */
public class SitesWrapper {
  private SitesWrapper() { } // Prevents instantiation
  public static final String ADMINISTRATOR_EMAIL = "toddurl@urlisit.com";
  public static final String ADMINISTRATOR_PASSWD = "w4rH4mm3r";
  public static final String GOOGLE_ACCOUNTS_URL = "https://accounts.google.com/";
  public static final String APPS_SCRIPT_CONFIGURATION_CLIENT_LOGIN_URI = "/ServiceLogin?continue=https%3A%2F%2Fdocs.google.com%2Fa%2Furlisit.com%2Fspreadsheets%2Fd%2F1HG6P-MswtJoXPSKhIhi0xl8gUGm5Whh-oivVNb9zd_s%2Fedit&ltmpl=sheets&service=wise&sacu=1&hd=urlisit.com";
  public static final String SITESWRAPPER_PRODUCTION_URL = "http://siteswrapper.appspot.com/";
  public static final String DATASTORE_VIEWER_LOGIN_URI = "/ServiceLogin?service=ah&passive=true&continue=https%3A%2F%2Fappengine.google.com%2F_ah%2Fconflogin%3Fcontinue%3Dhttps%3A%2F%2Fappengine.google.com%2Fdatastore%2Fexplorer%253F%2526app_id%253Ds%7Esiteswrapper&ltmpl=ae";
  public static final String DATASTORE_VIEWER_URL = "https://appengine.google.com/datastore/explorer?&app_id=s~siteswrapper";
}
