package uk.ac.wlv.augmentedmemory.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "augmentedMemory.db";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DbSchema.ReminderTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                DbSchema.ReminderTable.Cols.ACTIVITY + ", " +
                DbSchema.ReminderTable.Cols.DATE + ", " +
                DbSchema.ReminderTable.Cols.TIME + ", " +
                DbSchema.ReminderTable.Cols.LOCATION + ", " +
                DbSchema.ReminderTable.Cols.PEOPLE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DbSchema.ReminderTable.NAME);
        onCreate(db);
    }
}
