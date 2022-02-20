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
import java.util.HashMap;
import java.util.Scanner;

public class ClothesDB implements Serializable {

    // fields for object creation
    private static final long serialVersionUID = 1L;
    public static File myabsoluteVODKA = null;

    // fields
    public ArrayList <Clothe> clotheArrayList;
    public String user_spec_path = null; // this path should be different for each user

    public ClothesDB(String user_spec_pat){
        this.user_spec_path = user_spec_pat;
        clotheArrayList = new ArrayList<Clothe>();
    }

    public static boolean checkObjectFileExists(File f){
        try{
            Log.d("clothesDB","Checking that the file exists!");
            Scanner scanner = new Scanner(f);
            Log.d("clothesDB","Successfuly exists!");
            scanner.close();
        }
        catch (FileNotFoundException e){
            Log.d("clothesDB","Does not exists!");
            return false;
        }
        return true;
    }

    public static boolean store(File f, ClothesDB ub){
        try{
            Log.d("clothesDB","Storing the file!");
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            Log.d("clothesDB","Object output stream successfully created!");
            objectOutputStream.writeObject(ub);
            objectOutputStream.close();
            fileOutputStream.close();
            Log.d("clothesDB","The object is written successfully!");
            // return true;
        }
        catch(Exception ex){
            Log.d("clothesDB","Object can't be written!");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static ClothesDB load(File f){

        FileInputStream fileIn;
        ObjectInputStream objectIn;
        Object obj;

        Log.d("clothesDB","Loading function entered!");

        try {
            fileIn = new FileInputStream( (File) f );
            Log.d("clothesDB","+++++++++ The file inputstream is created! ");
            objectIn = new ObjectInputStream(fileIn);
            Log.d("clothesDB","+++++++++ The OBJECT INPUT STREAM is created! ");
            obj = objectIn.readObject();
            objectIn.close();
            fileIn.close();
            Log.d("clothesDB","++++++++ Object read successfully!");
            return (ClothesDB) obj;
        }
        catch (FileNotFoundException e) {
            Log.d("clothesDB","+++++++++ FFS EXC");
            return null;
        }
        catch (IOException e) {
            Log.d("clothesDB","+++++++++ OMG");
            e.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException e) {
            Log.d("clothesDB","+++++++++ KEV");
            e.printStackTrace();
            return null;
        }

    }



}
