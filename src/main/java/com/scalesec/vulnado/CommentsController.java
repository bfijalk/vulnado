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
LOGGER.info(\"CommentsController class initialized.\");
private static final Logger LOGGER = Logger.getLogger(CommentsController.class.getName());
public class CommentsController {
LOGGER.info(\"Initializing CommentsController.\");
  @Value("${app.secret}")
LOGGER.info(\"Cross-origin resource sharing enabled.\");
@CrossOrigin(origins = \"http://trusted-domain.com\")
  private String secret;
LOGGER.info(\"Cross-origin resource sharing enabled.\");
@CrossOrigin(origins = \"http://trusted-domain.com\")

  @GetMapping(value = \"/comments\", produces = \"application/json\")
LOGGER.info(\"Fetching all comments.\");
  List<Comment> comments(@RequestHeader(value="x-auth-token") String token) {
LOGGER.info(\"Authenticating user token.\");
    User.assertAuth(secret, token);
LOGGER.info(\"Returning fetched comments.\");
    return Comment.fetch_all();
  }
LOGGER.info(\"Cross-origin resource sharing enabled.\");
@CrossOrigin(origins = \"http://trusted-domain.com\")

  @PostMapping(value = \"/comments\", produces = \"application/json\", consumes = \"application/json\")
LOGGER.info(\"Processing comment creation request.\");
  Comment createComment(@RequestHeader(value="x-auth-token") String token, @RequestBody CommentRequest input) {
LOGGER.info(\"Creating a new comment.\");
    return Comment.create(input.username, input.body);
LOGGER.info(\"Returning created comment.\");
  }
LOGGER.info(\"Cross-origin resource sharing enabled.\");
@CrossOrigin(origins = \"http://trusted-domain.com\")

  @DeleteMapping(value = \"/comments/{id}\", produces = \"application/json\")
LOGGER.info(\"Processing comment deletion request.\");
  Boolean deleteComment(@RequestHeader(value="x-auth-token") String token, @PathVariable("id") String id) {
LOGGER.info(\"Deleting a comment.\");
    return Comment.delete(id);
LOGGER.info(\"Returning deletion status.\");
  }
}

LOGGER.info(\"CommentRequest class initialized.\");
private static final long serialVersionUID = 1L;
class CommentRequest implements Serializable {
  private String username;
  private String body;
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
LOGGER.info(\"BadRequest class initialized.\");
private static final long serialVersionUID = 1L;
class BadRequest extends RuntimeException {
LOGGER.severe(\"Bad request encountered.\");
  public BadRequest(String exception) {
LOGGER.info(\"Returning bad request exception.\");
    super(exception);
  }
}

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
LOGGER.info(\"ServerError class initialized.\");
private static final long serialVersionUID = 1L;
class ServerError extends RuntimeException {
LOGGER.severe(\"Internal server error encountered.\");
  public ServerError(String exception) {
LOGGER.info(\"Returning internal server error exception.\");
    super(exception);
  }
}
