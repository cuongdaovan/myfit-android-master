package com.example.thuca.myfit.helpers;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.thuca.myfit.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APIHelper {

    private String API_URL = "https://myfitapi.nddcoder.com/v1";

    private Context context;
    private String token;
    private Debug debug;

    public APIHelper(Context context, String token) {
        this.context = context;
        debug = new Debug(context);
        this.token = token;
    }

    public void searchSchedule(String maGV, String startTime, String endTime, Response.Listener<JSONObject> onResponse, Response.ErrorListener onError) {

        String url = API_URL + "/search/schedule?" +
                "teacher_code=" + maGV +
                "&start_time=" + startTime +
                "&end_time=" + endTime;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, onResponse, onError) {
            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        Volley.newRequestQueue(this.context).add(jsonObjectRequest);
    }

    private Map<String, String> getHeader() {
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", this.token);
        return map;
    }
}
