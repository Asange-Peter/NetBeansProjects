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

        Message[] messages = {message1,message2};

        for (Message msg : messages) {

            String hash = msg.createMessageHash();

            assertTrue(hash.equals(hash.toUpperCase()));
        }
    }
}

@Test
public void testSentMessagesArray_correctlyPopulated() {
// thids code sent messages array
    Message.getSentMessages().clear();

    Message.getSentMessages().add("Did you get the cake?");
    Message.getSentMessages().add("It is dinner time!");

    assertTrue(
            Message.getSentMessages().contains("Did you get the cake?")
    );

    assertTrue(
            Message.getSentMessages().contains("It is dinner time!")
    );
}
// this code longest messages
@Test
public void testDisplayLongestMessage_returnsCorrectMessage() {

    Message.getStoredMessages().clear();

    Message.getStoredMessages().add("Did you get the cake?");
    Message.getStoredMessages().add(
            "Where are you? You are late! I have asked you to be on time."
    );
    Message.getStoredMessages().add("Ok, I am leaving without you.");

    Message msg = new Message(
            1,
            "+27834557896",
            "test"
    );

    assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            msg.displayLongestMessage()
    );
}
// this code search by message ID
@Test
public void testSearchByMessageID_returnsCorrectMessage() {

    Message.getMessageIDs().clear();
    Message.getSentMessages().clear();

    Message.getMessageIDs().add("0838884567");
    Message.getSentMessages().add("It is dinner time!");

    Message msg = new Message(
            1,
            "+27834557896",
            "test"
    );

    assertEquals(
            "It is dinner time!",
            msg.searchByMessageID("0838884567")
    );
}
// This code search by recipient
@Test
public void testSearchByRecipient_returnsAllMatchingMessages() {

    Message.getRecipients().clear();
    Message.getSentMessages().clear();

    Message.getRecipients().add("+27838884567");
    Message.getSentMessages().add(
            "Where are you? You are late! I have asked you to be on time."
    );

    Message.getRecipients().add("+27838884567");
    Message.getSentMessages().add(
            "Ok, I am leaving without you."
    );

    Message msg = new Message(
            1,
            "+27834557896",
            "test"
    );

    String result = msg.searchByRecipient("+27838884567");

    assertTrue(
            result.contains(
                    "Where are you? You are late! I have asked you to be on time."
            )
    );

    assertTrue(
            result.contains(
                    "Ok, I am leaving without you."
            )
    );
}
// this code delete by hash
@Test
public void testDeleteByHash_removesCorrectMessage() {

    Message.getMessageHashes().clear();
    Message.getSentMessages().clear();

    Message.getMessageHashes().add("AB:1:WHEREYOU");
    Message.getSentMessages().add(
            "Where are you? You are late! I have asked you to be on time."
    );

    Message msg = new Message(
            1,
            "+27834557896",
            "test"
    );

    String result =
            msg.deleteByHash("AB:1:WHEREYOU");

    assertEquals(
            "Message: Where are you? You are late! I have asked you to be on time. successfully deleted.",
            result
    );
}
// this code report
@Test
public void testDisplayReport_containsRequiredFields() {

    Message.getSentMessages().clear();
    Message.getRecipients().clear();
    Message.getMessageHashes().clear();

    Message.getSentMessages().add("Did you get the cake?");
    Message.getRecipients().add("+27834557896");
    Message.getMessageHashes().add("AA:1:DIDCAKE");

    String report = Message.printMessages();

    assertTrue(report.contains("AA:1:DIDCAKE"));
    assertTrue(report.contains("+27834557896"));
    assertTrue(report.contains("Did you get the cake?"));
   
}
// this code

