package com.example.mobile_project.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mobile_project.Controller.MainController;
import com.example.mobile_project.Model.AnimeInTopList;
import com.example.mobile_project.MyAdapter;
import com.example.mobile_project.R;

import java.util.List;

public class ShowAnimeList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter.OnItemClickListener listener;
    private Context context;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_list_view);

        context = ShowAnimeList.this;
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        String param1, param2, nat;
        Intent i = getIntent();
        param1 = (String) i.getSerializableExtra("GetParam1");
        param2 = (String) i.getSerializableExtra("GetParam2");
        nat = (String) i.getSerializableExtra("Nature");

        controller = new MainController(this);
        controller.onCreate();

        switch (nat)
        {
            case "top":     controller.loadTopList(param1, param2);
                            break;
            case "seas":    controller.loadSeasList(param1, param2);
                            break;
            case "sched":   controller.loadSchedList(param1, param2);
                            break;
            case "upcom":   controller.loadUpcomList(param1, param2);
                            break;
            default:        Log.i("Error Switch", "Invalid Nat");
                            break;
        }
    }

    public void showList(List<AnimeInTopList> listTopAnimeInTopListListStruct) {

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, true);
        ((GridLayoutManager) layoutManager).setReverseLayout(false);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new MyAdapter(listTopAnimeInTopListListStruct, listener, context);
        recyclerView.setAdapter(mAdapter);
    }
}