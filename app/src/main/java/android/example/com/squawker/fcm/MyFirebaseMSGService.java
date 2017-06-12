package android.example.com.squawker.fcm;


import android.app.NotificationManager;
import android.content.ContentValues;
import android.example.com.squawker.R;
import android.example.com.squawker.provider.SquawkProvider;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import static android.example.com.squawker.provider.SquawkContract.COLUMN_AUTHOR;
import static android.example.com.squawker.provider.SquawkContract.COLUMN_AUTHOR_KEY;
import static android.example.com.squawker.provider.SquawkContract.COLUMN_DATE;
import static android.example.com.squawker.provider.SquawkContract.COLUMN_MESSAGE;

/**
 * Created by uelordi on 12/06/17.
 */

public class MyFirebaseMSGService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Map<String, String> data = remoteMessage.getData();
        ContentValues a = new ContentValues();
        a.put(COLUMN_AUTHOR, data.get("author"));
        a.put(COLUMN_MESSAGE, data.get("message"));
        a.put(COLUMN_DATE, data.get("date"));
        a.put(COLUMN_AUTHOR_KEY, data.get("authorKey"));
        getContentResolver().insert(SquawkProvider.SquawkMessages.CONTENT_URI, a);
        sendNotificationt(data.get("message"));
    }
    public void sendNotificationt(String message) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_duck)
                        .setContentTitle("New message received")
                        .setContentText(message);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());

    }
}
