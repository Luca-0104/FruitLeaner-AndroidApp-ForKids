package com.example.assignment2_fruits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //The language of this app, default value is English
    private String language;

    //we use shared preference to store setting data(e.g.language), use editor to write data in to this file
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //initialize the shared preference file
        this.pref = getSharedPreferences("data", MODE_PRIVATE);

        //load the existing setting data
        //get the data from the SharedPreferences file
        this.language = pref.getString("language", "English");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the editor
        editor = pref.edit();

        //update the SharedPreferences file
        editor.putString("language", this.language);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //create the object of the menu that we have defined, use it as the menu of this app
        getMenuInflater().inflate(R.menu.main, menu);

        //true means showing the menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //find the a fragment in the mainActivity: fruitListFragment
        FruitListFragment fruitListFragment = (FruitListFragment) getSupportFragmentManager().findFragmentById(R.id.fruit_list_fragment);

        switch (item.getItemId()){
            //when the language is set to Chinese
            case R.id.language_chinese:
                this.language = "Chinese";
                //update the SharedPreferences file
                editor.putString("language", this.language);
                editor.apply();
                break;

            //when the language is set to English
            case R.id.language_english:
                this.language = "English";
                //update the SharedPreferences file
                editor.putString("language", this.language);
                editor.apply();
                break;
        }

        //refresh the language in fruitListFragment
        fruitListFragment.refreshLanguage(this.language);

        return true;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}