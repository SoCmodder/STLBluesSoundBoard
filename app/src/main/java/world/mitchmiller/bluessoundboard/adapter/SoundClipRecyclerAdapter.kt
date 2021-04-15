package world.mitchmiller.bluessoundboard.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SoundClipRecyclerAdapter(val context: Context) : RecyclerView.Adapter<SoundClipRecyclerAdapter.SoundClipViewHolder>() {

    private val clips : ArrayList<SoundClipInfo> = arrayListOf()

    inner class SoundClipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundClipViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SoundClipViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}