package fontys.tests;
import fontys.agenda.Contact;
import fontys.time.DayInWeek;
import fontys.time.Time;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Célina Ngapy
 *
 */
class TimeTest {

    @Test
    void testAddTime(){
//        /**
//         * creation of a time-object with year y, month m, day d, hours h and
//         * minutes m; if the combination of y-m-d refers to a non-existing date
//         * the value of this Time-object will be not guaranteed
//         */
        Time t = new Time(2017, 11, 2, 3, 45);
        assertEquals(2017, t.getYear());
        assertEquals(11, t.getMonth());
        assertEquals(2, t.getDay());
        assertEquals(3, t.getHours());
        assertEquals(45, t.getMinutes());

    }
    @Test
    void testMonth(){
        // * @param m 0≤m≤11 (Java months start at 0)
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Time(1999, 13, 2, 3, 45);
            Assertions.fail("geen geldige maand");
        });

    }

    @Test
    void testDay(){
        // * @param d 1≤d≤31
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Time(1999, 11, 35, 3, 45);
            Assertions.fail("geen geldige dag");
        });
    }

    @Test
    void testHour(){
        // * @param h 0≤h≤23
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Time(1999, 11, 2, -5, 45);
            Assertions.fail("geen geldig uur");
        });
    }

    @Test
    void testMinute(){
        // * @param min 0≤m≤59
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Time(1999, 13, 2, 3, 100);
            Assertions.fail("geen geldige minuut");
        });
    }
    @Test
    void testGetDayInWeek(){

        //MONDAY
        Time mon = new Time(2017, 9, 30, 4, 45);
        assertEquals(DayInWeek.MON, mon.getDayInWeek());

        //TUESDAY
        Time tue = new Time(2017, 9, 31, 4, 45);
        assertEquals(DayInWeek.TUE, tue.getDayInWeek());

        //WEDNESDAY
        Time wed = new Time(2017, 10, 1, 4, 45);
        assertEquals(DayInWeek.WED, wed.getDayInWeek());

        //THURSDAY
        Time thur = new Time(2017, 10, 2, 4, 45);
        assertEquals(DayInWeek.THU, thur.getDayInWeek());

        //FRIDAY
        Time fri = new Time(2017, 10, 3, 4, 45);
        assertEquals(DayInWeek.FRI, fri.getDayInWeek());

        //SATURDAY
        Time sat = new Time(2017, 10, 4, 4, 45);
        assertEquals(DayInWeek.SAT, sat.getDayInWeek());

        //SUNDAY
        Time sun = new Time(2017, 10, 5, 4, 45);
        assertEquals(DayInWeek.SUN, sun.getDayInWeek());
    }

    @Test
    void testPlus() {
        Time t = new Time(2017, 10, 2, 21, 45);
        Time timeAdd = t.plus(15);
        Time timeAdded = new Time(2017, 10, 2, 22, 00);
        assertEquals(timeAdd.getHours(), timeAdded.getHours());
        assertEquals(timeAdd.getMinutes(),timeAdded.getMinutes());
    }

    @Test
    void testCompareTo() {
        Time time1 = new Time(2017, 10, 2, 21, 45);
        Time time2 = new Time(2017, 10, 3, 12, 00);
        int result = time1.compareTo(time2);
        assertEquals(-1,result);
    }

    @Test
    void testDifference() {
        Time time1 = new Time(2017, 10, 2, 8, 00);
        Time time2 = new Time(2017, 10, 2, 7, 10);
        int result = time1.difference(time2);
        assertEquals(-50,result );

    }

}