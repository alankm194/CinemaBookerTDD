import org.example.CinemaSeatBooker;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collection;


import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(expected, seatBooker.getSeat(1).get(0));
    }

    @ParameterizedTest
    @CsvSource({"1,'A1'", "2,'A1 A2'", "3,'A1 A2 A3'"})
    public void When1CustomerOrdersMultipleTicket(int orderAmount, String expectedTickets) {
        var seatBooker = new CinemaSeatBooker();
        var ticketList = seatBooker.getSeat(orderAmount);
        assertEquals(Arrays.asList(expectedTickets.split(" ")), ticketList);
    }

    @Test
    public void testTotalTicketsIssuesIsSameAsFilledCinemaSeats() {
        var seatBooker = new CinemaSeatBooker();
        var ticketCustomerMap = seatBooker.bookAllSeats();
        var totalTickets = ticketCustomerMap.values()
                .stream()
                .mapToInt(Collection::size)
                .sum();

        assertEquals(seatBooker.getFilledCinemaSeats(), totalTickets);
    }



}
