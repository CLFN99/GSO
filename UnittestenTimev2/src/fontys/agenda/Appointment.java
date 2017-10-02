package fontys.agenda;

import fontys.time.ITimeSpan;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author MagicLegend
 *
 */

public class Appointment {
    private String subject;
    private ITimeSpan timeSpan;
    private List<Contact> contacts;

    /**
     *
     * @param subject   Subject of the appointment
     * @param timeSpan  Timespan of the appointment
     */

    public Appointment(String subject, ITimeSpan timeSpan) {
        contacts = new ArrayList<>();
        this.subject = subject;
        this.timeSpan = timeSpan;
    }

    /**
     * Adds a contact to the appointment, and adds the appointment to that contact class
     * @param c     Add contact to the appointment
     * @return      Returns if the addAppointment was successful }
     */

    public boolean addContact(Contact c) {
        contacts.add(c);
        return c.addAppointment(this);
    }

    /**
     * Removes the given contact from the appointment
     * @param c     The contact to remove from the appointment
     */

    public void removeContact(Contact c) {
        contacts.remove(c);
        c.removeAppointment(this);
    }

    /**
     * Returns the subject of the current appointment
     * @return Returns the subject of the current appointment
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Returns the timespan of the appointment
     * @return Timespan object
     */
    public ITimeSpan getTimeSpan() {
        return timeSpan;
    }

    /**
     * Returns the iterator of contacts
     * @return the iterator of contacts
     */
    public Iterator<Contact> invitees() {
        return contacts.iterator();
    }
}
