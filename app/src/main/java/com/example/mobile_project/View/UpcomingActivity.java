package com.example.mobile_project.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mobile_project.Controller.UpcomAdapter;
import com.example.mobile_project.Controller.UpcomingController;
import com.example.mobile_project.Model.AnimeInUpcomingList;
import com.example.mobile_project.R;

import java.util.List;

public class UpcomingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private UpcomingController controller;
    private UpcomAdapter.OnItemClickListener listener_upcom;
    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcom_list_view);
        this.context = this;

        recyclerView = (RecyclerView) findViewById(R.id.up_recycler_view);

        controller = new UpcomingController(this);
        controller = new UpcomingController(this);
        controller.onCreate();

        controller.loadUpcomList("season", "later");
    }

    public void showUpcomList(List<AnimeInUpcomingList> RespAnimeInUpcomingList)
    {
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, true);
        ((GridLayoutManager) layoutManager).setReverseLayout(false);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new UpcomAdapter(RespAnimeInUpcomingList, listener_upcom, context);
        recyclerView.setAdapter(mAdapter);
    }
}
