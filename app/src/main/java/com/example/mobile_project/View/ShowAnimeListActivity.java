package com.example.mobile_project.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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

public class ShowAnimeListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TopAdapter.OnItemClickListener listener_top;
    private UpcomAdapter.OnItemClickListener listener_upcom;
    private SeasAdapter.OnItemClickListener listener_seas;
    private SchedAdapter.OnItemClickListener listener_sched;
    private Context context;
    private int page = 1;
    private List<AnimeInTopList> mAnimeInTopLists;
    private MainController controller;
    private String param1, param2, nat, day, season, year;
    private Spinner daySpin, seasonSpin;
    private EditText yearSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_list_view);

        day = "monday";
        season = "winter";
        year = "2018";

        context = ShowAnimeListActivity.this;
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        daySpin = (Spinner) findViewById(R.id.daySelect);
        seasonSpin = (Spinner) findViewById(R.id.seasonSelect);
        yearSelect = (EditText) findViewById(R.id.yearSelect);

        yearSelect.setText(year);

        daySpin.setVisibility(View.GONE);
        seasonSpin.setVisibility(View.GONE);
        yearSelect.setVisibility(View.GONE);

        Intent i = getIntent();
        this.param1 = (String) i.getSerializableExtra("GetParam1");
        this.param2 = (String) i.getSerializableExtra("GetParam2");
        this.nat = (String) i.getSerializableExtra("Nature");

        controller = new MainController(this);
        controller.onCreate();

        switch (nat)
        {
            case "top":     controller.loadTopList(param1, param2, Integer.toString(this.page));
                            break;
            case "seas":    controller.loadSeasList(year, season);
                            yearSelect.setVisibility(View.VISIBLE);
                            seasonSpin.setVisibility(View.VISIBLE);
                            break;
            case "sched":   controller.loadSchedList(param1, day);
                            daySpin.setVisibility(View.VISIBLE);
                            break;
            case "upcom":   controller.loadUpcomList(param1, param2);
                            break;
            default:        Log.i("Error Switch", "Invalid Nat");
                            break;
        }

        ArrayAdapter<CharSequence> spinnAdapter = ArrayAdapter.createFromResource(this,
                R.array.days, android.R.layout.simple_spinner_item);
        spinnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpin.setAdapter(spinnAdapter);
        daySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = parent.getItemAtPosition(position).toString();
                controller.loadSchedList("schedule", day.toLowerCase());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> spinnAdapterS = ArrayAdapter.createFromResource(this,
                R.array.seasons, android.R.layout.simple_spinner_item);
        spinnAdapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seasonSpin.setAdapter(spinnAdapterS);
        seasonSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                season = parent.getItemAtPosition(position).toString();
                controller.loadSeasList(year, season.toLowerCase());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yearSelect.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                year = s.toString();
                controller.loadSeasList(year, season.toLowerCase());
                yearSelect.requestFocus();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

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
                controller.getFollowingTopList(param1, param2, Integer.toString(page));
            }
        };
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