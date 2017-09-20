package fontys.tests;
import fontys.time.DayInWeek;
import fontys.time.Time;
import junit.framework.Assert;
import org.junit.jupiter.api.*;

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
        Assert.assertEquals(2017, t.getYear());
        Assert.assertEquals(11, t.getMonth());
        Assert.assertEquals(2, t.getDay());
        Assert.assertEquals(3, t.getHours());
        Assert.assertEquals(45, t.getMinutes());
    }
     @Test
     void testMonth(){
         // * @param m 0≤m≤11 (Java months start at 0)
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Time(1999, 13, 2, 3, 45);
            Assert.fail("geen geldige maand");
         });

     }

    @Test
    void testDay(){
        // * @param d 1≤d≤31
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Time(1999, 11, 35, 3, 45);
            Assert.fail("geen geldige dag");
        });
    }

    @Test
    void testHour(){
        // * @param h 0≤h≤23
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Time(1999, 11, 2, -5, 45);
            Assert.fail("geen geldig uur");
        });
    }

    @Test
    void testMinute(){
        // * @param min 0≤m≤59
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Time(1999, 13, 2, 3, 100);
            Assert.fail("geen geldige minuut");
        });
    }
    @Test
    void testGetDayInWeek(){
        Time t = new Time(2017, 10, 2, 4, 45);
        Assert.assertEquals(DayInWeek.THU, t.getDayInWeek());
    }

    @Test
    void testPlus() {
        Time t = new Time(2017, 10, 2, 21, 45);
        Time timeAdd = t.plus(15);
        Time timeAdded = new Time(2017, 10, 2, 22, 00);
        Assert.assertEquals(timeAdd.getHours(), timeAdded.getHours());
        Assert.assertEquals(timeAdd.getMinutes(),timeAdded.getMinutes());
    }

    @Test
    void testCompareTo() {
        Time time1 = new Time(2017, 10, 2, 21, 45);
        Time time2 = new Time(2017, 10, 3, 12, 00);
        int result = time1.compareTo(time2);
        Assert.assertEquals(-1,result);
    }

    @Test
    void testDifference() {
        Time time1 = new Time(2017, 10, 2, 8, 00);
        Time time2 = new Time(2017, 10, 2, 7, 10);
        int result = time1.difference(time2);
        Assert.assertEquals(-50,result );

    }

}