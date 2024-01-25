package com.example.pruebacontinuasqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var id : TextView = findViewById(R.id.campoId)
        var nom : TextView = findViewById(R.id.campoNom)
        var pes : TextView = findViewById(R.id.campoPeso)
        var pre : TextView = findViewById(R.id.campoPrecio)
        var ata : TextView = findViewById(R.id.campoAtaque)
        var def : TextView = findViewById(R.id.campoDefensa)
        var vida : TextView = findViewById(R.id.campoVida)
        var dbhelper : DatabaseHelper
        

    }
}