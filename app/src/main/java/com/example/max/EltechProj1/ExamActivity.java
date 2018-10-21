package com.example.max.EltechProj1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ExamActivity extends MainActivity {
    private TextView titText;
    private TimeToExam tte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examtime);

        titText = findViewById(R.id.title);
        tte = new TimeToExam();

    }

    @Override
    protected void onResume() {
        super.onResume();
        tte.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        tte.cancel(true);
    }

    class TimeToExam extends AsyncTask<Void, Long, Void> {
        private Date exam, today;
        private Calendar calExam, calToday;
        private long subst = -1;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exam = StringToDate("2019-01-16 00:00:00");
            calExam = Calendar.getInstance();
            calExam.setTime(exam);
            today = new Date();
            calToday = Calendar.getInstance();
            calToday.setTime(today);
            subst = calExam.getTimeInMillis() - calToday.getTimeInMillis();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                while (subst != 0) {
                    today = new Date();
                    calToday.setTime(today);
                    subst = calExam.getTimeInMillis() - calToday.getTimeInMillis();
                    publishProgress(subst);
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            titText.setText("Exam");
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
            titText.setText(getTimeInFormat(values[0]));
        }

    }

    private String getTimeInFormat(long input) {
        String out="";

        out += input / (24*60*60*1000) + " days ";
        input = input % (24*60*60*1000);
        out += input / (60*60*1000) + " hours ";
        input %= (60*60*1000);
        out += input / (60*1000) + " minutes ";
        input %= (60*1000);
        out += input / (1000) + " seconds";

        return out;
    }

    private Date StringToDate(String date) {
        Date result = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            result = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return result;
    }

    @Override
    public void finish() {
        super.finish();
        tte.cancel(true);
    }
}
