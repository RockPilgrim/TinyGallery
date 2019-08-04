package my.tinygallery.data.app;

import android.app.Application;

import my.tinygallery.IPresenterModelChange;

public class App extends Application {


    private static AppComponent appComponent;


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

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
