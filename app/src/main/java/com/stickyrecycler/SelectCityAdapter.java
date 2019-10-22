package com.stickyrecycler;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stickyrecycler.widget.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SelectCityAdapter extends RecyclerView.Adapter<SelectCityAdapter.SelectCityViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {


    private List<SelectCityBean> listData = new ArrayList<>();

    public void addDataToList(List<SelectCityBean> listData) {
        this.listData.addAll(listData);
        notifyDataSetChanged();
        initLetterMap();
    }

    private Map<String, Integer> letterMap = new HashMap<>();

    public void initLetterMap() {
        String firstLetter = "";
        int size = listData.size();
        for (int i = 0; i < size; i++) {
            SelectCityBean selectCityBean = listData.get(i);
            String letter = selectCityBean.getLetter();
            if (!firstLetter.equalsIgnoreCase(letter)) {
                letterMap.put(letter, i + 1);
                firstLetter = letter;
            }
        }
    }

    public int getFirstChart(String letter) {
        if (letterMap.get(letter) != null) {
            return letterMap.get(letter).intValue();
        } else {
            return -1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @NonNull
    @Override
    public SelectCityAdapter.SelectCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new SelectCityViewHolder(layoutInflater.inflate(R.layout.rb_select_city_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SelectCityAdapter.SelectCityViewHolder holder, int position) {
        SelectCityBean selectCityBean = listData.get(position);
        holder.cityName.setText(selectCityBean.getCityName());
    }

    @Override
    public long getHeaderId(int position) {
        if (position >= 0) {
            return listData.get(position).getLetter().charAt(0);
        }
        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rb_select_city_letter_item, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        String letterStr = listData.get(position).getLetter();
        if (letterStr.length() > 0) {
            TextView letter = (TextView) holder.itemView;
            letter.setText(String.valueOf(letterStr.charAt(0)));
        }
    }

    public class SelectCityViewHolder extends RecyclerView.ViewHolder {

        private TextView cityName;


        public SelectCityViewHolder(View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityName);
        }
    }

}
