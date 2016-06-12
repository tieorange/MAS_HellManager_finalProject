package tieorange.edu.hellmanager;

import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;

import org.fluttercode.datafactory.impl.DataFactory;

import java.lang.reflect.Method;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmObject;
import tieorange.edu.hellmanager.Activity.DepartmentTabsActivity;
import tieorange.edu.hellmanager.Entities.PunishmentToolEntity;
import tieorange.edu.hellmanager.Entities.SinnerEntity;
import tieorange.edu.hellmanager.Entities.TortureDepartmentEntity;
import tieorange.edu.hellmanager.Fragments.PunishmentToolsFragment;
import tieorange.edu.hellmanager.R;
import tieorange.edu.hellmanager.main.Sinners.Sinner;

/**
 * Created by tieorange on 06/06/16.
 */
public class Tools {
    public static DataFactory dataFactory = new DataFactory();

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
        // Deps:
        TortureDepartmentEntity tortureDepartmentEntity = new TortureDepartmentEntity("Boiling room");

        //region Punishment Tools:
        PunishmentToolEntity punishmentToolEntityHummer = new PunishmentToolEntity("Hummer", getRandomInt(), getRandomInt(), tortureDepartmentEntity);
        PunishmentToolEntity punishmentToolEntityFork = new PunishmentToolEntity("Heretics Fork", getRandomInt(), getRandomDouble(), tortureDepartmentEntity);
        PunishmentToolEntity punishmentToolEntityImpalement = new PunishmentToolEntity("Impalement", getRandomInt(), getRandomDouble(), tortureDepartmentEntity);
        PunishmentToolEntity punishmentToolEntityNeckTorture = new PunishmentToolEntity("Neck Torture", getRandomInt(), getRandomInt(), tortureDepartmentEntity);
        PunishmentToolEntity punishmentToolEntitySawTorture = new PunishmentToolEntity("Saw Torture", getRandomInt(), getRandomInt(), tortureDepartmentEntity);
        PunishmentToolEntity punishmentToolEntityChair = new PunishmentToolEntity("Chair of torture", getRandomInt(), getRandomDouble(), tortureDepartmentEntity);
        PunishmentToolEntity punishmentToolEntityRack = new PunishmentToolEntity("Rack", getRandomInt(), getRandomInt(), tortureDepartmentEntity);
        PunishmentToolEntity punishmentToolEntityRackBreast = new PunishmentToolEntity("Breast Ripper", getRandomInt(), getRandomInt(), tortureDepartmentEntity);
        //endregion

        // TODO: 12/06/16  
//        SinnerEntity sinnerEntity1 = new SinnerEntity()

//        tortureDepartmentEntity.id = UUID.randomUUID().toString();

        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(tortureDepartmentEntity);
        mRealm.copyToRealmOrUpdate(punishmentToolEntityHummer);
        mRealm.commitTransaction();
    }

    private static int getRandomInt() {
        int min = 0, max = 100;
        Random random = new Random();
        int randomNum = random.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    private static double getRandomDouble() {
        int min = 0, max = 100;
        Random random = new Random();
        double randomNum = min + (max - min) * random.nextDouble();
        return randomNum;
    }

}
