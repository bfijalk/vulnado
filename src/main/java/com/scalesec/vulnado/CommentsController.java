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
public class CommentsController {
  @Value("${app.secret}")
private static final Logger LOGGER = Logger.getLogger(CommentsController.class.getName());
  private String secret;

  @CrossOrigin(origins = \"http://trusted-domain.com\")
  @GetMapping(value = \"/comments\", produces = \"application/json\")
LOGGER.info(\"Comment fetch finalized.\");
LOGGER.info(\"Comment fetch initiated.\");
LOGGER.info(\"Processing comment fetch request.\");
  List<Comment> comments(@RequestHeader(value="x-auth-token") String token) {
LOGGER.info(\"User authentication finalized.\");
LOGGER.info(\"User authentication initiated.\");
LOGGER.info(\"User authenticated successfully.\");
LOGGER.info(\"Authenticating user token.\");
    User.assertAuth(secret, token);
LOGGER.info(\"Comment fetch finalized.\");
LOGGER.info(\"Comment fetch completed.\");
LOGGER.info(\"Fetching all comments.\");
    return Comment.fetch_all();
  }

  @CrossOrigin(origins = \"http://trusted-domain.com\")
  @PostMapping(value = \"/comments\", produces = \"application/json\", consumes = \"application/json\")
LOGGER.info(\"Comment creation finalized.\");
LOGGER.info(\"Comment creation initiated.\");
LOGGER.info(\"Processing comment creation request.\");
  Comment createComment(@RequestHeader(value="x-auth-token") String token, @RequestBody CommentRequest input) {
LOGGER.info(\"Comment creation finalized.\");
LOGGER.info(\"Comment creation completed.\");
LOGGER.info(\"Creating a new comment.\");
    return Comment.create(input.username, input.body);
LOGGER.info(\"Comment creation finalized.\");
LOGGER.info(\"Comment created successfully.\");
  }

  @CrossOrigin(origins = \"http://trusted-domain.com\")
  @DeleteMapping(value = \"/comments/{id}\", produces = \"application/json\")
LOGGER.info(\"Comment deletion finalized.\");
LOGGER.info(\"Comment deletion initiated.\");
LOGGER.info(\"Processing comment deletion request.\");
  Boolean deleteComment(@RequestHeader(value="x-auth-token") String token, @PathVariable("id") String id) {
LOGGER.info(\"Comment deletion finalized.\");
LOGGER.info(\"Comment deletion completed.\");
LOGGER.info(\"Deleting a comment.\");
    return Comment.delete(id);
LOGGER.info(\"Comment deletion finalized.\");
LOGGER.info(\"Comment deleted successfully.\");
  }
}

class CommentRequest implements Serializable {
  private String username;
  private String body;
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequest extends RuntimeException {
  public BadRequest(String exception) {
    super(exception);
  }
}

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class ServerError extends RuntimeException {
  public ServerError(String exception) {
    super(exception);
  }
}
