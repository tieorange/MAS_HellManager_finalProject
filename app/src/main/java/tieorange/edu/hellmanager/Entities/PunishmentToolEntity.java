package tieorange.edu.hellmanager.Entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import tieorange.edu.hellmanager.main.TortureDepartment;

/**
 * Created by tieorange on 03/06/16.
 */
public class PunishmentToolEntity extends RealmObject{

    @PrimaryKey
    public int id;

    private String name;
    private int damage;

    private TortureDepartmentEntity tortureDepartment;

   /* private Ice elementalPowerIce;
    private Flame elementalPowerFlame;

    private class Ice extends RealmObject {
        @PrimaryKey
        private int id;

        private Integer minTemperature;

        private Ice(int minTemperature) {
            setMinTemperature(minTemperature);
        }

        public Integer getMinTemperature() {
            return minTemperature;
        }

        public void setMinTemperature(Integer minTemperature) {
            if (minTemperature != null) {
                this.minTemperature = minTemperature;
            } else throw new IllegalArgumentException("minTemperature is NULL");
        }
    }

    private class Flame extends RealmObject {
        @PrimaryKey
        private int id;

        private Double maxTemperature;

        private Flame(Double maxTemperature) {
            this.maxTemperature = maxTemperature;
        }

        public Double getMaxTemperature() {
            return maxTemperature;
        }

        public void setMaxTemperature(Double maxTemperature) {
            if (maxTemperature != null) {
                this.maxTemperature = maxTemperature;
            } else throw new IllegalArgumentException("maxTemperature is NULL");
        }
    }*/
}
