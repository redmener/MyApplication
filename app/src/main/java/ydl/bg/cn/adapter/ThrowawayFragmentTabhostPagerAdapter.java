package ydl.bg.cn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ydl.bg.cn.sandanfragment.ThrowawayChirdFragmentOne;
import ydl.bg.cn.sandanfragment.ThrowawayChirdFragmentTwo;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class ThrowawayFragmentTabhostPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitle = new String[]{"求购中", "历史求购"};
    public ThrowawayFragmentTabhostPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new ThrowawayChirdFragmentTwo();
        }
        return new ThrowawayChirdFragmentOne();
    }
    @Override
    public int getCount() {
        return mTitle.length;
    }
    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
