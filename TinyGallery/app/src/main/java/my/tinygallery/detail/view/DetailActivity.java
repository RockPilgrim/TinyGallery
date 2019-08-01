package my.tinygallery.detail.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import my.tinygallery.R;
import my.tinygallery.MainActivity;
import my.tinygallery.detail.presenter.DetailPresenter;

public class DetailActivity extends MvpAppCompatActivity implements IMvpDetailView{


    @BindView(R.id.detail_imageView)
    public ImageView detailImageView;


    @InjectPresenter
    DetailPresenter presenter;


    private String imageUrl;

    @ProvidePresenter
    public DetailPresenter providePresenter() {
        return new DetailPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        imageUrl = intent.getStringExtra(MainActivity.EXTRA_SENT_URL);
    }

    @Override
    public void setImage() {
        Picasso.get().load(imageUrl).into(detailImageView);
    }
}
