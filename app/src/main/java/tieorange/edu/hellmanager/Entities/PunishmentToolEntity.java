package tieorange.edu.hellmanager.Entities;

import java.text.MessageFormat;
import java.util.UUID;

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
    public Double maxTemperature;

    public TortureDepartmentEntity tortureDepartment;

    public PunishmentToolEntity() {
        id = UUID.randomUUID().toString();
    }

    public PunishmentToolEntity(String name, Integer damage, Integer minTemperature, TortureDepartmentEntity tortureDepartment) {
        this();
        this.name = name;
        this.damage = damage;
        this.minTemperature = minTemperature;
        this.tortureDepartment = tortureDepartment;
    }

    public PunishmentToolEntity(String name, Integer damage, Double maxTemperature, TortureDepartmentEntity tortureDepartment) {
        this();
        this.name = name;
        this.damage = damage;
        this.maxTemperature = maxTemperature;
        this.tortureDepartment = tortureDepartment;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} " +
                "\n\uD83D\uDD25damage = {1}", name, damage);
    }
}
