package com.tasdasdsaduiz.what_to_wear;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.media.Image;
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

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Main_Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Main_Menu extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main__menu, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        MYCL = (ConstraintLayout) view;

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                NavController nc = (NavController) Navigation.findNavController(view);
                nc.navigate(R.id.action_main_Menu_to_login_Screen);
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
                                    return true;
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
                                    return true;
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
                                    return true;
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

}