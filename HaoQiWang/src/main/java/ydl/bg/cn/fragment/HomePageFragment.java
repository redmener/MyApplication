package ydl.bg.cn.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ydl.bg.cn.R;
import ydl.bg.cn.Untils.ObservableScrollView;
import ydl.bg.cn.Untils.SystemStatesBarUtils;
import ydl.bg.cn.activity.CommodityDetailsActivity;
import ydl.bg.cn.activity.ReleaseFor;
import ydl.bg.cn.activity.ShoppingActivity;
import ydl.bg.cn.adapter.ListviwAdapter;

public class HomePageFragment extends Fragment implements View.OnClickListener, ObservableScrollView.ScrollViewListener {

    private View mRootView;
    private ViewPager viewpager;
    private int[] imgsDate = {R.mipmap.s, R.mipmap.ss, R.mipmap.sss};
    private View[] views = new View[imgsDate.length];
    private ImageView imageViews[] = new ImageView[views.length];
    private LinearLayout yuandianLayout, hengxian3, jinrushangcheng,releasefor;
    private LinearLayout layout;
    private Handler handler;
    private int index;
    private ImageView imageView;
    private ListView listView;
    private ArrayList<Object[]> listviewList = new ArrayList<>();
    private String name[] = {"内蒙古攻击伊泰", "定边绿能", "内蒙古森泰", "榆林华晨", "宁夏天利丰", "宁夏天利丰", "宁夏天利丰", "宁夏天利丰", "宁夏天利丰", "宁夏天利丰"};
    private String companyName[] = {"内蒙古攻击伊泰有限公司", "定边绿能有限公司", "内蒙古森泰有限公司", "榆林华晨有限公司", "宁夏天利丰有限公司", "宁夏天利丰有限公司",
            "宁夏天利丰有限公司", "宁夏天利丰有限公司", "宁夏天利丰有限公司", "宁夏天利丰有限公司"};
    private Integer money[] = {2646, 6615, 8888, 9999, 1234, 1234, 1234, 1234, 1234, 1234};
    private ImageView im;
    private ObservableScrollView scroolview;
    private View mTopView;
    private int height;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.home_fragment, container, false);
        mTopView = mRootView.findViewById(R.id.view_topview);
        //设置状态栏的初始颜色
        SystemStatesBarUtils.setTopViewHeightColor(getActivity(), mTopView, R.color.transparence);
        getViewId();
        inittupian();
        initYuan();
        initListeners();
        listView.setAdapter(new ListviwAdapter(listviewList, getActivity()));
        setListViewHeightBasedOnChildren(listView);
        slistviewOnclickListener();
        viewpager.setAdapter(new MyAdapter());
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                for (int n = 0; n < imageViews.length; n++) {
                    if (n == arg0 % imageViews.length) {
                        imageViews[n].setImageResource(R.mipmap.incon_hei);
                    } else {
                        imageViews[n].setImageResource(R.mipmap.incon_bai);
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            //判断手动滑动viewpager的状态
            @Override
            public void onPageScrollStateChanged(int arg0) {
                switch (arg0) {
                    case ViewPager.SCROLL_STATE_DRAGGING://手指滑动状态
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE://停止状态

                        break;
                    case ViewPager.SCROLL_STATE_SETTLING://自动滑动状态

                        break;

                    default:
                        break;
                }

            }
        });

        handler = new Handler() {
            private int what;

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                what = msg.what;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
                    }
                }, 2000);
            }
        };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                index++;
                handler.sendEmptyMessage(index);
            }
        }, 2000, 2000);

        return mRootView;
    }

    public void getViewId() {
        layout = (LinearLayout) mRootView.findViewById(R.id.ll_search);
        hengxian3 = (LinearLayout) mRootView.findViewById(R.id.hengxian3);
        jinrushangcheng = (LinearLayout) mRootView.findViewById(R.id.jinrushangcheng);
        releasefor = (LinearLayout) mRootView.findViewById(R.id.releasefor);
        viewpager = (ViewPager) mRootView.findViewById(R.id.viewpager);
        yuandianLayout = (LinearLayout) mRootView.findViewById(R.id.yuandian_im);
        listView = (ListView) mRootView.findViewById(R.id.listview);
        scroolview = (ObservableScrollView) mRootView.findViewById(R.id.scroolview);
        im = (ImageView) mRootView.findViewById(R.id.tv_city);
        im.setOnClickListener(this);
        hengxian3.setOnClickListener(this);
        releasefor.setOnClickListener(this);
        jinrushangcheng.setOnClickListener(this);
        scroolview.setScrollViewListener(this);
        listviewList.add(name);
        listviewList.add(companyName);
        listviewList.add(money);

    }

    //  加载小圆点的方法  默认为黑色
    public void initYuan() {
        for (int i = 0; i < imageViews.length; i++) {
            final ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(15, 15));
            imageView.setImageResource(R.mipmap.incon_bai);
            yuandianLayout.addView(imageView);
            imageViews[i] = imageView;
            imageViews[0].setImageResource(R.mipmap.incon_hei);
        }
    }

    public void slistviewOnclickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object[] name = listviewList.get(0);
                Object[] companyName = listviewList.get(0);
                Object[] money = listviewList.get(0);

                Bundle bundle = new Bundle();
                bundle.putString("name", (String) name[i]);
                bundle.putString("companyName", (String) companyName[i]);
                bundle.putString("money", (String) money[i]);
                Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    // 设置轮播的图片
    public void inittupian() {
        for (int i = 0; i < views.length; i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.homepager_item, null);
            imageView = (ImageView) view.findViewById(R.id.page_imageview);
            imageView.setImageResource(imgsDate[i]);
            views[i] = view;
        }
    }

    //这是解决嵌套listview只显示一条的方法，计算其高度
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hengxian3:
                startActivity(new Intent(getActivity(), ShoppingActivity.class));
                break;
            case R.id.tv_city:
                startActivity(new Intent(getActivity(), ShoppingActivity.class));
                break;
            case R.id.jinrushangcheng:
                startActivity(new Intent(getActivity(), ShoppingActivity.class));
                break;
            case R.id.releasefor:
                startActivity(new Intent(getActivity(), ReleaseFor.class));
                break;
        }
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色 滑动到顶部
            layout.setBackgroundColor(Color.argb(0, 252, 175, 36));
            mTopView.setBackgroundColor(Color.argb(0, 252, 175, 36));
            //toolbar_common.setAlpha(0);
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变 中间渐变
            float scale = (float) y / height;
            float alpha = (205 * scale);
            layout.setBackgroundColor(Color.argb((int) alpha, 72, 137, 219));
            mTopView.setBackgroundColor(Color.argb((int) alpha, 72, 137, 219));
            //toolbar_common.setAlpha(scale);
            Log.e("111-------------------", "scale------" + scale + "alpha-----" + alpha);
        } else {    //滑动到banner下面设置普通颜色 滑动到底部了
            mTopView.setBackgroundColor(Color.argb(205, 72, 137, 219));
            layout.setBackgroundColor(Color.argb(205, 72, 137, 219));
            //toolbar_common.setAlpha(1);
            //toolbar_common.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        }
    }

    private void initListeners() {
        ViewTreeObserver vto = viewpager.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                height = viewpager.getHeight();
                scroolview.setScrollViewListener(HomePageFragment.this);
            }
        });
    }

    //viewpager的适配器
    class MyAdapter extends PagerAdapter {
        //返回要加载视图的多少
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(views[position % views.length]);
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(views[position % views.length]);
            return views[position % views.length];
        }
    }
}