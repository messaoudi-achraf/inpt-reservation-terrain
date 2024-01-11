package com.app.inptreservationux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.app.inptreservationux.databinding.ActivityGererEtudiantBinding;

import java.util.ArrayList;

public class GererTerrains extends AppCompatActivity {

    ArrayList<Terrain> filtred = new ArrayList<>() ;
    EditText search ;
    ArrayList<Terrain > arr = new ArrayList<>() ;
    static TerrainAdapter arrAdapter ;


    ActivityGererEtudiantBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGererEtudiantBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());



        arrAdapter  = new TerrainAdapter(this , Session.getTerData()) ;
        binding.listview.setAdapter(arrAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent in = new Intent(GererTerrains.this , TerrainInfoActivity.class) ;
                in.putExtra("Terrain" , (Parcelable) Session.getTerData().get(pos)) ;
                startActivity(in);

            }
        });

        Button addbtn = findViewById(R.id.addbtn)  ;


        addbtn.setOnClickListener(e->{

            Intent add = new Intent(this , AddTerrainActivity.class) ;
            startActivity(add);





        });

        EditText search = (EditText) findViewById(R.id.search) ;






    }
}