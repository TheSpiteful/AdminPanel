package com.example.adminpanel

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

    // Клас CellDetailsActivity, який розширює AppCompatActivity та відповідає за відображення
    // деталей клітинки
class CellDetailsActivity : AppCompatActivity() {

    private lateinit var cell: Cell

    // Метод onCreate, який викликаєтсься при створенні активності
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cell_details)

    // Отримання об'єкту Cell з наміру Intent, переданого з попередньої активності
        cell = intent.getParcelableExtra("cell")!!

    // Знаходження віджетів TextView та Button у макеті активності

        val tvId: TextView = findViewById(R.id.tv_id)
        val tvSize: TextView = findViewById(R.id.tv_size)
        val tvStatus: TextView = findViewById(R.id.tv_status)
        val tvDatetime: TextView = findViewById(R.id.tv_datetime)

        val btnOpen: Button = findViewById(R.id.btn_open)
        val btnChangeStatus: Button = findViewById(R.id.btn_change_status)

    // Встановлення значень відповідних властивостей клітинки для TextView
        tvId.text = cell.id
        tvSize.text = cell.size.toString()
        tvStatus.text = cell.status
        tvDatetime.text = cell.datetime

    // Обробник кліку на кнопці зміни статусу клітинки
        btnChangeStatus.setOnClickListener {

    // Відображення діалогового вікна для вибору нового статусу
            showChangeStatusDialog()
        }
    }
    // Функція для відображення діалогового вікна для зміни статусу клітинки
    private fun showChangeStatusDialog() {

    // Завантаження макету діалогового вікна
        val dialogView = layoutInflater.inflate(R.layout.dialog_change_status, null)
        val radioGroupStatus = dialogView.findViewById<RadioGroup>(R.id.radioGroupStatus)

    // Встановлення значень відповідних властивостей клітинки TextView
        when (cell.status) {
            "free" -> radioGroupStatus.check(R.id.radioButtonFree)
            "busy" -> radioGroupStatus.check(R.id.radioButtonBusy)
            "service" -> radioGroupStatus.check(R.id.radioButtonService)
        }

    //Створення діалогового вікна з використанням AlertDialog.Builder

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

    // Зміна статусу клітинки на обраний та оновлення інтерфейсу
                cell.status = selectedStatus
                updateCellDetailsUI()
            }
            .setNegativeButton("Cancel", null)

    // Створення та відображення діалогового вікна
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    // Функція для оновлення інтерфейсу з деталями клітинки після зміни статусу
    private fun updateCellDetailsUI() {
        val tvStatus: TextView = findViewById(R.id.tv_status)
        tvStatus.text = cell.status
    }
}

