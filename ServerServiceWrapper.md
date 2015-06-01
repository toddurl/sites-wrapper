# Functional Requirements - _Server Service Wrapper_ #

## Title: _Drop the Server Service Wrapper configuration in the datastore_ ##
### Identifier: _SitesWrapper-GAE-1_ ###
### Goal: ###
On occasion there arises the need to delete the contents of the datastore associated with an instance of the SitesWrapper-GAE-GWT Server Service Wrapper. Such a condition arises when the GAE application instance is being repurposed, or as part of a _setup_ routine which needs to be executed in order to test the features of another application component, the Apps Script Configuration Client for example.

The following steps will result in the data associated with a SitesWrapper-GAE-GWT application instance in Google App Engine being completely deleted.
### Primary Actor: _Application Administrator_ ###
### Precondition: ###
This Use Case requires that:
  1. The SitesWrapper-GAE-GWT software has been previously installed, initialized and updated at least once in Google App Engine as an application administered by the same Google account as the primary actor.
  1. The primary actor knows the **application id** of the application in Google App Engine.
  1. The primary actor is currently logged in (authenticated) to their Google Account.
The Test Case or Test Script associated with this Use Case should use the application id **siteswrapper**.
### Scenario: ###
  1. _SitesWrapper-GAE-GWT-1_ Application Owner drops the datastore Entities associated with a SitesWrapper-GAE-GWT install.
    1. Open https://appengine.google.com/dashboard?app_id=s~siteswrapper
    1. Click Login
    1. Type toddurl@urlisit.com(tab)password
    1. Click Login
    1. Click Source
    1. Click Browse
    1. Click SitesWrapper-Apps-Script
    1. Click Code.gs
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

<table cellpadding='1' cellspacing='1' border='1'>
<thead>
<tr><td>New Test</td></tr>
</thead><tbody>
<tr>
<blockquote><td>open</td>
<td>/p/siteswrapper-apps-script/</td>
<td></td>
</tr>
<tr>
<td>clickAndWait</td>
<td>link=Source</td>
<td></td>
</tr>
<tr>
<td>clickAndWait</td>
<td>link=Browse</td>
<td></td>
</tr>
<tr>
<td>click</td>
<td>link=SitesWrapper-Apps-Script</td>
<td></td>
</tr>
<tr>
<td>clickAndWait</td>
<td>link=Code.gs</td>
<td></td>
</tr>
<tr>
<td>clickAndWait</td>
<td>link=Edit file</td>
<td></td>
</tr>
<tr>
<td>waitForPopUp</td>
<td><i>blank</td></i><td>30000</td>
</tr>
<tr>
<td>click</td>
<td>//div[@id=':22.cmi']/div</td>
<td></td>
</tr>
<tr>
<td>click</td>
<td>id=docs-title-inner</td>
<td></td>
</tr>
<tr>
<td>type</td>
<td>id=:bo.ie</td>
<td>siteswrapper</td>
</tr>
<tr>
<td>click</td>
<td>name=ok</td>
<td></td>
</tr>
<tr>
<td>waitForPopUp</td>
<td>MacroService355329354</td>
<td>30000</td>
</tr>
<tr>
<td>selectWindow</td>
<td>name=MacroService355329354</td>
<td></td>
</tr>
<tr>
<td>type</td>
<td>css=textarea</td>
<td></td>
</tr>
<tr>
<td>click</td>
<td>id=docs-title-inner</td>
<td></td>
</tr>
<tr>
<td>type</td>
<td>css=input.gwt-TextBox.rename-input</td>
<td>siteswrapper</td>
</tr>
<tr>
<td>click</td>
<td>css=button.gwt-Button</td>
<td></td>
</tr>
<tr>
<td>click</td>
<td>//div[@id='saveButton']/div/div/div/div</td>
<td></td>
</tr>
<tr>
<td>selectWindow</td>
<td>null</td>
<td></td>
</tr>
<tr>
<td>click</td>
<td>name=continue</td>
<td></td>
</tr>
<tr>
<td>waitForPopUp</td>
<td><i>blank</td></i><td>30000</td>
</tr>
<tr>
<td>clickAndWait</td>
<td>id=submit_approve_access</td>
<td></td>
</tr>
<tr>
<td>click</td>
<td>name=1</td>
<td></td>
</tr>
<tr>
<td>selectAndWait</td>
<td>id=ae-datastore-explorer-kind</td>
<td>label=Site</td>
</tr>
<tr>
<td>selectAndWait</td>
<td>id=ae-datastore-explorer-kind</td>
<td>label=Style</td>
</tr>
<tr>
<td>selectAndWait</td>
<td>id=ae-datastore-explorer-kind</td>
<td>label=Landing</td>
</tr>
<tr>
<td>selectAndWait</td>
<td>id=ae-datastore-explorer-kind</td>
<td>label=Item</td>
</tr>
<tr>
<td>assertAlert</td>
<td>An error occured while loading image undefined</td>
<td></td>
</tr></blockquote>

</tbody></table>