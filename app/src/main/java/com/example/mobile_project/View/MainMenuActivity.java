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

        topButton = findViewById(R.id.top_button);
        seasButton = findViewById(R.id.season_button);
        schedButton = findViewById(R.id.schedule_button);
        upcomButton = findViewById(R.id.upcoming_button);
        searchButton = findViewById(R.id.search_button);
        toWatchButton = findViewById(R.id.to_watch);


        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent topL = new Intent(MainMenuActivity.this, LoadingActivity.class);
                topL.putExtra("activity_to_launch", 1);
                startActivity(topL);
            }
        });

        seasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seas = new Intent(MainMenuActivity.this, LoadingActivity.class);
                seas.putExtra("activity_to_launch", 2);
                startActivity(seas);
            }
        });

        schedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent schL = new Intent(MainMenuActivity.this, LoadingActivity.class);
                schL.putExtra("activity_to_launch", 3);
                startActivity(schL);
            }
        });

        upcomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upL = new Intent(MainMenuActivity.this, LoadingActivity.class);
                upL.putExtra("activity_to_launch", 4);
                startActivity(upL);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(MainMenuActivity.this, LoadingActivity.class);
                search.putExtra("activity_to_launch", 5);
                startActivity(search);
            }
        });

        toWatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toWatch = new Intent(MainMenuActivity.this, LoadingActivity.class);
                toWatch.putExtra("activity_to_launch", 6);
                startActivity(toWatch);
            }
        });
    }
}
