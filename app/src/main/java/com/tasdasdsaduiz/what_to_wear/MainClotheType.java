package com.tasdasdsaduiz.what_to_wear;

import android.util.Log;

import java.util.ArrayList;

public class MainClotheType {

    public String maintypes[] = new String[100];
    public String subtypes[][] = new String[100][];
    public float subweight[][] = new float[100][]; // weight in kg

    public MainClotheType(){

        // add the data for 0
        maintypes[0] = "T-shirt";
        String [] st0 = {"Normal", "Long-Sleeve", "No-sleeves"};
        subtypes[0] = st0;
        float [] wst0 = {0.10f,0.20f,0.12f};
        subweight[0] = wst0;

        // add the data for 1
        maintypes[1] = "T-shirt";
        String [] st1 = {"Normal", "Long-Sleeve", "No-sleeves"};
        subtypes[1] = st1;
        float [] wst1 = {0.10f,0.20f,0.12f};
        subweight[1] = wst1;

          // add the data for 1
          maintypes[1] = "T-shirt";
          String [] st2 = {"Normal", "Long-Sleeve", "No-sleeves"};
          subtypes[1] = st1;
          float [] wst2 = {0.10f,0.20f,0.12f};
          subweight[1] = wst1;




    }

}
