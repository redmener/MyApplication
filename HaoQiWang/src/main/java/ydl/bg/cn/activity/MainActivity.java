package ydl.bg.cn.activity;

import android.os.Build;
import android.os.Bundle;
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
        FragmentTransaction t;
        t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.fg_view, new HomePageFragment());
        t.commit();
        bt_one.setChecked(true);
    }
    @Override
    public void onClick(View v) {
        FragmentTransaction t;
        t = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.rb_one:
                t.replace(R.id.fg_view, new HomePageFragment());
                t.commit();
                break;
//            case R.id.rb_two:
//                t.replace(R.id.fg_view, new ThrowawayActivity());
//                t.commit();
//                break;
            case R.id.rb_three:
                t.replace(R.id.fg_view, new LargeFragment());
                t.commit();
                break;
            case R.id.rb_four:
                t.replace(R.id.fg_view, new OrderFragment());
                t.commit();
                break;
            case R.id.rb_five:
                t.replace(R.id.fg_view, new PersonalCenterFragment());
                t.commit();
                break;
            default:
                break;
        }

    }

}