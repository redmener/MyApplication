package ydl.bg.cn.gerenActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import ydl.bg.cn.R;
import ydl.bg.cn.activity.AddDischargeDot;

/**
 * Created by hebiao on 2017/5/19.
 */

public class MyXieQiDianActivity extends Activity  implements View.OnClickListener{

    private TextView add_dischargedot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myxieqi_layout);
        initview();
    }

    private void initview() {
        add_dischargedot = (TextView) findViewById(R.id.add_dischargedot);
        add_dischargedot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.add_dischargedot:
                startActivity(new Intent(this, AddDischargeDot.class));
                break;

        }
    }
}
