

import com.mycompany.part1.Login;
import com.mycompany.part1.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login system;

    @BeforeEach
    void setUp() {
        system = new Login();
    }

    // ========== USERNAME TESTS ==========

    @Test
    void testUsernameCorrectlyFormatted() {
        assertTrue(system.checkUserName("sip_3"));
        assertEquals("Username successfully captured.",
                system.getUsernameCaptureMessage("sip_3"));
    }

    @Test
    void testUsernameIncorrectlyFormatted() {
        assertFalse(system.checkUserName("sipho!!!!"));
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                system.getUsernameCaptureMessage("sipho!!!!"));
    }

    @Test
    void testUsernameCorrectlyFormatted_BriefData() {
        assertTrue(system.checkUserName("kyl_1"));
        assertEquals("Username successfully captured.",
                system.getUsernameCaptureMessage("kyl_1"));
    }

    @Test
    void testUsernameIncorrectlyFormatted_BriefData() {
        assertFalse(system.checkUserName("kyle!!!!!!"));
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                system.getUsernameCaptureMessage("kyle!!!!!!"));
    }

    // ========== PASSWORD TESTS ==========

    @Test
    void testPasswordMeetsComplexity() {
        assertTrue(system.checkPasswordComplexity("S@f3Code!"));
        assertEquals("Password successfully captured.",
                system.getPasswordCaptureMessage("S@f3Code!"));
    }

    @Test
    void testPasswordDoesNotMeetComplexity() {
        assertFalse(system.checkPasswordComplexity("weakpass"));
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                system.getPasswordCaptureMessage("weakpass"));
    }

    @Test
    void testPasswordMeetsComplexity_BriefData() {
        assertTrue(system.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertEquals("Password successfully captured.",
                system.getPasswordCaptureMessage("Ch&&sec@ke99!"));
    }

    @Test
    void testPasswordDoesNotMeetComplexity_BriefData() {
        assertFalse(system.checkPasswordComplexity("password"));
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                system.getPasswordCaptureMessage("password"));
    }

    // ========== CELL PHONE TESTS ==========

    @Test
    void testCellPhoneCorrectlyFormatted() {
        assertTrue(system.checkCellPhoneNumber("+27834567890"));
        assertEquals("Cell number successfully captured.",
                system.getCellPhoneCaptureMessage("+27834567890"));
    }

    @Test
    void testCellPhoneIncorrectlyFormatted() {
        assertFalse(system.checkCellPhoneNumber("0834567890"));
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.",
                system.getCellPhoneCaptureMessage("0834567890"));
    }

    @Test
    void testCellPhoneCorrectlyFormatted_BriefData() {
        assertTrue(system.checkCellPhoneNumber("+27838968976"));
        assertEquals("Cell number successfully captured.",
                system.getCellPhoneCaptureMessage("+27838968976"));
    }

    @Test
    void testCellPhoneIncorrectlyFormatted_BriefData() {
        assertFalse(system.checkCellPhoneNumber("08966553"));
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.",
                system.getCellPhoneCaptureMessage("08966553"));
    }

    // ========== LOGIN TESTS ==========

    @Test
    void testLoginSuccessful() {
        system.registerUser("sip_3", "S@f3Code!", "+27834567890", "Sipho", "Dlamini");
        assertTrue(system.loginUser("sip_3", "S@f3Code!"));
        assertEquals("Welcome Sipho Dlamini, it is great to see you again.",
                system.returnLoginStatus("sip_3", "S@f3Code!"));
    }

    @Test
    void testLoginFailed() {
        system.registerUser("sip_3", "S@f3Code!", "+27834567890", "Sipho", "Dlamini");
        assertFalse(system.loginUser("wrong", "wrong"));
        assertEquals("Username or password incorrect, please try again.",
                system.returnLoginStatus("wrong", "wrong"));
    }
}