package com.englesoft.movieapp.presentation.details

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {

    private var exoPlayer: ExoPlayer? = null
    private val preferences = context.getSharedPreferences("video_prefs", Context.MODE_PRIVATE)

    // Save the last played position
    private fun saveLastPlayedPosition(position: Long) {
        preferences.edit().putLong("last_played_position", position).apply()
    }

    // Get the last played position
    private fun getLastPlayedPosition(): Long {
        return preferences.getLong("last_played_position", 0)
    }

    // Prepare the ExoPlayer with video URL
    fun preparePlayer(videoUrl: String): ExoPlayer {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(context).build()
        }

        // Set the media item to the player
        val mediaItem = MediaItem.fromUri(videoUrl)
        exoPlayer?.setMediaItem(mediaItem)

        // Start the video from the last played position if available
        val position = getLastPlayedPosition()
        exoPlayer?.seekTo(position)
        exoPlayer?.prepare()
        exoPlayer?.playWhenReady = true

        return exoPlayer!!
    }

    // Save the current position before stopping
    fun saveCurrentPosition() {
        exoPlayer?.currentPosition?.let { saveLastPlayedPosition(it) }
    }

    // Release the ExoPlayer when done
    override fun onCleared() {
        super.onCleared()
        exoPlayer?.release()
    }
}
