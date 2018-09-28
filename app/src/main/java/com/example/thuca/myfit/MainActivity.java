package com.example.thuca.myfit;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

dao van cuong

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    NotificationFragment notificationFragment;
    AccountFragment accountFragment;

    boolean isBackPress = false;
    Debug debug;

    long lastBackPressTime = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        debug = new Debug(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
//                        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_left, R.anim.slide_out_left);

                        switch (item.getItemId()) {
                            case R.id.action_home:
                                hideNewsAndAccount(transaction);
                                transaction.show(homeFragment);

                                break;
                            case R.id.action_news:
                                hideHomeAndAccount(transaction);
                                transaction.show(notificationFragment);

                                break;
                            case R.id.action_account:
                                hideHomeAndNews(transaction);
                                transaction.show(accountFragment);
                                break;
                        }

                        transaction.commit();
                        return true;
                    }
                });

        //add notification badge
        BottomNavigationMenuView bt = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) bt.getChildAt(1);
        View badge = LayoutInflater.from(this).inflate(R.layout.notification_badge, bt, false);
        itemView.addView(badge);


        homeFragment = new HomeFragment();
        notificationFragment = new NotificationFragment();
        accountFragment = new AccountFragment();

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_layout, homeFragment);
        transaction.add(R.id.frame_layout, notificationFragment);
        transaction.add(R.id.frame_layout, accountFragment);
        hideNewsAndAccount(transaction);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {

        long curTime = new Date().getTime();

        if (curTime - lastBackPressTime > 3000) {
            debug.toast(this.getResources().getString(R.string.back_press_mesage));
            lastBackPressTime = curTime;
            return;
        }

        super.onBackPressed();
    }

    private void hideNewsAndAccount(FragmentTransaction transaction) {
        transaction.hide(notificationFragment);
        transaction.hide(accountFragment);
    }

    private void hideHomeAndAccount(FragmentTransaction transaction) {
        transaction.hide(homeFragment);
        transaction.hide(accountFragment);
    }

    private void hideHomeAndNews(FragmentTransaction transaction) {
        transaction.hide(homeFragment);
        transaction.hide(notificationFragment);
    }


}
