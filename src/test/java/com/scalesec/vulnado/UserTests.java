package com.scalesec.vulnado;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    @Test
    public void token_ShouldGenerateValidToken() {
        // Arrange
        String secret = "mysecretkey123456789012345678901234567890";
        User user = new User("1", "testuser", "hashedpassword");

        // Act
        String token = user.token(secret);

        // Assert
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        String subject = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        assertEquals("Token should contain the correct username", "testuser", subject);
    }

    @Test
    public void assertAuth_ShouldValidateTokenSuccessfully() {
        // Arrange
        String secret = "mysecretkey123456789012345678901234567890";
        User user = new User("1", "testuser", "hashedpassword");
        String token = user.token(secret);

        // Act & Assert
        try {
            User.assertAuth(secret, token);
        } catch (Exception e) {
            fail("Token validation should not throw an exception");
        }
    }

    @Test(expected = Unauthorized.class)
    public void assertAuth_ShouldThrowUnauthorizedForInvalidToken() {
        // Arrange
        String secret = "mysecretkey123456789012345678901234567890";
        String invalidToken = "invalidtoken";

        // Act
        User.assertAuth(secret, invalidToken);
    }

    @Test
    public void fetch_ShouldReturnUserForValidUsername() throws Exception {
        // Arrange
        String username = "testuser";
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery("select * from users where username = '" + username + "' limit 1")).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("user_id")).thenReturn("1");
        when(mockResultSet.getString("username")).thenReturn(username);
        when(mockResultSet.getString("password")).thenReturn("hashedpassword");

        Postgres mockPostgres = mock(Postgres.class);
        when(mockPostgres.connection()).thenReturn(mockConnection);

        // Act
        User user = User.fetch(username);

        // Assert
        assertNotNull("User should not be null", user);
        assertEquals("User ID should match", "1", user.id);
        assertEquals("Username should match", username, user.username);
        assertEquals("Password should match", "hashedpassword", user.hashedPassword);
    }

    @Test
    public void fetch_ShouldReturnNullForInvalidUsername() throws Exception {
        // Arrange
        String username = "invaliduser";
        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery("select * from users where username = '" + username + "' limit 1")).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        Postgres mockPostgres = mock(Postgres.class);
        when(mockPostgres.connection()).thenReturn(mockConnection);

        // Act
        User user = User.fetch(username);

        // Assert
        assertNull("User should be null for invalid username", user);
    }
}

