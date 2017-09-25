package fontys.agenda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Célina Ngapy
 * specificatie
 *
 */

public class Contact {
    private String name;
    private List<Appointment> agenda;

    /**
     * creation of a Contact object with name
     */

    public Contact(String name){
        this.name = name;
        this.agenda = new ArrayList<>();
    }
    /**
     *
     * @return the the name of this contact
     */
    public String getName(){return name;}

    /**
     * Adds an appointment to this contact's agenda
     * @return {@code true} when there is no overlap
     * @return {@code false} when there is overlap between appointments of this contact
     */
    protected boolean addAppointment(Appointment a){
        for (Appointment appt : this.agenda) {
            // appt: 1-5 a: 2-3
            // or begintime a is after begintime appt and before endtime appt
            int compareBegin = a.getTimeSpan().getBeginTime().compareTo(appt.getTimeSpan().getBeginTime());
            int compareBeginToEnd = a.getTimeSpan().getBeginTime().compareTo(appt.getTimeSpan().getEndTime());

            if(compareBegin == 1 || compareBegin == 0 && compareBeginToEnd == -1){
                return true;
            }
        }
        return false;
    }

    /**
     * Removes appointment from contact's agenda
     */
    protected void removeAppointment(Appointment a){
        this.agenda.remove(a);
    }

    /**
     * Iterates through appointments (? not sure)
     */
    public Iterator<Appointment> appointments(){
        return null;
    }

}
