package com.example.listadecompras20

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnInserir = findViewById<Button>(R.id.btn_inserir)
        val editTextProdutos = findViewById<EditText>(R.id.etxt_nome_produto)


        // Implementacao do botao inserir
        btnInserir.setOnClickListener {
            //Captura o que o usuario escreveu
            val produto = editTextProdutos.text.toString()

            if (produto.isEmpty()){
                editTextProdutos.error = "Coloque um item na lista"
            }else{
                //Enviando o produto para a lista


                //Limpando o EditText apos inserir o produto
                editTextProdutos.text.clear()
            }
        }
    }
}