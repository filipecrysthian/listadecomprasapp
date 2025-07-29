package com.example.listadecompras20

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text
import java.text.NumberFormat
import java.util.Locale

class ProdutosAdapter(contexto: Context) : ArrayAdapter<Produto>(contexto, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v: View

        if (convertView != null) {
            v = convertView
        } else {
            //inflar o layout
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)

            val item = getItem(position)

            // Armazendo os IDs
            val txt_produto = v.findViewById<TextView>(R.id.txt_item_produto)
            val txt_quantidade = v.findViewById<TextView>(R.id.txt_item_qnt)
            val txt_valor = v.findViewById<TextView>(R.id.txt_item_valor)
            val img_produto = v.findViewById<ImageView>(R.id.img_foto_produto)

            //Aplicando o data class Produto
            txt_quantidade.text = item!!.quantidade.toString()
            txt_produto.text = item!!.nome
            txt_valor.text = item!!.valor.toString()

            if (item!!.foto != null){
                img_produto.setImageBitmap(item!!.foto)
            }

            /* Formatação moeda BRL */

            // obtendo a instancia d objeto de formatação
            val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

            // Formantando no formato moeda
            txt_valor.text = f.format(item.valor)


        }
        return v
    }
}