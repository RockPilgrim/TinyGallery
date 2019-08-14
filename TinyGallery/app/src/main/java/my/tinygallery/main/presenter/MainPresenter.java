package my.tinygallery.main.presenter;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import my.tinygallery.main.view.IActivityMvpView;
import my.tinygallery.main.view.IPictureSettings;
import my.tinygallery.main.view.IUpdateRecyclerAdapter;
import my.tinygallery.model.Hit;
import my.tinygallery.model.Model;
import my.tinygallery.model.app.App;

@InjectViewState
public class MainPresenter extends MvpPresenter<IActivityMvpView> implements IPresenterForRecycler, IPresenterModelChange {

    public static final String TAG = "MainPresenter";
    @Inject
    public Model model;

    private List<Hit> hitList;

    private IUpdateRecyclerAdapter recyclerAdapter;

    public MainPresenter() {
        Log.i(TAG, "Created");
        App.getAppComponent().inject(this);
        model.setPresenter(this);
        hitList = model.getHitList();
    }

    //// Model
    public void setRecyclerAdapter(IUpdateRecyclerAdapter adapter) {
        this.recyclerAdapter = adapter;

        Log.i(TAG, "Set recyclerAdapter");
        recyclerAdapter.updateRecycler();
    }

    ////// RecyclerHolder method
    @Override
    public void getImage(int position, IPictureSettings settings) {
        settings.setImage(hitList.get(position).getPreviewURL());
        settings.setCheck(hitList.get(position).isFavorite());
    }

    ////// RecyclerHolder method
    @Override
    public void onImageClick(int position) {
        getViewState().changeActivity(position);
        Log.i(TAG, "OnClick: " + (position + 1)/*+ " "+hitList.get(position).getUrl()*/);
    }

    ////// RecyclerHolder method to Model
    @Override
    public void addFavorite(int position) {
        Disposable disposable = model.addPhoto(position).observeOn(AndroidSchedulers.mainThread()).subscribe((s, throwable) -> {
            Log.i(TAG, s);
        });
    }

    ////// RecyclerHolder method to Model
    @Override
    public void deleteFavorite(int position) {
        Log.i(TAG, "delete");
        Disposable disposable = model.deletePhoto(position).observeOn(AndroidSchedulers.mainThread()).subscribe((s, throwable) -> {
            Log.i(TAG, s);
        });
    }

    @Override
    public boolean isCheck(int position) {
        return model.getHitList().get(position).isFavorite();
    }

    ///// RecyclerAdapter method
    @Override
    public synchronized void updateList() {
        hitList = model.getHitList();
        int p = 0;
        try {
            for (int i = 0; i < hitList.size(); i++) {
                if (hitList.get(i).isFavorite()) {
                    Collections.swap(hitList, i, p);
                    p++;
                }
            }
            recyclerAdapter.updateRecycler();
        } catch (NullPointerException e) {
            Log.e(TAG, "updateList", e);
        }
    }

    ///// RecyclerAdapter method
    @Override
    public int getImageCount() {
        if (hitList != null)
            return hitList.size();
        return 0;
    }
}
