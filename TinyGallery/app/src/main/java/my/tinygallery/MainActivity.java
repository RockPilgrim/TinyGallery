package my.tinygallery;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import my.tinygallery.main.presenter.MainPresenter;
import my.tinygallery.detail.view.DetailActivity;
import my.tinygallery.main.view.IActivityMvpView;
import my.tinygallery.main.view.mRecyclerAdapter;

public class MainActivity extends MvpAppCompatActivity implements IActivityMvpView {


    public static final String EXTRA_SENT_URL = "Send url to activity";
    @BindView(R.id.m_recyclerView)
    public RecyclerView recyclerView;

    private mRecyclerAdapter adapter;


    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initUI();
    }

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return new MainPresenter();
    }

    private void initUI() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new mRecyclerAdapter(presenter);
        recyclerView.setAdapter(adapter);
        presenter.setRecyclerAdapter(adapter);
    }

    @Override
    public void changeActivity(String url) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_SENT_URL, url);
        startActivity(intent);
    }
}
