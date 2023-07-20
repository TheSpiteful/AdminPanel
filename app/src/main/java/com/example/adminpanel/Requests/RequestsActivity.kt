package com.example.adminpanel.Requests

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanel.Adapter.CellAdapter
import com.example.adminpanel.Cell
import com.example.adminpanel.R
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class RequestsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cellAdapter: CellAdapter
    private lateinit var cells: ArrayList<Cell>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rv_result)

        recyclerView = findViewById(R.id.recyclerViewCells)
        recyclerView.layoutManager = LinearLayoutManager(this)

        cells = ArrayList()

        cellAdapter = CellAdapter(this, cells)
        recyclerView.adapter = cellAdapter

        if (intent.hasExtra("result")) {
            processResponse(intent.getStringExtra("result").toString())
        }
    }

    private fun processResponse(responseData: String) {
        try {
            val jsonResponse = JSONObject(responseData)
            val jsonCells = jsonResponse.getJSONArray("cells")

            cells.clear()

            for (i in 0 until jsonCells.length()) {
                val jsonCell = jsonCells.getJSONObject(i)
                val id = jsonCell.getString("id")
                val size = jsonCell.getInt("size")
                val status = jsonCell.getString("status")
                val timestamp = jsonCell.getLong("datetime") // Assuming datetime is in Unix timestamp format

                // Convert timestamp to formatted date string
                val formattedDatetime = convertTimestampToFormattedDate(timestamp)

                val cell = Cell(id, size, status, formattedDatetime)
                cells.add(cell)
            }

            cellAdapter.notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Function to convert Unix timestamp to formatted date string
    private fun convertTimestampToFormattedDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = Date(timestamp)
        return sdf.format(date)
    }
}

