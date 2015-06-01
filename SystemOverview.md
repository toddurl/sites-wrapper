# System Overview #

Creative content is generated using Google Apps and hosted as URL referenceable information items in Google Drive, Google+, Picasa Web and virtually any other web service including Google Sites.

URL references and other attributes of the creative content are entered into various “forms” accessible via one or more configuration clients.

The configuration clients Create, Read, Update and Delete entity objects in the Google Datastore via Google Cloud Endpoints.

The entity objects are used by the Server and Client Wrappers to render HTML and JavaScript frontends respectively, each of which presents essentially the same webapp in terms of functionality via different paradigms depending on whether the site is accessed by a JavaScript enabled browser.

That is, if the App Engine based website is accessed via a JavaScript enabled browser, a browser independent GWT webapp is rendered following the MVP design pattern.

If the App Engine based website is accessed by an HTML only browser, the Googlebot for example, an HTML only webapp is rendered following the MVC design pattern.

Authentication and authorization is handled exclusively via Google Accounts using OAuth 2.0 and the Google+ signin button.