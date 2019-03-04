package com.example.mobile_project.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobile_project.Controller.MainController;
import com.example.mobile_project.Controller.SchedAdapter;
import com.example.mobile_project.Controller.SeasAdapter;
import com.example.mobile_project.Controller.UpcomAdapter;
import com.example.mobile_project.Model.AnimeInSchedList;
import com.example.mobile_project.Model.AnimeInSeasList;
import com.example.mobile_project.Model.AnimeInTopList;
import com.example.mobile_project.Model.AnimeInUpcomingList;
import com.example.mobile_project.Controller.TopAdapter;
import com.example.mobile_project.R;

import java.util.List;

public class ShowAnimeList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TopAdapter.OnItemClickListener listener_top;
    private UpcomAdapter.OnItemClickListener listener_upcom;
    private SeasAdapter.OnItemClickListener listener_seas;
    private SchedAdapter.OnItemClickListener listener_sched;
    private Context context;
    //private Button next_page;
    //private Button prev_page;
    private TextView page_numb;
    //private int page = 1;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_anime_list_view);

        context = ShowAnimeList.this;
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        page_numb = (TextView) findViewById(R.id.page_number);

        Intent i = getIntent();
        final String param1 = (String) i.getSerializableExtra("GetParam1");
        final String param2 = (String) i.getSerializableExtra("GetParam2");
        String nat = (String) i.getSerializableExtra("Nature");

        /*if(nat == "top")
        {
            setContentView(R.layout.anime_top_list_view);
        }
        else
            setContentView(R.layout.default_anime_list_view);

        this.next_page = (Button) findViewById(R.id.next_page_button);
        this.prev_page = (Button) findViewById(R.id.prev_page_button);*/

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

        /*this.prev_page.setEnabled(false);

        if(this.page>=2)
            this.prev_page.setEnabled(true);
        else
            this.prev_page.setEnabled(false);

        this.next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                page_numb.setText(Integer.toString(page));
                controller.loadTopList(param1, param2, Integer.toString(page));
            }
        });

        this.prev_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page--;
                page_numb.setText(Integer.toString(page));
                controller.loadTopList(param1, param2, Integer.toString(page));
            }
        });*/

    }

    public void showTopList(List<AnimeInTopList> RespAnimeInTopList) {

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, true);
        ((GridLayoutManager) layoutManager).setReverseLayout(false);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new TopAdapter(RespAnimeInTopList, listener_top, context);
        recyclerView.setAdapter(mAdapter);
    }

    public void showSeasList(List<AnimeInSeasList> RespAnimeInSeasList)
    {
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, true);
        ((GridLayoutManager) layoutManager).setReverseLayout(false);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new SeasAdapter(RespAnimeInSeasList, listener_seas, context);
        recyclerView.setAdapter(mAdapter);
    }

    public void showSchedList(List<AnimeInSchedList> RespAnimeInSchedList)
    {
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, true);
        ((GridLayoutManager) layoutManager).setReverseLayout(false);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new SchedAdapter(RespAnimeInSchedList, listener_sched, context);
        recyclerView.setAdapter(mAdapter);
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