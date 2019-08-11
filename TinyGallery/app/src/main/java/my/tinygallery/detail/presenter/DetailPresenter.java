package my.tinygallery.detail.presenter;

import android.util.Log;

import java.util.Set;

import javax.inject.Inject;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import my.tinygallery.detail.view.IMvpDetailView;
import my.tinygallery.main.presenter.IPresenterModelChange;
import my.tinygallery.model.Model;
import my.tinygallery.model.app.App;

@InjectViewState
public class DetailPresenter extends MvpPresenter<IMvpDetailView> implements IPresenterModelChange {


    public static final String TAG = "DetailPresenter";

    @Inject
    public Model model;

    private int position;

    @Override
    public Set<IMvpDetailView> getAttachedViews() {
        Log.i(TAG, "Attach");
        getViewState().setImage(model.getHitList().get(position).getUrl());
        return super.getAttachedViews();
    }

    public DetailPresenter() {
        App.getAppComponent().inject(this);
        model.setPresenter(this);
    }

    public void setPosition(int position) {

        this.position = position;
        Log.i(TAG, "setPosition " + position);

//        getViewState().setImage(model.getHitList().get(position).getUrl());
    }

    @Override
    public void updateList() {

    }
}
