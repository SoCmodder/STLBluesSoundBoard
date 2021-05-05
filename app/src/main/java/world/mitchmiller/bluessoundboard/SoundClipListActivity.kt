package world.mitchmiller.bluessoundboard

import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import world.mitchmiller.bluessoundboard.adapter.SoundClipInfo
import world.mitchmiller.bluessoundboard.adapter.SoundClipRecyclerAdapter
import world.mitchmiller.bluessoundboard.databinding.ActivitySoundClipListBinding
import java.util.*
import kotlin.collections.ArrayList

class SoundClipListActivity : AppCompatActivity(),
    SoundClipRecyclerAdapter.OnSoundClipClickListener, SearchView.OnQueryTextListener {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var binding: ActivitySoundClipListBinding
    private var lastPlayedClipId: Int = 0
    private lateinit var recyclerAdapter: SoundClipRecyclerAdapter

    private val allSoundClips: ArrayList<SoundClipInfo> = createSoundClipList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_sound_clip_list
        )
        setSupportActionBar(binding.toolbar)
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_sound_clip_list, menu)

        val searchViewItem: MenuItem = menu.findItem(R.id.search_bar)
        val searchView: EmptySubmitSearchView = searchViewItem.actionView as EmptySubmitSearchView

        // Disable searchview for now until I can get it working
        //searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    private fun filterClips(query: String): ArrayList<SoundClipInfo> {
        val filteredList: ArrayList<SoundClipInfo> = arrayListOf()
        val lowerCaseQuery: String = query.toLowerCase(Locale.getDefault()) ?: ""

        if (lowerCaseQuery.isNotEmpty()) {
            for (s in allSoundClips) {
                val title = s.title.toLowerCase(Locale.getDefault())
                if (title.contains(lowerCaseQuery)) {
                    filteredList.add(s)
                }
            }
            return filteredList
        }
        return allSoundClips
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
        soundClips.add(SoundClipInfo("Holy Jumpin'", R.raw.holy_jumpin))
        soundClips.add(SoundClipInfo("Do I Look Nervous?", R.raw.do_i_look_nervous))

        return soundClips
    }

    private fun setupRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        recyclerAdapter = SoundClipRecyclerAdapter(this, allSoundClips, this)
        binding.recyclerview.adapter = recyclerAdapter
        recyclerAdapter.notifyDataSetChanged()
    }

    private fun playSoundClip(clipId: Int) {
        if (lastPlayedClipId == clipId) {
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

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String): Boolean {
        if (query.isEmpty()) {
            recyclerAdapter.setMusicClips(allSoundClips)
            return false
        }

        val fClips = filterClips(query)
        if (fClips.size > 0) {
            recyclerAdapter.setMusicClips(fClips)
        }
        return true
    }
}