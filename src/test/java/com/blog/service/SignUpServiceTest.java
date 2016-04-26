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

import static com.blog.constant.ResponseMessage.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

/**
 * @author Timur Berezhnoi
 */
@RunWith(MockitoJUnitRunner.class)
public class SignUpServiceTest {

    private SignUpService signUpService;

    @Mock
    private UserDAO userDAO;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        signUpService = new SignUpService(userDAO);
    }

    @Test
    public void shouldThrowExceptionWhenEmailInvalid() throws InvalidUserDataException {
        // Given
        User user = new User("mdgogel.com", "name", "last", "123456");

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_EMAIL.getMessage()));
        signUpService.signUp(user);

        // When
        verify(userDAO, times(1)).addUser(user);
    }

    @Test
    public void shouldThrowExceptionWhenPasswordContainsSpecialCharacters() throws InvalidUserDataException {
        // Given
        User user = new User("goe@gmail.com", "namexa", "lzast", "@#^%@");

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_PASSWORD.getMessage()));

        // When
        signUpService.signUp(user);
    }

    @Test
    public void shouldThrowExceptionWhenEmailNull() throws InvalidUserDataException {
        // Given
        User user = new User(null, "namexa", "lzast", "@#^%@");

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_EMAIL.getMessage()));

        // When
        signUpService.signUp(user);
    }

    @Test
    public void shouldThrowExceptionWhenPasswordNull() throws InvalidUserDataException {
        // Given
        User user = new User("goe@gmail.com", "namexa", "lzast", null);

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_PASSWORD.getMessage()));

        // When
        signUpService.signUp(user);
    }

    @Test
    public void shouldThrowExceptionWhenNameNull() throws InvalidUserDataException {
        // Given
        User user = new User("goe@gmail.com", null, "lzast", "uyqwgw");

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_NAME.getMessage()));

        // When
        signUpService.signUp(user);
    }

    @Test
    public void shouldThrowExceptionWhenLastNameNull() throws InvalidUserDataException {
        // Given
        User user = new User("goe@gmail.com", "namexa", null, "asdsa");

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_NAME.getMessage()));

        // When
        signUpService.signUp(user);
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsToShort() throws InvalidUserDataException {
        // Given
        User user = new User("goe@gmail.com", "namexa", "adasd", "1");

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_PASSWORD.getMessage()));

        // When
        signUpService.signUp(user);
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsToLong() throws InvalidUserDataException {
        // Given
        User user = new User("goe@gmail.com", "namexa", "adasd", "asdasdasdasdasdasdasdasd");

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_PASSWORD.getMessage()));

        // When
        signUpService.signUp(user);
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsToShort() throws InvalidUserDataException {
        // Given
        User user = new User("g@l.m", "namexa", "adasd", "123456");

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_EMAIL.getMessage()));

        // When
        signUpService.signUp(user);
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsToLong() throws InvalidUserDataException {
        // Given
        User user = new User("gasdhgasdjasgdkjasdhgjashdgasdasd@l.m", "namexa", "adasd", "123456");

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(INVALID_USER_EMAIL.getMessage()));

        // When
        signUpService.signUp(user);
    }

    @Test
    public void shouldThrowExceptionIfUserAlredyExist() throws InvalidUserDataException {
        // Given
        User user = new User("email@gmail.com", "Optimus", "Prime", "123456");

        when(userDAO.getUserByEmail(user.getEmail())).thenReturn(user);

        // Then
        expectedException.expect(InvalidUserDataException.class);
        expectedException.expectMessage(equalTo(EMAIL_IN_USE.getMessage()));

        // When
        signUpService.signUp(user);
    }
}