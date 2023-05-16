package com.example.cooked_app.Activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cooked_app.Model.Course_Model;
import com.example.cooked_app.R;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class YouTubeActivity extends AppCompatActivity {
    private YouTubePlayerView YTPlayerView;
//    private boolean isFullScreen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_player);
        Course_Model course = (Course_Model) getIntent().getSerializableExtra("Course");
        YTPlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(YTPlayerView);

        YTPlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(course.getYouTubeID(), 0);
            }

//            @Override
//            public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState state) {
//                super.onStateChange(youTubePlayer, state);
//                if (state == PlayerConstants.PlayerState.PLAYING) {
//                    isFullScreen = false;
//                }
//            }
        });
    }


}
