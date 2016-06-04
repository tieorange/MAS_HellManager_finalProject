package tieorange.edu.hellmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Realm;
import tieorange.edu.hellmanager.Entities.TortureDepartmentEntity;

public class DepartmentActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "ID";
    private Realm mRealm;
    private TortureDepartmentEntity mDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        mRealm = HellManagerApplication.mRealm;


        getExtras();
    }

    private void getExtras() {
        final Intent intent = getIntent();
        final String id = intent.getStringExtra(EXTRA_ID);
        mDepartment = mRealm.where(TortureDepartmentEntity.class).equalTo("id", id).findFirst();
        setTitle(mDepartment.name);
    }


}
