package com.app.inptreservationux;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<etudiants> {

    public ListAdapter(Context con , ArrayList<etudiants> arr ){

        super(con , R.layout.list_item , arr ) ;


    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        etudiants e = getItem(position) ;
        if(convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item , parent , false) ;

        }

        TextView name = convertView.findViewById(R.id.name) ;
        TextView cin = convertView.findViewById(R.id.cin) ;
        TextView niv = convertView.findViewById(R.id.niv) ;
        TextView fil = convertView.findViewById(R.id.fil) ;

        name.setText(e.getNomPre());
        cin.setText(e.getCin());
        niv.setText(e.getNiv());
        fil.setText(e.getFiliere());

        return convertView ;
    }


}
