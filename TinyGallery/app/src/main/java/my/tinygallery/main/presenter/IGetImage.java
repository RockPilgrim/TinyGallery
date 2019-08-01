package my.tinygallery.main.presenter;

import android.widget.ImageView;

public interface IGetImage {

    void getImage(int position, ImageView imageView);

    void onImageClick(int position);
}
