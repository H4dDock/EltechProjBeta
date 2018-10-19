package com.example.max.EltechProj1;

import android.os.AsyncTask;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeToExam extends AsyncTask<Void, Long, Void> {
    private Date exam, today;
    private Calendar calExam, calToday;
    private long subst = -1;
    private TextView tv;

    TimeToExam(TextView tv){
        this.tv = tv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        exam = StringToDate("2018-01-16 00:00:00");
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
                subst = calExam.getTimeInMillis() - calToday.getTimeInMillis();
                publishProgress(subst);
                Thread.sleep(1000);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        tv.setText("Exam");
    }

    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
        tv.setText(values[0]+"");
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

}