package my.tinygallery.model.app;

import android.app.Application;
import android.util.Log;

public class App extends Application {


    public static final String TAG = "App";
    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initAppComponent();
    }

    public AppComponent initAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.i(TAG, "Terminate");
    }
}
