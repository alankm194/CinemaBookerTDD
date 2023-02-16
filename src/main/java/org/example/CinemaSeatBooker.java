package org.example;

public class CinemaSeatBooker {


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



}