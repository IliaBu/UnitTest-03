import org.example.hw.MainHW;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MainHWTest {
    MainHW mainhw = new MainHW();

    @Test
    void evenOddNumber() {
        assertThat(mainhw.evenOddNumber(6)).isEqualTo(true);
        assertThat(mainhw.evenOddNumber(3)).isEqualTo(false);
    }

    @Test
    void numberInInterval() {
        assertThat(mainhw.numberInInterval(25)).isEqualTo(true);
        assertThat(mainhw.numberInInterval(101)).isEqualTo(false);
    }


}
