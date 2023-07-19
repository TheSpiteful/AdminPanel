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

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_panel)

        progressBar = findViewById(R.id.progress_bar)
        progressBar.visibility = ProgressBar.INVISIBLE

        val btnGetRequest = findViewById<Button>(R.id.btnGetRequest)
        btnGetRequest.setOnClickListener {
            progressBar.visibility = ProgressBar.VISIBLE
            GlobalScope.launch(Dispatchers.IO) {
                val getCellData = GetCellData()
                val result = getCellData.getFakeResponse()

                launch(Dispatchers.Main) {
                    progressBar.visibility = ProgressBar.INVISIBLE
                    showResult(result)
                }
            }
        }
    }

    private fun showResult(result: String) {
        val intent = Intent(this, RequestsActivity::class.java)
        intent.putExtra("result", result)
        startActivity(intent)
    }
}
