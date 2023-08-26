package com.example.bancodedados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bancodedados.activity.Herois
import com.example.bancodedados.dbo.DBHelper

class FormularioActivity : AppCompatActivity() {

    lateinit var botaoSalvar : Button
    lateinit var botaoAlterar : Button
    lateinit var botaoRemover : Button

    lateinit var nomeHeroi : EditText
    lateinit var poderHeroi : EditText
    lateinit var planetaHeroi : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        // Chamar a conexao o banco de dados
        var dbconnect = DBHelper(this)

        // Recebendo nossos campos
        nomeHeroi = findViewById(R.id.nomeHeroi)
        poderHeroi = findViewById(R.id.poderHeroi)
        planetaHeroi = findViewById(R.id.planetaHeroi)

        botaoSalvar = findViewById(R.id.btnSalvar)
        botaoSalvar.setOnClickListener(){

            // Adicionar novo heroi
            dbconnect.InserirHerois( nomeHeroi.text.toString(), poderHeroi.text.toString(), planetaHeroi.text.toString() )

            finish()
        }

        botaoAlterar = findViewById(R.id.btnAlterar)
        botaoAlterar.setOnClickListener(){

            val heroi = Herois.CapturarPreferencias(this)

            dbconnect.AlterarHeroi( heroi.id, nomeHeroi.text.toString(), poderHeroi.text.toString(), planetaHeroi.text.toString() )

            finish()
        }

        botaoRemover = findViewById(R.id.btnRemover)
        botaoRemover.setOnClickListener(){

            val receberHeroi = Herois.CapturarPreferencias(this)

            // Excluir heroi
            dbconnect.ExcluirHeroi( receberHeroi.id )

            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        // Chamando a listagem de informacoes
        PreencherInformacoes()
    }

    fun PreencherInformacoes(){
        val heroi = Herois.CapturarPreferencias(this)

        // Verificar se retornou um heroi
        if( heroi.id > 0 )
        {
            nomeHeroi.setText( heroi.nomeHeroi )
            poderHeroi.setText( heroi.poderHeroi )
            planetaHeroi.setText( heroi.planetaOrigem )
        }
    }
}