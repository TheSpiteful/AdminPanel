package com.example.adminpanel.Requests

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

// Клас GetCellData, який використовується для відправки запитів до сервера та отримання відповіді
class GetCellData {

    // Функція для відправки POST-запиту до сервера та повернення відповіді у вигляді рядка
    private fun sendRequest(json: JSONObject): String {

        // URL сервера
        val url = "https://portal.trans-service-1.com.ua:40547"

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

    // Функція для відправки запиту "open_cell_by_admin"
    fun openCellByAdmin(): String {
        val json = JSONObject()
        json.put("typeOfRequest", "admin_parcel")
        json.put("parcel_action", "open_cell")
        json.put("phonenumber", "+380930000000")
        json.put("cell_id", "2")
        return sendRequest(json)
    }

    // Функція для відправки запиту "get_info_parcel_by_admin"
    fun getInfoParcelByAdmin(): String {
        val json = JSONObject()
        json.put("typeOfRequest", "admin_parcel")
        json.put("parcel_action", "get_info_parcel")
        json.put("id", "33c55435-3df1-4052-91fd-5e457cf33917")
        return sendRequest(json)
    }

    // Функція для відправки запиту "set_cell_status_by_admin"
    fun setCellStatusByAdmin(): String {
        val json = JSONObject()
        json.put("typeOfRequest", "admin_parcel")
        json.put("parcel_action", "set_cell_status")
        json.put("cell_id", "2")
        json.put("status", "service")
        json.put("phonenumber", "+380930000000")
        return sendRequest(json)
    }

    // Функція для відправки запиту "get_cell_status_by_admin"
    fun getCellStatusByAdmin(): String {
        val json = JSONObject()
        json.put("typeOfRequest", "admin_parcel")
        json.put("parcel_action", "get_cell_status")
        json.put("id", "33c55435-3df1-4052-91fd-5e457cf33917")
        return sendRequest(json)
    }
}
