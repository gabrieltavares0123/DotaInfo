package com.magrathea.core

class Logger(
    private val tag: String,
    private val isDebug: Boolean = true,
) {
    fun log(message: String) {
        if (!isDebug) {
            // Crashlytics here.
        } else {
            printLogD(tag, message)
        }
    }

    companion object Factory {
        fun buildDebug(tag: String) = Logger(tag, isDebug = true)
        fun buildRelease(tag: String) = Logger(tag, isDebug = false)
    }
}

fun printLogD(tag: String, message: String) {
    println("$tag: $message")
}