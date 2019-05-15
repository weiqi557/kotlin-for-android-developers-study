package com.weiqi.android.weather.domain.commands

interface Command<out T> {
    fun execute(): T
}