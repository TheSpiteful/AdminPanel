package com.example.adminpanel.Requests

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

    // Клас GetCellData, який використовується для відправки запитів до сервера та отримання відповіді
class GetCellData {

    // Функція sendRequest, яка відправляє POST запит до сервера та повертає відповідь у вигляді рядка
    fun sendRequest(): String {

    // URL сервера
        val url = "https://portal.trans-service-1.com.ua:40547"

    // Створення JSON об'єкту з даними для запиту
        val json = JSONObject()
        json.put("typeOfRequest", "phonebook")
        json.put("client_phonenumber", "+380000000333")
        json.put("token", "AjEA1hhg9scw6xzmFqGXvI5J3qKK2nBpFXsv")
        json.put("app_ver", "1.9.3")
        json.put("app_ver_name", "PIGEON")
        json.put("client_phonebook_version", "1689056681")

    // Встановлення типу даних контенту та створення запиту з JSON даними
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toString().toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

    // Створення клієнта та відправка запиту до сервера
        val client = OkHttpClient()
        return try {
            val response = client.newCall(request).execute()

    // Повернення відповіді сервера у вигляді рядка або повідомлення про помилку
            response.body?.string() ?: "Error: No response body"
        } catch (e: IOException) {
            "Error: ${e.message}"
        }
    }
    // Функція getFakeResponse, яка повертає штучні демонстраційні дані у вигляді рядка JSON
    fun getFakeResponse(): String {
        return """{
  "cells": [
    {
      "id": "A1",
      "size": 300,
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
      "size": 200,
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
      "id": "A6",
      "size": 100,
      "status": "free",
      "datetime": 1689321843000
    },
    {
      "id": "A7",
      "size": 200,
      "status": "busy",
      "datetime": 1689321843000
    },
    {
      "id": "A8",
      "size": 100,
      "status": "free",
      "datetime": 1689321843000
    },
    {
      "id": "A9",
      "size": 300,
      "status": "busy",
      "datetime": 1689321843000
    },
    {
      "id": "A10",
      "size": 100,
      "status": "free",
      "datetime": 1689321843000
    },
    {
      "id": "A11",
      "size": 200,
      "status": "busy",
      "datetime": 1689321843000
    },
    {
      "id": "A12",
      "size": 100,
      "status": "free",
      "datetime": 1689321843000
    },
    {
      "id": "A13",
      "size": 300,
      "status": "busy",
      "datetime": 1689321843000
    },
    {
      "id": "A14",
      "size": 300,
      "status": "busy",
      "datetime": 1689321843000
    },
    {
      "id": "A15",
      "size": 300,
      "status": "busy",
      "datetime": 1689321843000
    },
    {
      "id": "B16",
      "size": 100,
      "status": "busy",
      "datetime": 1689321843000
    },
    {
      "id": "B17",
      "size": 100,
      "status": "free",
      "datetime": 1689321843000
    },
    {
      "id": "B18",
      "size": 200,
      "status": "service",
      "datetime": 1689321843000
    },
    {
      "id": "B19",
      "size": 100,
      "status": "free",
      "datetime": 1689321843000
    },
    {
      "id": "B20",
      "size": 300,
      "status": "free",
      "datetime": 1689321843000
    },
    {
      "id": "B21",
      "size": 100,
      "status": "busy",
      "datetime": 1689321843000
    },
    {
      "id": "B22",
      "size": 100,
      "status": "free",
      "datetime": 1689321843000
    },
    {
      "id": "B23",
      "size": 200,
      "status": "service",
      "datetime": 1689321843000
    },
    {
      "id": "B24",
      "size": 100,
      "status": "free",
      "datetime": 1689321843000
    },
    {
      "id": "B25",
      "size": 300,
      "status": "free",
      "datetime": 1689321843000
    },
    {
      "id": "B26",
      "size": 100,
      "status": "busy",
      "datetime": 1689321843000
    },
    {
      "id": "B27",
      "size": 100,
      "status": "free",
      "datetime": 1689321843000
    },
    {
      "id": "B28",
      "size": 200,
      "status": "service",
      "datetime": 1689321843000
    },
    {
      "id": "B29",
      "size": 100,
      "status": "free",
      "datetime": 1689321843000
    },
    {
      "id": "B30",
      "size": 300,
      "status": "free",
      "datetime": 1689321843000
    },
    {
      "id": "C31",
      "size": 100,
      "status": "busy",
      "datetime": 1689321745000
    },
    {
      "id": "C32",
      "size": 200,
      "status": "free",
      "datetime": 1689321746000
    },
    {
      "id": "C33",
      "size": 300,
      "status": "free",
      "datetime": 1689321747000
    },
    {
      "id": "C34",
      "size": 100,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "C35",
      "size": 300,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "C36",
      "size": 100,
      "status": "busy",
      "datetime": 1689321745000
    },
    {
      "id": "C37",
      "size": 200,
      "status": "free",
      "datetime": 1689321746000
    },
    {
      "id": "C38",
      "size": 300,
      "status": "free",
      "datetime": 1689321747000
    },
    {
      "id": "C39",
      "size": 100,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "C40",
      "size": 300,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "C41",
      "size": 100,
      "status": "busy",
      "datetime": 1689321745000
    },
    {
      "id": "C42",
      "size": 200,
      "status": "free",
      "datetime": 1689321746000
    },
    {
      "id": "C43",
      "size": 300,
      "status": "free",
      "datetime": 1689321747000
    },
    {
      "id": "D44",
      "size": 100,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "D45",
      "size": 300,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "D46",
      "size": 100,
      "status": "busy",
      "datetime": 1689321745000
    },
    {
      "id": "D47",
      "size": 200,
      "status": "free",
      "datetime": 1689321746000
    },
    {
      "id": "D48",
      "size": 300,
      "status": "free",
      "datetime": 1689321747000
    },
    {
      "id": "D49",
      "size": 100,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "D50",
      "size": 300,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "D51",
      "size": 100,
      "status": "busy",
      "datetime": 1689321745000
    },
    {
      "id": "D52",
      "size": 200,
      "status": "free",
      "datetime": 1689321746000
    },
    {
      "id": "D53",
      "size": 300,
      "status": "free",
      "datetime": 1689321747000
    },
    {
      "id": "D54",
      "size": 100,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "D55",
      "size": 300,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "D56",
      "size": 100,
      "status": "busy",
      "datetime": 1689321745000
    },
    {
      "id": "D57",
      "size": 200,
      "status": "free",
      "datetime": 1689321746000
    },
    {
      "id": "D58",
      "size": 300,
      "status": "free",
      "datetime": 1689321747000
    },
    {
      "id": "E59",
      "size": 100,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "E60",
      "size": 300,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "E61",
      "size": 100,
      "status": "busy",
      "datetime": 1689321745000
    },
    {
      "id": "E62",
      "size": 200,
      "status": "free",
      "datetime": 1689321746000
    },
    {
      "id": "E63",
      "size": 300,
      "status": "free",
      "datetime": 1689321747000
    },
    {
      "id": "E64",
      "size": 100,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "E65",
      "size": 300,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "E66",
      "size": 100,
      "status": "busy",
      "datetime": 1689321745000
    },
    {
      "id": "E67",
      "size": 200,
      "status": "free",
      "datetime": 1689321746000
    },
    {
      "id": "E68",
      "size": 300,
      "status": "free",
      "datetime": 1689321747000
    },
    {
      "id": "E69",
      "size": 100,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "E75",
      "size": 300,
      "status": "busy",
      "datetime": 1689321748000
    },
    {
      "id": "E70",
      "size": 100,
      "status": "busy",
      "datetime": 1689321745000
    },
    {
      "id": "E71",
      "size": 200,
      "status": "free",
      "datetime": 1689321746000
    },
    {
      "id": "E72",
      "size": 300,
      "status": "free",
      "datetime": 17473218470440
    }
    
  ]
}"""
    }
}
