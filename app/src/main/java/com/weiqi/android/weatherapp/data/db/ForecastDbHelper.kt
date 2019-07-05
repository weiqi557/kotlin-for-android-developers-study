package com.weiqi.android.weatherapp.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.weiqi.android.weatherapp.App
import org.jetbrains.anko.db.*

class ForecastDbHelper(ctx: Context = App.instance) :
    ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    companion object{
        const val DB_NAME = "forecast.db"
        const val DB_VERSION = 1
        val instance: ForecastDbHelper by lazy {
            ForecastDbHelper()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(CityForecastTable.NAME,true)
        db.dropTable(DayForecastTable.NAME,true)
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(CityForecastTable.NAME,true,
            CityForecastTable.ID to INTEGER + PRIMARY_KEY,
            CityForecastTable.CITY to TEXT,
            CityForecastTable.COUNTRY to TEXT )

        db.createTable(DayForecastTable.NAME,true,
            DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            DayForecastTable.DATE to INTEGER,
            DayForecastTable.DESCRIPTION to TEXT,
            DayForecastTable.HIGH to INTEGER,
            DayForecastTable.LOW to INTEGER,
            DayForecastTable.ICON_URL to TEXT,
            DayForecastTable.CITY_ID to INTEGER)
    }


}