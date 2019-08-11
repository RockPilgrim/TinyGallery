package my.tinygallery.main.view;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import my.tinygallery.R;
import my.tinygallery.main.presenter.IGetImage;

public class MainRecyclerHolder extends RecyclerView.ViewHolder implements IPictureSettings {

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

    @OnClick(R.id.like_button)
    public void onCheckLike() {
        if (likeButton.isChecked())
            presenter.addFavorite(position);
        else
            presenter.deleteFavorite(position);

    }

    public void bind(int position, IGetImage presenter) {
        Log.i(TAG, "Holder " + position + " " + getAdapterPosition());
        this.position = position;
        this.presenter = presenter;
        likeButton.setChecked(presenter.isCheck(position));
        presenter.getImage(position, this);
    }


    @Override
    public void setImage(String url) {
        Picasso.get().load(url).into(imageView);
    }

    @Override
    public void setCheck(boolean heart) {
        likeButton.setChecked(heart);
    }
}
