package com.example.jurizo.bitacora;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
        question1.setVisibility(View.INVISIBLE);
        question2.setVisibility(View.INVISIBLE);
        question3.setVisibility(View.INVISIBLE);
        question4.setVisibility(View.INVISIBLE);
        question5.setVisibility(View.INVISIBLE);
        answers1.setVisibility(View.INVISIBLE);
        answers2.setVisibility(View.INVISIBLE);
        answers3.setVisibility(View.INVISIBLE);
        answers4.setVisibility(View.INVISIBLE);
        answers5.setVisibility(View.INVISIBLE);


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
        for (QuestionSegment question : qusSeg){
            int questionNumber = 1;
            switch (questionNumber){
                case 1:
                    question1.setText(question.getQuestion());
                    ArrayList<String> answerquestion1 = new ArrayList<>();
                    for(AnswerSegment answer : ansguers){
                        if(answer.getQuestions_segment_id() == question.getId()){
                            answerquestion1.add(answer.getAnsware());
                        }
                    }
                    final ArrayAdapter<String> adapterAnswer = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, answerquestion1);
                    answers1.setAdapter(adapterAnswer);
                    question1.setVisibility(View.VISIBLE);
                    answers1.setVisibility(View.VISIBLE);
                    answers1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    break;
                case 2:
                    question2.setText(question.getQuestion());
                    ArrayList<String> answerquestion2 = new ArrayList<>();
                    for(AnswerSegment answer : ansguers){
                        if(answer.getQuestions_segment_id() == question.getId()){
                            answerquestion2.add(answer.getAnsware());
                        }
                    }
                    ArrayAdapter<String> adapterAnswer2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, answerquestion2);
                    answers2.setAdapter(adapterAnswer2);
                    question2.setVisibility(View.VISIBLE);
                    answers2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    question3.setText(question.getQuestion());
                    ArrayList<String> answerquestion3 = new ArrayList<>();
                    for(AnswerSegment answer : ansguers){
                        if(answer.getQuestions_segment_id() == question.getId()){
                            answerquestion3.add(answer.getAnsware());
                        }
                    }
                    ArrayAdapter<String> adapterAnswer3 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, answerquestion3);
                    answers3.setAdapter(adapterAnswer3);
                    question3.setVisibility(View.VISIBLE);
                    answers3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    question4.setText(question.getQuestion());
                    ArrayList<String> answerquestion4 = new ArrayList<>();
                    for(AnswerSegment answer : ansguers){
                        if(answer.getQuestions_segment_id() == question.getId()){
                            answerquestion4.add(answer.getAnsware());
                        }
                    }
                    ArrayAdapter<String> adapterAnswer4 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, answerquestion4);
                    answers4.setAdapter(adapterAnswer4);
                    question4.setVisibility(View.VISIBLE);
                    answers4.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    question5.setText(question.getQuestion());
                    ArrayList<String> answerquestion5 = new ArrayList<>();
                    for(AnswerSegment answer : ansguers){
                        if(answer.getQuestions_segment_id() == question.getId()){
                            answerquestion5.add(answer.getAnsware());
                        }
                    }
                    ArrayAdapter<String> adapterAnswer5 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, answerquestion5);
                    answers5.setAdapter(adapterAnswer5);
                    question5.setVisibility(View.VISIBLE);
                    answers5.setVisibility(View.VISIBLE);
                    break;

            }
            questionNumber++;
        }
    }

}
