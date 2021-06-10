package com.hilbing.arkanhelp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.xbird.library.database.AppDatabase
import io.xbird.library.enum.Actions
import io.xbird.library.service.MotionDetectService
import io.xbird.library.utils.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Intent(this, MotionDetectService::class.java).also {
            it.action = Actions.START.name
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(it)
                return
            }
            startService(it)
        }

        val readings = AppDatabase.getAppDataBase(applicationContext)?.freeFallReadingDao()?.getLatestReadings()
        log(readings.toString())
    }
}



