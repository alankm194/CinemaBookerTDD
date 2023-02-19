import org.example.CinemaSeatBooker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collection;


import static org.junit.jupiter.api.Assertions.*;

public class CinemaSeatBookerTest {

    private CinemaSeatBooker seatBooker;
    private static final int MIN_POSSIBLE_CUSTOMER = 5;
    private static final int MAX_POSSIBLE_CUSTOMER = 15;

    private static final int MAX_POSSIBLE_ORDER = 3;
    @BeforeEach
    public void init() {
        seatBooker = new CinemaSeatBooker();
    }


    @RepeatedTest(20)
    public void testCustomerGeneratesTicketOrder() {
        assertTrue(seatBooker.customerGeneratesOrder() > 0 && seatBooker.customerGeneratesOrder() <= 3);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/orderToSeatId.csv")
    public void whenCustomerOrders1SeatAndisXOrder_returnCorrectAnswer(int order, String  expected ) {
        for (int i=1; i < order; i++) {
            seatBooker.getSeat(1);
        }
        assertEquals(expected, seatBooker.getSeat(1).get(0));
    }

    @ParameterizedTest
    @CsvSource({"1,'A1'", "2,'A1 A2'", "3,'A1 A2 A3'"})
    public void When1CustomerOrdersMultipleTicket(int orderAmount, String expectedTickets) {
        var ticketList = seatBooker.getSeat(orderAmount);
        assertEquals(Arrays.asList(expectedTickets.split(" ")), ticketList);
    }

    @Test
    public void testTotalTicketsIssuesIsSameAsFilledCinemaSeats() {
        var ticketCustomerMap = seatBooker.bookAllSeats();
        var totalTickets = ticketCustomerMap.values()
                .stream()
                .mapToInt(Collection::size)
                .sum();

        assertEquals(seatBooker.getFilledCinemaSeats(), totalTickets);
    }

    @Test
    public void testAllCustomersHaveBetween1And3Seats() {
        var ticketCustomerMap = seatBooker.bookAllSeats();
        var isTicketOrderInvalid = ticketCustomerMap.values()
                .stream()
                .anyMatch(e -> e.size() == 0 || e.size() > MAX_POSSIBLE_ORDER );

        assertFalse(isTicketOrderInvalid);
    }

    @RepeatedTest(20)
    public void testSeatBookerWillAlways5CustomersOrMoreAnd15orLess() {
        var ticketCustomerMap = seatBooker.bookAllSeats();
        assertTrue(ticketCustomerMap.size() >= MIN_POSSIBLE_CUSTOMER
                && ticketCustomerMap.size() <= MAX_POSSIBLE_CUSTOMER);
    }
}
