package com.example.terence.uthere;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

public class TimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);


        final Button back = (Button) findViewById(R.id.TimeBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton();

            }
        });

        final Button next = (Button) findViewById(R.id.TimeNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButton();
            }
        });
    }

    public void backButton() {
        Intent intent = getIntent();
        double lat = intent.getDoubleExtra("lat", -37.911067);
        double lng = intent.getDoubleExtra("lng", 145.133091);

        Intent i = new Intent(this, DateActivity.class);
        i.putExtra("lat", lat);
        i.putExtra("lng", lng);

        startActivity(i);
    }

    public void nextButton() {

        Intent intent = getIntent();

        double lat = intent.getDoubleExtra("lat", -37.911067);
        double lng = intent.getDoubleExtra("lng", 145.133091);

        int year = intent.getIntExtra("year", 0);
        int month = intent.getIntExtra("month", 0);
        int day = intent.getIntExtra("day", 0);

        TimePicker tp = (TimePicker) findViewById(R.id.timePicker);

        int hour, min;

        hour = tp.getHour();
        min = tp.getMinute();

        EditText duration = (EditText) findViewById(R.id.durationText);
        String durationInput = String.valueOf(duration.getText());
        double durationDouble = Double.parseDouble(durationInput);
        durationDouble *= 60;

        //duration.getText();

        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("lat", lat);
        i.putExtra("lng", lng);
        i.putExtra("hour", hour);
        i.putExtra("min", min);
        i.putExtra("year", year);
        i.putExtra("month", month);
        i.putExtra("day", day);
        i.putExtra("dur", durationDouble);

        String s = lat + " " + lng + " " + hour + " " + min + " " + year + " " + month + " " + day + " " + durationDouble;
        Context context = getApplicationContext();
        CharSequence text = s;
        int d = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, s, d);
        toast.show();

        //startActivity(i);
    }
}
