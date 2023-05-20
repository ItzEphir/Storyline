package com.ephirium.common.listener

interface DataConstListener<T> {
    fun onChange(value: T)
}