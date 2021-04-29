package world.mitchmiller.bluessoundboard

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import world.mitchmiller.bluessoundboard.adapter.SoundClipInfo
import world.mitchmiller.bluessoundboard.adapter.SoundClipRecyclerAdapter
import world.mitchmiller.bluessoundboard.databinding.ActivitySoundClipListBinding

class SoundClipListActivity: AppCompatActivity(),
    SoundClipRecyclerAdapter.OnSoundClipClickListener {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var binding: ActivitySoundClipListBinding
    private var lastPlayedClipId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_sound_clip_list
        )
        setSupportActionBar(binding.toolbar)
        setupRecyclerView()
    }

    private fun createSoundClipList(): ArrayList<SoundClipInfo> {
        val soundClips: ArrayList<SoundClipInfo> = arrayListOf()
        soundClips.add(SoundClipInfo("Power Play", R.raw.blues_power_play))
        soundClips.add(SoundClipInfo("Buffalo Head", R.raw.buffalo_head))
        soundClips.add(SoundClipInfo("GLORIA!", R.raw.gloria))
        soundClips.add(SoundClipInfo("GOAL!", R.raw.goal_horn))
        soundClips.add(SoundClipInfo("Lets Win A Game", R.raw.lets_go_win_a_game))
        soundClips.add(SoundClipInfo("Turbulence", R.raw.turbulence))
        soundClips.add(SoundClipInfo("We're Cummin'", R.raw.yeah_were_cummin))
        soundClips.add(SoundClipInfo("Blues Go Marching In", R.raw.blues_go_marching_in))
        soundClips.add(SoundClipInfo("Sad Chelsea Dagger", R.raw.sad_chelsea_dagger))

        return soundClips
    }

    private fun setupRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        val adapter = SoundClipRecyclerAdapter(this, createSoundClipList(), this)
        binding.recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun playSoundClip(clipId: Int) {
        if(lastPlayedClipId == clipId) {
            stopSoundClip()
            lastPlayedClipId = 0
            return
        }

        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            stopSoundClip()
        }

        mediaPlayer = MediaPlayer.create(this, clipId)
        mediaPlayer?.setOnCompletionListener {
            lastPlayedClipId = 0
        }
        mediaPlayer?.start()
        lastPlayedClipId = clipId
    }

    private fun stopSoundClip() {
        mediaPlayer?.stop()
    }

    override fun onSoundClipSelected(item: SoundClipInfo) {
        playSoundClip(item.resourceId)
    }
}