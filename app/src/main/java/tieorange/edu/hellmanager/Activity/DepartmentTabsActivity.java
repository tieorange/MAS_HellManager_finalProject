package tieorange.edu.hellmanager.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.realm.Realm;
import tieorange.edu.hellmanager.Entities.TortureDepartmentEntity;
import tieorange.edu.hellmanager.Fragments.PunishmentToolsFragment;
import tieorange.edu.hellmanager.Fragments.SinnersFragment;
import tieorange.edu.hellmanager.HellManagerApplication;
import tieorange.edu.hellmanager.R;

public class DepartmentTabsActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "ID";
    private static final String TAG = DepartmentTabsActivity.class.getCanonicalName();
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public TortureDepartmentEntity mDepartment;
    public Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_tabs);
        mRealm = HellManagerApplication.mRealm;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int currentTabIndex = mViewPager.getCurrentItem();
                switch (currentTabIndex) {
                    case 0:
                        Intent intentTool = new Intent(DepartmentTabsActivity.this, AddPunishmentToolActivity.class);
                        intentTool.putExtra(AddPunishmentToolActivity.EXTRA_ID, mDepartment.id);
                        startActivity(intentTool);
                        break;
                    case 1:
                        Intent intentSinner = new Intent(DepartmentTabsActivity.this, AddSinnerActivity.class);
                        intentSinner.putExtra(AddSinnerActivity.EXTRA_ID, mDepartment.id);
                        startActivity(intentSinner);
                        break;
                }
            }
        });

        getExtras();
    }

    private void getExtras() {
        final Intent intent = getIntent();
        final String id = intent.getStringExtra(EXTRA_ID);
        if (id == null) return;

        mDepartment = mRealm.where(TortureDepartmentEntity.class).equalTo("id", id).findFirst();
        setTitle(mDepartment.name);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_department_tabs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return PunishmentToolsFragment.newInstance("", "");
                case 1:
                    return SinnersFragment.newInstance("", "");
                default:
                    return PunishmentToolsFragment.newInstance("", "");
            }
            // getItem is called to instantiate the fragment for the given page.
            // Return a PunishmentToolsFragment (defined as a static inner class below).
//            return PunishmentToolsFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Tools";
                case 1:
                    return "Sinners";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
