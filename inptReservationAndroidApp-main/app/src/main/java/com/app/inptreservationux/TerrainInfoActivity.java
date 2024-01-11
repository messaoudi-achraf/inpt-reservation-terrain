package com.app.inptreservationux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.app.inptreservationux.databinding.ActivityEtudInfoBinding;
import com.app.inptreservationux.databinding.ActivityTerrainInfoBinding;

public class TerrainInfoActivity extends AppCompatActivity {
    ActivityTerrainInfoBinding binding ;
    Terrain t ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTerrainInfoBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());

        Intent in = this.getIntent() ;
        if(in!=null){
            //e =  new etudiants(in.getParcelableExtra("etudiant")) ;
            t = (Terrain)  in.getExtras().getParcelable("Terrain") ;
            binding.idterr.setText(t.getId());
            binding.idTerFoq.setText(t.getId());
            binding.sport.setText(t.getSport());

            Button modifer = (Button) findViewById(R.id.modifier) ;
            Button supprimer = (Button) findViewById(R.id.supprimer) ;

            supprimer.setOnClickListener(ev->{

                Session.removeTer(t);
                GererTerrains.arrAdapter.notifyDataSetChanged();
                Intent intent =new Intent(this , GererTerrains.class) ;
                startActivity(intent);
                Toast.makeText(this , "le terrain a été bien supprimé !" , Toast.LENGTH_LONG).show();
                finish();
            });

            modifer.setOnClickListener(ev->{
                Intent intent = new Intent(this , ModifierEtudActivity.class) ;
                startActivity(intent);
            });



        }
    }
}