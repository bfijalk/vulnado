# Documentation: `Comment` Class

## Overview

The `Comment` class is a Java implementation for managing user comments. It provides functionality to create, retrieve, and delete comments stored in a PostgreSQL database. Each comment includes an ID, username, body, and timestamp of creation. The class interacts with the database using SQL queries and handles exceptions for error scenarios.

---

## Class Details

### Package
- **Package Name**: `com.scalesec.vulnado`

### Dependencies
The class relies on the following imports:
- `org.apache.catalina.Server` (Unused in the current implementation)
- `java.sql.*` (For database interaction)
- `java.util.*` (For utility classes like `Date`, `List`, `ArrayList`, and `UUID`)

---

## Data Structure

### Fields
The `Comment` class defines the following fields:
| Field Name   | Type           | Description                          |
|--------------|----------------|--------------------------------------|
| `id`         | `String`       | Unique identifier for the comment.  |
| `username`   | `String`       | Username of the comment's author.   |
| `body`       | `String`       | Content of the comment.             |
| `created_on` | `Timestamp`    | Timestamp when the comment was created. |

### Constructor
The class provides a constructor to initialize a `Comment` object:
```java
public Comment(String id, String username, String body, Timestamp created_on)
```

---

## Methods

### 1. `create(String username, String body)`
Creates a new comment and saves it to the database.

| Parameter   | Type     | Description                          |
|-------------|----------|--------------------------------------|
| `username`  | `String` | Username of the comment's author.   |
| `body`      | `String` | Content of the comment.             |

**Returns**: A `Comment` object if successfully created. Throws exceptions in case of failure:
- `BadRequest` if the comment cannot be saved.
- `ServerError` for other exceptions.

**Logic**:
- Generates a unique ID using `UUID`.
- Captures the current timestamp.
- Calls the private `commit()` method to save the comment to the database.

---

### 2. `fetch_all()`
Retrieves all comments from the database.

**Returns**: A `List<Comment>` containing all comments.

**Logic**:
- Executes a SQL query (`SELECT * FROM comments`) to fetch all records.
- Maps each record to a `Comment` object and adds it to the list.
- Handles exceptions by printing stack traces and error messages.

---

### 3. `delete(String id)`
Deletes a comment from the database based on its ID.

| Parameter   | Type     | Description                          |
|-------------|----------|--------------------------------------|
| `id`        | `String` | Unique identifier of the comment.   |

**Returns**: `true` if the comment is successfully deleted, otherwise `false`.

**Logic**:
- Executes a SQL query (`DELETE FROM comments WHERE id = ?`) to delete the record.
- Uses a prepared statement to prevent SQL injection.

---

### 4. `commit()`
A private method to save the current `Comment` object to the database.

**Returns**: `true` if the comment is successfully saved, otherwise throws an exception.

**Logic**:
- Executes a SQL query (`INSERT INTO comments (id, username, body, created_on) VALUES (?,?,?,?)`) to insert the record.
- Uses a prepared statement to bind parameters.

---

## Insights

1. **Database Interaction**:
   - The class heavily relies on a PostgreSQL database for storing and retrieving comments.
   - The `Postgres.connection()` method is assumed to provide a valid database connection.

2. **Error Handling**:
   - Exceptions are caught and printed, but some methods (e.g., `delete`) always return `false` in the `finally` block, even if the operation succeeds. This is a potential bug.

3. **Security**:
   - Prepared statements are used to prevent SQL injection.
   - However, there is no input validation for `username` and `body` fields, which could lead to issues like storing invalid data.

4. **Scalability**:
   - The `fetch_all()` method retrieves all comments at once, which may not scale well for large datasets. Pagination or filtering could improve performance.

5. **Unused Import**:
   - The `org.apache.catalina.Server` import is not used in the current implementation and can be removed.

6. **Custom Exceptions**:
   - The class uses custom exceptions (`BadRequest` and `ServerError`), but their definitions are not provided. These should be implemented to ensure proper error handling.

7. **Thread Safety**:
   - The class does not address thread safety. Concurrent access to the database could lead to race conditions or data corruption.

8. **Code Quality**:
   - The `finally` block in `fetch_all()` and `delete` methods always returns a value, which may not reflect the actual operation result. This should be reviewed and corrected.
