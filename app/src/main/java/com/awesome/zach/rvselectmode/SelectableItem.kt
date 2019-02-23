package com.awesome.zach.rvselectmode

class SelectableItem(var name: String, var surname: String, var isSelected: Boolean) {

    override fun equals(other: Any?): Boolean {
        if (other == null) return false

        val itemCompare = other as SelectableItem

        if (itemCompare.name == this.name) return true

        return false
    }
}

