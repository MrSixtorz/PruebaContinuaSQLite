package com.example.pruebacontinuasqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context:Context) : SQLiteOpenHelper (context, DATABASE, null,DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "ObjMochila.db"
        private const val TABLA_OBJ_MOCHILA = "ObjMochila"
        private const val KEY_ID = "_id"
        private const val COLUMN_TIPO_ARTICULO = "Tipo"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_PESO = "peso"
        private const val COLUMN_PRECIO = "precio"
        private const val COLUMN_ATAQUE = "ataque"
        private const val COLUMN_DEFENSA = "defensa"
        private const val COLUMN_VIDA = "vida"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLA_OBJ_MOCHILA ($KEY_ID INTEGER PRIMARY KEY,"+
                "$COLUMN_TIPO_ARTICULO, $COLUMN_NOMBRE, $COLUMN_PESO, $COLUMN_PRECIO"+
                "$COLUMN_ATAQUE, $COLUMN_DEFENSA, $COLUMN_VIDA)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLA_OBJ_MOCHILA")
        onCreate(db)
    }

    fun addArticulo (art : Articulo){
        val db = this.writableDatabase
        val values = ContentValues ().apply {
            put(KEY_ID, art.getId().toInt())
            put(COLUMN_TIPO_ARTICULO, art.getTipoArticulo().toString())
            put(COLUMN_NOMBRE,art.getNombre().toString())
            put(COLUMN_PESO,art.getPeso())
            put(COLUMN_PRECIO,art.getPrecio())
            put(COLUMN_ATAQUE,art.getAtaque())
            put(COLUMN_DEFENSA,art.getDefensa())
            put(COLUMN_VIDA,art.getVida())
        }
        db.insert(TABLA_OBJ_MOCHILA, null, values)
        db.close()
    }
    @SuppressLint("Range")
    fun getContenido () : ArrayList <Articulo>{
        val articulos = ArrayList<Articulo>()
        val selectQuery = "SELECT * FROM $TABLA_OBJ_MOCHILA"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()){
            do{
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val tipo = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_ARTICULO))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val peso = cursor.getInt(cursor.getColumnIndex(COLUMN_PESO))
                val precio = cursor.getInt(cursor.getColumnIndex(COLUMN_PRECIO))
                val ataque = cursor.getInt(cursor.getColumnIndex(COLUMN_ATAQUE))
                val defensa = cursor.getInt(cursor.getColumnIndex(COLUMN_DEFENSA))
                val vida = cursor.getInt(cursor.getColumnIndex(COLUMN_VIDA))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return articulos
    }
    fun getNumeroArticulos () : Int {
        return getContenido().size
    }

}