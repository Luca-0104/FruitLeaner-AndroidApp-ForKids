package com.example.assignment2_fruits;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * This will be the right-part fragment in the double-page model
 */
public class FruitDetailFragment extends Fragment {

    private String font = "Font/ShortBaby-Mg2w.ttf";    //path of the font

    private View view;  //an instance of the view in this fragment

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //load the layout of 'fruit_detail_frag' as a fragment
        view = inflater.inflate(R.layout.fruit_detail_frag, container, false);
        return view;
    }

    /**
     *  This is a method for refreshing the picture and name of the fruit that is showing in the right side
     * @param fruitName The name of the fruit that is going to be shown in the right fragment
     * @param fruitPicID The picture id of the fruit that is going to be shown in the right fragment
     */
    public void refresh(String fruitName, int fruitPicID){
        //set the visibility of fruit-detail-layout (right part fragment)
        View mLlFruitDetail = view.findViewById(R.id.ll_fruit_detail);
        mLlFruitDetail.setVisibility(View.VISIBLE);

        //refresh the name and pictureID of the fruit that should be shown in the right fragment
        TextView mTvFruitName = view.findViewById(R.id.tv_fruit_name);
        ImageView mIvFruitPic = view.findViewById(R.id.iv_fruit_pic);
        TextView mTvFruitCapLetter = view.findViewById(R.id.tv_cap_letter);
        mTvFruitName.setText(fruitName);
        mIvFruitPic.setImageResource(fruitPicID);
        mTvFruitCapLetter.setText(fruitName.substring(0, 1).toUpperCase());
        //set the font of fruit name
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), font);
        mTvFruitName.setTypeface(typeface);

    }

}
