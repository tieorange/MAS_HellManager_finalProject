package tieorange.edu.hellmanager.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.greenfrvr.hashtagview.HashtagView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;
import tieorange.edu.hellmanager.Entities.DeadlySin;
import tieorange.edu.hellmanager.Entities.SinnerEntity;
import tieorange.edu.hellmanager.R;

public class SinnerActivity extends AppCompatActivity {
    public static final String EXTRA_SINNER_ID = "sinner id";
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
    @Bind(R.id.sins)
    public TextView mUiSins;

    @Bind(R.id.birth_date)
    public TextView mUiBirthDate;
    @Bind(R.id.start_date)
    public TextView mUiStartDate;
    @Bind(R.id.finish_date)
    public TextView mUiFinishDate;
    private Realm mRealm;
    private SinnerEntity mSinnerEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinner);
        ButterKnife.bind(this);
        mRealm = Realm.getDefaultInstance();

        getExtras();

        initSimpleViews();
        initSins();
    }

    private void initSins() {
        StringBuilder stringBuilder = new StringBuilder();
        final RealmList<DeadlySin> selectedSinsList = mSinnerEntity.getSinsList();

        for (DeadlySin deadlySin : selectedSinsList) {
            stringBuilder.append(deadlySin + "\n");
        }

        mUiSins.setText(stringBuilder.toString());
    }

    private void getExtras() {
        final String sinnerId = getIntent().getStringExtra(EXTRA_SINNER_ID);
        mSinnerEntity = mRealm.where(SinnerEntity.class).equalTo("id", sinnerId).findFirst();
    }

    private void initSimpleViews() {
        mUiFirstName.setText(mSinnerEntity.getFirstName());
        mUiLastName.setText(mSinnerEntity.getLastName());

        // dates
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String birthDate = dateFormat.format(mSinnerEntity.getBirthDate());
        String startDate = dateFormat.format(mSinnerEntity.getSufferingProcessList().get(0).getStartDate());
        String finishDate = dateFormat.format(mSinnerEntity.getSufferingProcessList().get(0).getFinishDate());

        mUiBirthDate.setText(birthDate);
        mUiStartDate.setText(startDate);
        mUiFinishDate.setText(finishDate);

        if (mSinnerEntity.isLiar()) {
            mUiIsLiar.setChecked(true);
            final String amountOfLies = String.valueOf(mSinnerEntity.getAmountOfLies());
            mUiLies.setText(amountOfLies);
        } else {
            mUiLies.setText("0");
        }

        if (mSinnerEntity.isMurderer()) {
            mUiIsMurderer.setChecked(true);
            final String amountOfVictims = String.valueOf(mSinnerEntity.getAmountOfVictims());
            mUiVictims.setText(amountOfVictims);
        } else {
            mUiVictims.setText("0");
        }
    }

    @Override
    protected void onDestroy() {
        mRealm.close();
        super.onDestroy();
    }
}
