package com.example.myquiz.Controller;

import android.net.sip.SipSession;
import android.os.PersistableBundle;
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

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String INDICE_QUESTION = "INDICE_QUESTION";
    private final Locale locale = new Locale("pt", "BR");
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
                final String answer = ((Button) v).getText().toString();

                AnalystQuest analystQuest = new AnalystQuest();
                Question question = repository.getListQuestion().get(indice_question);

                String mensagem;

                try {
                    NumberFormat format = NumberFormat.getInstance(locale);
                    Number number = format.parse(answer);

                    if (analystQuest.isAnswearCorrect(question, number.doubleValue())) {
                        mensagem = "Parabens, resposta correta";
                    } else {
                        mensagem = "Resposta Errada";
                    }
                } catch (ParseException e) {
                    mensagem = e.getMessage();
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
                indice_question++;

                if (indice_question >= repository.getListQuestion().size()) {
                    indice_question = 0;
                }

                showQuestion(indice_question);
            }
        };

        Button nextQuestion = findViewById(R.id.nextQuestion);
        nextQuestion.setOnClickListener(listenerNextQuestion);

        if (savedInstanceState != null) {
            indice_question = savedInstanceState.getInt(INDICE_QUESTION);
        }

        showQuestion(indice_question);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(INDICE_QUESTION, indice_question);
    }

    public void showQuestion(final int indice_question) {
        Question question = repository.getListQuestion().get(indice_question);

        textViewQuestion.setText(question.getText());

        String answerCorrect = String.format(locale, "%.2f", question.getAnswerCorrect());
        String answerIncorrect = String.format(locale, "%.2f", question.getAnswerIncorrect());

        buttonAnswer1.setText(answerCorrect);
        buttonAnswer2.setText(answerIncorrect);
    }
}
