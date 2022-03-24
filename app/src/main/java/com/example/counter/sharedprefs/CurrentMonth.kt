package com.example.counter

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object Month {
    private var sharedPreferences: SharedPreferences? = null

    fun setup(context: Context) {
        sharedPreferences =
            context.getSharedPreferences("counter.month", Context.MODE_PRIVATE)
    }

    var currentMonth : String?
        get() = Key.CURRENT.getString()
        set(value) = Key.CURRENT.setString(value)



    private enum class Key {
        CURRENT;

        fun getString(): String? =
            if (sharedPreferences!!.contains(name)) sharedPreferences!!.getString(name, "") else null

        fun setString(value: String?) =
            value?.let { sharedPreferences!!.edit { putString(name, value) } } ?: remove()

        fun remove() = sharedPreferences!!.edit { remove(name) }
    }
}