package uz.alien.manager

import android.annotation.SuppressLint
import android.app.Application
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
import androidx.core.content.ContextCompat

object Notification2 {

    private val TAG = NotificationManager::class.java.simpleName

    private const val CHANNEL_ID = "my_channel_id"
    private const val CHANNEL_NAME = "My Channel"
    private const val NOTIFICATION_ID = 1
    private const val MESSAGE = "NotificationManager has not been initialized. Please init NotificationManager in your Application class!!!"

    @Volatile
    private var notificationManager: NotificationManager? = null
    @Volatile
    private var appContext: Context? = null

    fun init(app: Application) {
        if (notificationManager == null) {
            synchronized(this) {
                if (notificationManager == null) {
                    notificationManager =
                        app.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    appContext = app.applicationContext
                    val importance = NotificationManager.IMPORTANCE_DEFAULT
                    val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
                    notificationManager!!.createNotificationChannel(channel)
                    log("NotificationManager has been initialized")
                }
            }
        }
    }

    private fun log(msg: String) {
        Log.w(TAG, msg)
    }

    private fun checkInitialization(): Context {
        if (notificationManager == null || appContext == null) {
            throw Exception(MESSAGE)
        }
        return appContext!!
    }

    private fun hasPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
    }

    fun requestNotificationPermission(activity: AppCompatActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = android.Manifest.permission.POST_NOTIFICATIONS
            if (!hasPermission(activity)) {
                ActivityCompat.requestPermissions(activity, arrayOf(permission), 1)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun sendNotification(title: String, message: String) {
        val context = checkInitialization()

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_words_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (!hasPermission(context)) {
                    log("Notification permission not granted")
                    return
                }
            }
            notify(NOTIFICATION_ID, builder.build())
        }
    }
}