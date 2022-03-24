package com.example.counter

object SetCurrentMonth {
    fun setMonth(n: Int) {
        when (n) {
            1 -> Month.currentMonth = "January"
            2 -> Month.currentMonth = "February"
            3 -> Month.currentMonth = "March"
            4 -> Month.currentMonth = "April"
            5 -> Month.currentMonth = "May"
            6 -> Month.currentMonth = "June"
            7 -> Month.currentMonth = "July"
            8 -> Month.currentMonth = "August"
            9 -> Month.currentMonth = "September"
            10 -> Month.currentMonth ="October"
            11 -> Month.currentMonth ="November"
            12 -> Month.currentMonth ="December"
            else -> Month.currentMonth = ""
        }
    }
}