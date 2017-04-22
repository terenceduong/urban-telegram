package com.example.terence.uthere;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.sql.Time;


/**
 * Created by sanyamehta on 22/04/17.
 */
/*
@TargetApi(24)
public class StatusActivity extends Activity {



    final View dialogView = View.inflate(activity, R.layout.activity_status, null);
    final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();

    dialogView.findViewById(R.id.date_time_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
                TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);

                Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                                        datePicker.getMonth(),
                                        datePicker.getDayOfMonth(),
                                        timePicker.getCurrentHour(),
                                        timePicker.getCurrentMinute());

                time = calendar.getTimeInMillis();
                alertDialog.dismiss();
            }});
    alertDialog.setView(dialogView);
    alertDialog.show();

}
*/