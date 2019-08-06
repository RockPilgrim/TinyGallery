package my.tinygallery.model;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import my.tinygallery.main.presenter.IPresenterModelChange;

public class Model {

    private static final String TAG = "Model Photo";
    private IPresenterModelChange presenter;

    private List<Hit> hitList;


    public Model() {
        Log.i(TAG, "Created");
        requirePhoto();
    }

    public void setPresenter(IPresenterModelChange presenter) {
        this.presenter = presenter;
    }

    private void requirePhoto() {
        Observable<Photo> observable = ServerConnector.requestToServer();

        Disposable disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribe(photo -> {
            hitList = photo.getHitList();
            presenter.onGetList();
            Log.i(TAG, "Server connect");
        }, throwable -> Log.e(TAG, "connection problem", throwable));
    }

    public List<Hit> getHitList() {
        return hitList;
    }
}
