package com.example.mobile_project.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mobile_project.Controller.SchedAdapter;
import com.example.mobile_project.Controller.SchedController;
import com.example.mobile_project.Model.AnimeInSchedList;
import com.example.mobile_project.R;

import java.util.List;

public class SchedActivity extends AppCompatActivity {
    private SchedAdapter.OnItemClickListener listener_sched;
    private String day;
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Spinner daySpin;
    private SchedController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_layout);

        day = "monday";
        this.context = this;

        recyclerView = (RecyclerView) findViewById(R.id.sched_recycler_view);

        daySpin = (Spinner) findViewById(R.id.daySelect);

        controller = new SchedController(this);
        controller.onCreate();
        controller.loadSchedList("schedule", day);

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

}
