package com.aurumsystem.mysqlite

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class NotifActivity : AppCompatActivity() {
    lateinit var notifManager: NotificationManager
    lateinit var notifChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelID = "12345"
    private val desc = "Test Notif"

    @SuppressLint("RemoteViewLayout")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notif)
        val btnNotif: MaterialButton = findViewById(R.id.btnNotif)

        notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btnNotif.setOnClickListener {
            val intent = Intent(this, DetailNotifActivity::class.java)

            val pendingIntent = PendingIntent.getActivity(this, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                notifChannel = NotificationChannel(channelID, desc, NotificationManager.IMPORTANCE_HIGH)
                notifChannel.enableLights(true)
                notifChannel.lightColor = Color.YELLOW
                notifChannel.enableVibration(false)
                notifManager.createNotificationChannel(notifChannel)

                builder = Notification.Builder(this, channelID)

                    .setContentTitle("Notification")
                    .setContentText("My Notif")
                    .setSmallIcon(R.drawable.ic_user)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                    .setContentIntent(pendingIntent)

            }
            else{
                builder = Notification.Builder(this)

                    .setSmallIcon(R.drawable.ic_user)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_user))
                    .setContentIntent(pendingIntent)
            }
            notifManager.notify(12345, builder.build())
            }

        }
    }
