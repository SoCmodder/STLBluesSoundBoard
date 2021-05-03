package world.mitchmiller.bluessoundboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
        holder.bind(soundClipInfo, listener)
    }

    override fun getItemCount(): Int = clips.size

    fun setMusicClips(c: ArrayList<SoundClipInfo>) {
        clips.clear()
        clips.addAll(c)
        notifyDataSetChanged()
    }

    inner class SoundClipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.clip_title)
        private val image: ImageView = itemView.findViewById(R.id.clip_image)

        fun bind(info: SoundClipInfo, listener: OnSoundClipClickListener) {
            title.text = info.title
            itemView.setOnClickListener {
                listener.onSoundClipSelected(info)
                shakeImage(image)
            }
        }

        private fun shakeImage(imageView: ImageView) {
            val shake: Animation = AnimationUtils.loadAnimation(context, R.anim.shake)
            imageView.startAnimation(shake)
        }
    }

    interface OnSoundClipClickListener {
        fun onSoundClipSelected(item: SoundClipInfo)
    }
}