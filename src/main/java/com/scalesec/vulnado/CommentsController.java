package com.scalesec.vulnado;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.*;
import java.util.List;
import java.util.logging.Logger;
import java.io.Serializable;

@RestController
@EnableAutoConfiguration
// Ensure proper logging and security measures are in place.
public class CommentsController {
  @Value("${app.secret}")
private static final Logger LOGGER = Logger.getLogger(CommentsController.class.getName());
  private String secret;

  @CrossOrigin(origins = "http://trusted-domain.com")
  @GetMapping(value = "/comments", produces = "application/json")
  List<Comment> comments(@RequestHeader(value="x-auth-token") String token) {
LOGGER.info("Authenticating user token.");
    User.assertAuth(secret, token);
    return Comment.fetch_all();
  }

  @CrossOrigin(origins = "http://trusted-domain.com")
  @PostMapping(value = "/comments", produces = "application/json", consumes = "application/json")
  Comment createComment(@RequestHeader(value="x-auth-token") String token, @RequestBody CommentRequest input) {
LOGGER.info("Creating a new comment.");
    return Comment.create(input.username, input.body);
  }

  @CrossOrigin(origins = "http://trusted-domain.com")
  @DeleteMapping(value = "/comments/{id}", produces = "application/json")
  Boolean deleteComment(@RequestHeader(value="x-auth-token") String token, @PathVariable("id") String id) {
LOGGER.info("Deleting a comment.");
    return Comment.delete(id);
  }
}

// CommentRequest class for handling user input.
class CommentRequest implements Serializable {
  private String username;
  private String body;
}

// Exception classes for handling errors.
@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequest extends RuntimeException {
LOGGER.warning("Bad request encountered.");
  public BadRequest(String exception) {
    super(exception);
  }
}

// Exception classes for handling errors.
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class ServerError extends RuntimeException {
LOGGER.severe("Internal server error encountered.");
  public ServerError(String exception) {
    super(exception);
  }
}
