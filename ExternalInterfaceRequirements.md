# External Interface Requirements #

Requirements placed on the external interfaces of the system are consistent with the character of the respective component.

  1. **Android Configuration Client** - Android provides a framework for creating user interfaces which is documented in the [Best Practices for User Interfaces](http://developer.android.com/training/best-ui.html) section of the Android developers website. This section, as well as all other “best practices” identified for Android programs will be adhered to.
  1. **iOS Configuration Client** - Likewise, all [Best Practices for iOS user experiences](https://developer.apple.com/videos/wwdc/2013/?id=225) will be adhered to.
  1. **Apps Script Configuration Client** - In the case of the AppScript Configuration client, [best practices](https://developers.google.com/apps-script/guides/html/best-practices) for an HTML interface are identified in the [HTML Services](https://developers.google.com/apps-script/guides/html/best-practices) section of the Apps Script developers site.
  1. **Server Service Wrapper** - The Server Service Wrapper presents three external interfaces; the GWT RPC which supports Client Wrapper API based communication with the Server Service Wrapper, Google Cloud Endpoints which provides a RESTful JSON based API to the configuration clients, and the HTML based user interface must adhere to the following [non functional](http://www.google.com/url?q=http%3A%2F%2Fen.wikipedia.org%2Fwiki%2FNon-functional_requirements&sa=D&sntz=1&usg=AFQjCNEfqIIqXPh9ybJpirov_fASn23HgA) requirements.
    1. **Respect information items, landing pages, pages and sites configuration attributes**
    1. **Generate accurate and relevant sitemap.xml data on demand**
    1. **Generate GWT Host Page for all configured pages and landing pages**
    1. **The Site must deploy to the Google App Engine application id specified for the attribute “App Engine Application ID” for the currently authenticated Google ID**
    1. **The Site must deploy to the Google App Engine application id version for the attribute “App Engine Application Version” for the currently authenticated Google ID**
  1. **Client Wrapper** - The external interface of the Client Wrapper represents the [web application](http://en.wikipedia.org/wiki/Web_application) feature functionality of SitesWrapper and must adhere to the following [non-functional](http://en.wikipedia.org/wiki/Non-functional_requirements) requirements.
    1. **Must render a completely functional site based on the contents of the configuration datastore regardless of the configured look and feel**
    1. **The Site must adhere to the value specified for the attribute “Site Name”**
    1. **The Site must deploy to the Google App Engine**