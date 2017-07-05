package ydl.bg.cn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ydl.bg.cn.R;

/**
 * Created by xy on 2017/4/27.
 */

public class ListviwAdapter extends BaseAdapter {
    private ArrayList<Object[]> list;
    private Context context;

    public ListviwAdapter(ArrayList<Object[]> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.get(0).length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.homegoodslistview_item, null);
            vh = new ViewHolder();
            vh.name = (TextView) view.findViewById(R.id.name);
            vh.companyName = (TextView) view.findViewById(R.id.companyname);
            vh.money = (TextView) view.findViewById(R.id.money);
            view.setTag(vh);
        }
        vh = (ViewHolder) view.getTag();
        String[] strings = (String[]) list.get(0);
        String[] strings1 = (String[]) list.get(1);
        Integer[] strings2 = (Integer[]) list.get(2);

        vh.name.setText(strings[i]);
        vh.companyName.setText(strings1[i]);
        vh.money.setText(strings2[i].toString());
        return view;
    }

    class ViewHolder {
        TextView name, companyName, money;
    }
}
