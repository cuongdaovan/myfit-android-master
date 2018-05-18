package com.example.thuca.myfit.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ViewFlipper;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.thuca.myfit.MainActivity;
import com.example.thuca.myfit.R;
import com.example.thuca.myfit.ScheduleActivity;
import com.example.thuca.myfit.adapters.MainMenuAdapter;
import com.example.thuca.myfit.helpers.Debug;
import com.example.thuca.myfit.models.ItemClickSupport;
import com.example.thuca.myfit.models.MainMenuItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    public static String TAG = "HomeFragment";

    ViewFlipper viewFlipper;
    RecyclerView rvMainMenu;
    List<MainMenuItem> menuItems;
    MainMenuAdapter mainMenuAdapter;
    Debug debug;

    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.context = view.getContext();


        debug = new Debug(this.context);

        viewFlipper = view.findViewById(R.id.viewlipper);

        rvMainMenu = view.findViewById(R.id.rv_main_menu);
        rvMainMenu.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this.context, 3);
        rvMainMenu.setLayoutManager(layoutManager);
        menuItems = MainMenuItem.createMenuItems();
        mainMenuAdapter = new MainMenuAdapter(menuItems, this.context);
        rvMainMenu.setAdapter(mainMenuAdapter);

        debug.toast("View created");

        ItemClickSupport.addTo(rvMainMenu).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                switch (menuItems.get(position).getId()) {
                    case "search":
                        clickSearch();
                        break;
                }
            }
        });

        ActionViewFlipper(this.context);
    }

    private void clickSearch() {

        final AutoCompleteTextView autoMaGV;
        Spinner spinnerThang, spinnerNam;

        MaterialDialog dialog =
                new MaterialDialog.Builder(this.context)
                        .title("Tra cuu")
                        .customView(R.layout.search_dialog, true)
                        .positiveText("Tra cuu")
                        .negativeText(android.R.string.cancel)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                if (dialog.getCustomView() != null) {
                                    View view = dialog.getCustomView();
                                    AutoCompleteTextView autoMaGV = view.findViewById(R.id.autoMaGV);
                                    Spinner spinnerThang = view.findViewById(R.id.spinnerMonth);
                                    Spinner spinnerNam = view.findViewById(R.id.spinnerYear);

                                    Intent intent = new Intent(context, ScheduleActivity.class);
                                    intent.putExtra("maGV", autoMaGV.getText().toString());
                                    intent.putExtra("thang", Integer.parseInt(spinnerThang.getSelectedItem().toString()) - 1);
                                    intent.putExtra("nam", Integer.parseInt(spinnerNam.getSelectedItem().toString()));

                                    startActivity(intent);
                                }


                            }
                        })
                        .build();

        if (dialog.getCustomView() != null) {

            View view = dialog.getCustomView();

            String[] dsGiangVien = this.context.getResources().getStringArray(R.array.list_giang_vien);
            String[] dsThang = this.context.getResources().getStringArray(R.array.list_thang);
            String[] dsNam = new String[101];
            for(int i = 0; i <= 100; i++) { dsNam[i]  = 2000 + i + ""; }

            autoMaGV = view.findViewById(R.id.autoMaGV);
            spinnerThang = view.findViewById(R.id.spinnerMonth);
            spinnerNam = view.findViewById(R.id.spinnerYear);

            ArrayAdapter<String> dsGiangVienAdapter = new ArrayAdapter<>(this.context, android.R.layout.simple_list_item_1, dsGiangVien);
            ArrayAdapter<String> dsThangAdapter = new ArrayAdapter<>(this.context, android.R.layout.simple_list_item_1, dsThang);
            ArrayAdapter<String> dsNamAdapter = new ArrayAdapter<>(this.context, android.R.layout.simple_list_item_1, dsNam);

//            dsThangAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
//            dsNamAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

            autoMaGV.setAdapter(dsGiangVienAdapter);
            spinnerThang.setAdapter(dsThangAdapter);
            spinnerNam.setAdapter(dsNamAdapter);

            Calendar mCalendar = Calendar.getInstance();

            spinnerThang.setSelection(mCalendar.get(Calendar.MONTH));
            spinnerNam.setSelection(mCalendar.get(Calendar.YEAR) - 2000);
        }

        dialog.show();
    }

    private void ActionViewFlipper(Context context) {
        viewFlipper.removeAllViews();
        ArrayList<String> arrSlides = new ArrayList<>();

        arrSlides.add("https://nddcoder.com/storage/slide/slide_2.jpg");
        arrSlides.add("https://nddcoder.com/storage/slide//152473534485521816.jpeg");
        arrSlides.add("https://nddcoder.com/storage/slide/slide_0.png");

        for(int i=0;i<arrSlides.size();i++)
        {
            ImageView imageView = new ImageView(context);
            Picasso.with(context).load(arrSlides.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.startFlipping();
        Animation animation_slide_in = AnimationUtils.loadAnimation(context,R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(context,R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }
}
