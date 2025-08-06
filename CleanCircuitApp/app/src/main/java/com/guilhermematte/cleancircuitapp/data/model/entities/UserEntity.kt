package com.guilhermematte.cleancircuitapp.data.model.entities

data class UserEntity(
    val id: Int,
    val nomeCompleto: String,
    val cpf: String,
    val email: String,
    val senha: String,
    val date: String,
    val telefone: String,
    val urlProfileImage: String
)
