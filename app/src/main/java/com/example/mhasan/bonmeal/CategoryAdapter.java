package com.example.mhasan.bonmeal;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mhasan on 6/7/2017.
 *
 */

public class CategoryAdapter extends FragmentPagerAdapter {
    private Context mContex;

    CategoryAdapter(Context context, FragmentManager fm){
        super(fm);
        mContex=context;


    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new KitchenChallenge();
            case 1:
                return  new CookBook();
            case 2:
                return  new Fridge();
            case 3:
                return  new MyRecipes();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position){
//            case 0:
//                return "tab1";
//            case 1:
//                return  mContex.getString(R.string.cook_book);
//            case 2:
//                return  mContex.getString(R.string.fridge);
//            case 3:
//                return  mContex.getString(R.string.my_recipes);
//        }
//        return null;
//    }
}
