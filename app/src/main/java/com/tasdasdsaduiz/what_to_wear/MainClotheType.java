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
        String [] st0 = {"Normal", "Long-Sleeve", "No-sleeves", "V-neck", "Round-neck"};
        subtypes[0] = st0;
        float [] wst0 = {0.10f,0.20f,0.12f};
        subweight[0] = wst0;

        // add the data for 1
        maintypes[1] = "Shirt";
        String [] st1 = {"Short-Sleeve", "Long-Sleeve"};
        subtypes[1] = st1;
        float [] wst1 = {0.15f,0.25f};
        subweight[1] = wst1;

        // add the data for 1
        maintypes[1] = "Swimming-wear";
        String [] st2 = {"Swimming-suit", "Shorts", "Bikini"};
        subtypes[1] = st1;
        float [] wst2 = {0.2f,0.2f,0.1f};
        subweight[1] = wst1;

        // add the data for 1
        maintypes[1] = "Dress";
        String [] st3 = {"Long", "Short", "Evening-dress", "Wedding-dress" };
        subtypes[1] = st1;
        float [] wst3 = {0.2f,0.35f,0.5f,2f};
        subweight[1] = wst1;

        // add the data for 1
        maintypes[1] = "Pants";
        String [] st4 = {"Business", "Jeans", "Chinos"};
        subtypes[1] = st1;
        float [] wst4 = {0.6f,1f,0.5f};
        subweight[1] = wst1;

        // add the data for 1
        maintypes[1] = "Pullover";
        String [] st5 = {"Hoodie", "Sweatshirt", "Cardigan", "Sweater"};
        subtypes[1] = st1;
        float [] wst5 = {0.5f,0.4f,0.5f, 0.5f};
        subweight[1] = wst1;

        // add the data for 1
        maintypes[1] = "Jacket";
        String [] st6 = {"Rain-jacket", "Wind-jacket", "Winter-jacket", "Down-jacket", "Summer-jacket", "Autumn-jacket"};
        subtypes[1] = st1;
        float [] wst6 = {1.2f,0.3f,1.5f, 0.4f, 1.2f};
        subweight[1] = wst1;

        // add the data for 1
        maintypes[1] = "Gloves";
        String [] st7 = {"Leather", "Wool", "Synthetic"};
        subtypes[1] = st1;
        float [] wst7 = {0.4f,0.3f,0.4f};
        subweight[1] = wst1;

        // add the data for 1
        maintypes[1] = "Headgear";
        String [] st8 = {"Hat-winter","Hat-summer" ,"Cap"};
        subtypes[1] = st1;
        float [] wst8 = {0.2f,0.15f,0.15f};
        subweight[1] = wst1;




    }

}
