package ydl.bg.cn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ydl.bg.cn.R;
import ydl.bg.cn.Untils.SystemStatesBarUtils;
import ydl.bg.cn.activity.ThrowawayActivity;
import ydl.bg.cn.adapter.LargeFragmentTabhostPagerAdapter;

/**
 * Created by donglinghao on 2016-01-28.
 */
public class LargeFragment extends Fragment implements View.OnClickListener{

    private View mRootView,layout;
    private ViewPager viewPager;
    private TabLayout mTabLayout;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;
    private TabLayout.Tab five;
    private TabLayout.Tab six;
    private TextView sd_qihuan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.large_fragment, container, false);
            layout = (View) mRootView.findViewById(R.id.view_topview);
            SystemStatesBarUtils.setTopViewHeightColor(getActivity(),layout,R.color.beijingse);
            initViewPage(mRootView);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    private void initViewPage(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new LargeFragmentTabhostPagerAdapter(getChildFragmentManager()));
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        sd_qihuan = (TextView) view.findViewById(R.id.sd_qiehuan);
        sd_qihuan.setOnClickListener(this);
        mTabLayout.setupWithViewPager(viewPager);
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);
        five = mTabLayout.getTabAt(4);
        six = mTabLayout.getTabAt(5);
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.sd_qiehuan:
                    startActivity(new Intent(getActivity(),ThrowawayActivity.class));
                    break;
            }
    }


}
