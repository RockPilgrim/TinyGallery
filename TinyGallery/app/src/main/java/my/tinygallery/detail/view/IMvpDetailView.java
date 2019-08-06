package my.tinygallery.detail.view;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface IMvpDetailView extends MvpView {

    @StateStrategyType(value = SkipStrategy.class)
    void setImage(String url);

}
