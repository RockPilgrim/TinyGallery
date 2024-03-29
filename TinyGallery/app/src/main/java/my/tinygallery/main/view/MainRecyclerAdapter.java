package my.tinygallery.main.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

import my.tinygallery.R;
import my.tinygallery.main.presenter.DiffHitCallback;
import my.tinygallery.main.presenter.IPresenterForRecycler;
import my.tinygallery.model.Hit;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerHolder> implements IUpdateRecyclerAdapter {


    public static final String TAG = "Recycler Adapter";
    private IPresenterForRecycler presenter;


    public MainRecyclerAdapter(IPresenterForRecycler presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MainRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new MainRecyclerHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerHolder holder, int position) {
        holder.bind(position, presenter);
    }

    @Override
    public int getItemCount() {
        return presenter.getImageCount();
    }

    @Override
    public void updateRecycler() {
        Log.i(TAG, "Update");
        notifyDataSetChanged();
    }

    @Override
    public void updateRecycler(ArrayList<Hit> oldHitList,ArrayList<Hit> newHitList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffHitCallback(oldHitList,newHitList));
        diffResult.dispatchUpdatesTo(this);
        Log.i(TAG, "Update");
        notifyDataSetChanged();
    }
}


