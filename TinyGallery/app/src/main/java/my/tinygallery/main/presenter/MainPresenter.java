package my.tinygallery.main.presenter;

import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import my.tinygallery.data.Hit;
import my.tinygallery.data.ModelServer;
import my.tinygallery.data.Photo;
import my.tinygallery.main.view.IActivityMvpView;
import my.tinygallery.main.view.IUpdateRecyclerAdapter;

@InjectViewState
public class MainPresenter extends MvpPresenter<IActivityMvpView> implements IPresenterForRecycler {

    public static final String TAG = "MainPresenter";

    private List<Hit> hitList;

    private ModelServer server;

    private IUpdateRecyclerAdapter recyclerAdapter;

    public MainPresenter() {
        server = new ModelServer();
    }

    public void setRecyclerAdapter(IUpdateRecyclerAdapter adapter) {
        this.recyclerAdapter = adapter;

        Observable<Photo> observable = server.requestToServer();

        Disposable disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribe(photo -> {
            hitList = photo.getHitList();
            recyclerAdapter.updateRecycler();
        }, throwable -> {
            Log.e(TAG, "connection problem",throwable);
        });
    }

    @Override
    public void getImage(int position, ImageView imageView) {
        Picasso.get()
                .load(hitList.get(position).getUrl())
                .into(imageView);
        Log.i(TAG, "getImage");
    }

    @Override
    public void onImageClick(int position) {
        getViewState().changeActivity(hitList.get(position).getUrl());
        Log.i(TAG, "OnClick: " + (position + 1)+ " "+hitList.get(position).getUrl());

    }

    @Override
    public int getImageCount() {
        if (hitList != null) {
            return 16;
        }
        return 0;
    }

}
