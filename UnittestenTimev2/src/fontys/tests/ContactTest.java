package fontys.tests;

import fontys.agenda.Appointment;
import fontys.agenda.Contact;
import fontys.time.Time;
import fontys.time.TimeSpan;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void testNewContact(){
        Contact c = new Contact("Maria Jones");
        assertEquals("Maria Jones", c.getName());
    }

    @Test
    void testAddAppointment(){
        Contact c = new Contact("Maria Jones");
        Appointment appointment = new Appointment("TestSubject",
                new TimeSpan(
                        new Time(2017, 9, 18, 12, 0),
                        new Time(2017, 9, 18, 12, 30)
                ));
        appointment.addContact(c);
        //List<Appointment> contactAppts = c.appointments();
        ////boolean apptAdded = c.appointments().contains(appointment);
        //assertEquals(true, apptAdded);

    }

    @Test
    void testAppointments() {
    }

}