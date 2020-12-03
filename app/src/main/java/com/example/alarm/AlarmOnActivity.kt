package com.example.alarm


import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_alarm_on.*

class AlarmOnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_on)

        var mediaPlayer = MediaPlayer.create(applicationContext, R.raw.police_car)
        mediaPlayer.start()

        stop_alarm.setOnClickListener {
            mediaPlayer.stop()
        }
    }
}