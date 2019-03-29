package com.example.mobile_project.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobile_project.SearchAdapter;
import com.example.mobile_project.Controller.SearchController;
import com.example.mobile_project.Model.AnimeInSearchList;
import com.example.mobile_project.R;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    private SearchController controller;
    private SearchAdapter.OnItemClickListener listener_search;
    private EditText searchContent;
    private List<AnimeInSearchList> mAnimeInSearchLists;
    private int page=1;
    private String search_field;
    private Button launchSearch;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        recyclerView = (RecyclerView) findViewById(R.id.search_recycler_view);

        searchContent = findViewById(R.id.search_content);
        launchSearch = findViewById(R.id.launch_search_button);

        this.context = this;

        controller = new SearchController(this);
        controller.onCreate();

        searchContent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_ENTER:
                            Editable s = searchContent.getText();
                            controller.loadSearchResults("search", "anime", parseString(s.toString()));
                            findViewById(R.id.searchL).requestFocus();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        launchSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable s = searchContent.getText();
                controller.loadSearchResults("search", "anime", parseString(s.toString()));
                findViewById(R.id.searchL).requestFocus();
            }
        });
    }

    public String parseString(String s)
    {
        search_field = s;
        String parsed=s;
        parsed.replace(" ", "%20");
        parsed = "?q=" + parsed + "&page=" + Integer.toString(page);
        Log.i("Search ", "################################" + parsed);
        return parsed;
    }

    public void showSearchResults(List<AnimeInSearchList> RespAnimeInSearchRes)
    {
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, true);
        ((GridLayoutManager) layoutManager).setReverseLayout(false);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new SearchAdapter(RespAnimeInSearchRes, getSearchListener() ,getScrollListener(), context);
        recyclerView.setAdapter(mAdapter);
    }

    private SearchAdapter.OnItemClickListener getSearchListener() {
        return new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AnimeInSearchList item) {
                Intent anime_desc_activity = new Intent(context, AnimeDescActivity.class);
                anime_desc_activity.putExtra("SelectedAnimeId", item.getMal_id());
                context.startActivity(anime_desc_activity);
            }
        };
    }

    private SearchAdapter.OnBottomReachedListener getScrollListener() {
        return new SearchAdapter.OnBottomReachedListener() {
            public void onBottomReached(int position) {
                page++;
                controller.getFollowingSearchRes("search", "anime", parseString(search_field));
            }
        };
    }

    public void setList(List<AnimeInSearchList> mAnimeInSearchList)
    {
        this.mAnimeInSearchLists = mAnimeInSearchList;
    }

    public void addToList(List<AnimeInSearchList> mAnimeInSearchList)
    {
        for(AnimeInSearchList curr : mAnimeInSearchList)
        {
            this.mAnimeInSearchLists.add(curr);
        }
        mAdapter.notifyItemInserted(this.mAnimeInSearchLists.size() - 1);
    }
}
