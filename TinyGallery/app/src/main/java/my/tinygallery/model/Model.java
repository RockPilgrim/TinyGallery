package my.tinygallery.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import my.tinygallery.main.presenter.IPresenterModelChange;
import my.tinygallery.model.app.App;
import my.tinygallery.model.database.FavoritePhoto;
import my.tinygallery.model.database.FavoritePhotoDao;

public class Model {

    private static final String TAG = "Model Photo";
    private IPresenterModelChange presenter;

    private List<Hit> hitList;
    private List<Hit> oldList;


    private FavoritePhotoDao photoDao;


    public Model() {
        Log.i(TAG, "Created");
        oldList = new ArrayList<>();
        hitList = new ArrayList<>();
        requirePhoto();
        connectToDb();
    }

    private void connectToDb() {
        photoDao = App.getPhotoDatabase().photoDao();
        Single<List<FavoritePhoto>> observable = photoDao.getAllPhoto();
        Disposable disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(photos -> {
                    for (int i = 0; i < photos.size(); i++) {
                        hitList.add(i, new Hit(photos.get(i).getUrl(), photos.get(i).getUrl(), true));
                    }
                    Log.i(TAG, "Db connect "+Thread.currentThread());
                    presenter.updateList();
                }, throwable -> {
                    Log.e(TAG, "Db exception", throwable);
                });
    }

    private void requirePhoto() {
        Observable<Photo> observable = ServerConnector.requestToServer();
        Disposable disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribe(photo -> {
            hitList.addAll(photo.getHitList());
            presenter.updateList();
            Log.i(TAG, "Server connect "+Thread.currentThread());
        }, throwable -> Log.e(TAG, "connection problem", throwable));
    }

    public Single<String> addPhoto(int index) {
        return Single.create((SingleOnSubscribe<String>) emitter -> {
            FavoritePhoto photo = new FavoritePhoto(hitList.get(index).getUrl(), hitList.get(index).getPreviewURL());
            photoDao.addPhoto(photo);
            emitter.onSuccess("Added photo: " + index);
            hitList.get(index).setFavorite(true);
        }).subscribeOn(Schedulers.io());
    }

    public Single<String> deletePhoto(int position) {
        return Single.create((SingleOnSubscribe<String>) emitter -> {
            photoDao.deleteByUrl(hitList.get(position).getUrl());
            emitter.onSuccess("Deleted");
            hitList.get(position).setFavorite(false);
        }).subscribeOn(Schedulers.io());
    }

    public void setPresenter(IPresenterModelChange presenter) {
        this.presenter = presenter;
    }

    public List<Hit> getHitList() {
        return hitList;
    }
}
