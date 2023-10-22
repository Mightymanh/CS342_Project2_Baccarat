import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
public class CardTest {
    @Test
    void CardConstructorTest() {
        Card newCard = new Card("Heart", 2, "Jack");

        assertEquals("Heart", newCard.suite);
        assertEquals(2, newCard.value);
        assertEquals("Jack", newCard.name);
    }
}
