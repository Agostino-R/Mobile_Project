package com.example.mobile_project.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.mobile_project.Controller.MainController;
import com.example.mobile_project.R;

public class Main_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button mButton = new Button(this);
        mButton = findViewById(R.id.launch_button);
        mButton.setEnabled(true);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start_activity = new Intent(Main_Menu.this, ShowAnimeList.class);
                startActivity(start_activity);
            }
        });
    }
}
