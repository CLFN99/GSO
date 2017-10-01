package fontys.tests;

import fontys.agenda.Appointment;
import fontys.agenda.Contact;
import fontys.time.Time;
import fontys.time.TimeSpan;
import org.junit.jupiter.api.Test;

/**
 * @author MagicLegend
 */

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
        Appointment appointment = new Appointment("TestSubject",
                new TimeSpan(
                        new Time(2017, 9, 18, 12, 0),
                        new Time(2017, 9, 18, 12, 30)
                ));
        Contact contact = new Contact("Maria Jones");
        appointment.addContact(contact);
    }
}
