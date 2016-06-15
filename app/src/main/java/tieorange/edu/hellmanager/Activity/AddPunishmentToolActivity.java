package tieorange.edu.hellmanager.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import tieorange.edu.hellmanager.Entities.PunishmentToolEntity;
import tieorange.edu.hellmanager.Entities.TortureDepartmentEntity;
import tieorange.edu.hellmanager.HellManagerApplication;
import tieorange.edu.hellmanager.R;

public class AddPunishmentToolActivity extends AppCompatActivity {

    @Bind(R.id.name)
    public EditText mUiNameEditText;
    @Bind(R.id.damage)
    public EditText mUiDamage;
    @Bind(R.id.radioGroupType)
    public RadioGroup mUiRadioGroup;
    @Bind(R.id.temperature)
    public EditText mUiTemperature;

    public static final String EXTRA_ID = "ID";
    private Realm mRealm;
    private TortureDepartmentEntity mDepartment;
    private boolean mIsFlame = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_punishment_tool);

        ButterKnife.bind(this);
        mRealm = HellManagerApplication.mRealm;

        getExtras();
        mUiRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonFlame:
                        mUiTemperature.setHint("Max temperature");
                        mIsFlame = true;
                        break;
                    case R.id.radioButtonIce:
                        mUiTemperature.setHint("Min temperature");
                        mIsFlame = false;
                        break;
                }
            }
        });
    }

    private void getExtras() {
        Intent intent = getIntent();
        final String id = intent.getStringExtra(EXTRA_ID);
        mDepartment = mRealm.where(TortureDepartmentEntity.class).equalTo("id", id).findFirst();
    }

    @OnClick(R.id.add)
    public void onClickAdd() {
        final String name = mUiNameEditText.getText().toString();
        final int damage = Integer.parseInt(mUiDamage.getText().toString());

        PunishmentToolEntity punishmentToolEntity = new PunishmentToolEntity();
        punishmentToolEntity.tortureDepartment = mDepartment;
        punishmentToolEntity.name = name;
        if (mIsFlame) {
            double maxTemperature = Double.parseDouble(mUiTemperature.getText().toString());
            punishmentToolEntity.maxTemperature = maxTemperature;
        } else {
            int minTemperature = Integer.parseInt(mUiTemperature.getText().toString());
            punishmentToolEntity.minTemperature = minTemperature;
        }

        punishmentToolEntity.damage = damage;

        mRealm.beginTransaction();
        mDepartment.punishmentTools.add(punishmentToolEntity);

        mRealm.copyToRealmOrUpdate(punishmentToolEntity);
        mRealm.copyToRealmOrUpdate(mDepartment);
        mRealm.commitTransaction();

        Toast.makeText(this, name + " added", Toast.LENGTH_SHORT).show();
        finish();
    }
}
