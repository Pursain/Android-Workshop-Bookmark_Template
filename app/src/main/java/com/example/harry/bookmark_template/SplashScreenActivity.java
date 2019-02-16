package com.example.harry.bookmark_template;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    //storing the views in variables is typically a good idea if you are going to use them a lot
    private TextView textView_splashAnswer;
    private String currentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //android does a whole bunch of things in the background
        super.onCreate(savedInstanceState);

        //selects which xml file this activity will use
        setContentView(R.layout.activity_splash_screen);

        //calling findViewById every time you need a view object can be costly
        //TODO uncomment the line below for Task 2
        //textView_splashAnswer = findViewById(R.id.textView_splashAnswer);


    }

    /*
    TODO Task 1: create the xml items needed for the splash screen

    You will need to make edits to the activity_splash_screen.xml file
     */

    public void onClick_findAnswer(View view) {
        /*
        TODO Task 2: modify the onClick_findAnswer function to add zots to the textView
        make sure to also edit the onclick attribute for the button in the XML file
         */

        //====== write code for Task 2 here ======

        //========================================
    }

    /*
    TODO Task 3: create an onClick function that uses intents to another activity
     */

    //=======write code for Task 3 here=======

    //========================================

}
