package com.ephirium.common.listener

fun <T> ((value: T) -> Any).convert() : DataConstListener<T>{
    return object : DataConstListener<T>{
        override fun onChange(value: T) {
            this@convert(value)
        }
    }
}

fun ((exception: Exception) -> Any).convert(): ErrorListener{
    return object : ErrorListener{
        override fun onError(exception: Exception) {
            this@convert(exception)
        }
    }
}
