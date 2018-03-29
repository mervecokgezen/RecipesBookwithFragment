package com.examples.vestel.recipebookv2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by vestel on 27.03.2018.
 */

public class FragmentAddFood extends android.support.v4.app.Fragment {


    EditText edt_foodname, edt_foodmetarials, edt_cooking;
    String foodname, foodmetarials, cooking, currentby;
    Button btn_addfood;

    DatabaseReference databaseReference, dbRef;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStated){

        //return inflater.inflate(R.layout.fragment_addfood, container, false);

        View viewAddFoodInfos = inflater.inflate(R.layout.fragment_addfood, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("Recipes");
        firebaseAuth      = FirebaseAuth.getInstance();
        firebaseUser      = firebaseAuth.getCurrentUser();

        edt_foodname      = (EditText)viewAddFoodInfos.findViewById(R.id.edt_foodname);
        edt_foodmetarials = (EditText)viewAddFoodInfos.findViewById(R.id.edt_fooditem);
        edt_cooking       = (EditText)viewAddFoodInfos.findViewById(R.id.edt_cooking);
        btn_addfood       = (Button) viewAddFoodInfos.findViewById(R.id.btn_addfood);

        btn_addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodname        = edt_foodname.getText().toString();
                foodmetarials   = edt_foodmetarials.getText().toString();
                cooking         = edt_cooking.getText().toString();

                String userID   = firebaseAuth.getCurrentUser().getUid();
                String usermail = firebaseAuth.getCurrentUser().getEmail();
                String foodID   = databaseReference.push().getKey();
                String food_category = "";

                Food food = new Food(foodname, foodmetarials, cooking, userID, usermail, foodID, food_category);
                AddFood(food, foodID);

                android.support.v4.app.Fragment replaceFragment = new FragmentMyRecipes();

                ((RecipesActivity) getActivity()).updateFragment(replaceFragment);

            }
        });

        return  viewAddFoodInfos;
    }

    private void AddFood(Food food, String foodID){

        databaseReference.child(foodID).setValue(food);

    }


}
