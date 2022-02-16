package com.tasdasdsaduiz.what_to_wear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // make it full screen and remove stupid title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // only after this you may set the content view
        setContentView(R.layout.activity_main);

        // UserDatabase: we create it if it is not there so that we can load it after int he fragment
        UserDatabase.myabsoluteVODKA = getFilesDir();
        if( ! UserDatabase.checkObjectFileExists( new File(getFilesDir(),UserDatabase.path) ) ){
            UserDatabase userDatabase = new UserDatabase();
            UserDatabase.store( new File(getFilesDir(),UserDatabase.path) , userDatabase );
            Log.d("UserDB","Created for the first time!");
        }
        /*else{
            this.userDatabase = UserDatabase.load( new File(getFilesDir(),UserDatabase.path) );
            Log.d("UserDB","Loaded successfully Like look beloq");

            if( this.userDatabase.equals(null) ){
                Log.d("UserDB","it is null from the loading whyyyyy????");
            }
            else{
                Log.d("UserDB","rush BEE");
                //Log.d("UserDB","it has a weight mate therefore it is " + this.userDatabase.usernames.size());
            }

        }*/

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // this if statement works only for edit texts
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

}