package com.tasdasdsaduiz.what_to_wear;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
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
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Wardrobe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Wardrobe extends Fragment {

    public ConstraintLayout MYCL;
    public ImageView receiver_preview;
    public Clothe consistent_pinakas_rouxon[][];
    public Bitmap bitmap_to_save;

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

    public void createAddClotheDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog;

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View popapp = inflater.inflate(R.layout.addclothepop, null);

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

        Button addcloth = (Button) popapp.findViewById(R.id.addtowardrobe);

        builder.setView(popapp);
        dialog = builder.create();
        dialog.show();

        addcloth.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // ADD clothe to database here

                        /*ContextWrapper cw = new ContextWrapper(getApplicationContext());
                        File directory = cw.getDir("profile", Context.MODE_PRIVATE);
                        if (!directory.exists()) {
                            directory.mkdir();
                        }
                        File mypath = new File(directory, "thumbnail.png");
                        // we first of all need to save the bitmap as object
                        FileOutputStream fos = null;
                        String image_path = getActivity().getFilesDir();
                        try{
                            fos = new FileOutputStream()
                        }*/

                        // THEN add it to the
                        dialog.dismiss();
                    }
                }
        );

    }

    public void wardrober(){

        // clears the previews clothes and puts the updated ones

        // clear the mock/previews clothes
        ConstraintLayout clothes_plain = MYCL.findViewById(R.id.ClothesPlain);
        clothes_plain.removeAllViews();

        // now load the cloths into the n X 3 array to feed it on the the view
        String user_clothes_path = (String) ("Clothes_of_" + Main_Menu.the_username_argument + ".obj");
        // we load the database
        ClothesDB clothesDB = ClothesDB.load( new File(getActivity().getFilesDir(), user_clothes_path) );

        // defining the 2d array
        int div = clothesDB.clotheArrayList.size() / 3;
        int mod = ( clothesDB.clotheArrayList.size() % 3 == 0 ) ? 1 : 0;
        consistent_pinakas_rouxon = new Clothe[ div + mod ][3];

        int tot = 0;

        for(int i = 0; i < div+mod; i++){
            for(int j = 0; j < 3; j++){

                // check if we are out-of-bounds
                tot++;
                if( tot > clothesDB.clotheArrayList.size() ){
                    break;
                }

                consistent_pinakas_rouxon[i][j] = clothesDB.clotheArrayList.get(tot-1);
                // we put the clothe in the wardrobe view
                putAt(i,j,consistent_pinakas_rouxon[i][j]);

            }
        }

    }

    void putAt(int i, int j, Clothe clothe){

        // first create the imageview
        ImageView imageView;




    }


}