package ydl.bg.cn.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;

import ydl.bg.cn.R;


/**
 * Created by hebiao on 2017/6/15.
 */

public class AddDischargeDot extends Activity implements View.OnClickListener {
    private boolean ischecked;
    private ImageView yes_or_no, timeicon;
    private TextView region, text_data, pop_fenlei;
    private Dialog dialog;
    private TextView chengshiranqi;
    private TextView jiaotongnengyuan;
    private TextView gongye;
    private TextView dianchang;
    private View views;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dischargedot);
        initview();
    }

    private void initview() {
        yes_or_no = (ImageView) findViewById(R.id.yes_or_no);
        timeicon = (ImageView) findViewById(R.id.timeicon);
        region = (TextView) findViewById(R.id.region);
        text_data = (TextView) findViewById(R.id.text_data);
        pop_fenlei = (TextView) findViewById(R.id.pop_fenlei);
        yes_or_no.setOnClickListener(this);
        pop_fenlei.setOnClickListener(this);
        text_data.setOnClickListener(this);
        region.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.yes_or_no:
                if (ischecked) {
                    yes_or_no.setImageResource(R.drawable.fou);
                    ischecked = false;
                } else {
                    yes_or_no.setImageResource(R.drawable.shi);
                    ischecked = true;
                }
                break;
            case R.id.region:
                //地区选择器  工具
                CityPicker cityPicker = new CityPicker.Builder(this).textSize(16)
                        .titleTextColor("#000000")
                        //.backgroundPop(0xa0000000)
//                        .province("江苏省")
//                        .city("常州市")
//                        .district("天宁区")
                        .textColor(Color.parseColor("#000000"))
                        .provinceCyclic(true)
                        .cityCyclic(false)
                        .districtCyclic(false)
                        .visibleItemsCount(7)
                        .itemPadding(10)
                        .district("nihao")
                        .build();
                        cityPicker.show();
                cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                        region.setText(citySelected[0] + citySelected[1] + citySelected[2]);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(AddDischargeDot.this, "已取消", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.text_data:
                showDialogTwo();
                break;
            case R.id.pop_fenlei:
                dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
                views = LayoutInflater.from(this).inflate(R.layout.pop_xieqi, null);
                chengshiranqi = (TextView) views.findViewById(R.id.chengshiranqi);
                jiaotongnengyuan = (TextView) views.findViewById(R.id.jiaotongnengyuan);
                gongye = (TextView) views.findViewById(R.id.gongye);
                dianchang = (TextView) views.findViewById(R.id.dianchang);
                dialog.setContentView(views);
                //获取当前Activity所在的窗体
                Window dialogWindow = dialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow.setGravity(Gravity.BOTTOM);
                //获得窗体的属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.y = 20;//设置Dialog距离底部的距离
                //       将属性设置给窗体
                dialogWindow.setAttributes(lp);
                dialog.show();//显示对话框
                chengshiranqi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop_fenlei.setText("城市燃气");
                        dialog.dismiss();
                    }
                });
                jiaotongnengyuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop_fenlei.setText("交通能源(车船用)");
                        dialog.dismiss();
                    }
                });
                gongye.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop_fenlei.setText("工业");
                        dialog.dismiss();
                    }
                });
                dianchang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop_fenlei.setText("电厂");
                        dialog.dismiss();
                    }
                });
                break;
        }

    }

    //选择作业时间的方法//
    private void showDialogTwo() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_date, null);
        final TimePicker startTime = (TimePicker) view.findViewById(R.id.st);
        final TimePicker endTime = (TimePicker) view.findViewById(R.id.et);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择时间");
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int hour = startTime.getCurrentHour();
                int minute = startTime.getCurrentMinute();
                int hour1 = endTime.getCurrentHour();
                int minute2 = endTime.getCurrentMinute();
                if (hour < hour1) {
                    timeicon.setVisibility(View.GONE);
                    text_data.setText("开始时间：" + hour + ":" + minute + "       结束时间：" + hour1 + ":" + minute2);
                } else if (hour == hour1) {
                    if (minute2 > minute) {
                        timeicon.setVisibility(View.GONE);
                        text_data.setText("开始时间：" + hour + ":" + minute + "       结束时间：" + hour1 + ":" + minute2);
                    } else {
                        Toast.makeText(AddDischargeDot.this, "你选择的结束时间早于开始时间", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddDischargeDot.this, "你选择的结束时间早于开始时间", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        //自动弹出键盘问题解决
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
