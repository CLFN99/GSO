package fontys.tests;

import fontys.agenda.Appointment;
import fontys.agenda.Contact;
import fontys.time.Time;
import fontys.time.TimeSpan;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
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
        boolean added = appointment.addContact(c);
        assertEquals(true, added);
    }

    @Test
    void testOverlap(){
        Contact c = new Contact("Josie Reynolds");
        TimeSpan t1 = new TimeSpan(
                new Time(2017, 9, 18, 12, 0),
                new Time(2017, 9, 18, 15, 30));
        TimeSpan t2 = new TimeSpan(
                new Time(2017,9,18,13,0),
                new Time (2017,9,18,16,0)
        );
        Appointment apptOne = new Appointment("Work meeting", t1);
        Appointment apptTwo = new Appointment("Driving lessons", t2);
        assertEquals(true, apptOne.addContact(c), "Appointment added!");
        assertEquals(false, apptTwo.addContact(c), "Contact already has an appointment in this time frame!");
    }

    @Test
    void testRemoveAppointments(){
        Contact c = new Contact("Pietje");
        TimeSpan t1 = new TimeSpan(
                new Time(2017, 9, 18, 12, 0),
                new Time(2017, 9, 18, 15, 30));
        TimeSpan t2 = new TimeSpan(
                new Time(2017,9,18,17,0),
                new Time (2017,9,18,19,0)
        );
        Appointment apptOne = new Appointment("Work meeting", t1);
        Appointment apptTwo = new Appointment("Driving lessons", t2);
        apptOne.addContact(c);
        apptTwo.addContact(c);

        boolean removed = false;
        apptOne.removeContact(c);

        Iterator<Appointment> itr = c.appointments();
        while(itr.hasNext()){
            Appointment appt = itr.next();
            if(appt.getSubject().equals("Work meeting")){
                removed = false;
            }
            else {
                removed = true;
            }
        }
        assertEquals(true, removed);
    }

    @Test
    void testAppointments() {
        Contact c = new Contact("Pietje");
        Iterator<Appointment> itr = c.appointments();
        assertEquals(false, itr.hasNext(), "Contact has no appointments");

        TimeSpan t1 = new TimeSpan(
                new Time(2017, 9, 18, 12, 0),
                new Time(2017, 9, 18, 15, 30));
        TimeSpan t2 = new TimeSpan(
                new Time(2017,9,18,17,0),
                new Time (2017,9,18,19,0)
        );
        Appointment apptOne = new Appointment("Work meeting", t1);
        Appointment apptTwo = new Appointment("Driving lessons", t2);
        apptOne.addContact(c);
        apptTwo.addContact(c);
        assertEquals(true, itr.hasNext(), "Contact has appointments");

    }

}