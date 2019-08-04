package my.tinygallery.detail.presenter;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import my.tinygallery.IPresenterModelChange;
import my.tinygallery.data.ModelPhoto;
import my.tinygallery.data.app.App;
import my.tinygallery.detail.view.IMvpDetailView;

@InjectViewState
public class DetailPresenter extends MvpPresenter<IMvpDetailView> implements IPresenterModelChange {


    @Inject
    public ModelPhoto modelPhoto;


    public DetailPresenter() {
        App.getAppComponent().inject(this);
        modelPhoto.setPresenter(this);
    }

    public void setPosition(int position) {
        getViewState().setImage(modelPhoto.getHitList().get(position).getUrl());
    }

    @Override
    public void onGetList() {

    }
}
