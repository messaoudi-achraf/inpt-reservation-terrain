package com.app.inptreservationux;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.inptreservationux.databinding.ActivityEtudInfoBinding;
import com.app.inptreservationux.databinding.ActivityEtudiantBinding;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Etudiant extends AppCompatActivity {

    ActivityEtudiantBinding binding ;
    ArrayList<Reservation> filtredRes ;
    EditText search ;

    static ExpandableListView expandableListView;
    static ExpandableListAdapter expandableListAdapter;
    static ExpandableListAdapter expandableListAdapterTer ;
    static ExpandableListAdapter expandableListAdapterCancel ;
    List<String> expandableListTitle;
    HashMap<String,List<Reservation>> expandableListDetail;



    static  ExpandableListView expandableListViewTer;

    List<String> expandableListTitleTer;
    HashMap<String,List<Reservation>> expandableListDetailTer;

    static ExpandableListView expandableListViewCancel;

    List<String> expandableListTitleCancel;
    HashMap<String,List<Reservation>> expandableListDetailCancel;

    String ter ;
    Timestamp datedeb  ;
    Timestamp datefin ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEtudiantBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());



        expandableListView = findViewById(R.id.expandableListView);

        expandableListDetail = Session.getresDataEncours(Session.getLoggedEtud());

        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());

        expandableListAdapter = new CustomExpandableListAdapter
                (this,expandableListTitle,expandableListDetail);

        expandableListView.setAdapter(expandableListAdapter);

        Button ref = findViewById(R.id.refresh) ;
        ref.setOnClickListener(e->{
            Intent in = new Intent(this , Etudiant.class) ;
            startActivity(in);
            finish();
        });



        expandableListViewTer = findViewById(R.id.expandableListViewTer);

        expandableListDetailTer = Session.getresDataTermine(Session.getLoggedEtud());

        expandableListTitleTer = new ArrayList<>(expandableListDetailTer.keySet());

        expandableListAdapterTer = new CustomExpandableListAdapterTer
                (this,expandableListTitleTer,expandableListDetailTer);

        expandableListViewTer.setAdapter(expandableListAdapterTer);


        expandableListViewCancel = findViewById(R.id.expandableListViewCanceled);

        expandableListDetailCancel = Session.getresDataAnnule(Session.getLoggedEtud());

        expandableListTitleCancel = new ArrayList<>(expandableListDetailCancel.keySet());

        expandableListAdapterCancel = new CustomExpandableListAdapterCancel
                (this,expandableListTitleCancel,expandableListDetailCancel);

        expandableListViewCancel.setAdapter(expandableListAdapterCancel);



        Button cancel = findViewById(R.id.cancel) ;



        TextView name = findViewById(R.id.name) ;
        name.setText(Session.getLoggedEtud().getNom());




        Button logout = findViewById(R.id.logout) ;
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showCustomDialogLogout();
            }

        });

        Button addbtn = findViewById(R.id.ajouterbtn) ;
        addbtn.setOnClickListener(e->{
            showCustomDialogAdd();
        });

        Button clear = findViewById(R.id.clear) ;
        clear.setOnClickListener(e->{
            showCustomDialogClear() ;

        });





    }
    void showCustomDialogClear() {
        final Dialog dialog = new Dialog(Etudiant.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.removeall_layout);

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.roundedpop);

        //Initializing the views of the dialog.

        Button supprimer = dialog.findViewById(R.id.supprimer);
        Button annuler = dialog.findViewById(R.id.annuler) ;


        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.removeHisto(Session.getLoggedEtud().getCin());
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

    void showCustomDialogAdd() {
        final Dialog dialog = new Dialog(Etudiant.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.add_reservation);

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.roundedpop);

        //Initializing the views of the dialog.

        Button ajouter = dialog.findViewById(R.id.ajouter);
        Button annuler = dialog.findViewById(R.id.annuler) ;


        AutoCompleteTextView autoTer = (AutoCompleteTextView) dialog.findViewById(R.id.autoTer);
        ArrayAdapter arrAdapFil = new ArrayAdapter<>(this, R.layout.menu_item, Session.getTerNames());
        autoTer.setAdapter(arrAdapFil);
        autoTer.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ter = adapterView.getItemAtPosition(i).toString() ;
                autoTer.setText(ter);

            }
        });

        EditText dated = dialog.findViewById(R.id.dateD) ;
        EditText datef = dialog.findViewById(R.id.dateF) ;

        datef.setOnClickListener(e->{
            // Initialize the DatePickerDialog with the current date and time
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Set the selected date in the calendar
                        calendar.set(selectedYear, selectedMonth, selectedDay);

                        // Initialize the TimePickerDialog with the selected time
                        TimePickerDialog timePickerDialog = new TimePickerDialog(
                                this,
                                (view1, selectedHour, selectedMinute) -> {
                                    // Set the selected time in the calendar
                                    calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                                    calendar.set(Calendar.MINUTE, selectedMinute);

                                    // Create a SimpleDateFormat object to format the date and time
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                                    // Get the formatted date and time as a string
                                    String timestamp = dateFormat.format(calendar.getTime());

                                    // Set the timestamp value to a TextView or other UI element
                                    datef.setText(timestamp);
                                    datefin = new Timestamp(calendar.getTimeInMillis()) ;
                                },
                                hour,
                                minute,
                                DateFormat.is24HourFormat(this)
                        );
                        timePickerDialog.show();
                    },
                    year,
                    month,
                    day
            );
            datePickerDialog.show();

        });

        dated.setOnClickListener(e->{
            // Initialize the DatePickerDialog with the current date and time
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Set the selected date in the calendar
                        calendar.set(selectedYear, selectedMonth, selectedDay);

                        // Initialize the TimePickerDialog with the selected time
                        TimePickerDialog timePickerDialog = new TimePickerDialog(
                                this,
                                (view1, selectedHour, selectedMinute) -> {
                                    // Set the selected time in the calendar
                                    calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                                    calendar.set(Calendar.MINUTE, selectedMinute);

                                    // Create a SimpleDateFormat object to format the date and time
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                                    // Get the formatted date and time as a string
                                    String timestamp = dateFormat.format(calendar.getTime());

                                    // Set the timestamp value to a TextView or other UI element
                                    dated.setText(timestamp);
                                    datedeb = new Timestamp(calendar.getTimeInMillis()) ;
                                },
                                hour,
                                minute,
                                DateFormat.is24HourFormat(this)
                        );
                        timePickerDialog.show();
                    },
                    year,
                    month,
                    day
            );
            datePickerDialog.show();

        });

        annuler.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           dialog.dismiss();
                                       }
                                   }

        );
        ajouter.setOnClickListener(e->{
            if(Session.checkDate(datedeb , datefin)){
                Session.addRes(new Reservation(datedeb , datefin , Session.getLoggedEtud().getCin() , ter));
            }
            else{
                Toast.makeText(this , "Ce terrain est déjà réservé pour cette date" , Toast.LENGTH_LONG).show(); ;
            }

            dialog.dismiss();

        });

        dialog.show();
    }


    void showCustomDialogLogout() {
        final Dialog dialog = new Dialog(Etudiant.this);
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
                Intent in = new Intent(Etudiant.this , LoginActivity.class) ;
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