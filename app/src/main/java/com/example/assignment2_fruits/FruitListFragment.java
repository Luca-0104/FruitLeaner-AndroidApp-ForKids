package com.example.assignment2_fruits;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class define the fruit list, which locates on the left part of the screen
 * This will choose the single or double page model automatically,
 * which means it can suit the phone and tablet automatically according to their screen size and dpi
 */
public class FruitListFragment extends Fragment {

    private boolean isTwoPanel;         //whether the current screen is in two-panel model
    private String[] fruitNamesChinese; //a list of fruit names in Chinese
    private String[] fruitNamesEnglish; //a list of fruit names in English
    private int[] fruitPicIDs;          //a list of int numbers representing the fruit picture ID
    private Fruit currentFruit;         //the object of the current selected fruit

    /**
     * when creating the view for the fragment
     * (i.e. when loading th layout)
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //initialize the fruit information: Chinese and English name lists, id list
        initFruitInfo();

        //load the layout of fruit_list_frag as this fragment
        View view = inflater.inflate(R.layout.fruit_list_frag, container, false);

        /* put the detailed data into the RecyclerView */
        //find the RecyclerView
        RecyclerView mRvFruitList = view.findViewById(R.id.rv_fruit_list);

        //set the LayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRvFruitList.setLayoutManager(layoutManager);

        //get the language of this app
        MainActivity mainActivity = (MainActivity) getActivity();
        String language = mainActivity.getLanguage();

        //set the Adapter
        FruitAdapter fruitAdapter = new FruitAdapter(getFruits(language));
        mRvFruitList.setAdapter(fruitAdapter);

        //set the divider line of each item
        mRvFruitList.addItemDecoration(new MyDecoration());

        //return the view
        return view;
    }

    /**
     * When the fragment and its activity are both created
     * This method is used to determine screen model (single-page or double-page)
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //determine is that a double-page model or single-page model
        //by whether or not can find a view with id of 'fruit_detail_layout'
        //(the view with id of 'fruit_detail_layout' should only exists in the layout of the double-page model)
        if (getActivity().findViewById(R.id.fruit_detail_layout) != null){
            isTwoPanel = true;  //if we can find, it should be double-page model
        }else{
            isTwoPanel = false; //if we cannot find, it should be single-page model
        }
    }

    /**
     *  This method is used to refresh the language in the recyclerView of fruit list and also the language in the fruitDetail fragment(if exists).
     *  This is realized by creating a new adapter for the recycler according to the new language.
     */
    public void refreshLanguage(String language){

        /* refresh the language in fruitListFragment */
        //find the recyclerView of this fruit list
        RecyclerView mRvFruitList = getActivity().findViewById(R.id.rv_fruit_list);

        //set a new Adapter for this recyclerView
        FruitAdapter fruitAdapter = new FruitAdapter(getFruits(language));
        mRvFruitList.setAdapter(fruitAdapter);

        /* refresh the language in fruitDetailFragment
        * only when there is a currentFruit selected and current model is two-panel */
        if (isTwoPanel && currentFruit != null){
            //find the other fragment on this activity, which is fruitDetailFragment (we are going to refresh it as well)
            FruitDetailFragment fruitDetailFragment = (FruitDetailFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fruit_detail_fragment);

            //find the index of the name in fruitNamesChinese array
            int indexInChineseNameArray = getIndex(fruitNamesChinese, currentFruit.getName());

            //current name is in English
            if (indexInChineseNameArray < 0){

                //find the index of the name in fruitNamesEnglish array
                int indexInEnglishNameArray = getIndex(fruitNamesEnglish, currentFruit.getName());

                if (language.equals("Chinese")){
                    //refresh the fruit details fragment (English --> Chinese)
                    fruitDetailFragment.refresh(fruitNamesChinese[indexInEnglishNameArray], currentFruit.getPicID());
                    //update the name of currentFruit
                    currentFruit.setName(fruitNamesChinese[indexInEnglishNameArray]);
                }

            //current name is in Chinese
            }else{

                if (language.equals("English")){
                    //refresh the fruit details fragment (Chinese --> English)
                    fruitDetailFragment.refresh(fruitNamesEnglish[indexInChineseNameArray], currentFruit.getPicID());
                    //update the name of currentFruit
                    currentFruit.setName(fruitNamesEnglish[indexInChineseNameArray]);
                }

            }

        }

    }

    /**
     * get the index of the item in an array
     * @param array The array to be looped through
     * @param name we will find the index of name in the array
     * @return  the index of name in the array, if not fund, -1 will be returned.
     */
    private int getIndex(String[] array, String name){
        //loop through the array ti find the name
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(name)){
                return i;
            }
        }
        //if the name is not fund in the array
        return -1;
    }

    /**
     * This method is used to initialize the fruit information, including
     * fruitNamesChinese array, fruitNamesEnglish array and fruitPicIDs array.
     */
    private void initFruitInfo(){
        //initialize the fruitNamesChinese array
        fruitNamesChinese = new String[]{
            "苹果",
            "香蕉",
            "樱桃",
            "葡萄",
            "芒果",
            "橘子",
            "梨",
            "菠萝",
            "草莓",
            "西瓜"
        };

        //initialize the fruitNamesEnglish array
        fruitNamesEnglish = new String[]{
            "Apple",
            "Banana",
            "Cherry",
            "Grape",
            "Mango",
            "Orange",
            "Pear",
            "Pineapple",
            "Strawberry",
            "Watermelon"
        };

        //initialize the fruitPicIDs array
        fruitPicIDs = new int[]{
            R.drawable.apple_pic,
            R.drawable.banana_pic,
            R.drawable.cherry_pic,
            R.drawable.grape_pic,
            R.drawable.mango_pic,
            R.drawable.orange_pic,
            R.drawable.pear_pic,
            R.drawable.pineapple_pic,
            R.drawable.strawberry_pic,
            R.drawable.watermelon_pic
        };
    }

    /**
     * initialize the fruit list
     * @param language According to the language of this app, we will initialize the different fruit lists
     * @return fruit list
     */
    private List<Fruit> getFruits(String language){
        //an arrayList contains the fruit objects of Fruit class
        ArrayList<Fruit> fruitList = new ArrayList<>();

        //we add the fruits into the list 2 times, so that the list can be long enough
        for(int i = 0; i < 3; i++){

            if (language.equals("Chinese")){
                //initialize our fruit list using their Chinese names
                for (int j = 0; j < fruitNamesChinese.length; j++) {
                    fruitList.add(new Fruit(fruitNamesChinese[j], fruitPicIDs[j]));
                }
            }else if (language.equals("English")){
                //initialize our fruit list using their English names
                for (int j = 0; j < fruitNamesEnglish.length; j++) {
                    fruitList.add(new Fruit(fruitNamesEnglish[j], fruitPicIDs[j]));
                }
            }

        }

        return fruitList;
    }

    public Fruit getCurrentFruit() {
        return currentFruit;
    }

    public void setCurrentFruit(Fruit currentFruit) {
        this.currentFruit = currentFruit;
    }

    /**
     * An adapter for the RecyclerView
     * We implement this class as an inner class so that
     * the instance variables like isTwoPanel can be accessed easily
     */
    class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitListViewHolder>{

        //the List of fruit
        private List<Fruit> mFruitList;

        //constructor
        public FruitAdapter(List<Fruit> fruitList){
            this.mFruitList = fruitList;
        }

        @Override
        public FruitListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            //inflate the layout using the 'item' of RecyclerView
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_item, viewGroup, false);

            //create a holder object using the 'view' we just got
            final FruitListViewHolder holder = new FruitListViewHolder(view);

            //set the click listener for the items
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //find the fruit that is clicked from the fruit list
                    Fruit fruit = mFruitList.get(holder.getAdapterPosition());

                    //update the currentFruit (only when clicking on the list item)
                    currentFruit = fruit;

                    if(isTwoPanel){
                        //if this the two-page model, we should refresh the content in the right part fragment
                        FruitDetailFragment fruitDetialFragment = (FruitDetailFragment) getFragmentManager().findFragmentById(R.id.fruit_detail_fragment);
                        fruitDetialFragment.refresh(fruit.getName(), fruit.getPicID());

                    }else{
                        //if this is the single-page model, we should start another activity of the fruit detail
                        FruitDetailActivity.actionStart(getActivity(), fruit.getName(), fruit.getPicID(), fruit.getFirstLetter());

                    }

                }
            });

            //return the object of holder
            return holder;
        }

        @Override
        public void onBindViewHolder(FruitListViewHolder holder, int position) {
            //initialize every view in this item
            Fruit fruit = mFruitList.get(position);
            holder.mTvFruitName.setText(fruit.getName());               //the fruit name
            holder.mIvFruitIcon.setImageResource(fruit.getPicID());     //the fruit icon
        }

        @Override
        public int getItemCount() {
            return mFruitList.size();
        }

        /**
         * A ViewHolder that holds all the views in the RecyclerView
         */
        class FruitListViewHolder extends RecyclerView.ViewHolder{

            //hold all the views in the RecyclerView
            ImageView mIvFruitIcon;
            TextView mTvFruitName;
            View view;

            public FruitListViewHolder(View itemView) {
                super(itemView);
                //find the views and initialize the instance variables
                mIvFruitIcon = itemView.findViewById(R.id.iv_fruit_pic);
                mTvFruitName = itemView.findViewById(R.id.tv_fruit_name);
                this.view = itemView;
            }
        }
    }

    //a inner class for declaring the decoration of each info item
    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            //a divider line under each item
            outRect.set(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
}
