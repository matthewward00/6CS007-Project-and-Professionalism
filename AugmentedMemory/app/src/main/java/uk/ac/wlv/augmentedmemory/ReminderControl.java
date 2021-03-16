package uk.ac.wlv.augmentedmemory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.CaseMap;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import uk.ac.wlv.augmentedmemory.Database.DatabaseHelper;
import uk.ac.wlv.augmentedmemory.Database.DatabaseCursorWrapper;
import uk.ac.wlv.augmentedmemory.Database.DbSchema;
import java.io.File;

public class ReminderControl {
    private static ReminderControl sReminderControl;
    private List<Reminder> mReminders;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ReminderControl get(Context context) {
        if (sReminderControl == null) {
            sReminderControl = new ReminderControl(context);
        }
        return sReminderControl;
    }
    private ReminderControl(Context context) {
        mReminders = new ArrayList<>();
        mContext = context.getApplicationContext();
        mDatabase = new DatabaseHelper(mContext).getWritableDatabase();
    }

    public List<Reminder> getReminders() {
        List<Reminder> reminders = new ArrayList<>();
        DatabaseCursorWrapper cursor = queryReminders(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                reminders.add(cursor.getReminder());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return reminders;
    }

    public void addReminder(Reminder r) {
        ContentValues values = getContentValues(r);
        mDatabase.insert(DbSchema.ReminderTable.NAME, null, values);
    }

    public void deleteReminder(UUID ID) {
        mDatabase.delete(DbSchema.ReminderTable.NAME,"UUID = ?",new String[] {ID.toString()});
    }

    public Reminder getReminder(UUID id) {
        DatabaseCursorWrapper cursor = queryReminders(
                DbSchema.ReminderTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getReminder();
        } finally {
            cursor.close();
        }
    }

    public void updateReminder(Reminder reminder) {
        String uuidString = reminder.getId().toString();
        ContentValues values = getContentValues(reminder);
        mDatabase.update(DbSchema.ReminderTable.NAME, values,
                DbSchema.ReminderTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private DatabaseCursorWrapper queryReminders(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                DbSchema.ReminderTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new DatabaseCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Reminder reminder) {
        ContentValues values = new ContentValues();
        values.put(DbSchema.ReminderTable.Cols.UUID, reminder.getId().toString());
        values.put(DbSchema.ReminderTable.Cols.ACTIVITY, reminder.getActivity());
        values.put(DbSchema.ReminderTable.Cols.DATEFROM, reminder.getDateFrom().toString());
        values.put(DbSchema.ReminderTable.Cols.DATETO, reminder.getDateTo().toString());
        values.put(DbSchema.ReminderTable.Cols.TIME, reminder.getTime());
        values.put(DbSchema.ReminderTable.Cols.LOCATION, reminder.getLocation());
        values.put(DbSchema.ReminderTable.Cols.PEOPLE, reminder.getPeople());
        return values;
    }

}