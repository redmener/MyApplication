package ydl.bg.cn.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;

import ydl.bg.cn.R;
import ydl.bg.cn.fragment.LargeFragment;
import ydl.bg.cn.fragment.OrderFragment;
import ydl.bg.cn.fragment.PersonalCenterFragment;
import ydl.bg.cn.fragment.HomePageFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton bt_one,bt_two,bt_three,bt_four,bt_five;

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private HomePageFragment homePageFragment;
    private LargeFragment largeFragment;
    private OrderFragment orderFragment;
    private PersonalCenterFragment personalCenterFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏 如果只是想要更改状态栏 这句可以注销
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }
    private void initView() {
        bt_one= (RadioButton) findViewById(R.id.rb_one);
//        bt_two= (RadioButton) findViewById(R.id.rb_two);
        bt_three= (RadioButton) findViewById(R.id.rb_three);
        bt_four= (RadioButton) findViewById(R.id.rb_four);
        bt_five= (RadioButton) findViewById(R.id.rb_five);

        bt_one.setOnClickListener(this);
       // bt_two.setOnClickListener(this);
        bt_three.setOnClickListener(this);
        bt_four.setOnClickListener(this);
        bt_five.setOnClickListener(this);
    }

    private void initData() {
            manager=getSupportFragmentManager();
            transaction=manager.beginTransaction();
            if (homePageFragment ==null ) {
                homePageFragment = new HomePageFragment();
            }
            transaction.add(R.id.fg_view, homePageFragment);
            // Commit the transaction
            transaction.commit();
            bt_one.setChecked(true);

    }
    @Override
    public void onClick(View v) {
        FragmentTransaction t;
        t = getSupportFragmentManager().beginTransaction();
        hideFragments(t);
        switch (v.getId()) {
            case R.id.rb_one:
                if (homePageFragment==null){
                    homePageFragment=new HomePageFragment();
                    t.add(R.id.fg_view,homePageFragment);
                }else {
                    t.show(homePageFragment);
                }
//                t.replace(R.id.fg_view, new HomePageFragment());
//                t.commit();
                break;
//            case R.id.rb_two:
//                t.replace(R.id.fg_view, new ThrowawayActivity());
//                t.commit();
//                break;
            case R.id.rb_three:
                if (largeFragment==null){
                    largeFragment=new LargeFragment();
                    t.add(R.id.fg_view,largeFragment);
                }else {
                    t.show(largeFragment);
                }
//                t.replace(R.id.fg_view, new LargeFragment());
//                t.commit();
                break;
            case R.id.rb_four:
                if (orderFragment==null){
                    orderFragment=new OrderFragment();
                    t.add(R.id.fg_view,orderFragment);
                }else {
                    t.show(orderFragment);
                }
//                t.replace(R.id.fg_view, new OrderFragment());
//                t.commit();
                break;
            case R.id.rb_five:
                if (personalCenterFragment==null){
                    personalCenterFragment=new PersonalCenterFragment();
                    t.add(R.id.fg_view,personalCenterFragment);
                }else {
                    t.show(personalCenterFragment);
                }
//                t.replace(R.id.fg_view, new PersonalCenterFragment());
//                t.commit();
                break;
        }
        t.commitAllowingStateLoss();
    }
    private void hideFragments(FragmentTransaction transaction) {
        if (homePageFragment!=null){
            transaction.hide(homePageFragment);
        }
        if (largeFragment!=null){
            transaction.hide(largeFragment);
        }
        if (orderFragment!=null){
            transaction.hide(orderFragment);
        }
        if (personalCenterFragment!=null){
            transaction.hide(personalCenterFragment);
        }
    }

}