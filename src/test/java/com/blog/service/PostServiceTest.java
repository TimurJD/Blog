package com.blog.service;

import com.blog.dao.PostDAO;
import com.blog.entity.Post;
import com.blog.exception.InvalidPostDataException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.blog.constant.ResponseMessage.INVALID_POST_BODY;
import static com.blog.constant.ResponseMessage.INVALID_POST_TITLE;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

/**
 * @author Timur Berezhnoi
 */
@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    private PostService postService;

    @Mock
    private PostDAO postDAO;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        postService = new PostService(postDAO);
    }

    @Test
    public void shouldReturnPostsByDateDescending() {
        // Given
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date date = cal.getTime();

        int limit = 10;
        int pageNumber = 1;

        List<Post> sortedPostsList = Arrays.asList(new Post("Title2", "Body2", date), new Post("Title", "Body", new Date()));

        when(postDAO.getPostsByDateDescending(limit, pageNumber)).thenReturn(sortedPostsList);

        // When
        postService.getPostsByDateDescending(10, pageNumber);

        // Then
        verify(postDAO, times(1)).getPostsByDateDescending(limit, pageNumber);
    }

    @Test
    public void shouldReturnPostsByDateDescendingWithLimit() {
        // Given
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date date = cal.getTime();

        int limit = 10;
        int pageNumber = 1;

        List<Post> sortedPostsList = Arrays.asList(new Post("Title2", "Body2", date), new Post("Title", "Body", new Date()));

        when(postDAO.getPostsByDateDescending(limit, pageNumber)).thenReturn(sortedPostsList);

        // When
        postService.getPostsByDateDescending(limit, pageNumber);

        // Then
        verify(postDAO, times(1)).getPostsByDateDescending(limit, pageNumber);
    }

    @Test
    public void shouldReturnPostsByDateDescendingWithLimitAndPageNumber() {
        // Given
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date date = cal.getTime();

        int limit = 1;
        int pageNumber = 1;

        List<Post> sortedPostsList = Arrays.asList(new Post("Title2", "Body2", date), new Post("Title", "Body", new Date()));

        when(postDAO.getPostsByDateDescending(limit, pageNumber)).thenReturn(sortedPostsList);

        // When
        postService.getPostsByDateDescending(limit, pageNumber);

        // Then
        verify(postDAO, times(1)).getPostsByDateDescending(limit, pageNumber);
    }

    @Test
    public void shouldThrowExceprionWhenTitleIsNull() throws InvalidPostDataException {
        // Given
        Post post = new Post(null, "Some body", new Date());

        // Then
        expectedException.expect(InvalidPostDataException.class);
        expectedException.expectMessage(equalTo(INVALID_POST_TITLE.getMessage()));

        // When
        postService.addNewPost(post);
    }

    @Test
    public void shouldThrowExceprionWhenTitleMoreThan30() throws InvalidPostDataException {
        // Given
        Post post = new Post("TitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitle", "Some body", new Date());

        // Then
        expectedException.expect(InvalidPostDataException.class);
        expectedException.expectMessage(equalTo(INVALID_POST_TITLE.getMessage()));

        // When
        postService.addNewPost(post);
    }

    @Test
    public void shouldThrowExceprionWhenTitleLessThan5() throws InvalidPostDataException {
        // Given
        Post post = new Post("Tit", "Some body", new Date());

        // Then
        expectedException.expect(InvalidPostDataException.class);
        expectedException.expectMessage(equalTo(INVALID_POST_TITLE.getMessage()));

        // When
        postService.addNewPost(post);
    }

    @Test
    public void shouldThrowExceptionWhenBodyIsNull() throws InvalidPostDataException {
        // Given
        Post post = new Post("title", null, new Date());

        // Then
        expectedException.expect(InvalidPostDataException.class);
        expectedException.expectMessage(equalTo(INVALID_POST_BODY.getMessage()));

        // When
        postService.addNewPost(post);
    }
}