package com.example.jurizo.bitacora;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jurizo.bitacora.CoreBitacoraMVA.controllers.AnswersSegmentController;
import com.example.jurizo.bitacora.CoreBitacoraMVA.controllers.QuestionSegmentController;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.AnswerSegment;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.Office;
import com.example.jurizo.bitacora.CoreBitacoraMVA.models.QuestionSegment;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class VisitLogisticaFragment extends Fragment {

    private View rootView;
    private TextView question1;
    private TextView question2;
    private TextView question3;
    private TextView question4;
    private TextView question5;
    private Spinner answers1;
    private Spinner answers2;
    private Spinner answers3;
    private Spinner answers4;
    private Spinner answers5;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_visit_logistica, container, false);
        initialize_controls();
        Office os = ((HelperBitacora)getActivity().getApplication()).getOffice();
        if(os != null){
            updateQuestions(os);
        }
        return rootView;
    }

    private void initialize_controls() {
        question1 = (TextView) rootView.findViewById(R.id.visit_logistica_question_one);
        question2 = (TextView) rootView.findViewById(R.id.visit_logistica_question_two);
        question3 = (TextView) rootView.findViewById(R.id.visit_logistica_question_three);
        question4 = (TextView) rootView.findViewById(R.id.visit_logistica_question_four);
        question5 = (TextView) rootView.findViewById(R.id.visit_logistica_question_five);
        answers1 = (Spinner) rootView.findViewById(R.id.visit_logistica_spinner_one);
        answers2 = (Spinner) rootView.findViewById(R.id.visit_logistica_spinner_two);
        answers3 = (Spinner) rootView.findViewById(R.id.visit_logistica_spinner_three);
        answers4 = (Spinner) rootView.findViewById(R.id.visit_logistica_spinner_four);
        answers5 = (Spinner) rootView.findViewById(R.id.visit_logistica_spinner_five);
    }

    private void updateQuestions(Office os) {
        QuestionSegmentController qsc = new QuestionSegmentController(getContext());
        List<QuestionSegment> qusSeg = qsc.getQuestionSegmentById(os.getSegment_logistics());
        if(qusSeg != null){
            List<AnswerSegment> ansguers = new ArrayList<>();
            for(QuestionSegment questionSegment : qusSeg){
                AnswersSegmentController asc = new AnswersSegmentController(getContext());
                List<AnswerSegment> answerSegmentsQuestion = asc.getAnswersQuestionsByIdQuestion(questionSegment.getId());
                ansguers.addAll(answerSegmentsQuestion);
            }
            if(qusSeg != null && ansguers != null){
                updateViewContent(qusSeg, ansguers);
            }
        }
    }

    private void updateViewContent(List<QuestionSegment> qusSeg, List<AnswerSegment> ansguers) {
    }

}
