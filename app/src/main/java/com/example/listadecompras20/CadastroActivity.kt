package com.example.listadecompras20

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CadastroActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Definindo Ids
        val btnInserir = findViewById<Button>(R.id.btn_inserir)
        val editTextProdutos = findViewById<EditText>(R.id.etxt_nome_produto)
        val editTextQuantidade = findViewById<EditText>(R.id.etxt_quantidade)
        val editTextValor = findViewById<EditText>(R.id.etxt_valor)


        // Implementacao do botao inserir
        btnInserir.setOnClickListener {
            //Captura o que o usuario escreveu
            val produto = editTextProdutos.text.toString()
            val qnt = editTextQuantidade.text.toString()
            val valor = editTextValor.text.toString()

            // verificando se o usuario digitou algum valor
            if(produto.isNotEmpty() && qnt.isNotEmpty() && valor.isNotEmpty()){
                val prod = Produto(produto, qnt.toInt(), valor.toDouble())
                produtosGlobal.add(prod)
                // Limpandos campos após informar os valores
                editTextValor.text.clear()
                editTextQuantidade.text.clear()
                editTextProdutos.text.clear()

            }else{
                editTextProdutos.error = if (produto.isEmpty()) "Preencha o nome do produto" else null
                editTextValor.error =  if (valor.isEmpty()) "Preencha o valor" else null
                editTextQuantidade.error = if (qnt.isEmpty()) "Preencha a quantidade" else null
            }

        }
    }
}