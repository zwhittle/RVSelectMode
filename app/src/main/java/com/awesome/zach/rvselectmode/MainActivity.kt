package com.awesome.zach.rvselectmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SelectableViewHolder.OnItemSelectedListener {

    private lateinit var adapter: SelectableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selection_list.layoutManager = LinearLayoutManager(this)

        val selectableItems = generateItems()

        adapter = SelectableAdapter(this, selectableItems, true)
        selection_list.adapter = adapter
    }

    fun generateItems() : ArrayList<SelectableItem> {
        val selectableItems = ArrayList<SelectableItem>()
        selectableItems.add(SelectableItem("cem", "karaca", false))
        selectableItems.add(SelectableItem("sezen","aksu", false))
        selectableItems.add(SelectableItem("baris","manco", false))

        return selectableItems
    }

    override fun onItemSelected(item: SelectableItem) {
        val selectedItems = adapter.getSelectedItems()
        Snackbar.make(selection_list, "Selected item is ${item.name}. Total selected count is ${selectedItems.size}", Snackbar.LENGTH_LONG).show()
    }
}
