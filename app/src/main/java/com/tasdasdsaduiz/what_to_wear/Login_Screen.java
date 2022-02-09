package com.tasdasdsaduiz.what_to_wear;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import javax.sql.ConnectionEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login_Screen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login_Screen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ConstraintLayout MYCL;

    public Login_Screen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login_Screen.
     */
    // TODO: Rename and change types and number of parameters
    public static Login_Screen newInstance(String param1, String param2) {
        Login_Screen fragment = new Login_Screen();
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
        return inflater.inflate(R.layout.fragment_login__screen, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            /*Button continueButton = (Button) view.findViewById(R.id.testButton);
            continueButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            NavController JohannaNC = Navigation.findNavController(view);
                            JohannaNC.navigate(R.id.action_login_Screen_to_trends);

                        }
                    }
            );*/
        // catching the view as a constraind layout so that we can access its layout parameters
        MYCL = (ConstraintLayout) view;

        super.onViewCreated(view, savedInstanceState);

        // this is to remove the physicall back button messing up the navigation
        // also to make sure we do not save previous fragment instances in the stack trace
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                getActivity().moveTaskToBack(true);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(callback);//  addCallback(this,callback);

        // TODO: add dialog window for forgot password
        Button forgot_pass_button = (Button) view.findViewById(R.id.forgot_password);
        /*forgot_pass_button.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if(b){
                            SpannableString asis = new SpannableString(forgot_pass_button.getText().toString());
                            asis.setSpan(new UnderlineSpan(), 0, asis.length(), 0);
                            forgot_pass_button.setText(asis);
                        }
                        else{
                            forgot_pass_button.setText(forgot_pass_button.getText().toString());
                        }
                    }
                }
        );*/
        forgot_pass_button.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {

                        if( event.getAction() == MotionEvent.ACTION_UP ){
                            forgot_pass_button.setText(forgot_pass_button.getText().toString());
                        }
                        else if( event.getAction() == MotionEvent.ACTION_DOWN ){
                            SpannableString asis = new SpannableString(forgot_pass_button.getText().toString());
                            asis.setSpan(new UnderlineSpan(), 0, asis.length(), 0);
                            forgot_pass_button.setText(asis);
                        }

                        return true;
                    }
                }
        );

        forgot_pass_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Here we have to add the dialog box for user to enter his e-mail
                        // and request a reset link

                    }
                }
        );

        // Here I'm adding an image first I declare it. On the constructors you usually just need
        // one parameter - the context which in our case is our activity so just call
        // getActivity() should do the trick
        ImageView login_icon = new ImageView(getActivity());
        // here I'm setting what drawble will my image view carry
        login_icon.setImageDrawable( ContextCompat.getDrawable( getActivity(),R.drawable.login_screen ) );
        // and with add view I add it to the fragment
        MYCL.addView(login_icon);
        // Now to adjust its position we need to get the layour paramters
        ConstraintLayout.LayoutParams MYCLparams_login_icon = new ConstraintLayout.LayoutParams(MYCL.getLayoutParams());
        MYCLparams_login_icon.startToStart = 0; // 0 refers to the parent view (i.e the fragment)
        MYCLparams_login_icon.endToEnd = 0; // start = left, end = right
        MYCLparams_login_icon.horizontalBias = (float) 0.5; // this is to put it dead center
        MYCLparams_login_icon.topToTop = 0;
        MYCLparams_login_icon.topMargin = 125;
        // here we set the sizes otherwise it will be set to 'wrap_content' and the image will have
        // its actual size which is undesirable
        MYCLparams_login_icon.height = 400;
        MYCLparams_login_icon.width = 400;
        // and now finally we are setting the parametrs for our view
        login_icon.setLayoutParams(MYCLparams_login_icon);

        EditText username = view.findViewById(R.id.userEditText);
        EditText password = view.findViewById(R.id.passwordEditText);

        username.setText("Username");
        username.setTextColor(Color.GRAY);
        username.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if(b){
                            if(username.getText().toString().equals("Username")){
                                username.setText("");
                                username.setTextColor(Color.BLACK);
                            }
                        }
                        else{
                            if(username.getText().toString().equals("")){
                                username.setText("Username");
                                username.setTextColor(Color.GRAY);
                            }
                        }
                    }
                }
        );

        password.setText("Password");
        password.setTextColor(Color.GRAY);
        password.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if(b){
                            if(password.getText().toString().equals("Password")){
                                password.setText("");
                                password.setTextColor(Color.BLACK);
                            }
                        }
                        else{
                            if(password.getText().toString().equals("")){
                                password.setText("Password");
                                password.setTextColor(Color.GRAY);
                            }
                        }
                    }
                }
        );

        // this button is that we can login during debuggin will be removed eventually
        // by remove I mean comment out btw
        Button hacker_button = new Button(getActivity());
        hacker_button.setText("HACK!");
        MYCL.addView(hacker_button);
        ConstraintLayout.LayoutParams MYCLparams_hacker_button = new ConstraintLayout.LayoutParams(MYCL.getLayoutParams());
        MYCLparams_hacker_button.startToStart = 0;
        MYCLparams_hacker_button.topToTop = 0;
        MYCLparams_hacker_button.topMargin = 0;
        MYCLparams_hacker_button.leftMargin = 0;
        MYCLparams_hacker_button.height = 200;
        MYCLparams_hacker_button.width = 200;
        hacker_button.setLayoutParams(MYCLparams_hacker_button);
        hacker_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NavController nc = (NavController) Navigation.findNavController(view);
                        nc.navigate(R.id.action_login_Screen_to_main_Menu);
                    }
                }
        );
        hacker_button.setTextColor(Color.WHITE);
        hacker_button.setBackgroundColor(Color.WHITE);

        Button loginButton = (Button) view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if( is_valid_creds( username.getText().toString() , password.getText().toString() ) ) {
                            NavController nc = (NavController) Navigation.findNavController(view);
                            nc.navigate(R.id.action_login_Screen_to_main_Menu);
                        }
                        else{

                            if( username.getText().toString().equals("Username") ||
                                    username.getText().toString().equals("")
                            ){
                                Toast toast = Toast.makeText(view.getContext(),R.string.empty_username_toast,Toast.LENGTH_LONG);
                                toast.show();
                                return;
                            }

                            if( password.getText().toString().equals("Password") ||
                                    password.getText().toString().equals("")
                            ){
                                Toast toast = Toast.makeText(view.getContext(),R.string.empty_password_toast,Toast.LENGTH_LONG);
                                toast.show();
                                return;
                            }

                            // display a message saying it's invalid
                            Toast toast = Toast.makeText(view.getContext(),R.string.invalid_credentials_toast,Toast.LENGTH_LONG);
                            toast.show();

                        }

                    }
                }
        );

    }

    boolean is_valid_creds(String user, String pass){

        // we can update this if we have back-end in the future
        if( user.equals("tester") && pass.equals("tester") ){
            return true;
        }

        return false;

    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }


}