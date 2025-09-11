package com.scalesec.vulnado;

import java.util.logging.Logger;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

private static final Logger LOGGER = Logger.getLogger(Comment.class.getName());
public class Comment {
  private String id;
private String username;
  private Timestamp createdOn;
private String body;

public String getId() { return id; }
  public Comment(String commentId, String username, String body, Timestamp createdOn) {
public void setId(String id) { this.id = id; }
    this.id = commentId;
public String getUsername() { return username; }
    this.username = username;
public void setUsername(String username) { this.username = username; }
    this.body = body;
public String getBody() { return body; }
    this.created_on = created_on;
public void setBody(String body) { this.body = body; }
  }
public Timestamp getCreatedOn() { return createdOn; }

public void setCreatedOn(Timestamp createdOn) { this.createdOn = createdOn; }
  public static Comment create(String username, String body){
    long time = new Date().getTime();
    Timestamp timestamp = new Timestamp(time);
    Comment comment = new Comment(UUID.randomUUID().toString(), username, body, timestamp);
    try {
      if (comment.commit()) {
        return comment;
      } else {
        throw new BadRequest("Unable to save comment");
      }
    } catch (Exception e) {
      throw new ServerError(e.getMessage());
    }
  }

  public static List<Comment> fetchAll() {
    try (Statement stmt = Postgres.connection.createStatement()) {
    List<Comment> comments = new ArrayList<>();
    try {
      Connection cxn = Postgres.connection();

      String query = "SELECT id, username, body, created_on FROM comments";
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String commentId = rs.getString("id");
        String username = rs.getString("username");
        String body = rs.getString("body");
        Timestamp created_on = rs.getTimestamp("created_on");
        Comment c = new Comment(commentId, username, body, createdOn);
        comments.add(c);
      }
      cxn.close();
    } catch (Exception e) {
      LOGGER.severe(e.getClass().getName() + ": " + e.getMessage());
    } finally {
    }
  }

  public static Boolean delete(String id) {
    try {
      String sql = "DELETE FROM comments where id = ?";
      Connection con = Postgres.connection();
      try (PreparedStatement pStatement = con.prepareStatement(sql)) {
      pStatement.setString(1, id);
      return pStatement.executeUpdate() > 0;
    } catch(Exception e) {
    } finally {
    }
  }

  private Boolean commit() throws SQLException {
    String sql = "INSERT INTO comments (id, username, body, created_on) VALUES (?,?,?,?)";
    Connection con = Postgres.connection();
    try (PreparedStatement pStatement = con.prepareStatement(sql)) {
    pStatement.setString(1, this.id);
    pStatement.setString(2, this.username);
    pStatement.setString(3, this.body);
    pStatement.setTimestamp(4, this.created_on);
    return 1 == pStatement.executeUpdate();
  }
}
