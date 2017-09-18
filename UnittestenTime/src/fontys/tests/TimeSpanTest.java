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
        Assertions.assertThrows(IllegalArgumentException.class, () -> {

        });
    }


    @Test
    void getBeginTime() {
    }

    @Test
    void getEndTime() {
    }

    @Test
    void length() {
    }

    @Test
    void setBeginTime() {
    }

    @Test
    void setEndTime() {
    }

    @Test
    void move() {
    }

    @Test
    void changeLengthWith() {
    }

    @Test
    void isPartOf() {
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