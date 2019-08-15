package my.tinygallery.main.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import my.tinygallery.R;
import my.tinygallery.detail.view.DetailActivity;
import my.tinygallery.main.presenter.MainPresenter;

public class MainActivity extends MvpAppCompatActivity implements IActivityMvpView {


    public static final String EXTRA_SENT_POSITION = "Send";
    public static final String TAG = "MainActivity";
    private int span=2;
    private int offset = 40;

    @BindView(R.id.m_recyclerView)
    public RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @InjectPresenter
    MainPresenter presenter;
    private MainRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_button:
                presenter.updateList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return new MainPresenter();
    }

    private void initUI() {
        recyclerView.setLayoutManager(new MyGridLayout(this, span));
        recyclerView.addItemDecoration(new ItemDecoration(offset));
        adapter = new MainRecyclerAdapter(presenter);
        recyclerView.setAdapter(adapter);
        presenter.setRecyclerAdapter(adapter);
    }


    @Override
    public void changeActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_SENT_POSITION, position);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            presenter.updateList();
        } catch (NullPointerException e) {
            Log.e(TAG, "onResume", e);
        }
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }
}
