# Software Interfaces #

  1. **Server Service Wrapper** is deployed as an Google App Engine instance and makes use of the following software interfaces:
    * [Java Servlets API](http://java.sun.com/products/servlet/)
    * [Google App Engine Scalable Datastore](https://developers.google.com/appengine/docs/java/datastore/)
    * [Java OAuth API](https://developers.google.com/appengine/docs/java/oauth/)
    * [Java Persistence API](http://java.sun.com/developer/technicalArticles/J2EE/jpa/)
    * [Google Cloud Endpoints](https://developers.google.com/appengine/docs/java/endpoints/)
  1. **Client Wrapper**, a Google Web Toolkit module makes use of the following software interfaces:
    * [GWT RPC libraries](http://www.gwtproject.org/doc/latest/DevGuideServerCommunication.html#DevGuideRemoteProcedureCalls)
    * [GWT Dictionary](http://www.gwtproject.org/javadoc/latest/com/google/gwt/i18n/client/Dictionary.html)
  1. **Android Configuration Client** The Android Configuration Client makes use of the following software interfaces:
    * [Google Cloud Endpoint Client Libraries](http://android-developers.blogspot.com/2013/06/adding-backend-to-your-app-in-android.html)
    * [Google Cloud Messaging](http://developer.android.com/google/gcm/index.html)
    * [Android Wear API](http://developer.android.com/wear/index.html?utm_source=ausdroid.net)
  1. **iOS Configuration Client** The iOS Configuration Client makes use of the following software interfaces:
    * [Google Cloud Endpoint Client Libraries for iOS](https://developers.google.com/appengine/docs/java/endpoints/consume_ios)
  1. **Apps Script Configuration Client** The AppScript Configuration Client makes use of the following software interfaces:
    * The Apps Script [UrlFetchApp](https://developers.google.com/apps-script/reference/url-fetch/url-fetch-app?csw=1) class