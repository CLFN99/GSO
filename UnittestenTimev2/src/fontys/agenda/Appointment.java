package fontys.agenda;

import fontys.time.ITimeSpan;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author MagicLegend
 *
 */

public class Appointment implements Iterator<Contact> {
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

    public ITimeSpan getTimeSpan() {
        return timeSpan;
    }

    /*public Iterator<Contact> invitees() {

    }*/



    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */

    @Override
    public boolean hasNext() {
        return false;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Contact next() {
        return null;
    }
}
