package com.test.quizexampleinterview.models;

import java.util.ArrayList;

/**
 * Created by pritesh.patel on 2017-04-12, 3:17 PM.
 * ADESA, Canada
 */

public class QuizQuestions
{
    private int questionId;
    private String question;
    private String options1;
    private String options2;
    private String options3;
    private String options4;
    private String correctAnswer;
    private String answerExplanation;
    private static ArrayList<QuizQuestions>mQuizQuestionsArrayList;

    public QuizQuestions()
    {
    }

    public QuizQuestions(int questionId, String question, String options1, String options2, String options3, String options4, String correctAnswer, String answerExplanation)
    {
        this.questionId = questionId;
        this.question = question;
        this.options1 = options1;
        this.options2 = options2;
        this.options3 = options3;
        this.options4 = options4;
        this.correctAnswer = correctAnswer;
        this.answerExplanation = answerExplanation;
    }

    public String getCorrectAnswer()
    {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer)
    {
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(int questionId)
    {
        this.questionId = questionId;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getOptions1()
    {
        return options1;
    }

    public void setOptions1(String options1)
    {
        this.options1 = options1;
    }

    public String getOptions2()
    {
        return options2;
    }

    public void setOptions2(String options2)
    {
        this.options2 = options2;
    }

    public String getOptions3()
    {
        return options3;
    }

    public void setOptions3(String options3)
    {
        this.options3 = options3;
    }

    public String getOptions4()
    {
        return options4;
    }

    public void setOptions4(String options4)
    {
        this.options4 = options4;
    }

    public String getAnswerExplanation()
    {
        return answerExplanation;
    }

    public void setAnswerExplanation(String answerExplanation)
    {
        this.answerExplanation = answerExplanation;
    }

    public static  ArrayList<QuizQuestions> getQuizQuestionsArrayList()
    {
        return mQuizQuestionsArrayList;
    }


    public static void initData()
    {

    }
}
