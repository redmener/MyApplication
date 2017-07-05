package ydl.bg.cn.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ydl.bg.cn.R;

/**
 * Created by hebiao on 2017/6/9.
 */

public class SignUpActivity extends Activity implements View.OnClickListener{

    private TextView textView;
    private LinearLayout kefuipone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        initview();
        //给textview添加下划线
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

    }
    private void initview() {
        textView = (TextView) findViewById(R.id.yonghuxieyi);
        kefuipone = (LinearLayout) findViewById(R.id.kefuipone);
        textView.setOnClickListener(this);
        kefuipone.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.kefuipone:
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:17600127225"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            break;
        }
    }
}
