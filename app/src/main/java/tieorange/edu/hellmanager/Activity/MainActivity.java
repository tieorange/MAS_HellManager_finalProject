package tieorange.edu.hellmanager.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemLongClick;
import io.realm.Realm;
import io.realm.RealmResults;
import tieorange.edu.hellmanager.Activity.ui.OnItemRemovedFromRealm;
import tieorange.edu.hellmanager.Entities.TortureDepartmentEntity;
import tieorange.edu.hellmanager.HellManagerApplication;
import tieorange.edu.hellmanager.R;
import tieorange.edu.hellmanager.Tools;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.listView)
    public ListView mUiListView;
    private Realm mRealm;
    private ArrayAdapter mAdapter;
    private RealmResults<TortureDepartmentEntity> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRealm = HellManagerApplication.mRealm;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTortureDepActivity.class);
                startActivity(intent);

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        setupListView();
    }

    private void setupListView() {
        mList = getDepsList();
        mAdapter = new ArrayAdapter(this, R.layout.item_department, R.id.name, mList);
        mUiListView.setAdapter(mAdapter);
        mUiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TortureDepartmentEntity selectedDep = mList.get(position);

                Intent intent = new Intent(MainActivity.this, DepartmentTabsActivity.class);
                intent.putExtra(DepartmentTabsActivity.EXTRA_ID, selectedDep.id);
                startActivity(intent);
            }
        });

    }

    @OnItemLongClick(R.id.listView)
    public boolean onItemLongClick(int position) {
        final TortureDepartmentEntity entity = mList.get(position);
        Tools.onListItemSelect(this, mRealm, entity, new OnItemRemovedFromRealm() {
            @Override
            public void onItemRemoved() {
                setupListView();
            }
        });

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_populate_dummy_data:
                Tools.populateDummyData(mRealm);
                setupListView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
