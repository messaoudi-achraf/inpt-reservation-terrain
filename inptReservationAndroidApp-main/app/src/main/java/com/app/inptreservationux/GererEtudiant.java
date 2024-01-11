package com.app.inptreservationux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.app.inptreservationux.databinding.ActivityGererEtudiantBinding;
import com.app.inptreservationux.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;



public class GererEtudiant extends AppCompatActivity  {


    ArrayList<etudiants> filtred = new ArrayList<>() ;
    EditText search ;
    ArrayList<etudiants > arr = new ArrayList<>() ;
    static ListAdapter arrAdapter ;


    ActivityGererEtudiantBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        binding = ActivityGererEtudiantBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());



        String[] nom  = {"messaoudi" , "siffar" , "Omari" , "Agnaou","Abdessadek" } ;
        String[] prenom = {"Achraf" , "Soufiane" , "Jamal" , "Mohammed","Abdelmajid"} ;
        String[] cin  = {"WA297911" , "BA10000" , "AZ101122" , "RE124578","DA96890"} ;
        String[] niv  = {"INE2" , "INE2" , "INE2" , "INE2","INE2"} ;
        String[] fil  = {"AMOA" , "AMOA" , "AMOA" , "AMOA","AMOA"} ;
        String[] num = {"0611111111" , "0622114455" , "0788441122" , "0622114455","0688680212"} ;
        String[] email = {"keke.com" , "hehe.cem" , "lo.ma" , "eaeaza.com","deez.com"} ;








        arrAdapter  = new ListAdapter(this , Session.getetudiantsData()) ;
        binding.listview.setAdapter(arrAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
            Intent in = new Intent(GererEtudiant.this , etudInfo.class) ;
            System.out.println(Session.getetudiantsData().get(pos));
            in.putExtra("etudiant" , (Parcelable) Session.getetudiantsData().get(pos)) ;
            startActivity(in);

            }
        });



        Button addbtn = findViewById(R.id.addbtn)  ;


    addbtn.setOnClickListener(e->{

        Intent add = new Intent(this , addEtudiant.class) ;
        startActivity(add);
        finish();




    });





        search = findViewById(R.id.search) ;


       /* public void filter(String txt){

            for(String item : arr){
                if(item.equals(txt)){
                    filtred.add(item) ;
                }
            }
        } */
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().isEmpty()){
                    binding.listview.setAdapter( arrAdapter );
                }
                else{
                    filter(s.toString()) ;
                }
            }
        });





    }



    private void filter(String txt) {

        filtred.clear();

        for(etudiants etud : arr){
            if(etud.getCin().startsWith(txt)){
                filtred.add(etud) ;
            }
        }
        ListAdapter arrAdapter  = new ListAdapter(this , filtred) ;
        binding.listview.setAdapter(arrAdapter );
    }




    /*


    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_add_etudiant, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);



        // dismiss the popup window when touched

      popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });


    }
    */





}