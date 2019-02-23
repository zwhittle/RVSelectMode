package com.awesome.zach.rvselectmode

import android.graphics.Color
import android.view.View
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView

class SelectableViewHolder(var view: View, var listener: OnItemSelectedListener) : RecyclerView.ViewHolder(view) {

    companion object {
        const val SINGLE_SELECTION = 1
        const val MULTI_SELECTION = 2
    }

    lateinit var mItem: SelectableItem
    var textView: CheckedTextView

    init {
        textView = view.findViewById(R.id.checked_text_item)
    }

    fun bind(item: SelectableItem) {
        textView = view.findViewById(R.id.checked_text_item)
        textView.text = item.name
        textView.setOnClickListener {view ->
            if (mItem.isSelected && itemViewType == MULTI_SELECTION) {
                setChecked(false)
            } else {
                setChecked(true)
            }

            listener.onItemSelected(mItem)
        }

        mItem = item
    }

    fun setChecked(b: Boolean) {
        if (b) {
            textView.setBackgroundColor(Color.LTGRAY)
        } else {
            textView.background = null
        }

        mItem.isSelected = b
        textView.isChecked = b
    }

    interface OnItemSelectedListener {
        fun onItemSelected(item: SelectableItem)
    }


}