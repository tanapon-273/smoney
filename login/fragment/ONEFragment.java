package com.example.kittisaks.login.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kittisaks.login.R;
import com.example.kittisaks.login.fragment.ONE_Expenditure;
import com.example.kittisaks.login.fragment.ONE_Revenue;


/**
 * A simple {@link Fragment} subclass.
 */
public class ONEFragment extends Fragment {



    public ONEFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        android.support.v4.app.FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.add(R.id.linearLayout1,new ONE_Revenue());
        trans.commit();

        android.support.v4.app.FragmentTransaction trans1 = getFragmentManager().beginTransaction();
        trans1.add(R.id.linearLayout2,new ONE_Expenditure());
        trans1.commit();
        return view;
    }

}
