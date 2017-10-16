package ydl.bg.cn.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ydl.bg.cn.R;

/**
 * Created by hebiao on 2017/6/12.
 */

public class CommodityDetailsActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_details);
        TextView fromWhere=(TextView)findViewById(R.id.name);
        Bundle b=getIntent().getExtras();
        //获取Bundle的信息
        String info=b.getString("name");
        fromWhere.setText(info);
        initview();
    }

    private void initview() {
        LinearLayout tr_layout= (LinearLayout) findViewById(R.id.tr_layout);
        TextView buynow= (TextView) findViewById(R.id.buynow);
        tr_layout.setOnClickListener(this);
        buynow.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
switch (view.getId()){
    case R.id.tr_layout:
        startActivity(new Intent(this,TemperamentReportActivity.class));
        break;
    case R.id.buynow:
        startActivity(new Intent(this,OrderInfo.class));
        break;
}
    }
}
