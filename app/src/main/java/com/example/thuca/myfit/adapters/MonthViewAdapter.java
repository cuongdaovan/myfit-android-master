package com.example.thuca.myfit.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thuca.myfit.R;
import com.example.thuca.myfit.helpers.Debug;
import com.example.thuca.myfit.helpers.Util;
import com.example.thuca.myfit.models.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class MonthViewAdapter extends RecyclerView.Adapter<MonthViewAdapter.ViewHolder> {
    private GregorianCalendar mCalendar;
    private Calendar mCalendarToday;
    private Context mContext;
    private List<String> mItems;
    private int mMonth;
    private int mYear;
    private int mDaysShown;
    private int mDaysLastMonth;
    private int mDaysNextMonth;
    private final String[] mDays = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
    private final int[] mDaysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private ArrayList<Day> dayData;

    Debug debug;

    private int selectedPos = -1;

    public MonthViewAdapter(Context context, int month, int year, ArrayList<Day> dayData) {
        mContext = context;
        mMonth = month;
        mYear = year;
        mCalendar = new GregorianCalendar(mYear, mMonth, 1);
        mCalendarToday = Calendar.getInstance();
        this.dayData = dayData;
        populateMonth();
        debug = new Debug(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.custom_calendar_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        holder.text.setText(mItems.get(position));
        holder.text.setTextColor(Color.BLACK);

        int[] date = getDate(position);

        //date == null => hàng tiêu đề
        if (date == null) {
            holder.layout.setBackgroundResource(0);
            holder.text.setTypeface(holder.text.getTypeface(), Typeface.BOLD);
            holder.period_1.setVisibility(Util.getVisibility(false));
            holder.period_2.setVisibility(Util.getVisibility(false));
            holder.period_3.setVisibility(Util.getVisibility(false));
            holder.period_4.setVisibility(Util.getVisibility(false));
            holder.period_5.setVisibility(Util.getVisibility(false));

            return;
        }

        if (position == selectedPos) {
//            debug.toast(String.valueOf(selectedPos + " " + mItems.get(position)));
            holder.layout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gray));
        }

        //không phải ngày của tháng này => màu xám
        if (!Util.resolveDate(date[1], mMonth)) holder.text.setTextColor(Color.GRAY);

        for (Day day: dayData) {
            if (Util.dateEqual(date, day.getDay(), day.getMonth(), day.getYear())) {
                holder.period_1.setVisibility(Util.getVisibility(day.getPeriod_1().size() > 0));
                holder.period_2.setVisibility(Util.getVisibility(day.getPeriod_2().size() > 0));
                holder.period_3.setVisibility(Util.getVisibility(day.getPeriod_3().size() > 0));
                holder.period_4.setVisibility(Util.getVisibility(day.getPeriod_4().size() > 0));
                holder.period_5.setVisibility(Util.getVisibility(day.getPeriod_5().size() > 0));
            }
        }



        if (isToday(date[0], date[1], date[2])) {
            if (selectedPos == -1) {
                holder.layout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gray));
            }

            holder.text.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        }
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    public void setSelectedPos(int pos) {
        this.selectedPos = pos;
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }


    private void populateMonth() {
        mItems = new ArrayList<String>();
        for (String day : mDays) {
            mItems.add(day);
            mDaysShown++;
        }

        int firstDay = Util.getDay(mCalendar.get(Calendar.DAY_OF_WEEK));
        int prevDay;
        if (mMonth == 0)
            prevDay = daysInMonth(11) - firstDay + 1;
        else
            prevDay = daysInMonth(mMonth - 1) - firstDay + 1;
        for (int i = 0; i < firstDay; i++) {
            mItems.add(String.valueOf(prevDay + i));
            mDaysLastMonth++;
            mDaysShown++;
        }

        int daysInMonth = daysInMonth(mMonth);
        for (int i = 1; i <= daysInMonth; i++) {
            mItems.add(String.valueOf(i));
            mDaysShown++;
        }

        mDaysNextMonth = 1;
        while (mDaysShown % 7 != 0) {
            mItems.add(String.valueOf(mDaysNextMonth));
            mDaysShown++;
            mDaysNextMonth++;
        }
    }

    private int daysInMonth(int month) {
        int daysInMonth = mDaysInMonth[month];
        if (month == 1 && mCalendar.isLeapYear(mYear))
            daysInMonth++;
        return daysInMonth;
    }

    private boolean isToday(int day, int month, int year) {
        return mCalendarToday.get(Calendar.MONTH) == month
                && mCalendarToday.get(Calendar.YEAR) == year
                && mCalendarToday.get(Calendar.DAY_OF_MONTH) == day;
    }

    public int[] getDate(int position) {
        int date[] = new int[3];
        if (position <= 6) {
            return null; // text names
        } else if (position <= mDaysLastMonth + 6) {
            // previous month
            date[0] = Integer.parseInt(mItems.get(position));
            if (mMonth == 0) {
                date[1] = 11;
                date[2] = mYear - 1;
            } else {
                date[1] = mMonth - 1;
                date[2] = mYear;
            }
        } else if (position <= mDaysShown - mDaysNextMonth  ) {
            // current month
            date[0] = position - (mDaysLastMonth + 6);
            date[1] = mMonth;
            date[2] = mYear;
        } else {
            // next month
            date[0] = Integer.parseInt(mItems.get(position));
            if (mMonth == 11) {
                date[1] = 0;
                date[2] = mYear + 1;
            } else {
                date[1] = mMonth + 1;
                date[2] = mYear;
            }
        }
        return date;
    }

    public List<String> getmItems() {
        return mItems;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout;
        TextView text, period_1, period_2, period_3, period_4, period_5;
//        ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
//            img = itemView.findViewById(R.id.img);
            layout = itemView.findViewById(R.id.calendar_item_layout);
            text = itemView.findViewById(R.id.text);
            period_1 = itemView.findViewById(R.id.period_1);
            period_2 = itemView.findViewById(R.id.period_2);
            period_3 = itemView.findViewById(R.id.period_3);
            period_4 = itemView.findViewById(R.id.period_4);
            period_5 = itemView.findViewById(R.id.period_5);
        }
    }

}
