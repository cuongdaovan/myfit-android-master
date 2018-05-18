package com.example.thuca.myfit.helpers;

import android.content.Context;
import android.widget.Toast;

public class Debug {

    Context context;

    public Debug(Context context) {
        this.context = context;
    }

    public void toast(String mess) {
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }
}
