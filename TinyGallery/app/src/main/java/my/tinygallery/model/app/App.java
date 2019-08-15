package my.tinygallery.model.app;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.squareup.leakcanary.LeakCanary;

import my.tinygallery.model.database.PhotoDatabase;

public class App extends Application {


    public static final String TAG = "App";
    private static AppComponent appComponent;
    private static PhotoDatabase photoDatabase;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initAppComponent();
        photoDatabase = initDatabase();

        initLaekCanary();
    }

    private void initLaekCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private PhotoDatabase initDatabase() {
        return Room.databaseBuilder(getApplicationContext(), PhotoDatabase.class, "photo_database").build();
    }

    public AppComponent initAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static PhotoDatabase getPhotoDatabase() {
        return photoDatabase;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.i(TAG, "Terminate");
    }
}
