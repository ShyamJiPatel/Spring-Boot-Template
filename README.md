# JWT Spring Security Demo

## About
This is just a simple demo for using **JSON Web Token (JWT)** with **Spring Security** and
**Spring Boot 2**. [REST Security with JWT using Java and Spring Security]

## Requirements
This demo is build with with Maven 3 and Java 1.8.

## Usage
Just start the application with the Spring Boot maven plugin (`mvn spring-boot:run`). The application is
running at [http://localhost:8080](http://localhost:8080).

There are three user accounts present to demonstrate the different levels of access to the endpoints in
the API and the different authorization exceptions:
```
Admin - admin:admin
User - user:password
Disabled - disabled:password (this user is disabled)
```

There are three endpoints that are reasonable for the demo:
```
/auth - authentication endpoint with unrestricted access
/employee - an example endpoint that is restricted to authorized users (a valid JWT token must be present in the request header)
/project - an example endpoint that is restricted to authorized users (a valid JWT token must be present in the request header)
/protected - an example endpoint that is restricted to authorized users with the role 'ROLE_ADMIN' (a valid JWT token must be present in the request header)
```

I've written a small Javascript client and put some comments in the code that hopefully makes this demo
understandable.

### Generating password hash for new users

I'm using [bcrypt](https://en.wikipedia.org/wiki/Bcrypt) to encode passwords. Your can generate your hashes with this simple tool: [Bcrypt Generator](https://www.bcrypt-generator.com)

### Using another database

Actually this demo is using a MySQL DB

## Creator

**Shyam Ji**

