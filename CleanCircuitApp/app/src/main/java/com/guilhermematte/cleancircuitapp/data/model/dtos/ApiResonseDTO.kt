package com.guilhermematte.cleancircuitapp.data.model.dtos

import com.google.gson.JsonElement

data class ApiResonseDTO(
    val status_res: JsonElement?, // pode ser objeto, lista, string, null...
    val status_msg: String,
    val status_code: Int
)
