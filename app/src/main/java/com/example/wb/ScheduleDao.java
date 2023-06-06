package com.example.wb;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// data access object

@Dao
public interface ScheduleDao {
    @Insert
    void SetInsertSchedule(TABLE_SCHEDULE schedule);

    @Update
    void SetUpdateSchedule(TABLE_SCHEDULE schedule);

    @Delete
    void SetDeleteSchedule(TABLE_SCHEDULE schedule);

//    @Query("SELECT * FROM TABLE_SCHEDULE WHERE bas_dt=:bas_dt")
//    List<TABLE_SCHEDULE> getScheduleBasdt(String bas_dt);

    @Query("SELECT * FROM TABLE_SCHEDULE WHERE bas_dt = :basDt")
    List<TABLE_SCHEDULE> getScheduleByDate(String basDt);

    @Query("SELECT * FROM TABLE_SCHEDULE")
    List<TABLE_SCHEDULE> getAllSchedules();

    @Query("DELETE FROM TABLE_SCHEDULE WHERE dbSchSeq=:seq")
    void deleteScheduleById(int seq);

    @Query("SELECT * FROM TABLE_SCHEDULE WHERE dbSchSeq = :seq")
    List<TABLE_SCHEDULE> getScheduleBySeq(int seq);
}
