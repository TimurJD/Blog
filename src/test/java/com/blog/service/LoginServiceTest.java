package com.blog.service;

import com.blog.dao.UserDAO;
import com.blog.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.blog.constant.ResponseMessage.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Timur Berezhnoi
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    private String email;
    private String password;
    private String error;

    private LoginService loginService;

    @Mock
    private UserDAO userDAO;

    @Before
    public void setUp() {
        loginService = new LoginService(userDAO);
    }

    @Test
    public void shouldReturnUserWhenLoginSuccessfull() {
        // Given
        email = "email@g.com";
        password = "987654321";
        User user = new User("email@g.com", "fisrt", "second", PasswordHasher.hashPassword(password));

        when(userDAO.getUserByEmail(email)).thenReturn(user);

        // When
        User loggedUser = loginService.getUser(email, password);

        // Then
        verify(userDAO, times(1)).getUserByEmail(email);
        assertNotNull(loggedUser);
    }

    @Test
    public void shouldContaninsErrorWhenEmailInvalid() {
        // Given
        setIncomingParameters("invalidEmail", "123456", INVALID_EMAIL_PATTERN.getMessage());

        // When
        Notification notification = loginService.validateLoginData(email, password);

        // Then
        assertTrue(notification.hasErrors());
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldContainsErrorWhenPasswordToShort() {
        // Given
        setIncomingParameters("tim@gmail.com", "12", INVALID_PASSWORD_LENGTH.getMessage());

        // When
        Notification notification = loginService.validateLoginData(email, password);

        // Then
        assertTrue(notification.hasErrors());
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldContainsErrorWhenEmailIsNull() {
        // Given
        setIncomingParameters(null, "1234567", INVALID_EMAIL_NULL.getMessage());

        // When
        Notification notification = loginService.validateLoginData(email, password);

        // Then
        assertTrue(notification.hasErrors());
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldContainsErrorWhenPasswordIsNull() {
        // Given
        setIncomingParameters("tim@g.com", null, INVALID_PASSWORD_NULL.getMessage());

        // When
        Notification notification = loginService.validateLoginData(email, password);

        // Then
        assertTrue(notification.hasErrors());
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldContainsErrorWhenEmailAndPasswordAreNull() {
        // Given
        setIncomingParameters(null, null, INVALID_EMAIL_NULL.getMessage() + ", " + INVALID_PASSWORD_NULL.getMessage());

        // When
        Notification notification = loginService.validateLoginData(email, password);

        // Then
        assertTrue(notification.hasErrors());
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldContainsErrorWhenEmailIsToLong() {
        // Given
        setIncomingParameters("timsadasdasdsadasdsasadasdasdasdxzxczxczxczxczxczxc@g.com", "123456", INVALID_EMAIL_LENGTH.getMessage());

        // When
        Notification notification = loginService.validateLoginData(email, password);

        // Then
        assertTrue(notification.hasErrors());
        assertEquals(error, notification.getError());
    }

    @Test
    public void shouldContainsErrorWhenPasswordIsToLong() {
        // Given
        setIncomingParameters("ts@g.com", "sadas21098310293uisoki09qwie20193ioksoasoosososo123456", INVALID_PASSWORD_LENGTH.getMessage());

        // When
        Notification notification = loginService.validateLoginData(email, password);

        // Then
        assertTrue(notification.hasErrors());
        assertEquals(error, notification.getError());
    }

    private void setIncomingParameters(String email, String password, String error) {
        this.email = email;
        this.password = password;
        this.error = error;
    }
}