package ydl.bg.cn.gerenActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ydl.bg.cn.R;

/**
 * Created by hebiao on 2017/6/5.
 */

public class MyBill extends Activity implements View.OnClickListener{
    private TextView addbill;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_bill);
        addbill = (TextView) findViewById(R.id.addbill);
        addbill.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout:
                break;
            case R.id.addbill:
                startActivity(new Intent(this,AddBill.class));
                break;
        }
    }
}
