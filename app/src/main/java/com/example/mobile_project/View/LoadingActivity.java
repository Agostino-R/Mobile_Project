package com.example.mobile_project.View;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.mobile_project.R;

public class LoadingActivity extends AppCompatActivity {

    private int activity_to_launch;
    private boolean execute;
    private ImageView animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        execute = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);
        animation = findViewById(R.id.img_goku_anim);

        animation.setBackgroundResource(R.drawable.goku_anim);
        final AnimationDrawable slashAnim1 = (AnimationDrawable) animation.getBackground();
        slashAnim1.setOneShot(false);
        AlphaAnimation GOKU = new AlphaAnimation(1.0f,1.0f);

        activity_to_launch = getIntent().getIntExtra("activity_to_launch", 0);

        GOKU.setDuration(1900);
        GOKU.setFillAfter(true);

        GOKU.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                slashAnim1.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                slashAnim1.stop();
                Intent i = new Intent();
                switch (activity_to_launch)
                {
                    case(1):
                        i = new Intent(LoadingActivity.this, TopActivity.class);
                        break;

                    case(2):
                        i = new Intent(LoadingActivity.this, SeasonActivity.class);
                        break;

                    case(3):
                        i = new Intent(LoadingActivity.this, SchedActivity.class);
                        break;

                    case(4):
                        i = new Intent(LoadingActivity.this, UpcomingActivity.class);
                        break;

                    case(5):
                        i = new Intent(LoadingActivity.this, SearchActivity.class);
                        break;

                    case(6):
                        i = new Intent(LoadingActivity.this, ToWatchActivity.class);
                        break;

                    default:
                        Log.i("ANIM ERROR", "NE RECUPERE RIEN");
                }
                execute = false;
                startActivity(i);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation.startAnimation(GOKU);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!execute)
        {
            Intent i = new Intent(LoadingActivity.this, MainMenuActivity.class);
            startActivity(i);
        }
    }
}
