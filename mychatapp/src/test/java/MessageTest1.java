/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.mychatapp.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Asange
 */
public class MessageTest1 {
    
    public MessageTest1() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}


/**
 *
 * @author Asange
 */


    private Message message1;
    private Message message2;

    // =========================
    // SETUP
    // =========================
    @BeforeEach
    public void setUp() {

        message1 = new Message(
                1,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        message2 = new Message(
                2,
                "08575975889",
                "Hi Keegan, did you receive the payment?"
        );
    }

    // =========================
    // MESSAGE LENGTH TESTS
    // =========================

    @Test
    public void testCheckMessageLength_validMessage_returnsSuccess() {
        assertEquals("Message ready to send.",
                message1.checkMessageLength("Hello"));
    }

    @Test
    public void testCheckMessageLength_over250chars_returnsFailureWithCount() {

        String longText = new String(new char[260]).replace('\0', 'a');

        String result = message1.checkMessageLength(longText);

        assertTrue(result.contains("Message exceeds 250 characters"));
    }

    @Test
    public void testCheckMessageLength_exactlyAtLimit_returnsSuccess() {

        String text = new String(new char[250]).replace('\0', 'a');

        assertEquals("Message ready to send.",
                message1.checkMessageLength(text));
    }

    @Test
    public void testCheckMessageLength_oneOver_returnsFailureWithCountOf1() {

        String text = new String(new char[251]).replace('\0', 'a');

        String result = message1.checkMessageLength(text);

        assertTrue(result.contains("1"));
    }

    // =========================
    // RECIPIENT TESTS
    // =========================

    @Test
    public void testCheckRecipientCell_validNumber_returnsSuccess() {

        assertEquals(
                "Cell phone number successfully captured.",
                message1.checkRecipientCell("+27718693002")
        );
    }

    @Test
    public void testCheckRecipientCell_invalidNumber_returnsFailure() {

        assertEquals(
                "Cell phone number incorrectly formatted or does not contain international code.",
                message2.checkRecipientCell("08575975889")
        );
    }

    // =========================
    // HASH TESTS
    // =========================

    @Test
    public void testCreateMessageHash_isUppercase() {

        String hash = message1.createMessageHash();

        assertEquals(hash.toUpperCase(), hash);
    }

    @Test
    public void testCreateMessageHash_loopTest() {

        Message[] messages = {message1, message2};

        for (Message msg : messages) {
            String hash = msg.createMessageHash();
            assertEquals(hash.toUpperCase(), hash);
        }
    }

