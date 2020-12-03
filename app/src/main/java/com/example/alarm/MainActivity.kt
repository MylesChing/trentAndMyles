package com.example.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var alarmManager : AlarmManager
    lateinit var timePicker : TimePicker
    var notificationId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonSet: Button = findViewById(R.id.button_set)
        timePicker = findViewById(R.id.time_picker)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        //timePicker.setIs24HourView(true)
        var mediaPlayer = MediaPlayer.create(applicationContext, R.raw.police_car)

        buttonSet.setOnClickListener { setAlarm() }
    }

    fun setAlarm(){
        val calendar: Calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, timePicker.hour)
            set(Calendar.MINUTE, timePicker.minute)
            set(Calendar.SECOND, 0)
        }

        val intent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            Intent(applicationContext, AlarmBroadcastReceiver::class.java).apply{
                putExtra("notificationId", ++notificationId)
            },
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmClockInfo: AlarmManager.AlarmClockInfo =
            AlarmManager.AlarmClockInfo(calendar.timeInMillis,
                intent
            )

        alarmManager.setAlarmClock(alarmClockInfo, intent)
//        alarmManager.set(
//           AlarmManager.RTC_WAKEUP,
//           calendar.timeInMillis,
//           PendingIntent.getBroadcast(
//               applicationContext,
//               0,
//               Intent(applicationContext, AlarmBroadcastReceiver::class.java).apply{
//                   putExtra("notificationId", ++notificationId)
//               },
//               PendingIntent.FLAG_CANCEL_CURRENT
//           )
//       )

        Toast.makeText(applicationContext, "Alarm Set", Toast.LENGTH_SHORT).show()
    }

//    fun cancelAlarm(){
//
//    }
}