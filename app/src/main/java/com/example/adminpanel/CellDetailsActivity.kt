package com.example.adminpanel

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class CellDetailsActivity : AppCompatActivity() {

    private lateinit var cell: Cell

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cell_details)

        cell = intent.getParcelableExtra("cell")!!

        val tvId: TextView = findViewById(R.id.tv_id)
        val tvSize: TextView = findViewById(R.id.tv_size)
        val tvStatus: TextView = findViewById(R.id.tv_status)
        val tvDatetime: TextView = findViewById(R.id.tv_datetime)

        val btnOpen: Button = findViewById(R.id.btn_open)
        val btnChangeStatus: Button = findViewById(R.id.btn_change_status)

        tvId.text = cell.id
        tvSize.text = cell.size.toString()
        tvStatus.text = cell.status
        tvDatetime.text = cell.datetime

        btnChangeStatus.setOnClickListener {
            showChangeStatusDialog()
        }
    }

    private fun showChangeStatusDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_change_status, null)
        val radioGroupStatus = dialogView.findViewById<RadioGroup>(R.id.radioGroupStatus)

        when (cell.status) {
            "free" -> radioGroupStatus.check(R.id.radioButtonFree)
            "busy" -> radioGroupStatus.check(R.id.radioButtonBusy)
            "service" -> radioGroupStatus.check(R.id.radioButtonService)
        }

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Change Status")
            .setPositiveButton("Apply") { _, _ ->
                val selectedStatus = when (radioGroupStatus.checkedRadioButtonId) {
                    R.id.radioButtonFree -> "free"
                    R.id.radioButtonBusy -> "busy"
                    R.id.radioButtonService -> "service"
                    else -> cell.status
                }

                cell.status = selectedStatus
                updateCellDetailsUI()
            }
            .setNegativeButton("Cancel", null)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun updateCellDetailsUI() {
        val tvStatus: TextView = findViewById(R.id.tv_status)
        tvStatus.text = cell.status
    }
}

