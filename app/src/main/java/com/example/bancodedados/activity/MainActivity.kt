package com.example.bancodedados.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bancodedados.FormularioActivity
import com.example.bancodedados.R
import com.example.bancodedados.adapter.RecyclerAdapter
import com.example.bancodedados.dbo.DBHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var floatButton : FloatingActionButton

    // Chamar a conexao o banco de dados
    val dbconnect = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Clique do botao flutuante
        floatButton = findViewById(R.id.floatingActionButton)
        floatButton.setOnClickListener(){
            val intent = Intent(this, FormularioActivity::class.java)
            startActivity( intent )
        }
    }

    override fun onResume() {
        super.onResume()

        // Limpando o 'cache'
        Herois.LimparPreferencias(this)

        // Clicando nos itens da tabela
        val listaHerois = dbconnect.ListarHerois();

//        Toast.makeText(this, "${ listaHerois[0].nomeHeroi}", Toast.LENGTH_SHORT).show()

        // Listar Heroi
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = RecyclerAdapter(this, dbconnect.ListarHerois())
//        recyclerView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaHerois)
//
//        recyclerView.setOnItemClickListener(){ _, _, posicao, _ ->
//
//            Herois.SalvarPreferencias(this, listaHerois[posicao] )
//
//            val intent = Intent(this, FormularioActivity::class.java)
//            startActivity( intent )
//        }
    }
}