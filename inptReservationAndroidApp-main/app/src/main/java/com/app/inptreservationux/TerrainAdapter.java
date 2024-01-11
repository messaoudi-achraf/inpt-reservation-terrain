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

public class TerrainAdapter extends ArrayAdapter<Terrain> {

    public TerrainAdapter(Context con , ArrayList<Terrain> arr ){

        super(con , R.layout.list_item , arr ) ;


    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Terrain t = getItem(position) ;
        if(convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item , parent , false) ;

        }

        TextView name = convertView.findViewById(R.id.name) ;
        TextView cin = convertView.findViewById(R.id.cin) ;

        name.setText(t.getId());
        cin.setText(t.getSport());


        return convertView ;
    }


}
