package com.ephirium.common.listener

interface DataChangeListener<T>: DataConstListener<List<T>> {

    var data: MutableList<T>

    override fun onChange(value: List<T>) {
        data = value.toMutableList()
    }

    fun onAdd(value: T)
}