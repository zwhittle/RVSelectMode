package com.awesome.zach.rvselectmode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_basic.*
import kotlinx.android.synthetic.main.content_basic.*

class MainActivity : AppCompatActivity(), SelectableViewHolder.OnItemSelectedListener {

    private var actionModeEnabled = false

    private lateinit var adapter: SelectableAdapter
    private var mActionModeCallback = PrimaryActionModeCallback()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
        setSupportActionBar(toolbar)

        selection_list.layoutManager = LinearLayoutManager(this)

        val selectableItems = generateItems()

        adapter = SelectableAdapter(this, selectableItems, true)
        selection_list.adapter = adapter

//        fab.setOnClickListener {
//            if (actionModeEnabled) {
//                finishActionMode()
//            } else {
//                startActionMode()
//            }
//        }
    }

    private fun startActionMode() {
        mActionModeCallback.startActionMode(
            view = toolbar,
            menuResId = R.menu.menu_action_mode,
            title = "Title",
            subtitle = "Subtitle")
        actionModeEnabled = true
    }

    private fun finishActionMode() {
        mActionModeCallback.finishActionMode()
        actionModeEnabled = false
    }

    private fun generateItems(): ArrayList<SelectableItem> {
        val selectableItems = ArrayList<SelectableItem>()
        selectableItems.add(SelectableItem("cem", "karaca", false))
        selectableItems.add(SelectableItem("sezen", "aksu", false))
        selectableItems.add(SelectableItem("baris", "manco", false))

        return selectableItems
    }

    override fun onItemSelected(item: SelectableItem) {
        val selectedItems = adapter.getSelectedItems()
        if (selectedItems.isNotEmpty()) {
            if (!actionModeEnabled) {
                startActionMode()
            }
        } else if (selectedItems.isEmpty()) {
            if (actionModeEnabled) {
                finishActionMode()
            }
        }

        Snackbar.make(
            selection_list,
            "Selected item is ${item.name}. Total selected count is ${selectedItems.size}",
            Snackbar.LENGTH_LONG
        ).show()
    }
}
