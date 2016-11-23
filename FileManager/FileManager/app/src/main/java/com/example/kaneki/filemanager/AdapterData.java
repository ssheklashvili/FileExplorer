package com.example.kaneki.filemanager;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ssheklashvili on 11/24/2016.
 */
public class AdapterData {
    private File[] files;
    private boolean[] checked;
    private String path;

    public AdapterData(String path){
        this.path = "/";
        openFolder(path);
    }

    public boolean isChecked(int position){
        return checked[position];
    }

    public void setChecked(int position, boolean checked){
        this.checked[position] = checked;
    }

    public ArrayList<Integer> getChecked(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int k = 0; k < checked.length; k++){
            if (checked[k])
                list.add(k);
        }
        return list;
    }

    public void openFolder(String folder_name){
        path += folder_name + '/';
        files = new File(path).listFiles();
        checked = new boolean[files.length];
    }

    public void openFolder(int position){
        openFolder(files[position].getName());
    }

    public File getItem(int position){
        return files[position];
    }

    public void unCheckAll(){
        for (int k = 0; k < checked.length; k++)
            checked[k] = false;
    }

    public boolean goBack(){
        int index;
        for (index = path.length() - 2; index >= 0; index--)
            if (path.charAt(index) == '/')
                break;
        if (index <= 0)
            return false;
        path = path.substring(0, index);
        System.out.println(path);
        openFolder("");
        return true;
    }

    public String getPath(){
        return path;
    }

}
