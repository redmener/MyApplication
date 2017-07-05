package ydl.bg.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import ydl.bg.cn.R;
import ydl.bg.cn.Untils.SystemStatesBarUtils;
import ydl.bg.cn.adapter.OrderFragmentTabhostPagerAdapter;

/**
 * Created by donglinghao on 2016-01-28.
 */
public class OrderFragment extends Fragment {

    private View mRootView,layout;
    private ViewPager viewPager;
    private TabLayout mTabLayout;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;
    private TabLayout.Tab five;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = inflater.inflate(R.layout.order_fragment,container,false);
            layout = (View) mRootView.findViewById(R.id.view_topview);
            SystemStatesBarUtils.setTopViewHeightColor(getActivity(),layout,R.color.beijingse);
            initViewPage(mRootView);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null){
            parent.removeView(mRootView);
        }
        return mRootView;
    }
    private void initViewPage(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.dingdan_viewpager);
        viewPager.setAdapter(new OrderFragmentTabhostPagerAdapter(getChildFragmentManager()));
        mTabLayout = (TabLayout)view. findViewById(R.id.dingdan_tabLayout);
        mTabLayout.setupWithViewPager(viewPager);
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);
        five = mTabLayout.getTabAt(4);
    }
}
