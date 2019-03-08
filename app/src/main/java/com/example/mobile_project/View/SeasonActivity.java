package com.example.mobile_project.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mobile_project.Controller.SeasAdapter;
import com.example.mobile_project.Controller.SeasonController;
import com.example.mobile_project.Model.AnimeInSeasList;
import com.example.mobile_project.R;

import java.util.List;

public class SeasonActivity extends AppCompatActivity {
    private SeasAdapter.OnItemClickListener listener_seas;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String season, year;
    Spinner seasonSpin;
    private EditText yearSelect;
    Context context;
    private SeasonController controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_anime_layout);

        season = "winter";
        year = "2018";

        recyclerView = (RecyclerView) findViewById(R.id.seas_recycler_view);

        seasonSpin = (Spinner) findViewById(R.id.seasonSelect);
        yearSelect = (EditText) findViewById(R.id.yearSelect);
        yearSelect.setText(year);

        controller = new SeasonController(this);
        controller.onCreate();
        controller.loadSeasList(year, season);

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

}
