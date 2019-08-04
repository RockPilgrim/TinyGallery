package my.tinygallery.main.presenter;

import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import my.tinygallery.IPresenterModelChange;
import my.tinygallery.data.Hit;
import my.tinygallery.data.ModelPhoto;
import my.tinygallery.data.ModelServer;
import my.tinygallery.data.Photo;
import my.tinygallery.data.app.App;
import my.tinygallery.main.view.IActivityMvpView;
import my.tinygallery.main.view.IUpdateRecyclerAdapter;

@InjectViewState
public class MainPresenter extends MvpPresenter<IActivityMvpView> implements IPresenterForRecycler, IPresenterModelChange {

    public static final String TAG = "MainPresenter";

    private List<Hit> hitList;

    @Inject
    public ModelPhoto modelPhoto;

    private IUpdateRecyclerAdapter recyclerAdapter;

    public MainPresenter() {
        Log.i(TAG, "Created");
        App.getAppComponent().inject(this);
        modelPhoto.setPresenter(this);
    }

    public void setRecyclerAdapter(IUpdateRecyclerAdapter adapter) {
        this.recyclerAdapter = adapter;

        Log.i(TAG, "Get list");
        recyclerAdapter.updateRecycler();
    }

    @Override
    public void onGetList() {
        hitList = modelPhoto.getHitList();
        recyclerAdapter.updateRecycler();
    }

    @Override
    public void getImage(int position, ImageView imageView) {
        Picasso.get()
                .load(hitList.get(position).getPreviewURL())
                .into(imageView);
        Log.i(TAG, "setPosition");
    }

    @Override
    public void onImageClick(int position) {
        getViewState().changeActivity(position);
        Log.i(TAG, "OnClick: " + (position + 1)/*+ " "+hitList.get(position).getUrl()*/);

    }

    @Override
    public int getImageCount() {
        if (hitList != null)
            return 16;
        return 0;
    }
}
