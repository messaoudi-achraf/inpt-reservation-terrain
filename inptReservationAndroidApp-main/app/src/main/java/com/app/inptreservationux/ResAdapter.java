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

public class ResAdapter extends ArrayAdapter<Reservation> {

    public ResAdapter(Context con, ArrayList<Reservation> arr) {

        super(con, R.layout.list_item, arr);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Reservation res = getItem(position) ;

        if(convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.res_item , parent , false) ;

        }

        TextView id = convertView.findViewById(R.id.id) ;
        TextView date = convertView.findViewById(R.id.date) ;
        

        return convertView;
    }

}
