package world.mitchmiller.bluessoundboard

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goal_horn.setOnClickListener(this)
        stop_button.setOnClickListener(this)
        play_gloria_button.setOnClickListener(this)
        power_play_button.setOnClickListener(this)
        placeholder.setOnClickListener(this)
        win_a_game_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.goal_horn -> playGoalHorn()
            R.id.play_gloria_button -> playGloria()
            R.id.stop_button -> stop()
            R.id.power_play_button -> powerPlay()
            R.id.placeholder -> buffaloHead()
            R.id.win_a_game_button -> winAGame()
        }
    }

    private fun winAGame() {
        mediaPlayer = MediaPlayer.create(this, R.raw.lets_go_win_a_game)
        setupPlayCompleteListener()
        mediaPlayer.start()
        stop_button.visibility = View.VISIBLE
    }

    private fun buffaloHead() {
        mediaPlayer = MediaPlayer.create(this, R.raw.buffalo_head)
        setupPlayCompleteListener()
        mediaPlayer.start()
        stop_button.visibility = View.VISIBLE
    }

    private fun powerPlay() {
        mediaPlayer = MediaPlayer.create(this, R.raw.blues_power_play)
        setupPlayCompleteListener()
        mediaPlayer.start()
        stop_button.visibility = View.VISIBLE
    }

    private fun playGloria() {
        mediaPlayer = MediaPlayer.create(this, R.raw.gloria)
        setupPlayCompleteListener()
        mediaPlayer.start()
        stop_button.visibility = View.VISIBLE
    }

    private fun playGoalHorn() {
        mediaPlayer = MediaPlayer.create(this, R.raw.goal_horn)
        setupPlayCompleteListener()
        mediaPlayer.start()
        stop_button.visibility = View.VISIBLE
    }

    private fun setupPlayCompleteListener() {
        mediaPlayer.setOnCompletionListener {
            stop_button.visibility = View.GONE
        }
    }

    private fun stop() {
        mediaPlayer.stop()
        stop_button.visibility = View.GONE
    }

}
