package my.tinygallery.main.view;


import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleTagStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface IActivityMvpView extends MvpView {

    @StateStrategyType(value = AddToEndSingleTagStrategy.class)
    void changeActivity(String url);
}
