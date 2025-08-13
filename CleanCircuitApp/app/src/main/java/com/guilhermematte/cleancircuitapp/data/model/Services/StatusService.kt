package com.guilhermematte.cleancircuitapp.data.model.Services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatusService {
    suspend fun isApiOnline(urlString: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val url = java.net.URL(urlString)
                val connection = url.openConnection() as java.net.HttpURLConnection
                connection.connectTimeout = 3000
                connection.readTimeout = 3000
                connection.requestMethod = "GET"

                val responseCode = connection.responseCode
                responseCode in 200..399
            } catch (e: Exception) {
                false
            }
        }

    }
}