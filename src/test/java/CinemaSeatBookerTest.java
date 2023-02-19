import org.example.CinemaSeatBooker;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CinemaSeatBookerTest {


    @RepeatedTest(20)
    public void testCustomerGeneratesTicketOrder() {
        CinemaSeatBooker seatBooker = new CinemaSeatBooker();
        assertTrue(seatBooker.customerGeneratesOrder() > 0 && seatBooker.customerGeneratesOrder() <= 3);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/orderToSeatId.csv")
    public void whenCustomerOrders1SeatAndisXOrder_returnCorrectAnswer(int order, String  expected ) {
        CinemaSeatBooker seatBooker = new CinemaSeatBooker();
        for (int i=1; i < order; i++) {
            seatBooker.getSeat(1);
        }
        assertEquals(expected, seatBooker.getSeat(1));
    }

}
