# Referrer-based access control demo

## issue
Referer header is not going to send if redirect from https to http without tag <meta name="referrer">

## add following line host file
127.0.0.1 local.rbac-demo.com

 
## test
browser: https://local.rbac-demo.com:8009/

will see "referer: https://local.rbac-demo.com:8009/" at "http://localhost:8080/landing" page.







