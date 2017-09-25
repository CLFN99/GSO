package fontys.tests;

import fontys.agenda.Contact;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void testNewContact(){
        Contact c = new Contact("Maria Jones");
        assertEquals("Maria Jones", c.getName());
    }


    @Test
    void testAppointments() {
    }

}