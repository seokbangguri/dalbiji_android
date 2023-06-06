package com.example.wb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.room.Room;

import org.chromium.base.Promise;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

// API LIST
public class WebAppInterface {
    Context mContext;
    public ScheduleDao mScheduleDao;
    private ScheduleDatabase mDb;
    private WebView webview;



    /** Instantiate the interface and set the context */
    public WebAppInterface(Context c, ScheduleDatabase db) {
        mContext = c;
        mDb = db;

    }

    /** Show a toast from the web page */

    @JavascriptInterface
    public void test() {
        System.out.println("hello");
    }

    @JavascriptInterface
    public void test1() {
        mScheduleDao = mDb.scheduleDao();
//        TABLE_SCHEDULE schedule = new TABLE_SCHEDULE();
//
//        List<TABLE_SCHEDULE> ScheduleList = mScheduleDao.getScheduleAll();
//        System.out.println("LIST TEST: "+ ScheduleList);
//        // schedule 모든 데이터 조회
//        for (int i = 0; i < ScheduleList.size(); i++) {
//            Log.d("TEST", ScheduleList.get(i).getBas_dt() + "\n"
//                    + ScheduleList.get(i).getDbSchName() + "\n");
//        }

    }
//    @JavascriptInterface
//    public void insert(String SchName) {
//        mScheduleDao = mDb.scheduleDao();
//        TABLE_SCHEDULE schedule = new TABLE_SCHEDULE();
//        schedule.setBas_dt("2023-03-06");
//        schedule.setDbSchName( SchName );
////        schedule.setDbSchName("일정제목 테스트");
//        schedule.setDbStartTime("08:30");
//        schedule.setDbEndTime("09:00");
//        schedule.setDbComplete("OK");
//        schedule.setDbAddress("성결대학로");
//        schedule.setDbLocLat("");
//        schedule.setDbLocLon("");
//        schedule.setDbEtc("test");
//        mScheduleDao.SetInsertSchedule(schedule);
//    }

    @JavascriptInterface
    public String insert(String json) {
        mScheduleDao = mDb.scheduleDao();
        String resultJson = "";
        try {
            JSONObject jsonObj = new JSONObject(json);
            TABLE_SCHEDULE schedule = new TABLE_SCHEDULE();
            schedule.setBas_dt(jsonObj.optString("bas_dt", null));
            schedule.setDbSchName(jsonObj.optString("dbSchName", null));
            schedule.setDbStartTime(jsonObj.optString("dbStartTime", null));
            schedule.setDbEndTime(jsonObj.optString("dbEndTime", null));
            schedule.setDbComplete(jsonObj.optString("dbComplete", null));
            schedule.setDbAddress(jsonObj.optString("dbAddress", null));
            schedule.setDbLocLat(jsonObj.optString("dbLocLat", null));
            schedule.setDbLocLon(jsonObj.optString("dbLocLon", null));
            schedule.setDbEtc(jsonObj.optString("dbEtc", null));
            mScheduleDao.SetInsertSchedule(schedule);
            JSONObject resultObj = new JSONObject();
            resultObj.put("status", "OK");
            resultJson = resultObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultJson;
    }

//    @SuppressLint("MissingPermission")
//    @JavascriptInterface
//    public String getCurrentLocation() {
//        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
//        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        double latitude = location.getLatitude();
//        double longitude = location.getLongitude();
//        JSONObject resultObj = new JSONObject();
//        try {
//            resultObj.put("latitude", latitude);
//            resultObj.put("longitude", longitude);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return resultObj.toString();
//    }

    @SuppressLint("MissingPermission")
    @JavascriptInterface
    public void startLocationUpdates() {
        String resultJson = "";
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                JSONObject resultObj = new JSONObject();
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                try {
                    resultObj.put("latitude", latitude);
                    resultObj.put("longitude", longitude);
                    String resultJson = resultObj.toString();
                    // TODO: pass the result back to JavaScript code
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }






//    @JavascriptInterface
//    public Promise<String> insert(String json) {
//        mScheduleDao = mDb.scheduleDao();
//        try {
//            JSONObject jsonObj = new JSONObject(json);
//            TABLE_SCHEDULE schedule = new TABLE_SCHEDULE();
//            schedule.setBas_dt(jsonObj.getString("bas_dt"));
//            schedule.setDbSchName(jsonObj.getString("dbSchName"));
//            schedule.setDbStartTime(jsonObj.getString("dbStartTime"));
//            schedule.setDbEndTime(jsonObj.getString("dbEndTime"));
//            schedule.setDbComplete(jsonObj.getString("dbComplete"));
//            schedule.setDbAddress(jsonObj.getString("dbAddress"));
//            schedule.setDbLocLat(jsonObj.getString("dbLocLat"));
//            schedule.setDbLocLon(jsonObj.getString("dbLocLon"));
//            schedule.setDbEtc(jsonObj.getString("dbEtc"));
//            mScheduleDao.SetInsertSchedule(schedule);
//            JSONObject resultObj = new JSONObject();
//            resultObj.put("status", "OK");
//            final String resultJson = resultObj.toString();
//            return Promise.resolve(resultJson);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return Promise.reject(e);
//        }
//    }


    // seq를 받아서 해당 seq data 삭제
    @JavascriptInterface
    public void deleteSeq(int seq) {
        mScheduleDao = mDb.scheduleDao();
        mScheduleDao.deleteScheduleById(seq);
    }




    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

//    @JavascriptInterface
//    public void selectAll() {
//        TABLE_SCHEDULE schedule = new TABLE_SCHEDULE();
//        List<TABLE_SCHEDULE> ScheduleList = mScheduleDao.getScheduleAll();
//        // schedule 모든 데이터 조회
//        for (int i = 0; i < ScheduleList.size(); i++) {
//            Log.d("TEST", ScheduleList.get(i).getBas_dt() + "\n"
//                    + ScheduleList.get(i).getDbSchName() + "\n");
//        }
//
//    }

    //DB에서 해당 날짜에 해당하는 data select
    @JavascriptInterface
    public String selectByDate(String basDt) {
        mScheduleDao = mDb.scheduleDao();
        List<TABLE_SCHEDULE> schedules = mScheduleDao.getScheduleByDate(basDt);
        JSONArray jsonArray = new JSONArray();
        for (TABLE_SCHEDULE schedule : schedules) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("dbSchSeq", schedule.getDbSchSeq());
                jsonObject.put("bas_dt", schedule.getBas_dt());
                jsonObject.put("dbSchName", schedule.getDbSchName());
                jsonObject.put("dbStartTime", schedule.getDbStartTime());
                jsonObject.put("dbEndTime", schedule.getDbEndTime());
                jsonObject.put("dbComplete", schedule.getDbComplete());
                jsonObject.put("dbAddress", schedule.getDbAddress());
                jsonObject.put("dbLocLat", schedule.getDbLocLat());
                jsonObject.put("dbLocLon", schedule.getDbLocLon());
                jsonObject.put("dbEtc", schedule.getDbEtc());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray.toString();
    }

    //DB에서 dbSchSeq에 해당하는 data select
    @JavascriptInterface
    public String selectBySeq(int seq) {
        mScheduleDao = mDb.scheduleDao();
        List<TABLE_SCHEDULE> schedules = mScheduleDao.getScheduleBySeq(seq);
        JSONArray jsonArray = new JSONArray();
        for (TABLE_SCHEDULE schedule : schedules) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("dbSchSeq", schedule.getDbSchSeq());
                jsonObject.put("bas_dt", schedule.getBas_dt());
                jsonObject.put("dbSchName", schedule.getDbSchName());
                jsonObject.put("dbStartTime", schedule.getDbStartTime());
                jsonObject.put("dbEndTime", schedule.getDbEndTime());
                jsonObject.put("dbComplete", schedule.getDbComplete());
                jsonObject.put("dbAddress", schedule.getDbAddress());
                jsonObject.put("dbLocLat", schedule.getDbLocLat());
                jsonObject.put("dbLocLon", schedule.getDbLocLon());
                jsonObject.put("dbEtc", schedule.getDbEtc());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray.toString();
    }


    //DB에서 모든 data를 select
    @JavascriptInterface
    public String selectAll() {
        mScheduleDao = mDb.scheduleDao();
        List<TABLE_SCHEDULE> schedules = mScheduleDao.getAllSchedules();
        JSONArray jsonArray = new JSONArray();
        for (TABLE_SCHEDULE schedule : schedules) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("dbSchSeq", schedule.getDbSchSeq());
                jsonObject.put("bas_dt", schedule.getBas_dt());
                jsonObject.put("dbSchName", schedule.getDbSchName());
                jsonObject.put("dbStartTime", schedule.getDbStartTime());
                jsonObject.put("dbEndTime", schedule.getDbEndTime());
                jsonObject.put("dbComplete", schedule.getDbComplete());
                jsonObject.put("dbAddress", schedule.getDbAddress());
                jsonObject.put("dbLocLat", schedule.getDbLocLat());
                jsonObject.put("dbLocLon", schedule.getDbLocLon());
                jsonObject.put("dbEtc", schedule.getDbEtc());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray.toString();
    }

    //update할 JSON을 받으면 update
    @JavascriptInterface
    public String update(String json) {
        mScheduleDao = mDb.scheduleDao();
        String resultJson = "";
        try {
            JSONObject jsonObj = new JSONObject(json);
            TABLE_SCHEDULE schedule = new TABLE_SCHEDULE();
            schedule.setDbSchSeq(jsonObj.optInt("dbSchSeq", 0));
            schedule.setBas_dt(jsonObj.optString("bas_dt", ""));
            schedule.setDbSchName(jsonObj.optString("dbSchName", ""));
            schedule.setDbStartTime(jsonObj.optString("dbStartTime", ""));
            schedule.setDbEndTime(jsonObj.optString("dbEndTime", ""));
            schedule.setDbComplete(jsonObj.optString("dbComplete", ""));
            schedule.setDbAddress(jsonObj.optString("dbAddress", ""));
            schedule.setDbLocLat(jsonObj.optString("dbLocLat", ""));
            schedule.setDbLocLon(jsonObj.optString("dbLocLon", ""));
            schedule.setDbEtc(jsonObj.optString("dbEtc", ""));

            mScheduleDao.SetUpdateSchedule(schedule);
            JSONObject resultObj = new JSONObject();
            resultObj.put("status", "OK");
            resultJson = resultObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultJson;
    }



}
