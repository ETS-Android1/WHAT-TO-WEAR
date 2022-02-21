package com.tasdasdsaduiz.what_to_wear;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class TagsDB implements Serializable {

    private static final long serialVersionUID = 1L;
    public static File myabsoluteVODKA = null;

    // fields
    public ArrayList <String> tags;
    public String user_spec_path = null; // this path should be different for each user

    public TagsDB(){
        this.tags = new ArrayList<String>();
    }

    public static boolean checkObjectFileExists(File f){
        try{
            Log.d("tagsDB","Checking that the file exists!");
            Scanner scanner = new Scanner(f);
            Log.d("tagsDB","Successfuly exists!");
            scanner.close();
        }
        catch (FileNotFoundException e){
            Log.d("tagsDB","Does not exists!");
            return false;
        }
        return true;
    }

    public static boolean store(File f, TagsDB ub){
        try{
            Log.d("tagsDB","Storing the file!");
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            Log.d("tagsDB","Object output stream successfully created!");
            objectOutputStream.writeObject(ub);
            objectOutputStream.close();
            fileOutputStream.close();
            Log.d("tagsDB","The object is written successfully!");
            // return true;
        }
        catch(Exception ex){
            Log.d("tagsDB","Object can't be written!");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static TagsDB load(File f){

        FileInputStream fileIn;
        ObjectInputStream objectIn;
        Object obj;

        Log.d("tagsDB","Loading function entered!");

        try {
            fileIn = new FileInputStream( (File) f );
            Log.d("tagsDB","+++++++++ The file inputstream is created! ");
            objectIn = new ObjectInputStream(fileIn);
            Log.d("tagsDB","+++++++++ The OBJECT INPUT STREAM is created! ");
            obj = objectIn.readObject();
            objectIn.close();
            fileIn.close();
            Log.d("tagsDB","++++++++ Object read successfully!");
            return (TagsDB) obj;
        }
        catch (FileNotFoundException e) {
            Log.d("tagsDB","+++++++++ FFS EXC");
            return null;
        }
        catch (IOException e) {
            Log.d("tagsDB","+++++++++ OMG");
            e.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException e) {
            Log.d("tagsDB","+++++++++ KEV");
            e.printStackTrace();
            return null;
        }

    }




}
