package tafadzwa.nutritionist.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationPublisher extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "activity_notification-id";
    public static String NOTIFICATION = "activity_notification";

    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = intent.getParcelableExtra(NotificationPublisher.NOTIFICATION);
        int id = intent.getIntExtra(NotificationPublisher.NOTIFICATION_ID, 0);
        notificationManager.notify(id, notification);

    }
}
