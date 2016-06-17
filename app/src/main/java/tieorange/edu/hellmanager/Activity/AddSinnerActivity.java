package tieorange.edu.hellmanager.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.greenfrvr.hashtagview.HashtagView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import tieorange.edu.hellmanager.Entities.DeadlySin;
import tieorange.edu.hellmanager.Entities.SinnerEntity;
import tieorange.edu.hellmanager.Entities.SufferingProcessEntity;
import tieorange.edu.hellmanager.Entities.TortureDepartmentEntity;
import tieorange.edu.hellmanager.HellManagerApplication;
import tieorange.edu.hellmanager.R;
import tieorange.edu.hellmanager.main.MyUtils.MyTools;

public class AddSinnerActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_READ_ONLY = "READ_ONLY";
    private static final String TAG = AddSinnerActivity.class.getCanonicalName();
    private static final int MINIMAL_PROCESS_DURATION = 1000;

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
    @Bind(R.id.sinsHashtags)
    public HashtagView mUiSinsHashtags;
    @Bind(R.id.finish_date)
    public DatePicker mUiFinishDate;
    @Bind(R.id.add)
    public Button mUiAddButton;

    private TortureDepartmentEntity mDepartment;
    private Realm mRealm;
    private List<DeadlySin> mSinsList = new ArrayList<>();
    private boolean mIsReadOnly = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinner);
        ButterKnife.bind(this);
        mRealm = HellManagerApplication.mRealm;
        getExtras();

        initSwitches();
        initDatePickers();

        initHashtags();

        checkIfReadOnly();
    }

    private void checkIfReadOnly() {
        Log.d(TAG, "checkIfReadOnly() called with: " + "readonly = " + mIsReadOnly);
        if (!mIsReadOnly) return;

        mUiFirstName.setFocusable(false);
        mUiLastName.setFocusable(false);

        mUiIsLiar.setEnabled(false);
        mUiIsMurderer.setEnabled(false);
        mUiSinsHashtags.setInSelectMode(false);
        mUiBirthDate.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
        mUiStartDate.setFocusable(false);
        mUiFinishDate.setEnabled(false);
        mUiAddButton.setVisibility(View.GONE);
    }

    private void initHashtags() {
        List<DeadlySin> sinsList = DeadlySin.getSevenDeadlySins();

        mUiSinsHashtags.setData(sinsList, new HashtagView.DataTransform<DeadlySin>() {
            @Override
            public CharSequence prepare(DeadlySin item) {
                return item.getName();
            }
        });


        mUiSinsHashtags.addOnTagSelectListener(new HashtagView.TagsSelectListener() {
            @Override
            public void onItemSelected(Object item, boolean selected) {
                Log.d(TAG, "onItemSelected() called with: " + "item = [" + item + "], selected = [" + selected + "]");
                DeadlySin sin = (DeadlySin) item;
                if (selected) {
                    mSinsList.add(sin);
                } else {
                    if (mSinsList.contains(sin))
                        mSinsList.remove(sin);
                }
            }
        });
    }

    private void initDatePickers() {
        final Date startDateExample = MyTools.getStartDateExample();
        final Date endDateExample = MyTools.getEndDateExample();

        mUiStartDate.updateDate(startDateExample.getYear(), startDateExample.getMonth(), startDateExample.getDay());
        mUiFinishDate.updateDate(endDateExample.getYear(), endDateExample.getMonth(), endDateExample.getDay());
    }

    private void getExtras() {
        Intent intent = getIntent();
        final String id = intent.getStringExtra(EXTRA_ID);
        mIsReadOnly = intent.getBooleanExtra(EXTRA_READ_ONLY, false);
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
        SinnerEntity sinnerEntity = buildSinnerEntity(firstName, lastName, amountOfLies, amountOfVictims, birthDate);
        SufferingProcessEntity sufferingProcessEntity = buildSufferingProcessEntity(startDate, finishDate, sinnerEntity);

        // validation:
        if (!ifBurnedLongEnough(startDate, finishDate)) {
            Toast.makeText(AddSinnerActivity.this, "suffering process must be longer than 1000 years", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isValidNames()) {
            Toast.makeText(AddSinnerActivity.this, "fill all text fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isValidSins()){
            Toast.makeText(AddSinnerActivity.this, "select min 1 Sin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Dep
        mRealm.beginTransaction();
        sinnerEntity.getSufferingProcessList().add(sufferingProcessEntity);
        sinnerEntity.getSinsList().addAll(mSinsList);

        sufferingProcessEntity.setTortureDepartment(mDepartment);
        sufferingProcessEntity.setSinner(sinnerEntity);

        mDepartment.sufferingProcessesList.add(sufferingProcessEntity);

        mRealm.copyToRealmOrUpdate(sinnerEntity);
        mRealm.copyToRealmOrUpdate(sufferingProcessEntity);
        mRealm.copyToRealmOrUpdate(mDepartment);
        mRealm.commitTransaction();

        Toast.makeText(AddSinnerActivity.this, firstName + " is burning very well \uD83D\uDE08", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean isValidSins() {
        return mSinsList.size() > 0;
    }

    private boolean ifBurnedLongEnough(Date starDate, Date finishDate) {
        int diffYears = MyTools.getDiffYears(starDate, finishDate);
        return diffYears >= MINIMAL_PROCESS_DURATION;
    }

    private boolean isValidNames() {
        boolean isValid = true;
        if (mUiFirstName.getText().length() < 1 || mUiLastName.getText().length() < 1) {
            isValid = false;
        }
        return isValid;
    }

    @NonNull
    private SufferingProcessEntity buildSufferingProcessEntity(Date startDate, Date finishDate, SinnerEntity sinnerEntity) {
        // SufferingProcess
        SufferingProcessEntity sufferingProcessEntity = new SufferingProcessEntity();
        sufferingProcessEntity.setStartDate(startDate);
        sufferingProcessEntity.setFinishDate(finishDate);
        sufferingProcessEntity.setSinner(sinnerEntity);
        sufferingProcessEntity.setTortureDepartment(mDepartment);
        return sufferingProcessEntity;
    }

    @NonNull
    private SinnerEntity buildSinnerEntity(String firstName, String lastName, int amountOfLies, int amountOfVictims, Date birthDate) {
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
        return sinnerEntity;
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
