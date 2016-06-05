package com.blog.service;

import com.blog.dao.UserDAO;
import com.blog.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.blog.constant.ResponseMessage.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Timur Berezhnoi
 */
@RunWith(MockitoJUnitRunner.class)
public class SignUpServiceTest {

    private SignUpService signUpService;
    private String error;

    @Mock
    private UserDAO userDAO;

    @Before
    public void setUp() {
        signUpService = new SignUpService(userDAO);
    }

    @Test
    public void shouldErrorWhenEmailInvalid() {
        // Given
        User user = new User("mdgogel.com", "name", "last", "123456");
        error = INVALID_EMAIL_PATTERN.getMessage();

        // When
        Notification notification = signUpService.validate(user);

        // Then
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldErrorWhenEmailNull() {
        // Given
        User user = new User(null, "namexa", "lzast", "@#^%@s");
        error = INVALID_EMAIL_NULL.getMessage();

        // When
        Notification notification = signUpService.validate(user);

        // Then
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldErrorWhenPasswordNull() {
        // Given
        User user = new User("goe@gmail.com", "namexa", "lzast", null);
        error = INVALID_PASSWORD_NULL.getMessage();

        // When
        Notification notification = signUpService.validate(user);

        // Then
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldErrorWhenFirstNameNull() {
        // Given
        User user = new User("goe@gmail.com", null, "lzast", "uyqwgw");
        error = INVALID_FIRST_NAME_NULL.getMessage();

        // When
        Notification notification = signUpService.validate(user);

        // Then
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldErrorWhenLastNameNull() {
        // Given
        User user = new User("goe@gmail.com", "namexa", null, "asdkhbsa");
        error = INVALID_LAST_NAME_NULL.getMessage();

        // When
        Notification notification = signUpService.validate(user);

        // Then
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldErrorWhenPasswordIsToShort() {
        // Given
        User user = new User("goe@gmail.com", "namexa", "adasd", "1");
        error = INVALID_PASSWORD_LENGTH.getMessage();

        // When
        Notification notification = signUpService.validate(user);

        // Then
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldErrorWhenPasswordIsToLong() {
        // Given
        User user = new User("goe@gmail.com", "namexa", "adasd", "asdasdasdasdasdasdasdasd");
        error = INVALID_PASSWORD_LENGTH.getMessage();

        // When
        Notification notification = signUpService.validate(user);

        // Then
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldErrorWhenEmailIsToLong() {
        // Given
        User user = new User("gasdhgasdjasgdkjasdhgjashdgasdasd@l.m", "namexa", "adasd", "123456");
        error = INVALID_EMAIL_LENGTH.getMessage();

        // When
        Notification notification = signUpService.validate(user);

        // Then
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldErrorIfUserAlredyExist() {
        // Given
        User user = new User("email@gmail.com", "Optimus", "Prime", "123456");
        error = EMAIL_IN_USE.getMessage();

        when(userDAO.getUserByEmail(user.getEmail())).thenReturn(user);

        // When
        Notification notification = signUpService.validate(user);

        // Then
        assertEquals(error, notification.getError());
    }
}