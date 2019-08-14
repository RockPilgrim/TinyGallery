package my.tinygallery.main.view;

import java.util.ArrayList;

import my.tinygallery.model.Hit;

public interface IUpdateRecyclerAdapter {

    void updateRecycler();
    void updateRecycler(ArrayList<Hit> oldHitList,ArrayList<Hit> newHitList);
}
