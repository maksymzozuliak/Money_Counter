package com.example.counter.activities.statistics

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidplot.pie.Segment
import com.androidplot.pie.SegmentFormatter
import com.example.counter.AppPreferences
import com.example.counter.Month
import com.example.counter.R
import kotlinx.android.synthetic.main.activity_statistics.*

class Statistics : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        current_month_text.text = "Spent in " + Month.currentMonth

        month_spent_text.text = AppPreferences.spent.toString()

        if ( AppPreferences.else_spent != 0f) {
            pieChart.addSegment(Segment("Else \n ${AppPreferences.else_spent}",
                AppPreferences.else_spent?:return), SegmentFormatter(Color.rgb(255,173,173)))}
        if ( AppPreferences.food_spent != 0f) {
            pieChart.addSegment(Segment("Food \n ${AppPreferences.food_spent}",
                AppPreferences.food_spent?:return), SegmentFormatter(Color.rgb(255,214,165)))}
        if ( AppPreferences.entertainment_spent != 0f) {
            pieChart.addSegment(Segment("Entertainment \n ${AppPreferences.entertainment_spent}",
                AppPreferences.entertainment_spent?:return), SegmentFormatter(Color.rgb(253,255,182)))}
        if ( AppPreferences.services_spent != 0f) {
            pieChart.addSegment(Segment("Services \n ${AppPreferences.services_spent}",
                AppPreferences.services_spent?:return), SegmentFormatter(Color.rgb(155,246,255)))}
        if ( AppPreferences.games_spent != 0f) {
            pieChart.addSegment(Segment("Games \n ${AppPreferences.games_spent}",
                AppPreferences.games_spent?:return), SegmentFormatter(Color.rgb(160,196,255)))}
        if ( AppPreferences.studying_spent != 0f) {
            pieChart.addSegment(Segment("Studying \n ${AppPreferences.studying_spent}",
                AppPreferences.studying_spent?:return), SegmentFormatter(Color.rgb(189,178,255)))}
        if ( AppPreferences.trading_spent != 0f) {
            pieChart.addSegment(Segment("Trading \n ${AppPreferences.trading_spent}",
                AppPreferences.trading_spent?:return), SegmentFormatter(Color.rgb(255,198,255)))}

        trading_spent_text.text = AppPreferences.trading_spent.toString()
        trading_income_text.text = AppPreferences.trading_income.toString()
        trading_earnings_text.text = ((AppPreferences.trading_income!! - AppPreferences.trading_spent!!)/ AppPreferences.trading_spent!!*100).toInt().toString() + "%"
    }
}