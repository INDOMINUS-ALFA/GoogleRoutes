package com.example.googleroutes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText etSource;
EditText etDestination;
Button btn_Track;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btn_Track = findViewById(R.id.bt_track);
        btn_Track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get value from edit text
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                //Check condition
                if(sSource.equals("") && sDestination.equals("")) {
                    //Where both value blank
                    Toast.makeText(getApplicationContext(), "Enter both location", Toast.LENGTH_LONG).show();
                }else {
                    //Wher both value fill
                    //Daisplay track
                    DisplayTrack(sSource, sDestination);
                }
            }
        });
    }

    private void DisplayTrack(String sSource, String sDestination) {
        //If the device does not have a map installed then redirect it to play storte
        try{
            //When google map is instale
            //Initialize uri
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+ sSource+ "/" + sDestination);
            //initialize intent with view
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            //Set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);
        } catch (ActivityNotFoundException e){
            //When google map is not installed
            //Initialice uri
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            //Initialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //Set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);
        }
    }
}