package com.awesome.zach.rvselectmode

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SelectableAdapter(
    private val listener: SelectableViewHolder.OnItemSelectedListener,
    private val values: ArrayList<SelectableItem>,
    private val isMultiSelectionEnabled: Boolean) : RecyclerView.Adapter<SelectableViewHolder>(), SelectableViewHolder.OnItemSelectedListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectableViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.checked_item, parent, false)
        return SelectableViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: SelectableViewHolder, position: Int) {
        val selectableItem = values[position]
        holder.bind(selectableItem)

        if (isMultiSelectionEnabled) {
            val value = TypedValue()
            holder.textView.context.theme.resolveAttribute(android.R.attr.listChoiceIndicatorMultiple, value, true)
            val checkMarkDrawableResId = value.resourceId
            holder.textView.setCheckMarkDrawable(checkMarkDrawableResId)
        } else {
            val value = TypedValue()
            holder.textView.context.theme.resolveAttribute(android.R.attr.listChoiceIndicatorSingle, value, true)
            val checkMarkDrawableResId = value.resourceId
            holder.textView.setCheckMarkDrawable(checkMarkDrawableResId)
        }

        holder.mItem = selectableItem
        holder.setChecked(holder.mItem.isSelected)
    }

    override fun getItemCount() = values.size

    fun getSelectedItems() : List<SelectableItem> {
        val selectedItems = ArrayList<SelectableItem>()

        values.forEach {
            if (it.isSelected) selectedItems.add(it)
        }

        return selectedItems.toList()
    }

    override fun getItemViewType(position: Int): Int {
        return if (isMultiSelectionEnabled) {
            SelectableViewHolder.MULTI_SELECTION
        } else {
            SelectableViewHolder.SINGLE_SELECTION
        }
    }

    override fun onItemSelected(item: SelectableItem) {
        if (!isMultiSelectionEnabled) {
            values.forEach{
                if (it != item && it.isSelected) {
                    it.isSelected = false
                } else if (it == item && it.isSelected) {
                    it.isSelected = true
                }
            }
            notifyDataSetChanged()
        }
        listener.onItemSelected(item)
    }
}