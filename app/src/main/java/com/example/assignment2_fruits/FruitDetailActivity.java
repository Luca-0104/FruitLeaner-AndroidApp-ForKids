package com.example.assignment2_fruits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Define what the right part view should be like in the single-page model.
 * We import the layout of right side part of the double-page model.
 * (Due to there is only one part in the single-page model, this view should takes the whole screen.)
 */
public class FruitDetailActivity extends AppCompatActivity {

    /**
     * This method is used to start this activity in another activity
     * @param context Another activity
     * @param fruitName The name of fruit
     * @param fruitPicID The id of fruit picture
     */
    public static void actionStart(Context context, String fruitName, int fruitPicID, String fruitCapLetter){
        Intent intent = new Intent(context, FruitDetailActivity.class);
        intent.putExtra("fruit_name", fruitName);
        intent.putExtra("fruit_pic_id", fruitPicID);
        intent.putExtra("fruit_cap_letter", fruitCapLetter);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruit_detail);

        //extract the detailed information about the fruit
        String fruitName = getIntent().getStringExtra("fruit_name");
        String fruitCapLetter = getIntent().getStringExtra("fruit_cap_letter");
        int fruitPicID = getIntent().getIntExtra("fruit_pic_id", 0);

        //find the fragment and refresh it
        FruitDetailFragment fruitDetailFragment = (FruitDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fruit_detail_fragment);
        fruitDetailFragment.refresh(fruitName, fruitPicID);
    }
}