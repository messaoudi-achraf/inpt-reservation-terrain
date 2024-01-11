package com.app.inptreservationux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;
import java.sql.Timestamp;

public class MainActivity extends AppCompatActivity {
    private boolean isBackPressedOnce = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<etudiants> etudlog = new ArrayList<>() ;
        ArrayList<Terrain> TerData = new ArrayList<>() ;


        etudlog.add(new etudiants("Messaoudi" , "achraf" , "WA297911" , "INE2" , "AMOA" , "0614582811" , "achmess@gmail.com" , "azer12"));
        etudlog.add(new etudiants("agnaou" , "mohamed" , "BA114455" , "INE2" , "AMOA" , "0611447788" , "agnaou@gmail.com", "azer12"));
        etudlog.add(new etudiants("omari" , "jamal" , "A1122554" , "INE2" , "AMOA" , "0655887744" , "omari@gmail.com", "azer12"));
        etudlog.add(new etudiants("siffar" , "soufiane" , "Z1144552" , "INE2" , "AMOA" , "0788441122" , "siffar@gmail.com", "azer12"));


        TerData.add(new Terrain("F1" , "Football")) ;
        TerData.add(new Terrain("B1" , "BasketBall"));
        TerData.add(new Terrain("V1" , "VolleyBall"));
        ArrayList<Reservation> myRes = new ArrayList<>() ;

        myRes.add(new Reservation(new Timestamp(123 , 4 , 10 , 17 , 00 , 00,0) , new Timestamp(2023 , 5 , 10 , 19 , 00 , 00,0) , "WA297911" , "F1") ) ;
        myRes.add(new Reservation(new Timestamp(123 , 4 , 13 , 22 , 00 , 00,0) , new Timestamp(2023 , 5 , 10 , 19 , 00 , 00,0) , "WA297911" , "V1") ) ;
        myRes.add(new Reservation(new Timestamp(123 ,4 , 9 , 20, 30 , 00,0) , new Timestamp(2023 , 5 , 10 , 19 , 00 , 00,0) , "WA297911" , "F1") ) ;
        myRes.add(new Reservation(new Timestamp(123 , 4 , 8 , 17 , 00 , 00,0) , new Timestamp(2023 , 5 , 10 , 19 , 00 , 00,0) , "A1122554" , "B1") ) ;
        myRes.add(new Reservation(new Timestamp(123 , 4 , 10 , 19 , 00 , 00,0) , new Timestamp(2023 , 5 , 10 , 19 , 00 , 00,0) , "Z1144552" , "V1") ) ;
        myRes.add(new Reservation(new Timestamp(123 , 4 , 10 , 19 , 00 , 00,0) , new Timestamp(2023 , 5 , 10 , 19 , 00 , 00,0) , "WA297911" , "V1","Terminé") ) ;
        myRes.add(new Reservation(new Timestamp(123 , 4 , 10 , 19 , 00 , 00,0) , new Timestamp(2023 , 5 , 10 , 19 , 00 , 00,0) , "WA297911" , "V1","Terminé") ) ;
        myRes.add(new Reservation(new Timestamp(123 ,4 , 7 , 20, 42, 00,0) , new Timestamp(2023 , 5 , 10 , 19 , 00 , 00,0) , "WA297911" , "V1") ) ;



        Session.setetudiantsData(etudlog);
        Session.setTerrainData(TerData);
        Session.setResData(myRes);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startIntent = new Intent(MainActivity.this, ForegroundService.class);
                startService(startIntent);

                Intent mainintent = new Intent( MainActivity.this, LoginActivity.class ) ;
                startActivity(mainintent) ;
                finish();
            }

        }, 4000);



    }



}