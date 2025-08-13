package com.guilhermematte.cleancircuitapp.data.network

import com.guilhermematte.cleancircuitapp.data.model.dtos.ApiResonseDTO
import com.guilhermematte.cleancircuitapp.data.model.dtos.LoginDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
@POST("/login")
suspend fun login (@Body loginDTO: LoginDTO): Response<ApiResonseDTO>
}

