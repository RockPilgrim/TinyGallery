package my.tinygallery.main.view;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyGridLayout extends GridLayoutManager {

    public MyGridLayout(Context context, int spanCount) {
        super(context, spanCount);


    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        if (getOrientation() == RecyclerView.VERTICAL) {
            setSpanCount(2);
        }else
            setSpanCount(4);
    }
}
