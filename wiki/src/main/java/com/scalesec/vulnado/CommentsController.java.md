# Documentation: CommentsController.java

## Overview
The `CommentsController` class is a RESTful controller implemented using Spring Boot. It provides endpoints for managing comments, including fetching, creating, and deleting comments. The controller includes authentication mechanisms and error handling for bad requests and server errors.

---

## Class Details

### **CommentsController**
The main controller class that handles HTTP requests related to comments.

#### **Fields**
| Field Name | Type   | Description                                                                 |
|------------|--------|-----------------------------------------------------------------------------|
| `secret`   | String | A secret value injected from application properties for authentication.     |

#### **Endpoints**
| HTTP Method | Endpoint         | Description                                                                 |
|-------------|------------------|-----------------------------------------------------------------------------|
| `GET`       | `/comments`      | Fetches all comments. Requires an authentication token in the request header. |
| `POST`      | `/comments`      | Creates a new comment. Requires an authentication token and a JSON request body. |
| `DELETE`    | `/comments/{id}` | Deletes a comment by its ID. Requires an authentication token.              |

#### **Methods**
1. **`comments(String token)`**
   - **Description**: Fetches all comments.
   - **Parameters**:
     - `token`: Authentication token passed in the `x-auth-token` header.
   - **Returns**: A list of `Comment` objects.
   - **Authentication**: Validates the token using `User.assertAuth`.

2. **`createComment(String token, CommentRequest input)`**
   - **Description**: Creates a new comment.
   - **Parameters**:
     - `token`: Authentication token passed in the `x-auth-token` header.
     - `input`: A `CommentRequest` object containing the username and comment body.
   - **Returns**: The created `Comment` object.

3. **`deleteComment(String token, String id)`**
   - **Description**: Deletes a comment by its ID.
   - **Parameters**:
     - `token`: Authentication token passed in the `x-auth-token` header.
     - `id`: The ID of the comment to be deleted.
   - **Returns**: A boolean indicating whether the deletion was successful.

---

### **CommentRequest**
A data structure representing the request body for creating a comment.

#### **Fields**
| Field Name | Type   | Description                     |
|------------|--------|---------------------------------|
| `username` | String | The username of the commenter. |
| `body`     | String | The content of the comment.    |

---

### **BadRequest**
A custom exception class for handling bad requests.

#### **Constructor**
- **`BadRequest(String exception)`**
  - **Description**: Initializes the exception with a custom message.
  - **HTTP Status**: Returns `400 BAD_REQUEST`.

---

### **ServerError**
A custom exception class for handling server errors.

#### **Constructor**
- **`ServerError(String exception)`**
  - **Description**: Initializes the exception with a custom message.
  - **HTTP Status**: Returns `500 INTERNAL_SERVER_ERROR`.

---

## Insights

1. **Authentication**: The controller uses a secret value (`app.secret`) to validate authentication tokens via the `User.assertAuth` method. This ensures that only authorized users can access the endpoints.

2. **Cross-Origin Resource Sharing (CORS)**: All endpoints are configured to allow requests from any origin (`@CrossOrigin(origins = "*")`), which is useful for enabling communication between different domains but may pose security risks if not properly managed.

3. **Error Handling**: Custom exceptions (`BadRequest` and `ServerError`) are used to handle specific error scenarios, providing meaningful HTTP status codes and messages.

4. **Data Structure**: The `CommentRequest` class is a simple data structure for encapsulating the input required to create a comment. It is marked as `Serializable`, which allows it to be easily transmitted or stored.

5. **Scalability**: The controller relies on static methods (`Comment.fetch_all`, `Comment.create`, and `Comment.delete`) for data operations. This design may limit flexibility and scalability if the application grows in complexity.

6. **Security Considerations**:
   - The use of a secret for authentication is a good practice, but the actual implementation of `User.assertAuth` is not shown, which is critical for ensuring secure token validation.
   - Allowing all origins in CORS may expose the application to potential security vulnerabilities, especially in production environments.

7. **HTTP Status Codes**: The controller adheres to RESTful principles by using appropriate HTTP status codes for responses, such as `400 BAD_REQUEST` and `500 INTERNAL_SERVER_ERROR`.
