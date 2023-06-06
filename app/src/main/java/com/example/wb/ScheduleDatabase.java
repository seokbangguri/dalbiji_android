package com.example.wb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TABLE_SCHEDULE.class}, version = 1)
public abstract class ScheduleDatabase extends RoomDatabase {
    public abstract ScheduleDao scheduleDao();
}
