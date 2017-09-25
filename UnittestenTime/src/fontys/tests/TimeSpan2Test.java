package fontys.tests;

import fontys.time.Time;
import fontys.time.TimeSpan2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeSpan2Test {
    @Test
    void testConstructor() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TimeSpan2 ts = new TimeSpan2(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 11, 30));
        });
        System.out.println(exception.getMessage());
    }


    @Test
    void getBeginTime() {
        Time beginTime = new Time(2017, 9, 18, 12, 0);
        Time endTime = new Time(2017, 9, 18, 12, 30);
        TimeSpan2 ts = new TimeSpan2(beginTime, endTime);
        assertEquals(beginTime, ts.getBeginTime());
    }

    @Test
    void getEndTime() {
        Time beginTime = new Time(2017, 9, 18, 12, 0);
        Time endTime = new Time(2017, 9, 18, 12, 30);
        TimeSpan2 ts = new TimeSpan2(beginTime, endTime);
        assertEquals(endTime.getMinutes(), ts.getEndTime().getMinutes());
        assertEquals(endTime.getHours(), ts.getEndTime().getHours());
    }

    @Test
    void length() {
        Time beginTime = new Time(2017, 9, 18, 12, 0);
        Time endTime = new Time(2017, 9, 18, 12, 30);
        TimeSpan2 ts = new TimeSpan2(beginTime, endTime);
        assertEquals(beginTime.difference(endTime), ts.length());
    }

    @Test
    void setBeginTime() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Time newTime = new Time(2017, 9, 18, 11, 30); //Time 30 mins before the first beginTime
            TimeSpan2 ts = new TimeSpan2(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 11, 30));
            ts.setBeginTime(newTime);
        });
        System.out.println(exception.getMessage());
    }

    @Test
    void setEndTime() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Time newTime = new Time(2017, 9, 18, 11, 30); //Time 30 mins before the first beginTime
            TimeSpan2 ts = new TimeSpan2(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 11, 30));
            ts.setEndTime(newTime);
        });
        System.out.println(exception.getMessage());
    }

    @Test
    void move() {
        //Untestable? The Time.plus function returns a new Time object, so it can't be compared?
        Time beginTime = new Time(2017, 9, 18, 12, 0);
        Time endTime = new Time(2017, 9, 18, 12, 30);
        TimeSpan2 ts = new TimeSpan2(beginTime, endTime);
        ts.move(30);
        assertEquals(ts.getBeginTime().getHours(), beginTime.plus(30).getHours());
        assertEquals(ts.getBeginTime().getMinutes(), beginTime.plus(30).getMinutes());
        assertEquals(ts.getEndTime().getHours(), endTime.plus(30).getHours());
        assertEquals(ts.getEndTime().getMinutes(), endTime.plus(30).getMinutes());
    }

    @Test
    void changeLengthWith() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TimeSpan2 ts = new TimeSpan2(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 12, 30));
            ts.changeLengthWith(-10);
        });
        System.out.println(exception.getMessage());

        //Untestable? The Time.plus function returns a new Time object, so it can't be compared?
        Time endTime = new Time(2017, 9, 18, 12, 30);
        TimeSpan2 ts = new TimeSpan2(new Time(2017, 9, 18, 12, 0), endTime);
        ts.changeLengthWith(10);
        assertEquals(ts.getEndTime().getHours(), endTime.plus(10).getHours());
        assertEquals(ts.getEndTime().getMinutes(), endTime.plus(10).getMinutes());
    }

    @Test
    void isPartOf() {
        TimeSpan2 ts = new TimeSpan2(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 12, 30));
        TimeSpan2 ts2 = new TimeSpan2(new Time(2018, 9, 18, 12, 0), new Time(2018, 9, 18, 12, 30));
        System.out.println(ts.isPartOf(ts2));
    }

    @Test
    void unionWith() {
        TimeSpan2 ts = new TimeSpan2(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 12, 30));
        TimeSpan2 ts2 = new TimeSpan2(new Time(2018, 9, 18, 12, 0), new Time(2018, 9, 18, 12, 30));
        ts.unionWith(ts2);
    }
    @Test
    void intersectionWith() {
    }
}