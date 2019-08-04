package my.tinygallery.data.app;


import javax.inject.Singleton;

import dagger.Component;
import my.tinygallery.detail.presenter.DetailPresenter;
import my.tinygallery.main.presenter.MainPresenter;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

    void inject(MainPresenter presenter);
    void inject(DetailPresenter presenter);

}
