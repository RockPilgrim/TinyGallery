package my.tinygallery.main.presenter;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import my.tinygallery.model.Hit;

public class DiffHitCallback extends DiffUtil.Callback {

    private List<Hit> oldHitList;
    private List<Hit> newHitList;

    public DiffHitCallback(List<Hit> oldHitList, List<Hit> newHitList) {
        this.oldHitList = oldHitList;
        this.newHitList = newHitList;
    }

    @Override

    public int getOldListSize() {
        return oldHitList.size();
    }

    @Override
    public int getNewListSize() {
        return newHitList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldHitList.get(oldItemPosition).getUrl().equals(newHitList.get(newItemPosition).getUrl());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldHitList.get(oldItemPosition).equals(newHitList.get(newItemPosition));
    }


}
