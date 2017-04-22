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

import java.io.File;
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
                File dir = getFilesDir();
                File file = new File(dir, "database.txt");
                boolean deleted = file.delete();
                Toast toast = Toast.makeText(getApplicationContext(),deleted+"",Toast.LENGTH_SHORT);
                toast.show();
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
                Context context = getApplicationContext();
                CharSequence text = "Change this to next activity!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
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




    // hello
}
