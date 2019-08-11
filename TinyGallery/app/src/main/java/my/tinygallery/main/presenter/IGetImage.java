package my.tinygallery.main.presenter;

import android.widget.ImageView;

import my.tinygallery.main.view.IPictureSettings;

public interface IGetImage {

    void getImage(int position, IPictureSettings settings);

    void onImageClick(int position);

    void addFavorite(int position);

    void deleteFavorite(int position);

    boolean isCheck(int position);
}
