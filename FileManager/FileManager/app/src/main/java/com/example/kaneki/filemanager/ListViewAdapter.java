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
import java.util.Date;

public class ListViewAdapter extends AbstractAdapter {

    public ListViewAdapter(Context c, AdapterData data) {
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
            grid = inflater.inflate(R.layout.list_single, null);
            //Get text field
            ((TextView) grid.findViewById(R.id.name)).setText(item.getName());
            String modified = new Date(item.lastModified()).toString();
            ((TextView) grid.findViewById(R.id.date)).setText(modified);


            //Get image field
            ImageView imageView = (ImageView)grid.findViewById(R.id.list_image);

            if (data.isChecked(position))
                ((LinearLayout)grid.findViewById(R.id.item_list)).setBackgroundColor(R.color.checked);
            else
                ((LinearLayout)grid.findViewById(R.id.item_list)).setBackgroundColor(Color.WHITE);

            //Set text and image
            if (item.isDirectory()) {
                imageView.setImageResource(mThumbIds[0]);
                ((TextView) grid.findViewById(R.id.items)).setText((String)(Integer.toString(5)) + " Item");
                ((TextView) grid.findViewById(R.id.type)).setText("drw");

            }
            else {
                imageView.setImageResource(getFileIcon(position));
                ((TextView) grid.findViewById(R.id.items)).setText((String)(Integer.toString(0)) + " Item");
                ((TextView) grid.findViewById(R.id.type)).setText("file");
            }
        } else
            grid = (View)convertView;


        return grid;
    }
}
