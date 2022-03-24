package com.example.counter.activities.changebalance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.counter.AppPreferences
import com.example.counter.R
import kotlinx.android.synthetic.main.activity_change_balance.*

class ChangeBalance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_balance)

        edit_balance_button.setOnClickListener {
            try {
                AppPreferences.balance = edittext_balance.text.toString().toFloat()
                finish()
            } catch (e: NumberFormatException) {
                Toast.makeText(applicationContext, "Wrong records", Toast.LENGTH_SHORT).show()
            }
        }
    }
}