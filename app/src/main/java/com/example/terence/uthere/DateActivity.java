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
        setContentView(R.layout.activity_date_time);

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
                day = datePicker.getMonth();


                passActivity();
            }
        });
    }

    public void passActivity() {

        Intent intent = getIntent();

        double lat = intent.getDoubleExtra("Latitude", -37.911067);
        double lng = intent.getDoubleExtra("Longitude", 145.133091);

        Intent i = new Intent(this, TimeActivity.class);

        i.putExtra("Year", year);
        i.putExtra("Month", month);
        i.putExtra("Day", day);

        i.putExtra("Lat", lat);
        i.putExtra("Lng", lng);

        startActivity(i);
    }
}
