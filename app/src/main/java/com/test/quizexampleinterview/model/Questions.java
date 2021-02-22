package com.test.quizexampleinterview.model;

/**
 * Created by macstudent on 2017-12-05.
 */

public class Questions {
        private int ID;
        private String QUESTION;
        private String OPTA;
        private String OPTB;
        private String OPTC;
        private String OPTD;
        private String ANSWER;
        private String ANSWEREXPLANATION;

    public Questions()
        {
            ID=0;
            QUESTION="";
            OPTA="";
            OPTB="";
            OPTC="";
            OPTD="";
            ANSWER="";
            ANSWEREXPLANATION="";
        }
    public Questions(int ID, String QUESTION, String OPTA, String OPTB, String OPTC, String OPTD, String ANSWER, String ANSWEREXPLANATION) {

        this.ID = ID;
        this.QUESTION = QUESTION;
        this.OPTA = OPTA;
        this.OPTB = OPTB;
        this.OPTC = OPTC;
        this.OPTD = OPTD;
        this.ANSWER = ANSWER;
        this.ANSWEREXPLANATION = ANSWEREXPLANATION;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public String getOPTA() {
        return OPTA;
    }

    public void setOPTA(String OPTA) {
        this.OPTA = OPTA;
    }

    public String getOPTB() {
        return OPTB;
    }

    public void setOPTB(String OPTB) {
        this.OPTB = OPTB;
    }

    public String getOPTC() {
        return OPTC;
    }

    public void setOPTC(String OPTC) {
        this.OPTC = OPTC;
    }

    public String getOPTD() {
        return OPTD;
    }

    public void setOPTD(String OPTD) {
        this.OPTD = OPTD;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }

    public String getANSWEREXPLANATION() {
        return ANSWEREXPLANATION;
    }

    public void setANSWEREXPLANATION(String ANSWEREXPLANATION) {
        this.ANSWEREXPLANATION = ANSWEREXPLANATION;
    }
}
