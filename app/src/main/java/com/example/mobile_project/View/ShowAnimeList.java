package com.example.mobile_project.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mobile_project.Controller.MainController;
import com.example.mobile_project.Model.Anime;
import com.example.mobile_project.MyAdapter;
import com.example.mobile_project.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowAnimeList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_list_view);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        controller = new MainController(this);
        controller.onCreate();
    }

    public void showList(List<Anime> listAnime) {

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, true);
        ((GridLayoutManager) layoutManager).setReverseLayout(false);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new MyAdapter(listAnime);
        recyclerView.setAdapter(mAdapter);
    }
}