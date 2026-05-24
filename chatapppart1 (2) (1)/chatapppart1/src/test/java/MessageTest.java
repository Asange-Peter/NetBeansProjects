import com.mycompany.chatapppart1.Message;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    private Message message1;
    private Message message2;

    // =========================
    // SETUP
    // =========================

    @Before
    public void setUp() {

        // POE Test Data Message 1
        message1 = new Message(
                1,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        // POE Test Data Message 2
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

        // ARRANGE
        String text = "Hello";

        // ACT
        String result = message1.checkMessageLength(text);

        // ASSERT
        assertEquals(
                "Message ready to send.",
                result
        );
    }

    @Test
    public void testCheckMessageLength_over250chars_returnsFailureWithCount() {

        // ARRANGE
        String longText = "a".repeat(260);

        // ACT
        String result = message1.checkMessageLength(longText);

        // ASSERT
        assertEquals(
                "Message exceeds 250 characters by 10, please reduce size.",
                result
        );
    }

    @Test
    public void testCheckMessageLength_exactlyAtLimit_returnsSuccess() {

        // ARRANGE
        String text = "a".repeat(250);

        // ACT
        String result = message1.checkMessageLength(text);

        // ASSERT
        assertEquals(
                "Message ready to send.",
                result
        );
    }

    @Test
    public void testCheckMessageLength_oneOver_returnsFailureWithCountOf1() {

        // ARRANGE
        String text = "a".repeat(251);

        // ACT
        String result = message1.checkMessageLength(text);

        // ASSERT
        assertEquals(
                "Message exceeds 250 characters by 1, please reduce size.",
                result
        );
    }

    // =========================
    // RECIPIENT CELL TESTS
    // =========================

    @Test
    public void testCheckRecipientCell_validNumber_returnsSuccess() {

        String result = message1.checkRecipientCell(
                "+27718693002"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                result
        );
    }

    @Test
    public void testCheckRecipientCell_invalidNumber_returnsFailure() {

        String result = message2.checkRecipientCell(
                "08575975889"
        );

        assertEquals(
                "Cell phone number incorrectly formatted or does not contain international code.",
                result
        );
    }

    // =========================
    // MESSAGE HASH TESTS
    // =========================

    @Test
    public void testCreateMessageHash_correctFormat_endsWithExpectedWords() {

        String hash = message1.createMessageHash();

        assertTrue(
                hash.endsWith(":1:HITONIGHT")
        );
    }

    @Test
    public void testCreateMessageHash_isUppercase() {

        String hash = message1.createMessageHash();

        assertEquals(
                hash.toUpperCase(),
                hash
        );
    }

    @Test
    public void testCreateMessageHash_multipleMessages_loopTest() {

        Message[] messages = {message1, message2};

        for (Message msg : messages) {

            String hash = msg.createMessageHash();

            assertTrue(hash.equals(hash.toUpperCase()));
        }
    }
}