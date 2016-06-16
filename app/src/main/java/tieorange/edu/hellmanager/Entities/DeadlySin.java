package tieorange.edu.hellmanager.Entities;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tieorange on 16/06/16.
 */
public class DeadlySin extends RealmObject {
    @PrimaryKey
    private String name;

    public DeadlySin(String name) {
        this.name = name;
    }

    public DeadlySin() {
    }

    @NonNull
    public static List<DeadlySin> getSevenDeadlySins() {
        List<DeadlySin> sinsList = new ArrayList<>();
        sinsList.add(new DeadlySin("Pride"));
        sinsList.add(new DeadlySin("Envy"));
        sinsList.add(new DeadlySin("Gluttony"));
        sinsList.add(new DeadlySin("Anger"));
        sinsList.add(new DeadlySin("Greed"));
        sinsList.add(new DeadlySin("Sloth"));
        sinsList.add(new DeadlySin("Lust"));

        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            for (DeadlySin sin : sinsList) {
                realm.copyToRealmOrUpdate(sin);
            }
            realm.commitTransaction();
        } finally {
            if (realm != null)
                realm.close();
        }
        return sinsList;
    }

    @Override
    public String toString() {
        return "DeadlySin{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
