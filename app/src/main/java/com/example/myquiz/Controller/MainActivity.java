package com.example.myquiz.Controller;

import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myquiz.Model.AnalystQuest;
import com.example.myquiz.Model.Question;
import com.example.myquiz.Model.QuestionRepository;
import com.example.myquiz.R;

public class MainActivity extends AppCompatActivity {

    private QuestionRepository repository = new QuestionRepository();
    private int indice_question = 0;
    private TextView textViewQuestion;
    private Button buttonAnswer1;
    private Button buttonAnswer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Question question = repository.getListQuestion().get(0);

        // Instanciando a view
        textViewQuestion = findViewById(R.id.textView2);
        textViewQuestion.setText(question.getText());

        /**
         * Criação dos Listeners do Buttons
         */
        final View.OnClickListener listenerButtonAnswer = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String answer = ((Button)v).getText().toString();

                AnalystQuest analystQuest = new AnalystQuest();
                Question question = repository.getListQuestion().get(indice_question);

                String mensagem;

                if (analystQuest.isAnswearCorrect(question, Double.valueOf(answer) )) {
                    mensagem = "Parabens, resposta correta";
                }
                else {
                    mensagem = "Resposta Errada";
                }
                Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_SHORT).show();
            }
        };

        buttonAnswer1 = findViewById(R.id.button3);
        buttonAnswer1.setText(String.valueOf(question.getAnswerCorrect()));
        buttonAnswer1.setOnClickListener(listenerButtonAnswer);

        buttonAnswer2 = findViewById(R.id.button4);
        buttonAnswer2.setText(String.valueOf(question.getAnswerIncorrect()));
        buttonAnswer2.setOnClickListener(listenerButtonAnswer);

        View.OnClickListener listenerNextQuestion = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indice_question ++;

                Question question = repository.getListQuestion().get(indice_question);

                textViewQuestion.setText(question.getText());
                buttonAnswer1.setText(String.valueOf(question.getAnswerCorrect()));
                buttonAnswer2.setText(String.valueOf(question.getAnswerIncorrect()));
            }
        };

        Button nextQuestion = findViewById(R.id.nextQuestion);
        nextQuestion.setText("Proxima Pergunta");
        nextQuestion.setOnClickListener(listenerNextQuestion);
    }
}
