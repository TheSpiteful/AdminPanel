package com.example.adminpanel.Requests

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
        return try {
            val response = client.newCall(request).execute()

            response.body?.string() ?: "Error: No response body"
        } catch (e: IOException) {
            "Error: ${e.message}"
        }
    }

    fun getFakeResponse(): String {
        return """{
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
    }
}
