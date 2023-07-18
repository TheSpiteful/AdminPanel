package com.example.adminpanel.Requests

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanel.Adapter.CellAdapter
import com.example.adminpanel.Cell
import com.example.adminpanel.R
import org.json.JSONObject

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

        val responseData = """{
        "cells": [
            {
                "id": "A1",
                "size": 100,
                "status": "free",
                "datetime": 1689321843000
            },
            {
                "id": "A2",
                "size": 100,
                "status": "free",
                "datetime": 1689321843000
            },
            {
                "id": "A3",
                "size": 100,
                "status": "busy",
                "datetime": 1689321843000
            },
            {
                "id": "A4",
                "size": 100,
                "status": "free",
                "datetime": 1689321843000
            },
            {
                "id": "A5",
                "size": 300,
                "status": "busy",
                "datetime": 1689321843000
            },
            {
                "id": "B1",
                "size": 100,
                "status": "busy",
                "datetime": 1689321843000
            },
            {
                "id": "B2",
                "size": 100,
                "status": "free",
                "datetime": 1689321843000
            },
            {
                "id": "B3",
                "size": 100,
                "status": "service",
                "datetime": 1689321843000
            },
            {
                "id": "B4",
                "size": 100,
                "status": "free",
                "datetime": 1689321843000
            },
            {
                "id": "B5",
                "size": 300,
                "status": "free",
                "datetime": 1689321843000
            }
        ]
    }"""

        processResponse(responseData)
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

                val cell = Cell(id, size, status, datetime)
                cells.add(cell)
            }

            cellAdapter.notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}



