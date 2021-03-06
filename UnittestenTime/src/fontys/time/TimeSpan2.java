package fontys.time;

public class TimeSpan2 implements ITimeSpan {

    private ITime bt;
    private long duration;

    public TimeSpan2(ITime bt, ITime et) {
        if (bt.compareTo(et) > 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        this.bt = bt;
        String endTimeString = String.valueOf(bt.difference(et));
        this.duration = Long.parseLong(endTimeString);
    }

    @Override
    public ITime getBeginTime() {
        return bt;
    }

    @Override
    public ITime getEndTime() {
        return bt.plus((int)duration );
    }

    @Override
    public int length() {
        return (int)duration ;
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        if (beginTime.compareTo(bt.plus((int)duration)) >= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + bt.plus((int)duration));
        }

        bt = beginTime;
    }

    @Override
    public void setEndTime(ITime endTime) {
        if (endTime.compareTo(bt) <= 0) {
            throw new IllegalArgumentException("end time "
                    + bt.plus((int)duration) + " must be later then begin time " + bt);
        }

        bt = endTime;
    }

    @Override
    public void move(int minutes) {
        bt = bt.plus(minutes);
        duration = duration + minutes; //changed bt to et
    }

    @Override
    public void changeLengthWith(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("length of period must be positive");
        }
        duration = duration + minutes;
    }

    @Override
    public boolean isPartOf(ITimeSpan timeSpan) {
        return (getBeginTime().compareTo(timeSpan.getBeginTime()) >= 0
                && getEndTime().compareTo(timeSpan.getEndTime()) <= 0);
    }

    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
        Time et = bt.plus((int)duration);
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
        Time et = bt.plus((int)duration);
        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) > 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (et.compareTo(timeSpan.getEndTime()) < 0) {
            endtime = et;
        } else {
            endtime = timeSpan.getEndTime();
        }

        if (begintime.compareTo(endtime) >= 0) {
            return null;
        }

        return new TimeSpan(begintime, endtime);
    }
}
