package my.tinygallery.main.view;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import my.tinygallery.R;
import my.tinygallery.main.presenter.IGetImage;

public class MainRecyclerHolder extends RecyclerView.ViewHolder {

    public static final String TAG = "MainRecyclerHolder";
    @BindView(R.id.preview_imageView)
    public ImageView imageView;

    @BindView(R.id.like_button)
    public ToggleButton likeButton;

    private int position;

    private IGetImage presenter;

    public MainRecyclerHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.preview_imageView)
    public void onImageClick() {
        presenter.onImageClick(position);
    }

    @OnCheckedChanged(R.id.like_button)
    public void onCheckLike() {
        if (likeButton.isChecked()) {
            Log.i(TAG, "Checked on " + position);
        } else {
            Log.i(TAG, "Checked off " + position);
        }
    }

    public void bind(int position, IGetImage presenter) {
        this.position = position;
        this.presenter = presenter;
        presenter.getImage(position, imageView);
    }
}
