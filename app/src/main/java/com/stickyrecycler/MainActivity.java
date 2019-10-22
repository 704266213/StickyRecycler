package com.stickyrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stickyrecycler.widget.SideBar;
import com.stickyrecycler.widget.StickyRecyclerHeadersDecoration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SideBar.OnLetterChangedListener {

    private SideBar sideBar;

    private RecyclerView recyclerView;
    private SelectCityAdapter selectCityAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        sideBar = findViewById(R.id.sideBar);
        sideBar.setOnLetterChangedListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        // 为RecyclerView添加LayoutManager
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        selectCityAdapter = new SelectCityAdapter();
        // 为RecyclerView中的Item添加Header头部（自动获取头部ID，将相邻的ID相同的聚合到一起形成一个Header）
        StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(selectCityAdapter);
        recyclerView.addItemDecoration(headersDecor);
        recyclerView.setAdapter(selectCityAdapter);

        String json = getJson("city.json");
        Gson gson = new Gson();
        List<SelectCityBean> selectCityBeans = gson.fromJson(json, new TypeToken<List<SelectCityBean>>() {
        }.getType());
        selectCityAdapter.addDataToList(selectCityBeans);
    }

    @Override
    public void letterChange(String letter) {
        int position = selectCityAdapter.getFirstChart(letter);
        if (position != -1) {
            layoutManager.scrollToPositionWithOffset(position, 0);
        }
    }

    public String getJson(String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


}
