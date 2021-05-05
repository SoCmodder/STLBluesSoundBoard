package world.mitchmiller.bluessoundboard

import android.content.Context
import android.view.KeyEvent
import android.widget.TextView
import androidx.appcompat.widget.SearchView

class EmptySubmitSearchView(context: Context) : SearchView(context) {
    lateinit var searchSrcTextView: SearchAutoComplete
    private var listener: OnQueryTextListener? = null

    override fun setOnQueryTextListener(listener: OnQueryTextListener?) {
        super.setOnQueryTextListener(listener)
        this.listener = listener
        searchSrcTextView = this.findViewById(androidx.appcompat.R.id.search_src_text)
        searchSrcTextView.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (listener != null) {
                    listener.onQueryTextChange(query.toString())
                }
                return true
            }

        })
    }
}