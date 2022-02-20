package com.tasdasdsaduiz.what_to_wear;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link registrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class registrationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean is_on_reg_proc = false;

    public registrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment registrationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static registrationFragment newInstance(String param1, String param2) {
        registrationFragment fragment = new registrationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        is_on_reg_proc = false;

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

                if( is_on_reg_proc ){
                    return;
                }

                NavController nc = (NavController) Navigation.findNavController(view);
                nc.navigate(R.id.action_registrationFragment_to_login_Screen);

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(callback);//  addCallback(this,callback);

        ConstraintLayout MYCL = (ConstraintLayout) view;
        Button register = (Button) view.findViewById(R.id.register_regform);
        EditText username = (EditText) view.findViewById(R.id.username_regform);
        EditText password = (EditText) view.findViewById(R.id.password_regform);
        EditText email = (EditText) view.findViewById(R.id.email_regform);
        EditText check_pass = (EditText) view.findViewById(R.id.confpass_regform);

        username.setTextColor(Color.GRAY);
        username.setText("Username");
        username.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if(b) {
                            if (username.getText().toString().equals("Username")){
                                username.setText("");
                                username.setTextColor(Color.BLACK);
                            }
                        }
                        else{
                            if( onlywhitespace(username.getText().toString()) || username.getText().toString().equals("Username") ){
                                    username.setText("Username");
                                    username.setTextColor(Color.GRAY);
                            }
                        }
                    }
                }
        );

        email.setTextColor(Color.GRAY);
        email.setText("e-mail");
        email.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if(b) {
                            if (email.getText().toString().equals("e-mail")) {
                                email.setText("");
                                email.setTextColor(Color.BLACK);
                            }
                        }
                        else{
                            if( onlywhitespace(email.getText().toString()) || email.getText().toString().equals("e-mail") ){
                                email.setText("e-mail");
                                email.setTextColor(Color.GRAY);
                            }
                        }
                    }
                }
        );

        password.setTextColor(Color.GRAY);
        password.setText("Password");
        password.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if(b) {
                            if (password.getText().toString().equals("Password")) {
                                password.setText("");
                                password.setTextColor(Color.BLACK);
                                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                                InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                            }
                        }
                        else{
                            if( onlywhitespace(password.getText().toString()) ){ //|| password.getText().toString().equals("Password") ){
                                password.setText("Password");
                                // IT WILL BE ONLY TEXT ONLY IF I CLICK OUTSIDE AND IT IS EMPTY
                                password.setInputType(InputType.TYPE_CLASS_TEXT);
                                password.setTextColor(Color.GRAY);
                            }
                        }
                    }
                }
        );

        check_pass.setTextColor(Color.GRAY);
        check_pass.setText("Confirm Password");
        check_pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        check_pass.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if(b) {
                            if (check_pass.getText().toString().equals("Confirm Password")) {
                                check_pass.setText("");
                                check_pass.setTextColor(Color.BLACK);
                                check_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                                InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                            }
                        }
                        else{
                            if( onlywhitespace( check_pass.getText().toString() ) ){ //|| check_pass.getText().toString().equals("Confirm Password") ){
                                check_pass.setText("Confirm Password");
                                check_pass.setInputType(InputType.TYPE_CLASS_TEXT);
                                check_pass.setTextColor(Color.GRAY);
                            }
                        }
                    }
                }
        );

        MYCL.post(
                new Runnable() {
                    @Override
                    public void run() {

                        register.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        if( ( username.getText().length() == 0 ) || ( username.getText().toString().equals("Username") ) ){
                                            Toast toast = Toast.makeText(getContext(),"You must have a username!",Toast.LENGTH_LONG);
                                            toast.show();
                                            return;
                                        }

                                        if( (email.getText().length() == 0) || ( email.getText().toString().equals("e-mail") ) ){
                                            Toast toast = Toast.makeText(getContext(),"You need to associate an e-mail with your account!",Toast.LENGTH_LONG);
                                            toast.show();
                                            return;
                                        }

                                        if( !isvalidemail(email.getText().toString()) ){
                                            Toast toast = Toast.makeText(getContext(),"Invalid e-mail",Toast.LENGTH_LONG);
                                            toast.show();
                                            return;
                                        }

                                        if( password.getText().toString().length() < 8 ){
                                            Toast toast = Toast.makeText(getContext(),"The password length must be at least 8.",Toast.LENGTH_LONG);
                                            toast.show();
                                            return;
                                        }

                                        if( hasNOsymbol( password.getText().toString() ) || hasNOnumber( password.getText().toString() ) ){
                                            Toast toast = Toast.makeText(getContext(),"The password must contain at least one symbol and at least one number.",Toast.LENGTH_LONG);
                                            toast.show();
                                            return;
                                        }

                                        // check passwords match
                                        if( !password.getText().toString().equals( check_pass.getText().toString() ) ){
                                            Toast toast = Toast.makeText(getContext(),"Passwords do not match!",Toast.LENGTH_LONG);
                                            toast.show();
                                            return;
                                        }

                                        // we still have to make checks

                                        // create the candidate account
                                        String string_username = (String) username.getText().toString();
                                        String string_password = (String) password.getText().toString();
                                        String string_email = (String) email.getText().toString();
                                        UserAccount canditate = new UserAccount(string_username,string_email,string_password);

                                        // contain the database
                                        UserDatabase userDatabase = UserDatabase.load( new File(UserDatabase.myabsoluteVODKA,UserDatabase.path) );

                                        Log.d("UserDB","Loading from registration frag");
                                        if(userDatabase.equals(null)){
                                            Log.d("UserDB","The loaded object is empty");
                                        }
                                        else{
                                            Log.d("UserDB","The loading is OK");
                                        }

                                        // check if the username does not exist
                                        String reg_result = userDatabase.regUser(canditate);

                                        if( reg_result.equals("SUCCESS") ){

                                            Toast toast = Toast.makeText(getContext(),"Registration Successful!",Toast.LENGTH_LONG);
                                            toast.show();

                                            // disabling everythiing
                                            // stopping the interaction
                                            katastrofi(username);
                                            katastrofi(password);
                                            katastrofi(email);
                                            katastrofi(check_pass);
                                            // careful this may cause a bug
                                            // katastrofi(register);
                                            // stopping the back button as well
                                            is_on_reg_proc = true;

                                            // thread for registration
                                            /*new Thread(
                                                    new Runnable() {
                                                        @Override
                                                        public void run() {*/
                                                            // storing the updated database
                                                            UserDatabase.store( new File( UserDatabase.myabsoluteVODKA , UserDatabase.path ) , userDatabase );
                                                            NavController nc = (NavController) Navigation.findNavController(view);
                                                            nc.navigate(R.id.action_registrationFragment_to_login_Screen);
                                                       /* }
                                                    }
                                            ).start();*/

                                            return;

                                        }
                                        else if(reg_result.equals("ERROR: EMAIL ALREADY EXISTS")){
                                            Toast toast = Toast.makeText(getContext(),"E-mail already used!",Toast.LENGTH_LONG);
                                            toast.show();
                                            return;
                                        }

                                        Toast toast = Toast.makeText(getContext(),"Username already exists!",Toast.LENGTH_LONG);
                                        toast.show();

                                    }
                                }
                        );

                    }
                }
        );


        /*TextView worksUser = new TextView(getActivity());
        MYCL.addView(worksUser);

        EditText userET = (EditText) view.findViewById(R.id.username_regform);
        worksUser.setBackgroundColor(Color.BLUE);

        ConstraintLayout.LayoutParams params_user = new ConstraintLayout.LayoutParams(MYCL.getLayoutParams());

        params_user.topToTop = 0;
        params_user.bottomToBottom = 0;
        params_user.verticalBias = (float) 0.2;
        params_user.leftToLeft = 0;
        params_user.rightToRight = 0;
        params_user.horizontalBias = (float) 0.5;
        worksUser.setLayoutParams(params_user);

        worksUser.getLayoutParams().height = userET.getLayoutParams().height;
        worksUser.getLayoutParams().width = userET.getLayoutParams().width;*/
        /*worksUser.setTextSize(userET.getTextSize());
        worksUser.setText("A");
        worksUser.setTextColor(Color.BLUE);*/

    }

    public boolean onlywhitespace(String X){
        for(int i = 0; i < X.length(); i++){
            if(X.charAt(i) != ' '){
                return false;
            }
        }
        return true;
    }

    public boolean hasNOsymbol(String X){
        for(int i = 0; i < X.length(); i++){
            char c = X.charAt(i);
            boolean isnum = ( c >= '0' ) && ( c <= '9' );
            boolean islow = ( c >= 'a' ) && ( c <= 'z' );
            boolean ishigh = ( c >= 'A' ) && ( c <= 'Z' );
            if( !isnum && !islow && !ishigh ){
                return false;
            }
        }
        return true;
    }

    public boolean hasNOnumber(String X){
        for(int i = 0; i < X.length(); i++){
            if( (X.charAt(i) >= '0') && (X.charAt(i) <= '9') ){
                return false;
            }
        }
        return true;
    }

    public boolean isvalidemail(String X){
        boolean foundat = false;
        boolean founddot = false;

        for(int i = 0; i < X.length(); i++){
            if(( X.charAt(i) == '@' ) && (foundat == false)){
                foundat = true;
                continue;
            }
            if( (X.charAt(i)=='.') && (foundat==true) ){
                founddot = true;
            }
        }

        return (boolean)( foundat && founddot );

    }

    public void katastrofi(View item){
        item.setVisibility(View.GONE);
        item.setFocusable(false);
        item.setClickable(false);
        item.setEnabled(false);
    }

}