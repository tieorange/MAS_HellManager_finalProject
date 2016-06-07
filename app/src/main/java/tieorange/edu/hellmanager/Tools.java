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
import tieorange.edu.hellmanager.Entities.TortureDepartmentEntity;
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

    public static void populateDummyData(Realm mRealm) {
      /*  mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Dep:
                TortureDepartmentEntity tortureDepartmentEntity = new TortureDepartmentEntity();
                tortureDepartmentEntity.name = "Boiling room";

                // Tool
                PunishmentToolEntity punishmentToolEntity = new PunishmentToolEntity();
                punishmentToolEntity.name = "Hummer";
                punishmentToolEntity.damage = 30;
                punishmentToolEntity.minTemperature = 2;
                punishmentToolEntity.tortureDepartment = tortureDepartmentEntity;
            }
        });*/

        TortureDepartmentEntity entity = new TortureDepartmentEntity();
        entity.name = "Boiling room";
//        entity.id = UUID.randomUUID().toString();

        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(entity);
        mRealm.commitTransaction();
    }
}
