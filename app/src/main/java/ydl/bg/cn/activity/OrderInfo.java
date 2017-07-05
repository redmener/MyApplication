package ydl.bg.cn.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import ydl.bg.cn.R;
import ydl.bg.cn.Untils.DateTimePickDialogUtil;
import ydl.bg.cn.gerenActivity.MyXieQiDianActivity;

/**
 * Created by hebiao on 2017/6/13.
 */

public class OrderInfo extends Activity implements View.OnClickListener {

    private TextView tons,timer;
    private EditText ed;
    private View inflate;
    private LayoutInflater inflater;
    private LinearLayout timerselect,harvest_address;
    private String now;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_info);
        initview();
        //拿到当前时间 设置
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        timer.setText(now);
    }
    private void initview() {
        tons = (TextView) findViewById(R.id.tons);
        timer = (TextView) findViewById(R.id.timer);
        timerselect = (LinearLayout) findViewById(R.id.timerselect);
        harvest_address = (LinearLayout) findViewById(R.id.harvest_address);
        tons.setOnClickListener(this);
        timerselect.setOnClickListener(this);
        harvest_address.setOnClickListener(this);
        inflater = LayoutInflater.from(OrderInfo.this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tons:
                //购买数量 弹dialog
                inflate = inflater.inflate(R.layout.dialogstyle, null);
                ed = (EditText) inflate.findViewById(R.id.ed);
                CharSequence text = tons.getText();
                ed.setText(text);
                //获取dialog
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(inflate);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String s = ed.getText().toString();
                        tons.setText(s);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                builder.create();
                            }
                        }).show();
                break;
            case R.id.timerselect:
                //调用工具类     设置时间
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        this, "");
                dateTimePicKDialog.dateTimePicKDialog(timer);
                break;

            case  R.id.harvest_address:
                startActivity(new Intent(this, MyXieQiDianActivity.class));
                break;
        }
    }

}
