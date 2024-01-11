package com.app.inptreservationux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.app.Dialog;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Button geretudbtn , gerTerBtn ;
        geretudbtn= findViewById(R.id.geretudbtn) ;
        gerTerBtn = findViewById(R.id.gerterbtn) ;
        geretudbtn.setOnClickListener(e->{
            Intent in = new Intent(this , GererEtudiant.class) ;
            startActivity(in);
        });

        gerTerBtn.setOnClickListener(e->{
            Intent in = new Intent(this , GererTerrains.class) ;
            startActivity(in);
        });
        Button logout = (Button) findViewById(R.id.logout) ;
        logout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    showCustomDialog();
                }

        });

    }

    void showCustomDialog() {
        final Dialog dialog = new Dialog(Admin.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.logout_layout);

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.roundedpop);

        //Initializing the views of the dialog.

        Button ouibtn = dialog.findViewById(R.id.oui);
        Button annuler = dialog.findViewById(R.id.annuler) ;


        ouibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Admin.this , LoginActivity.class) ;
                startActivity(in);
                finish();
                dialog.dismiss();
            }
        });
        annuler.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           dialog.dismiss();
                                       }
                                   }

        );

        dialog.show();
    }

    private boolean isBackPressedOnce = false ;

    @Override
    public void onBackPressed() {


        if (isBackPressedOnce) {
            super.onBackPressed();
            return;
        }

        Toast.makeText(this, "Press again to exit!!", Toast.LENGTH_SHORT).show();
        isBackPressedOnce = true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressedOnce = false;
            }
        }, 2000);

    }



}