# Documentation: `User.java`

## Overview
The `User` class is part of the `com.scalesec.vulnado` package and provides functionality for user management, including token generation, authentication, and database interaction. It encapsulates user-related data and operations, such as fetching user details from a database and verifying authentication tokens.

---

## Class: `User`

### Fields
| Field Name      | Type   | Description                                      |
|------------------|--------|--------------------------------------------------|
| `id`            | String | Unique identifier for the user.                  |
| `username`      | String | Username of the user.                            |
| `hashedPassword`| String | Hashed password of the user.                     |

### Constructor
#### `User(String id, String username, String hashedPassword)`
Initializes a new `User` object with the provided `id`, `username`, and `hashedPassword`.

---

### Methods

#### `String token(String secret)`
Generates a JSON Web Token (JWT) for the user using the provided secret key.

- **Parameters**:
  - `secret`: A string used as the secret key for signing the JWT.
- **Returns**:
  - A signed JWT containing the `username` as the subject.
- **Implementation Details**:
  - Uses the `io.jsonwebtoken` library to create the token.
  - The secret key is derived using `Keys.hmacShaKeyFor`.

---

#### `static void assertAuth(String secret, String token)`
Validates the provided JWT token using the given secret key.

- **Parameters**:
  - `secret`: A string used as the secret key for verifying the JWT.
  - `token`: The JWT to be validated.
- **Throws**:
  - `Unauthorized`: If the token validation fails.
- **Implementation Details**:
  - Uses the `io.jsonwebtoken` library to parse and validate the token.
  - If validation fails, an exception is thrown with the error message.

---

#### `static User fetch(String un)`
Fetches a user from the database based on the provided username.

- **Parameters**:
  - `un`: The username of the user to fetch.
- **Returns**:
  - A `User` object populated with data from the database, or `null` if no user is found.
- **Implementation Details**:
  - Establishes a connection to the database using `Postgres.connection()`.
  - Executes a SQL query to retrieve user details.
  - Constructs a `User` object using the retrieved data.
  - Closes the database connection after execution.
- **Potential Issues**:
  - SQL Injection: The query concatenates the `username` directly into the SQL string, making it vulnerable to SQL injection attacks.

---

## Insights

### Security Concerns
1. **SQL Injection Vulnerability**:
   - The `fetch` method directly concatenates the `username` into the SQL query string without sanitization or parameterization. This makes the application susceptible to SQL injection attacks.
   - **Recommendation**: Use prepared statements to safely handle user input.

2. **Hardcoded Secret Key Handling**:
   - The `token` and `assertAuth` methods rely on a secret key passed as a string. If the secret is not securely managed, it could lead to token forgery or compromise.
   - **Recommendation**: Store secrets securely using environment variables or a secrets management tool.

3. **Exception Handling**:
   - The `assertAuth` method catches all exceptions and rethrows them as `Unauthorized`. This could expose sensitive error details in production environments.
   - **Recommendation**: Log errors securely and avoid exposing stack traces to end users.

---

### Dependencies
The class relies on the following external libraries and classes:
- **`io.jsonwebtoken`**:
  - Used for JWT creation and validation.
- **`Postgres.connection()`**:
  - Assumed to be a utility method for establishing database connections.
- **`Unauthorized`**:
  - A custom exception class, presumably defined elsewhere in the project.

---

### Usage Scenarios
1. **Token Generation**:
   - Use the `token` method to generate a JWT for user authentication.
2. **Token Validation**:
   - Use the `assertAuth` method to verify the authenticity of a JWT.
3. **Database Interaction**:
   - Use the `fetch` method to retrieve user details from the database based on the username.

---

### Potential Enhancements
1. **Input Validation**:
   - Validate inputs for methods like `fetch` to prevent invalid or malicious data.
2. **Error Handling**:
   - Improve error handling to provide meaningful feedback without exposing sensitive information.
3. **Database Query Optimization**:
   - Use parameterized queries to enhance security and performance.
