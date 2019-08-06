package my.tinygallery.main.presenter;

import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import my.tinygallery.main.view.IActivityMvpView;
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

    public void setRecyclerAdapter(IUpdateRecyclerAdapter adapter) {
        this.recyclerAdapter = adapter;

        Log.i(TAG, "Set recyclerAdapter");
        recyclerAdapter.updateRecycler();
    }

    @Override
    public void onGetList() {
        hitList = model.getHitList();
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
