package com.example.jurizo.bitacora;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DAOs.DAO_Oficinas;
import com.example.jurizo.bitacora.Core.CoreBitacora.Database.DBHelper;
import com.example.jurizo.bitacora.Core.CoreBitacora.Entity.EntityOficina;

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

        DAO_Oficinas daoOficinas = new DAO_Oficinas(getContext());
        List<EntityOficina> oficinas = daoOficinas.getOficinas();
        String[] oficinasArray = new String[oficinas.size()];
        for (int i =0; i < oficinas.size(); i++){
            oficinasArray[i] = oficinas.get(i).getOficina();
        }
        ArrayAdapter<String> adapter = null;
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, oficinasArray);
        autoCompleteTextViewOficinas.setAdapter(adapter);
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
