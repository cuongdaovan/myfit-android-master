package com.example.thuca.myfit;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.thuca.myfit.helpers.APIHelper;
import com.example.thuca.myfit.helpers.Debug;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.TimeZone;

public class TempActivity extends AppCompatActivity {

    String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InR1YW5ucSIsIm5hbWUiOiJ0dWFubnEiLCJlbWFpbCI6InR1YW5ucUB1dGMuZWR1LnZuIiwicGhvbmUiOiIwMTY4OTc5ODczMSIsIm1heF9yb2xlIjoiYWRtaW4iLCJyb2xlcyI6WyJhZG1pbiIsImRlYW4iLCJhc3NvY2lhdGVfZGVhbiIsImhlYWRfb2ZfZGVwYXJ0bWVudCIsImRlcHV0eV9oZWFkX29mX2RlcGFydG1lbnQiLCJ0ZWFjaGVyIiwic3R1ZGVudCJdLCJkZXBhcnRtZW50IjoiTUhUIiwidGVhY2hlcl9jb2RlIjoidHVhbm5xIiwiaWF0IjoxNTI0MzIyNTg5LCJleHAiOjE1MjQzMjI2MTl9.RPtVSr25HSxTX5sdnOnrwHHgWhT9lSkAQicl6ZAN5o4";

    String headers[] = {"Ngày", "Ca 1", "Ca 2", "Ca 3", "Ca 4"};

    String schedules[][] = {
            {"23/5/2018", "201.A2\nATBMTT(N01)", "201.A3\nATBMTT(N02)", "201.A4\nATBMTT(N03)", "201.A5\nATBMTT(N04)"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
            {"23/5/2018", "201.A2", "201.A3", "201.A4", "201.A5"},
    };

    TableLayout tableHeader, tableBody;
    TableRow tr;
    TextView tv;
    EditText txtMaGV, txtFrom, txtTo;
    Button btnFrom, btnTo, btnTraCuu;
    DatePickerDialog pic;
    DatePickerDialog.OnDateSetListener callback;
    int curFromYear, curFromMonth, curFromDay;
    int curToYear, curToMonth, curToDay;

    Debug debug;
    APIHelper apiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        debug = new Debug(this);
        apiHelper = new APIHelper(this, TOKEN);

        addControls();
        initData();

        addEvents();

        addTableHeader();
    }


    private void initData() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

//        curFromYear = curToYear = calendar.get(Calendar.YEAR);
//        curFromMonth = curToMonth = calendar.get(Calendar.MONTH);
//        curFromDay = curToDay = calendar.get(Calendar.DAY_OF_MONTH);

//        String date = curFromDay + "/" + (curFromMonth + 1) + "/" + curFromYear;

        curFromYear = curToYear = 2018;
        curFromMonth = 2;
        curToMonth = 3;
        curFromDay = 15;
        curToDay = 7;
        String fromDate = "15/3/2018";
        String toDate = "7/4/2018";
        txtFrom.setText(fromDate);
        txtTo.setText(toDate);
    }

    private void addEvents() {
        btnFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(R.id.btnFrom);
            }
        });
        btnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(R.id.btnTo);
            }
        });
        btnTraCuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                traCuu();
            }
        });
    }

    private void traCuu() {
        String maGV = txtMaGV.getText().toString().trim();

        if (maGV.length() == 0) {
            debug.toast("Nhập mã giảng viên");
            txtMaGV.requestFocus();
            return;
        }
        tableBody.removeAllViews();
        addTableHeader();

        Response.Listener<JSONArray> onResponse = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                debug.toast(response.toString());
                int length = response.length();
                try {


                    for (int i = 0; i < length; i++) {
                        JSONObject object = response.getJSONObject(i);
                        String[] schedule = {
                                object.getString("date"),
                                object.getString("period_1"),
                                object.getString("period_2"),
                                object.getString("period_3"),
                                object.getString("period_4")
                        };
                        addTableRow(schedule);

                    }
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

        String startTime = (curFromMonth + 1) + "/" + curFromDay + "/" + curFromYear;
        String endTime = (curToMonth + 1) + "/" + curToDay + "/" + curToYear;


//        apiHelper.searchSchedule(maGV, startTime, endTime, onResponse, onError);
    }

    public void showDatePickerDialog(final int ID_BTN) {
        callback = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                String value = (dayOfMonth) + "/" + (monthOfYear + 1) + "/" + year;
                switch (ID_BTN) {
                    case R.id.btnFrom:
                        txtFrom.setText(value);
                        curFromYear = year;
                        curFromMonth = monthOfYear;
                        curFromDay = dayOfMonth;
                        break;
                    case R.id.btnTo:
                        txtTo.setText(value);
                        curToYear = year;
                        curToMonth = monthOfYear;
                        curToDay = dayOfMonth;
                        break;
                    default:
                        break;
                }

            }
        };

        switch (ID_BTN) {
            case R.id.btnFrom:
                pic = new DatePickerDialog(
                        this,
                        callback, curFromYear, curFromMonth, curFromDay);
                break;
            case R.id.btnTo:
                pic = new DatePickerDialog(
                        this,
                        callback, curToYear, curToMonth, curToDay);
                break;
            default:
                break;
        }

        pic.show();
    }

    private void addControls() {
        tableHeader = findViewById(R.id.tableHeader);
        tableBody = findViewById(R.id.tableBody);
        txtMaGV = findViewById(R.id.txtMaGV);
        txtFrom = findViewById(R.id.txtFrom);
        txtTo = findViewById(R.id.txtTo);
        btnFrom = findViewById(R.id.btnFrom);
        btnTo = findViewById(R.id.btnTo);
        btnTraCuu = findViewById(R.id.btnTraCuu);
    }

    public void addTableHeader() {


        tr = new TableRow(this);

        tr.setLayoutParams(new TableRow.LayoutParams(

                TableRow.LayoutParams.MATCH_PARENT,

                TableRow.LayoutParams.WRAP_CONTENT));

        for (String header : headers) {

            tv = new TextView(this);
            tv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
            tv.setPadding(0, 20, 0, 20);
            tv.setText(header);
            tv.setGravity(Gravity.CENTER);
            tr.addView(tv);  // Adding textView to tablerow.
        }
        tableBody.addView(tr);

    }

    public void addTableRow(String[] schedule) {
        tr = new TableRow(this);

        tr.setLayoutParams(new TableRow.LayoutParams(

                TableRow.LayoutParams.MATCH_PARENT,

                TableRow.LayoutParams.WRAP_CONTENT));

        for (String cell : schedule) {
            tv = new TextView(this);
            tv.setTextSize(10);
            tv.setPadding(0, 20, 0, 20);
            tv.setTextColor(Color.RED);
            tv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
            tv.setText(cell);
            tv.setGravity(Gravity.CENTER);
            tr.addView(tv);
        }

        tableBody.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
    }

    public void addTableBody() {

        for (String[] schedule : schedules) {

            tr = new TableRow(this);

            tr.setLayoutParams(new TableRow.LayoutParams(

                    TableRow.LayoutParams.MATCH_PARENT,

                    TableRow.LayoutParams.WRAP_CONTENT));

            for (String cell : schedule) {
                tv = new TextView(this);
                tv.setTextSize(10);
                tv.setPadding(0, 20, 0, 20);
                tv.setTextColor(Color.RED);
                tv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
                tv.setText(cell);
                tv.setGravity(Gravity.CENTER);
                tr.addView(tv);
            }

            tableBody.addView(tr, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }

    }
}
