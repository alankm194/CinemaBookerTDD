import org.example.CinemaSeatBooker;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CinemaSeatBookerTest {


    @RepeatedTest(20)
    public void testCustomerGeneratesTicketOrder() {
        CinemaSeatBooker seatBooker = new CinemaSeatBooker();
        assertTrue(seatBooker.customerGeneratesOrder() > 0 && seatBooker.customerGeneratesOrder() <= 3);
    }

}
