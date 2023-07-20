package com.example.adminpanel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.adminpanel.R

class CellDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cell_details)

        val cell = intent.getParcelableExtra<Cell>("cell")

        val tvId: TextView = findViewById(R.id.tv_id)
        val tvSize: TextView = findViewById(R.id.tv_size)
        val tvStatus: TextView = findViewById(R.id.tv_status)
        val tvDatetime: TextView = findViewById(R.id.tv_datetime)

        val btnOpen: Button = findViewById(R.id.btn_open)
        val btnChangeStatus: Button = findViewById(R.id.btn_change_status)

        cell?.let {
            tvId.text = it.id
            tvSize.text = it.size.toString()
            tvStatus.text = it.status
            tvDatetime.text = it.datetime
        }
    }
}

