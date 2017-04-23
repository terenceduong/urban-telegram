// key: AIzaSyDo_PKtztHRmM8N2qhpUhZym40LtGtAiSw

package com.example.terence.uthere;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.InfoWindowAdapter {

    private GoogleMap mMap;
    private ArrayList<Marker> markers = new ArrayList<Marker>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Button deleteButton = (Button) findViewById(R.id.btnDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFile();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng startPosition = new LatLng(-37.911067, 145.133091);


        Marker temp;
        temp = mMap.addMarker(new MarkerOptions().position(startPosition).title("Click to drop a pin!"));
        markers.add(temp);
        temp.showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLng(startPosition));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));

        mMap.setOnMapClickListener(this);



    }

    public void onMapClick(LatLng point) {
        clearMarkers();
        final Marker temp = mMap.addMarker(new MarkerOptions().position(point).title("Nice memes"));
        markers.add(temp);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(point), 750, null);

        temp.showInfoWindow();
        final Button checkInButton = (Button) findViewById(R.id.btnCheckIn);
        final Button cancelButton = (Button) findViewById(R.id.btnCancel);
        checkInButton.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.VISIBLE);
        checkInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                sampleToast();
                passActivity(temp);

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clearMarkers();
                checkInButton.setVisibility(View.INVISIBLE);
                cancelButton.setVisibility(View.INVISIBLE);
            }
        });

        checkNearby(point);

    }


    public View getInfoWindow(Marker m) {
        Marker a;
        return null;
    }

    public View getInfoContents(Marker m) {

        return null;
    }

    public void passActivity(Marker m){
        Intent i = new Intent(this , DateActivity.class);

        i.putExtra("lat" , m.getPosition().latitude);
        i.putExtra("lng" , m.getPosition().longitude);

        startActivity(i);
    }

    public void clearMarkers() {
        for (int i = 0; i < markers.size(); i++) {
            markers.get(i).remove();
        }
        markers.clear();
    }

    public void sampleToast() {
        Context context = getApplicationContext();
        CharSequence text = "Change this to next activity!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void resetFile() {
        String filename = "database.txt";
        File dir = getFilesDir();
        File file = new File(dir, filename);
        boolean deleted = file.delete();
        Toast toast = Toast.makeText(getApplicationContext(),deleted+"",Toast.LENGTH_SHORT);
        toast.show();

//        lat + "\t" + lng + "\t" + hour + "\t" + min + "\t" + year + "\t" + month + "\t" + day + "\t" + durationDouble;
        String string = "Bob    -37.911478  145.133083  15   0   2017    04  23  2\n" +
                "Jane\t-37.9110378\t145.1330417\t17\t0\t2017\t04\t23\t2\tSunbaking\n" +
                "Trent\t-37.9110378\t145.1330417\t16\t0\t2017\t04\t23\t2\tFooty\n" +
                "Greg\t-37.9110378\t145.1330417\t15\t0\t2017\t04\t23\t2\tStudying\n" +
                "John\t-37.9110378\t145.1330417\t22\t0\t2017\t04\t23\t8\tSleeping\n" +
                "Terence\t-37.909555\t145.133820\t9\t0\t2017\t04\t22\t30\tHackathon\n" +
                "Phillip\t-37.909555\t145.133820\t9\t0\t2017\t04\t22\t30\tHackathon\n" +
                "Sanya\t-37.909555\t145.133820\t9\t0\t2017\t04\t22\t30\tHackathon\n" +
                "John\t-37.909555\t145.133820\t9\t0\t2017\t04\t22\t30\tHackathon\n" +
                "Sarah\t-37.911074\t145.132848\t13\t0\t2017\t04\t23\t3\tPartying\n" +
                "Susie\t-37.911074\t145.132848\t13\t0\t2017\t04\t23\t3\tPartying\n" +
                "Steph\t-37.911074\t145.132848\t13\t0\t2017\t04\t23\t3\tPartying\n";
        FileOutputStream outputStream;
        System.out.println(getFilesDir());
        try {
            outputStream = openFileOutput(filename, MODE_PRIVATE);
            outputStream.write(string.getBytes());
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
            } catch (IOException e) {
            //You'll need to add proper error handling here
            }
            System.out.println(txt.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // checks for people nearby
    public void checkNearby(LatLng point) {
        try {
            String filename = "database.txt";
            File dir = getFilesDir();
            File file = new File(dir, filename);
            InputStream in = null;
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
            } catch (IOException e) {
                //You'll need to add proper error handling here
            }
            System.out.println(txt.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // hello
}
