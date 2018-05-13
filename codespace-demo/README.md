# codesense e-wallet demo

to compile
> mvn clean install

this will build application and run tests.

after that in a command line

> java -jar {application-jar}

this will run application at http://localhost:8080

application run with form login authentication, therefore, in order to be able call requests, firstly use register and login links, or use pre-created users written in DataInitialize.class

in order to login

> http://localhost:8080/login


to register

> http://localhost:8080/registration.html


i have used hsqldb, so you dont need to set database configuration

## Postman collection api link

https://documenter.getpostman.com/view/56655/codespace/RW81vYqD


## Requirements
- java 1.8
- maven or junit to run tests
