import com.mycompany.part1.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private static final String RECIPIENT_1 = "+27718693002";
    private static final String MESSAGE_1 = "Hi Mike, can you join us for dinner tonight";
    private static final String RECIPIENT_2 = "08575975889";
    private static final String MESSAGE_2 = "Hi Keegan, did you receive the payment?";

    @BeforeEach
    void setUp() {
        Message.reset();
    }

    @Test
    void testMessageLengthSuccess() {
        Message m = new Message(0, RECIPIENT_1, MESSAGE_1);
        assertEquals("Message ready to send.", m.getMessageLengthMessage());
    }

    @Test
    void testMessageLengthFailure() {
        Message m = new Message(0, RECIPIENT_1, "a".repeat(260));
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", m.getMessageLengthMessage());
    }

    @Test
    void testRecipientSuccess() {
        Message m = new Message(0, RECIPIENT_1, MESSAGE_1);
        assertEquals("Cell phone number successfully captured.", m.getRecipientCellMessage());
    }

    @Test
    void testRecipientFailure() {
        Message m = new Message(1, RECIPIENT_2, MESSAGE_2);
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                m.getRecipientCellMessage());
    }

    @Test
    void testMessageHash() {
        Message m = new Message("0012345678", 0, RECIPIENT_1, MESSAGE_1);
        assertEquals("00:0:HITONIGHT", m.getMessageHash());
    }

    @Test
    void testMessageHashLoop() {
        String[] ids = {"0012345678", "1198765432"};
        String[] texts = {MESSAGE_1, MESSAGE_2};
        String[] expected = {"00:0:HITONIGHT", "11:1:HIPAYMENT"};
        for (int i = 0; i < texts.length; i++) {
            Message m = new Message(ids[i], i, RECIPIENT_1, texts[i]);
            assertEquals(expected[i], m.getMessageHash());
        }
    }

    @Test
    void testMessageIDCreated() {
        Message m = new Message(0, RECIPIENT_1, MESSAGE_1);
        assertTrue(m.checkMessageID());
        assertEquals("Message ID generated: " + m.getMessageID(), m.getMessageIDCreatedMessage());
    }

    @Test
    void testSendMessage() {
        Message m = new Message(0, RECIPIENT_1, MESSAGE_1);
        assertEquals("Message successfully sent.", m.sentMessage(1));
    }

    @Test
    void testDisregardMessage() {
        Message m = new Message(1, RECIPIENT_2, MESSAGE_2);
        assertEquals("Press 0 to delete the message.", m.sentMessage(2));
    }

    @Test
    void testStoreMessage() {
        Message m = new Message(0, RECIPIENT_1, MESSAGE_1);
        assertEquals("Message successfully stored.", m.sentMessage(3));
    }

    @Test
    void testTotalMessages() {
        new Message(0, RECIPIENT_1, MESSAGE_1).sentMessage(1);
        new Message(1, RECIPIENT_2, MESSAGE_2).sentMessage(2);
        assertEquals(1, Message.returnTotalMessages());
    }
}
