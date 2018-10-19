package com.example.max.EltechProj1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.max.EltechProj1.adapter.TabsPagerFragmentAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_main;
    private TabsPagerFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        InitTabs();
        SetDay(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)%2);

    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu);
    }

    private void InitTabs() {
        ViewPager viewPager = findViewById(R.id.viewPager);

        adapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        int setDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        if(setDay != 1){
            viewPager.setCurrentItem(setDay-2);
        }

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        adapter.updateData(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)%2);

    }

    private void initNavigationView(){
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

    }

    public void onSettingsMenuClick(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.unused_button:
                adapter.updateData(0);
                SetDay(0);
                break;
            case R.id.unused_button1:
                adapter.updateData(1);
                SetDay(1);
                break;
            case R.id.schedule:
                break;
        }

    }

    private void SetDay(int weekParity) {
        TextView dayText = findViewById(R.id.dayText);

        String dayWeek = "";

        switch(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)){
            case 1:
                dayWeek = "Вс";
                break;
            case 2:
                dayWeek = "Пн";
                break;
            case 3:
                dayWeek = "Вт";
                break;
            case 4:
                dayWeek = "Ср";
                break;
            case 5:
                dayWeek = "Чт";
                break;
            case 6:
                dayWeek = "Пт";
                break;
            case 7:
                dayWeek = "Сб";
                break;
        }


        String date = "Сегодня "+ dayWeek + new SimpleDateFormat(" dd.MM", Locale.US).format(new Date()) +
                " (Выбрана " + (weekParity == 0 ? "четная" : "нечетная")
                + " неделя)";
        dayText.setText(date);
    }
}

