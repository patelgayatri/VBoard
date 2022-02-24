package com.techand.vboard.ui.videoplay

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import com.techand.vboard.R
import com.techand.vboard.network.youtubeKey
import com.techand.vboard.utils.ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoPlayActivity : AppCompatActivity() {
    private val args: VideoPlayActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)
        ID = args.video
        val youTubePlayerFragment =
            supportFragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerSupportFragmentX

        youTubePlayerFragment.initialize(youtubeKey, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                youTubePlayer: YouTubePlayer?,
                wasRestored: Boolean
            ) {
                youTubePlayer?.loadVideo(ID)
                youTubePlayer?.play()
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                youTubeInitializationResult: YouTubeInitializationResult?
            ) {
                val requestCode = 0

                if (youTubeInitializationResult?.isUserRecoverableError == true) {
                    youTubeInitializationResult.getErrorDialog(this@VideoPlayActivity, requestCode).show()
                } else {
                    val errorMessage =
                        "There was an error initializing the YoutubePlayer ($youTubeInitializationResult)"
                    Toast.makeText(this@VideoPlayActivity, errorMessage, Toast.LENGTH_LONG).show()
                }
            }

        })

    }
}