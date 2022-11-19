import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsTest {
    @Test
    public void shouldReturn5_23 () {
        Assertions.assertEquals("5,23",Utils.decimalFormat(5.23111111d));
    }

    @Test
    public void shouldReturn0 () {
        Assertions.assertEquals("0",Utils.decimalFormat(0d));
    }
}
