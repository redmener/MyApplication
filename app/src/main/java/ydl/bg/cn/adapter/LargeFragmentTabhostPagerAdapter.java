package ydl.bg.cn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ydl.bg.cn.dadanfragment.LargeChirdFragmentFive;
import ydl.bg.cn.dadanfragment.LargeChirdFragmentFor;
import ydl.bg.cn.dadanfragment.LargeChirdFragmentOne;
import ydl.bg.cn.dadanfragment.LargeChirdFragmentSix;
import ydl.bg.cn.dadanfragment.LargeChirdFragmentThree;
import ydl.bg.cn.dadanfragment.LargeChirdFragmentTwo;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class LargeFragmentTabhostPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"全部", "报价中", "议价中","部分成交","已完成","已取消"};

    public LargeFragmentTabhostPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new LargeChirdFragmentTwo();
        } else if (position == 2) {
            return new LargeChirdFragmentThree();
        }else if (position==3){
            return new LargeChirdFragmentFor();
        }else if (position==4){
            return new LargeChirdFragmentFive();
        }else if (position==5){
            return new LargeChirdFragmentSix();
        }
        return new LargeChirdFragmentOne();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
