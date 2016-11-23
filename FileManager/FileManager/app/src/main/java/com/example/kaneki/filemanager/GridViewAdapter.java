package com.example.kaneki.filemanager;

/**
 * Created by ssheklashvili on 11/24/2016.
 */


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class GridViewAdapter extends AbstractAdapter {

    public GridViewAdapter(Context c, AdapterData data) {
        super(c, data);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            File item = (File) getItem(position);
            //Get layout for grid view
            grid = inflater.inflate(R.layout.grid_single, null);
            //Get text field
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            //Get image field
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            //Set text and image
            textView.setText(((File)getItem(position)).getName());
            if (data.isChecked(position))
                ((LinearLayout)grid.findViewById(R.id.grid_item)).setBackgroundColor(R.color.checked);
            else
                ((LinearLayout)grid.findViewById(R.id.grid_item)).setBackgroundColor(Color.WHITE);

            if (item.isDirectory())
                imageView.setImageResource(mThumbIds[0]);
            else
                imageView.setImageResource(getFileIcon(position));

        } else
            grid = (View)convertView;


        return grid;
    }
}
