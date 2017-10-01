package fontys.tests;

import fontys.agenda.Appointment;
import fontys.time.Time;
import fontys.time.TimeSpan;
import org.junit.jupiter.api.Test;

public class AppointmentTest {
    @Test
    public void TestConstructor() {
        Appointment appointment = new Appointment("TestSubject",
                new TimeSpan(
                        new Time(2017, 9, 18, 12, 0),
                        new Time(2017, 9, 18, 12, 30)
                ));
    }

    @Test
    public void addContact() {

    }
}
