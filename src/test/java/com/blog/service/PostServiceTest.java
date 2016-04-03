package com.blog.service;

import com.blog.dao.PostDAO;
import com.blog.entity.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
    public void shouldReturnAllPosts() {
        // Given
        List<Post> postList = Arrays.<Post> asList(new Post("Title", "Body", new Date()));

        when(postDAO.getAllPosts()).thenReturn(postList);

        // When
        List<Post> result = postService.getAllPosts();

        // Then
        verify(postDAO, times(1)).getAllPosts();

        assertNotNull(result);
        assertEquals(postList, result);
    }
}