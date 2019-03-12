package com.example.mobile_project.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobile_project.Controller.Description_View_Controller;
import com.example.mobile_project.Model.Api_Desc_Struct_Resp;
import com.example.mobile_project.R;

public class AnimeDescActivity extends AppCompatActivity {
    private TextView test;
    private Description_View_Controller controller;
    private Button addButton;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int mal_id;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_desc_layout);

        addButton = (Button) findViewById(R.id.addToToWatchList);

        sharedPreferences = this.getSharedPreferences("user_to_watch_anime", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        mal_id = (Integer)intent.getSerializableExtra("SelectedAnimeId");

        controller = new Description_View_Controller(this);
        controller.onCreate();
        controller.loadAnimeDescription("anime", Integer.toString(mal_id));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.addItemIntoList(mal_id);
            }
        });
    }


    public void showAnimeDesc(Api_Desc_Struct_Resp api_Desc_Struct_Resp)
    {
        //Infinité de settext
    }
}
