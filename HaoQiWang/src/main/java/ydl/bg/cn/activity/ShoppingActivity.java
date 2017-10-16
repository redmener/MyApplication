package ydl.bg.cn.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

import ydl.bg.cn.R;
import ydl.bg.cn.adapter.ShopingListviwAdapter;

/**
 * Created by hebiao on 2017/5/5.
 */

public class ShoppingActivity extends Activity implements View.OnClickListener {

    private TextView jiagetext, gonghuotext, peisongtext, shaixuantext;
    private ImageView im1, im2, im3, im4;
    private int flag = 0;
    private int flag1 = 0;
    private int flag2 = 0;
    private int flag3 = 0;
    private PopupWindow pop;
    private LinearLayout daohang;
    private View views;
    private ImageView pop_im;
    private ImageView pop_im1;
    private ImageView pop_im2;
    private ListView listView;
    private String name[]={"唐山曹妃甸","唐山曹妃甸","天津大港","江苏启东","宁波北仓","河南京宝","江苏如东","沁水新奥","内蒙时泰","宁波北仓","宁波北仓"
            ,"宁波北仓","天津大港","唐山曹妃甸","宁波北仓"};
    private  String timer[]={"2017-02-02~2017-03-8","2017-02-02~2017-03-8","2017-02-02~2017-03-8","2017-02-02~2017-03-8","2017-02-02~2017-03-8","2017-02-02~2017-03-8","2017-02-02~2017-03-8",
            "2017-02-02~2017-03-8","2017-02-02~2017-03-8","2017-02-02~2017-03-8","2017-02-02~2017-03-8","2017-02-02~2017-03-8","2017-02-02~2017-03-8","2017-02-02~2017-03-8","2017-02-02~2017-03-8",};
   private String  companyName[]={"新奥能源贸易有限公司","山东富贵石油化工有限公司","山东博陆能源科技有限公司","巨轮能源投资有限公司","巨轮能源投资有限公司","巨轮能源投资有限公司","巨轮能源投资有限公司","巨轮能源投资有限公司"
           ,"巨轮能源投资有限公司","巨轮能源投资有限公司","巨轮能源投资有限公司","巨轮能源投资有限公司","巨轮能源投资有限公司","巨轮能源投资有限公司","巨轮能源投资有限公司"};
    private ArrayList<Object[]> list=new ArrayList();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_lb);

        views = LayoutInflater.from(this).inflate(R.layout.popupwindow_item, null);
        pop = new PopupWindow(views, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setOutsideTouchable(true);
        pop.setBackgroundDrawable(new BitmapDrawable());
        initPopupwindowView(views);
        initView();
        initdata();
        listView.setAdapter(new ShopingListviwAdapter(list,this));


    }

    public void initView() {
        LinearLayout jiagelayout = (LinearLayout) findViewById(R.id.detail_activity_home_list1_jiage);
        LinearLayout gonghuolayout = (LinearLayout) findViewById(R.id.detail_activity_home_list1_gonghuo);
        LinearLayout peisonglayout = (LinearLayout) findViewById(R.id.detail_activity_home_list1_peisong);
        LinearLayout shaixuanlayout = (LinearLayout) findViewById(R.id.detail_activity_home_list1_shaixuan);
        daohang = (LinearLayout) findViewById(R.id.daohang);
        jiagetext = (TextView) findViewById(R.id.jiage);
        gonghuotext = (TextView) findViewById(R.id.gonghuo);
        peisongtext = (TextView) findViewById(R.id.peisong);
        shaixuantext = (TextView) findViewById(R.id.shaixuan);
        listView = (ListView) findViewById(R.id.listview);
        im1 = (ImageView) findViewById(R.id.im1);
        im2 = (ImageView) findViewById(R.id.im2);
        im3 = (ImageView) findViewById(R.id.im3);
        im4 = (ImageView) findViewById(R.id.im4);
        jiagelayout.setOnClickListener(this);
        gonghuolayout.setOnClickListener(this);
        peisonglayout.setOnClickListener(this);
        shaixuanlayout.setOnClickListener(this);
    }
    public void initdata(){
        list.add(name);
        list.add(companyName);
        list.add(timer);

    }
    public void initPopupwindowView(View view){
        LinearLayout pop_ziti = (LinearLayout) view.findViewById(R.id.pop_ziti);
        LinearLayout pop_peisong = (LinearLayout) view.findViewById(R.id.pop_peisong);
        LinearLayout pop_quanbu = (LinearLayout) view.findViewById(R.id.pop_quanbu);
        pop_im = (ImageView) view.findViewById(R.id.pop_im);
        pop_im1 = (ImageView) view.findViewById(R.id.pop_im1);
        pop_im2 = (ImageView) view.findViewById(R.id.pop_im2);

        pop_ziti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected3(pop_im);
                pop.dismiss();
            }
        });
        pop_peisong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected3(pop_im1);
                pop.dismiss();
            }
        });
        pop_quanbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected3(pop_im2);
                pop.dismiss();
            }
        });
    }
    private void selected(TextView tv,ImageView iv,int tag){
        reset();
        tv.setTextColor(getResources().getColor(R.color.lanse));
        if (tag==0){
            iv.setImageResource(R.drawable.ups);
            //flag=1;
        }else if (tag==1){
            iv.setImageResource(R.drawable.up);
            //flag=0;
        }
    }
    private  void selected2(){
        pop_im.setVisibility(View.GONE);
        pop_im1.setVisibility(View.GONE);
        pop_im2.setVisibility(View.GONE);

    }
    private  void selected3(View views){
        selected2();
        views.setVisibility(View.VISIBLE);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_activity_home_list1_jiage:
                switch (flag) {
                    case 0:
                        //第一次进来 走这里
                        selected(jiagetext,im1,0);
                        flag = 1;
                        break;
                    case 1:
                        selected(jiagetext,im1,1);
                        flag = 0;
                        break;
                }
                flag1 = 0;
                flag2 = 0;
                flag3 = 0;
                break;
            case R.id.detail_activity_home_list1_gonghuo:
                switch (flag1) {
                    case 0:
                        selected(gonghuotext,im2,0);
                        flag1 = 1;
                        break;
                    case 1:
                        selected(gonghuotext,im2,1);
                        flag1 = 0;
                        break;
                }
                flag = 0;
                flag2 = 0;
                flag3 = 0;
                break;
            case R.id.detail_activity_home_list1_peisong:
                switch (flag2) {
                    case 0:
                        selected(peisongtext,im3,0);
                        pop.showAsDropDown(daohang);
                        flag2 = 1;
                        break;
                    case 1:
                        selected(peisongtext,im3,1);
                        pop.dismiss();
                        flag2 = 0;
                        break;
                }
                flag = 0;
                flag1 = 0;
                flag3 = 0;
                break;
            case R.id.detail_activity_home_list1_shaixuan:
                switch (flag3) {
                    case 0:
                        selected(shaixuantext,im4,0);
                        flag3 = 1;
                        break;
                    case 1:
                        selected(shaixuantext,im4,1);
                        flag3 = 0;
                        break;
                }
                flag = 0;
                flag1 = 0;
                flag2 = 0;
                break;
        }
    }
    private void reset() {
        jiagetext.setTextColor(getResources().getColor(R.color.heise));
        gonghuotext.setTextColor(getResources().getColor(R.color.heise));
        peisongtext.setTextColor(getResources().getColor(R.color.heise));
        shaixuantext.setTextColor(getResources().getColor(R.color.heise));
        im1.setImageResource(R.drawable.ic_filter_down);
        im2.setImageResource(R.drawable.ic_filter_down);
        im3.setImageResource(R.drawable.ic_filter_down);
        im4.setImageResource(R.drawable.ic_filter_down);
    }

}
