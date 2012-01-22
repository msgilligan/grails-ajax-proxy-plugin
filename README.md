Grails Ajax Proxy Plugin
========================

Want to use some Ajax in your Grails application?  Same Origin Policy getting in your way?  Looking for a quick and easy solution?

For example, say you want to use the Google Translate API but either don't want to use JSONP or can't because you need to translate strings that are too long to include in the URL as query parameters.

Run

    grails install-plugin ajax-proxy
    
Add the folloing to grails-app/conf/Config.groovy

    plugins {
      proxy {
        proxyScheme = 'https://'
        proxyHost = 'www.googleapis.com'
        proxyPort = '443'
        proxyPath = ''
      }
    }
    
Write your JavaScript to access Google Translate at the following URL on your server

    /proxy/language/translate/v2
    
(That's /proxy + the path on the destination server)

You're done.

Notes
-----
Grails will process (e.g. turn a POST into a GET) and swallow the X-HTTP-Method-Override header.  (It appears this is done by the HiddenHttpMethodFilter.)

To pass your forwarded requests through unchanged use X-Forward-HTTP-Method-Override, which will be forwarded as X-HTTP-Method-Override.  (New in Version 0.1.2)

Release on Grails.org
---------------------
This plugin should be available on the Grails Plugin Portal
http://grails.org/plugin/ajax-proxy

(The version on the Portal may not be as recent as the source code posted here, if it isn't, you'll need to build and install from source.)

License
-------
Licensed under the terms of the Apache Software License 2.0

Proxy Servlet
-------------
The included ProxyServlet.Java file is from:
http://edwardstx.net/2010/06/http-proxy-servlet/
(Also Apache 2.0 Licensed)

Since that Servlet is not hosted under source control, we've included the .java file in src/java/net/edwardstx/ProxyServlet.java and have made some minor modification to it.

