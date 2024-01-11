package com.app.inptreservationux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class addEtudiant extends AppCompatActivity {

    String[] niveau = { "INE1", "INE2", "INE3"};
    String[] filiere = { "AMOA", "DATA", "ICCN" , "CLOUD" };
    ArrayList<String> arrniv = new ArrayList<>() ;
    ArrayList<String> arrfil = new ArrayList<>() ;
    ArrayAdapter<String> arrAdapNiv  ;
    ArrayAdapter<String> arrAdapFil  ;
    AutoCompleteTextView autoNiv , autoFil ;
    String niv , fil ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_etudiant);
        for(int i =0 ; i<niveau.length ; i++){
            arrniv.add(niveau[i]) ;
            arrfil.add(filiere[i]) ;
        }
        autoFil = (AutoCompleteTextView) findViewById(R.id.autoFil) ;
        arrAdapFil = new ArrayAdapter<>(this , R.layout.menu_item , filiere) ;
        autoFil.setAdapter(arrAdapFil);
        autoFil.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                fil = adapterView.getItemAtPosition(i).toString() ;
                autoFil.setText(fil);
            }
        });
        autoNiv = (AutoCompleteTextView) findViewById(R.id.autoNiv) ;
        arrAdapNiv = new ArrayAdapter<>(this , R.layout.menu_item , niveau) ;
        autoNiv.setAdapter(arrAdapNiv);
        autoNiv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                niv = adapterView.getItemAtPosition(i).toString() ;
                autoNiv.setText(niv);
            }
        });






        EditText nom = (EditText) findViewById(R.id.nom) ;
        EditText prenom = (EditText) findViewById(R.id.prenom) ;
        EditText cin = (EditText) findViewById(R.id.cin) ;
        EditText tel = (EditText) findViewById(R.id.tel) ;
        EditText email = (EditText) findViewById(R.id.email) ;
        EditText pass = (EditText) findViewById(R.id.pass) ;
        Button annuler = (Button) findViewById(R.id.annuler) ;
        annuler.setOnClickListener(e->{
            Intent intent = new Intent(this , GererEtudiant.class) ;
            //  Session.addEtud(e) ;
            startActivity(intent);
            finish();
        });

      Button ajouter = (Button) findViewById(R.id.ajouter) ;
        ajouter.setOnClickListener(ev->{
            etudiants e = new etudiants(nom.getText().toString() , prenom.getText().toString() , cin.getText().toString() ,fil ,niv, tel.getText().toString() , email.getText().toString() ,pass.getText().toString()) ;

            Session.addEtud(e) ;
            GererEtudiant.arrAdapter.notifyDataSetChanged();
            Toast.makeText(this , "l'étudiant a été bien ajouté !" , Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this , GererEtudiant.class) ;
            startActivity(intent);
            finish();
                }

        );


    }




}