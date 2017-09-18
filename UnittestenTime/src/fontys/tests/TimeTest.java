package fontys.tests;
import fontys.time.Time;
import junit.framework.Assert;
import org.junit.jupiter.api.*;

/**
 * @author Célina Ngapy
 *
 */
class TimeTest {
    @BeforeEach
    void setUp() {
    }

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
         // * @param m 1≤m≤12
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Time t = new Time(1999, 13, 2, 3, 45);
            Assert.fail("geen geldige maand");
         });

     }

    @Test
    void testDay(){
        // * @param d 1≤d≤31
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Time t = new Time(1999, 11, 35, 3, 45);
            Assert.fail("geen geldige dag");
        });
    }

    @Test
    void testHour(){
        // * @param h 0≤h≤23
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Time t = new Time(1999, 11, 2, -5, 45);
            Assert.fail("geen geldig uur");
        });
    }

    @Test
    void testMinute(){
        // * @param min 0≤m≤59
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Time t = new Time(1999, 13, 2, 3, 100);
            Assert.fail("geen geldige minuut");
        });
    }
    @Test
    void testGetDayInWeek(){

    }

    @Test
    void testPlus() {

    }

    @Test
    void testCompareTo() {
    }

    @Test
    void testDifference() {
    }

}