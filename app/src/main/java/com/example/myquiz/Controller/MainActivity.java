package com.example.myquiz.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.myquiz.Model.Question;
import com.example.myquiz.Model.QuestionRepository;
import com.example.myquiz.R;

public class MainActivity extends AppCompatActivity {

    private QuestionRepository repository = new QuestionRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Question question = repository.getListQuestion().get(0);

        // Instanciando a view
        TextView textViewQuestion = findViewById(R.id.textView2);
        textViewQuestion.setText(question.getText());

        Button buttonAnswer1 = findViewById(R.id.button3);
        buttonAnswer1.setText(String.valueOf(question.getAnswerCorrect()));

        Button buttonAnswer2 = findViewById(R.id.button4);
        buttonAnswer2.setText(String.valueOf(question.getAnswerIncorrect()));

        Button nextQuestion = findViewById(R.id.nextQuestion);
        nextQuestion.setText("Proxima Pergunta");
    }
}
