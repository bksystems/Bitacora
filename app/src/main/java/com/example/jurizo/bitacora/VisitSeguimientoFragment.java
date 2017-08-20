package com.example.jurizo.bitacora;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Office;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Visit;


/**
 * A simple {@link Fragment} subclass.
 */
public class VisitSeguimientoFragment extends Fragment {
    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_visit_seguimiento, container, false);
        FloatingActionButton add_segimiento = (FloatingActionButton)rootView.findViewById(R.id.visit_segimiento_add);
        add_segimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Office os = ((HelperBitacora)getActivity().getApplication()).getOffice();
                if(os!= null) {
                    Intent intentTracing = new Intent(getActivity(), TracingActivity.class);
                    intentTracing.putExtra("cc", os.getCost_center());
                    startActivity(intentTracing);
                }else{
                    Toast.makeText(getContext(), "No ha seleccionado ninguna oficina", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }

}
