package ydl.bg.cn.dadanfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ydl.bg.cn.R;
import ydl.bg.cn.activity.ReleaseFor;

/**
 * Created by xy on 2017/5/2.
 */

public class LargeChirdFragmentOne extends Fragment {
    private View mRootView;
    private Button sd_fabuqiugou;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.largefragment_one, container, false);
            sd_fabuqiugou = (Button) mRootView.findViewById(R.id.sd_fabuqiugou);

            sd_fabuqiugou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //传值  由于从大单 和散单跳过去  ，布局显示不一样  所以要写值
                    Intent intent=new Intent(getActivity(), ReleaseFor.class);
                    intent.putExtra("dadan",1);
                    startActivity(intent);
                }
            });
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }
}
