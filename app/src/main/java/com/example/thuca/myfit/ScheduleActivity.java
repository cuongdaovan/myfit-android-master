package com.example.thuca.myfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.thuca.myfit.adapters.DetailsAdapter;
import com.example.thuca.myfit.adapters.MonthViewAdapter;
import com.example.thuca.myfit.helpers.APIHelper;
import com.example.thuca.myfit.helpers.Debug;
import com.example.thuca.myfit.helpers.Util;
import com.example.thuca.myfit.models.Day;
import com.example.thuca.myfit.models.ItemClickSupport;
import com.example.thuca.myfit.models.Schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {


    RecyclerView rvCalendar;
    MonthViewAdapter monthViewAdapter;
    DetailsAdapter detailsAdapter;

    TextView currentMonth;

    TextView btnNext, btnPrev;
    ListView lv_schedule;

    ArrayList<Day> dayData;

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    List<Schedule> schedules;

    APIHelper apiHelper;

    Debug debug;

    int month;
    int year;
    String maGV;
    List<Schedule>  list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        debug = new Debug(this);
        apiHelper = new APIHelper(this, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6WyJ0ZWFjaGVyIl0sIl9pZCI6IjVhZGMyOTM3YjcyMDNiMmFjOTRlNDEzNSIsInVzZXJuYW1lIjoidHVhbm5xIiwiZGVwYXJ0bWVudF9jb2RlIjoiTUhUIiwibWF4X3JvbGUiOiJ0ZWFjaGVyIiwibmFtZSI6Ik5ndXnhu4VuIFF14buRYyBUdeG6pW4iLCJ0ZWFjaGVyX2NvZGUiOiJ0dWFubnEiLCJpbXBvcnRfaGFzaCI6ImFiNTNmOWIwMDQxNmI2MmRjZTRiOThiYjQ0YjIxOGRkIiwiZW1haWwiOiJ0dWFubnFAdXRjLmVkdS52biIsInBob25lIjoiMDEyMzQ1Njc4OSIsImhhc2giOiI4YjJjZjAwOTU0YTMxYzlmNmIzMmMwNWNiZmZmMDRlZSIsImlhdCI6MTUyNTgzMjk4NywiZXhwIjoxNTI4NDI0OTg3fQ.DqHrTLKaYomEcsl632yDwhr5rr1bQAvIisUV4qSOHOo");

        Intent intent = getIntent();
        maGV = intent.getStringExtra("maGV");
        month = intent.getIntExtra("thang", 0);
        year = intent.getIntExtra("name", 2018);
        schedules=new ArrayList<>();
        list=new ArrayList<>();
        addControls();
        addEvents();
        setCalendar(month,year);
    }

    private void addControls() {
        rvCalendar = findViewById(R.id.rvCalendar);
        currentMonth = findViewById(R.id.currentMonth);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        lv_schedule=findViewById(R.id.lv_schedule);
    }

    private void addEvents() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNext();
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPrev();
            }
        });

        ItemClickSupport.addTo(rvCalendar).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                viewDateDetails(position);
            }
        });
    }

    private void viewDateDetails(int position) {
        int[] date = monthViewAdapter.getDate(position);

        if (date == null) return;

        //TODO: load thong tin chi tiet
        //for(dayData)
            //if (Util.dateEqual(date, ))
        int dataLength=dayData.size();
        schedules.clear();
        for(int i=0;i<dataLength;i++){
            if(dayData.get(i).getDay()==Integer.parseInt( monthViewAdapter.getmItems().get(position))
                    && dayData.get(i).getMonth()==month ){


                if(dayData.get(i).getPeriod_1().size()>0){
                    Schedule schedule=new Schedule();
                    schedule.setCa(1);
                    schedules.add(schedule);
                    for(int j=0;j<dayData.get(i).getPeriod_1().size();j++){
                        if(dayData.get(i).getPeriod_1().get(j).getTeacher_code()==null){
                            continue;
                        }else{
                            dayData.get(i).getPeriod_1().get(j).setCa(1);
                            schedules.add(dayData.get(i).getPeriod_1().get(j));
                        }

                    }
                }
                if(dayData.get(i).getPeriod_2().size()>0){
                    Schedule schedule=new Schedule();
                    schedule.setCa(2);
                    schedules.add(schedule);
                    for(int j=0;j<dayData.get(i).getPeriod_2().size();j++){
                        if(dayData.get(i).getPeriod_2().get(j).getTeacher_code()==null){
                            continue;
                        }else {
                            dayData.get(i).getPeriod_2().get(j).setCa(2);
                            schedules.add(dayData.get(i).getPeriod_2().get(j));
                        }

                    }
                }

                if(dayData.get(i).getPeriod_3().size()>0){
                    Schedule schedule=new Schedule();
                    schedule.setCa(3);
                    schedules.add(schedule);
                    for(int j=0;j<dayData.get(i).getPeriod_3().size();j++){
                        if(dayData.get(i).getPeriod_3().get(j).getTeacher_code()==null){
                            continue;
                        }else {
                            dayData.get(i).getPeriod_3().get(j).setCa(3);
                            schedules.add(dayData.get(i).getPeriod_3().get(j));
                        }

                    }
                }
                if(dayData.get(i).getPeriod_4().size()>0){
                    Schedule schedule=new Schedule();
                    schedule.setCa(4);
                    schedules.add(schedule);
                    for(int j=0;j<dayData.get(i).getPeriod_4().size();j++){
                        if(dayData.get(i).getPeriod_4().get(j).getTeacher_code()==null){
                            continue;
                        }else {
                            dayData.get(i).getPeriod_4().get(j).setCa(4);
                            schedules.add(dayData.get(i).getPeriod_4().get(j));
                        }

                    }
                }

                if(dayData.get(i).getPeriod_5().size()>0){
                    Schedule schedule=new Schedule();
                    schedule.setCa(5);
                    schedules.add(schedule);
                    for(int j=0;j<dayData.get(i).getPeriod_5().size();j++){
                        if(dayData.get(i).getPeriod_5().get(j).getTeacher_code()==null){
                            continue;
                        }else {
                            dayData.get(i).getPeriod_5().get(j).setCa(5);
                            schedules.add(dayData.get(i).getPeriod_5().get(j));
                        }

                    }
                }


            }

        }
        if(schedules.size()==0){
            debug.toast("null");
        }
        monthViewAdapter.setSelectedPos(position);

        detailsAdapter=new DetailsAdapter(schedules,this);
        lv_schedule.setAdapter(detailsAdapter);
        detailsAdapter.notifyDataSetChanged();
        monthViewAdapter.notifyDataSetChanged();
    }

    public void setCalendar(int month,int year) {

        final MaterialDialog dialogLoading = new MaterialDialog.Builder(this)
                .title("Loading")
                .content("Please wait")
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .show();

        Calendar mCalendar = Calendar.getInstance();

        rvCalendar.setLayoutManager(new GridLayoutManager(this, 7));
        rvCalendar.setHasFixedSize(false);

        String curMonth = Util.getMonth(month + 1) + " " + String.valueOf(year);
        currentMonth.setText(curMonth);

        this.dayData = new ArrayList<>();

        Response.Listener<JSONObject> onResponse = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    int dataLength = data.length();

                    dayData.clear();

                    for(int i = 0; i < dataLength; i++) {
                        JSONObject dayDataObject = data.getJSONObject(i);

                        JSONObject dateInfo = dayDataObject.getJSONObject("date");
                        Day day = new Day(dateInfo.getInt("day"), dateInfo.getInt("month"), dateInfo.getInt("year"));

                        day.setPeriod_1(getListSchedule(dayDataObject.getJSONArray("period_1")));
                        day.setPeriod_2(getListSchedule(dayDataObject.getJSONArray("period_2")));
                        day.setPeriod_3(getListSchedule(dayDataObject.getJSONArray("period_3")));
                        day.setPeriod_4(getListSchedule(dayDataObject.getJSONArray("period_4")));
                        day.setPeriod_5(getListSchedule(dayDataObject.getJSONArray("period_5")));

                        dayData.add(day);
                    }

                    monthViewAdapter.notifyDataSetChanged();

                    dialogLoading.dismiss();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener onError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                debug.toast("error: "+ error.toString());
            }
        };

        String from = (month + 1) + "/1/" + year;
        String to = (month + 1) + "/" + Util.getDayOfMonth(month) + "/" + year;

        apiHelper.searchSchedule(maGV, from, to, onResponse, onError);

        monthViewAdapter = new MonthViewAdapter(ScheduleActivity.this, month, year, dayData);
        rvCalendar.setAdapter(monthViewAdapter);
    }

    private List<Schedule> getListSchedule(JSONArray array) throws JSONException {

        List<Schedule> ketQua = new ArrayList<>();

        int length = array.length();

        for (int i = 0; i < length; i++) {
            JSONObject scheduleData = array.getJSONObject(i);

            String teacher_code = scheduleData.getString("teacher_code");
            String room = scheduleData.getString("room");
            String subject_code = scheduleData.getString("subject_code");
            String subject_name = scheduleData.getString("subject_name");
            String merged_class = scheduleData.getString("merged_class");

            ketQua.add(new Schedule(teacher_code, room, subject_code, subject_name, merged_class));
        }
        return ketQua;
    }

    public void onClickNext() {

        schedules.clear();
        month = month + 1;

        if (month > 11) {
            month = 0;
            year = year + 1;
        }

        String curMonth = Util.getMonth(month + 1) + " " + String.valueOf(year);
        currentMonth.setText(curMonth);

        //TODO: load month data
//        apiHelper.searchSchedule();
        this.setCalendar(month,year);
    }

    public void onClickPrev() {
        schedules.clear();
        month = month - 1;

        if (month < 0) {
            month = 11;
            year = year - 1;
        }

        String curMonth = Util.getMonth(month + 1) + " " + String.valueOf(year);

        //TODO: load month data

        currentMonth.setText(curMonth);

        this.setCalendar(month,year);
    }

}
