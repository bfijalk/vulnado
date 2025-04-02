# Documentation: `LoginController.java`

## Overview
The `LoginController` class is part of a Spring Boot application and provides functionality for user authentication. It exposes an endpoint for login requests, validates user credentials, and returns a token upon successful authentication. The controller leverages dependency injection, custom exception handling, and serialization for request and response objects.

---

## File Metadata
- **File Name**: `LoginController.java`

---

## Components

### 1. **LoginController**
The main controller class responsible for handling login requests.

#### Key Features:
- **Endpoint**: `/login`
- **HTTP Method**: POST
- **Consumes**: JSON
- **Produces**: JSON
- **Cross-Origin Support**: Enabled for all origins (`@CrossOrigin(origins = "*")`).

#### Fields:
| Field Name | Type   | Description                          |
|------------|--------|--------------------------------------|
| `secret`   | String | Application secret injected via `@Value("${app.secret}")`. Used for token generation. |

#### Method:
| Method Name | Parameters                     | Return Type     | Description                                                                 |
|-------------|--------------------------------|-----------------|-----------------------------------------------------------------------------|
| `login`     | `@RequestBody LoginRequest`   | `LoginResponse` | Authenticates the user and returns a token if credentials are valid. Throws `Unauthorized` exception otherwise. |

#### Logic:
1. Fetches the user details using `User.fetch(input.username)`.
2. Compares the hashed password (`Postgres.md5(input.password)`) with the stored hashed password (`user.hashedPassword`).
3. If valid, generates a token using `user.token(secret)` and returns it in a `LoginResponse`.
4. If invalid, throws an `Unauthorized` exception with the message "Access Denied".

---

### 2. **LoginRequest**
A data structure representing the login request payload.

#### Fields:
| Field Name | Type   | Description                     |
|------------|--------|---------------------------------|
| `username` | String | The username provided by the user. |
| `password` | String | The password provided by the user. |

#### Characteristics:
- Implements `Serializable` for object serialization.

---

### 3. **LoginResponse**
A data structure representing the login response payload.

#### Fields:
| Field Name | Type   | Description                     |
|------------|--------|---------------------------------|
| `token`    | String | The authentication token generated upon successful login. |

#### Constructor:
| Constructor Name | Parameters | Description                          |
|------------------|------------|--------------------------------------|
| `LoginResponse`  | `String msg` | Initializes the `token` field with the provided message. |

#### Characteristics:
- Implements `Serializable` for object serialization.

---

### 4. **Unauthorized**
A custom exception class used to handle unauthorized access.

#### Features:
- Annotated with `@ResponseStatus(HttpStatus.UNAUTHORIZED)` to return a 401 HTTP status code when thrown.
- Extends `RuntimeException`.

#### Constructor:
| Constructor Name | Parameters       | Description                          |
|------------------|------------------|--------------------------------------|
| `Unauthorized`   | `String exception` | Initializes the exception with a custom message. |

---

## Insights

### Security Considerations:
1. **Hardcoded Secret**: The `secret` field is injected from application properties, which is a good practice. Ensure the secret is stored securely and not exposed in version control.
2. **Password Hashing**: The password is hashed using `Postgres.md5`. Verify that the hashing algorithm is strong enough for production use.
3. **Cross-Origin Requests**: The controller allows requests from all origins (`@CrossOrigin(origins = "*")`). This may pose a security risk if not properly restricted.

### Error Handling:
- The `Unauthorized` exception provides a clear mechanism for handling authentication failures, returning a 401 status code.

### Serialization:
- Both `LoginRequest` and `LoginResponse` implement `Serializable`, ensuring compatibility with Java serialization mechanisms.

### Dependency Injection:
- The `@Value("${app.secret}")` annotation demonstrates the use of Spring's dependency injection for configuration management.

### Scalability:
- The current implementation assumes synchronous processing. For high traffic scenarios, consider optimizing database calls and token generation.

---

## Dependencies
| Dependency                  | Purpose                                                                 |
|-----------------------------|-------------------------------------------------------------------------|
| `org.springframework.boot` | Provides Spring Boot functionality for application configuration.       |
| `org.springframework.web.bind.annotation` | Enables RESTful web services and request handling.          |
| `org.springframework.http.HttpStatus` | Used for defining HTTP status codes in custom exceptions.      |
| `org.springframework.beans.factory.annotation.Value` | Injects configuration properties into the application. |
| `java.io.Serializable`      | Enables serialization of request and response objects.                 |

---
