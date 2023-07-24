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


    // Клас RequestsActivity, який розширює AppCompatActivity та відповідає за головний екран
class RequestsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cellAdapter: CellAdapter
    private lateinit var cells: ArrayList<Cell>

    // Метод onCreate, який викликається під час створення активності
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rv_result)

    // Отримання посилання на RecyclerView та встановлення LayoutManager
        recyclerView = findViewById(R.id.recyclerViewCells)
        recyclerView.layoutManager = LinearLayoutManager(this)

    // Ініціалізація списку cells як ArrayList
        cells = ArrayList()

    // Ініціалізація та встановлення адаптера для RecyclerView
        cellAdapter = CellAdapter(this, cells)
        recyclerView.adapter = cellAdapter

    // Перевірка чи існує додатковий параметр result у переданому Intent
    // Якщо так, то викликається функція processResponce
        if (intent.hasExtra("result")) {
            processResponse(intent.getStringExtra("result").toString())
        }
    }

    // Функція для обробки відповіді responceData у форматі JSON
    private fun processResponse(responseData: String) {
        try {
            val jsonResponse = JSONObject(responseData)
            val jsonCells = jsonResponse.getJSONArray("cells")

    // Очистка списку cells перед заповненням новими даними
            cells.clear()

    // Проходження через всі елементи масиву jsonCells
            for (i in 0 until jsonCells.length()) {
                val jsonCell = jsonCells.getJSONObject(i)
                val id = jsonCell.getString("id")
                val size = jsonCell.getInt("size")
                val status = jsonCell.getString("status")
                val timestamp = jsonCell.getLong("datetime")

    // Форматування часової мітки в зрозумілий рядок
                val formattedDatetime = convertTimestampToFormattedDate(timestamp)

    // Створення нового об'єкта класу cell з отриманими даними та додавання його до списку cell
                val cell = Cell(id, size, status, formattedDatetime)
                cells.add(cell)
            }

    // Повідомлення адаптера про зміни даних, щоб RecyclerView оновив свій вміст
            cellAdapter.notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Функція для конвертації Timestamp в рядок з датою та часом
    private fun convertTimestampToFormattedDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = Date(timestamp)
        return sdf.format(date)
    }
}