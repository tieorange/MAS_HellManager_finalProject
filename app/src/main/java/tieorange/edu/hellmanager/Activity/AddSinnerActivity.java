package tieorange.edu.hellmanager.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
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
import tieorange.edu.hellmanager.Entities.SinnerEntity;
import tieorange.edu.hellmanager.Entities.SufferingProcessEntity;
import tieorange.edu.hellmanager.Entities.TortureDepartmentEntity;
import tieorange.edu.hellmanager.HellManagerApplication;
import tieorange.edu.hellmanager.R;
import tieorange.edu.hellmanager.main.MyUtils.MyTools;

public class AddSinnerActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "ID";
    private static final String TAG = AddSinnerActivity.class.getCanonicalName();

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
        initDatePickers();

        initHashtags();
    }

    private void initHashtags() {
        List<String> sinsList = new ArrayList<>();
        sinsList.add("Pride");
        sinsList.add("Envy");
        sinsList.add("Gluttony");
        sinsList.add("Anger");
        sinsList.add("Greed");
        sinsList.add("Sloth");
/*
        HashtagView.DataTransform<String> stateTransform = new HashtagView.DataStateTransform<String>() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public CharSequence prepare(String item) {
                String label = item;
                SpannableString spannableString = new SpannableString(label);
//                spannableString.setSpan(new SuperscriptSpan(), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                spannableString.setSpan(new ForegroundColorSpan(getColor(color1)), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                spannableString.setSpan(new BackgroundColorSpan(getColor(color1)), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return spannableString;
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public CharSequence prepareSelected(String item) {
                String label = item;
                SpannableString spannableString = new SpannableString(label);
//                spannableString.setSpan(new SuperscriptSpan(), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                spannableString.setSpan(new ForegroundColorSpan(getColor(color1)), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

//                spannableString.setSpan(new StrikethroughSpan(), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return spannableString;
            }
        };*/

        mUiSinsHashtags.setData(sinsList);


        mUiSinsHashtags.addOnTagSelectListener(new HashtagView.TagsSelectListener() {
            @Override
            public void onItemSelected(Object item, boolean selected) {
                Log.d(TAG, "onItemSelected:");
            }
        });

//        mUiSinsHashtags.setInSelectMode(true);
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
