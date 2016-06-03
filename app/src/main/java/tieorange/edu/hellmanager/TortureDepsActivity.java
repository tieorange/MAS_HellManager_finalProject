package tieorange.edu.hellmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import tieorange.edu.hellmanager.Entities.TortureDepartmentEntity;

public class TortureDepsActivity extends AppCompatActivity {
    @Bind(R.id.listView)
    public ListView mUiListView;
    private Realm mRealm;
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torture_dep);
        ButterKnife.bind(this);
        mRealm = HellManagerApplication.mRealm;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TortureDepsActivity.this, AddTortureDepActivity.class);
                startActivity(intent);

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        setupListView();
    }

    private void setupListView() {
        final RealmResults<TortureDepartmentEntity> depsList = getDepsList();
        mAdapter = new ArrayAdapter(this, R.layout.item_department, R.id.name, depsList);
        mUiListView.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setupListView();
    }

    public RealmResults<TortureDepartmentEntity> getDepsList() {
        final RealmResults<TortureDepartmentEntity> all = mRealm.where(TortureDepartmentEntity.class).findAll();
        return all;
    }

}
