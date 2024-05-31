package com.example.exercicioksoap

import SoapService
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity(), SensorEventListener {

    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build()
        )

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        mSensorManager!!.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)

        findViewById<Button>(R.id.btnSend).setOnClickListener {
            val xValue = findViewById<TextView>(R.id.xAxys).text.toString()
            val yValue = findViewById<TextView>(R.id.yAxys).text.toString()
            val zValue = findViewById<TextView>(R.id.zAxys).text.toString()
            val timestamp = findViewById<TextView>(R.id.timeStamp).text.toString()

            val position = Position(xValue, yValue, zValue, timestamp)

            val soapService = SoapService()
            soapService.sendPosition(position)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            val xValue = event.values[0].toString()
            val yValue = event.values[1].toString()
            val zValue = event.values[2].toString()
            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

            findViewById<TextView>(R.id.xAxys).text = xValue
            findViewById<TextView>(R.id.yAxys).text = yValue
            findViewById<TextView>(R.id.zAxys).text = zValue
            findViewById<TextView>(R.id.timeStamp).text = timestamp
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }
}


