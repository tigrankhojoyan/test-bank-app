# XML rest API

### Description
Application intended to register user and provide account to store user balance. 
User data/account is stored in MYSQL db

### Technologies

The following technologies are used:

Spring Boot, Spring JDBC, Spring Validator, Lombok, Jackson xml

### Installation
You should have Java 11, mysql and maven installed on your machine to run this app.

Steps:

Install:

`mvn clean install`

Run app:

`mvn spring-boot:run`

Go to app page:

`http://localhost:8080/`

### Register user:
Post the following request with 'Content-Type' 'application/xml' headers to `localhost:8080/api/user/register` uri address:

`<?xml version="1.0" encoding="utf-8"?>
 <request>
 <request-type>CREATE-AGT</request-type>
 <extra name="login">123456er</extra>
 <extra name="password">pwd</extra>
 </request>`
 
The following responses are possible:

1: User registered successfully: 

`HTTP 201 CREATED, ` 
`<response>
 <result-code>0</result-code>
 </response>`

2: User with same username already exists: 

`HTTP 409 CONFLICT, ` 
`<response>
 <result-code>1</result-code>
 </response>`

3: Invalid by syntax data(e.g <request-type>CREATE-AGT1</request-type>) 

 `HTTP 400 BAD REQUEST, ` 
 `<response>
  <result-code>2</result-code>
  </response>`

4: Technical error 

`HTTP 500 INTERNAL SERVER ERROR BAD REQUEST, ` 
`<response>
 <result-code>2</result-code>
 </response>`
 

 
###Get user balance:

Post the following request with 'Content-Type' 'application/xml' headers to `localhost:8080/api/user/getbalance` uri address:

The following responses are possible:

1: Entered user data is correct: 

`HTTP 200 OK, ` 
`<response>
 <result-code>0</result-code
 <extra name="balance">0</extra>
 </response>`

2: User with given username does not exist: 

`HTTP 404 NOT FOUND, ` 
`<response>
 <result-code>3</result-code>
 </response>`

3: Password is incorrect(username is correct)  

 `HTTP 401 UNAUTRHORIZED, ` 
 `<response>
  <result-code>4</result-code>
  </response>`

4: Invalid by syntax data(e.g <request-type>CREATE-AGT1</request-type>) 

 `HTTP 400 BAD REQUEST, ` 
 `<response>
  <result-code>2</result-code>
  </response>`

5: Technical error 

`HTTP 500 INTERNAL SERVER ERROR BAD REQUEST, ` 
`<response>
 <result-code>2</result-code>
 </response>`
