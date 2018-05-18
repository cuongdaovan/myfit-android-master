package com.example.thuca.myfit.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thuca.myfit.R;
import com.example.thuca.myfit.models.Schedule;

import java.util.List;

/**
 * Created by THINKPAD on 09-May-18.
 */

public class DetailsAdapter extends BaseAdapter {


    private List<Schedule> schedules;
    private Activity context;

    public DetailsAdapter(List<Schedule> schedules, Activity context) {
        this.schedules = schedules;
        this.context = context;
    }

    @Override
    public int getCount() {
        return schedules.size();
    }

    @Override
    public Schedule getItem(int position) {
        return schedules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView =context.getLayoutInflater().inflate(R.layout.schedule_item,null);
            viewHolder=new ViewHolder();
            viewHolder.thoigian=convertView.findViewById(R.id.thoigian);
            viewHolder.monhoc=convertView.findViewById(R.id.monhoc);
            viewHolder.giangvien=convertView.findViewById(R.id.giangvien);
            viewHolder.layout=(LinearLayout)convertView.findViewById(R.id.schedule_item_layout);


            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        Schedule schedule=getItem(position);

        if(schedule.getCa()==1 && schedule.getTeacher_code()==null){
            viewHolder.layout.setBackgroundColor(Color.rgb(32,90,167));
            viewHolder.layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 10));
//            viewHolder.layout.getLayoutParams().height= 20;
//            viewHolder.layout.requestLayout();
        }
        else  if(schedule.getCa()==2 && schedule.getTeacher_code()==null){
            viewHolder.layout.setBackgroundColor(Color.rgb(110,195,201));
            viewHolder.layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 10));
//            viewHolder.layout.getLayoutParams().height= 20;
//            viewHolder.layout.requestLayout();
        }
        else if(schedule.getCa()==3 && schedule.getTeacher_code()==null){
            viewHolder.layout.setBackgroundColor(Color.rgb(252,245,76));
            viewHolder.layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 10));
//            viewHolder.layout.getLayoutParams().height= 20;
//            viewHolder.layout.requestLayout();
        }
        else if(schedule.getCa()==4 && schedule.getTeacher_code()==null){
            viewHolder.layout.setBackgroundColor(Color.rgb(183,183,183));
            viewHolder.layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 10));
//            viewHolder.layout.getLayoutParams().height= 20;
//            viewHolder.layout.requestLayout();
        }
        else if(schedule.getCa()==5 && schedule.getTeacher_code()==null){
            viewHolder.layout.setBackgroundColor(Color.rgb(197,124,172));
            viewHolder.layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 10));
//            viewHolder.layout.getLayoutParams().height= 20;
//            viewHolder.layout.requestLayout();
        }
        else {
            switch (schedule.getCa()){
                case 1:
                    viewHolder.thoigian.setTextColor(Color.rgb(32,90,167));
                    viewHolder.giangvien.setTextColor(Color.rgb(32,90,167));
                    viewHolder.monhoc.setTextColor(Color.rgb(32,90,167));
                    viewHolder.thoigian.setText("1 2 3");
                    break;
                case 2:
                    viewHolder.thoigian.setTextColor(Color.rgb(110,195,201));
                    viewHolder.giangvien.setTextColor(Color.rgb(110,195,201));
                    viewHolder.monhoc.setTextColor(Color.rgb(110,195,201));
                    viewHolder.thoigian.setText("4 5 6");
                    break;
                case 3:
                    viewHolder.thoigian.setTextColor(Color.rgb(199,195,0));
                    viewHolder.giangvien.setTextColor(Color.rgb(199,195,0));
                    viewHolder.monhoc.setTextColor(Color.rgb(199,195,0));
                    viewHolder.thoigian.setText("7 8 9");
                    break;
                case 4:
                    viewHolder.thoigian.setTextColor(Color.rgb(183,183,183));
                    viewHolder.giangvien.setTextColor(Color.rgb(183,183,183));
                    viewHolder.monhoc.setTextColor(Color.rgb(183,183,183));
                    viewHolder.thoigian.setText("10 11 12");
                    break;
                case 5:
                    viewHolder.thoigian.setTextColor(Color.rgb(197,124,172));
                    viewHolder.giangvien.setTextColor(Color.rgb(197,124,172));
                    viewHolder.monhoc.setTextColor(Color.rgb(197,124,172));
                    viewHolder.thoigian.setText("13 14 15");
                    break;

            }

            viewHolder.monhoc.setText(schedule.getSubject_name());
            viewHolder.giangvien.setText(schedule.getTeacher_code()+"  "+schedule.getRoom()+" "+schedule.getMerged_class());
        }

        return convertView;
    }

    public class  ViewHolder{
        TextView thoigian,giangvien,monhoc;
        LinearLayout layout;
    }
}
