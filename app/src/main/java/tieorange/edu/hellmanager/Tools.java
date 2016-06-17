package tieorange.edu.hellmanager;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.Toast;

import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;

import org.fluttercode.datafactory.impl.DataFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import tieorange.edu.hellmanager.Activity.ui.OnItemRemovedFromRealm;
import tieorange.edu.hellmanager.Entities.DeadlySin;
import tieorange.edu.hellmanager.Entities.PunishmentToolEntity;
import tieorange.edu.hellmanager.Entities.SinnerEntity;
import tieorange.edu.hellmanager.Entities.SufferingProcessEntity;
import tieorange.edu.hellmanager.Entities.TortureDepartmentEntity;

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
        TortureDepartmentEntity tortureDepartmentEntity666 = new TortureDepartmentEntity("Dep# 666");
        List<PunishmentToolEntity> punishmentToolEntities = getDummyPunishmentToolEntityList(tortureDepartmentEntity);


        // TODO: 12/06/16  
        final List<SinnerEntity> dummySinnerEntityList = getDummySinnerEntityList(tortureDepartmentEntity, 10);


//        tortureDepartmentEntity.id = UUID.randomUUID().toString();

        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(tortureDepartmentEntity666);
        mRealm.copyToRealmOrUpdate(tortureDepartmentEntity);
        for (PunishmentToolEntity punishmentToolEntity : punishmentToolEntities) {
            mRealm.copyToRealmOrUpdate(punishmentToolEntity);
        }

        for (SinnerEntity sinnerEntity : dummySinnerEntityList) {
            mRealm.copyToRealmOrUpdate(sinnerEntity);
        }
        mRealm.commitTransaction();
    }

    @NonNull
    private static List<PunishmentToolEntity> getDummyPunishmentToolEntityList(TortureDepartmentEntity tortureDepartmentEntity) {
        List<PunishmentToolEntity> list = new ArrayList<>();

        list.add(new PunishmentToolEntity("Hummer", getRandomInt(), getRandomInt(), tortureDepartmentEntity));
        list.add(new PunishmentToolEntity("Heretics Fork", getRandomInt(), getRandomDouble(), tortureDepartmentEntity));
        list.add(new PunishmentToolEntity("Impalement", getRandomInt(), getRandomDouble(), tortureDepartmentEntity));
        list.add(new PunishmentToolEntity("Neck Torture", getRandomInt(), getRandomInt(), tortureDepartmentEntity));
        list.add(new PunishmentToolEntity("Saw Torture", getRandomInt(), getRandomInt(), tortureDepartmentEntity));
        list.add(new PunishmentToolEntity("Chair of torture", getRandomInt(), getRandomDouble(), tortureDepartmentEntity));
        list.add(new PunishmentToolEntity("Rack", getRandomInt(), getRandomInt(), tortureDepartmentEntity));
        list.add(new PunishmentToolEntity("Breast Ripper", getRandomInt(), getRandomInt(), tortureDepartmentEntity));

        return list;
    }

    @NonNull
    private static List<SinnerEntity> getDummySinnerEntityList(TortureDepartmentEntity tortureDepartmentEntity, int size) {
        List<SinnerEntity> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            boolean isMurderer = Math.random() < 0.5;
            if (isMurderer) {
                final SinnerEntity sinner = new SinnerEntity();
                RealmList<SufferingProcessEntity> sufferingProcessEntities = getDummySufferingProcesses(tortureDepartmentEntity, sinner);
                RealmList<DeadlySin> deadlySins = new RealmList<>();
                deadlySins.add(new DeadlySin("Anger"));
                deadlySins.add(new DeadlySin("Greed"));

                sinner.setLiar(false);
                sinner.setMurderer(true);
                sinner.setAmountOfVictims(getRandomInt());
                sinner.setBirthDate(dataFactory.getBirthDate());
                sinner.setSufferingProcessList(sufferingProcessEntities);
                sinner.setFirstName(dataFactory.getFirstName());
                sinner.setLastName(dataFactory.getLastName());
                sinner.setSinsList(deadlySins);
                list.add(sinner);

            } else {
                final SinnerEntity sinner = new SinnerEntity();
                RealmList<SufferingProcessEntity> sufferingProcessEntities = getDummySufferingProcesses(tortureDepartmentEntity, sinner);
                RealmList<DeadlySin> deadlySins = new RealmList<>();
                deadlySins.add(new DeadlySin("Envy"));
                deadlySins.add(new DeadlySin("Gluttony"));
                deadlySins.add(new DeadlySin("Pride"));
                deadlySins.add(new DeadlySin("Sloth"));

                sinner.setLiar(true);
                sinner.setMurderer(false);
                sinner.setAmountOfLies(getRandomInt());
                sinner.setBirthDate(dataFactory.getBirthDate());
                sinner.setSufferingProcessList(sufferingProcessEntities);
                sinner.setFirstName(dataFactory.getFirstName());
                sinner.setLastName(dataFactory.getLastName());
                sinner.setSinsList(deadlySins);
                list.add(sinner);
            }
        }

        return list;
    }

    private static RealmList<SufferingProcessEntity> getDummySufferingProcesses(TortureDepartmentEntity departmentEntity, SinnerEntity sinner) {
        RealmList<SufferingProcessEntity> sufferingProcessEntities = new RealmList<>();

        // TODO: 15/06/16 make RANDOM (1-3)
        final int size = 1;
        for (int i = 0; i < size; i++) {
            sufferingProcessEntities.add(new SufferingProcessEntity(getStartDateExample(), getEndDateExample(), departmentEntity, sinner));
        }

        return sufferingProcessEntities;
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

    public static Date getStartDateExample() {
        return new Date(700, 1, 1);
    }

    /**
     * return the example of end date of suffering process (to have more than 1000 years in result)
     *
     * @return
     */
    public static Date getEndDateExample() {
        return new Date(2900, 1, 1);
    }

}
