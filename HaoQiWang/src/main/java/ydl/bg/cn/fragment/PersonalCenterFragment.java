package ydl.bg.cn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import ydl.bg.cn.R;
import ydl.bg.cn.Untils.SystemStatesBarUtils;
import ydl.bg.cn.activity.ZhangHuXinXi;
import ydl.bg.cn.gerenActivity.MyBill;
import ydl.bg.cn.gerenActivity.MyProvider;
import ydl.bg.cn.gerenActivity.MySettings;
import ydl.bg.cn.gerenActivity.MyXieQiDianActivity;
import ydl.bg.cn.gerenActivity.ZiZhiRenZhengActivity;

/**
 * Created by xy on 2017/4/20.
 */
public class PersonalCenterFragment extends Fragment implements View.OnClickListener {

    private View mRootView,layouts;
    private LinearLayout layout, zizhirenzheng_layout,xieqidian,gongyingshang,myfapiao,mysettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.personalcenter_fragment, container, false);
            layouts = (View) mRootView.findViewById(R.id.view_topview);
            SystemStatesBarUtils.setTopViewHeightColor(getActivity(),layouts,R.color.beijingse);
            initview(mRootView);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    public void initview(View view) {
        layout = (LinearLayout) view.findViewById(R.id.geren_layout);
        zizhirenzheng_layout = (LinearLayout) view.findViewById(R.id.zizhirenzheng_layout);
        xieqidian = (LinearLayout) view.findViewById(R.id.xieqidian);
        gongyingshang = (LinearLayout) view.findViewById(R.id.gongyingshang);
        myfapiao = (LinearLayout) view.findViewById(R.id.myfapiao);
        mysettings = (LinearLayout) view.findViewById(R.id.mysettings);
        layout.setOnClickListener(this);
        zizhirenzheng_layout.setOnClickListener(this);
        xieqidian.setOnClickListener(this);
        gongyingshang.setOnClickListener(this);
        myfapiao.setOnClickListener(this);
        mysettings.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.geren_layout:
                startActivity(new Intent(getActivity(), ZhangHuXinXi.class));
                break;
            case R.id.zizhirenzheng_layout:
                startActivity(new Intent(getActivity(), ZiZhiRenZhengActivity.class));
                break;
            case R.id.xieqidian:
                startActivity(new Intent(getActivity(), MyXieQiDianActivity.class));
                break;
            case R.id.gongyingshang:
                startActivity(new Intent(getActivity(), MyProvider.class));
                break;
            case R.id.myfapiao:
                startActivity(new Intent(getActivity(), MyBill.class));
                break;
            case R.id.mysettings:
                startActivity(new Intent(getActivity(), MySettings.class));
                break;
        }
    }
}
