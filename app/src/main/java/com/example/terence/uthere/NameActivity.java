package com.example.terence.uthere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        final Button back = (Button) findViewById(R.id.BackButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton();

            }
        });

        final Button next = (Button) findViewById(R.id.NextButton);
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

        int year = intent.getIntExtra("year", 0);
        int month = intent.getIntExtra("month", 0);
        int day = intent.getIntExtra("day", 0);

        Intent i = new Intent(this, TimeActivity.class);

        i.putExtra("lat", lat);
        i.putExtra("lng", lng);
        i.putExtra("year", year);
        i.putExtra("month", month);
        i.putExtra("day", day);

        startActivity(i);
    }

    public void nextButton() {

        Intent intent = getIntent();

        double lat = intent.getDoubleExtra("lat", -37.911067);
        double lng = intent.getDoubleExtra("lng", 145.133091);

        int year = intent.getIntExtra("year", 0);
        int month = intent.getIntExtra("month", 0);
        int day = intent.getIntExtra("day", 0);

        int hour = intent.getIntExtra("hour", 0);
        int min = intent.getIntExtra("min", 0);

        double dur = intent.getDoubleExtra("dur", 0);

        EditText name = (EditText) findViewById(R.id.NameInput);
        String nameInput = String.valueOf(name.getText());

        EditText status = (EditText) findViewById(R.id.StatusInput);
        String statusInput = String.valueOf(status.getText());

        //Store information to file

        int d = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), lat + " " + lng + " " + year + " " + month + " " + day + " " + hour
        + " " + min + " " + dur + " " + nameInput + " " + statusInput, d);
        toast.show();
    }
}
