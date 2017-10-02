/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

/**
 *
 * @author Frank Peeters, Nico Kuijpers
 *
 * LET OP: De klasse TimeSpan bevat enkele fouten.
 *
 */
public class TimeSpan implements ITimeSpan {

    /* class invariant:
     * A stretch of time with a begin time and end time.
     * The end time is always later then the begin time; the length of the time span is
     * always positive
     *
     */
    private ITime bt, et;

    /**
     *
     * @param bt BeginTime must be earlier than et
     * @param et EndTime
     */
    public TimeSpan(ITime bt, ITime et) {
        if (bt.compareTo(et) > 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        this.bt = bt;
        this.et = et;
    }

    @Override
    public ITime getBeginTime() {
        return bt;
    }

    @Override
    public ITime getEndTime() {
        return et;
    }

    @Override
    public int length() {
        return bt.difference(et);
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        if (beginTime.compareTo(et) >= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        bt = beginTime;
    }

    @Override
    public void setEndTime(ITime endTime) {
        if (endTime.compareTo(bt) <= 0) {
            throw new IllegalArgumentException("end time "
                    + et + " must be later then begin time " + bt);
        }

        bt = endTime;
    }

    @Override
    public void move(int minutes) {
        bt = bt.plus(minutes);
        et = et.plus(minutes); //changed bt to et
    }

    @Override
    public void changeLengthWith(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("length of period must be positive");
        }

        et = et.plus(minutes);
    }

    @Override
    public boolean isPartOf(ITimeSpan timeSpan) {
        return (getBeginTime().compareTo(timeSpan.getBeginTime()) >= 0
                && getEndTime().compareTo(timeSpan.getEndTime()) <= 0);
    }

    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
        if (bt.compareTo(timeSpan.getEndTime()) > 0 || et.compareTo(timeSpan.getBeginTime()) < 0) {
            return null;
        }

        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) < 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (et.compareTo(timeSpan.getEndTime()) > 0) {
            endtime = et;
        } else {
            endtime = timeSpan.getEndTime();
        }

        return new TimeSpan(begintime, endtime);

    }

    @Override
    public ITimeSpan intersectionWith(ITimeSpan timeSpan) {

        int compareBegin = timeSpan.getBeginTime().compareTo(this.getBeginTime());
        int compareBeginToEnd = timeSpan.getBeginTime().compareTo(this.getEndTime());

        if(compareBegin == 0 || compareBegin > 0 && compareBeginToEnd < 0 ){
            // the beginTime is the same or the begintime is after begintime this and before endtime this
            //they intersect
            ITime begintime = null, endtime = null;

            //determine begintime
            if (timeSpan.getBeginTime().compareTo(bt) < 0 ) {
                begintime = bt;
            }
            else if (timeSpan.getBeginTime().compareTo(bt) == 0){
                begintime = bt;
            }
            else if (timeSpan.getBeginTime().compareTo(bt) > 0){
                begintime = timeSpan.getBeginTime();
            }

            //determine endtime
            if(timeSpan.getEndTime().compareTo(et) > 0){
                endtime = et;
            }
            else if (timeSpan.getEndTime().compareTo(et) < 0){
                endtime = timeSpan.getEndTime();
            }
            else if (timeSpan.getEndTime().compareTo(et) == 0){
                endtime = et;
            }

            return new TimeSpan(begintime, endtime);

        }
        return null;

    }
}
