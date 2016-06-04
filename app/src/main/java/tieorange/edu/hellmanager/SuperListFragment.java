package tieorange.edu.hellmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by tieorange on 04/06/16.
 */
public class SuperListFragment extends Fragment {

    public static SinnersFragment newInstance(String param1, String param2) {
        SinnersFragment fragment = new SinnersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
