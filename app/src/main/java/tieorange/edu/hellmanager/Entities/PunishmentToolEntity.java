package tieorange.edu.hellmanager.Entities;

import java.text.MessageFormat;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import tieorange.edu.hellmanager.main.TortureDepartment;

/**
 * Created by tieorange on 03/06/16.
 */
public class PunishmentToolEntity extends RealmObject {

    @PrimaryKey
    public String id;

    public String name;
    public Integer damage;

    public Integer minTemperature;
    public Integer maxTemperature;

    public TortureDepartmentEntity tortureDepartment;


    @Override
    public String toString() {
        return MessageFormat.format("name = {0} \ndamage = {1}", name, damage);
    }
}
