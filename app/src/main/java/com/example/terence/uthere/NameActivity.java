package com.example.terence.uthere;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

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


        String s = nameInput + "\t" + lat + "\t" + lng + "\t" + hour + "\t" + min + "\t" + year
                + "\t" + month + "\t" + day + "\t" + dur + "\t" + statusInput + "\n";
        Context context = getApplicationContext();

        int d = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, "Checked in!", d);
        toast.show();

        //Store information to file
        String filename = "database.txt";
        File file = new File(getFilesDir(), filename);
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, MODE_APPEND);
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        InputStream in = null;
        try {
            in = openFileInput(filename);
            Scanner input = new Scanner(in);

            StringBuilder txt = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    txt.append(line);
                    txt.append('\n');
                }
                br.close();
            }
            catch (IOException e) {
                //You'll need to add proper error handling here
            }
            System.out.println(txt.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }
}
