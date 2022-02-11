package com.tasdasdsaduiz.what_to_wear;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        ConstraintLayout MYCL = (ConstraintLayout) view;

        Button register = (Button) view.findViewById(R.id.register_regform);

        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        NavController nc = (NavController) Navigation.findNavController(view);
                        nc.navigate(R.id.action_registrationFragment_to_login_Screen);
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

}