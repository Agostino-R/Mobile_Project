package com.example.mobile_project.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobile_project.R;

public class Anime_Desc extends AppCompatActivity {
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_desc);

        test = (TextView) findViewById(R.id.test_id);
        int anime_id;
        Intent i = getIntent();
        anime_id = (int)i.getSerializableExtra("SelectedAnimeId");
        test.setText("ID : " + anime_id);

    }
}
