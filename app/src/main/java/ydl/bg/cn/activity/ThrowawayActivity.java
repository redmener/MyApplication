package ydl.bg.cn.activity;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import ydl.bg.cn.R;
import ydl.bg.cn.Untils.SystemStatesBarUtils;
import ydl.bg.cn.adapter.ThrowawayFragmentTabhostPagerAdapter;

/**
 * Created by donglinghao on 2016-01-28.
 */
public class ThrowawayActivity extends AppCompatActivity {
    private View mRootView,layout;
    private ViewPager viewPager;
    private TabLayout mTabLayout;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    /*
    * 由于大单和散单合并了,由之前的Fragment改成了Activity,,切记 一定要继承AppCompatActivity或者
    * 是FragmentActivity不然的话 下面的getSupportFragmentManager
    *
    * */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Activity的话必须加上这些话，还有setContentView后面两句，一些在前一些在后，ok，不然的话标题栏那块会不一样显示效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏 如果只是想要更改状态栏 这句可以注销
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.throwaway_fragment);
        layout = findViewById(R.id.view_topview);
        SystemStatesBarUtils.setTopViewHeightColor(this,layout,R.color.beijingse);

        initViewPage();
        viewPager.setAdapter(new ThrowawayFragmentTabhostPagerAdapter(getSupportFragmentManager()));
    }
    private void initViewPage() {
        viewPager = (ViewPager) findViewById(R.id.sandan_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.sandan_tabLayout);
        mTabLayout.setupWithViewPager(viewPager);
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
    }
}
