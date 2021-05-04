package world.mitchmiller.bluessoundboard

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SearchView

class EmptySubmitSearchView(context: Context, attr: AttributeSet?, defStyleAttr: Int = 0) : SearchView(context, attr, defStyleAttr) {
    lateinit var searchSrcTextView: SearchAutoComplete
    lateinit var listener: OnQueryTextListener

    override fun setOnQueryTextListener(listener: OnQueryTextListener) {
        super.setOnQueryTextListener(listener)
        this.listener = listener
        searchSrcTextView = this.findViewById(androidx.appcompat.R.id.search_src_text)
        searchSrcTextView.setOnEditorActionListener { v, actionId, event ->
            listener.onQueryTextSubmit(query.toString())
            true
        }
    }
}