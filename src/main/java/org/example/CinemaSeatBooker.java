package org.example;


import java.util.*;
/*
 * Assumptions:
 *  - when the last customer orders seats over the number of remaining seats left,
 *    the cinema is considered full even though some seats are still are free
 *
 * Algorithm
 * 1. initialize map of number to letter map
 * 2. while seatCount < 15
 * 3.   customer orders a ransom number of seats between 1 and 3
 * 4.   while customerTickerOrder > 0
 * 5.     find the smallest key in map that is greater than seatcount
 * 6.     get key value pair
 * 7.     find the value of seat number by seatCount % 5
 * 8.     concatenate letter value to seatNumber
 * 9.     add cinemaSeat to temp List cinemaSeats
 * 10.   add customer number and cinemaSeats to map
 */

public class CinemaSeatBooker {


    private static final int MAX_ORDER = 3;

    private static final Random rand = new Random();

    private static final NavigableMap<Integer, String> ROW_NUMBER_SEAT_MAP;

    private static final int MAX_CINEMA_SEATS = 15;

    private int filledCinemaSeats = 0;

    static {
        var map = new TreeMap<Integer, String>();
        map.put(5, "A");
        map.put(10, "B");
        map.put(15, "C");
        ROW_NUMBER_SEAT_MAP = Collections.unmodifiableNavigableMap(map);
    }

    public List<String> getSeat(int order) {
        List<String> ticketList = new ArrayList<>();
        for (int i = 0; i < order; i++) {
            var seatNum = (filledCinemaSeats % 5) + 1;
            var seatId = ROW_NUMBER_SEAT_MAP.higherEntry(filledCinemaSeats).getValue() + seatNum;
            filledCinemaSeats++;
            ticketList.add(seatId);
        }
        return ticketList;
    }

    public Map<Integer, List<String>> bookAllSeats(){
        Map<Integer, List<String>> customerToSeatMap = new HashMap<>();
        int customerNumber = 1;
        while (filledCinemaSeats < MAX_CINEMA_SEATS) {
            var order = customerGeneratesOrder();
            if ((order + filledCinemaSeats) > MAX_CINEMA_SEATS) {
                return customerToSeatMap;
            }
            customerToSeatMap.put(customerNumber++, getSeat(order));
        }
        return customerToSeatMap;
    }

    public int customerGeneratesOrder() {
        return rand.nextInt(MAX_ORDER) + 1;
    }

    public int getFilledCinemaSeats() {
        return filledCinemaSeats;
    }
}