package com.example.adminpanel

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CellDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cell_details)

        val cell = intent.getParcelableExtra<Cell>("cell")

        val tvId = findViewById<TextView>(R.id.tv_id)
        val tvSize = findViewById<TextView>(R.id.tv_size)
        val tvStatus = findViewById<TextView>(R.id.tv_status)
        val tvDatetime = findViewById<TextView>(R.id.tv_datetime)

        tvId.text = cell?.id ?: "N/A"
        tvSize.text = cell?.size.toString() ?: "N/A"
        tvStatus.text = cell?.status ?: "N/A"
        tvDatetime.text = cell?.datetime ?: "N/A"
    }
}
