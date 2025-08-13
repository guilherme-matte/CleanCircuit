package com.guilhermematte.cleancircuitapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.guilhermematte.cleancircuitapp.R
import com.guilhermematte.cleancircuitapp.data.model.Services.StatusService
import com.guilhermematte.cleancircuitapp.data.model.dtos.LoginDTO
import com.guilhermematte.cleancircuitapp.data.network.RetrofitClient
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
lifecycleScope.launch {
    val online = StatusService().isApiOnline("http://192.168.15.200:8080")

    if (!online) {
        Toast.makeText(
            this@LoginActivity,
            "API Offline",
            Toast.LENGTH_LONG
        ).show()
        return@launch
    } }
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                val loginDTO = LoginDTO(email, password)


                lifecycleScope.launch {


                    try {
                        val response = RetrofitClient.instance.login(loginDTO)

                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body != null) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Login OK: ${body.status_msg}",
                                    Toast.LENGTH_LONG
                                ).show()
                                println("RESPOSTA: $body")
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Erro: corpo vazio da resposta",
                                    Toast.LENGTH_LONG
                                ).show()
                                println("RESPOSTA VAZIA!")
                            }
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Erro HTTP: ${response.code()}",
                                Toast.LENGTH_LONG
                            ).show()
                            println(
                                "ERRO HTTP: ${response.code()}, ${
                                    response.errorBody()?.string()
                                }"
                            )
                        }
                    } catch (e: Exception) {
                        println("ERRO: ${e.message}")
                        Toast.makeText(
                            this@LoginActivity,
                            "Erro de conexão: ${e.message}",

                            Toast.LENGTH_LONG
                        ).show()
                        println("EXCEÇÃO: ${e.printStackTrace()}")
                    }
                }

            } else {
                Toast.makeText(
                    this@LoginActivity,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }
}