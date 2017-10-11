package procressbar.zk.com.mapleadtest.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import procressbar.zk.com.mapleadtest.bean.MapInfo;
import procressbar.zk.com.mapleadtest.adapter.MapListAdapter;
import procressbar.zk.com.mapleadtest.utils.MapTranslateUtils;
import procressbar.zk.com.mapleadtest.R;

/**
 * author: ZK.
 * date:   On 2016/8/16
 */
public class MapSelectDialog extends Dialog {


    private Activity mActivity;

    // 起点坐标 113.40694,23.049772 百度坐标
    private double nowLat = 23.049772;
    private double nowLng = 113.40694;
    private String nowLocation = "广州大学城大学城南地铁站";

    // 目的坐标  113.32983,23.112109

    private double desLat = 23.112109;
    private double desLng = 113.32983;
    private String desAddress = "广州塔";
    private TextView mTvTitle;
    private ListView mLlMap;


    public MapSelectDialog(Activity activity, int themeResId) {
        super(activity, themeResId);
        mActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_dialog, null);
        setContentView(view);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mLlMap = (ListView) view.findViewById(R.id.ll_map);

        MapListAdapter adapter = new MapListAdapter(mActivity);
        adapter.setDatas(getMaps());
        mLlMap.setAdapter(adapter);
        initListener();
    }

    private void initListener() {
        mLlMap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        selectBaidu();
                        break;
                    case 1:
                        selectGaode();
                        break;
                    case 2:
                        selectTencent();
                        break;
                }
            }


        });
    }


    private void selectBaidu() {
        this.dismiss();
        try {
            //调起App
            if (isInstallByread("com.baidu.BaiduMap")) {
                Intent intent = Intent.getIntent("intent://map/direction?origin=latlng:"
                        + nowLat + "," + nowLng
                        + "|name:&destination=" + desAddress + "&mode=driving®ion=" + "我的位置"
                        + "&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                mActivity.startActivity(intent);
            } else {
                /*String url = "http://api.map.baidu.com/direction?origin=latlng:" + mLatitude + ","
                                + mLongitude + "|name:&destination=" + mDestination
                                + "&mode=driving&output=html&src=天工项目共建";
                        WebViewActivity.launch(getActivity(), url, "网页版地图导航");*/
                Toast.makeText(mActivity, "如果您没有安装百度地图APP，" +
                        "可能无法正常使用导航，建议选择其他地图", Toast.LENGTH_SHORT).show();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    private void selectGaode() {
        this.dismiss();
        double[] txDesLatLng = MapTranslateUtils.map_bd2hx(desLat, desLng);
        double[] txNowLatLng = MapTranslateUtils.map_bd2hx(nowLat, nowLng);
        if (isInstallByread("com.autonavi.minimap")) {
            try {
                Intent intentOther = new Intent("android.intent.action.VIEW",
                        Uri.parse("androidamap://navi?sourceApplication=amap&lat="
                                + desLat + "&lon=" + desLng + "&dev=1&stype=0"));
                intentOther.setPackage("com.autonavi.minimap");
                intentOther.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mActivity.startActivity(intentOther);
            } catch (Exception e) {
                String url = "http://m.amap.com/?from="
                        + nowLat + "," + nowLng
                        + "(from)&to=" + desLat + "," + desLng + "(to)&type=0&opt=1&dev=0";
                WebViewActivity.launch(mActivity, url, "网页版地图导航");
            }

        } else {
            String url = "http://m.amap.com/?from="
                    + txNowLatLng[0] + "," + txNowLatLng[1]
                    + "(from)&to=" + txDesLatLng[0] + "," + txDesLatLng[1] + "(to)&type=0&opt=1&dev=0";
            WebViewActivity.launch(mActivity, url, "网页版地图导航");

        }

    }


    private void selectTencent() {
        this.dismiss();
        double[] txDesLatLng = MapTranslateUtils.map_bd2hx(desLat, desLng);
        double[] txNowLatLng = MapTranslateUtils.map_bd2hx(nowLat, nowLng);
        String url = "http://apis.map.qq.com/uri/v1/routeplan?type=drive&from=&fromcoord="
                + txNowLatLng[0] + "," + txNowLatLng[1]
                + "&to=&tocoord=" + txDesLatLng[0] + "," + txDesLatLng[1] + "&policy=0&referer=myapp";
        WebViewActivity.launch(mActivity, url, "网页版地图导航");
    }


    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }


    public List<MapInfo> getMaps() {
        List<MapInfo> maps = new ArrayList<MapInfo>();
        MapInfo map0 = new MapInfo();
        map0.id = R.drawable.icon_baidu_logo;
        map0.name = "百度地图";

        MapInfo map1 = new MapInfo();
        map1.id = R.drawable.icon_gaode_logo;
        map1.name = "高德地图";

        MapInfo map2 = new MapInfo();
        map2.id = R.drawable.icon_tencent_logo;
        map2.name = "腾讯地图";

        maps.add(map0);
        maps.add(map1);
        maps.add(map2);
        return maps;


    }
}
