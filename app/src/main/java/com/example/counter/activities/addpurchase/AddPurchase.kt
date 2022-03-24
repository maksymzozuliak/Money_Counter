package com.example.counter.activities.addpurchase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.counter.AppPreferences
import com.example.counter.Purchase
import com.example.counter.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_purchase.*
import kotlinx.android.synthetic.main.activity_change_balance.*

class AddPurchase : AppCompatActivity() {
    var currentCategory = ""
    var card = false
    val category = arrayOf("Else","Food","Entertainment","Services","Games")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_purchase)
        val arrayAdapter = ArrayAdapter(this@AddPurchase,android.R.layout.simple_spinner_dropdown_item,category)
        spinner_category.adapter = arrayAdapter
        spinner_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currentCategory = category[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                currentCategory = "Else"
            }
        }

        card_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                card = true
            } else {
                card = false
            }
        }

        val database = Firebase.database("https://counter-8e3f9-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("purchase")

        add_button.setOnClickListener {
            try {
                myRef.child(myRef.push().key ?: "blabla").setValue(
                    Purchase(
                    name_field.text.toString(),
                    price_field.text.toString().toFloat(),
                    currentCategory,
                    card
                )
                )
                if (card) {
                    AppPreferences.balance =
                        AppPreferences.balance!! - price_field.text.toString().toFloat()
                }
                finish()
            } catch (e: NumberFormatException) {
                Toast.makeText(applicationContext, "Wrong records", Toast.LENGTH_SHORT).show()
            }
        }
    }
}