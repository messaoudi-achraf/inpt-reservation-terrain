package com.app.inptreservationux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button connecter ;
        connecter = (Button) findViewById(R.id.connecter) ;
        EditText log ;
        log = (EditText) findViewById(R.id.username_input)  ;
        EditText pass ;
        pass = (EditText) findViewById(R.id.pass_input)  ;
        TextView err = (TextView) findViewById(R.id.error) ;

        Button messenger = findViewById(R.id.messenger) ;
        messenger.setOnClickListener(e->{
            gotUrl("https://www.messenger.com/t/100087141325865");
        });

        Button whatsapp = findViewById(R.id.wtsp) ;
        whatsapp.setOnClickListener(e->{
            gotUrl("https://wa.me/212614582811");
        });

        Button gmail = findViewById(R.id.gmail) ;
        gmail.setOnClickListener(e->{
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "comitesportcontact@gmail.com" });
            intent.putExtra(Intent.EXTRA_SUBJECT, "Un probléme dans l'app de réservation");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });

        Button phone = findViewById(R.id.phone) ;
        phone.setOnClickListener(e->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:212614582811"));
            startActivity(intent);
        });






        connecter.setOnClickListener(e->{

            System.out.println( pass.getText().toString().equals("inpt")  );
            System.out.println( log.getText()+ " " + pass.getText() );



            if (log.getText().toString().isEmpty() || pass.getText().toString().isEmpty() ){
                err.setText(" enter shy qalwa!");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        err.setText("");
                    }

                }, 3000);
            }


            if(log.getText().toString().equals("appmasterinpt@inpt.ac.ma") && pass.getText().toString().equals("i"))
            {



                Intent in = new Intent(this , Admin.class) ;
                startActivity(in);
                finish();
            }else if (Session.checkData(log.getText().toString() , pass.getText().toString())){

                Intent in = new Intent(this , Etudiant.class) ;
                startActivity(in);
                finish();

            }else{
                if(log.getText().toString().isEmpty()){
                    log.setError("Entrer votre email ! ");
                }

                if(pass.getText().toString().isEmpty()){
                    pass.setError("Entrer votre mot de passe ! ");
                }
                else{

                    err.setText("email ou mot de passe incorrect !");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            err.setText("");
                        }

                    }, 3000);

                }



            }
            log.setText("");
            pass.setText("");




        });




    }

    private void gotUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
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