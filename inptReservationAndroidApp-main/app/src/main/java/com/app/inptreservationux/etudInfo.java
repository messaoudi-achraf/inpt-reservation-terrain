package com.app.inptreservationux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.inptreservationux.databinding.ActivityEtudInfoBinding;

public class etudInfo extends AppCompatActivity {

    ActivityEtudInfoBinding binding ;
    etudiants e ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEtudInfoBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());

        Intent in = this.getIntent() ;
        if(in!=null){
            e = (etudiants)  in.getExtras().getParcelable("etudiant") ;
            binding.nom.setText(e.getNom());
            binding.prenom.setText(e.getPrenom());
            binding.cin.setText(e.getCin());
            binding.niv.setText(e.getNiv());
            binding.fil.setText(e.getFiliere());
            binding.prenomprof.setText(e.getPrenom());
            binding.tel.setText(e.getNumTel());
            binding.email.setText(e.getEmail());




        }

        Button modifer = (Button) findViewById(R.id.modifier) ;
        Button supprimer = (Button) findViewById(R.id.supprimer) ;


        supprimer.setOnClickListener(ev->{
            Session.removeEtud(e);
            GererEtudiant.arrAdapter.notifyDataSetChanged();
            Intent intent =new Intent(this , GererEtudiant.class) ;
            Toast.makeText(this , "l'étudiant a été bien supprimé !" , Toast.LENGTH_LONG).show();
            //startActivity(intent);
            super.onBackPressed();


        finish();
        });

        modifer.setOnClickListener(ev->{
            Intent intent = new Intent(this , ModifierEtudActivity.class) ;
            startActivity(intent);
        });


    }
}