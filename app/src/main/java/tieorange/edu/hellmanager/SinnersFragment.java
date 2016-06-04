package tieorange.edu.hellmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import tieorange.edu.hellmanager.Entities.SinnerEntity;
import tieorange.edu.hellmanager.Entities.SufferingProcessEntity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SinnersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SinnersFragment extends SuperListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DepartmentTabsActivity mActivity;
    private List<SinnerEntity> mList;
    private ArrayAdapter mAdapter;
    @Bind(R.id.listView)
    public ListView mUiListView;


    public SinnersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_with_list, container, false);
        ButterKnife.bind(this, view);
        mActivity = (DepartmentTabsActivity) getActivity();

        setupListView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupListView();
    }

    private void setupListView() {
        mList = getList();
        mAdapter = new ArrayAdapter(mActivity, R.layout.item_department, R.id.name, mList);
        mUiListView.setAdapter(mAdapter);
    }

    private List<SinnerEntity> getList() {
        List<SinnerEntity> results = new ArrayList<>();

        final String depId = mActivity.mDepartment.id;
        final RealmResults<SufferingProcessEntity> allSufferingProcesses = mActivity.mRealm
                .where(SufferingProcessEntity.class)
                .equalTo("tortureDepartment.id", depId)
                .findAll();

        for (SufferingProcessEntity process : allSufferingProcesses) {
            final SinnerEntity sinner = process.getSinner();
            results.add(sinner);
        }

        return results;
    }

}
