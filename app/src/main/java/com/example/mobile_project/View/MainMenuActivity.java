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
        Button scrButton;
        Button searchButton;
        final ScrollView mScrollView;

        scrButton = findViewById(R.id.launch_button);
        topButton = findViewById(R.id.top_button);
        seasButton = findViewById(R.id.season_button);
        schedButton = findViewById(R.id.schedule_button);
        upcomButton = findViewById(R.id.upcoming_button);
        searchButton = findViewById(R.id.search_button);
        mScrollView = findViewById(R.id.welcome_menu);

        scrButton.setEnabled(true);

        scrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollView.fullScroll(View.FOCUS_DOWN);
            }
        });

        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent topL = new Intent(MainMenuActivity.this, ShowAnimeListActivity.class);
                topL.putExtra("GetParam1", "top");
                topL.putExtra("GetParam2", "anime");
                topL.putExtra("Nature", "top");
                startActivity(topL);
            }
        });

        schedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent schL = new Intent(MainMenuActivity.this, ShowAnimeListActivity.class);
                schL.putExtra("GetParam1", "schedule");
                schL.putExtra("GetParam2", "");
                schL.putExtra("Nature", "sched");
                startActivity(schL);
            }
        });

        upcomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upL = new Intent(MainMenuActivity.this, ShowAnimeListActivity.class);
                upL.putExtra("GetParam1", "season");
                upL.putExtra("GetParam2", "later");
                upL.putExtra("Nature", "upcom");
                startActivity(upL);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upL = new Intent(MainMenuActivity.this, ShowAnimeListActivity.class);
                upL.putExtra("GetParam1", "search");
                upL.putExtra("GetParam2", "anime");
                upL.putExtra("Nature", "search");
                startActivity(upL);
            }
        });
    }
}
