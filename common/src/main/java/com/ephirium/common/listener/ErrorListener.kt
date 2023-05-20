package com.ephirium.common.listener

import java.lang.Exception

interface ErrorListener {
    fun onError(exception: Exception)
}