package com.tasdasdsaduiz.what_to_wear;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Main_Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Main_Menu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
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
    // TODO: Rename and change types and number of parameters
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
                    int pixofw = ( (MYCL.getWidth() - 150) - 1 * 50 ) / 2;
                    Log.d("omg","just output something chuck!");
                    Log.d("measuredH = " , "" + MYCL.getMeasuredHeight() );
                    // int pixofh = MYCL.getHeight();


                    // here I'm just getting the button under a variable I can use
                    Button wardrobeButton = (Button) view.findViewById(R.id.wardrobeButton);
                    wardrobeButton.setBackgroundColor(Color.RED);
                    /*
                    ConstraintLayout.LayoutParams params_ward = new ConstraintLayout.LayoutParams(MYCL.getLayoutParams());
                    params_ward.topToTop = 0;
                    // params_ward.bottomToBottom = 0;
                    // params_ward.verticalBias = (float) 0.25;
                    params_ward.topMargin = (int) ( pixofh * 0.2 );
                    params_ward.leftToLeft = 0;
                    params_ward.leftMargin = 75;
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
                    // params_trends.bottomToBottom = 0;
                    // params_trends.verticalBias = (float) 0.25;
                    params_ward.topMargin = (int) ( pixofh * 0.2 ) + (int)( pixofw/2 );
                    params_trends.leftToLeft = 0;
                    params_trends.leftMargin = 75 + pixofw + 25;
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
                    servicesButton.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    NavController nc = (NavController) Navigation.findNavController(view);
                                    nc.navigate(R.id.action_main_Menu_to_services);
                                }
                            }
                    );
                    */





                }
            }
        );


    }

}