package my.tinygallery.data;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ModelPhoto {

    private static final String TAG = "Model Photo";
    private ModelServer server;

    private List<Hit> hitList;

/*    public List<Hit> getHitList() {
        if (hitList != null) {
            return hitList;
        }else
            return requestToServer();
    }*/

/*    private List<Hit> requestToServer() {
        Observable<Photo> observable = server.requestToServer();

        Disposable disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribe(photo -> {
            hitList = photo.getHitList();
//            recyclerAdapter.updateRecycler();
            return photo.getHitList();
        }, throwable -> {
            Log.e(TAG, "connection problem",throwable);
        });
    }*/
}
