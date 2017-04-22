package com.example.terence.uthere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Date;

public class DateActivity extends AppCompatActivity {

    int year, month, day;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        Date date = new Date();

        datePicker.setMinDate(date.getTime());

        Button nextButton = (Button) findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //action on click
                year = datePicker.getYear();
                month = datePicker.getMonth();
                day = datePicker.getDayOfMonth();


                nextActivity();
            }
        });

        Button backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });
    }

    public void nextActivity() {

        Intent intent = getIntent();

        double lat = intent.getDoubleExtra("lat", -37.911067);
        double lng = intent.getDoubleExtra("lng", 145.133091);

        Intent i = new Intent(this, TimeActivity.class);

        i.putExtra("year", year);
        i.putExtra("month", month);
        i.putExtra("day", day);

        i.putExtra("lat", lat);
        i.putExtra("lng", lng);

        startActivity(i);
    }

    public void backActivity() {
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }
}
