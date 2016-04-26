package com.blog.service;

import com.blog.dao.UserDAO;
import com.blog.entity.User;
import com.blog.exception.InvalidUserDataException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * @author Timur Berezhnoi
 */
@RunWith(MockitoJUnitRunner.class)
public class SignUpServiceTest {

    private SignUpService signUpService;

    @Mock
    private UserDAO userDAO;

    @Before
    public void setUp() {
        signUpService = new SignUpService(userDAO);
    }

    @Test
    public void test() throws InvalidUserDataException {
        // Given
        User user = new User("mdeca@gogel.com", "name", "last", "123456");

        when(userDAO.getUserByEmail(user.getEmail())).thenReturn(null);
        when(userDAO.addUser(user)).thenReturn(true);

        // When
        signUpService.signUp(user);

        // Then
        verify(userDAO, times(1)).addUser(user);
    }
}