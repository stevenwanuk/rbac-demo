# Referrer-based access control demo

## issue
Referer header is not going to send if redirect from https to http without tag <meta name="referrer">

## setup 
add following line host file

127.0.0.1 local.rbac-demo.com

 
## demo
browser to : https://local.rbac-demo.com:8009/, click "test"

redirects to:   https://local.rbac-demo.com:8009/demo with Referer
redirects to:   https://localhost:8009/demo2  with Referer
redirects to:   http://localhost:8080/landing   without Referer (if NOT setup <meta name="referrer">)







