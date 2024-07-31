package uz.alien.manager

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object Notification {

    private val TAG = Notification::class.java.simpleName

    private lateinit var activity: AppCompatActivity

    private val channelId = "my_channel_id"
    private val channelName = "My Channel"
    private val notificationId = 1

    private val importance = NotificationManager.IMPORTANCE_DEFAULT
    private val channel = NotificationChannel(channelId, channelName, importance)

    private var notificationManager: NotificationManager? = null

    private fun log(msg: String) {
        Log.w(TAG, msg)
    }

    fun init(activity: AppCompatActivity) {

        this.activity = activity

        notificationManager =
            activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager!!.createNotificationChannel(channel)

        log("Notification has been initialized :)")
    }

    private fun hasGranted(perm: String): Int {

        return ActivityCompat.checkSelfPermission(activity, perm)
    }

    @SuppressLint("MissingPermission")
    fun sendNotification(title: String, message: String) {

        if (notificationManager == null) {

            log("Notification has not been initialized :(")

        } else {

            val builder = NotificationCompat.Builder(activity, channelId)
                .setSmallIcon(R.drawable.ic_words_notification)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(activity)) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                    val perm = Manifest.permission.POST_NOTIFICATIONS

                    if (hasGranted(perm) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(activity, arrayOf(perm), 1)

                    } else {

                        notify(notificationId, builder.build())
                    }
                }
            }
        }
    }
}