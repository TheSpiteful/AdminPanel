package com.example.adminpanel

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class GetCellData {

    fun sendRequest(): String {
        val url = "https://portal.trans-service-1.com.ua:40547"

        val json = JSONObject()
        json.put("typeOfRequest", "phonebook")
        json.put("client_phonenumber", "+380000000333")
        json.put("token", "AjEA1hhg9scw6xzmFqGXvI5J3qKK2nBpFXsv")
        json.put("app_ver", "1.9.3")
        json.put("app_ver_name", "PIGEON")
        json.put("client_phonebook_version", "1689056681")

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = json.toString().toRequestBody(mediaType)

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val client = OkHttpClient()
        try {
            val response = client.newCall(request).execute()

            val responseData = response.body?.string()
            if (responseData != null) {
                val jsonResponse = JSONObject(responseData)
                val code = jsonResponse.getInt("code")
                val newContactCount = jsonResponse.getInt("new_contact_count")

                return "Code: $code\nNew Contact Count: $newContactCount"
            } else {
                return "Error: No response body"
            }
        } catch (e: IOException) {
            return "Error: ${e.message}"
        }
    }
}
