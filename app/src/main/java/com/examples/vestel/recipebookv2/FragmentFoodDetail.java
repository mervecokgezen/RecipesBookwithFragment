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

    TextView tv_foodname, tv_fooditems, tv_foodcooking, tv_writer;
    Button btn_fooddelete;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference, dbRef;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String foodname;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStated){

        View viewFood = inflater.inflate(R.layout.fragment_fooddetail, container, false);

        tv_foodname    = (TextView)viewFood.findViewById(R.id.tv_foodname);
        tv_fooditems   = (TextView)viewFood.findViewById(R.id.tv_fooditems);
        tv_foodcooking = (TextView)viewFood.findViewById(R.id.tv_cooking);
        btn_fooddelete = (Button)viewFood.findViewById(R.id.btn_delete);
        tv_writer      = (TextView)viewFood.findViewById(R.id.tv_writer);

        tv_foodname.setText(getArguments().getString("tv_foodname"));
        tv_fooditems.setText(getArguments().getString("tv_fooditems"));
        tv_foodcooking.setText(getArguments().getString("tv_cooking"));
        tv_writer.setText(getArguments().getString("tv_writer"));

        btn_fooddelete.setOnClickListener(this);

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
        }
    }
}
