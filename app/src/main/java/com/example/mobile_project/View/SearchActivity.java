package com.example.mobile_project.View;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.mobile_project.Controller.SchedController;
import com.example.mobile_project.Controller.SearchAdapter;
import com.example.mobile_project.Controller.SearchController;
import com.example.mobile_project.Controller.TopAdapter;
import com.example.mobile_project.Model.AnimeInSearchList;
import com.example.mobile_project.Model.AnimeInTopList;
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
    private List<AnimeInTopList> mAnimeInSearchLists;
    private int page=1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        recyclerView = (RecyclerView) findViewById(R.id.seas_recycler_view);

        searchContent = findViewById(R.id.search_content);

        controller = new SearchController(this);
        controller.onCreate();
        controller.loadSearchResults("a", "b", "c");

        searchContent.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                controller.loadSearchResults("a", "b", "c");
                searchContent.requestFocus();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    public void showSearchResults(List<AnimeInSearchList> RespAnimeInSearchRes)
    {
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, true);
        ((GridLayoutManager) layoutManager).setReverseLayout(false);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new SearchAdapter(RespAnimeInSearchRes, listener_search ,getScrollListener(), context);
        recyclerView.setAdapter(mAdapter);
    }

    private SearchAdapter.OnBottomReachedListener getScrollListener() {
        return new SearchAdapter.OnBottomReachedListener() {
            public void onBottomReached(int position) {
                page++;
                controller.getFollowingSearchRes("top", "anime", Integer.toString(page));
            }
        };
    }

    public void setList(List<AnimeInTopList> mAnimeInTopList)
    {
        this.mAnimeInSearchLists = mAnimeInTopList;
    }

    public void addToList(List<AnimeInTopList> mAnimeInTopList)
    {
        for(AnimeInTopList curr : mAnimeInTopList)
        {
            this.mAnimeInSearchLists.add(curr);
        }
        mAdapter.notifyItemInserted(this.mAnimeInSearchLists.size() - 1);
    }
}
