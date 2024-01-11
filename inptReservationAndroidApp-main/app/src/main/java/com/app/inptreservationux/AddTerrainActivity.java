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
import android.widget.Toast;

public class AddTerrainActivity extends AppCompatActivity {

    String[] sportsArr = {"Football" , "BasketBall" , "VolleyBall"} ;
    ArrayAdapter<String> spAdapter ;
    AutoCompleteTextView sport ;
    String sp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_terrain);

    EditText idter = (EditText) findViewById(R.id.idTer) ;
        sport = (AutoCompleteTextView) findViewById(R.id.sportTer) ;
        spAdapter = new ArrayAdapter<>(this , R.layout.menu_item , sportsArr) ;
        sport.setAdapter(spAdapter);
        sport.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sp = adapterView.getItemAtPosition(i).toString() ;
                sport.setText(sp);
            }
        });

        Button annuler = (Button) findViewById(R.id.annuler) ;
        annuler.setOnClickListener(e->{
            Intent intent = new Intent(this , GererTerrains.class) ;
            //  Session.addEtud(e) ;

            startActivity(intent);
            finish();
        });
        Button ajouter = (Button) findViewById(R.id.ajouter) ;
        ajouter.setOnClickListener(e->{
            Session.addTerrain(new Terrain(idter.getText().toString() , sp)  );
            Intent in = new Intent(this , GererTerrains.class) ;
            Toast.makeText(this , "le terrain a été bien ajouté !" , Toast.LENGTH_LONG).show();
            startActivity(in);
            finish();

        });


    }
}