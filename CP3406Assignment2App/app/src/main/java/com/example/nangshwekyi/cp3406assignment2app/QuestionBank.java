package com.example.nangshwekyi.cp3406assignment2app;


import android.content.Context;

import com.example.nangshwekyi.cp3406assignment2app.MyDataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    // declare list of Question objects
    List<Question> list = new ArrayList<>();
    MyDataBaseHelper myDataBaseHelper;

    // method returns number of questions in list
    public int getLength(){
        return list.size();
    }

    // method returns question from list based on list index
    public String getQuestion(int a) {
        return list.get(a).getQuestion();
    }

    // method return a single multiple choice item for question based on list index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4
    // as an argument
    public String getChoice(int index, int num) {
        return list.get(index).getChoice(num-1);
    }

    //  method returns correct answer for the question based on list index
    public String getCorrectAnswer(int a) {
        return list.get(a).getAnswer();
    }



    public void initQuestions(Context context) {
        myDataBaseHelper = new MyDataBaseHelper(context);
        list = myDataBaseHelper.getAllQuestionsList();//get questions/choices/answers from database

        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            myDataBaseHelper.addInitialQuestion(new Question("Calcium hydroxide + Phosphoric acid => ? + water",
                    new String[]{"Phophate", "Calcium Oxide", "Calcium"}, "Calcium phosphate"));
            myDataBaseHelper.addInitialQuestion(new Question("What is H20 more commonly known as?",
                    new String[]{"Nitrogen", "Hydrogen dioxide", "Helium"}, "Water"));
            myDataBaseHelper.addInitialQuestion(new Question("True or false? Bases have a pH level below 7.",
                    new String[]{"No idea", "True", "False"}, "False"));
            myDataBaseHelper.addInitialQuestion(new Question("What is the chemical symbol for gold?",
                    new String[]{"Au", "Pb", "pH"}, "Au"));
            myDataBaseHelper.addInitialQuestion(new Question("Is sodium hydroxide (NaOH) an acid or base or liquid?",
                    new String[]{"Acid", "Salt", "Liquid"}, "Base"));
            myDataBaseHelper.addInitialQuestion(new Question("What is the first element on the periodic table?",
                    new String[]{"Hydrogen", "Carbon", "Chlorine"}, "Hydrogen"));
            myDataBaseHelper.addInitialQuestion(new Question("What is the main gas found in the air we breathe?",
                    new String[]{"carbon dioxide","Oxengy","Nitrogen"},"Nitrogen (around 78%)"));


            list = myDataBaseHelper.getAllQuestionsList();//get list from database again

        }
    }

}
