package procressbar.zk.com.mapleadtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import procressbar.zk.com.mapleadtest.R;
import procressbar.zk.com.mapleadtest.bean.MapInfo;

/**
 * author: ZK.
 * date:   On 2016/8/16
 */
public class MapListAdapter extends BaseAdapter {
    private List<MapInfo> maps;
    private Context mContext;

    public MapListAdapter(Context context) {
        mContext = context;
    }

    public  void  setDatas(List<MapInfo> maps){
        this.maps = maps;

    }

    @Override
    public int getCount() {
        return maps.size();
    }

    @Override
    public Object getItem(int i) {
        return maps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();

        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_dialog,viewGroup,false);
            viewHolder.mapIv = (ImageView) view.findViewById(R.id.iv_map_logo);
            viewHolder.nameTv = (TextView) view.findViewById(R.id.tv_map_name);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mapIv.setImageResource(maps.get(i).id);
        viewHolder.nameTv.setText(maps.get(i).name);

        return view;
    }

    class  ViewHolder{
        ImageView mapIv;
        TextView nameTv;
    }
}
