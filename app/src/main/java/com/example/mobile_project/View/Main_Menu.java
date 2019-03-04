package com.example.mobile_project.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.mobile_project.Controller.MainController;
import com.example.mobile_project.R;

public class Main_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button seasButton;
        Button topButton;
        Button upcomButton;
        Button schedButton;
        Button scrButton;
        final ScrollView mScrollView;

        scrButton = findViewById(R.id.launch_button);
        topButton = findViewById(R.id.top_button);
        seasButton = findViewById(R.id.season_button);
        schedButton = findViewById(R.id.schedule_button);
        upcomButton = findViewById(R.id.upcoming_button);
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
                Intent topL = new Intent(Main_Menu.this, ShowAnimeList.class);
                topL.putExtra("GetParam1", "top");
                topL.putExtra("GetParam2", "anime");
                topL.putExtra("Nature", "top");
                startActivity(topL);
            }
        });

        seasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seasL = new Intent(Main_Menu.this, ShowAnimeList.class);
                seasL.putExtra("GetParam1", "2018");
                seasL.putExtra("GetParam2", "winter");
                seasL.putExtra("Nature", "seas");
                startActivity(seasL);
            }
        });

        schedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent schL = new Intent(Main_Menu.this, ShowAnimeList.class);
                schL.putExtra("GetParam1", "schedule");
                schL.putExtra("GetParam2", "monday");
                schL.putExtra("Nature", "sched");
                startActivity(schL);
            }
        });

        upcomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upL = new Intent(Main_Menu.this, ShowAnimeList.class);
                upL.putExtra("GetParam1", "season");
                upL.putExtra("GetParam2", "later");
                upL.putExtra("Nature", "upcom");
                startActivity(upL);
            }
        });
    }
}
