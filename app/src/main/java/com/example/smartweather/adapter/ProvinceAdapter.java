package com.example.smartweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smartweather.R;
import com.example.smartweather.data.bean.Province;

import java.util.List;

/*
 * CREATED BY: Sinry
 * TIME: 2019/3/12 19:31
 * DESCRIPTION:
 */

public class ProvinceAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mCityNameList;

    public ProvinceAdapter(Context context, List<String> dataList){
        this.mContext = context;
        this.mCityNameList = dataList;
    }

    @Override
    public int getCount() {
        return mCityNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCityNameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_city, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = convertView.findViewById(R.id.city_text);
            viewHolder.mView = convertView.findViewById(R.id.item_view);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextView.setText(mCityNameList.get(position));

        return convertView;
    }

    class ViewHolder{
        View mView;
        TextView mTextView;
    }
}
