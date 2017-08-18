package com.example.jurizo.bitacora;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class VisitMovilidadFragment extends Fragment {


    /*public static VisitMovilidadFragment newInstance() {
        // Required empty public constructor
        VisitMovilidadFragment fragment = new VisitMovilidadFragment();
        return fragment;
    }*/


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visit_movilidad, container, false);
    }

}
