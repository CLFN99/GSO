package fontys.tests;

import fontys.agenda.Appointment;
import fontys.agenda.Contact;
import fontys.time.Time;
import fontys.time.TimeSpan;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(true, appointment.addContact(contact), "Message");
    }

    @Test
    public void removeContact() {
        Appointment appointment = new Appointment("TestSubject",
                new TimeSpan(
                        new Time(2017, 9, 18, 12, 0),
                        new Time(2017, 9, 18, 12, 30)
                ));
        Contact contact = new Contact("Maria Jones");
        appointment.addContact(contact);
        appointment.removeContact(contact);
    }

    @Test
    public void getSubject() {
        Appointment appointment = new Appointment("TestSubject",
                new TimeSpan(
                        new Time(2017, 9, 18, 12, 0),
                        new Time(2017, 9, 18, 12, 30)
                ));
        assertEquals("TestSubject", appointment.getSubject());
    }

    @Test
    public void getTimeSpan() {
        TimeSpan ts = new TimeSpan(
                new Time(2017, 9, 18, 12, 0),
                new Time(2017, 9, 18, 12, 30)
        );

        Appointment appointment = new Appointment("TestSubject",
                    ts
                );

        assertEquals(ts, appointment.getTimeSpan());
    }

    @Test
    public void invitees() {
        Appointment appointment = new Appointment("TestSubject",
                new TimeSpan(
                        new Time(2017, 9, 18, 12, 0),
                        new Time(2017, 9, 18, 12, 30)
                ));
        Contact contact = new Contact("Maria Jones");

        Iterator<Contact> iterator = appointment.invitees();
        assertEquals(false, iterator.hasNext(), "Fresh iterator was not empty?");

        appointment.addContact(contact);

        iterator = appointment.invitees(); //Re-add the iterator
        assertEquals(true, iterator.hasNext(), "Iterator had a next");
        assertEquals(contact, iterator.next(), "Iterator gave another contact back");

        iterator.remove();

        assertEquals(false, iterator.hasNext(), "Iterator gave non-existent contact back");
    }
}
