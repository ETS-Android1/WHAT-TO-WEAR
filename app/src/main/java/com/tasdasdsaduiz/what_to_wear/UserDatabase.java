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
import java.util.HashMap;
import java.util.Scanner;

public class UserDatabase implements Serializable{

    // fields for object creation
    private static final long serialVersionUID = 1L;
    public static final String path = "UserDatabase.obj";
    public static File myabsoluteVODKA = null;

    // fields
    public HashMap < String , Boolean > usernames;
    public HashMap < String , String > hashMapSimple; // pass to string from now on
    public HashMap < UserAccount, Boolean > hashMapNormal;
    public HashMap < String , Boolean > hashemail;

    public UserDatabase(){

        usernames = new HashMap<String,Boolean>();
        hashMapSimple = new HashMap<String, String>();
        hashMapNormal = new HashMap<UserAccount,Boolean>();
        hashemail = new HashMap<String, Boolean>();

    }

    public static boolean checkObjectFileExists(File f){
        try{
            Log.d("UserDB","Checking that the file exists!");
            Scanner scanner = new Scanner(f);
            Log.d("UserDB","Successfuly exists!");
            scanner.close();
        }
        catch (FileNotFoundException e){
            Log.d("UserDB","Does not exists!");
            return false;
        }
        return true;
    }

    public static boolean store(File f, UserDatabase ub){
        try{
            Log.d("UserDB","Storing the file!");
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            Log.d("UserDB","Object output stream successfully created!");
            objectOutputStream.writeObject(ub);
            objectOutputStream.close();
            fileOutputStream.close();
            Log.d("UserDB","The object is written successfully!");
            // return true;
        }
        catch(Exception ex){
            Log.d("UserDB","Object can't be written!");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static UserDatabase load(File f){

        FileInputStream fileIn;
        ObjectInputStream objectIn;
        Object obj;

        Log.d("UserDB","Loading function entered!");

        try {
            fileIn = new FileInputStream( (File) f );
            Log.d("UserDB","+++++++++ The file inputstream is created! ");
            objectIn = new ObjectInputStream(fileIn);
            Log.d("UserDB","+++++++++ The OBJECT INPUT STREAM is created! ");
            obj = objectIn.readObject();
            objectIn.close();
            fileIn.close();
            Log.d("UserDB","++++++++ Object read successfully!");
            return (UserDatabase) obj;

        }
        catch (FileNotFoundException e) {
            Log.d("UserDB","+++++++++ FFS EXC");
            return null;
        }
        catch (IOException e) {
            Log.d("UserDB","+++++++++ OMG");
            e.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException e) {
            Log.d("UserDB","+++++++++ KEV");
            e.printStackTrace();
            return null;
        }

    }

    // maybe it should be static?
    public String regUser(UserAccount ua){

        Boolean result = usernames.get(ua.getUsername());
        if( (result != null) && (result == true) ){
            return "ERROR: USERNAME ALREADY EXISTS";
        }

        Boolean result_email = hashemail.get( ua.getEmail() );
        if( (result_email != null) && (result_email == true) ){
            return "ERROR: EMAIL ALREADY EXISTS";
        }

        // if username does not exists then we create the account
        usernames.put( ua.getUsername() , Boolean.TRUE );
        hashemail.put( ua.getEmail() , Boolean.TRUE );
        hashMapSimple.put( ua.getUsername() , ua.getPassword() );
        hashMapNormal.put( ua , Boolean.TRUE );
        return "SUCCESS";

    }

    public boolean emailexists(String X){
        Boolean result_email = hashemail.get( X );
        if( (result_email != null) && (result_email == true) ){
            return true;
        }
        return false;
    }

    // should this be static instead!?
    public String authenticate(UserAccount ua){

        Log.d("UserDB", "the authentication function is entered!");

        if( usernames.equals(null) ){
            Log.d("userDB","WHY IS THIS NULL!!!! THE LOADING MUST NOT BE CORRECT THEN!");
        }

        Log.d("UserDB", "the result you get is " + usernames.get(ua.getUsername()) );

        Boolean result = usernames.get(ua.getUsername());

        Log.d("UserDB","but the result var gets " + result);

        if( (result == null) || (result != true) ){
            Log.d("UserDB", "the if statement is entered for not existgance!");
            return "ERROR: USERNAME DOES NOT EXIST";
        }

        int sizeofsimpmap = hashMapSimple.size();
        Log.d("UserDB","the size of this map is " + sizeofsimpmap);

        Log.d("UserDB","now printing the hashmap: ");
        for(String name : hashMapSimple.keySet() ){
            String key = name;
            String value = hashMapSimple.get(key).toString();
            Log.d("UserDB","entry equals to " + key + "  ,  " + value);
        }

        String result2 = hashMapSimple.get( ua.getUsername() );
        Log.d("UserDB","the second result you get " + result2);
        Log.d("UserDB","the litteral second result you get is " + hashMapSimple.get( ua.getUsername() ) );

        if( (result2 == null) || (!result2.equals(ua.getPassword())) ){
            return "ERROR: INVALIDE PASSWORD";
        }

        return "SUCCESS";

    }

}
