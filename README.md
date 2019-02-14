# Bookmark_Template
This is the Template code for the Intro to Android Development for HackUCI 2019

For the Complete Code: https://github.com/Pursain/Bookmark_Complete

If you haven't already, please download Android Studio: https://developer.android.com/studio/

Once Android Studio is installed, make sure to set up AVD (Android Virtual Device): https://developer.android.com/studio/run/managing-avds

Before the workshop begins, download the Bookmark_Template from github and open up the project through Android Studio. Have the AVD running in the background as well.

# Table Of Contents
1. **Exploring Android and the Android Studio IDE**
2. **Let's click for Zots** (Changing text with a button onClick)
3. **It's time to move on to another activity** (Changing activities with intents)
4. **If I had 100 items... do I make 100 textViews?** (Interacting with listView, ArrayList, ArrayAdapters)
5. **Hardcoding is bad...** (Dynamically populating the listView)
6. **Let's launch a link!** (Setting OnClickListeners to launch links with Uri and intents) 
7. **Try adding an item and rotating the screen** (Digging deeper into the activity lifecycle and saving information in the bundle)
8. **Debugging with Android** (Toasts and Logging)

## 1. Exploring Android and the Android Studio IDE

### Some Android Vocabulary

- build.Gradle: The tool that builds your application. A script that runs that automates the process of building an application. Built on top of Apache Maven and Apache Ant. 

- Manifest: Defines the entire application by its characteristics and the components that constitute it. Also where you place permissions for accessing different functionality of a phone.

- Android Activity: The activity initiates an Android program and is typically characterized by the onCreate() method, which should be nested in the Activity object

- Android Layouts: Layouts are defined in XML files. They are a bit cumbersome at first but you will see the usefulness of the clear organization when you undertake larger projects. Works similarly to HTML/CSS

- setContentView(): A method that sets the layout of the page to the XML page specified

- Bundle: Used to pass data between activities. Also used to retain data about a page when the state changes (i.e. orientation of the device, browser changes, etc.)

More can be found here: https://www.tutorialspoint.com/android/android_user_interface_layouts.htm

## 2. Let's click for Zots (Changing text with a button onClick)

Let's do something fun to start. In this section, you will create a mini-splash page that will pop up text whenever you click a button. Sounds fun enough yeah?

Here's what it should look like:

<img src='https://github.com/Pursain/Bookmark_Complete/blob/master/github_media/splash_page.gif' title="TODO 1" width=''/>

Now lets break it down. There seems to be three elements at play here; there's the title, the "Zot"s that pop out of nowhere and 
a button that you can click that makes the Zots. To me, that sounds like I need to make 2 TextViews and a button. It seems like we also need to write a function that changes the text inside one of the TextViews and its triggered by the button click.

### Task 1: create the XML views needed for the splash screen

It's easiest to edit the XML file using Android Studio's design interface, its pretty intuitive with the drag and drop interface. 

For the sake of coherency, name your view objects as the following:
 - **textView_splashQuestion** for the TextView that says "What do Anteaters say?"
 - **textView_splashAnswer** for the TextView that will contain our Zots
 - **button_splashGetAnswer** for the Button that says "Click to find out!"

Below is the text solution to the XML needed to make those 3 elements. Looks really wordy right? The built-in design tool in Android Studio astracts it so you won't have to worry about every minute detail but it is good to know that this is the actual xml text that is used to draw your apps. 

    <TextView
        android:id="@+id/textView_splashQuestion"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="32dp"
        android:layout_marginEnd="8dp"
        android:text="What do Anteaters say?"
        android:textSize="24sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/textView_splashAnswer"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="8dp"
        android:textAlignment="center"
        android:textSize="18sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView_splashQuestion"
        tools:text="Zot Zot Zot" />

    <Button
        android:id="@+id/button_splashGetAnswer"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="32dp"
        android:layout_marginEnd="8dp"
        android:text="Click to find out!"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView_splashAnswer" />

### Task 2: modify the onClick_findAnswer function to add zots to the textView 

How are we going to change the text of a TextView? Well, there are two functions in the TextView class that we will use to change the text of the TextView: 

    //will return the String inside the textView 
    String someString = someTextView.getText().toString() 
    
    //will set the Hello World of the textView to the String
    someTextView.setText("Hello World") 

Heres what the Puesdocode looks like if you wanted to append text to a TextView:
 - get the String from the textView and store it into a String variable 
 - append the additional text to the String variable 
 - set the String variable to the textView 
 
Implementing the Puesdocode in our application will look like this:

    public void onClick_findAnswer(View view){
      String currentText = textView_splashAnswer.getText().toString();
      currentText += "Zot ";
      textView_splashAnswer.setText(currentText);
    }
 
Notice the view parameter in this function. It is a necessary signature for the onClick attribute to link to this function. What the view object is, in this case, the button itself. Again, everything in the xml file are views, including buttons.  
 
Cool, we have the function written now but clicking the button doesn't do anything. We want it to do something when you click the button. If the xml file is where all the visuals are and the java activity file is where all functions are run, how do I connect the two? There are many ways the XML files interact with the java class. For our purposes, we are interested in the onClick attribute of the button. To implement that, you will want to add this line to the button view:

    android:onClick="onClick_findAnswer"

Your splash screen should zot on now everytime you click the button.

## 3. It's time to move on to another activity (Changing activities with intents)

Applications often have different screens with each screen dedicated to a specific function. At the moment, we only have one screen/activity but let's say we another screen/activity. How do we move from one activity to another?

**Intents** are what we will use to move from one activity to another. Intents are really powerful and are used throughout Android to do many things (as we will see later), one of its capabilities is to allow us to move from one activity to another nicely. This is what intents look like in the code: 

    Intent someIntent = new Intent(this, theActivityWeWantToGoTo.class);
    startActivity(someIntent);
    
The first parameter of the Intent is the context of the application. The context is essentially background information about the app that the intent uses in the background when it does things, you don't need to worry too much about this, "this" will typically do. 
The second paramter is the name of the activity you want to go to, often the file name followed by .class. The second line startActivity will run the intent object.   

Here's what we want to do next: 

<img src='https://github.com/Pursain/Bookmark_Complete/blob/master/github_media/intent_nextActivity.gif' title="TODO 1" width=''/>

We have a button where when you click on it, it takes us to another activity. We would need to make a new button and use this new thing called intents

### Task 3: creating a button that takes us to another activity

First thing we need to do is to create another button (lets name it button_moveToNextScreen) and link its onClick attribute to a function in the java file (lets call it onClick_goToBookmarks). We have already done it in the previous task so refer back to that if needed. 

Here's what the code should look like:

    public void onClick_goToBookmarks(View view){
        Intent intentToGoToBookmarks = new Intent(this, BookmarksActivity.class);
        startActivity(intentToGoToBookmarks);
    }
    
If class name matches up and the button is connected, clicking the button should move you to the next activity :D

## 4. If I had 100 items... do I make 100 textViews? (Interacting with listView, ArrayList, ArrayAdapters)

Let's say I want to show a list of contacts in my app. At this point, we've seen how TextViews can display text for us. If I had just 5 contacts, I could make 5 TextViews, one for each item in the list and great, it works. But how about 10 contacts? 20 contacts? 100 contacts? There has to be a better way and luckily, there is. Android provides us with a view called ListView that lets you represent data in a long continous scrollable list. Although ListView is considered to be depreciated and replaced by the new RecyclerView, its still a great entry point to understanding Android and whip up something quick. 

There are typically three elements at play when you are using a ListView. There is the ListView itself, a list of data, and an adapter inbetween to take the list of data and gives it to the ListView to display whenever its needed. For our purposes, we will be using a ListView, ArrayList, ArrayAdapter. 

The code below shows how they are pieced together:

    //Creating an ArrayList of Strings and populating it
    ArrayList<Strings> someArrayList = new ArrayList<Strings>();
    someArrayList.add("Hello");
    someArrayList.add("World");
    
    //Creating an ArrayAdapter passing in this, a built-in XML layout and the ArrayList
    ArrayAdapter<String> someArrayAdapter = new ArrayAdapter<String>
            (this, android.R.layout.simple_list_item_1, someArrayList);

    //Obtain the ListView and connecting it to the ArrayAdapter
    ListView someListView = findViewById(R.id.some_List_View);
    someListView.setAdapter(someArrayAdapter);

In our application, we're going to make a list of bookmarked links like such:

<img src='https://github.com/Pursain/Bookmark_Complete/blob/master/github_media/listView.gif' title="TODO 1" width=''/>

### Task 4: Connecting the Trio (ListView, ArrayList, ArrayAdapter)

Before you get started, start by taking a look at the provided code in the BookmarksAcitivty.java and activity_bookmarks.xml. There is a bit of starter code already for you and the GUI is already implemented. 

There will be three things that needs to be done to make it work:
 - Initialize the arrayList_bookmark and add an entry containing a title and link seperated by a newline character (eg. "Google\nhttp://google.com")
 - Initialize the arrayAdapter_bookmark with the built-in layout (android.R.layout.simple_list_item_1) and the ArrayList arrayList_bookmark
 - Set listView_bookmark with the ArrayAdapter arrayAdapter_bookmark
 
The resultant code should like this:
   
     arrayList_bookmark = new ArrayList<String>();
     arrayList.add("Google\nhttp://www.google.com);
     arrayAdapter_bookmark = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList_bookmark);
     listView_bookmark.setAdapter(arrayAdapter_bookmark);

## 5. Hardcoding is bad... (Dynamically populating the listView)

At this point, we've been able to add entries into the ArrayList but only at compile time. Let's make it so that the user is able to  add entries into the list. The XML layout already contains the necessary UI elements to acheive this with 2 EditText views and a button. 

Before we get going, there is one thing to clarify. When the arraylist is altered (eg. a new entry is added) after the adapter has already been created, the arrayadapter has to be notified of any changes to the arraylist to reflect it on the ListView. To do that, you would call the function someArrayAdapter.notifyDataSetChanged():

      //assume that someArrayAdapter has already been initialized with someArrayList
      someArrayList.add("this text will not show up in the listView until arrayadapter is notified");
      someArrayAdapter.notifyDataSetChanged();
      
As a side note, the ArrayAdapter has methods that allow you to edit the ArrayList entries to it directly:

      someArrayAdapter.add("this text show up in the listView once it runs");

Either implementation will work and has its use cases. 

Back to the app, we want the user to be able to add entries like this:

<img src='https://github.com/Pursain/Bookmark_Complete/blob/master/github_media/populate_listView.gif' title="TODO 1" width=''/>

### Task 5: Edit the method onClick_addNewBookmark to add the text of the EditText into the ListView

The format of the each entry should be: a title followed by a newline character followed by the link (eg. "Youtube\nhttp://youtube.com)

There are 3 parts to this task
 - getting the text inside the EditTexts and formatting it into a single String
 - adding the formatted String to the ListView
 - clear the EditText 
 - (OPTIONAL) adding some input validation

Here is a possible implementation:

    public void onClick_addNewBookmark(View view) {
    
        String title = editText_title.getText().toString();
        String URL = editText_URL.getText().toString();
        
        //a little bit of validation
        //rejects if atleast one of the editTexts are empty
        if (!title.equals("") && !URL.equals("")) {
            arrayAdapter_bookmark.add(title + "\n" + URL);
        }
        
        editText_title.setText("");
        editText_URL.setText("");
    }

## 6. Let's launch a link! (Setting OnClickListeners to launch links with Uri and intent) 

This next part is pretty cool. We are gonna add the capability to click on an entry in the ListView and it is going to launch the link in our default web browser: 

<img src='https://github.com/Pursain/Bookmark_Complete/blob/master/github_media/launch_link.gif' title="TODO 1" width=''/>

There's no tasks for this section so just follow along.

There are 3 things that needs to be done here:
 - adding a OnClickListener to the ListView so that it can react when we click on an entry 
 - parsing the entry to get just the link
 - starting an intent with the link
 
First things first, we need to add an OnClickListener to our ListView, it'll look like this:

    //typically done like this
    listView_bookmark.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //this method is called when an item on the listView is clicked
        }
    });
    
    //More spanned out
    AdapterView.OnItemClickListener someListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //this method is called when an item on the listView is clicked
        }
    }
    listView_bookmark.setOnItemClickListener(someListener);
    
This code looks a bit daunting but what it's doing is creating a new object called AdapterView.OnItemClickListener and setting that as the object we want to use for our ListView. The AdapterView.OnItemClickListener is abstract so it requires you to define the onItemClick() method. That onItemClick() method will be called whenever the user clicks on an entry in the ListView. The position parameter tells us which item in the list the user clicked on which will be useful to us. 

The second part is to parse the entry into just a link, this part is purely java, if it doesn't make sense, don't fret, just believe it works:

    String link = arrayAdapter_bookmark.getItem(position).split("\n")[1];

The last part is to create the intent with the link. In the past, we've used intents to move from activity to activity (this is called explicit intents). We are going to use something called implicit intents which makes Android decide what to do with the data we give it. We except that if we give it a link, it will open up the link in the brower. 

    Intent intentToOpenLink = new Intent(Intent.ACTION_VIEW);
    intentToOpenLink.setData(Uri.parse(link));
    //starts the intent, does not check for validity tho :/
    startActivity(intentToOpenLink);
    
Intent.ACTION_VIEW tells android to find the most reasonable way to handle the information at hand and then show us in a view. The .setData() is used here to put the link data into the intent but before that, it must be formatted by the Uri with Uri.parse() to ensure the link is formatted properly. The last line is to start the intent. There are more intricacies at play here but its beyond the scope of this workshop. That's why Android is so cool, there's always more to know :)

The completed code should look like this:
   
    listView_bookmark.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            
                //parse the item text by its name and url
                String link = arrayAdapter_bookmark.getItem(position).split("\n")[1];

                //converts the URL String into an URI so the intent can use it
                Intent intentToOpenLink = new Intent(Intent.ACTION_VIEW);
                intentToOpenLink.setData(Uri.parse(link));

                //starts the intent, does not check for validity tho :/
                startActivity(intentToOpenLink);
            }
        });

## 7. Try adding an item and rotating the screen (Digging deeper into the activity lifecycle and saving information in the bundle)

There is one concept that is quite important that I would like to reiterate. The activity lifecycle of an Android app is something that should always be kept in mind when building an application. 

<img src='https://github.com/Pursain/Bookmark_Complete/blob/master/github_media/android_lifecycle.png' title="TODO 1" width=''/> 

There are many ways the an activity moves from one state to another. This can include calling an intent to move to another activity, pressing the home button and clicking back into the app from the most recents page. 

One subtle event that changes the activity's current state in the lifecycle is when your device rotates and changes orientations, this actually causes the activity to destory itself and recreate it. On the user end, they expect data before they rotated the screen to be persistent to after they rotate the screen. One way to keep our data persistent is with the method onSaveInstanceState. It is called right before the onStop() method is called in our activity lifecycle. For our application, we want to make sure that the entries in the ListView stays consistent so we will store the ArrayList data into the Bundle, an object that gets passed around from one lifecycle to another:

    protected void onSaveInstanceState(Bundle outState){
        //store the arraylist data into the bundle key-value pair
        outState.putStringArrayList("bookmarks", arrayList_bookmark);
        super.onSaveInstanceState(outState);   
    }
    
By adding this code, we can modify our onCreate(Bundle savedInstanceState) method to check if there is anything in the savedInstanceState and if theres is, initialize our arraylist with the arraylist saved in the bundle:
    
    //checks if there is anything in the bundle
    if (savedInstanceState != null) {
        //gets the saved arraylist if there is
        arrayList_bookmark = savedInstanceState.getStringArrayList("bookmarks");
    } else {
        //creates a new arraylist and put google in by default 
        arrayList_bookmark = new ArrayList<String>();
        arrayList_bookmark.add("Google\nhttp://www.google.com");
    }   

The image on the left did not save the ArrayList into the bundle before it onStop() was called. The image on the right does and has code to get that information whenever onCreate() is called thus the persistence of the Youtube link:

<img src='https://github.com/Pursain/Bookmark_Complete/blob/master/github_media/with_bundleSave.gif' title="TODO 1" width='49%'/> <img src='https://github.com/Pursain/Bookmark_Complete/blob/master/github_media/without_bundleSave.gif' title="TODO 1" width='49%'/>

## 8. Debugging with Android (Toasts and Logging)

"print statements > debugger", change my mind

Android has a built in debugger and it works quite nicely if you know what you're doing, albiet not very beginner friendly, theres just too much information to digest if you don't know what you're looking for. Our alternative is our good ole friend the print statement, easy and convenient; Java's equivalent is System.out.println(). OOF! Wait, where is Android's console? 

Android has a what is called the Logcat which is essentially Android's way of logging everything that is happening and when I say everything, I mean EVERYTHING. Try opening up the logcat when your app is running, it's located on the bottom bar by default. To filter out all the shanigans that we don't care about, what you want to do is on the top right of the logcat window, click the filter dropdown and select "show only selected application" which will show only log messages in the app your running. 

How do I write a log in my application? By default, the logging should look like this:
    
    Log.d("Your current Class", "Your current function: you message here ");

There are other logs types, feel free to look at those, we chose Log.d which stands for debugging. When your application reaches that line in your code, it will print this into the logcat and its a pretty good debugging tool to see what's happening. Feel free to litter your code with these logs, the more the merrier :)

Toasts are another feature android has that is useful both as an indicator to the user of what is happening and also as a hacky visual print statement for you as well. Unlike the log statement, the toast will appear on top of the screen whenever you call it. 

    Toast.makeText(this, "Your message here", Toast.LENGTH_SHORT).show();
    
If you look closely at my gifs above when I click an entry and launch the link, you can see me using a Toast message to notify the user that the entry was added. 
