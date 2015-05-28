CookieCRUD is a central session server for holding user/session information.

==========
This provides functionality for 
1. multiple values in one cookie
2. encryption of those values using a symetric key
3. decryption of those values using a symetric key
4. storing cookie values and later retrieving them
5. sharing user information across different domains
==========

Use Case 1: store info on CookieCRUD and then later retrieve it.
website A can redirect a user to CookieCRUD with a create cookie command and a redirectTo http://back.to.me
CookieCRUD will create a cookie with the name value as seen above and then redirect the user back to http://back.to.me.
website A can later request this value from CookieCrud by again redirecting the user with a viewCookie and a redirectTo http://back.to.me
CookieCRUD will append the list of name/value pairs as HTTP parameters
   
Use Case 2: share user information across domains
website A can redirect a user to CookieCRUD with a create cookie command and a redirectTo http://back.to.me. The cookieName could be "CHOCCY".
website B who is aware that website A used the name "CHOCCY" can then redirect the user to CookieCRUD with a viewCookie for "CHOCCY" and a redirectTo http://back.to.me
CookieCRUD reads the value(s) of the cookie called "CHOCCY" and redirects back to website B, CookieCRUD will append the list of name/value pairs as HTTP parameters

Use Case 3: No More Cookies :-) server side lookup of user information - no cookie solution
website A can redirect a user to CookieCRUD with a create cookie command and a redirectTo http://back.to.me
CookieCRUD will create the appropriate cookie AND also store this in the session of that user on the CookieCRUD server. The redirect will include the session id.
website A can call cookieCRUD directly with the session id and the cookieName to find out all the values that are in the cookie (assuming the user has not manually changed them).
It can also perform all of this without sending any cookie information to the user.

==========

Security:
CookieCRUD could support groups of websites that are all allowed to share user information. It would add a symetric key to the request that is only known to that group of websites.
Could use HTTPS for the redirects, but of course the participant website would need a secure endpoint for CookieCRUD to redirect the user to (with the sensitive information).
CookieCRUD can encrypt the content of the cookie so that it is not visible to the user.
CookieCRUD can create a cookie with a strongly encrypted name, and include an expiry time for that cookie regardless of the actual settings in the cookie itself.
   
==========
   
Future: for cookieCRUD to maintain user information without using cookies (or at least one central cookie)
cookieCRUD can be downloaded and installed on your own server
cookieCRUD could output some advertising embedded in the return to page, and for that advertising to subsequently disappear on the next user click
Admin areas for websites
Admin areas for users who want to exclude themselves from cookieCRUD or see what information is being stored about them
Central login on CookieCRUD 
Document database to store centralised session information
Illustrated testing
One pixel controller for setting values on the cookiecrudserver
==========

Concerns:
too many values being stored what are the breaking points?
performance of writing and sharing all this information across sites?
what happens when you add something that makes the cookie too big? 4096 bytes is the maximum size.
==========

Notes:
Cipher is not thread safe; but the CookieBakery is threadsafe.
Performance test has not been conducted.
Problems may exist when the delimeters of the string are characters found within the key value pairs themselves.

==========

Supported Browsers:

==========

Usage:

Start the jetty servlet container on port 9090 and you can access this by http://localhost:9090/showcookies
This will be the cookie crud server.

mvn -Djetty.port=9090 jetty:run -Dserver=http://someserver.com -Dme=http://localhost:9090
Add these parameters to MAVEN_OPTS to debug
-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=y 

http://localhost:9090/addcookie/chocco/choc/yummy
http://localhost:9090/getvaluefromcookie/chocco/choc

The strength here is that value can be accessed from a different website.
Probably best to use a different browser to test (some browsers don't differentiate between ports for cookies)
Start a new instances of jetty on a different port e.g. 9091/9092. These instances need to be aware of the cookie crud server.

Share info between websites:

==========
