package com.example.jurizo.bitacora;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.jurizo.bitacora.CoreBitacoraMVA.controllers.OfficesController;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Office;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisitGeneralFragment extends Fragment {


    private View rootView;

    public static VisitGeneralFragment newInstance() {
        // Required empty public constructor
        VisitGeneralFragment fragment = new VisitGeneralFragment();
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void loadAutoCompleteOficinas() {
        AutoCompleteTextView autoCompleteTextViewOficinas;
        autoCompleteTextViewOficinas = (AutoCompleteTextView) rootView.findViewById(R.id.visit_general_oficinas);

        OfficesController officesController = new OfficesController(getContext());
        List<Office> offices = officesController.getOfficesDataBase();
        if(offices != null && offices.size() > 0){
            List<String> officesArray = new ArrayList<>();
            for(Office ofi : offices){
                officesArray.add(ofi.getOffice());
            }
            if(officesArray != null && officesArray.size() > 0){
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, officesArray);
                autoCompleteTextViewOficinas.setAdapter(adapter);
            }


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_visit_general, container, false);
        rootView = inflater.inflate(R.layout.fragment_visit_general, container, false);
        loadAutoCompleteOficinas();
        return rootView;
    }

}
