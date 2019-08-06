package my.tinygallery.model.app;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import my.tinygallery.model.Model;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Model provideModel() {
        return new Model();
    }
}
