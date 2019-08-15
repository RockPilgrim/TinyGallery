package my.tinygallery.main.view;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ItemDecoration extends RecyclerView.ItemDecoration {

    private int offset;

    public ItemDecoration(int offset) {
        this.offset = offset;
    }



    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();


        if (layoutParams.getSpanIndex() % 2 == 0) {
            outRect.left= offset;
        }else {
            outRect.right= offset;
            outRect.left = offset/2;
        }
        outRect.top = offset;

//            super.getItemOffsets(outRect, view, parent, state);
    }
}
