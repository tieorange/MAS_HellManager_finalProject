package tieorange.edu.hellmanager;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by tieorange on 03/06/16.
 */
public class HellManagerApplication extends Application {

    public static Realm mRealm;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    private void initRealm() {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);

        // Get a Realm instance for this thread
        mRealm = Realm.getDefaultInstance();
    }
}
