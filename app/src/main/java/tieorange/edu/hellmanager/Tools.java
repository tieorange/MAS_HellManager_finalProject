package tieorange.edu.hellmanager;

import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;

import java.lang.reflect.Method;

import io.realm.Realm;
import io.realm.RealmObject;
import tieorange.edu.hellmanager.Activity.DepartmentTabsActivity;
import tieorange.edu.hellmanager.Entities.PunishmentToolEntity;
import tieorange.edu.hellmanager.Fragments.PunishmentToolsFragment;
import tieorange.edu.hellmanager.R;

/**
 * Created by tieorange on 06/06/16.
 */
public class Tools {
    public static void onListItemSelect(final Activity mActivity, final Realm realm, final RealmObject realmObject, final OnItemRemovedFromRealm onItemRemovedFromRealm) {
        BottomSheetListener myListener = new BottomSheetListener() {

            @Override
            public void onSheetItemSelected(MenuItem menuItem) {
                final int itemId = menuItem.getItemId();
                switch (itemId) {
                    case R.id.action_remove:
                        remove(realm, realmObject);
                        onItemRemovedFromRealm.onItemRemoved();
                        Toast.makeText(mActivity, "Item is removed", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                }
            }

            @Override
            public void onSheetShown() {

            }

            @Override
            public void onSheetDismissed(int i) {

            }
        };

        new BottomSheet.Builder(mActivity)
                .setSheet(R.menu.menu_bottom_sheet)
                .setTitle("Options")
                .setListener(myListener)
                .show();
    }

    private static void remove(Realm realm, final RealmObject realmObject) {
        realm.beginTransaction();
        realmObject.deleteFromRealm();
        realm.commitTransaction();
    }
}
