package com.example.jurizo.bitacora;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jurizo.bitacora.CoreBitacoraMVA.controllers.QuestionSegmentController;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Office;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.QuestionSegment;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class VisitLogisticaFragment extends Fragment {

    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_visit_logistica, container, false);
        Office os = ((HelperBitacora)getActivity().getApplication()).getOffice();
        if(os != null){
            updateQuestions(os);
        }
        return rootView;
    }

    private void updateQuestions(Office os) {
        QuestionSegmentController qsc = new QuestionSegmentController(getContext());
        List<QuestionSegment> qusSeg = qsc.getQuestionSegmentById(os.getSegment_logistics());
        if(qusSeg != null){

        }
    }

}
