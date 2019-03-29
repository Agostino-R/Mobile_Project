package com.example.mobile_project.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mobile_project.Controller.TopController;
import com.example.mobile_project.Model.AnimeInTopList;
import com.example.mobile_project.TopAdapter;
import com.example.mobile_project.R;

import java.util.List;

public class TopActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TopAdapter.OnItemClickListener listener_top;
    private Context context;
    private int page = 1;
    private List<AnimeInTopList> mAnimeInTopLists;
    private TopController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_list_layout);

        recyclerView = findViewById(R.id.top_recycler_view);

        this.context = this;

        controller = new TopController(this);
        controller.onCreate();

        controller.loadTopList("top", "anime", Integer.toString(this.page));
    }

    public void setList(List<AnimeInTopList> mAnimeInTopList)
    {
        this.mAnimeInTopLists = mAnimeInTopList;
    }

    public void addToList(List<AnimeInTopList> mAnimeInTopList)
    {
        for(AnimeInTopList curr : mAnimeInTopList)
        {
            this.mAnimeInTopLists.add(curr);
        }
        mAdapter.notifyItemInserted(this.mAnimeInTopLists.size() - 1);
    }

    public void showTopList(List<AnimeInTopList> RespAnimeInTopList) {
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, true);
        ((GridLayoutManager) layoutManager).setReverseLayout(false);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new TopAdapter(RespAnimeInTopList, getTopListener(),getScrollListener(), context);
        recyclerView.setAdapter(mAdapter);
    }

    private TopAdapter.OnItemClickListener getTopListener() {
        return new TopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AnimeInTopList item) {
                Intent anime_desc_activity = new Intent(context, AnimeDescActivity.class);
                anime_desc_activity.putExtra("SelectedAnimeId", item.getId());
                context.startActivity(anime_desc_activity);
            }
        };
    }

    private TopAdapter.OnBottomReachedListener getScrollListener() {
        return new TopAdapter.OnBottomReachedListener() {
            public void onBottomReached(int position) {
                page++;
                controller.getFollowingTopList("top", "anime", Integer.toString(page));
            }
        };
    }
}