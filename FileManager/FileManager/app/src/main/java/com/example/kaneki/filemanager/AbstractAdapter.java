package com.example.kaneki.filemanager;

import android.content.Context;
import android.widget.BaseAdapter;

import java.io.File;

/**
 * Created by ssheklashvili on 11/24/2016.
 */
public abstract class AbstractAdapter extends BaseAdapter{

    private static String[] icon_names = {"doc", "mp3", "png", "txt", "xls", "zip"};
    protected Context mContext;
    protected AdapterData data;

    protected Integer[] mThumbIds = {
            R.drawable.folder,
            R.drawable.doc,
            R.drawable.mp3,
            R.drawable.png,
            R.drawable.txt,
            R.drawable.xls,
            R.drawable.zip,
    };

    public AbstractAdapter(Context c, AdapterData data){
        this.mContext = c;
        this.data = data;
    }

    public void openFolder(int item_index){
        data.openFolder(item_index);
    }

    public String getItemName(int position){
        return data.getItem(position).getName();
    }

    public Integer getFileIcon(int position){
        String file_extension = getExtension((File)getItem(position));
        int index = 0;
        for (; index < icon_names.length; index++)
            if (icon_names[index].equals(file_extension))
                return mThumbIds[index + 1];

        return mThumbIds[index];
    }

    private String getExtension(File file){
        String filenameArray[] = file.getName().split("\\.");
        return filenameArray[filenameArray.length-1];
    }

    @Override
    public int getCount() {
        return this.mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return data.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
