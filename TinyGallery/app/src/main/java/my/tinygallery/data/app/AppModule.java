package my.tinygallery.data.app;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import my.tinygallery.IPresenterModelChange;
import my.tinygallery.data.ModelPhoto;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    ModelPhoto provideModelPhoto() {
        return new ModelPhoto();
    }
}
