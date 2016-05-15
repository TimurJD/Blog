package com.blog.service;

import com.blog.dao.UserDAO;
import com.blog.entity.User;
import com.blog.exception.InvalidUserDataException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.blog.constant.ResponseMessage.INVALID_USER_EMAIL;
import static com.blog.constant.ResponseMessage.INVALID_USER_PASSWORD;
import static com.blog.constant.ResponseMessage.LOGIN_FAIL;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Timur Berezhnoi
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    private LoginService loginService;
    private String email;
    private String password;

    @Mock
    private UserDAO userDAO;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        loginService = new LoginService(userDAO);
    }

    @Test
    public void shouldReturnUserWhenLoginSuccessfull() throws InvalidUserDataException {
        // Given
        email = "email@g.com";
        password = "987654321";

        // Expected user
        User user = new User("email@g.com", "fisrt", "second", PasswordHasher.hashPassword(password));

        when(userDAO.getUserByEmail(email)).thenReturn(user);

        // When - Then
        assertEquals(user, loginService.login(email, password));
        verify(userDAO, times(1)).getUserByEmail(email);
    }

    @Test
    public void shouldThrowExceptionWhenEmailInvalid() throws InvalidUserDataException {
        // Given
        email = "invalidEmail";
        password = "invalidPassword";

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_EMAIL.getMessage()));

        // When
        loginService.login(email, password);
    }

    @Test
    public void shouldThrowExceptionWhenPasswordContainsSpecialCharacters() throws InvalidUserDataException {
        // Given
        email = "tim@gmail.com";
        password = "@#^%@";

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_PASSWORD.getMessage()));

        // When
        loginService.login(email, password);
    }

    @Test
    public void shouldThrowExceptionWhenEmailNull() throws InvalidUserDataException {
        // Given
        email = null;
        password = "123456";

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_EMAIL.getMessage()));

        // When
        loginService.login(email, password);
    }

    @Test
    public void shouldThrowExceptionWhenPasswordNull() throws InvalidUserDataException {
        // Given
        email = "tim@g.com";
        password = null;

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_PASSWORD.getMessage()));

        // When
        loginService.login(email, password);
    }

    @Test
    public void shouldThrowExceptionWhenUserDoesnotExist() throws InvalidUserDataException {
        // Given
        email = "tim@g.com";
        password = "123456";

        // Expected user
        User user = null;

        when(userDAO.getUserByEmail(email)).thenReturn(user);

        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(LOGIN_FAIL.getMessage()));

        // When - Then
        assertEquals(user, loginService.login(email, password));
        verify(userDAO, times(1)).getUserByEmail(email);
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsToLong() throws InvalidUserDataException {
        // Given
        email = "timsadasdasdsadasdsasadasdasdasdxzxczxczxczxczxczxc@g.com";
        password = "123456";

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_EMAIL.getMessage()));

        // When
        loginService.login(email, password);
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsToLong() throws InvalidUserDataException {
        // Given
        email = "ts@g.com";
        password = "sadas21098310293uisoki09qwie20193ioksoasoosososo123456";

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_PASSWORD.getMessage()));

        // When
        loginService.login(email, password);
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsShort() throws InvalidUserDataException {
        // Given
        email = "ts@g.com";
        password = "123";

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_PASSWORD.getMessage()));

        // When
        loginService.login(email, password);
    }
}