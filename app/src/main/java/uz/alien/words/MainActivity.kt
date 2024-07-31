package uz.alien.words

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import uz.alien.ui.MainPager
import uz.alien.ui.pages.PageWelcome

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManager
    private lateinit var mainPager: MainPager
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handler = Handler(mainLooper)

        Thread.sleep(1096L)
        installSplashScreen()

        enableEdgeToEdge()

        handler.post {

            mainPager = MainPager()
            mainPager.init(this)

            mainPager.firstPage(PageWelcome.self, 1096L)

            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            sendNotification(this, "Hello", "Salom sizga kimdir xabar yubordi...")
        }
    }

    fun sendNotification(context: Context, title: String, message: String) {
        val channelId = "my_channel_id"
        val channelName = "My Channel"
        val notificationId = 1

        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance)
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_words_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ActivityCompat.requestPermissions(this@MainActivity,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
                }
                return
            }
            notify(notificationId, builder.build())
        }
    }
}