package com.example.mobile_project.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.mobile_project.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);

        Button seasButton;
        Button topButton;
        Button upcomButton;
        Button schedButton;
        Button searchButton;
        Button toWatchButton;
        final ScrollView mScrollView;

        topButton = findViewById(R.id.top_button);
        seasButton = findViewById(R.id.season_button);
        schedButton = findViewById(R.id.schedule_button);
        upcomButton = findViewById(R.id.upcoming_button);
        searchButton = findViewById(R.id.search_button);
        toWatchButton = findViewById(R.id.to_watch);


        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent topL = new Intent(MainMenuActivity.this, TopActivity.class);
                startActivity(topL);
            }
        });

        seasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent schL = new Intent(MainMenuActivity.this, SeasonActivity.class);
                startActivity(schL);
            }
        });

        schedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent schL = new Intent(MainMenuActivity.this, SchedActivity.class);
                startActivity(schL);
            }
        });

        upcomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upL = new Intent(MainMenuActivity.this, UpcomingActivity.class);
                startActivity(upL);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(MainMenuActivity.this, SearchActivity.class);
                startActivity(search);
            }
        });

        toWatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent options = new Intent(MainMenuActivity.this, ToWatchActivity.class);
                startActivity(options);
            }
        });
    }
}
