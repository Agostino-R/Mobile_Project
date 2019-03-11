package com.example.mobile_project.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mobile_project.Controller.ToWatchAdapter;
import com.example.mobile_project.Controller.ToWatchController;
import com.example.mobile_project.Model.AnimeInToWatchList;
import com.example.mobile_project.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class ToWatchActivity extends AppCompatActivity {
    List<Integer> mal_Id_Anime_To_Watch;
    ArrayList<AnimeInToWatchList> to_Watch_List;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ToWatchAdapter.OnItemClickListener listener_watch;
    Map<String, Integer> loadedValues;
    SharedPreferences sharedPreferences;
    ToWatchController controller;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.to_watch_list_layout);
        sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        recyclerView = (RecyclerView) findViewById(R.id.to_watch_recycler_view);
        mal_Id_Anime_To_Watch = new ArrayList<Integer>();
        to_Watch_List = new ArrayList<AnimeInToWatchList>();
        if(sharedPreferences!=null)
            loadListFromCache();
        mal_Id_Anime_To_Watch.add(5114);

        controller = new ToWatchController(this);
        controller.onCreate();

        for(int i : mal_Id_Anime_To_Watch)
        {
            controller.loadToWatchAnime("anime", Integer.toString(i));
        }
    }

    public void loadListFromCache()
    {
        loadedValues = (Map<String, Integer>) sharedPreferences.getAll();
        Set key = loadedValues.keySet();
        Iterator it = key.iterator();
        while (it.hasNext()){
            String cle = (String) it.next();
            int id = (Integer) loadedValues.get(cle);
            mal_Id_Anime_To_Watch.add(id);
        }
    }

    public void addToList(AnimeInToWatchList animeInToWatchList)
    {
        to_Watch_List.add(animeInToWatchList);
    }

    public void showToWatchList()
    {
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, true);
        ((GridLayoutManager) layoutManager).setReverseLayout(false);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new ToWatchAdapter(to_Watch_List, getToWatchListener(), context);
        recyclerView.setAdapter(mAdapter);
    }

    private ToWatchAdapter.OnItemClickListener getToWatchListener() {
        return new ToWatchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AnimeInToWatchList item) {
                Intent anime_desc_activity = new Intent(context, AnimeDescActivity.class);
                anime_desc_activity.putExtra("SelectedAnimeId", item.getId());
                context.startActivity(anime_desc_activity);
            }
        };
    }
}