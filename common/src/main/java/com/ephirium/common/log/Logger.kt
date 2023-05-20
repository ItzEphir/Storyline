package com.ephirium.common.log

import android.util.Log

@JvmField
var defaultTag = "Debug"

@JvmField
var infoTag = "Info"

@JvmField
var warningTag = "Warning"

@JvmField
var errorTag = "Error"


@JvmOverloads
fun log(tag: String = defaultTag, message: String) = Log.d(tag, message)

@JvmOverloads
fun <T> log(tag: String = defaultTag, message: String, clazz: Class<T>?) =
    Log.d(tag, message + " " + clazz?.typeName)

infix fun Any.log(message: String) = log(defaultTag, message, this.javaClass)

@JvmOverloads
fun logError(tag: String = errorTag, message: String) = Log.e(tag, message)

@JvmOverloads
fun <T> logError(tag: String = errorTag, message: String, clazz: Class<T>?) =
    Log.e(tag, message + " " + clazz?.typeName)

infix fun Any.logError(message: String) = logError(defaultTag, message, this.javaClass)

@JvmOverloads
fun info(tag: String = infoTag, message: String) = Log.i(tag, message)

@JvmOverloads
fun <T> info(tag: String = infoTag, message: String, clazz: Class<T>?) =
    Log.i(tag, message + " " + clazz?.typeName)

infix fun Any.info(message: String) = info(infoTag, message, this.javaClass)

@JvmOverloads
fun warning(tag: String = warningTag, message: String) = Log.w(tag, message)

@JvmOverloads
fun <T> warning(tag: String = warningTag, message: String, clazz: Class<T>?) =
    Log.w(tag, message + " " + clazz?.typeName)

infix fun Any.warning(message: String) = warning(warningTag, message, this.javaClass)

@JvmOverloads
fun error(tag: String = errorTag, message: String) = Log.e(tag, message)

@JvmOverloads
fun <T> error(tag: String = errorTag, message: String, clazz: Class<T>?) =
    Log.e(tag, message + " " + clazz?.typeName)

infix fun Any.error(message: String) = error(errorTag, message, this.javaClass)