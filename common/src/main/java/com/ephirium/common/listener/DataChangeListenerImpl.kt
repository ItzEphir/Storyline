package com.ephirium.common.listener

class DataChangeListenerImpl<T>(override var data: MutableList<T>) : DataChangeListener<T> {
    override fun onAdd(value: T) {
        data.add(value)
    }
}