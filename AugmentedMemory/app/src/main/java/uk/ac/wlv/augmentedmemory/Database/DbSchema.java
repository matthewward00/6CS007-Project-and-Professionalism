package uk.ac.wlv.augmentedmemory.Database;

public class DbSchema {
    public static final class ReminderTable {
        public static final String NAME = "reminders";
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String ACTIVITY = "activity";
            public static final String DATE = "date";
            public static final String TIME = "time";
            public static final String LOCATION = "location";
            public static final String PEOPLE = "people";
        }
    }
}
