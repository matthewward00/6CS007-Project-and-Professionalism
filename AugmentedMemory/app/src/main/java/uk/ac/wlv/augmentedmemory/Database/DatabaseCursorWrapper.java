package uk.ac.wlv.augmentedmemory.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

//import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import uk.ac.wlv.augmentedmemory.Reminder;

public class DatabaseCursorWrapper extends CursorWrapper {
    public DatabaseCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Reminder getReminder() {
        String uuidString = getString(getColumnIndex(DbSchema.ReminderTable.Cols.UUID));
        String activity = getString(getColumnIndex(DbSchema.ReminderTable.Cols.ACTIVITY));
        LocalDateTime dateFrom = LocalDateTime.parse(getString(getColumnIndex(DbSchema.ReminderTable.Cols.DATEFROM)));
        LocalDateTime dateTo = LocalDateTime.parse(getString(getColumnIndex(DbSchema.ReminderTable.Cols.DATETO)));
        String time = getString(getColumnIndex(DbSchema.ReminderTable.Cols.TIME));
        String location = getString(getColumnIndex(DbSchema.ReminderTable.Cols.LOCATION));
        String people = getString(getColumnIndex(DbSchema.ReminderTable.Cols.PEOPLE));
        Reminder reminder = new Reminder(UUID.fromString(uuidString));
        reminder.setActivity(activity);
        reminder.setDateFrom(dateFrom);
        reminder.setDateTo(dateTo);
        reminder.setTime(time);
        reminder.setLocation(location);
        reminder.setPeople(people);
        return reminder;
    }
}
