package com.example.adminpanel.Requests

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanel.Adapter.CellAdapter
import com.example.adminpanel.Cell
import com.example.adminpanel.R
import org.json.JSONObject
import java.sql.Timestamp
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

        cellAdapter = CellAdapter(cells)
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
                val datetime = jsonCell.getLong("datetime")

                val date = Timestamp(datetime)
                val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val formattedDateTime = format.format(date)

                val cell = Cell(id, size, status, formattedDateTime)
                cells.add(cell)
            }

            cellAdapter.notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
