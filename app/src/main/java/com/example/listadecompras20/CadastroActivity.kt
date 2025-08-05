package com.example.listadecompras20

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CadastroActivity : AppCompatActivity() {

    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private lateinit var imgFotoProduto: ImageView
    private var imageUriSelecionada: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // IDs
        val btnInserir = findViewById<Button>(R.id.btn_inserir)
        val editTextProdutos = findViewById<EditText>(R.id.etxt_nome_produto)
        val editTextQuantidade = findViewById<EditText>(R.id.etxt_quantidade)
        val editTextValor = findViewById<EditText>(R.id.etxt_valor)
        imgFotoProduto = findViewById(R.id.img_foto_produto)

        // Inicializa o launcher da galeria
        imagePickerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri = result.data?.data
                imageUriSelecionada = imageUri
                imgFotoProduto.setImageURI(imageUri) // exibe imagem no ImageView
            }
        }

        // Ação do botão de inserir produto
        btnInserir.setOnClickListener {
            val produto = editTextProdutos.text.toString()
            val qnt = editTextQuantidade.text.toString()
            val valor = editTextValor.text.toString()

            if (produto.isNotEmpty() && qnt.isNotEmpty() && valor.isNotEmpty()) {
                val prod = Produto(produto, qnt.toInt(), valor.toDouble())
                produtosGlobal.add(prod)
                editTextValor.text.clear()
                editTextQuantidade.text.clear()
                editTextProdutos.text.clear()
                imgFotoProduto.setImageResource(android.R.drawable.ic_menu_camera) // ou qualquer imagem padrão
            } else {
                editTextProdutos.error = if (produto.isEmpty()) "Preencha o nome do produto" else null
                editTextValor.error = if (valor.isEmpty()) "Preencha o valor" else null
                editTextQuantidade.error = if (qnt.isEmpty()) "Preencha a quantidade" else null
            }
        }

        // Ao clicar na imagem
        imgFotoProduto.setOnClickListener {
            abrirGaleria()
        }
    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        imagePickerLauncher.launch(Intent.createChooser(intent, "Selecione uma imagem"))
    }
}
