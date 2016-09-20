package com.example.owl.heritage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private int tabCount;
    private Context mContext;

    public TabPagerAdapter(Context mContext, FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tab_1 tab1 = new Tab_1(mContext);
                return tab1;
            case 1:
                Tab_2 tab2 = new Tab_2(mContext);
                return tab2;
            case 2:
                Tab_3 tab3 = new Tab_3(mContext);
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}