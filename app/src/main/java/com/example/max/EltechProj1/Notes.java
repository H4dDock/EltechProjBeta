package com.example.max.EltechProj1;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;


public class Notes extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        TextView titText = findViewById(R.id.title);
        titText.setText("Notes");
    }

    public void onSettingsMenuClick2(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.idschedule:
                Intent intent = new Intent (this, Schedule.class);
                startActivity(intent);
                break;
            case R.id.idnotes:
                Intent intent3 = new Intent (this, Notes.class);
                startActivity(intent3);
                break;
        }
    }
}