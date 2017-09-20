package fontys.agenda;

import fontys.time.ITime;
import fontys.time.ITimeSpan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Appointment implements Iterator<Contact> {
    private String subject;
    private ITimeSpan timeSpan;
    private List<Contact> contacts;

    public Appointment(String subject, ITimeSpan timeSpan) {
        contacts = new ArrayList<>();
        this.subject = subject;
        this.timeSpan = timeSpan;
    }

    public boolean addContact(Contact c) {
        contacts.add(c);
        return true;
    }

    public void removeContact(Contact c) {
        contacts.remove(c);
    }

    public String getSubject() {
        return subject;
    }

    public ITimeSpan getTimeSpan() {
        return timeSpan;
    }

    public Iterator<Contact> invitees() {

    }


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
