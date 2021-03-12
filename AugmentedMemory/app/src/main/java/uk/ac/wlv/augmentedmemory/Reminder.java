package uk.ac.wlv.augmentedmemory;

import java.util.Date;
import java.util.UUID;

public class Reminder {
    private UUID mId;
    private String mActivity;
    private Date mDate;
    private String mTime;
    private String mLocation;
    private String mPeople;

    public Reminder() {
        this(UUID.randomUUID());
    }

    public Reminder(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getActivity() {
        return mActivity;
    }

    public void setActivity(String activity) {
        mActivity = activity;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getPeople() {
        return mPeople;
    }

    public void setPeople(String people) {
        mPeople = people;
    }
}
