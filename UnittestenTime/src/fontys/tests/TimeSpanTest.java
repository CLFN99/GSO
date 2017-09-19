package fontys.tests;

import fontys.time.Time;
import fontys.time.TimeSpan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MagicLegend
 */

class TimeSpanTest {
    @Test
    void testConstructor() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TimeSpan ts = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 11, 30));
        });
        System.out.println(exception.getMessage());
    }


    @Test
    void getBeginTime() {
        Time beginTime = new Time(2017, 9, 18, 12, 0);
        Time endTime = new Time(2017, 9, 18, 12, 30);
        TimeSpan ts = new TimeSpan(beginTime, endTime);
        assertEquals(beginTime, ts.getBeginTime());
    }

    @Test
    void getEndTime() {
        Time beginTime = new Time(2017, 9, 18, 12, 0);
        Time endTime = new Time(2017, 9, 18, 12, 30);
        TimeSpan ts = new TimeSpan(beginTime, endTime);
        assertEquals(endTime, ts.getEndTime());
    }

    @Test
    void length() {
        Time beginTime = new Time(2017, 9, 18, 12, 0);
        Time endTime = new Time(2017, 9, 18, 12, 30);
        TimeSpan ts = new TimeSpan(beginTime, endTime);
        assertEquals(endTime.difference(beginTime), ts.length());
    }

    @Test
    void setBeginTime() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Time newTime = new Time(2017, 9, 18, 11, 30); //Time 30 mins before the first beginTime
            TimeSpan ts = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 11, 30));
            ts.setBeginTime(newTime);
        });
        System.out.println(exception.getMessage());
    }

    @Test
    void setEndTime() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Time newTime = new Time(2017, 9, 18, 11, 30); //Time 30 mins before the first beginTime
            TimeSpan ts = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 11, 30));
            ts.setEndTime(newTime);
        });
        System.out.println(exception.getMessage());
    }

    @Test
    void move() {
        //Untestable? The Time.plus function returns a new Time object, so it can't be compared?
        Time beginTime = new Time(2017, 9, 18, 12, 0);
        Time endTime = new Time(2017, 9, 18, 12, 30);
        TimeSpan ts = new TimeSpan(beginTime, endTime);
        ts.move(30);
        assertEquals(ts.getBeginTime(), beginTime.plus(30));
        assertEquals(ts.getEndTime(), endTime.plus(30));
    }

    @Test
    void changeLengthWith() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TimeSpan ts = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 12, 30));
            ts.changeLengthWith(-10);
        });
        System.out.println(exception.getMessage());

        //Untestable? The Time.plus function returns a new Time object, so it can't be compared?
        Time endTime = new Time(2017, 9, 18, 12, 30);
        TimeSpan ts = new TimeSpan(new Time(2017, 9, 18, 12, 0), endTime);
        ts.changeLengthWith(10);
        assertEquals(ts.getEndTime(), endTime.plus(10));
    }

    @Test
    void isPartOf() {
        TimeSpan ts = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 12, 30));
        TimeSpan ts2 = new TimeSpan(new Time(2018, 9, 18, 12, 0), new Time(2018, 9, 18, 12, 30));
        System.out.println(ts.isPartOf(ts2));
    }

    @Test
    void unionWith() {
        TimeSpan ts = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 12, 30));
        TimeSpan ts2 = new TimeSpan(new Time(2018, 9, 18, 12, 0), new Time(2018, 9, 18, 12, 30));
        ts.unionWith(ts2);
    }

    @Test
    void intersectionWith() {
    }

}