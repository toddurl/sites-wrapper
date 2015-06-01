# Functional Requirements for SitesWrapper-Apps-Script #

## Title: _Bind and initialize the spreadsheet and update the initial configuration in the datastore_ ##
### Identifier: _SitesWrapper-GAS-1_ ###
### Goal: ###
Initially neither the spreadsheet nor the datastore contain information, and there ins't an established relationship of trust between the two. In order to overcome this situation, the user of the configuration client binds the configuration document (_i.e._; the newly created spreadsheet) to the newly created Server Service Wrapper, which at this point will have an empty datastore, and creates a new, default configuration which matches between the two (_i.e._; the spreadsheet and the datastore).

This goal is accomplished by the Configuration Client composing and sending a DKIM (DomainKeys Identified Mail) verified email to the Service Server Wrapper having the application id in the subject line and the Google Apps document id of the Configuration Client in the id field of the mail header. Since the datastore is empty, and DKIM verified domain of the sender matches the domain of the receiver, the Server Service Wrapper can be certain the document id contained in the header is in fact authorized by the application owner to initialize and subsequently update the configuration in the datastore.
### Primary Actor: _Application Administrator_ ###
### Precondition: ###
This Use Case requires that:
  1. SitesWrapper-GAE-GWT is installed in (_i.e._: deployed to) Google App Engine as an application administered by the same Google account as the primary actor, but not yet configured in either the Configuration Client or the datastore.
  1. The primary actor knows the **application id** of the application in Google App Engine.
  1. The primary actor is currently logged in (authenticated) to their Google Account.
  1. **Note** - In the case that SitesWrapper-Apps-Script has already been installed and this Use Case needs to be performed (_i.e._; the Test Case needs to be re-executed), Use Case [SitesWrapper-GAE-1](https://code.google.com/p/sites-wrapper/wiki/ServerServiceWrapper) (Drop the Server Service Wrapper configuration in the datastore) can be executed as a setup routine.
The Test Case and Test Script associated with this Use Case should use the following data:
  * Google account **toddurl@urlisit.com**
  * Password **N/A**
  * GAE application id **siteswrapper**
### Scenario: ###
  1. _SitesWrapper-Apps-Ascript-1_ Application Owner installs SitesWrapper-App-Script.
    1. Open https://code.google.com/p/siteswrapper-apps-script/)
    1. Click Login
    1. Type toddurl@urlisit.com(tab)password
    1. Click Login
    1. Click Source
    1. Click Browse
    1. Click SitesWrapper-Apps-Script
    1. Click SitesWrapper.gs
    1. Click Edit file
    1. Click anywhere in the file
    1. Select all (control a)
    1. Copy (control c)
    1. Browse to Google Drive (http://drive.google.com)
    1. Click Create
    1. Click Spreadsheet
    1. Click Untitled Spreadsheet
    1. Type siteswrapper
    1. Click OK
    1. Click Tools
    1. Click Script editor
    1. Click Blank Project
    1. Select all (control a)
    1. Paste (control v)
    1. Click Untitled Project
    1. Type siteswrapper
    1. Click OK
    1. Click close tab
    1. Click siteswrapper tab
    1. Click refresh
    1. Click SitesWrapper
    1. Click Initialize
### Oracle: ###
The following criteria are used for determining whether this scenario has passed or failed.
  1. Five new sheets are created as part of the spreadsheet, and appear at the bottom of the page labeled **Site**, **Style**, **Landing**, **Item** and **Page**.
  1. One new objects appears in the Datastore Viewer of the App Engine dashboard for the siteswrapper application instance (_i.e._: https://appengine.google.com/datastore/explorer?&app_id=s~siteswrapper). The name of the object is **ConfigurationId**.

## Title: _Update SitesWrapper-GAE-GWT datastore with the default configuration_ ##
### Identifier: _SitesWrapper-GAS-2_ ###
### Goal: ###
SitesWrapper initialization results in a ConfigurationId entity with a valid Id being created in the datastore, as well as a valid default configuration being created in the spreadsheet managed by the Apps Script Configuration Client, but the application data model still needs to be created in the datastore and populated with the data in the spreadsheet.

This goal is accomplished by running the updateConfiguration routine of the Apps Script Configuration Client from the SitesWrapper menu. The updateConfiguration routine packages up the data defined in the  cells of each sheet and submits it to the RESTful servlet of the Server Service Wrapper via the Apps Script UrlFetchApp service over HTTPS. Each valid POST results in a corresponding object being created and populated in the datastore. The Site sheet becomes the Site object, the Style sheet a collection of Style objects, Landing the Landing objects, Item the Item objects and each Page sheet becomes the corresponding populated Page object in the datastore.
### Primary Actor: _Application Administrator_ ###
### Precondition: ###
This use case requires that:
  1. The SitesWrapper-Apps-Script (Apps Script Configuration Client) spreadsheet must have been successfully initialized as described in SitesWrapper-GAS-1, but not yet propagated to the GAE datastore.
  1. The primary actor is currently logged in (authenticated) to their Google Account.
  1. The primary actor has the SitesWrapper-Apps Script spreadsheet open and populated with the default configuration.
  1. use the application id **siteswrapper** for the test case associated with this use case.
### Scenario: ###
  1. Application Owner runs the Update siteswrapper configuration option of the Apps Script Configuration Client.
    1. Click SitesWrapper
    1. Click Update siteswrapper configuration
### Oracle: ###
The following criteria are used for determining whether this scenario has passed or failed.
  1. Five new objects appear in the Datastore Viewer of the App Engine dashboard for the towingenterpriseexecutive application instance (_i.e._: https://appengine.google.com/datastore/explorer?&app_id=s~towingenterpriseexecutive). The names of these objects match the names of the new sheets in the spreadsheet, **Site**, **Style**, **Landing**, **Item** and **Page**.
  1. Opening a browser **with** javascript enabled and navigating to http://siteswrapper.appspot.com results in the Client Wrapper displaying an AJAX web application matching the default configuration.
  1. Opening a browser **without** javascript enabled and navigating to http://siteswrapper.appspot.com results in the Client Wrapper displaying an HTML only web application matching the default configuration.