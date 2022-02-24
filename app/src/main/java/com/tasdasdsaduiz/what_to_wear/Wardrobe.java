package com.tasdasdsaduiz.what_to_wear;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;







import android.net.Uri;
import android.util.Log;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.tasdasdsaduiz.what_to_wear.R.color.design_default_color_primary;
import static com.tasdasdsaduiz.what_to_wear.R.drawable.*;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Wardrobe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Wardrobe extends Fragment {

    public ConstraintLayout MYCL;
    public ImageView receiver_preview;
    // public Clothe consistent_pinakas_rouxon[][];
    public int slot_to_add_i; // THIS MUST REMAIN UPDATED EVEN WHEN THE CLOTHE IS DELETED
    public int slot_to_add_j;
    public Bitmap bitmap_to_save;
    public ArrayList<String> active_tags;
    public String primarytype = null;
    public String secondarytype = null;
    public boolean should_I_save_type = false;
    public View the_fallen_view = null;

    // to put the bitmap in the preview
    public void PUTin(Uri uri) throws IOException {
        Log.d("getimmage","it is in! what is URI = " + uri.toString() );
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
        bitmap_to_save = bitmap;
        Log.d("getimmage","bitmap set!");
        Log.d("getimmage","image view retrieved");
        receiver_preview.setImageBitmap(bitmap);
        Log.d("getimmage","then why do we get an exception bor?");
    }

    // IN!ner
    class MyLifeCycleObserver implements DefaultLifecycleObserver {

        public final ActivityResultRegistry mRegistry;
        public ActivityResultLauncher<String> mGetContent;

        public MyLifeCycleObserver(@NonNull ActivityResultRegistry registry) {
            mRegistry = registry;
        }

        public void onCreate(@NonNull LifecycleOwner owner) {

            mGetContent = mRegistry.register("key", owner, new ActivityResultContracts.GetContent(),
                    new ActivityResultCallback<Uri>() {
                        @Override
                        public void onActivityResult(Uri uri) {
                            // Handle the returned Uri
                            Log.d("getimmage","easiest man alive");
                            try{
                                PUTin(uri);
                            }
                            catch (Exception e){
                                // UAV is not online then
                                Log.d("getimmage","Uav not online!");
                            }

                        }
                    });
        }

        public void selectImage() {
            // Open the activity to select an image
            mGetContent.launch("image/*");
            Log.d("getimmage","ahadsfasdhfads;l;lkfdjalsdf");
        }

    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Wardrobe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Wardrobe.
     */
    // TODO: Rename and change types and number of parameters
    public static Wardrobe newInstance(String param1, String param2) {
        Wardrobe fragment = new Wardrobe();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    private MyLifeCycleObserver mObserver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mObserver = new MyLifeCycleObserver(requireActivity().getActivityResultRegistry());
        getLifecycle().addObserver(mObserver);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wardrobe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        MYCL = (ConstraintLayout) view;

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                NavController nc = (NavController) Navigation.findNavController(view);
                nc.navigate(R.id.action_wardrobe_to_main_Menu);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(callback);//  addCallback(this,callback);

        ImageView addclothebutton = (ImageView) view.findViewById(R.id.addclotheimageview);
        addclothebutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        active_tags = new ArrayList<String>();
                        primarytype = null;
                        secondarytype = null;
                        createAddClotheDialog();
                        // pickfromgallery();
                    }
                }
        );

        MYCL.post(
                new Runnable() {
                    @Override
                    public void run() {

                        wardrober(); // this function may take time so we need to make it that it doesn't cause a crash

                    }
                }
        );

    }

    public void createTagsDialog(){

        Log.d("tagsDB","we are entering the create dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog;

        builder.setTitle("Tags Selection:");

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View popapp = inflater.inflate(R.layout.tagsselectpopup, null);

        Log.d("tagsDB","the popup xml is added no problem");

        String user_tags_path = (String) ("Tags_of_" + Main_Menu.the_username_argument + ".obj");
        // we load the database
        TagsDB tagsDB = TagsDB.load( new File(getActivity().getFilesDir(), user_tags_path) );

        Log.d("tagsDB","the tags datbase is loade3d");

        TableLayout tabLayout = (TableLayout) popapp.findViewById(R.id.tagstable);
        tabLayout.removeAllViews();

        Log.d("tagsDB","the table layout is cleared");

        TableRow EG = new TableRow(getActivity());
        tabLayout.addView(EG);
        EG.setBackgroundColor(Color.BLUE);
        Button EGbutton = new Button(getActivity());
        EG.addView(EGbutton);
        EGbutton.getLayoutParams().height = dpTopx(20);
        EGbutton.setTextColor(Color.RED);
        EG.setVisibility(View.INVISIBLE);
        EG.setClickable(false);
        EGbutton.setVisibility(View.INVISIBLE);
        EGbutton.setClickable(false);

        TableRow current_row = new TableRow(getActivity());
        current_row.setGravity(Gravity.CENTER);
        tabLayout.addView(current_row);

        Log.d("tagsDB","the table row is added");

        for(int i=0; i < tagsDB.tags.size(); i++){

            String to_add = tagsDB.tags.get(i);
            Log.d("tagsDB","next tag name succ retrieved = " + to_add);

            Button button = new Button(getActivity());
            current_row.addView(button);

            Log.d("tagsDB","button added in the table row");

            button.setText(to_add);
            button.setTextSize(12);

            Log.d("tagsDB","button text configed");

            button.getLayoutParams().height = dpTopx(39);
            button.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;

            // we need to check if the text is in the ArrayList
            boolean isin = false;

            for(int ix=0;ix<active_tags.size();ix++){
                if( active_tags.get(ix).toString().equals(to_add) ){
                    isin = true;
                    break;
                }
            }
            if( !isin ){
                button.setBackground( ContextCompat.getDrawable( getActivity() , R.drawable.tagsbutton) );
                button.setTextColor( Color.parseColor("#7B1FA2") );
            }
            else{
                button.setBackground( ContextCompat.getDrawable( getActivity() , R.drawable.activatedtag ) );
                button.setTextColor( Color.parseColor("#FFFFFFFF") );
            }


            //Log.d("draw","thedraw = " + button.getBackground() )
            button.setOnClickListener(
                    new View.OnClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(View view) {
                            if( button.getCurrentTextColor() == Color.parseColor("#FFFFFFFF") ){
                                button.setBackground( ContextCompat.getDrawable( getActivity() , R.drawable.tagsbutton) );
                                button.setTextColor( Color.parseColor("#7B1FA2") );
                            }
                            else{
                                button.setBackground( ContextCompat.getDrawable( getActivity() , activatedtag) );
                                button.setTextColor( Color.parseColor("#FFFFFFFF") );
                            }
                        }
                    }
            );

            Log.d("tagsDB","button height and width configed");

            if( i % 3 == 0 && i > 0){
                // we need to remove the last added button
                current_row.removeView(button);
                current_row = new TableRow(getActivity());
                current_row.setGravity(Gravity.CENTER);
                tabLayout.addView(current_row);
                current_row.addView(button);
            }

        }

        TableRow EG2 = new TableRow(getActivity());
        tabLayout.addView(EG2);
        EG2.setBackgroundColor(Color.BLUE);
        Button EGbutton2 = new Button(getActivity());
        EG2.addView(EGbutton2);
        EGbutton2.getLayoutParams().height = dpTopx(20);
        EGbutton2.setTextColor(Color.RED);
        EG2.setVisibility(View.INVISIBLE);
        EG2.setClickable(false);
        EGbutton2.setVisibility(View.INVISIBLE);
        EGbutton2.setClickable(false);

        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int id) {
                // User clicked OK button

                Log.d("Dismissal","dismissal entered");
                active_tags = new ArrayList<String>();
                Log.d("Dismissal","the active tags have been reinitialized");
                // we need to gather the tags that are active
                Log.d("Dismissal","The child count for the TableLayout = " + tabLayout.getChildCount() );

                for(int i = 0; i < tabLayout.getChildCount(); i++){
                    TableRow tb = (TableRow) tabLayout.getChildAt(i);
                    for(int j=0; j<tb.getChildCount(); j++) {

                        Button v = (Button) tb.getChildAt(j);
                        Log.d("Dismissal", "the child at tb = " + i + "j = " + " is retrived");

                        if (v.getCurrentTextColor() == Color.parseColor("#FFFFFFFF")) {
                            Log.d("Dismissal", "color testing success");
                            active_tags.add(v.getText().toString());
                            Log.d("Dismissal", "adding it");
                        }

                    }

                }

                dialogInterface.dismiss();
            }
        });

        // to show the dialog
        builder.setView(popapp);
        dialog = builder.create();

        dialog.show();

        dialog.setOnDismissListener(
                new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {

                        // fixint the text view
                        TextView tvtags = the_fallen_view.findViewById(R.id.howmanytags);
                        tvtags.setText( active_tags.size() + " tag(s) selected" );

                    }
                }
        );

    }

    public void createTypeDialog(){

        should_I_save_type = false;

        Log.d("type","we are entering the create dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog;

        builder.setTitle("Select Type:");
        builder.setMessage("Select Primary and Secondary types.");

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View popapp = inflater.inflate(R.layout.typeselectionpopup, null);

        MainClotheType mainClotheType = new MainClotheType();

        // load the maintypes
        LinearLayout scrollViewprim = popapp.findViewById(R.id.primaryscroll);
        scrollViewprim.removeAllViews();

        for(int i = 0; !( mainClotheType.maintypes[i] == null ); i++){

            String to_add = mainClotheType.maintypes[i];
            Log.d("type","next tag name succ retrieved = " + to_add);

            Button button = new Button(getActivity());
            scrollViewprim.addView(button);

            Log.d("type","button added in the scroll view");

            button.setText(to_add);
            button.setTextSize( 18 );
            button.setPadding(dpTopx(10),0,dpTopx(10),0);

            Log.d("type","button text configed");

            button.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            button.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;

            button.setBackground( ContextCompat.getDrawable( getActivity() , R.drawable.tagsbutton) );
            button.setTextColor( Color.parseColor("#7B1FA2") );

            button.setOnClickListener(
                    new View.OnClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(View view) {

                            // search which button is parple and make it white
                            for(int ji = 0; ji < scrollViewprim.getChildCount(); ji++){
                                Button under = (Button) scrollViewprim.getChildAt(ji);
                                if( under.getCurrentTextColor() == Color.parseColor("#FFFFFFFF") ){
                                    under.setBackground( ContextCompat.getDrawable( getActivity() , R.drawable.tagsbutton) );
                                    under.setTextColor( Color.parseColor("#7B1FA2") );
                                    // don't break here
                                }
                            }

                            // make this one purple
                            button.setBackground( ContextCompat.getDrawable( getActivity() , activatedtag) );
                            button.setTextColor( Color.parseColor("#FFFFFFFF") );

                            // load the subtypes to the secondary scroll
                            String maintype = button.getText().toString();

                            MainClotheType mainClotheType1 = new MainClotheType();

                            for(int ji=0; !( mainClotheType1.maintypes[ji] == null ); ji++){
                                if( mainClotheType1.maintypes[ji].toString().equals(button.getText().toString()) ){
                                    LinearLayout scrollViewSecondary = popapp.findViewById(R.id.secondaryscroll);
                                    scrollViewSecondary.removeAllViews();

                                    for(int ij=0; mainClotheType1.subtypes[ji].length > ij; ij++){

                                        String to_add2 = mainClotheType.subtypes[ji][ij];
                                        Log.d("type","adding subtype = " + to_add2);

                                        Button button_secondary = new Button(getActivity());
                                        scrollViewSecondary.addView(button_secondary);

                                        Log.d("type","button added in the secondary scroll view");

                                        button_secondary.setText(to_add2);
                                        button_secondary.setTextSize( 18 );
                                        button_secondary.setPadding(dpTopx(10),0,dpTopx(10),0);

                                        Log.d("type","button secondary text configed");

                                        button_secondary.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                                        button_secondary.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;

                                        button_secondary.setBackground( ContextCompat.getDrawable( getActivity() , R.drawable.tagsbutton) );
                                        button_secondary.setTextColor( Color.parseColor("#7B1FA2") );

                                        // here add the listener for the subtype to save they type

                                        button_secondary.setOnClickListener(
                                                new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {

                                                        // make all of the secondary scroll views to be white
                                                        // search which button is parple and make it white
                                                        for(int jix = 0; jix < scrollViewSecondary.getChildCount(); jix++){
                                                            Button underx = (Button) scrollViewSecondary.getChildAt(jix);
                                                            if( underx.getCurrentTextColor() == Color.parseColor("#FFFFFFFF") ){
                                                                underx.setBackground( ContextCompat.getDrawable( getActivity() , R.drawable.tagsbutton) );
                                                                underx.setTextColor( Color.parseColor("#7B1FA2") );
                                                                // don't break here
                                                            }
                                                        }

                                                        // make this one purple
                                                        button_secondary.setBackground( ContextCompat.getDrawable( getActivity() , activatedtag) );
                                                        button_secondary.setTextColor( Color.parseColor("#FFFFFFFF") );

                                                        // put in the public variable this type combination
                                                        primarytype = button.getText().toString();
                                                        secondarytype = button_secondary.getText().toString();

                                                    }
                                                }
                                        );

                                    }
                                    break;
                                }
                            }

                        }
                    }
            );

        }

        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int id) {
                // User clicked OK button
                should_I_save_type = (primarytype != null);
                if(should_I_save_type) {
                    Log.d("whydoyoubrake", "comehere");
                    TextView textView = (TextView) the_fallen_view.findViewById(R.id.typeselected33);
                    Log.d("whydoyoubrake", "text view retrieved and is not " + (textView == null) );
                    textView.setText((String) (primarytype + " / " + secondarytype));
                    Log.d("whydoyoubrake", "text is set");
                }
                should_I_save_type = false;
                dialogInterface.dismiss();
            }
        });

        // to show the dialog
        builder.setView(popapp);
        dialog = builder.create();

        dialog.show();

    }

    public void createAddClotheDialog(){

        // check if we could load the main
        // MainClotheType mainClotheType = new MainClotheType();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog;

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View popapp = inflater.inflate(R.layout.addclothepop, null);

        the_fallen_view = popapp;

        receiver_preview = (ImageView) popapp.findViewById(R.id.cloth_preview);
        receiver_preview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mObserver.selectImage();
                        receiver_preview.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
        );

        Button selecttags = (Button) popapp.findViewById(R.id.selecttags);
        selecttags.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        createTagsDialog();

                    }
                }
        );

        Button selectype = (Button) popapp.findViewById(R.id.selecttype);
        selectype.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        createTypeDialog();

                    }
                }
        );


        Button addcloth = (Button) popapp.findViewById(R.id.addtowardrobe);

        builder.setView(popapp);
        dialog = builder.create();
        dialog.show();

        addcloth.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.d("justadd","Entering the listener!");

                        // check prerequesties

                        // checking the image
                        if(receiver_preview.getDrawable() == null){
                            Toast toast = Toast.makeText(view.getContext(),"You must select an image!",Toast.LENGTH_LONG);
                            toast.show();
                            return;
                        }

                        if(primarytype == null){
                            Toast toast = Toast.makeText(view.getContext(),"You must select a primary and secondary type!",Toast.LENGTH_LONG);
                            toast.show();
                            return;
                        }

                        // check that type has been selected

                        // to avoid crashes make all view unclickable here!!!
                        // TODO: MAKE THEM ALL UNCK

                        // first of all the popapps views should be not clickable from this point
                        LinearLayout ll = (LinearLayout) popapp;
                        for(int ixx=0; ixx < ll.getChildCount() ; ixx++){
                            View v = ll.getChildAt(ixx);
                            v.setActivated(false);
                            v.setClickable(false);
                        }


                        // Load the database
                        // now load the cloths into the n X 3 array to feed it on the the view
                        String user_clothes_path = (String) ("Clothes_of_" + Main_Menu.the_username_argument + ".obj");
                        // we load the database
                        ClothesDB clothesDB = ClothesDB.load( new File(getActivity().getFilesDir(), user_clothes_path) );
                        Log.d("justadd","Clothes database loaded!");

                        // ADD clothe to database here (and to the consistent table)
                        long clothe_ID = clothesDB.getNext_id();

                        // save the image located in bitmap_to_save variable to a specific path

                        // we need the file which points to the correct direcotry
                        String clothe_path = (String) ("Clothe_" + clothe_ID + "_of_" + Main_Menu.the_username_argument + ".obj");
                        File directory = new File( getActivity().getFilesDir() , clothe_path );
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(directory);
                            bitmap_to_save.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                            Log.d("justadd","Success the bitmap file is saved!");
                            // this supposedly saves the bitmap so all we need to retrieve it is the path
                        }
                        catch (Exception e){
                            Log.d("justadd","could not write the object for the new clothe");
                        }

                        // retrieve filters
                        ArrayList <String> tags = new ArrayList<String>(active_tags);
                        String type = "" + primarytype + "/" + secondarytype;

                        // now fill the clothes data fields
                        Clothe clothe = new Clothe(clothe_ID,tags,directory,type);
                        Log.d("justadd","clothe created!");

                        // we add it to the database
                        clothesDB.clotheArrayList.add(clothe);
                        ClothesDB.store( new File(getActivity().getFilesDir(), user_clothes_path) , clothesDB );
                        Log.d("justadd","clothe added and database updated!");

                        // let's update the consistent table
                        //consistent_pinakas_rouxon[slot_to_add_i][slot_to_add_j] = clothe;
                        //Log.d("justadd","consistent table updated!");

                        putAt(slot_to_add_i,slot_to_add_j,clothe);
                        Log.d("justadd","clothe putted at view!");

                        // THEN add it to the
                        dialog.dismiss();

                        // RESTORE! => to avoid crashes make all view unclickable here!!!
                        // TODO: RESTORE -> MAKE THEM ALL UNCK

                    }
                }
        );

    }

    public void wardrober(){

        // clears the previews clothes and puts the updated ones

        // clear the mock/previews clothes
        ConstraintLayout clothes_plain = MYCL.findViewById(R.id.ClothesPlain);
        clothes_plain.removeAllViews();
        slot_to_add_i = 0;
        slot_to_add_j = 0;

        // now load the cloths into the n X 3 array to feed it on the the view
        String user_clothes_path = (String) ("Clothes_of_" + Main_Menu.the_username_argument + ".obj");
        Log.d("justadd","the path for the clothDB is " + user_clothes_path);
        // we load the database
        ClothesDB clothesDB = ClothesDB.load( new File(getActivity().getFilesDir(), user_clothes_path) );

        // defining the 2d array
        int div = clothesDB.clotheArrayList.size() / 3;
        int mod = ( clothesDB.clotheArrayList.size() % 3 != 0 ) ? 1 : 0;
        //consistent_pinakas_rouxon = new Clothe[ div + mod ][3]; // resetting the table from the bat

        int tot = 0;

        Log.d("justadd","We have div = " + div + "and mod = " + mod);
        for(int i = 0; i < div+mod; i++){
            for(int j = 0; j < 3; j++){

                // check if we are out-of-bounds
                tot++;
                if( tot > clothesDB.clotheArrayList.size() ){
                    Log.d("justadd","out of bounds of the clothes array list");
                    break;
                }

                // consistent_pinakas_rouxon[i][j] = clothesDB.clotheArrayList.get(tot-1);
                // we put the clothe in the wardrobe view
                putAt(i,j, clothesDB.clotheArrayList.get(tot-1) );
                Log.d("justadd","putted i = " + i + " and j = " + j);

            }
        }

    }

    void putAt(int i, int j, Clothe clothe){

        // open the next slot to add
        slot_to_add_i = i;
        slot_to_add_j = j+1;
        if(slot_to_add_j > 2){
            slot_to_add_i++;
            slot_to_add_j = 0;
        }
        Log.d("justadd","next open slot coordinates fixed!");

        // first create the imageview and set bitmap and properties
        ImageView imageView = new ImageView(getActivity());

        try {
            FileInputStream fileInputStream = new FileInputStream(clothe.image_dir);
            Log.d("justadd","fileinputstream fine!");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            Log.d("justadd","buffered inputstream fine!");
            Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            Log.d("justadd","bitmap decoded fine!");
            imageView.setImageBitmap(bitmap);
            Log.d("justadd","bitmap set fine!");
        }
        catch (Exception e){
            Log.d("justadd","The image could not be loaded to the imageview");
        }

        ConstraintLayout clothes_plain = MYCL.findViewById(R.id.ClothesPlain);
        Log.d("justadd","layout retrieved correctly!");

        // now add the imageview to the layout
        clothes_plain.addView(imageView);
        Log.d("justadd","view added successfully!");

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(clothes_plain.getLayoutParams());

        params.topMargin = dpTopx(10);
        params.leftMargin = dpTopx(10);

        if( i == 0 ){
            params.topToTop = 0;
        }
        else{
            params.topToBottom = ( (ImageView) clothes_plain.findViewById( (i*3) + 1 ) ).getId();
        }
        if(j == 0){
            params.leftToLeft = 0;
        }
        else{
            params.leftToRight = ( (ImageView) clothes_plain.findViewById( ( (i+1) *3 ) + (j) ) ).getId();
        }
        imageView.setLayoutParams(params);

        imageView.getLayoutParams().height = dpTopx(110);
        imageView.getLayoutParams().width = dpTopx(110);
        Log.d("clothes","height and width set successfully");

        imageView.setId( (i+1) * 3 + (j + 1) ); // very important stuff here
        Log.d("clothes","id set successfully");

        // now adjust it's margins based on the ids of the previous ones (top and left)

    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public int dpTopx(int dp){
        int px = ( dp * 100 ) / pxToDp(100);
        return px;
    }

}