package com.examples.vestel.recipebookv2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by vestel on 28.03.2018.
 */

public class FragmentAllFoodDetail extends Fragment {

    private TextView tv_foodname,tv_fooditems,tv_foodcooking ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStated){

        View viewFood = inflater.inflate(R.layout.fragment_allfooddetail, container, false);

        tv_foodname = (TextView)viewFood.findViewById(R.id.tv_foodnamee);
        tv_fooditems = (TextView)viewFood.findViewById(R.id.tv_fooditemss);
        tv_foodcooking = (TextView)viewFood.findViewById(R.id.tv_cookingg);

        tv_foodname.setText(getArguments().getString("tv_foodname"));
        tv_fooditems.setText(getArguments().getString("tv_fooditems"));
        tv_foodcooking.setText(getArguments().getString("tv_cooking"));


        return viewFood;

    }

}
