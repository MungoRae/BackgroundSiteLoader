package uk.me.mungorae.loadinbackground;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AsyncWebPageLoaderService extends Service {
    public AsyncWebPageLoaderService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }
}
