package tieorange.edu.hellmanager.main.PunishmentTools;



import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import tieorange.edu.hellmanager.main.TortureDepartment;
import tieorange.edu.hellmanager.main.Torturers.IExploder;

/**
 * Created by tieorange on 22/04/16.
 */
// teacher
public abstract class PunishmentTool{
    private static final long serialVersionUID = -222864131214757024L;

    public int id;

    private String name;
    private int damage;

    private TortureDepartment tortureDepartment;

    private Ice elementalPowerIce;
    private Flame elementalPowerFlame;
//    private Enum elementalPower // TODO: 17/06/16

    //region Constructors
    public PunishmentTool(String name, int damage, TortureDepartment tortureDepartment, Integer minTemperature) {
        setName(name);
        setDamage(damage);
        setTortureDepartment(tortureDepartment);
        setElementalPowerIce(minTemperature);
    }

    public PunishmentTool(String name, int damage, TortureDepartment tortureDepartment, Double maxTemperature) {
        setName(name);
        setDamage(damage);
        setTortureDepartment(tortureDepartment);
        setElementalPowerFlame(maxTemperature);
    }
    //endregion

    //region Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name is NULL");
        } else {
            this.name = name;
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public TortureDepartment getTortureDepartment() {
        return tortureDepartment;
    }

    public void setTortureDepartment(TortureDepartment tortureDepartment) {
        if (this.tortureDepartment != tortureDepartment) {
            if (this.tortureDepartment != null) {
                this.tortureDepartment.removePunishmentTool(this);
            }
            this.tortureDepartment = tortureDepartment;
            this.tortureDepartment.addPunishmentTool(this);
        }
    }

    public Ice getElementalPowerIce() {
        return elementalPowerIce;
    }

    /**
     * set's the Ice elemental power
     * @param minTemperature minimal temperature
     */
    public void setElementalPowerIce(Integer minTemperature) {
        if (minTemperature != null) {
            this.elementalPowerIce = new Ice(minTemperature);
        } else throw new IllegalArgumentException("minTemperature is NULL");
    }


    public Flame getElementalPowerFlame() {
        return elementalPowerFlame;
    }

    /**
     * set's the Flame elemental power
     * @param maxTemperature maximal temperature
     */
    public void setElementalPowerFlame(Double maxTemperature) {
        if (maxTemperature != null) {
            this.elementalPowerFlame = new Flame(maxTemperature);
        } else throw new IllegalArgumentException("maxTemperature is NULL");
    }
    //endregion

    private abstract class ElementalPower {

    }

    private class Ice extends ElementalPower {
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

    private class Flame extends ElementalPower {
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
    }

}
