package com.example.adminpanel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class RequestsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results)

        val result = intent.getStringExtra("result")

        val tvRequest = findViewById<TextView>(R.id.tv_result)
        tvRequest.text = result
    }
}
