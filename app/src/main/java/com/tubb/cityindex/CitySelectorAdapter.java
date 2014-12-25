package com.tubb.cityindex;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

public class CitySelectorAdapter extends BaseAdapter implements Indexer {

    private final LayoutInflater mInflate;
    private List<City> mCitys;
    private Context mContext;
    private HashMap<String, Integer> indexMap = new HashMap<String, Integer>();

    public CitySelectorAdapter(Context context, List<City> citys) {
        mCitys = citys;
        mContext = context;
        mInflate = LayoutInflater.from(mContext);
        // 列表特征和分组首项进行关联
        for (int i = 0; i < mCitys.size(); i++) {
            City city = mCitys.get(i);
            String cityId = city.getId();
            if(cityId == null || "".equals(cityId)) continue;
            String section = cityId.toUpperCase().substring(0, 1);
            if(!indexMap.containsKey(section)){
                indexMap.put(section, i);
            }
        }
    }
    public int getCount() {
        return mCitys.size();
    }
    public Object getItem(int position) {
        return mCitys.get(position);
    }
    public long getItemId(int position) {
        return mCitys.get(position).getId().hashCode();
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflate.inflate(R.layout.city_list_item, parent, false);
            holder = new ViewHolder();
            holder.tvName = (TextView)convertView.findViewById(R.id.tvName);
            holder.tvSection = (TextView)convertView.findViewById(R.id.tvSection);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        City city = mCitys.get(position);
        holder.tvName.setText(city.getName());

        String id = city.getId();
        char idFirstChar = id.toUpperCase().charAt(0);
        if (position == 0) {
            setIndex(holder.tvSection, String.valueOf(idFirstChar));
        } else {
            String preLabel = mCitys.get(position - 1).getId();
            char preFirstChar = preLabel.toUpperCase().charAt(0);
            if (idFirstChar != preFirstChar) { // diff group
                setIndex(holder.tvSection, String.valueOf(idFirstChar));
            } else { // same group
                holder.tvSection.setVisibility(View.GONE);
            }
        }
        return convertView;
    }

    private void setIndex(TextView section, String str){
        section.setVisibility(View.VISIBLE);
        if("#".equals(str)) section.setText("当前城市");
        else section.setText(str);
    }

    @Override
    public int getStartPositionOfSection(String section) {
        if(indexMap.containsKey(section))
            return indexMap.get(section);
        return -1;
    }

    static class ViewHolder{
        TextView tvName;
        TextView tvSection;
    }
}
