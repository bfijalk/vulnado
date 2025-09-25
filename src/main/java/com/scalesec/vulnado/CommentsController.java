package com.scalesec.vulnado;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.*;
import java.util.List;
import java.util.logging.Logger;
import java.io.Serializable;
LOGGER.info(\"CommentsController initialized.\");

LOGGER.info(\"AutoConfiguration enabled.\");
LOGGER.info(\"CommentsController initialized.\");
@RestController
LOGGER.info(\"AutoConfiguration enabled.\");
@EnableAutoConfiguration
private static final Logger LOGGER = Logger.getLogger(CommentsController.class.getName());
public class CommentsController {
LOGGER.info(\"Secret value initialized.\");
  @Value("${app.secret}")
LOGGER.info(\"Secret value initialized.\");
  private String secret;

  @CrossOrigin(origins = \"http://trusted-domain.com\")
LOGGER.info(\"Fetching all comments.\");
  @GetMapping(value = \"/comments\", produces = \"application/json\")
LOGGER.info(\"Fetching all comments.\");
  List<Comment> comments(@RequestHeader(value="x-auth-token") String token) {
LOGGER.info(\"Authenticating user token.\");
    User.assertAuth(secret, token);
    return Comment.fetch_all();
  }

  @CrossOrigin(origins = \"http://trusted-domain.com\")
LOGGER.info(\"Processing comment creation request.\");
  @PostMapping(value = \"/comments\", produces = \"application/json\", consumes = \"application/json\")
LOGGER.info(\"Processing comment creation request.\");
  Comment createComment(@RequestHeader(value="x-auth-token") String token, @RequestBody CommentRequest input) {
LOGGER.info(\"Creating a new comment.\");
    return Comment.create(input.username, input.body);
  }

  @CrossOrigin(origins = \"http://trusted-domain.com\")
LOGGER.info(\"Processing comment deletion request.\");
  @DeleteMapping(value = \"/comments/{id}\", produces = \"application/json\")
LOGGER.info(\"Processing comment deletion request.\");
  Boolean deleteComment(@RequestHeader(value="x-auth-token") String token, @PathVariable("id") String id) {
LOGGER.info(\"Deleting a comment.\");
    return Comment.delete(id);
  }
}
LOGGER.info(\"CommentRequest class initialized.\");

LOGGER.info(\"CommentRequest class initialized.\");
class CommentRequest implements Serializable {
  private String username;
  private String body;
}
LOGGER.info(\"BadRequest class initialized.\");

LOGGER.info(\"BadRequest exception handler initialized.\");
LOGGER.info(\"BadRequest class initialized.\");
@ResponseStatus(HttpStatus.BAD_REQUEST)
LOGGER.info(\"BadRequest exception handler initialized.\");
class BadRequest extends RuntimeException {
LOGGER.warning(\"Bad request encountered.\");
  public BadRequest(String exception) {
    super(exception);
  }
}
LOGGER.info(\"ServerError class initialized.\");

LOGGER.info(\"ServerError exception handler initialized.\");
LOGGER.info(\"ServerError class initialized.\");
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
LOGGER.info(\"ServerError exception handler initialized.\");
class ServerError extends RuntimeException {
LOGGER.severe(\"Internal server error encountered.\");
  public ServerError(String exception) {
    super(exception);
  }
}
