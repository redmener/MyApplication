package ydl.bg.cn.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import java.text.SimpleDateFormat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ydl.bg.cn.R;
import ydl.bg.cn.Untils.DateTimePickDialogUtil;
import ydl.bg.cn.gerenActivity.MyXieQiDianActivity;

/**
 * Created by hebiao on 2017/6/19.
 */

public class ReleaseFor extends Activity implements View.OnClickListener {
    private TextView type, dd_data, tv_dadan, tv_sandan, dd_peisong, tv_ziti, tv_peisong, dd_zhifu, tv_zhijie, tv_danbao,
            dd_fukuan, dd_yufu, dd_houfu, dd_buxian, dd_addxieqi, sd_daohuodata, sd_peisong, sd_fukuan, sd_zhifu;
    private View views, buy_dadan, buy_sandan, view1, view2, view3;
    private Dialog dialog;
    private Button dd_btnDecrease, dd_btnIncrease;
    private EditText dd_etAmount;
    private int amount = 0; //购买数量
    private int goods_storage = 200000; //商品库存
    private int year;
    private ImageView riqiicon, dd_qiyuan;
    private boolean ischecked;
    private LinearLayout sd_shouhuo;
    private int dadan, sandan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_for);
        ininview();
        //这个是判断下intent的传值 是不是空  不然 从别的地方跳过来的时候会报空
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            dadan = intent.getExtras().getInt("dadan");
            sandan = intent.getExtras().getInt("sandan");
            getIntentText();
        }
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        int months = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int m = months + 1;
        //交付时间设置 当前时间
        dd_data.setText(year + "-" + m + "-" + day);
        dd_etAmount.setText("20");
        //到货时间设置当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        sd_daohuodata.setText(now);

    }

    private void ininview() {
        buy_dadan = findViewById(R.id.buy_dadan);
        buy_sandan = findViewById(R.id.buy_sandan);
        dd_btnDecrease = (Button) findViewById(R.id.dd_btnDecrease);
        dd_btnIncrease = (Button) findViewById(R.id.dd_btnIncrease);
        dd_etAmount = (EditText) findViewById(R.id.dd_etAmount);
        type = (TextView) findViewById(R.id.type);
        dd_addxieqi = (TextView) findViewById(R.id.dd_addxieqi);
        dd_peisong = (TextView) findViewById(R.id.dd_peisong);
        dd_data = (TextView) findViewById(R.id.dd_data);
        dd_zhifu = (TextView) findViewById(R.id.dd_zhifu);
        dd_fukuan = (TextView) findViewById(R.id.dd_fukuan);
        riqiicon = (ImageView) findViewById(R.id.riqiicon);
        dd_qiyuan = (ImageView) findViewById(R.id.dd_qiyuan);
        sd_shouhuo = (LinearLayout) findViewById(R.id.sd_shouhuo);
        sd_daohuodata = (TextView) findViewById(R.id.sd_daohuodata);
        sd_peisong = (TextView) findViewById(R.id.sd_peisong);
        sd_fukuan = (TextView) findViewById(R.id.sd_fukuan);
        sd_zhifu = (TextView) findViewById(R.id.sd_zhifu);
        type.setOnClickListener(this);
        sd_shouhuo.setOnClickListener(this);
        dd_btnDecrease.setOnClickListener(this);
        dd_addxieqi.setOnClickListener(this);
        dd_fukuan.setOnClickListener(this);
        dd_qiyuan.setOnClickListener(this);
        dd_zhifu.setOnClickListener(this);
        dd_peisong.setOnClickListener(this);
        dd_btnIncrease.setOnClickListener(this);
        riqiicon.setOnClickListener(this);
        dd_data.setOnClickListener(this);
        sd_daohuodata.setOnClickListener(this);
        sd_peisong.setOnClickListener(this);
        sd_fukuan.setOnClickListener(this);
        sd_zhifu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.type:
                //切换大单和散单弹出的dialog
                views = LayoutInflater.from(this).inflate(R.layout.pop_buytype, null);
                tv_dadan = (TextView) views.findViewById(R.id.tv_dadan);
                tv_sandan = (TextView) views.findViewById(R.id.tv_sandan);
                tv_dadan.setOnClickListener(this);
                tv_sandan.setOnClickListener(this);
                dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
                dialog.setContentView(views);
                //获取当前Activity所在的窗体
                Window dialogWindow = dialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow.setGravity(Gravity.BOTTOM);
                //获得窗体的属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.y = 20;//设置Dialog距离底部的距离
                // 将属性设置给窗体
                dialogWindow.setAttributes(lp);
                dialog.show();//显示对话框
                break;
            // 散单 和大单切换的 按钮
            case R.id.tv_sandan:
                dialog.dismiss();
                type.setText("散单");
                buy_dadan.setVisibility(View.GONE);
                buy_sandan.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_dadan:
                dialog.dismiss();
                type.setText("大单");
                buy_sandan.setVisibility(View.GONE);
                buy_dadan.setVisibility(View.VISIBLE);
                break;
            // 需求总量加减数量
            case R.id.dd_btnDecrease:
                String ss = String.valueOf(dd_etAmount.getText());
                amount = Integer.parseInt(ss);
                if (amount >= 20) {
                    amount -= 20;
                    dd_etAmount.setText(amount + "");
                } else {
                    Toast.makeText(this, "亲···不能再减啦~", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.dd_btnIncrease:
                String sss = String.valueOf(dd_etAmount.getText());
                amount = Integer.parseInt(sss);
                if (amount < goods_storage) {
                    amount += 20;
                    dd_etAmount.setText(amount + "");
                }
                break;
            //交付时间的
            case R.id.dd_data:
                showDialogTwo();
                break;
            case R.id.dd_peisong:
                peisong(dd_peisong);
                break;
            case R.id.dd_zhifu:
                zhifu(dd_zhifu);
                break;
            case R.id.dd_fukuan:
                fukuan(dd_fukuan);
                break;
            //起源地是否按扭切换的
            case R.id.dd_qiyuan:
                if (ischecked) {
                    dd_qiyuan.setImageResource(R.drawable.fou);
                    ischecked = false;
                } else {
                    dd_qiyuan.setImageResource(R.drawable.shi);
                    ischecked = true;
                }
                break;
            case R.id.dd_addxieqi:
                startActivity(new Intent(this, MyXieQiDianActivity.class));
                break;
            case R.id.sd_shouhuo:
                startActivity(new Intent(this, MyXieQiDianActivity.class));
                break;
            case R.id.sd_daohuodata:
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        this, "");
                dateTimePicKDialog.dateTimePicKDialog(sd_daohuodata);
                break;
            case R.id.sd_peisong:
                peisong(sd_peisong);
                break;
            case R.id.sd_fukuan:
                fukuan(sd_fukuan);
                break;
            case R.id.sd_zhifu:
                zhifu(sd_zhifu);
                break;
        }
    }

    //支付的方法  由于大单和散单一样  所以抽取
    private void zhifu(final TextView textView) {
        view2 = LayoutInflater.from(this).inflate(R.layout.pop_paymentmethod, null);
        tv_zhijie = (TextView) view2.findViewById(R.id.tv_zhijie);
        tv_danbao = (TextView) view2.findViewById(R.id.tv_danbao);
        tv_zhijie.setOnClickListener(this);
        tv_danbao.setOnClickListener(this);
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view2);
        //获取当前Activity所在的窗体
        Window dialogWindow2 = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow2.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp2 = dialogWindow2.getAttributes();
        lp2.y = 20;//设置Dialog距离底部的距离
        // 将属性设置给窗体
        dialogWindow2.setAttributes(lp2);
        dialog.show();//显示对话框
        tv_zhijie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                textView.setText("直接支付");
            }
        });
        tv_danbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                textView.setText("担保支付");
            }
        });
    }

    //付款方式的方法  由于大单和散单一样  所以抽取
    private void fukuan(final TextView textView) {
        view3 = LayoutInflater.from(this).inflate(R.layout.pop_payingmethod, null);
        dd_yufu = (TextView) view3.findViewById(R.id.dd_yufu);
        dd_houfu = (TextView) view3.findViewById(R.id.dd_houfu);
        dd_buxian = (TextView) view3.findViewById(R.id.dd_buxian);
        dd_yufu.setOnClickListener(this);
        dd_houfu.setOnClickListener(this);
        dd_buxian.setOnClickListener(this);
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view3);
        //获取当前Activity所在的窗体
        Window dialogWindow3 = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow3.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp3 = dialogWindow3.getAttributes();
        lp3.y = 20;//设置Dialog距离底部的距离
        // 将属性设置给窗体
        dialogWindow3.setAttributes(lp3);
        dialog.show();//显示对话框
        dd_yufu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                textView.setText("预付");
            }
        });
        dd_houfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                textView.setText("后付");
            }
        });
        dd_buxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                textView.setText("不限");
            }
        });
    }

    //配送方式的方法  由于大单和散单一样  所以抽取
    private void peisong(final TextView textView) {
        view1 = LayoutInflater.from(this).inflate(R.layout.pop_deliverymethod, null);
        tv_ziti = (TextView) view1.findViewById(R.id.tv_ziti);
        tv_peisong = (TextView) view1.findViewById(R.id.tv_peisong);
        tv_ziti.setOnClickListener(this);
        tv_peisong.setOnClickListener(this);
        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view1);
        //获取当前Activity所在的窗体
        Window dialogWindow1 = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow1.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp1 = dialogWindow1.getAttributes();
        lp1.y = 20;//设置Dialog距离底部的距离
        // 将属性设置给窗体
        dialogWindow1.setAttributes(lp1);
        dialog.show();//显示对话框
        tv_ziti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                textView.setText("自提");
            }
        });
        tv_peisong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                textView.setText("厂家配送");
            }
        });
    }

    //交付时间  时间显示 和选择  弹dialog选择   的方法
    private void showDialogTwo() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_date2, null);
        final DatePicker startTime = (DatePicker) view.findViewById(R.id.st);
        final DatePicker endTime = (DatePicker) view.findViewById(R.id.et);
        startTime.updateDate(startTime.getYear(), startTime.getMonth(), 01);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择时间");
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int month = startTime.getMonth() + 1;
                String st = "" + startTime.getYear() + "-" + month + "-" + startTime.getDayOfMonth();
                int month1 = endTime.getMonth() + 1;
                String et = "" + endTime.getYear() + "-" + month1 + "-" + endTime.getDayOfMonth();
                if (startTime.getYear() == endTime.getYear() && startTime.getYear() <= year) {
                    if (month > month1) {
                        Toast.makeText(ReleaseFor.this, "结束时间不能小于开始时间", Toast.LENGTH_SHORT).show();
                    } else if (month < month1) {
                        riqiicon.setVisibility(View.GONE);
                        dd_data.setText(st + "   至   " + et);
                    } else if (month == month1) {
                        if (startTime.getDayOfMonth() > endTime.getDayOfMonth()) {
                            Toast.makeText(ReleaseFor.this, "结束时间不能小于开始时间", Toast.LENGTH_SHORT).show();
                        } else {
                            riqiicon.setVisibility(View.GONE);
                            dd_data.setText(st + "   至   " + et);
                        }
                    }
                } else if (endTime.getYear() > startTime.getYear()) {
                    riqiicon.setVisibility(View.GONE);
                    dd_data.setText(st + "   至   " + et);
                } else if (startTime.getYear() > year) {
                    Toast.makeText(ReleaseFor.this, "开始时间不能大于当前时间", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ReleaseFor.this, "结束时间不能小于开始时间", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        //自动弹出键盘问题解决
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    //判断 传值 的方法
    public void getIntentText() {
        if (dadan == 1) {
            buy_sandan.setVisibility(View.GONE);
            buy_dadan.setVisibility(View.VISIBLE);
            type.setText("大单");
        } else if (sandan == 2) {
            buy_sandan.setVisibility(View.VISIBLE);
            buy_dadan.setVisibility(View.GONE);
            type.setText("散单");
        }
    }
}
