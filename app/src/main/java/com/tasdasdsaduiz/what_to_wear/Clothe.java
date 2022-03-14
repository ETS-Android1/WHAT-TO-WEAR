package com.tasdasdsaduiz.what_to_wear;

import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Clothe implements Serializable{

    public long ID; // this is very important
    public ArrayList <String> filters; // these are essentially the tags
    public File image_dir = null;
    public String type = null;
    public Date last_wore_on = null;

    public Clothe(long id, ArrayList<String> infilters, File image, String type){
        this.ID = id;
        this.filters = new ArrayList<String>(infilters);
        this.image_dir = image;
        this.type = type;
        last_wore_on = null;
    }

    public boolean equals(Clothe b){
        if(this.ID == b.ID){
            return true;
        }
        return false;
    }

    public boolean andfilter(ArrayList <String> input_filters){

        for(int i=0; i<input_filters.size(); i++){
            String cfilt = input_filters.get(i);
            boolean found = false;
            for(int j=0; j<filters.size(); j++){
                if( cfilt.equals(filters.get(j)) ){
                    found = true;
                    break;
                }
            }
            if(!found){
                return false;
            }
        }

        return true;

    }

    public boolean orfilter(ArrayList <String> input_filters){

        for(int i=0; i<input_filters.size(); i++){
            String cfilt = input_filters.get(i);
            for(int j=0; j<filters.size(); j++){
                if( cfilt.equals(filters.get(j)) ){
                    return true;
                }
            }
        }

        return false;

    }

}
