package com.examples.vestel.recipebookv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by vestel on 27.03.2018.
 */

public class FragmentFoodDetail extends Fragment implements View.OnClickListener {

    private TextView tv_foodname, tv_fooditems, tv_foodcooking,tv_supplementary, tv_writer, tv_foodid, tv_category;
    String food_name,  food_items,  cooking,  supplementary,  writer,  food_id,  food_category;
    private Button btn_fooddelete, btn_updatego;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference, dbRef;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String foodname;
    private Food food;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStated){

        View viewFood = inflater.inflate(R.layout.fragment_fooddetail, container, false);

        tv_foodname    = (TextView)viewFood.findViewById(R.id.tv_foodname);
        tv_fooditems   = (TextView)viewFood.findViewById(R.id.tv_fooditems);
        tv_foodcooking = (TextView)viewFood.findViewById(R.id.tv_cooking);
        btn_fooddelete = (Button)viewFood.findViewById(R.id.btn_delete);
        tv_writer      = (TextView)viewFood.findViewById(R.id.tv_writer);
        btn_updatego   = (Button)viewFood.findViewById(R.id.btn_updatego);

        tv_foodname.setText(getArguments().getString("tv_foodname"));
        tv_fooditems.setText(getArguments().getString("tv_fooditems"));
        tv_foodcooking.setText(getArguments().getString("tv_cooking"));
        tv_writer.setText(getArguments().getString("tv_writer"));

        food_name       = getArguments().getString("tv_foodname");
        food_items      = getArguments().getString("tv_fooditems");
        cooking         = getArguments().getString("tv_cooking");
        supplementary   = getArguments().getString("tv_supplementary");
        food_id         = getArguments().getString("food_id");
        writer          = getArguments().getString("tv_writer");
        food_category   = getArguments().getString("tv_foodcategory");


        btn_fooddelete.setOnClickListener(this);
        btn_updatego.setOnClickListener(this);

        return viewFood;

    }


    @Override
    public void onClick(View view) {
        firebaseAuth      = FirebaseAuth.getInstance();
        firebaseUser      = firebaseAuth.getCurrentUser();
        firebaseDatabase  = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Recipes");
        switch (view.getId()){
            case R.id.btn_delete:{
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dbRef = firebaseDatabase.getReference().child("Recipes").child(getArguments().getString("food_id"));
                        dbRef.removeValue();

                        android.support.v4.app.Fragment fragment = new FragmentMyRecipes();

                        ((RecipesActivity) getActivity()).updateFragment(fragment);

                        }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                break;
            }
            case R.id.btn_updatego:{
                Log.d("CLICK","UPDATE CLICK");

                //bundle.putString("tv_foodname", food.getFood_name());

                food = new Food(food_name, food_items, cooking, supplementary, food_id, writer, food_category);

                Bundle bundle = new Bundle();
                bundle.putString("food_name",           food.getFood_name());
                bundle.putString("food_items",          food.getFood_items());
                bundle.putString("food_cooking",        food.getCooking());
                bundle.putString("food_supplementary",  food.getSupplementary());
                bundle.putString("food_id",             food.getFood_id());
                bundle.putString("food_category",       food.getFood_category());
                bundle.putString("user_mail",           food.getWriter());

                Log.d("NAMEEEEE" ,food.getFood_name());


                android.support.v4.app.Fragment fragment = new FragmentUpdateFood();
                fragment.setArguments(bundle);

                ((RecipesActivity) getActivity()).updateFragment(fragment);

            }
        }
    }
}
