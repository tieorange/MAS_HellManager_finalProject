package tieorange.edu.hellmanager.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import tieorange.edu.hellmanager.Entities.SinnerEntity;
import tieorange.edu.hellmanager.Entities.SufferingProcessEntity;
import tieorange.edu.hellmanager.Entities.TortureDepartmentEntity;
import tieorange.edu.hellmanager.HellManagerApplication;
import tieorange.edu.hellmanager.R;

public class AddSinnerActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "ID";

    @Bind(R.id.first_name)
    public EditText mUiFirstName;

    @Bind(R.id.last_name)
    public EditText mUiLastName;

    @Bind(R.id.is_liar)
    public Switch mUiIsLiar;

    @Bind(R.id.amount_of_lies)
    public EditText mUiLies;

    @Bind(R.id.is_murderer)
    public Switch mUiIsMurderer;

    @Bind(R.id.amount_of_victims)
    public EditText mUiVictims;

    @Bind(R.id.birth_date)
    public DatePicker mUiBirthDate;

    @Bind(R.id.start_date)
    public DatePicker mUiStartDate;

    @Bind(R.id.finish_date)
    public DatePicker mUiFinishDate;
    private TortureDepartmentEntity mDepartment;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinner);
        ButterKnife.bind(this);
        mRealm = HellManagerApplication.mRealm;
        getExtras();

        initSwitches();

    }

    private void getExtras() {
        Intent intent = getIntent();
        final String id = intent.getStringExtra(EXTRA_ID);
        mDepartment = mRealm.where(TortureDepartmentEntity.class).equalTo("id", id).findFirst();
    }

    @OnClick(R.id.add)
    public void onClickAdd() {
        String firstName = mUiFirstName.getText().toString();
        String lastName = mUiLastName.getText().toString();

        int amountOfLies = 0;
        try {
            amountOfLies = Integer.parseInt(mUiLies.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        int amountOfVictims = 0;
        try {
            amountOfVictims = Integer.parseInt(mUiVictims.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Date birthDate = getDateFromDatePicket(mUiBirthDate);
        Date startDate = getDateFromDatePicket(mUiStartDate);
        Date finishDate = getDateFromDatePicket(mUiFinishDate);

        // sinnerEntity
        SinnerEntity sinnerEntity = new SinnerEntity();
        sinnerEntity.setFirstName(firstName);
        sinnerEntity.setLastName(lastName);
        sinnerEntity.setBirthDate(birthDate);
        if (mUiIsLiar.isChecked()) {
            sinnerEntity.setLiar(true);
            sinnerEntity.setAmountOfLies(amountOfLies);
        }
        if (mUiIsMurderer.isChecked()) {
            sinnerEntity.setMurderer(true);
            sinnerEntity.setAmountOfVictims(amountOfVictims);
        }

        // SufferingProcess
        SufferingProcessEntity sufferingProcessEntity = new SufferingProcessEntity();
        sufferingProcessEntity.setStartDate(startDate);
        sufferingProcessEntity.setFinishDate(finishDate);
        sufferingProcessEntity.setSinner(sinnerEntity);
        sufferingProcessEntity.setTortureDepartment(mDepartment);

        // Dep
        mRealm.beginTransaction();
        sinnerEntity.getSufferingProcessList().add(sufferingProcessEntity);

        sufferingProcessEntity.setTortureDepartment(mDepartment);
        sufferingProcessEntity.setSinner(sinnerEntity);

        mDepartment.sufferingProcessesList.add(sufferingProcessEntity);

        mRealm.copyToRealmOrUpdate(sinnerEntity);
        mRealm.copyToRealmOrUpdate(sufferingProcessEntity);
        mRealm.copyToRealmOrUpdate(mDepartment);
        mRealm.commitTransaction();

        Toast.makeText(AddSinnerActivity.this, firstName + " added", Toast.LENGTH_SHORT).show();
        finish();
    }


    private void initSwitches() {
        mUiLies.setVisibility(View.GONE);
        mUiVictims.setVisibility(View.GONE);

        mUiIsLiar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mUiLies.setVisibility(View.VISIBLE);
                else
                    mUiLies.setVisibility(View.GONE);
            }
        });

        mUiIsMurderer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mUiVictims.setVisibility(View.VISIBLE);
                else
                    mUiVictims.setVisibility(View.GONE);
            }
        });
    }

    public static java.util.Date getDateFromDatePicket(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }


}
