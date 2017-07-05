package ydl.bg.cn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ydl.bg.cn.dinggoufragment.OrderChirdFragmentFive;
import ydl.bg.cn.dinggoufragment.OrderChirdFragmentFor;
import ydl.bg.cn.dinggoufragment.OrderChirdFragmentOne;
import ydl.bg.cn.dinggoufragment.OrderChirdFragmentThree;
import ydl.bg.cn.dinggoufragment.OrderChirdFragmentTwo;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class OrderFragmentTabhostPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitle = new String[]{"全部", "待确定","配送中","对账中","完成"};
    public OrderFragmentTabhostPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new OrderChirdFragmentTwo();
        }else  if (position==2){
            return  new OrderChirdFragmentThree();
        }else  if (position==3){
            return  new OrderChirdFragmentFor();
        }else  if (position==4){
            return  new OrderChirdFragmentFive();
        }
        return new OrderChirdFragmentOne();
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
