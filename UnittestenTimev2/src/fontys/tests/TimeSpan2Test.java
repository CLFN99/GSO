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
        //**B
        TimeSpan2 t1 = new TimeSpan2(
                new Time(2017, 9, 18, 12, 0),
                new Time(2017, 9, 18, 15, 30));
        TimeSpan2 t2 = new TimeSpan2(
                new Time(2017,9,18,13,0),
                new Time (2017,9,18,16,0)
        );
        TimeSpan2 t = (TimeSpan2)t1.intersectionWith(t2);
        TimeSpan2 intersection = new TimeSpan2(
                new Time (2017,9,18,13,0),
                new Time(2017,9,18,15,30)
        );

        assertEquals(intersection.getBeginTime().getHours(), t.getBeginTime().getHours());
        assertEquals(intersection.getEndTime().getHours(), t.getEndTime().getHours());
        assertEquals(intersection.getBeginTime().getMinutes(), t.getBeginTime().getMinutes());
        assertEquals(intersection.getEndTime().getMinutes(), t.getEndTime().getMinutes());
        //**

        //**Same start; different end
        t1 = new TimeSpan2(
                new Time(2017, 9, 18, 12, 0),
                new Time(2017, 9, 18, 15, 30));
        t2 = new TimeSpan2(
                new Time(2017,9,18,12,0),
                new Time (2017,9,18,16,0)
        );
        t = (TimeSpan2)t1.intersectionWith(t2);
        intersection = new TimeSpan2(
                new Time (2017,9,18,12,0),
                new Time(2017,9,18,15,30)
        );

        assertEquals(intersection.getBeginTime().getHours(), t.getBeginTime().getHours());
        assertEquals(intersection.getEndTime().getHours(), t.getEndTime().getHours());
        assertEquals(intersection.getBeginTime().getMinutes(), t.getBeginTime().getMinutes());
        assertEquals(intersection.getEndTime().getMinutes(), t.getEndTime().getMinutes());
        //**

        //**First start earlier than the second start
        TimeSpan2 t3 = new TimeSpan2(
                new Time(2017, 9, 18, 13, 0),
                new Time(2017, 9, 18, 16, 0));
        TimeSpan2 t4 = new TimeSpan2(
                new Time(2017,9,18,12,0),
                new Time (2017,9,18,16,0)
        );
        t = (TimeSpan2)t3.intersectionWith(t4);
        intersection = new TimeSpan2(
                new Time (2017,9,18,13,0),
                new Time(2017,9,18,16,0)
        );

        assertEquals(intersection.getBeginTime().getHours(), t.getBeginTime().getHours());
        assertEquals(intersection.getEndTime().getHours(), t.getEndTime().getHours());
        assertEquals(intersection.getBeginTime().getMinutes(), t.getBeginTime().getMinutes());
        assertEquals(intersection.getEndTime().getMinutes(), t.getEndTime().getMinutes());
        //**

        //**Ending test 1
        t3 = new TimeSpan2(
                new Time(2017, 9, 18, 12, 0),
                new Time(2017, 9, 18, 15, 30));
        t4 = new TimeSpan2(
                new Time(2017,9,18,13,0),
                new Time (2017,9,18,16,0)
        );
        t = (TimeSpan2)t3.intersectionWith(t4);
        intersection = new TimeSpan2(
                new Time (2017,9,18,13,0),
                new Time(2017,9,18,15,30)
        );

        assertEquals(intersection.getBeginTime().getHours(), t.getBeginTime().getHours());
        assertEquals(intersection.getEndTime().getHours(), t.getEndTime().getHours());
        assertEquals(intersection.getBeginTime().getMinutes(), t.getBeginTime().getMinutes());
        assertEquals(intersection.getEndTime().getMinutes(), t.getEndTime().getMinutes());
        //**

        //**Ending test 2
        t3 = new TimeSpan2(
                new Time(2017, 9, 18, 12, 0),
                new Time(2017, 9, 18, 16, 30));
        t4 = new TimeSpan2(
                new Time(2017,9,18,13,0),
                new Time (2017,9,18,16,0)
        );
        t = (TimeSpan2)t3.intersectionWith(t4);
        intersection = new TimeSpan2(
                new Time (2017,9,18,13,0),
                new Time(2017,9,18,16,0)
        );

        assertEquals(intersection.getBeginTime().getHours(), t.getBeginTime().getHours());
        assertEquals(intersection.getEndTime().getHours(), t.getEndTime().getHours());
        assertEquals(intersection.getBeginTime().getMinutes(), t.getBeginTime().getMinutes());
        assertEquals(intersection.getEndTime().getMinutes(), t.getEndTime().getMinutes());
        //**
    }
}