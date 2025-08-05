package com.example.listadecompras20

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Armazenar os Ids
        val listaProdutos = findViewById<ListView>(R.id.list_item)
        val btnAdicionar = findViewById<Button>(R.id.btn_adicionar)

        //Definir Adapter
        val produtosAdapter = ProdutosAdapter(this)

        //Implementacao do adaptador na lista
        listaProdutos.adapter = produtosAdapter

        //Remover item da lista
        listaProdutos.setOnItemClickListener { parent, view, position, id ->
            //Buscando o produto e convertendo para string
            val produtos = parent.getItemAtPosition(position) as? String

//            produtos?.let {
//                produtosAdapter.remove(it)
//                Toast.makeText(
//                    this@MainActivity,
//                    "$it foi removido da lista",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }

            //Retorna indicando que o clique foi realizado com sucesso
            true
        }

        btnAdicionar.setOnClickListener {
            // Criando a intent
            val intent = Intent(this, CadastroActivity::class.java)
            //Iniciando a atividade
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val adapter = findViewById<ListView>(R.id.list_item).adapter as ProdutosAdapter
        adapter.clear()
        adapter.addAll(produtosGlobal)

        // Soma os valores dos itens da lista
        val txtTotal = findViewById<TextView>(R.id.txt_total)
        val soma = produtosGlobal.sumByDouble { it.valor * it.quantidade }
        val f = NumberFormat.getCurrencyInstance(Locale("pt","br"))
        txtTotal.text = "TOTAL: ${f.format(soma)}"

    }

}