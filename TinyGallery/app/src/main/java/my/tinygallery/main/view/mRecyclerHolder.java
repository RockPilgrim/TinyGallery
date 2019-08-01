package my.tinygallery.main.view;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import my.tinygallery.main.presenter.IGetImage;
import my.tinygallery.R;

public class mRecyclerHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.preview_imageView)
    public ImageView imageView;

    private int position;

    private IGetImage presenter;

    public mRecyclerHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);
    }

    @OnClick(R.id.preview_imageView)
    public void onImageClick() {
        presenter.onImageClick(position);
    }

    public void bind(int position,IGetImage presenter) {
        this.position = position;
        this.presenter = presenter;
        presenter.getImage(position,imageView);
    }
}
