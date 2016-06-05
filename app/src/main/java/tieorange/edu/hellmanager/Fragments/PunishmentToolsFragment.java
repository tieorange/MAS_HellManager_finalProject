package tieorange.edu.hellmanager.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import tieorange.edu.hellmanager.Activity.DepartmentTabsActivity;
import tieorange.edu.hellmanager.Entities.PunishmentToolEntity;
import tieorange.edu.hellmanager.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PunishmentToolsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PunishmentToolsFragment extends SuperListFragment {
    @Bind(R.id.listView)
    ListView mUiListView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RealmResults<PunishmentToolEntity> mList;
    private DepartmentTabsActivity mActivity;
    private ArrayAdapter mAdapter;

    public PunishmentToolsFragment() {
        // Required empty public constructor
    }

    public static PunishmentToolsFragment newInstance(String param1, String param2) {
        PunishmentToolsFragment fragment = new PunishmentToolsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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

    private RealmResults<PunishmentToolEntity> getList() {
        final String depId = mActivity.mDepartment.id;
        final RealmResults<PunishmentToolEntity> all = mActivity.mRealm
                .where(PunishmentToolEntity.class)
                .equalTo("tortureDepartment.id", depId)
                .findAll();
        return all;
    }

}
