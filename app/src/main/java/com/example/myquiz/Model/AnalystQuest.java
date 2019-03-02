package com.example.myquiz.Model;

public class AnalystQuest {

    public boolean isAnswearCorrect(Question question, double answer){
        return question.getAnswerCorrect() == answer;
    }
}
