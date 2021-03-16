package uk.ac.wlv.augmentedmemory;

//import java.util.Date;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reminder {
    private UUID mId;
    private String mActivity;
    private LocalDateTime mDateFrom;
    private LocalDateTime mDateTo;
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

    public LocalDateTime getDateFrom() {
        return mDateFrom;
    }

    public void setDateFrom(LocalDateTime dateTo) {
        mDateFrom = dateTo;
    }

    public LocalDateTime getDateTo() {
        return mDateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        mDateTo = dateTo;
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
