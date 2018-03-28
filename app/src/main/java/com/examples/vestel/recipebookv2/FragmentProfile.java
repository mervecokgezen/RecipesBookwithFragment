package com.examples.vestel.recipebookv2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vestel on 27.03.2018.
 */

public class FragmentProfile extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceStated){

        return inflater.inflate(R.layout.fragment_profile, container, false);

    }
}
