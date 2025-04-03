package com.scalesec.vulnado;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommentsControllerTests {

    @InjectMocks
    private CommentsController commentsController;

    @Mock
    private User userMock;

    @Mock
    private Comment commentMock;

    @Value("${app.secret}")
    private String secret;

    // Helper method to create a mock CommentRequest
    private CommentRequest createMockCommentRequest(String username, String body) {
        CommentRequest request = new CommentRequest();
        request.username = username;
        request.body = body;
        return request;
    }

    @Test
    public void comments_ShouldReturnListOfComments_WhenAuthTokenIsValid() {
        // Arrange
        String validToken = "valid-token";
        List<Comment> mockComments = new ArrayList<>();
        mockComments.add(new Comment());
        mockComments.add(new Comment());

        Mockito.doNothing().when(userMock).assertAuth(secret, validToken);
        Mockito.when(Comment.fetch_all()).thenReturn(mockComments);

        // Act
        List<Comment> result = commentsController.comments(validToken);

        // Assert
        assertNotNull("Result should not be null", result);
        assertEquals("Result size should match mock comments size", mockComments.size(), result.size());
    }

    @Test(expected = ResponseStatusException.class)
    public void comments_ShouldThrowException_WhenAuthTokenIsInvalid() {
        // Arrange
        String invalidToken = "invalid-token";
        Mockito.doThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED)).when(userMock).assertAuth(secret, invalidToken);

        // Act
        commentsController.comments(invalidToken);
    }

    @Test
    public void createComment_ShouldReturnCreatedComment_WhenInputIsValid() {
        // Arrange
        String validToken = "valid-token";
        CommentRequest mockRequest = createMockCommentRequest("testUser", "testBody");
        Comment mockComment = new Comment();

        Mockito.doNothing().when(userMock).assertAuth(secret, validToken);
        Mockito.when(Comment.create(mockRequest.username, mockRequest.body)).thenReturn(mockComment);

        // Act
        Comment result = commentsController.createComment(validToken, mockRequest);

        // Assert
        assertNotNull("Result should not be null", result);
        assertEquals("Result should match mock comment", mockComment, result);
    }

    @Test(expected = ResponseStatusException.class)
    public void createComment_ShouldThrowException_WhenAuthTokenIsInvalid() {
        // Arrange
        String invalidToken = "invalid-token";
        CommentRequest mockRequest = createMockCommentRequest("testUser", "testBody");

        Mockito.doThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED)).when(userMock).assertAuth(secret, invalidToken);

        // Act
        commentsController.createComment(invalidToken, mockRequest);
    }

    @Test
    public void deleteComment_ShouldReturnTrue_WhenCommentIsDeletedSuccessfully() {
        // Arrange
        String validToken = "valid-token";
        String commentId = "123";
        Mockito.doNothing().when(userMock).assertAuth(secret, validToken);
        Mockito.when(Comment.delete(commentId)).thenReturn(true);

        // Act
        Boolean result = commentsController.deleteComment(validToken, commentId);

        // Assert
        assertTrue("Result should be true", result);
    }

    @Test(expected = ResponseStatusException.class)
    public void deleteComment_ShouldThrowException_WhenAuthTokenIsInvalid() {
        // Arrange
        String invalidToken = "invalid-token";
        String commentId = "123";

        Mockito.doThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED)).when(userMock).assertAuth(secret, invalidToken);

        // Act
        commentsController.deleteComment(invalidToken, commentId);
    }
}

