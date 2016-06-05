//package com.blog.service;
//
//import com.blog.dao.UserDAO;
//import com.blog.entity.User;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import static com.blog.constant.ResponseMessage.INVALID_EMAIL_PATTERN;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
///**
// * @author Timur Berezhnoi
// */
//@RunWith(MockitoJUnitRunner.class)
//public class SignUpServiceTest {
//
//    private SignUpService signUpService;
//
//    @Mock
//    private UserDAO userDAO;
//
//    @Before
//    public void setUp() {
//        signUpService = new SignUpService(userDAO);
//    }
//
//    @Test
//    public void shouldContainsErrorWhenEmailInvalid() {
//        // Given
//        User user = new User("mdgogel.com", "name", "last", "123456");
//        String error = INVALID_EMAIL_PATTERN.getMessage();
//
//        // When
//        Notification notification = signUpService.signUp(user);
//
//        // Then
//        verify(userDAO, times(1)).addUser(user);
//        assertEquals(error, notification.getError());
//    }
//
////    @Test
////    public void shouldThrowExceptionWhenPasswordContainsSpecialCharacters() {
////        // Given
////        User user = new User("goe@gmail.com", "namexa", "lzast", "@#^%@");
////
////        // Then
////
////        // When
////        signUpService.signUp(user);
////    }
////
////    @Test
////    public void shouldThrowExceptionWhenEmailNull() {
////        // Given
////        User user = new User(null, "namexa", "lzast", "@#^%@");
////
////        // Then
////
////        // When
////        signUpService.signUp(user);
////    }
////
////    @Test
////    public void shouldThrowExceptionWhenPasswordNull() {
////        // Given
////        User user = new User("goe@gmail.com", "namexa", "lzast", null);
////
////        // Then
////
////        // When
////        signUpService.signUp(user);
////    }
////
////    @Test
////    public void shouldThrowExceptionWhenNameNull() {
////        // Given
////        User user = new User("goe@gmail.com", null, "lzast", "uyqwgw");
////
////        // Then
////
////        // When
////        signUpService.signUp(user);
////    }
////
////    @Test
////    public void shouldThrowExceptionWhenLastNameNull() {
////        // Given
////        User user = new User("goe@gmail.com", "namexa", null, "asdsa");
////
////        // Then
////
////        // When
////        signUpService.signUp(user);
////    }
////
////    @Test
////    public void shouldThrowExceptionWhenPasswordIsToShort() {
////        // Given
////        User user = new User("goe@gmail.com", "namexa", "adasd", "1");
////
////        // Then
////
////        // When
////        signUpService.signUp(user);
////    }
////
////    @Test
////    public void shouldThrowExceptionWhenPasswordIsToLong() {
////        // Given
////        User user = new User("goe@gmail.com", "namexa", "adasd", "asdasdasdasdasdasdasdasd");
////
////        // Then
////
////        // When
////        signUpService.signUp(user);
////    }
////
////    @Test
////    public void shouldThrowExceptionWhenEmailIsToShort() {
////        // Given
////        User user = new User("g@l.m", "namexa", "adasd", "123456");
////
////        // Then
////
////        // When
////        signUpService.signUp(user);
////    }
////
////    @Test
////    public void shouldThrowExceptionWhenEmailIsToLong() {
////        // Given
////        User user = new User("gasdhgasdjasgdkjasdhgjashdgasdasd@l.m", "namexa", "adasd", "123456");
////
////        // Then
////
////        // When
////        signUpService.signUp(user);
////    }
////
////    @Test
////    public void shouldThrowExceptionIfUserAlredyExist() {
////        // Given
////        User user = new User("email@gmail.com", "Optimus", "Prime", "123456");
////
////        when(userDAO.getUserByEmail(user.getEmail())).thenReturn(user);
////
////        // Then
////
////        // When
////        signUpService.signUp(user);
////    }
//}