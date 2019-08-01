package my.tinygallery.main.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import my.tinygallery.main.presenter.IPresenterForRecycler;
import my.tinygallery.R;

public class mRecyclerAdapter extends RecyclerView.Adapter<mRecyclerHolder> implements IUpdateRecyclerAdapter{


    public static final String TAG = "Recycler Adapter";
    private IPresenterForRecycler presenter;


    public mRecyclerAdapter(IPresenterForRecycler presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public mRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new mRecyclerHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull mRecyclerHolder holder, int position) {
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
}
