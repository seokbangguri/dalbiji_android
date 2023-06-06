package com.example.wb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class TABLE_SCHEDULE {
    @PrimaryKey(autoGenerate = true)
    public int dbSchSeq;

    @ColumnInfo(name = "bas_dt")
    public String bas_dt;

    @ColumnInfo(name = "dbSchName")
    public String dbSchName;

    @ColumnInfo(name = "dbStartTime")
    public String dbStartTime;

    @ColumnInfo(name = "dbEndTime")
    public String dbEndTime;

    @ColumnInfo(name = "dbComplete")
    public String dbComplete;

    @ColumnInfo(name = "dbAddress")
    public String dbAddress;

    @ColumnInfo(name = "dbLocLat")
    public String dbLocLat;

    @ColumnInfo(name = "dbLocLon")
    public String dbLocLon;

    @ColumnInfo(name = "dbEtc")
    public String dbEtc;

    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    String dbDateModified;

    public int getDbSchSeq() {
        return dbSchSeq;
    }

    public void setDbSchSeq(int dbSchSeq) {
        this.dbSchSeq = dbSchSeq;
    }

    public String getBas_dt() {
        return bas_dt;
    }

    public void setBas_dt(String bas_dt) {
        this.bas_dt = bas_dt;
    }

    public String getDbSchName() {
        return dbSchName;
    }

    public void setDbSchName(String dbSchName) {
        this.dbSchName = dbSchName;
    }

    public String getDbStartTime() {
        return dbStartTime;
    }

    public void setDbStartTime(String dbStartTime) {
        this.dbStartTime = dbStartTime;
    }

    public String getDbEndTime() {
        return dbEndTime;
    }

    public void setDbEndTime(String dbEndTime) {
        this.dbEndTime = dbEndTime;
    }

    public String getDbComplete() {
        return dbComplete;
    }

    public void setDbComplete(String dbComplete) {
        this.dbComplete = dbComplete;
    }

    public String getDbAddress() {
        return dbAddress;
    }

    public void setDbAddress(String dbAddress) {
        this.dbAddress = dbAddress;
    }

    public String getDbLocLat() {
        return dbLocLat;
    }

    public void setDbLocLat(String dbLocLat) {
        this.dbLocLat = dbLocLat;
    }

    public String getDbLocLon() {
        return dbLocLon;
    }

    public void setDbLocLon(String dbLocLon) {
        this.dbLocLon = dbLocLon;
    }

    public String getDbEtc() {
        return dbEtc;
    }

    public void setDbEtc(String dbEtc) {
        this.dbEtc = dbEtc;
    }

    public String getDbDateModified() {
        return dbDateModified;
    }

    public void setDbDateModified(String dbDateModified) {
        this.dbDateModified = dbDateModified;
    }
}
