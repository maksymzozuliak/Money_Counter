package com.example.counter.activities.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.counter.Purchase
import com.example.counter.R
import com.example.counter.RcAdapter
import com.google.firebase.database.ktx.database
import com.example.counter.databinding.ActivityHistoryBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_history.*

class History : AppCompatActivity() {
    lateinit var binding: ActivityHistoryBinding
    lateinit var adapter: RcAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val database = Firebase.database("https://counter-8e3f9-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("purchase")

        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Purchase>()
                for (s in snapshot.children) {
                    val purchase = s.getValue(Purchase::class.java)
                    if (purchase != null)list.add(purchase)
                }
                adapter.submitList(list)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
        initRcView()
    }
    private fun initRcView() {
        adapter = RcAdapter()
        rcView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL ,false)
        rcView.adapter = adapter
    }
}