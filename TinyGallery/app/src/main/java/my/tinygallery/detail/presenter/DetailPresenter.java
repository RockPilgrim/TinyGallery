package my.tinygallery.detail.presenter;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import my.tinygallery.detail.view.IMvpDetailView;

@InjectViewState
public class DetailPresenter extends MvpPresenter<IMvpDetailView> {

    public DetailPresenter() {
        getViewState().setImage();
    }
}
