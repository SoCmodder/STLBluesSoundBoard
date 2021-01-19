package world.mitchmiller.bluessoundboard

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goal_horn.setOnClickListener(this)
        stop_button.setOnClickListener(this)
        play_gloria_button.setOnClickListener(this)
        power_play_button.setOnClickListener(this)
        buffalo_head_button.setOnClickListener(this)
        win_a_game_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.goal_horn -> playGoalHorn()
            R.id.play_gloria_button -> playGloria()
            R.id.stop_button -> stop()
            R.id.power_play_button -> powerPlay()
            R.id.buffalo_head_button -> buffaloHead()
            R.id.win_a_game_button -> winAGame()
        }
    }

    private fun winAGame() {
        cancelIfPlaying()
        mediaPlayer = MediaPlayer.create(this, R.raw.lets_go_win_a_game)
        setupPlayCompleteListener()
        mediaPlayer?.start()
        stop_button.visibility = View.VISIBLE
    }

    private fun buffaloHead() {
        cancelIfPlaying()
        mediaPlayer = MediaPlayer.create(this, R.raw.buffalo_head)
        setupPlayCompleteListener()
        mediaPlayer?.start()
        stop_button.visibility = View.VISIBLE
    }

    private fun powerPlay() {
        cancelIfPlaying()
        mediaPlayer = MediaPlayer.create(this, R.raw.blues_power_play)
        setupPlayCompleteListener()
        mediaPlayer?.start()
        stop_button.visibility = View.VISIBLE
    }

    private fun playGloria() {
        cancelIfPlaying()
        mediaPlayer = MediaPlayer.create(this, R.raw.gloria)
        setupPlayCompleteListener()
        mediaPlayer?.start()
        stop_button.visibility = View.VISIBLE
    }

    private fun playGoalHorn() {
        cancelIfPlaying()
        mediaPlayer = MediaPlayer.create(this, R.raw.goal_horn)
        setupPlayCompleteListener()
        mediaPlayer?.start()
        stop_button.visibility = View.VISIBLE
    }

    // Listener for hiding button after a sound completes
    private fun setupPlayCompleteListener() {
        mediaPlayer?.setOnCompletionListener {
            stop_button.visibility = View.GONE
        }
    }

    // If a sound is already playing, then stop the media player
    private fun cancelIfPlaying() {
        mediaPlayer?.stop()
    }

    // Stop the media player and hide the stop button
    private fun stop() {
        mediaPlayer?.stop()
        stop_button.visibility = View.GONE
    }

}
