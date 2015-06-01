# Functional Requirements #

This section of the wiki identifies the [functional requirements](http://en.wikipedia.org/wiki/Functional_requirement) of SitesWrapper. For the most part, the functional requirements of SitesWrapper are comprised of the [use cases](http://en.wikipedia.org/wiki/Use_case) associated with each of the various components of SitesWrapper; the **Android Configuration Client**, the **iOS Configuration Client**, the **Apps Script Configuration Client**, the **Server Service Wrapper** and the **Client Wrapper**.

The Use Cases, grouped together in subsections of this section of the wiki, shall have a one to one relationship with a corresponding functional [Test Case](http://en.wikipedia.org/wiki/Test_Case), each of which shall be [scripted](http://en.wikipedia.org/wiki/Test_script) and grouped together to form a [Test Suite](http://en.wikipedia.org/wiki/Test_suite) covering the functionality of the associated component of SitesWrapper.

For example, the Use Cases identified in the Android Configuration Client subsection of the Functional Requirements section of this wiki shall be translated directly into functional Test Cases covering the functionality of the Android Configuration Client, scripted using UiAutomator and grouped into a Test Suite in order to facilitate automated test execution and test reporting.

## Language ##

The language used in each Use Case of each functional requirement for each subsection of this section shall be definitive, and dictate the language used for each deliverable associated with the [Software Development Lifecycle](http://en.wikipedia.org/wiki/Software_development_process) of SitesWrapper including, but not limited to, test cases, test scripts, test suites and test summary reports.

For example, the title of the Use Case shall be used as the title of the corresponding Test Case as well as the title of the corresponding Test Script, and shall be used to identify the Test Case within the corresponding Test Suite as well as the Test Summary Report.

In the case that new language is required or created as part of any other deliverable, that language shall be back ported and incorporated into the corresponding Use Case so that the usage of the language is once again consistent across the deliverables associated with the SDLC.

For example, if a unique identifier is created as part of writing a Test Case, that unique identifier (_i.e._; test case id) shall be incorporated into the language of the corresponding Use Case and used were and if required in the corresponding Test Script, Test Suite and Test Summary Report.

## Suites ##

<table>
<blockquote><tr><td><b>Suite Of Tests</b></td></tr>
<tr><td><a href='./Login.html'>Login</a></td></tr>
<tr><td><a href='./SearchValues.html'>Test Searching for Values</a></td></tr>
<tr><td><a href='./SaveValues.html'>Test Save</a></td></tr>
</table>