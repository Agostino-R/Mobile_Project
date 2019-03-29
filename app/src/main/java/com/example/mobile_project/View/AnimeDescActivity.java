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
    private Button synopsisB;
    private Button backB;
    private Button moreInfo;
    private SharedPreferences sharedPreferences;
    private ImageView image;
    private TextView title;
    private TextView note;
    private TextView popularity;
    private TextView synopsis;
    private TextView back_text;
    private TextView source;
    private TextView sourceStatic;
    private TextView trailer;
    private TextView trailerStatic;
    private TextView rank;
    private TextView status;
    private TextView episodes;
    private int mal_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anime_desc_layout);

        image = findViewById(R.id.image_for_desc);
        title = findViewById(R.id.title_desc);
        note = findViewById(R.id.desc_note);
        popularity = findViewById(R.id.desc_popularity);
        synopsis = findViewById(R.id.synopsis);
        back_text = findViewById(R.id.background_text);
        addButton = (Button) findViewById(R.id.addToToWatchList);
        synopsisB = findViewById(R.id.synopsis_button);
        backB = findViewById(R.id.background_button);
        moreInfo = findViewById(R.id.supp_info);
        source = findViewById(R.id.souce_url);
        trailer = findViewById(R.id.trailer_url);
        sourceStatic = findViewById(R.id.src_pres);
        trailerStatic = findViewById(R.id.tr_pres);
        rank = findViewById(R.id.rank);
        status = findViewById(R.id.status);
        episodes = findViewById(R.id.episodes);

        sharedPreferences = this.getSharedPreferences("user_to_watch_anime", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        mal_id = (Integer) intent.getSerializableExtra("SelectedAnimeId");

        controller = new Description_View_Controller(this);
        controller.onCreate();
        controller.loadAnimeDescription("anime", Integer.toString(mal_id));

        setButtonBehavior(controller.getIsInWatchList());

        synopsis.setVisibility(View.GONE);
        back_text.setVisibility(View.GONE);
        source.setVisibility(View.GONE);
        trailer.setVisibility(View.GONE);
        sourceStatic.setVisibility(View.GONE);
        trailerStatic.setVisibility(View.GONE);

        synopsisB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(synopsis.getVisibility() == View.GONE)
                {
                    synopsis.setVisibility(View.VISIBLE);
                }
                else
                {
                    synopsis.setVisibility(View.GONE);
                }

            }
        });

        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(back_text.getVisibility() == View.GONE)
                {
                    back_text.setVisibility(View.VISIBLE);
                }
                else
                {
                    back_text.setVisibility(View.GONE);
                }
            }
        });

        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(source.getVisibility() == View.GONE)
                {
                    source.setVisibility(View.VISIBLE);
                    trailer.setVisibility(View.VISIBLE);
                    trailerStatic.setVisibility(View.VISIBLE);
                    sourceStatic.setVisibility(View.VISIBLE);
                }
                else
                {
                    source.setVisibility(View.GONE);
                    trailer.setVisibility(View.GONE);
                    sourceStatic.setVisibility(View.GONE);
                    trailerStatic.setVisibility(View.GONE);
                }
            }
        });
    }


    public void showAnimeDesc(Api_Desc_Struct_Resp api_Desc_Struct_Resp) {
        Picasso.get()
                .load(api_Desc_Struct_Resp.getImage_url())
                .resize(160, 230)
                .into(image);
        title.setText(api_Desc_Struct_Resp.getTitle());
        note.setText(String.valueOf(api_Desc_Struct_Resp.getScore()));
        synopsis.setText("Synopsis:\n" + api_Desc_Struct_Resp.getSynopsis());
        if(api_Desc_Struct_Resp.getBackground()!=null)
            back_text.setText("Background:\n" + api_Desc_Struct_Resp.getBackground());
        popularity.setText(String.valueOf(api_Desc_Struct_Resp.getPopularity()));
        source.setText(api_Desc_Struct_Resp.getUrl());
        trailer.setText(api_Desc_Struct_Resp.getTrailer_url());
        rank.setText(String.valueOf(api_Desc_Struct_Resp.getRank()));
        status.setText(api_Desc_Struct_Resp.getStatus());
        episodes.setText("Nombre d'épisodes : " + String.valueOf(api_Desc_Struct_Resp.getEpisodes()));
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
