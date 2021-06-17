package collegeofmedicine.researcher.cmr;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Hissa on 07/08/17.
 */

public class NotifyService extends Service {

    private NotificationManager mManager;
    CharSequence title, message;

    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);

        title = intent.getStringExtra("title");
        message = intent.getStringExtra("message");

        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(),splash.class);

        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, intent1, 0);


android.support.v4.app.NotificationCompat.Builder mBuilder =
        new android.support.v4.app.NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(contentIntent);
        mManager.notify(0, mBuilder.build());
    }

    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }


}
