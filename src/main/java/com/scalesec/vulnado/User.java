package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class User {
  private String id;
private String username;

private String hashedPassword;
  public User(String id, String username, String hashedPassword) {
    this.id = id;
    this.username = username;
    this.hashedPassword = hashedPassword;
  }

  public String token(String secret) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder().setSubject(this.username).signWith(key).compact();
    return jws;
  }

  public static void assertAuth(String secret, String token) {
    try {
      SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        .setSigningKey(key)
        .parseClaimsJws(token);
    } catch(Exception e) {
      throw new Unauthorized(e.getMessage());
      throw new Unauthorized(e.getMessage());
    }
  }

  public static User fetch(String un) {
    Statement stmt = null;
    User user = null;
    try {
Logger logger = Logger.getLogger(User.class.getName());
      Connection cxn = Postgres.connection();
      try (Statement stmt = cxn.createStatement()) {
      Logger logger = Logger.getLogger(User.class.getName());
logger.info(\"Opened database successfully\");

      String query = \"SELECT * FROM users WHERE username = ? LIMIT 1\";
      logger.info(query);
      PreparedStatement pstmt = cxn.prepareStatement(\"SELECT * FROM users WHERE username = ? LIMIT 1\");
pstmt.setString(1, un);
      PreparedStatement pstmt = cxn.prepareStatement(query);
        pstmt.setString(1, un);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
        String userId = rs.getString(\"userid\");
      String username = rs.getString(\"username\");
      String password = rs.getString(\"password\");
    user = new User(userId, username, password);
      cxn.close();
      logger.severe(e.getMessage());
    logger.severe(e.getClass().getName() + \": \" + e.getMessage());
    }
  }
}
}