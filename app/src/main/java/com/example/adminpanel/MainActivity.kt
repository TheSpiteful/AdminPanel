package com.example.adminpanel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.adminpanel.Requests.GetCellData
import com.example.adminpanel.Requests.RequestsActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

    // Клас MainActivity, який розширює AppCompatActivity та відповідає за головний екран
class MainActivity : AppCompatActivity() {

    // Властивість для збереження посилання на ProgressBar
    private lateinit var progressBar: ProgressBar

    // Метод onCreate, який викликається при створенні активності
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_panel)

    // Знаходження ProgressBar за його id та приховування його на початку
        progressBar = findViewById(R.id.progress_bar)
        progressBar.visibility = ProgressBar.INVISIBLE

    // Знаходження кнопки за її id та додавання обробника події onClick
        val btnGetRequest = findViewById<Button>(R.id.btnGetRequest)
        btnGetRequest.setOnClickListener {

    // При натисканні на кнопку, відображення ProgressBar
            progressBar.visibility = ProgressBar.VISIBLE

    // Запуск асинхронного запиту на отримання даних
            GlobalScope.launch(Dispatchers.IO) {
                val result = GetCellData().getFakeResponce()

    // Перехід до головного потоку UI для відображення результату
                launch(Dispatchers.Main) {
                    progressBar.visibility = ProgressBar.INVISIBLE
                    showResult(result)
                }
            }
        }
    }
    // Функція для переходу до активності RequestsActivity та передачі результату запиту
    private fun showResult(result: String) {
        val intent = Intent(this, RequestsActivity::class.java)
        intent.putExtra("result", result)
        startActivity(intent)
    }
}
