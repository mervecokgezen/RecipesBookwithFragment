package com.examples.vestel.recipebookv2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by vestel on 29.03.2018.
 */

public class FragmentUpdateFood extends android.support.v4.app.Fragment implements View.OnClickListener{
    EditText edt_foodname, edt_foodmetarials, edt_cooking;
    String foodname, foodmetarials, cooking, foodcategory, foodID, supplementary, usermail;
    Button btn_update;

    String fname, fitems, fcooking, fsupplementary, fwriter, fid, fcategory;

    DatabaseReference databaseReference, dbRef;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    Food food;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStated) {

        View viewUpdateFoodInfos = inflater.inflate(R.layout.fragment_updatefood, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Recipes");
        firebaseAuth      = FirebaseAuth.getInstance();
        firebaseUser      = firebaseAuth.getCurrentUser();

        edt_foodname        = (EditText)viewUpdateFoodInfos.findViewById(R.id.edt_foodname);
        edt_foodmetarials   = (EditText)viewUpdateFoodInfos.findViewById(R.id.edt_fooditem);
        edt_cooking         = (EditText)viewUpdateFoodInfos.findViewById(R.id.edt_cooking);
        btn_update          = (Button)viewUpdateFoodInfos.findViewById(R.id.btn_update);


        foodname        = getArguments().getString("food_name");
        foodmetarials   = getArguments().getString("food_items");
        cooking         = getArguments().getString("food_cooking");

        edt_foodname.setText(foodname);
        edt_foodmetarials.setText(foodmetarials);
        edt_cooking.setText(cooking);

        btn_update.setOnClickListener(this);
        return viewUpdateFoodInfos;
    }

    public void UpdateFood(String fname, String fitems, String fcooking, String fsupplementary,String fwriter, String fid, String fcategory){

        food = new Food(fname, fitems, fcooking, fsupplementary, fwriter, fid, fcategory);
        databaseReference.child(fid).setValue(food);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_update:{

                fname           = edt_foodname.getText().toString();
                fitems          = edt_foodmetarials.getText().toString();
                fcooking        = edt_cooking.getText().toString();
                fsupplementary  = getArguments().getString("food_supplementary");
                fwriter         = getArguments().getString("user_mail");
                fid             = getArguments().getString("food_id");
                fcategory       = getArguments().getString("food_category");

                UpdateFood(fname, fitems, fcooking, fsupplementary, fwriter, fid, fcategory);

                Bundle bundle = new Bundle();

                android.support.v4.app.Fragment fragment = new FragmentMyRecipes();
                fragment.setArguments(bundle);

                ((RecipesActivity) getActivity()).updateFragment(fragment);
            }}
    }
}
