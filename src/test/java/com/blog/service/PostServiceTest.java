package com.blog.service;

import com.blog.dao.PostDAO;
import com.blog.entity.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Timur Berezhnoi
 */
@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    private PostService postService;

    @Mock
    private PostDAO postDAO;

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

        int limit = 10;
        int pageNumber = 1;

        List<Post> sortedPostsList = Arrays.asList(new Post("Title2", "Body2", date), new Post("Title", "Body", new Date()));

        when(postDAO.getPostsByDateDescending(limit, pageNumber)).thenReturn(sortedPostsList);

        // When
        postService.getPostsByDateDescending(limit, pageNumber);

        // Then
        verify(postDAO, times(1)).getPostsByDateDescending(limit, pageNumber);
    }
}