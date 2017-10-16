package ydl.bg.cn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ydl.bg.cn.R;

/**
 * Created by hebiao on 2017/6/8.
 */

public class MyLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout login;
    private TextView signup;
    private TextView wangjimima;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myapp_login);
        setViewOnclickLister();
    }
    public void setViewOnclickLister(){
        login = (LinearLayout) findViewById(R.id.login);
        signup = (TextView) findViewById(R.id.signup);
        wangjimima = (TextView) findViewById(R.id.wangjimima);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        wangjimima.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.signup:
                startActivity(new Intent(this,SignUpActivity.class));
                break;
            case R.id.wangjimima:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                break;
        }
    }
}
