package fontys.tests;

import fontys.time.ITimeSpan;
import fontys.time.Time;
import fontys.time.TimeSpan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(beginTime.difference(endTime), ts.length());
    }

    @Test
    void setBeginTime() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Time newTime = new Time(2017, 9, 18, 11, 30); //Time 30 mins before the first beginTime
            TimeSpan ts = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 11, 30));
            ts.setBeginTime(newTime);
        });
        System.out.println(exception.getMessage());

        Time newTime = new Time(2017, 9, 18, 12, 30); //right begintime
        TimeSpan ts = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 14, 30));
        ts.setBeginTime(newTime);
        assertEquals(ts.getBeginTime().getHours(), newTime.getHours());
        assertEquals(ts.getBeginTime().getMinutes(), newTime.getMinutes());
    }

    @Test
    void setEndTime() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Time newTime = new Time(2017, 9, 18, 11, 30); //Time 30 mins before the first beginTime
            TimeSpan ts = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 11, 30));
            ts.setEndTime(newTime);
        });


        Time newTime = new Time(2017, 9, 18, 14, 30); //right begintime
        TimeSpan ts = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 14, 30));
        ts.setEndTime(newTime);
        assertEquals(ts.getEndTime().getHours(), newTime.getHours());
        assertEquals(ts.getEndTime().getMinutes(), newTime.getMinutes());
    }

    @Test
    void move() {
        //Untestable? The Time.plus function returns a new Time object, so it can't be compared?
        Time beginTime = new Time(2017, 9, 18, 12, 0);
        Time endTime = new Time(2017, 9, 18, 12, 30);
        TimeSpan ts = new TimeSpan(beginTime, endTime);
        ts.move(30);
        assertEquals(ts.getBeginTime().getHours(), beginTime.plus(30).getHours());
        assertEquals(ts.getBeginTime().getMinutes(), beginTime.plus(30).getMinutes());
        assertEquals(ts.getEndTime().getHours(), endTime.plus(30).getHours());
        assertEquals(ts.getEndTime().getMinutes(), endTime.plus(30).getMinutes());
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
        assertEquals(ts.getEndTime().getHours(), endTime.plus(10).getHours());
        assertEquals(ts.getEndTime().getMinutes(), endTime.plus(10).getMinutes());
    }

    @Test
    void isPartOf() {
        TimeSpan ts = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 12, 30));
        TimeSpan ts2 = new TimeSpan(new Time(2018, 9, 18, 12, 0), new Time(2018, 9, 18, 12, 30));
        boolean partOf = ts2.isPartOf(ts);
        assertEquals(false, partOf);

        TimeSpan ts3 = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 12, 30));
        TimeSpan ts4 = new TimeSpan(new Time(2017, 9, 18, 12, 10), new Time(2017, 9, 18, 12, 20));
        boolean partOfTrue = ts4.isPartOf(ts3);
        assertEquals(true, partOfTrue);
    }

    @Test
    void unionWith() {
        TimeSpan P = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 16, 0));

        //testing union F
        TimeSpan F = new TimeSpan(new Time(2018, 9, 18, 12, 0), new Time(2018, 9, 18, 12, 30));
        TimeSpan noUnion = (TimeSpan)P.unionWith(F);
        assertEquals(null, noUnion);

        //testing  union A
        TimeSpan A = new TimeSpan(new Time(2017, 9, 18, 7, 0), new Time(2017, 9, 18, 12, 0));
        TimeSpan resultA = (TimeSpan)P.unionWith(A);
        TimeSpan expectedA = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 12, 0));
        assertEquals(expectedA.getBeginTime().getHours(), resultA.getBeginTime().getHours());
        assertEquals(expectedA.getEndTime().getHours(), resultA.getEndTime().getHours());
        assertEquals(expectedA.getBeginTime().getMinutes(), resultA.getBeginTime().getMinutes());
        assertEquals(expectedA.getEndTime().getMinutes(), resultA.getEndTime().getMinutes());

        //testing union B
        TimeSpan B = new TimeSpan(new Time(2017, 9, 18, 8, 0), new Time(2017, 9, 18, 13, 0));
        TimeSpan resultB = (TimeSpan)P.unionWith(B);
        TimeSpan expectedB = new TimeSpan(new Time(2017, 9, 18, 12, 0), new Time(2017, 9, 18, 13, 0));
        assertEquals(expectedB.getBeginTime().getHours(), resultB.getBeginTime().getHours());
        assertEquals(expectedB.getEndTime().getHours(), resultB.getEndTime().getHours());
        assertEquals(expectedB.getBeginTime().getMinutes(), resultB.getBeginTime().getMinutes());
        assertEquals(expectedB.getEndTime().getMinutes(), resultB.getEndTime().getMinutes());

        //testing union C
        TimeSpan C = new TimeSpan(new Time(2017, 9, 18, 13, 0), new Time(2017, 9, 18, 14, 0));
        TimeSpan resultC = (TimeSpan)P.unionWith(C);
        TimeSpan expectedC = new TimeSpan(new Time(2017, 9, 18, 13, 0), new Time(2017, 9, 18, 14, 0));
        assertEquals(expectedC.getBeginTime().getHours(), resultC.getBeginTime().getHours());
        assertEquals(expectedC.getEndTime().getHours(), resultC.getEndTime().getHours());
        assertEquals(expectedC.getBeginTime().getMinutes(), resultC.getBeginTime().getMinutes());
        assertEquals(expectedC.getEndTime().getMinutes(), resultC.getEndTime().getMinutes());

        //testing union D
        TimeSpan D = new TimeSpan(new Time(2017, 9, 18, 11, 0), new Time(2017, 9, 18, 17, 30));
        TimeSpan resultD = (TimeSpan)P.unionWith(D);
        assertEquals(P.getBeginTime().getHours(), resultD.getBeginTime().getHours());
        assertEquals(P.getEndTime().getHours(), resultD.getEndTime().getHours());
        assertEquals(P.getBeginTime().getMinutes(), resultD.getBeginTime().getMinutes());
        assertEquals(P.getEndTime().getMinutes(), resultD.getEndTime().getMinutes());

        //testing union E
        TimeSpan E = new TimeSpan(new Time(2017, 9, 18, 16, 0), new Time(2017, 9, 18, 22, 0));
        TimeSpan resultE = (TimeSpan)P.unionWith(E);
        TimeSpan expectedE = new TimeSpan(new Time(2017, 9, 18, 16, 0), new Time(2017, 9, 18, 16, 0));
        assertEquals(expectedE.getBeginTime().getHours(), resultE.getBeginTime().getHours());
        assertEquals(expectedE.getEndTime().getHours(), resultE.getEndTime().getHours());
        assertEquals(expectedE.getBeginTime().getMinutes(), resultE.getBeginTime().getMinutes());
        assertEquals(expectedE.getEndTime().getMinutes(), resultE.getEndTime().getMinutes());
    }

    @Test
    void intersectionWith() {
        TimeSpan t1 = new TimeSpan(
                new Time(2017, 9, 18, 12, 0),
                new Time(2017, 9, 18, 15, 30));
        TimeSpan t2 = new TimeSpan(
                new Time(2017,9,18,13,0),
                new Time (2017,9,18,16,0)
        );
        TimeSpan t = (TimeSpan)t1.intersectionWith(t2);
        TimeSpan intersection = new TimeSpan(
                new Time (2017,9,18,13,0),
                new Time(2017,9,18,15,30)
        );

        assertEquals(intersection.getBeginTime().getHours(), t.getBeginTime().getHours());
        assertEquals(intersection.getEndTime().getHours(), t.getEndTime().getHours());
        assertEquals(intersection.getBeginTime().getMinutes(), t.getBeginTime().getMinutes());
        assertEquals(intersection.getEndTime().getMinutes(), t.getEndTime().getMinutes());

    }
}
