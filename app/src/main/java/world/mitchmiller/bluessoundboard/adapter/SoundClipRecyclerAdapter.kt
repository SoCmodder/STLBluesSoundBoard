package world.mitchmiller.bluessoundboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.sound_list_item.view.*
import world.mitchmiller.bluessoundboard.R

class SoundClipRecyclerAdapter(
    private val context: Context,
    private val clips: ArrayList<SoundClipInfo>,
    private val listener: OnSoundClipClickListener
) :
    RecyclerView.Adapter<SoundClipRecyclerAdapter.SoundClipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundClipViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.sound_list_item, parent, false)
        return SoundClipViewHolder(v)
    }

    override fun onBindViewHolder(holder: SoundClipViewHolder, position: Int) {
        val soundClipInfo = clips[position]
        holder.bind(soundClipInfo)
        holder.itemView.setOnClickListener {
            listener.onSoundClipSelected(soundClipInfo)
        }
    }

    override fun getItemCount(): Int = clips.size

    inner class SoundClipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.clip_title)
        private val image: ImageView = itemView.findViewById(R.id.clip_image)

        fun bind(info: SoundClipInfo) {
            title.text = info.title
        }
    }

    interface OnSoundClipClickListener {
        fun onSoundClipSelected(item: SoundClipInfo)
    }
}