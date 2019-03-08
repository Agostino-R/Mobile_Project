package com.example.mobile_project.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mobile_project.Controller.Description_View_Controller;
import com.example.mobile_project.Model.Api_Desc_Struct_Resp;
import com.example.mobile_project.R;

public class AnimeDescActivity extends AppCompatActivity {
    TextView test;
    Description_View_Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int mal_id;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_desc_layout);

        Intent intent = getIntent();
        mal_id = (Integer)intent.getSerializableExtra("SelectedAnimeId");

        controller.onCreate();
        controller.loadAnimeDescription("anime", Integer.toString(mal_id));

    }

    public void showAnimeDesc(Api_Desc_Struct_Resp api_Desc_Struct_Resp)
    {
        //Infinité de settext
    }
}
