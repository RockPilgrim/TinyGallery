package my.tinygallery.detail.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import my.tinygallery.R;
import my.tinygallery.detail.presenter.DetailPresenter;
import my.tinygallery.main.view.MainActivity;

public class DetailActivity extends MvpAppCompatActivity implements IMvpDetailView {

    public static final String TAG = "DetailActivity";
    @BindView(R.id.detail_imageView)
    public ImageView detailImageView;

    @InjectPresenter
    DetailPresenter presenter;

    private int position;

    @ProvidePresenter
    public DetailPresenter providePresenter() {
        return new DetailPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Log.i(TAG, "onCreate");
        ButterKnife.bind(this);
        Intent intent = getIntent();
        position = intent.getIntExtra(MainActivity.EXTRA_SENT_POSITION, 0);
        presenter.setPosition(position);
    }



    @Override
    public void setImage(String url) {
        Picasso.get().load(url).into(detailImageView);
    }
}
