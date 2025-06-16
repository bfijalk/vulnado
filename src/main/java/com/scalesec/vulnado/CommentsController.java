package com.scalesec.vulnado;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.*;
import java.util.List;
// Import Logger for logging purposes
import java.util.logging.Logger;
import java.io.Serializable;

// CommentsController manages comment-related operations
@RestController
@EnableAutoConfiguration
// Ensure safe CORS configuration
public class CommentsController {
// Secret key for authentication
  @Value("${app.secret}")
// Logger for tracking application events
private static final Logger LOGGER = Logger.getLogger(CommentsController.class.getName());
  private String secret;

// Configure CORS for trusted domain
  @CrossOrigin(origins = \"http://trusted-domain.com\")
// Map GET request to fetch comments
  @GetMapping(value = \"/comments\", produces = \"application/json\")
// Fetch all comments
  List<Comment> comments(@RequestHeader(value="x-auth-token") String token) {
// Log authentication process
LOGGER.info(\"Authenticating user token.\");
    User.assertAuth(secret, token);
    return Comment.fetch_all();
  }

// Configure CORS for trusted domain
  @CrossOrigin(origins = \"http://trusted-domain.com\")
// Map POST request to create comments
  @PostMapping(value = \"/comments\", produces = \"application/json\", consumes = \"application/json\")
// Create a new comment
  Comment createComment(@RequestHeader(value="x-auth-token") String token, @RequestBody CommentRequest input) {
//
// Log comment creation process
LOGGER.info(\"Creating a new comment.\");
    return Comment.create(input.username, input.body);
  }

// Configure CORS for trusted domain
  @CrossOrigin(origins = \"http://trusted-domain.com\")
// Map DELETE request to delete comments
  @DeleteMapping(value = \"/comments/{id}\", produces = \"application/json\")
// Delete a comment
  Boolean deleteComment(@RequestHeader(value="x-auth-token") String token, @PathVariable("id") String id) {
// Log comment deletion process
LOGGER.info(\"Deleting a comment.\");
    return Comment.delete(id);
  }
}

// Encapsulate fields for CommentRequest
class CommentRequest implements Serializable {
// Username of the comment author
  private String username;
// Body of the comment
  private String body;
}

// Handle bad request exceptions
@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequest extends RuntimeException {
// Log bad request details
LOGGER.warning(\"Bad request encountered.\");
  public BadRequest(String exception) {
    super(exception);
  }
}

// Handle server error exceptions
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class ServerError extends RuntimeException {
// Log internal server error details
LOGGER.severe(\"Internal server error encountered.\");
  public ServerError(String exception) {
    super(exception);
  }
}
