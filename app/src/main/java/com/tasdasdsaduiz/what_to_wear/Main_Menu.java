package com.tasdasdsaduiz.what_to_wear;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Main_Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Main_Menu extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String the_username_argument = null;

    private String mParam1;
    private String mParam2;
    private ConstraintLayout MYCL;

    public Main_Menu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Main_Menu.
     */
    public static Main_Menu newInstance(String param1, String param2) {
        Main_Menu fragment = new Main_Menu();
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
        // Inflate the layout for this

        // Load the users clothe's database
        // ClotheDatabase: we create it if it is not there so that we can load it after int he

        if(the_username_argument == null) {
            // this code block is to create a database if there is none
            Log.d("clothesDB", "gets in the onCreateView function no problem!");
            String theusername = getArguments().getString("theusername");
            the_username_argument = theusername;
            Log.d("clothesDB", "the username is retrieved AND IT IS = " + theusername);
            ClothesDB.myabsoluteVODKA = getActivity().getFilesDir();
            String user_clothes_path = (String) ("Clothes_of_" + theusername + ".obj");
            Log.d("clothesDB", "The paths are fixed!");
            if ( !ClothesDB.checkObjectFileExists(new File(getActivity().getFilesDir(), user_clothes_path)) ) {
                ClothesDB clothesDB = new ClothesDB(user_clothes_path);
                ClothesDB.store(new File(getActivity().getFilesDir(), clothesDB.user_spec_path), clothesDB);
                Log.d("clothesDB", "Created for the first time!");
            } else {
                // the database exists
                Log.d("clothesDB", "The clothes database already exists!");
            }

            // let us do the same for the tags database
            Log.d("tagsDB", "gets in the initial check!");

        }

        return inflater.inflate(R.layout.fragment_main__menu, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("clothesDB","onviewcreated entered!");
        MYCL = (ConstraintLayout) view;

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                createLogoutDialog(view);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(callback);//  addCallback(this,callback);

        // we implement components in this .post function so that the constraint layout has been
        // created (thus the post keyword)
        MYCL.post(
            new Runnable() {
                @Override
                public void run() {

                    // at this point trying to have the balance that was suggested in the 9/2 lecture
                    // here I'm getting the width of the fragment. I can do this because I call
                    // the getWidth() function int he .post() function
                    int side_margin = 75;
                    int mid_margin = 50;
                    float top_cat = (float) 0.18;
                    int pixofw = ( (MYCL.getWidth() - 2*side_margin ) - mid_margin ) / 2;
                    int pixofh = MYCL.getHeight();


                    // here I'm just getting the button under a variable I can use
                    Button wardrobeButton = (Button) view.findViewById(R.id.wardrobeButton);
                    wardrobeButton.setBackgroundColor(Color.RED);

                    ConstraintLayout.LayoutParams params_ward = new ConstraintLayout.LayoutParams(MYCL.getLayoutParams());
                    params_ward.topToTop = 0;
                    params_ward.topMargin = (int) ( pixofh * top_cat );
                    params_ward.leftToLeft = 0;
                    params_ward.leftMargin = side_margin;
                    wardrobeButton.setLayoutParams(params_ward);

                    // I'm making it rectangular
                    wardrobeButton.getLayoutParams().width = pixofw;
                    wardrobeButton.getLayoutParams().height = pixofw;

                    wardrobeButton.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    NavController nc = (NavController) Navigation.findNavController(view);
                                    nc.navigate(R.id.action_main_Menu_to_wardrobe);
                                }
                            }
                    );

                    Button trendsButton = (Button) view.findViewById(R.id.trendButton);

                    ConstraintLayout.LayoutParams params_trends = new ConstraintLayout.LayoutParams(MYCL.getLayoutParams());
                    params_trends.topToTop = 0;
                    params_trends.topMargin = (int)( pixofh * top_cat ) + ( pixofw / 2 );
                    params_trends.leftToRight = wardrobeButton.getId();
                    params_trends.leftMargin = mid_margin;
                    trendsButton.setLayoutParams(params_trends);

                    trendsButton.getLayoutParams().height = pixofw;
                    trendsButton.getLayoutParams().width = pixofw;

                    trendsButton.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    // trendsButton.setBackgroundColor(Color.RED);

                                    NavController nc = (NavController) Navigation.findNavController(view);
                                    nc.navigate(R.id.action_main_Menu_to_trends);
                                }
                            }
                    );

                    Button stylesButton = (Button) view.findViewById(R.id.stylesButton);

                    ConstraintLayout.LayoutParams params_styles = new ConstraintLayout.LayoutParams(MYCL.getLayoutParams());
                    params_styles.topToBottom = wardrobeButton.getId();
                    params_styles.topMargin = mid_margin;
                    params_styles.leftToLeft = 0;
                    params_styles.leftMargin = side_margin;

                    stylesButton.setLayoutParams(params_styles);

                    stylesButton.getLayoutParams().height = pixofw;
                    stylesButton.getLayoutParams().width = pixofw;

                    stylesButton.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    NavController nc = (NavController) Navigation.findNavController(view);
                                    nc.navigate(R.id.action_main_Menu_to_styles);
                                }
                            }
                    );

                    Button servicesButton = (Button) view.findViewById(R.id.servicesButton);

                    ConstraintLayout.LayoutParams params_services = new ConstraintLayout.LayoutParams(MYCL.getLayoutParams());

                    params_services.topToBottom = trendsButton.getId();
                    params_services.topMargin = mid_margin;
                    params_services.leftToRight = stylesButton.getId();
                    params_services.leftMargin = mid_margin;

                    servicesButton.setLayoutParams(params_services);

                    servicesButton.getLayoutParams().height = pixofw;
                    servicesButton.getLayoutParams().width = pixofw;

                    servicesButton.setBackgroundColor(Color.RED);

                    servicesButton.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    NavController nc = (NavController) Navigation.findNavController(view);
                                    nc.navigate(R.id.action_main_Menu_to_services);
                                }
                            }
                    );

                    ImageView title = new ImageView(getActivity());
                    MYCL.addView(title);

                    title.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.second_title_attempt));
                    //title.setBackgroundColor(Color.BLUE);
                    ConstraintLayout.LayoutParams params_title = new ConstraintLayout.LayoutParams(MYCL.getLayoutParams());

                    //params_title.leftToLeft = 0;
                    //params_title.leftMargin = side_margin;
                    params_title.topToTop = 0;
                    params_title.topMargin = (int)(pixofh * top_cat) / 3;

                    title.setLayoutParams(params_title);

                    title.getLayoutParams().height = (int)( (int)(pixofh * top_cat) / 1.7 );
                    //title.getLayoutParams().width = MYCL.getWidth() - 2 * side_margin;
                    //title.setBackgroundColor(Color.BLUE);

                    ImageView star = new ImageView(getActivity());

                    MYCL.addView(star);

                    star.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.star));

                    ConstraintLayout.LayoutParams params_star = new ConstraintLayout.LayoutParams(MYCL.getLayoutParams());

                    params_star.leftToLeft = 0;
                    params_star.leftMargin = side_margin;
                    params_star.topToTop = 0;
                    params_star.topMargin = (int)(pixofh*top_cat) + 2 * mid_margin + 2 * pixofw;// + mid_margin + 2 * pixofw + mid_margin;

                    star.setLayoutParams(params_star);

                    star.getLayoutParams().width = pixofw / 2;
                    star.getLayoutParams().height = pixofw / 2;

                    star.setOnTouchListener(
                            new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View view, MotionEvent event) {
                                    if( event.getAction() == MotionEvent.ACTION_UP){
                                        star.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.star));
                                    }
                                    else if( event.getAction() == MotionEvent.ACTION_DOWN){
                                        star.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.shiny_star));
                                    }
                                    return false;
                                }
                            }
                    );
                    star.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    createRateDialog();
                                }
                            }
                    );

                    ImageView star2 = new ImageView(getActivity());

                    MYCL.addView(star2);

                    star2.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.privacy_policy));

                    ConstraintLayout.LayoutParams params_star2 = new ConstraintLayout.LayoutParams(MYCL.getLayoutParams());

                    params_star2.leftToLeft = 0;
                    params_star2.leftMargin = side_margin + pixofw / 2;
                    params_star2.topToTop = 0;
                    params_star2.topMargin = (int)(pixofh*top_cat) + 2 * mid_margin + 2 * pixofw;// + mid_margin + 2 * pixofw + mid_margin;

                    star2.setLayoutParams(params_star2);

                    star2.getLayoutParams().width = pixofw / 2;
                    star2.getLayoutParams().height = pixofw / 2;

                    star2.setOnTouchListener(
                            new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View view, MotionEvent event) {
                                    if( event.getAction() == MotionEvent.ACTION_UP){
                                        star2.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.privacy_policy));
                                    }
                                    else if( event.getAction() == MotionEvent.ACTION_DOWN){
                                        star2.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.privacy_policy_activated));
                                    }
                                    return false;
                                }
                            }
                    );
                    star2.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    createPrivacyDialog();
                                }
                            }
                    );

                    ImageView star3 = new ImageView(getActivity());

                    MYCL.addView(star3);

                    star3.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.donate));

                    ConstraintLayout.LayoutParams params_star3 = new ConstraintLayout.LayoutParams(MYCL.getLayoutParams());

                    params_star3.leftToLeft = 0;
                    params_star3.leftMargin = side_margin;
                    params_star3.topToTop = 0;
                    params_star3.topMargin = (int)(pixofw/2) + (int)(pixofh*top_cat) + 2 * mid_margin + 2 * pixofw;// + mid_margin + 2 * pixofw + mid_margin;

                    star3.setLayoutParams(params_star3);

                    star3.getLayoutParams().width = pixofw / 2;
                    star3.getLayoutParams().height = pixofw / 2;

                    star3.setOnTouchListener(
                            new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View view, MotionEvent event) {
                                    if( event.getAction() == MotionEvent.ACTION_UP){
                                        star3.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.donate));
                                    }
                                    else if( event.getAction() == MotionEvent.ACTION_DOWN){
                                        star3.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.donate_activated));
                                    }
                                    return false;
                                }
                            }
                    );
                    star3.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    createDonateDialog();
                                }
                            }
                    );

                    ImageView star4 = new ImageView(getActivity());

                    MYCL.addView(star4);

                    star4.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.super_deal));

                    ConstraintLayout.LayoutParams params_star4 = new ConstraintLayout.LayoutParams(MYCL.getLayoutParams());

                    params_star4.leftToLeft = 0;
                    params_star4.leftMargin = side_margin + pixofw/2;
                    params_star4.topToTop = 0;
                    params_star4.topMargin = (int)(pixofw/2) + (int)(pixofh*top_cat) + 2 * mid_margin + 2 * pixofw;// + mid_margin + 2 * pixofw + mid_margin;

                    star4.setLayoutParams(params_star4);

                    star4.getLayoutParams().width = pixofw / 2;
                    star4.getLayoutParams().height = pixofw / 2;

                    star4.setOnTouchListener(
                            new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View view, MotionEvent event) {
                                    if( event.getAction() == MotionEvent.ACTION_UP){
                                        star4.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.super_deal));
                                    }
                                    else if( event.getAction() == MotionEvent.ACTION_DOWN){
                                        star4.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.super_deal_activated));
                                    }
                                    return true;
                                }
                            }
                    );

                }
            }
        );


    }

    public void createLogoutDialog(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog alertDialog;

        builder.setMessage("Do you want to log out?").setTitle("Log out");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                the_username_argument = null;
                NavController nc = (NavController) Navigation.findNavController(view);
                nc.navigate(R.id.action_main_Menu_to_login_Screen);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog = builder.create();

        alertDialog.setOnShowListener(
                new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        Button positive = alertDialog.getButton(Dialog.BUTTON_POSITIVE);
                        positive.setTextSize(pxToDp(70));
                        Button negative = alertDialog.getButton(Dialog.BUTTON_NEGATIVE);
                        negative.setTextSize(pxToDp(70));
                    }
                }
        );
        alertDialog.show();


    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void createRateDialog(){

        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog;

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View popapp = inflater.inflate(R.layout.rate5stars, null);
        Button rate = popapp.findViewById(R.id.rate5starbutton);
        builder.setView(popapp);

        // 2. Chain together various setter methods to set the dialog characteristics
        //builder.setMessage("Enter e-mail to get reset link:")
        //        .setTitle("Reset password");

        /*builder.setPositiveButton("SENT LINK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });*/

        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        dialog = builder.create();
        dialog.show();

        rate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://play.google.com/store"); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        dialog.dismiss();
                        Toast toast = Toast.makeText(view.getContext(),"Thank you!",Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }
                }
        );

    }

    public void createPrivacyDialog(){

        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog;

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View popapp = inflater.inflate(R.layout.privacypolicypop, null);
        //Button rate = popapp.findViewById(R.id.rate5starbutton);
        builder.setView(popapp);

        // 2. Chain together various setter methods to set the dialog characteristics
        //builder.setMessage("Enter e-mail to get reset link:")
        //        .setTitle("Reset password");

        /*builder.setPositiveButton("SENT LINK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });*/

        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        dialog = builder.create();
        dialog.show();

        /*rate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse("https://play.google.com/store"); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        dialog.dismiss();
                        Toast toast = Toast.makeText(view.getContext(),"Thank you!",Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }
                }
        );*/

    }

    public void createDonateDialog(){

        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog;

        // 2. whatever ws this steop called
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View popapp = inflater.inflate(R.layout.donatepop, null);
        builder.setView(popapp);

        TextView textView = popapp.findViewById(R.id.charitiestv);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml( "<h1>British Red Cross</h1>" +
                    "</br> Donate clothes by post for free! </br> " +
                    "</br> <a href=\"https://giftshop.redcross.org.uk/products/free-donation-bag\"> Visit here!</a> "+
                    "</hr><h1>Stadsmissionen</h1>" +
                            "</br>No one chooses their own home - homelessness can affect everyone. We offer emergency help with food, heating and hygiene. Become a monthly donor and we will make a difference together.</br> " +
                            "</br> <a href=\"https://www.stadsmissionen.se/\"> Visit here!</a> "+
                    "</hr><h1>Myrorna</h1>" +
                            "</br>Smutsigt, utslitet, trasigt eller varor som är obrukbara som vi inte kan sälja i Myrornas butiker. Det som lämnas in till oss som inte kan säljas medför stora kostnader. Vi reserverar oss för att tacka nej till det vi bedömer inte går att sälja vidare.</br> " +
                            "</br> <a href=\"https://www.myrorna.se/lamna-in/\"> Visit here!\n</a> "+
                    "</hr><h1>Swedish Red Cross</h1>" +
                            "</br> Donate clothes for free! </br> " +
                            "</br> <a href=\"https://www.rodakorset.se/\"> Visit here!</a> </hr></hr></br></br>" +
                            "</br> \t </br> " +
                            "</br> \t </br> " +
                            "</br> =================== </br> "
                    , Html.FROM_HTML_MODE_COMPACT));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            textView.setText(Html.fromHtml( "<h1>British Red Cross</h1>" +
                    "</br> Donate clothes by post for free! </br> " +
                    "</br> <a href=\"https://giftshop.redcross.org.uk/products/free-donation-bag\"> Visit here!</a> "+
                    "</hr><h1>Stadsmissionen</h1>" +
                    "</br>No one chooses their own home - homelessness can affect everyone. We offer emergency help with food, heating and hygiene. Become a monthly donor and we will make a difference together.</br> " +
                    "</br> <a href=\"https://www.stadsmissionen.se/\"> Visit here!</a> "+
                    "</hr><h1>Myrorna</h1>" +
                    "</br>Smutsigt, utslitet, trasigt eller varor som är obrukbara som vi inte kan sälja i Myrornas butiker. Det som lämnas in till oss som inte kan säljas medför stora kostnader. Vi reserverar oss för att tacka nej till det vi bedömer inte går att sälja vidare.</br> " +
                    "</br> <a href=\"https://www.myrorna.se/lamna-in/\"> Visit here!\n</a> "+
                    "</hr><h1>Swedish Red Cross</h1>" +
                    "</br> Donate clothes for free! </br> " +
                    "</br> <a href=\"https://www.rodakorset.se/\"> Visit here!</a> </hr></hr></br></br>" +
                    "</br> \t </br> " +
                    "</br> \t </br> " +
                    "</br> =================== </br> "));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }

        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        dialog = builder.create();
        dialog.show();


    }

}