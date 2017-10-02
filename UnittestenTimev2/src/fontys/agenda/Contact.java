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
     * @param name  Name of the contact
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
     * @param a Appointment to be added to the list
     * @return {@code true} when there is no overlap
     * @return {@code false} when there is overlap between appointments of this contact
     */
    protected boolean addAppointment(Appointment a){
        boolean intersect = false;
        if (agenda.isEmpty()){
            this.agenda.add(a);
            return true;
        }
        else {


            //if(agenda.isEmpty()){
                for (Appointment appt : this.agenda) {
                    if(appt.getTimeSpan().intersectionWith(a.getTimeSpan()) != null){
                        //they  intersect
                        intersect = true;
                    }
                }
            //}
        }
        if (intersect){
            return  false;
        }
        this.agenda.add(a);
        return true;
    }

    /**
     * Removes appointment from contact's agenda
     * @param a Appointment to remove from the agenda
     */
    protected void removeAppointment(Appointment a){
        this.agenda.remove(a);
    }

    /**
     *
     * @return Agenda iterator
     */
    public Iterator<Appointment> appointments(){
        return agenda.iterator();
    }

}
