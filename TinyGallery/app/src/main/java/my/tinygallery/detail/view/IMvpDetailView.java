package my.tinygallery.detail.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleTagStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface IMvpDetailView extends MvpView {

    @StateStrategyType(value = AddToEndSingleTagStrategy.class)
    void setImage();

}
