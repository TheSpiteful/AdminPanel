package com.example.adminpanel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_panel)

        val btnGetRequest = findViewById<Button>(R.id.btnGetRequest)
        btnGetRequest.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val getCellData = GetCellData()
                val result = getCellData.sendRequest()

                launch(Dispatchers.Main) {
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

