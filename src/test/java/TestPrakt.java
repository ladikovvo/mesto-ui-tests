import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestPrakt {
    @Test
    public void pointTest() {
        Point point  = new Point(15, 20);
        Point newpoint = new Point(30, 4);
        point.update(newpoint);
        assertAll(
                () -> assertEquals(30, newpoint.getX()),
                () -> assertEquals(4, newpoint.getY())

        );
    }
}
