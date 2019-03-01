package com.example.loginapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter<String> {


    private Context mContext;
    private ArrayList<String> mTitle;


    CustomAdapter(@NonNull Context context, ArrayList<String> title) {
        super(context, R.layout.addtask, title);
        this.mContext = context;
        this.mTitle = title;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.addtask, parent, false);

        TextView myText = (TextView) listItem.findViewById(R.id.task);
        myText.setText(mTitle.get(position));

        return listItem;
    }
}
