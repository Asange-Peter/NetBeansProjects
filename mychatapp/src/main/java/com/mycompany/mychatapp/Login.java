/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mychatapp;

/**
 *
 * @author Asange
 */
public class Login {
    
}
class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        // Initialize the Login object before each test
        login = new Login();
    }

    // USERNAME TESTS
    @Test
    void testValidUsername() {
        assertTrue(login.checkUserName("ky_1"));
    }

    @Test
    void testInvalidUsername_NoUnderscore() {
        assertFalse(login.checkUserName("kyle"));
    }

    @Test
    void testInvalidUsername_TooLong() {
        // Username should not exceed 5 characters
        assertFalse(login.checkUserName("kyleeee"));
    }

    // PASSWORD TESTS
    @Test
    void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    void testInvalidPassword_NoUpperCase() {
        assertFalse(login.checkPasswordComplexity("ch&&sec@ke99!"));
    }

    @Test
    void testInvalidPassword_NoNumber() {
        assertFalse(login.checkPasswordComplexity("Ch&&sec@ke!!"));
    }

    @Test
    void testInvalidPassword_NoSpecialCharacter() {
        assertFalse(login.checkPasswordComplexity("Chsecreke99"));
    }


    // PHONE NUMBER TESTS
    @Test
    void testValidPhoneNumber() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    void testInvalidPhoneNumber_NoInternationalCode() {
        assertFalse(login.checkCellPhoneNumber("0838968976"));
    }

    @Test
    void testInvalidPhoneNumber_TooLong() {
        assertFalse(login.checkCellPhoneNumber("+27838968976123"));
    }

    // REGISTRATION TESTS
    @Test
    void testValidRegistration() {
        String result = login.registerUser("ky_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User registered successfully.", result);
    }

    @Test
    void testInvalidRegistrationUsername() {
        String result = login.registerUser("kyle", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("username is not correctly formatted ;please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    void testInvalidRegistrationPassword() {
        String result = login.registerUser("ky_1", "password", "+27838968976");
        assertEquals("Password is not correctly formatted;please ensure the password contains atleast eight characters, a capital letter, a number, and a special character", result);
    }

    @Test
    void testInvalidRegistrationPhoneNumber() {
        String result = login.registerUser("ky_1", "Ch&&sec@ke99!", "0838968976");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code", result);
    }

    // LOGIN TESTS
    @Test
    void testValidLogin() {
        login.registerUser("ky_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.loginUser("ky_1", "Ch&&sec@ke99!");
        assertTrue(result, "Login should be valid.");
    }

    @Test
    void testInvalidLoginPassword() {
        login.registerUser("ky_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.loginUser("ky_1", "wrongpassword");
        assertFalse(result, "Login should fail with the wrong password.");
    }

    @Test
    void testInvalidLoginUsername() {
        login.registerUser("ky_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.loginUser("wronguser", "Ch&&sec@ke99!");
        assertFalse(result, "Login should fail with the wrong username.");
    }

    @Test
    void testLoginAfterRegistration() {
        // Register a user
        login.registerUser("ky_1", "Ch&&sec@ke99!", "+27838968976");

        // Attempt login with correct username and password
        boolean validLogin = login.loginUser("ky_1", "Ch&&sec@ke99!");
        assertTrue(validLogin, "Login should succeed with correct credentials.");

        // Attempt login with incorrect password
        boolean invalidLogin = login.loginUser("ky_1", "wrongpassword");
        assertFalse(invalidLogin, "Login should fail with incorrect password.");
    }

    @Test
    void testLoginStatusMessageOnSuccess() {
        login.registerUser("ky_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loginSuccess = login.loginUser("ky_1", "Ch&&sec@ke99!");
        String result = login.returnLoginStatus(loginSuccess);
        assertEquals("welcomeky_1it is great to see you again.", result);
    }

    @Test
    void testLoginStatusMessageOnFailure() {
        login.registerUser("ky_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loginFailure = login.loginUser("ky_1", "wrongpassword");
        String result = login.returnLoginStatus(loginFailure);
        assertEquals("username or password incorrect, pease try again.", result);
    }
}          
            
            
            
        
        
        
    



        