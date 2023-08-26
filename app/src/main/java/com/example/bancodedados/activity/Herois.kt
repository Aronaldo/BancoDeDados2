package com.example.bancodedados.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class Herois
    ( val id : Int,
      var nomeHeroi : String,
      var poderHeroi : String,
      var planetaOrigem : String
    ){

    companion object{
        fun SalvarPreferencias(context: Context, herois: Herois){
            // Salvar as informações em 'cache'
            val preferencias = context.getSharedPreferences("chaveGeral", AppCompatActivity.MODE_PRIVATE)

            val editorPreferencia = preferencias.edit()
            editorPreferencia.putInt( "idHeroi", herois.id )
            editorPreferencia.putString( "nomeHeroi", herois.nomeHeroi )
            editorPreferencia.putString( "poderHeroi", herois.poderHeroi )
            editorPreferencia.putString( "planetaHeroi", herois.planetaOrigem )
            editorPreferencia.commit()
        }

        fun LimparPreferencias(context: Context){
            // Salvar as informações em 'cache'
            val preferencias = context.getSharedPreferences("chaveGeral", AppCompatActivity.MODE_PRIVATE)

            val editorPreferencia = preferencias.edit()
            editorPreferencia.remove("idHeroi")
            editorPreferencia.remove("nomeHeroi")
            editorPreferencia.remove("poderHeroi")
            editorPreferencia.remove("planetaHeroi")
            editorPreferencia.apply()
        }

        fun CapturarPreferencias(context: Context) : Herois{
            // Resgata as informações em 'cache'
            val preferencias = context.getSharedPreferences("chaveGeral", AppCompatActivity.MODE_PRIVATE)

            val idHeroi = preferencias.getInt("idHeroi", 0)
            val nomeHeroi = preferencias.getString("nomeHeroi", "").toString()
            val poderHeroi = preferencias.getString("poderHeroi", "").toString()
            val planetaOrigem = preferencias.getString("planetaHeroi", "").toString()

            // Criando o heroi com as informações salvas
            val heroi = Herois( idHeroi, nomeHeroi, poderHeroi, planetaOrigem )

            return heroi
        }
    }


    override fun toString(): String {
        return "id: ${id}, ${nomeHeroi} e o seu poder ${poderHeroi}"
    }
}