package com.example.myquiz.Model;


public class Question {

    private String text;
    private Double answerCorrect;
    private Double answerIncorrect;

    /**
     *
     * @param text
     * @param answerCorrect
     * @param answerIncorrect
     */
    public Question(String text, double answerCorrect, double answerIncorrect){
        this.text = text;
        this.answerCorrect = answerCorrect;
        this.answerIncorrect = answerIncorrect;
    }

    /**
     *
     * @return
     */
    public String getText(){
        return text;
    }

    /**
     *
     * @return
     */
    public double getAnswerCorrect(){
        return answerCorrect;
    }

    /**
     *
     * @return
     */
    public Double getAnswerIncorrect() {
        return answerIncorrect;
    }
}
