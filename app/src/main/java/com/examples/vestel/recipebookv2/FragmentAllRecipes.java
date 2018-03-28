package com.examples.vestel.recipebookv2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vestel on 28.03.2018.
 */

public class FragmentAllRecipes extends Fragment {

    private DatabaseReference databaseReference ;
    private FirebaseDatabase firebaseDatabase ;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private RecyclerView recyclerView;
    private List<Food> list_food;
    private Food f;
    private String sup;
    private String userid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceStated) {

        firebaseAuth        = FirebaseAuth.getInstance();
        firebaseUser        = firebaseAuth.getCurrentUser();
        firebaseDatabase    = FirebaseDatabase.getInstance();
        databaseReference   = firebaseDatabase.getReference().child("Recipes");

        userid = firebaseAuth.getCurrentUser().getUid();

        final View viewMyRecipesInfos = inflater.inflate(R.layout.fragment_allrecipes, container, false);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list_food = new ArrayList<Food>();

                for(DataSnapshot ds :dataSnapshot.getChildren())
                {

                    f = new Food(ds.child("food_name").getValue().toString(),ds.child("food_items").getValue().toString(),ds.child("cooking").getValue().toString(), ds.child("supplementary").getValue().toString(),
                            ds.child("writer").getValue().toString(), ds.child("food_id").getValue().toString(), ds.child("food_category").getValue().toString());
                    list_food.add(f);

                }
                recyclerView = (RecyclerView)viewMyRecipesInfos.findViewById(R.id.recycler_view) ;
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                linearLayoutManager.scrollToPosition(0);

                recyclerView.setLayoutManager(linearLayoutManager);

                SimpleRecyclerView adapter_items = new SimpleRecyclerView(list_food, new Food.CustomItemClickListener(){

                    @Override
                    public void onItemClick(View v, int position) {
                        //Log.d("position", "Tıklanan Pozisyon:" + position);
                        Food food = list_food.get(position);
                        android.support.v4.app.Fragment replaceFragment = null;
                        replaceFragment = new FragmentAllFoodDetail();
                        Bundle bundle = new Bundle();
                        bundle.putString("tv_foodname", food.getFood_name());
                        bundle.putString("tv_fooditems", food.getFood_items());
                        bundle.putString("tv_cooking", food.getCooking());

                        replaceFragment.setArguments(bundle);

                        ((RecipesActivity) getActivity()).updateFragment(replaceFragment);

                    }
                });
                recyclerView.setAdapter(adapter_items);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return viewMyRecipesInfos;
    }
}
