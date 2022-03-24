package com.example.counter

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

object AppPreferences {
    private var sharedPreferences: SharedPreferences? = null

    fun setup(context: Context) {
        sharedPreferences =
            context.getSharedPreferences("counter.sharedprefs", MODE_PRIVATE)
    }

    var balance: Float?
        get() = Key.BALANCE.getFloat()
        set(value) = Key.BALANCE.setFloat(value)

    private enum class Key {
        BALANCE;

        fun getFloat(): Float? =
            if (sharedPreferences!!.contains(name)) sharedPreferences!!.getFloat(name, 0f) else null

        fun setFloat(value: Float?) =
            value?.let { sharedPreferences!!.edit { putFloat(name, value) } } ?: remove()

        fun remove() = sharedPreferences!!.edit { remove(name) }
    }
}