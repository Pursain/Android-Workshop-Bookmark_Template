package com.example.harry.bookmark_template;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class BookmarksActivity extends AppCompatActivity {

    //member variables of view objects
    private ListView listView_bookmark;
    private ArrayList<String> arrayList_bookmark;
    private ArrayAdapter<String> arrayAdapter_bookmark;
    private EditText editText_title;
    private EditText editText_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        //Storing them in variables for later use
        editText_title = findViewById(R.id.editText_title);
        editText_URL =  findViewById(R.id.editText_URL);
        listView_bookmark = findViewById(R.id.listView_bookmark);

        /*
        TODO Task 4: Connecting the Trio (ListView, ArrayList, ArrayAdapter)
        Initialize the arrayList_bookmark and add an entry containing a title and link seperated
            by a newline character (eg. "Google\nhttp://google.com")
        Initialize the arrayAdapter_bookmark with the built-in layout
            (android.R.layout.simple_list_item_1) and the ArrayList arrayList_bookmark
        Set listView_bookmark with the ArrayAdapter arrayAdapter_bookmark
         */
        //====== write code for Task 4 here ======

        //========================================

    }

    public void onClick_addNewBookmark(View view) {

        /*TODO Task 5: Edit the method onClick_addNewBookmark to add the text of the EditText into the ListView
        Get the text inside the EditTexts and formatting it into a single String
        Add the formatted String to the ListView
        Clear the EditText
         */

        //====== write code for Task 5 here ======

        //========================================

    }
}
