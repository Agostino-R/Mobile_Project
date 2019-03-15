package com.example.mobile_project.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobile_project.Controller.Description_View_Controller;
import com.example.mobile_project.Model.Api_Desc_Struct_Resp;
import com.example.mobile_project.R;
import com.squareup.picasso.Picasso;

public class AnimeDescActivity extends AppCompatActivity {
    private Description_View_Controller controller;
    private Button addButton;
    private SharedPreferences sharedPreferences;
    private ImageView image;
    private TextView title;
    private TextView note;
    private int mal_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_desc_layout);

        image = findViewById(R.id.image_for_desc);
        title = findViewById(R.id.title_desc);
        note = findViewById(R.id.desc_note);
        addButton = (Button) findViewById(R.id.addToToWatchList);

        sharedPreferences = this.getSharedPreferences("user_to_watch_anime", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        mal_id = (Integer) intent.getSerializableExtra("SelectedAnimeId");

        controller = new Description_View_Controller(this);
        controller.onCreate();
        controller.loadAnimeDescription("anime", Integer.toString(mal_id));

        setButtonBehavior(controller.getIsInWatchList());
    }


    public void showAnimeDesc(Api_Desc_Struct_Resp api_Desc_Struct_Resp) {
        //Infinité de settext
        Picasso.get()
                .load(api_Desc_Struct_Resp.getImage_url())
                .resize(160, 230)
                .into(image);
        title.setText(api_Desc_Struct_Resp.getTitle());
        note.setText(String.valueOf(api_Desc_Struct_Resp.getScore()));
    }

    public int getMal_id() {
        return mal_id;
    }

    public void setButtonBehavior(Boolean isInWatchList)
    {
        if(isInWatchList) {
            addButton.setText("-      Remove");
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    controller.removeItemFromList(mal_id);
                }
            });
        }
        else {
            addButton.setText("+      Add");
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    controller.addItemIntoList(mal_id);
                }
            });
        }
    }
}
