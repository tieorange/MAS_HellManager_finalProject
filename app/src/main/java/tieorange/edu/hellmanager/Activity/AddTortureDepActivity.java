package tieorange.edu.hellmanager.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import tieorange.edu.hellmanager.Entities.TortureDepartmentEntity;
import tieorange.edu.hellmanager.HellManagerApplication;
import tieorange.edu.hellmanager.R;

public class AddTortureDepActivity extends AppCompatActivity {

    @Bind(R.id.name)
    public EditText mUiName;
    @Bind(R.id.add)
    public Button mUiAdd;
    public Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_torture_dep);
        ButterKnife.bind(this);
        mRealm = HellManagerApplication.mRealm;
    }

    @OnClick(R.id.add)
    public void onClickAdd() {
        final String name = mUiName.getText().toString();

        TortureDepartmentEntity entity = new TortureDepartmentEntity();
        entity.name = name;
//        entity.id = UUID.randomUUID().toString();

        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(entity);
        mRealm.commitTransaction();

        Toast.makeText(AddTortureDepActivity.this, name + " added", Toast.LENGTH_SHORT).show();
        finish();
    }
}
