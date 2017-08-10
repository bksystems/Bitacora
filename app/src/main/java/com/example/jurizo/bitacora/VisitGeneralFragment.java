package com.example.jurizo.bitacora;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.CompletionInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jurizo.bitacora.CoreBitacoraMVA.controllers.OfficesController;
import com.example.jurizo.bitacora.CoreBitacoraMVA.controllers.SegmentController;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Office;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Segment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisitGeneralFragment extends Fragment {


    private View rootView;
    private ArrayAdapter<String> adapter;
    private TextView labelDireccion;
    private TextView labelSubdireccion;
    private TextView labelRegiion;
    private TextView labelCC;
    private TextView labelOffice;
    private TextView labelSegmentPL;
    private TextView labelSegmentMV;
    private TextView labelPlantillaAut;
    private EditText txtPlantillaReal;
    private GoogleMap mMap;
    private MapView mapView;

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

        final OfficesController officesController = new OfficesController(getContext());
        List<Office> offices = officesController.getOfficesDataBase();
        if(offices != null && offices.size() > 0){
            List<String> officesArray = new ArrayList<>();
            for(Office ofi : offices){
                officesArray.add(ofi.getOffice());
            }
            if(officesArray != null && officesArray.size() > 0){
                adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, officesArray);
                autoCompleteTextViewOficinas.setAdapter(adapter);
                autoCompleteTextViewOficinas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String os = adapter.getItem(position);
                        Office officeSelected = officesController.GetOfficeByName(os);
                        if(officeSelected != null){
                            SegmentController segmentController = new SegmentController(getContext());
                            Segment sgmMovilidad = segmentController.getSegmentById(officeSelected.getSegment_mobility());
                            Segment sgmLogistica = segmentController.getSegmentById(officeSelected.getSegment_logistics());
                            UpdateViewOfficeSelecte(officeSelected, sgmLogistica, sgmMovilidad, 1);
                        }
                    }
                });
            }


        }
    }

    private void UpdateViewOfficeSelecte(Office officeSelected, Segment segmentPL, Segment segmentMV, int option) {
        switch (option){
            case 0:
                labelDireccion.setText("");
                labelSubdireccion.setText("");
                labelRegiion.setText("");
                labelCC.setText("");
                labelOffice.setText("");
                labelSegmentPL.setText("");
                labelSegmentMV.setText("");
                labelPlantillaAut.setText("");
                txtPlantillaReal.setText("");
                break;
            case 1:
                labelDireccion.setText(officeSelected.getDirecction());
                labelSubdireccion.setText(officeSelected.getSubdirection());
                labelRegiion.setText(officeSelected.getRegion());
                labelCC.setText(String.valueOf(officeSelected.getCost_center()));
                labelOffice.setText(officeSelected.getOffice());
                labelSegmentPL.setText(segmentPL.getSegment());
                labelSegmentMV.setText(segmentMV.getSegment());
                labelPlantillaAut.setText(String.valueOf(officeSelected.getAuthorized_employees()));
                txtPlantillaReal.setText(String.valueOf(officeSelected.getActive_employees()));
                UpdateMap(officeSelected);
                break;
        }
    }

    private void UpdateMap(Office ofc) {
        if(ofc != null) {
            LatLng point = new LatLng(ofc.getLatitude(), ofc.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(point)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            );
            mMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_visit_general, container, false);
        loadAutoCompleteOficinas();
        initializeComponents();
        UpdateViewOfficeSelecte(null, null, null, 0);
        mapView = (MapView) rootView.findViewById(R.id.visit_general_mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                setUpMap(googleMap);
            }
        });
        return rootView;
    }

    private void initializeComponents() {
        labelDireccion = (TextView) rootView.findViewById(R.id.visit_general_txt_direction);
        labelSubdireccion = (TextView) rootView.findViewById(R.id.visit_general_txt_subdirection);
        labelRegiion = (TextView) rootView.findViewById(R.id.visit_general_txt_region);
        labelCC = (TextView) rootView.findViewById(R.id.visit_general_txt_cc);
        labelOffice = (TextView) rootView.findViewById(R.id.visit_general_txt_oficce);
        labelSegmentPL = (TextView) rootView.findViewById(R.id.visit_general_txt_segment_pl);
        labelSegmentMV = (TextView) rootView.findViewById(R.id.visit_general_txt_segment_mv);
        labelPlantillaAut = (TextView) rootView.findViewById(R.id.visit_general_txt_plantilla);
        txtPlantillaReal = (EditText) rootView.findViewById(R.id.visit_general_txt_plantilla_real);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    private void setUpMap(GoogleMap map) {
        mMap = map;
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        );
    }
}

