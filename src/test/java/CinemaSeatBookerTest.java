import org.example.CinemaSeatBooker;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CinemaSeatBookerTest {


    @RepeatedTest(20)
    public void testCustomerGeneratesTicketOrder() {
        CinemaSeatBooker seatBooker = new CinemaSeatBooker();
        assertTrue(seatBooker.customerGeneratesOrder() > 0 && seatBooker.customerGeneratesOrder() <= 3);
    }

    @Test
    public void whenCustomerOrders1SeatAndTicketsRemainingIs15_returnA1() {
        CinemaSeatBooker seatBooker = new CinemaSeatBooker();
        assertEquals("A1", seatBooker.getSeat(1));
    }
}
